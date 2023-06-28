package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.state.LexStateSaver

interface Lexer : LexStateSaver {

    fun lexTokenAtPosition(offset : Int): TokenRange?

}