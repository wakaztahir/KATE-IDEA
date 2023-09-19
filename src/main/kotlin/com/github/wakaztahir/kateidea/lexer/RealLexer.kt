package com.github.wakaztahir.kateidea.lexer

import com.github.wakaztahir.kateidea.lexer.states.DefaultNoRawLexer
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.*

class RealLexer(val source: TextSourceStream) {

    // States
    val state = LexerState()
    val current = DefaultNoRawLexer(source, state, false)

    private fun lexToken(offset: Int): TokenRange? {
        return current.lexTokenAtPosition(offset)
    }

    fun lexCurrentToken(): TokenRange? {
        return lexToken(0)
    }

    fun getState(): Int {
        return state.save()
    }

    fun restoreState(intState: Int) {
        state.restore(intState)
    }

}