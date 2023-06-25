package com.github.wakaztahir.kateidea.lexer

object KATETokens {

    private fun MutableMap<String, KATEToken.String>.put(vararg tokens: KATEToken.String) {
        for (token in tokens) this[token.value] = token
    }

    val KeywordsMap by lazy {
        hashMapOf<String, KATEToken.String>().apply {
            put(
                If, For, Var, Function, Embed, EmbedOnce, Placeholder, PlaceholderUse, DefinePlaceholder,
                EndDefinePlaceholder, DefaultNoRaw, EndDefaultNoRaw, PartialRaw, EndPartialRaw, Raw, EndRaw,
            )
        }
    }

    // Language

    val If = object : KATEToken.String("if", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertIf(this)
        }
    }

    val For = object : KATEToken.String("for", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertFor(this)
        }
    }

    val Var = object : KATEToken.String("var", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertVar(this)
        }
    }

    val Function = object : KATEToken.String("function", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertFunction(this)
        }
    }

    // Operators

    val Plus = object : KATEToken.Char('+', TokenType.OperationSign) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertPlus(this)
        }
    }

    val Minus = object : KATEToken.Char('-', TokenType.OperationSign) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertMinus(this)
        }
    }

    val Multiply = object : KATEToken.Char('*', TokenType.OperationSign) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertMultiply(this)
        }
    }

    val Divide = object : KATEToken.Char('/', TokenType.OperationSign) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertDivide(this)
        }
    }

    val Modulus = object : KATEToken.Char('%', TokenType.OperationSign) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertModulus(this)
        }
    }

    // General

    val LeftParenthesis = object : KATEToken.Char('(', TokenType.Parentheses) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertLeftParenthesis(this)
        }
    }

    val RightParenthesis = object : KATEToken.Char(')', TokenType.Parentheses) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertRightParenthesis(this)
        }
    }

    val LeftBracket = object : KATEToken.Char('[', TokenType.Brackets) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertLeftBracket(this)
        }
    }

    val RightBracket = object : KATEToken.Char(']', TokenType.Brackets) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertRightBracket(this)
        }
    }

    val LeftBrace = object : KATEToken.Char('{', TokenType.Braces) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertLeftBrace(this)
        }
    }

    val RightBrace = object : KATEToken.Char('}', TokenType.Braces) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertRightBrace(this)
        }
    }

    val At = object : KATEToken.Char('@', TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertAt(this)
        }
    }

    // Comment

    val CommentStart = object : KATEToken.String("<%--", TokenType.BlockComment) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertCommentStart(this)
        }
    }

    val CommentEnd = object : KATEToken.String("--%>", TokenType.BlockComment) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertCommentEnd(this)
        }
    }

    // Embed

    val Embed = object : KATEToken.String("embed", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertEmbed(this)
        }
    }

    val EmbedOnce = object : KATEToken.String("embed_once", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertEmbedOnce(this)
        }
    }

    // Placeholder

    val PlaceholderUse = object : KATEToken.String("placeholder_use", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertPlaceholderUse(this)
        }
    }

    val Placeholder = object : KATEToken.String("placeholder", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertPlaceholder(this)
        }
    }

    val DefinePlaceholder = object : KATEToken.String("define_placeholder", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertDefinePlaceholder(this)
        }
    }

    val EndDefinePlaceholder = object : KATEToken.String("end_define_placeholder", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertEndDefinePlaceholder(this)
        }
    }


    // Modes

    val DefaultNoRaw = object : KATEToken.String("default_no_raw", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertDefaultNoRaw(this)
        }
    }

    val EndDefaultNoRaw = object : KATEToken.String("end_default_no_raw", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertEndDefaultNoRaw(this)
        }
    }

    val PartialRaw = object : KATEToken.String("partial_raw", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertPartialRaw(this)
        }
    }

    val EndPartialRaw = object : KATEToken.String("end_partial_raw", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertEndPartialRaw(this)
        }
    }

    val Raw = object : KATEToken.String("raw", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertRaw(this)
        }
    }

    val EndRaw = object : KATEToken.String("endraw", TokenType.Keyword) {
        override fun <T> convert(converter: TokenConverter<T>): T {
            return converter.convertEndRaw(this)
        }
    }


}