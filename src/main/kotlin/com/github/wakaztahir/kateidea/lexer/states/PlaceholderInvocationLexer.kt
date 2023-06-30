package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.github.wakaztahir.kateidea.lexer.state.state
import com.github.wakaztahir.kateidea.lexer.states.value.PrimitiveValueLexer
import com.wakaztahir.kate.lexer.stream.SourceStream

class PlaceholderInvocationLexer(private val source: SourceStream, private val isDefaultNoRaw: Boolean) : Lexer,
    CompositeLexState() {

    enum class State {
        None,
        LeftParenthesis,
        PlaceholderName,
        FirstCommaOrRightParenthesis,
        DefinitionName,
        Value,
        RightParenthesis
    }

    private var state by state(State.None)
    private val valueLexer by lazyState { PrimitiveValueLexer(source) }

    private fun resetState() {
        state = State.None
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        when (state) {
            State.None -> {
                return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.Placeholder) {
                    state = State.LeftParenthesis
                }
            }

            State.LeftParenthesis -> {
                if (source.lookAhead(offset) == KATETokens.LeftParenthesis.value) {
                    return source.range(offset = offset, KATETokens.LeftParenthesis) {
                        state = State.PlaceholderName
                    }
                }
                return source.lexAsBadCharacters(offset)
            }

            State.PlaceholderName -> {
                val text =
                    source.readTextAheadUntilLambdaOrStreamEnds(offset) { currChar, _ -> currChar == ',' || currChar == ')' }
                return text?.let {
                    source.range(
                        offset = offset,
                        token = KATEToken.Identifier(text),
                        onIncrement = {
                            state = State.FirstCommaOrRightParenthesis
                        }
                    )
                }
            }

            State.FirstCommaOrRightParenthesis -> {
                return source.lookAhead(offset)?.let {
                    when (it) {
                        ')' -> source.range(offset = offset, KATETokens.RightParenthesis) {
                            state = State.None
                        }

                        ',' -> source.range(offset = offset, KATETokens.Comma) {
                            state = State.DefinitionName
                        }

                        else -> source.range(offset = offset, KATEToken.BadCharacter(1))
                    }
                }
            }

            State.DefinitionName -> {
                val currChar = source.lookAhead(offset)
                if (currChar != null) {
                    if (currChar == ',') {
                        return source.range(offset = offset, KATETokens.Comma) {
                            state = State.Value
                        }
                    }
                    if (currChar == ')') {
                        return source.range(offset = offset, KATETokens.RightParenthesis) {
                            state = State.None
                        }
                    }
                    val text = source.readTextAheadUntilLambdaOrStreamEnds(offset){ currChar, offset -> currChar == ',' || currChar == ')' }
                    return text?.let {
                        source.range(offset = offset, KATEToken.Identifier(it)) {
                            state = State.Value
                        }
                    }
                } else return null
            }

            State.Value -> {
                if (source.lookAhead(offset) == ',') {
                    return source.range(offset = offset, KATETokens.Comma) {
                        state = State.Value
                    }
                }
                if (source.lookAhead(offset) == ')') {
                    return source.range(offset = offset, KATETokens.RightParenthesis) {
                        state = State.None
                    }
                }
                valueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement(
                        block = {
                            if (!valueLexer.isLexing()) {
                                state = State.RightParenthesis
                            }
                        }
                    )
                }
                return source.lexAsBadCharacters(offset = offset, null)
            }

            State.RightParenthesis -> {
                if (source.lookAhead(offset) == KATETokens.RightParenthesis.value) {
                    return source.range(offset = offset, KATETokens.RightParenthesis) {
                        state = State.None
                    }
                }
                return source.lexAsBadCharacters(offset = offset, null)
            }
        }
    }

}