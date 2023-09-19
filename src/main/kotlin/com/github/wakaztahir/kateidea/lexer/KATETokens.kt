package com.github.wakaztahir.kateidea.lexer

import com.github.wakaztahir.kateidea.lexer.token.TypedToken

object KATETokens {

    // Language

    object If : TypedToken.String("if") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object For : TypedToken.String("for") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EndFor : TypedToken.String("endfor") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Var : TypedToken.String("var") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object SetVar : TypedToken.String("set_var") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Function : TypedToken.String("function") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Write : TypedToken.String("write") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    // General

    object Dot : TypedToken.Char('.') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Comma : TypedToken.Char(',') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object SingleEqual : TypedToken.Char('=') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object LeftParenthesis : TypedToken.Char('(') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object RightParenthesis : TypedToken.Char(')') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object LeftBracket : TypedToken.Char('[') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object RightBracket : TypedToken.Char(']') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object LeftBrace : TypedToken.Char('{') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object RightBrace : TypedToken.Char('}') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Hash : TypedToken.Char('#') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    // Comment

    object CommentStart : TypedToken.String("<%--") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object CommentEnd : TypedToken.String("--%>") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    // Embed

    object Embed : TypedToken.String("embed") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EmbedOnce : TypedToken.String("embed_once") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    // Placeholder

    object PlaceholderUse : TypedToken.String("use_placeholder") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Placeholder : TypedToken.String("placeholder") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object DefinePlaceholder : TypedToken.String("define_placeholder") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EndDefinePlaceholder : TypedToken.String("end_define_placeholder") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }


    // Modes

    object DefaultNoRaw : TypedToken.String("default_no_raw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EndDefaultNoRaw : TypedToken.String("end_default_no_raw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object PartialRaw : TypedToken.String("partial_raw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EndPartialRaw : TypedToken.String("end_partial_raw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Raw : TypedToken.String("raw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EndRaw : TypedToken.String("endraw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }


}