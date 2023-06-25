package com.github.wakaztahir.kateidea.lexer

import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.SourceStream
import com.wakaztahir.kate.lexer.stream.increment

sealed interface KATEToken {

    val type: TokenType

    fun <T> convert(converter: TokenConverter<T>): T

    interface Ranger : KATEToken {
        fun range(stream: SourceStream): TokenRange
    }

    interface Static : Ranger {
        fun increment(stream: SourceStream)
    }

    abstract class String(val value: kotlin.String, override val type: TokenType) : Static {

        override fun increment(stream: SourceStream) {
            stream.increment(value)
        }

        override fun range(stream: SourceStream): TokenRange {
            return TokenRange(start = stream.pointer, token = this, length = value.length)
        }
    }

    abstract class Char(val value: kotlin.Char, override val type: TokenType) : Static {

        override fun increment(stream: SourceStream) {
            stream.increment(value)
        }

        override fun range(stream: SourceStream): TokenRange {
            return TokenRange(start = stream.pointer, token = this, length = 1)
        }

    }

    class ErrorToken(val message: kotlin.String) : KATEToken {

        override val type: TokenType = TokenType.BadCharacter

        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

    }

    class DefaultNoRawString(val value: kotlin.String) : Ranger {
        override val type: TokenType = TokenType.String
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun range(stream: SourceStream): TokenRange {
            return TokenRange(stream.pointer, this, value.length)
        }
    }


}