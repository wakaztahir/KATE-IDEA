package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

data class TokenRange(
    val start: Int,
    val token: KATEToken,
    private val onIncrement: (() -> Unit)?,
) {

    val end get() = start + token.length

    fun increment(source: SourceStream) {
        source.incrementPointer(token.length)
        onIncrement?.invoke()
    }

}