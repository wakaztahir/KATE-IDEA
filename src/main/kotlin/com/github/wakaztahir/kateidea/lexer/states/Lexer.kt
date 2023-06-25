package com.github.wakaztahir.kateidea.lexer.states

interface Lexer {

    val hasLexed: Boolean

    fun lexTokenAtCurrentPosition(): TokenRange?

}