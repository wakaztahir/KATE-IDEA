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
        if (currChar != null && stopIf(currChar, x)) {
            return parsedText
        } else if (currChar != null) {
            parsedText += currChar
        }
        x++
    } while (currChar != null)
    return parsedText.ifEmpty { null }
}

fun SourceStream.readTextAheadUntil(offset: Int, stopIf: (currChar: Char?, offset: Int) -> Boolean): String? {
    var parsedText = ""
    var x = offset
    do {
        val currChar = lookAhead(x)
        if (stopIf(currChar, x)) {
            return parsedText
        } else if (currChar != null) {
            parsedText += currChar
        }
        x++
    } while (currChar != null)
    return null
}

@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.readTextAheadUntil(offset: Int, stopAt: String): String? {
    return readTextAheadUntil(offset) { currChar, currOffset ->
        currChar == null || (currChar == stopAt[0] && isAtCurrentPosition(stopAt, currOffset))
    }?.ifEmpty { null }
}


@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.readSingleWordAhead(offset: Int): String? {
    return readTextAheadUntil(offset = offset) { currChar, _ -> currChar == ' ' || currChar == null || currChar == '\n' }?.ifEmpty { null }
}

@Suppress("NOTHING_TO_INLINE")
inline fun SourceStream.isAtCurrentPositionFast(offset: Int, text: String): Boolean {
    return if (lookAhead(offset) == text[0]) isAtCurrentPositionText(offset = offset, text = text) else false
}