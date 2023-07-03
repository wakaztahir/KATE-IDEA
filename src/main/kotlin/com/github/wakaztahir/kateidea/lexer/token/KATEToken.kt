package com.github.wakaztahir.kateidea.lexer.token

import com.github.wakaztahir.kateidea.lexer.TokenConverter

sealed interface KATEToken {

    val length: Int

    fun <T> convert(converter: TokenConverter<T>): T

    class BooleanValue(val value: Boolean, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class IntValue(val value: Int, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class DoubleValue(val value: Double, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class LongValue(val value: Long, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class ArithmeticOperator(val value: Char) : KATEToken {
        override val length: Int = 1
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class BadCharacter(override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class Whitespace(override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class ErrorToken(val message: String, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class OutputString(val value: String) : KATEToken {
        override val length: Int get() = value.length
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class Identifier(val text: String) : KATEToken {
        override val length: Int get() = text.length
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class CommentString(val value: String) : KATEToken {
        override val length: Int get() = value.length
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class Text(val value: String) : KATEToken {
        override val length: Int get() = value.length
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }


}