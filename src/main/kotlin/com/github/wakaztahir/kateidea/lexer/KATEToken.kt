package com.github.wakaztahir.kateidea.lexer

import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.SourceStream

sealed interface KATEToken {

    val length: Int

    fun <T> convert(converter: TokenConverter<T>): T

    abstract class String(val value: kotlin.String) : KATEToken {
        override val length: Int
            get() = value.length
    }

    abstract class Char(val value: kotlin.Char) : KATEToken {
        override val length: Int
            get() = 1
    }

    class ErrorToken(val message: kotlin.String, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class DefaultNoRawString(val value: kotlin.String) : KATEToken {
        override val length: Int
            get() = value.length

        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class CommentString(val value: kotlin.String) : KATEToken {
        override val length: Int
            get() = value.length

        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }


}