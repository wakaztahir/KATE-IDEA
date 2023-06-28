package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

data class TokenRange(
    val start: Int,
    val token: KATEToken,
    val length: Int,
    val onIncrement: (() -> Unit)?,
) {

    val end get() = start + length

    fun increment(source: SourceStream) {
        source.incrementPointer(length)
        onIncrement?.invoke()
    }

}