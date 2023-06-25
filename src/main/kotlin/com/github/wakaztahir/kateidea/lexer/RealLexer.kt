package com.github.wakaztahir.kateidea.lexer

import com.github.wakaztahir.kateidea.lexer.states.DefaultNoRawLexer
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.*

class RealLexer(val source: TextSourceStream) {

    // States
    val current = DefaultNoRawLexer(source, false)

    // This is for testing only
    fun lexCurrentOld(): TokenRange? {
        if (source.hasEnded) return null
        val start = source.pointer
        return if (source.isAtCurrentPosition("if")) {
            TokenRange(start, KATETokens.If, 2)
        } else if (source.isAtCurrentPosition("var")) {
            TokenRange(start, KATETokens.Var, 3)
        } else {
            val text = source.readTextAheadUntil { currChar, offset ->
                if(currChar != null) {
                    if (currChar == 'i' || currChar == 'v') {
                        source.isAtCurrentPosition("if", offset = offset) ||
                                source.isAtCurrentPosition("var", offset = offset)
                    } else {
                        false
                    }
                } else true
            }
            TokenRange(start, KATEToken.DefaultNoRawString(text!!), text.length)
        }
    }

    private fun lexCurrentNew(): TokenRange? {
        if (source.hasEnded) return null
        return current.lexTokenAtCurrentPosition() ?: run {
            Throwable("couldn't lex token at :${source.readAllAheadFromCurrentPosition()}").printStackTrace()
            null
        }
    }

    fun lexCurrentToken(): TokenRange? {
        return lexCurrentNew()
    }

    fun incrementStream(token: TokenRange) {
        this.source.incrementPointer(token.length)
    }

    // TODO
    fun getState(): Int {
        return current.getState()
    }

    // TODO
    fun restoreState(state: Int) {
        current.restoreState(state)
    }

}