package com.github.wakaztahir.kateidea.lexer.states.value

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.range
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.github.wakaztahir.kateidea.lexer.states.ValueLexer
import com.wakaztahir.kate.lexer.stream.SourceStream
import com.wakaztahir.kate.lexer.stream.isAtCurrentPosition

object BooleanValueLexer : ValueLexer {

    private const val TrueValue = "true"
    private const val FalseValue = "false"

    override fun lexTokenAtPosition(stream: SourceStream, offset: Int): TokenRange? {
        if (stream.isAtCurrentPosition(TrueValue, offset = offset)) {
            return stream.range(offset, KATEToken.BooleanValue(true, TrueValue.length))
        } else if (stream.isAtCurrentPosition(FalseValue, offset = offset)) {
            return stream.range(offset, KATEToken.BooleanValue(false, FalseValue.length))
        }
        return null
    }

}