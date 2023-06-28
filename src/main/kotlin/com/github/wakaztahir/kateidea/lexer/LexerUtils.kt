package com.github.wakaztahir.kateidea.lexer

import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.SourceStream

@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.range(
    offset: Int,
    token: KATEToken,
    noinline onIncrement: (() -> Unit)? = null
): TokenRange {
    return TokenRange(start = pointer + offset, token = token, length = token.length, onIncrement = onIncrement)
}

@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.range(
    offset: Int,
    lengthOffset: Int,
    token: KATEToken,
    noinline onIncrement: (() -> Unit)? = null
): TokenRange {
    return TokenRange(
        start = pointer + offset,
        token = token,
        length = token.length + lengthOffset,
        onIncrement = onIncrement
    )
}

fun SourceStream.lexWhitespaces(
    offset: Int,
    onIncrement: (() -> Unit)? = null
): TokenRange? {
    return readTextAheadUntilLambdaOrStreamEnds(offset) { currChar, _ -> currChar != ' ' }?.let {
        range(offset = offset, token = KATEToken.Whitespace(it.length), onIncrement = onIncrement)
    }
}

fun SourceStream.lexAsBadCharacters(
    offset: Int,
    onIncrement: (() -> Unit)? = null
): TokenRange? {
    return readTextAheadUntilLambdaOrStreamEnds(offset) { currChar, _ -> false }?.let {
        range(offset = offset, token = KATEToken.BadCharacter(it.length), onIncrement = onIncrement)
    }
}

fun SourceStream.directiveRangeAtPosition(
    isDefaultNoRaw: Boolean,
    offset: Int,
    directive: KATEToken.String,
    onIncrement: (() -> Unit)?
): TokenRange? {
    val startOffset = if (isAtCurrentPosition(offset = offset, KATETokens.At.value)) 1 else {
        if (isDefaultNoRaw) return null else 0
    }
    if (isAtCurrentPositionText(offset = offset + startOffset, directive.value)) {
        return range(
            offset = offset,
            lengthOffset = startOffset,
            token = directive,
            onIncrement = onIncrement
        )
    }
    return null
}