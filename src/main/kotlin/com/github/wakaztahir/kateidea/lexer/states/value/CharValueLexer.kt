package com.github.wakaztahir.kateidea.lexer.states.value

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.isAtCurrentPosition
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.github.wakaztahir.kateidea.lexer.states.ValueLexer
import com.wakaztahir.kate.lexer.stream.SourceStream

object CharValueLexer : ValueLexer {

    fun transformCharAfterBackslash(char: Char): Char? {
        return when (char) {
            'b' -> '\b'
            'n' -> '\n'
            'r' -> '\r'
            't' -> '\t'
            '\\' -> '\\'
            '\'' -> '\''
            '\"' -> '\"'
            else -> null
        }
    }

    override fun lexTokenAtPosition(stream: SourceStream, offset: Int): TokenRange? = with(stream) {
        if (isAtCurrentPosition(offset = offset, char = '\'')) {
            val current = lookAhead(offset = offset + 1)
            val isEscape = current == '\\'
            val escapeOffset = if (isEscape) 1 else 0
            val escapeChar = lookAhead(offset = offset + 2) ?: return@with null
            val characterValue = (if (current == '\\') {
                transformCharAfterBackslash(escapeChar)
            } else current)
            return if (lookAhead(offset = offset + 2 + escapeOffset) == '\'') {
                return TokenRange(
                    start = pointer + offset,
                    token = if (isEscape || characterValue == null) {
                        KATEToken.StringEscape(
                            value = characterValue ?: escapeChar,
                            isValid = characterValue != null
                        )
                    } else KATEToken.CharValue(characterValue),
                    length = 3 + escapeOffset,
                    onIncrement = null
                )
            } else null
        }
        return@with null
    }

}