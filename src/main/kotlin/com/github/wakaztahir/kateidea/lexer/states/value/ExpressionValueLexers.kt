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

    private val accessChainLexerState = lazyState {
        AccessChainLexer(
            source = source,
            isDefaultNoRaw = isDefaultNoRaw,
            createNestedValueLexer = { DefaultExpressionValueLexer(source, isDefaultNoRaw) }
        )
    }
    private val valueLexerState = lazyState { PrimitiveValueLexer(source = source) }

    private val accessChainLexer get() = accessChainLexerState.state
    private val valueLexer get() = valueLexerState.state

    fun isLexing() = (accessChainLexerState.isInitialized() && accessChainLexer.isLexing())
            || (valueLexerState.isInitialized() && valueLexer.isLexing())

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (!(valueLexerState.isInitialized() && valueLexer.isLexing())) {
            accessChainLexer.lexTokenAtPosition(offset)?.let {
                return it
            }
        }
        if (!accessChainLexer.isLexing()) {
            valueLexer.lexTokenAtPosition(offset)?.let {
                return it
            }
        }
        return null
    }
}