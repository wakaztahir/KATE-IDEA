package com.github.wakaztahir.kateidea.lexer.states.value

import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.github.wakaztahir.kateidea.lexer.isAtCurrentPosition
import com.github.wakaztahir.kateidea.lexer.range
import com.github.wakaztahir.kateidea.lexer.readTextAheadUntilLambdaOrStreamEnds
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.github.wakaztahir.kateidea.lexer.states.ValueLexer
import com.wakaztahir.kate.lexer.stream.SourceStream

object NumberValueLexer : ValueLexer {

    override fun lexTokenAtPosition(stream: SourceStream, offset: Int): TokenRange? = with(stream) {

        val isNegative = stream.isAtCurrentPosition(offset, '-')

        var currentOffset = if (isNegative) offset + 1 else offset

        val textValue = stream.readTextAheadUntilLambdaOrStreamEnds(currentOffset) { currChar, _ ->
            !currChar.isDigit()
        } ?: return null

        currentOffset += textValue.length

        val wholeValue = (if(isNegative) "-" else "") + textValue

        if (stream.isAtCurrentPosition(currentOffset, '.')) { // Fraction

            val fractionPart = stream.readTextAheadUntilLambdaOrStreamEnds(currentOffset + 1) { currChar, _ ->
                !currChar.isDigit()
            } ?: return null

            val completeNumber = "$wholeValue.$fractionPart"

            completeNumber.toDoubleOrNull()?.let {
                return stream.range(offset, KATEToken.DoubleValue(it, completeNumber.length))
            }

        } else if (stream.isAtCurrentPosition(currentOffset, 'L')) {
            wholeValue.toLongOrNull()?.let {
                return stream.range(offset, KATEToken.LongValue(it, wholeValue.length + 1))
            }
        } else {
            wholeValue.toIntOrNull()?.let {
                return stream.range(offset, KATEToken.IntValue(it, wholeValue.length))
            }
        }

        return null
    }

}