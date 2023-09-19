package com.github.wakaztahir.kateidea.lexer.token

import com.github.wakaztahir.kateidea.lexer.TokenConverter

sealed interface KATEToken {

    val length: Int

    override fun toString() : String

    fun <T> convert(converter: TokenConverter<T>): T

    class BooleanValue(val value: Boolean, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return value.toString()
        }
    }

    class IntValue(val value: Int, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return value.toString()
        }
    }

    class DoubleValue(val value: Double, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return value.toString()
        }
    }

    class LongValue(val value: Long, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return value.toString()
        }
    }

    class ArithmeticOperator(val value: Char) : KATEToken {
        override val length: Int = 1
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return value.toString()
        }
    }

    class BadCharacter(override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return ""
        }
    }

    class Whitespace(override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return " "
        }
    }

    class ErrorToken(val message: String, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return ""
        }
    }

    class OutputString(val value: String) : KATEToken {
        override val length: Int get() = value.length
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return value
        }
    }

    class Identifier(val text: String) : KATEToken {
        override val length: Int get() = text.length
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return text
        }
    }

    class CommentString(val value: String) : KATEToken {
        override val length: Int get() = value.length
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return value
        }
    }

    class Text(val value: String) : KATEToken {
        override val length: Int get() = value.length
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }

        override fun toString(): String {
            return value
        }
    }


}