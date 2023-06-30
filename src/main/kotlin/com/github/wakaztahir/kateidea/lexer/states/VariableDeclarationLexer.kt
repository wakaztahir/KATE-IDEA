package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.github.wakaztahir.kateidea.lexer.state.state
import com.github.wakaztahir.kateidea.lexer.states.value.DefaultExpressionValueLexer
import com.github.wakaztahir.kateidea.lexer.states.value.PrimitiveValueLexer
import com.wakaztahir.kate.lexer.stream.SourceStream

class VariableDeclarationLexer(
    private val source: SourceStream,
    private val isDefaultNoRaw: Boolean
) : Lexer, CompositeLexState() {

    enum class State {
        None,
        VariableNameOrParenthesis,
        EqualOnly,
        Value,
        RefValue,
        RightParenthesis
    }

    private var state by state(State.None)
    private val valueLexer by lazyState { DefaultExpressionValueLexer(source, isDefaultNoRaw = isDefaultNoRaw) }

    private fun resetState() {
        state = State.None
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        when (state) {
            State.None -> {
                return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.Var) {
                    state = State.VariableNameOrParenthesis
                }
            }

            State.VariableNameOrParenthesis -> {
                if (isDefaultNoRaw && source.lookAhead(offset) == '(') {
                    return source.range(offset, KATETokens.LeftParenthesis) {
                        state = State.RefValue
                    }
                }
                source.lexWhitespaces(offset = offset)?.let { return it }
                val word = source.readSingleWordAhead(offset = offset)
                return word?.let {
                    source.range(offset, KATEToken.Identifier(it)) {
                        state = State.EqualOnly
                    }
                }
            }

            State.EqualOnly -> {
                return source.lookAhead(offset)?.let { char ->
                    when (char) {
                        ' ' -> {
                            return source.range(offset, KATEToken.Whitespace(1), null)
                        }

                        '=' -> {
                            return source.range(offset, KATETokens.SingleEqual) {
                                state = State.Value
                            }
                        }

                        else -> {
                            return source.range(offset, KATEToken.BadCharacter(1), null)
                        }
                    }
                }
            }

            State.Value, State.RefValue -> {
                if (state != State.RefValue) {
                    source.lexWhitespaces(offset)?.let { return it }
                }
                valueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement(
                        block = when (state) {
                            State.Value -> {
                                {
                                    if (!valueLexer.isLexing()) resetState()
                                }
                            }

                            State.RefValue -> {
                                {
                                    if (!valueLexer.isLexing()) state = State.RightParenthesis
                                }
                            }

                            else -> {
                                error("Not supposed to be here")
                            }
                        }
                    )
                }
                return source.lexAsBadCharacters(offset)
            }

            State.RightParenthesis -> {
                source.lookAhead(offset)?.let {
                    if (it == ')') {
                        return source.range(offset, KATETokens.RightParenthesis) {
                            resetState()
                        }
                    }
                    return source.lexAsBadCharacters(offset)
                }
            }
        }
        return null
    }
}