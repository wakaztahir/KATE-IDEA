package com.github.wakaztahir.kateidea.lexer.states.value

import com.github.wakaztahir.kateidea.lexer.LexerState
import com.github.wakaztahir.kateidea.lexer.states.AccessChainLexer
import com.github.wakaztahir.kateidea.lexer.states.Lexer
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.SourceStream

class DefaultExpressionValueLexer(
    private val source: SourceStream,
    private val state: LexerState,
    val isDefaultNoRaw: Boolean
) : Lexer {

    private val accessChainLexerState = lazy {
        AccessChainLexer(
            source = source,
            isDefaultNoRaw = isDefaultNoRaw,
            createNestedValueLexer = { DefaultExpressionValueLexer(source, state = state, isDefaultNoRaw) },
            state = state
        )
    }
    private val valueLexerState = lazy { PrimitiveValueLexer(source = source, state = state) }

    private val accessChainLexer get() = accessChainLexerState.value
    private val valueLexer get() = valueLexerState.value

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