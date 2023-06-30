package com.github.wakaztahir.kateidea.lexer

object KATETokens {

    // Language

    object If : KATEToken.String("if") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object For : KATEToken.String("for") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Var : KATEToken.String("var") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object SetVar : KATEToken.String("set_var") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Function : KATEToken.String("function") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Write : KATEToken.String("write") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    // General

    object Dot : KATEToken.Char('.') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Comma : KATEToken.Char(',') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object SingleEqual : KATEToken.Char('=') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object LeftParenthesis : KATEToken.Char('(') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object RightParenthesis : KATEToken.Char(')') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object LeftBracket : KATEToken.Char('[') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object RightBracket : KATEToken.Char(']') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object LeftBrace : KATEToken.Char('{') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object RightBrace : KATEToken.Char('}') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object At : KATEToken.Char('@') {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    // Comment

    object CommentStart : KATEToken.String("<%--") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object CommentEnd : KATEToken.String("--%>") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    // Embed

    object Embed : KATEToken.String("embed") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EmbedOnce : KATEToken.String("embed_once") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    // Placeholder

    object PlaceholderUse : KATEToken.String("use_placeholder") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Placeholder : KATEToken.String("placeholder") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object DefinePlaceholder : KATEToken.String("define_placeholder") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EndDefinePlaceholder : KATEToken.String("end_define_placeholder") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }


    // Modes

    object DefaultNoRaw : KATEToken.String("default_no_raw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EndDefaultNoRaw : KATEToken.String("end_default_no_raw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object PartialRaw : KATEToken.String("partial_raw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EndPartialRaw : KATEToken.String("end_partial_raw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object Raw : KATEToken.String("raw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }

    object EndRaw : KATEToken.String("endraw") {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convert(this)
        }
    }


}