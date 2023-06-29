package com.github.wakaztahir.kateidea.lexer.states.value

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.isAtCurrentPosition
import com.github.wakaztahir.kateidea.lexer.readTextAheadUntilLambdaOrStreamEnds
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.github.wakaztahir.kateidea.lexer.states.Lexer
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.SourceStream

class StringValueLexer(private val stream: SourceStream) : Lexer, CompositeLexState() {

    private var isLexingString by state(false)

    private fun resetState() {
        isLexingString = false
    }

    fun isLexing(): Boolean = isLexingString

    private fun lexStringUntilEndOrEscapeSeq(offset: Int, start: Int = offset, lengthOffset: Int = 0): TokenRange? {
        val text = stream.readTextAheadUntilLambdaOrStreamEnds(offset) { currChar, _ ->
            currChar == '\n' || currChar == '"' || currChar == '\\'
        } ?: (if (stream.lookAhead(offset) == '"') "" else return null)
        val currChar = stream.lookAhead(offset + text.length)
        return TokenRange(
            start = stream.pointer + start,
            token = KATEToken.StringValue(text),
            length = text.length + (if (currChar != null && currChar != '\\') 1 else 0) + lengthOffset,
            onIncrement = when (currChar) {
                '\\' -> {
                    {
                        isLexingString = true
                    }
                }

                else -> {
                    {
                        resetState()
                    }
                }
            }
        )
    }

    private fun lexEscapeToken(offset: Int): TokenRange? {
        return if (stream.lookAhead(offset) == '\\') {
            stream.lookAhead(offset + 1)?.let { escapeChar ->
                val realValue = CharValueLexer.transformCharAfterBackslash(escapeChar)
                TokenRange(
                    start = stream.pointer + offset,
                    token = KATEToken.StringEscape(realValue ?: escapeChar, isValid = realValue != null),
                    length = 2,
                    onIncrement = null
                )
            }
        } else {
            null
        }
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (isLexingString) {
            lexEscapeToken(offset = offset)?.let { return it }
            lexStringUntilEndOrEscapeSeq(offset = offset)?.let { return it }
        } else if (stream.isAtCurrentPosition(offset = offset, '"')) {
            return lexStringUntilEndOrEscapeSeq(offset = offset + 1, start = offset, lengthOffset = 1)
        }
        return null
    }

}