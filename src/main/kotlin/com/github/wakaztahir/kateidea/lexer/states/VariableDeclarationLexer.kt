package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.github.wakaztahir.kateidea.lexer.state.state
import com.github.wakaztahir.kateidea.lexer.states.value.PrimitiveValueLexer
import com.wakaztahir.kate.lexer.stream.SourceStream

class VariableDeclarationLexer(
    private val source: SourceStream,
    private val isDefaultNoRaw: Boolean
) : Lexer, CompositeLexState() {

    enum class State {
        None,
        VariableName,
        EqualOnly,
        Value
    }

    private var state by state(State.None)
    private val valueLexer by lazyState { PrimitiveValueLexer(source) }

    private fun resetState() {
        state = State.None
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        when (state) {
            State.None -> {
                return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.Var) {
                    state = State.VariableName
                }
            }

            State.VariableName -> {
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

            State.Value -> {
                source.lexWhitespaces(offset)?.let { return it }
                valueLexer.lexTokenAtPosition(offset)?.let {
                    return it.copy(
                        onIncrement = {
                            it.onIncrement?.invoke()
                            if (!valueLexer.isLexing()) resetState()
                        }
                    )
                }
                return source.lexAsBadCharacters(offset)
            }
        }
    }
}