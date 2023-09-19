package com.github.wakaztahir.kateidea.lexer.token

import com.github.wakaztahir.kateidea.lexer.TokenConverter

/**
 * [TypedToken] is any token that must be typed as it is
 *
 * Its value is known before compiling e.g "if" keyword
 * examples also include '(' or '+' or '@if'
 * what can't be a [TypedToken] is a dynamic string or identifier that user can change
 */
sealed interface TypedToken<T> : KATEToken {

    val value: T

    abstract class String(override val value: kotlin.String) : TypedToken<kotlin.String> {
        override val length: Int get() = value.length
        override fun toString(): kotlin.String {
            return value
        }
    }

    abstract class Char(override val value: kotlin.Char) : TypedToken<kotlin.Char> {
        override val length: Int get() = 1
        override fun toString(): kotlin.String {
            return value.toString()
        }
    }

    class CharValue(value: kotlin.Char) : Char(value = value) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class StringValue(value: kotlin.String) : String(value) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    class CharEscape(value: kotlin.Char, val isValid: Boolean) : Char(value = value) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

}