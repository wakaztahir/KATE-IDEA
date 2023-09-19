package com.github.wakaztahir.kateidea.lexer.states

interface Lexer {

    fun lexTokenAtPosition(offset : Int): TokenRange?

}