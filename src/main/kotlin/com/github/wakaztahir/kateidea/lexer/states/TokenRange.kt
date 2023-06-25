package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.KATEToken

data class TokenRange(val start: Int, val token: KATEToken, val length: Int) {
    val end get() = start + length
}