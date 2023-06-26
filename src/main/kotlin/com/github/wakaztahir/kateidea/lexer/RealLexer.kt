package com.github.wakaztahir.kateidea.lexer

import com.github.wakaztahir.kateidea.lexer.states.DefaultNoRawLexer
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.*

class RealLexer(val source: TextSourceStream) {

    // States
    val current = DefaultNoRawLexer(source, false)

    private fun lexToken(offset: Int): TokenRange? {
        return current.lexTokenAtPosition(offset)
    }

    fun lexCurrentToken(): TokenRange? {
        return lexToken(0)
    }

    fun getState(): Int {
        return current.toState()
    }

    fun restoreState(state: Int) {
        current.restoreState(state)
    }

}