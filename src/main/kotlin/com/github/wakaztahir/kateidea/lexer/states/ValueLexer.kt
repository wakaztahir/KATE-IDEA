package com.github.wakaztahir.kateidea.lexer.states

import com.wakaztahir.kate.lexer.stream.SourceStream

interface ValueLexer {

    fun lexTokenAtPosition(stream: SourceStream, offset: Int): TokenRange?

}