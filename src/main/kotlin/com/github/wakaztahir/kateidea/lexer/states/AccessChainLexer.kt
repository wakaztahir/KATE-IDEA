package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.github.wakaztahir.kateidea.lexer.state.state
import com.github.wakaztahir.kateidea.lexer.states.value.PrimitiveValueLexer
import com.wakaztahir.kate.lexer.stream.SourceStream

class AccessChainLexer(
    private val source: SourceStream,
    private val isDefaultNoRaw: Boolean
) : Lexer, CompositeLexState() {

    enum class State {
        None,
        DPB, // DPB = Dot or Parenthesis or Brackets
        MustIdentifier,
        ParameterOrEndingParenthesis,
        Parameter,
        CommaOrEndingParenthesis,
        EndingBracket,
        SingleParameter
    }

    private var state by state(State.None)
    private val valueLexer by lazyState { PrimitiveValueLexer(source) }

    private fun Char.isDPB() = this == '(' || this == '[' || this == '.'

    fun isLexing(): Boolean = state != State.None || valueLexer.isLexing()

    private fun resetState() {
        state = State.None
    }

    private fun lexDPB(offset: Int): TokenRange? {
        val current = source.lookAhead(offset) ?: return null
        when (current) {
            '.' -> {
                return source.range(offset, KATETokens.Dot) {
                    state = State.MustIdentifier
                }
            }

            '(' -> {
                return source.range(offset, KATETokens.LeftParenthesis) {
                    state = State.ParameterOrEndingParenthesis
                }
            }

            '[' -> {
                return source.range(offset, KATETokens.LeftBracket) {
                    state = State.SingleParameter
                }
            }

            else -> return null
        }
    }

    private fun dpbStateIncrementerIfExistsAt(offset: Int): () -> Unit {
        val isDPBNext = source.lookAhead(offset)?.isDPB() == true
        return if (isDPBNext) {
            { state = State.DPB }
        } else {
            { state = State.None }
        }
    }

    private fun lexIdentifier(offset: Int): TokenRange? {
        if (source.lookAhead(offset)?.isLetter() == true) {
            val text =
                source.readTextAheadUntilLambdaOrStreamEnds(offset) { currChar, _ -> !currChar.isLetterOrDigit() }
            return text?.let {
                source.range(
                    offset = offset,
                    token = KATEToken.Identifier(it),
                    onIncrement = dpbStateIncrementerIfExistsAt(offset + it.length)
                )
            }
        }
        return null
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        when (state) {
            State.None -> {
                return lexIdentifier(offset)
            }

            State.DPB -> {
                return lexDPB(offset) ?: source.lexAsBadCharacters(offset)
            }

            State.MustIdentifier -> {
                return lexIdentifier(offset) ?: source.lexAsBadCharacters(offset)
            }

            State.ParameterOrEndingParenthesis -> {
                if (source.lookAhead(offset) == ')') {
                    return source.range(offset, KATETokens.RightParenthesis, onIncrement = dpbStateIncrementerIfExistsAt(offset + 1))
                }
                valueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement {
                        if (!valueLexer.isLexing()) state = State.CommaOrEndingParenthesis
                    }
                }
                return null
            }

            State.CommaOrEndingParenthesis -> {
                source.lookAhead(offset)?.let {
                    when (it) {
                        ')' -> {
                            return source.range(offset, KATETokens.RightParenthesis, onIncrement = dpbStateIncrementerIfExistsAt(offset + 1))
                        }

                        ',' -> return source.range(offset, KATETokens.Comma) {
                            state = State.Parameter
                        }

                        else -> {}
                    }
                }
                return null
            }

            State.Parameter -> {
                valueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement(
                        block = {
                            if (!valueLexer.isLexing()) state = State.CommaOrEndingParenthesis
                        }
                    )
                }
                return source.lexAsBadCharacters(offset)
            }

            State.EndingBracket -> {
                return if (source.lookAhead(offset) == ']') {
                    source.range(offset, KATETokens.RightBracket, onIncrement = dpbStateIncrementerIfExistsAt(offset + 1))
                } else {
                    source.lexAsBadCharacters(offset)
                }
            }

            State.SingleParameter -> {
                return valueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement(
                        block = {
                            if (!valueLexer.isLexing()) state = State.EndingBracket
                        }
                    )
                } ?: source.lexAsBadCharacters(offset)
            }
        }
    }

}