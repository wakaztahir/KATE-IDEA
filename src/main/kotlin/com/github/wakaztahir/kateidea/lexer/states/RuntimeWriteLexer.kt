package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.github.wakaztahir.kateidea.lexer.state.state
import com.github.wakaztahir.kateidea.lexer.states.value.DefaultExpressionValueLexer
import com.github.wakaztahir.kateidea.lexer.states.value.PrimitiveValueLexer
import com.wakaztahir.kate.lexer.stream.SourceStream
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor

class RuntimeWriteLexer(
    private val source: SourceStream,
    private val isDefaultNoRaw: Boolean
) : Lexer, CompositeLexState() {

    enum class State {
        None,
        OpenParenthesis,
        Value,
        ClosingParenthesis
    }

    private var state by state(State.None)
    private val valueLexer by lazyState { DefaultExpressionValueLexer(source, isDefaultNoRaw) }

    private fun resetState() {
        state = State.None
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        when (state) {
            State.None -> {
                return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.Write) {
                    state = State.OpenParenthesis
                }
            }

            State.OpenParenthesis -> {
                if (source.lookAhead(offset) == KATETokens.LeftParenthesis.value) {
                    return source.range(offset = offset, KATETokens.LeftParenthesis) {
                        state = State.Value
                    }
                }
                return source.lexAsBadCharacters(offset)
            }

            State.Value -> {
                valueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement(
                        block = {
                            if (!valueLexer.isLexing()) {
                                state = State.ClosingParenthesis
                            }
                        }
                    )
                }
                return source.lexAsBadCharacters(offset)
            }

            State.ClosingParenthesis -> {
                if (source.lookAhead(offset) == KATETokens.RightParenthesis.value) {
                    return source.range(offset = offset, KATETokens.RightParenthesis) {
                        state = State.None
                    }
                }
                return source.lexAsBadCharacters(offset)
            }
        }
    }

}