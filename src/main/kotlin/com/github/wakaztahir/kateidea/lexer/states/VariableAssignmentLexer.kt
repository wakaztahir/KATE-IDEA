package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.github.wakaztahir.kateidea.lexer.state.state
import com.github.wakaztahir.kateidea.lexer.states.value.DefaultExpressionValueLexer
import com.github.wakaztahir.kateidea.lexer.states.value.PrimitiveValueLexer
import com.wakaztahir.kate.lexer.stream.SourceStream

class VariableAssignmentLexer(
    private val source: SourceStream,
    private val isDefaultNoRaw: Boolean
) : Lexer, CompositeLexState() {

    enum class State {
        None,
        VariableName,
        OperatorOrEqual,
        EqualOnly,
        Value
    }

    private var state by state(State.None)
    private val valueLexer by lazyState { DefaultExpressionValueLexer(source,isDefaultNoRaw) }

    private fun resetState() {
        state = State.None
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        when (state) {
            State.None -> {
                return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.SetVar) {
                    state = State.VariableName
                }
            }

            State.VariableName -> {
                source.lexWhitespaces(offset = offset)?.let { return it }
                val word = source.readSingleWordAhead(offset = offset)
                return word?.let {
                    source.range(offset, KATEToken.Identifier(it)) {
                        state = State.OperatorOrEqual
                    }
                }
            }

            State.OperatorOrEqual, State.EqualOnly -> {
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

                        '+', '-', '*', '/', '%' -> {
                            if (state == State.EqualOnly) {
                                return source.range(offset, KATEToken.BadCharacter(1), null)
                            } else {
                                return source.range(offset, KATEToken.ArithmeticOperator(char)) {
                                    state = State.EqualOnly
                                }
                            }
                        }

                        else -> {
                            return source.range(offset, KATEToken.BadCharacter(1), null)
                        }
                    }
                }
            }

            State.Value -> {
                source.lexWhitespaces(offset)?.let { return it }
                valueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement(
                        block = {
                            if (!valueLexer.isLexing()) resetState()
                        }
                    )
                }
                return source.lexAsBadCharacters(offset)
            }
        }
    }
}