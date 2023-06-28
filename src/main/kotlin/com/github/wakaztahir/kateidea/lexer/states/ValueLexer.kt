package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.wakaztahir.kate.lexer.stream.SourceStream

class ValueLexer(
    private val source: SourceStream,
    private val isDefaultNoRaw: Boolean
) : Lexer, CompositeLexState() {
    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        return null
    }
}