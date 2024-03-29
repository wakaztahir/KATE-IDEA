package com.github.wakaztahir.kateidea.lexer.states.value

import com.github.wakaztahir.kateidea.lexer.LexerState
import com.github.wakaztahir.kateidea.lexer.isAtCurrentPosition
import com.github.wakaztahir.kateidea.lexer.readTextAheadUntilLambdaOrStreamEnds
import com.github.wakaztahir.kateidea.lexer.states.Lexer
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.github.wakaztahir.kateidea.lexer.token.TypedToken
import com.wakaztahir.kate.lexer.stream.SourceStream

class StringValueLexer(
    private val stream: SourceStream,
    private val state : LexerState
) : Lexer {

    private fun resetState() {
        state.isLexingString = false
    }

    fun isLexing(): Boolean = state.isLexingString

    private fun lexStringUntilEndOrEscapeSeq(offset: Int, start: Int = offset, lengthOffset: Int = 0): TokenRange? {
        val text = stream.readTextAheadUntilLambdaOrStreamEnds(offset) { currChar, _ ->
            currChar == '\n' || currChar == '"' || currChar == '\\'
        } ?: (if (stream.lookAhead(offset) == '"') "" else return null)
        val currChar = stream.lookAhead(offset + text.length)
        return TokenRange(
            start = stream.pointer + start,
            token = TypedToken.StringValue(text),
            length = text.length + (if (currChar != null && currChar != '\\') 1 else 0) + lengthOffset,
            onIncrement = when (currChar) {
                '\\' -> {
                    {
                        state.isLexingString = true
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

    private fun lexEscapeToken(offset: Int, start: Int = offset, lengthOffset: Int = 0): TokenRange? {
        return if (stream.lookAhead(offset) == '\\') {
            stream.lookAhead(offset + 1)?.let { escapeChar ->
                val realValue = CharValueLexer.transformCharAfterBackslash(escapeChar)
                TokenRange(
                    start = stream.pointer + start,
                    token = TypedToken.CharEscape(realValue ?: escapeChar, isValid = realValue != null),
                    length = 2 + lengthOffset,
                    onIncrement = if (!state.isLexingString) {
                        { state.isLexingString = true }
                    } else null
                )
            }
        } else {
            null
        }
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (state.isLexingString) {
            lexEscapeToken(offset = offset)?.let { return it }
            lexStringUntilEndOrEscapeSeq(offset = offset)?.let { return it }
        } else if (stream.isAtCurrentPosition(offset = offset, '"')) {
            val start = offset + 1
            lexEscapeToken(offset = start, start = offset,lengthOffset = 1)?.let { return it }
            lexStringUntilEndOrEscapeSeq(offset = start, start = offset, lengthOffset = 1)?.let { return it }
        }
        return null
    }

}