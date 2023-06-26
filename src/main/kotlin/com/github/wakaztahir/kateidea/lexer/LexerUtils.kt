package com.github.wakaztahir.kateidea.lexer

import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.SourceStream

@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.range(
    offset: Int,
    token: KATEToken,
    noinline onIncrement: (() -> Unit)? = null
): TokenRange {
    return TokenRange(start = pointer + offset, token = token, onIncrement = onIncrement)
}