package com.github.wakaztahir.kateidea.lexer.states.value

import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.github.wakaztahir.kateidea.lexer.state.state
import com.github.wakaztahir.kateidea.lexer.states.AccessChainLexer
import com.github.wakaztahir.kateidea.lexer.states.Lexer
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.SourceStream

class DefaultExpressionValueLexer(
    private val source: SourceStream,
    val isDefaultNoRaw: Boolean
) : Lexer, CompositeLexState() {

    enum class State {
        None,
        ValueOnly
    }

    private var state by state(State.None)

    private val valueLexer by lazyState { PrimitiveValueLexer(source = source) }
    private val accessChainLexer by lazyState { AccessChainLexer(source = source, isDefaultNoRaw = isDefaultNoRaw) }

    fun isLexing() = accessChainLexer.isLexing() || valueLexer.isLexing()

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        when (state) {
            State.None -> {
                accessChainLexer.lexTokenAtPosition(offset)?.let {
                    return it
                }
                if (!accessChainLexer.isLexing()) {
                    valueLexer.lexTokenAtPosition(offset)?.let {
                        return it.alsoOnIncrement {
                            if (valueLexer.isLexing()) state = State.ValueOnly
                        }
                    }
                }
            }

            State.ValueOnly -> {
                valueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement {
                        if (!valueLexer.isLexing()) state = State.None
                    }
                }
            }
        }
        return null
    }
}