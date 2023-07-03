package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.state.*
import com.github.wakaztahir.kateidea.lexer.states.value.DefaultExpressionValueLexer
import com.github.wakaztahir.kateidea.lexer.states.value.PrimitiveValueLexer
import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

class AccessChainLexer(
    private val source: SourceStream,
    private val isDefaultNoRaw: Boolean,
    private val createNestedValueLexer : () -> DefaultExpressionValueLexer
) : Lexer, CompositeLexState() {

    enum class State {
        None,
        DPB, // DPB = Dot or Parenthesis or Brackets
        MustIdentifier,
        ParameterOrEndingParenthesis,
        Parameter,
        CommaOrEndingParenthesis,
        NestedValue,
        EndingBracket,
        SingleParameter
    }

    private var state: State by state(State.None)

    private val nestedValueLexerState = lazyState(createNestedValueLexer)
    private val nestedValueLexer by nestedValueLexerState

    private fun Char.isDPB() = this == '(' || this == '[' || this == '.'
    fun isLexing(): Boolean = state != State.None || (nestedValueLexerState.isInitialized() && nestedValueLexer.isLexing())

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

            State.NestedValue -> {
                return nestedValueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement {
                        if (!nestedValueLexer.isLexing()) state = State.EndingBracket
                    }
                } ?: source.lexAsBadCharacters(offset)
            }

            State.ParameterOrEndingParenthesis -> {
                if (source.lookAhead(offset) == ')') {
                    return source.range(
                        offset,
                        KATETokens.RightParenthesis,
                        onIncrement = dpbStateIncrementerIfExistsAt(offset + 1)
                    )
                }
                nestedValueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement {
                        if (!nestedValueLexer.isLexing()) state = State.CommaOrEndingParenthesis
                    }
                }
                return null
            }

            State.CommaOrEndingParenthesis -> {
                source.lookAhead(offset)?.let {
                    when (it) {
                        ')' -> {
                            return source.range(
                                offset,
                                KATETokens.RightParenthesis,
                                onIncrement = dpbStateIncrementerIfExistsAt(offset + 1)
                            )
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
                nestedValueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement(
                        block = {
                            if (!nestedValueLexer.isLexing()) state = State.CommaOrEndingParenthesis
                        }
                    )
                }
                return source.lexAsBadCharacters(offset)
            }

            State.EndingBracket -> {
                return if (source.lookAhead(offset) == ']') {
                    source.range(
                        offset,
                        KATETokens.RightBracket,
                        onIncrement = dpbStateIncrementerIfExistsAt(offset + 1)
                    )
                } else {
                    source.lexAsBadCharacters(offset)
                }
            }

            State.SingleParameter -> {
                return nestedValueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement {
                        if (!nestedValueLexer.isLexing()) state = State.EndingBracket
                    }
                } ?: source.lexAsBadCharacters(offset)
            }
        }
    }

}