package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

data class TokenRange(
    val start: Int,
    val token: KATEToken,
    val length: Int,
    private val onIncrement: (() -> Unit)?,
) {

    val end get() = start + length

    fun increment(source: SourceStream) {
        source.incrementPointer(length)
        onIncrement?.invoke()
    }

    fun alsoOnIncrement(block: () -> Unit): TokenRange {
        return copy(
            onIncrement = if (onIncrement == null) block else {
                {
                    onIncrement.invoke()
                    block()
                }
            }
        )
    }

}