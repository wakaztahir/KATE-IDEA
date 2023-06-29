package com.github.wakaztahir.kateidea.lexer

import com.wakaztahir.kate.lexer.model.StaticToken
import com.wakaztahir.kate.lexer.stream.SourceStream
import com.wakaztahir.kate.lexer.stream.isAtCurrentPosition
import com.wakaztahir.kate.lexer.stream.readTextAheadUntil

// ----------- Char Utils

@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.isAtCurrentPosition(offset: Int, char: Char): Boolean {
    return lookAhead(offset = offset) == char
}

@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.isAtCurrentPosition(offset: Int, token: StaticToken.Char): Boolean {
    return isAtCurrentPosition(offset = offset, char = token.representation)
}

// ------------- String Utils

fun SourceStream.isAtCurrentPositionText(offset: Int, text: String): Boolean {
    var x = 0
    while (x < text.length) {
        lookAhead(x + offset)?.let {
            if (text[x] != it) {
                return false
            } else {
                x++
            }
        } ?: break
    }
    return x == text.length
}

fun SourceStream.readTextAheadUntilLambdaOrStreamEnds(
    offset: Int,
    stopIf: (currChar: Char, offset: Int) -> Boolean
): String? {
    var parsedText = ""
    var x = offset
    do {
        val currChar = lookAhead(x)
        if (currChar != null) {
            if (stopIf(currChar, x)) {
                return parsedText.ifEmpty { null }
            } else {
                parsedText += currChar
            }
            x++
        }
    } while (currChar != null)
    return parsedText.ifEmpty { null }
}

@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.readTextAheadUntil(offset: Int, stopAt: Char): String? {
    return readTextAheadUntilLambdaOrStreamEnds(offset) { currChar, _ -> currChar == stopAt }
}

@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.readTextAheadUntil(offset: Int, stopAt: String): String? {
    return readTextAheadUntilLambdaOrStreamEnds(offset) { currChar, currOffset ->
        (currChar == stopAt[0] && isAtCurrentPosition(stopAt, currOffset))
    }
}


@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.readSingleWordAhead(offset: Int): String? {
    return readTextAheadUntilLambdaOrStreamEnds(offset = offset) { currChar, _ -> currChar == ' ' || currChar == '\n' }
}

@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.isAtCurrentPositionFast(offset: Int, text: String): Boolean {
    return if (lookAhead(offset) == text[0]) isAtCurrentPositionText(offset = offset, text = text) else false
}