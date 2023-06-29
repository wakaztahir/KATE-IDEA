package com.github.wakaztahir.kateidea.lexer

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

    class CharValue(value: kotlin.Char) : Char(value = value) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class StringValue(value : kotlin.String) : String(value) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class StringEscape(value : kotlin.Char,val isValid : kotlin.Boolean) : Char(value = value) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class BooleanValue(val value : Boolean, override val length: Int) : KATEToken {
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

    class LongValue(value: Long, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class ArithmeticOperator(val operation: kotlin.Char) : KATEToken {
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

    class ErrorToken(val message: kotlin.String, override val length: Int) : KATEToken {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class OutputString(val value: kotlin.String) : KATEToken {
        override val length: Int
            get() = value.length

        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class Identifier(val text: kotlin.String) : KATEToken {
        override val length: Int
            get() = text.length

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

    class Text(val value : kotlin.String) : KATEToken {
        override val length: Int
            get() = value.length

        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }


}