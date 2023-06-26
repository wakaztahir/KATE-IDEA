package com.github.wakaztahir.kateidea.lexer

interface TokenConverter<T> {

    fun convert(token : KATEToken.DefaultNoRawString) : T

    fun convert(token : KATEToken.CommentString) : T

    fun convert(token : KATEToken.ErrorToken) : T

    fun convert(token : KATETokens.If) : T

    fun convert(token : KATETokens.For) : T

    fun convert(token : KATETokens.Var) : T

    fun convert(token : KATETokens.Function) : T

    fun convert(token : KATETokens.Plus) : T

    fun convert(token : KATETokens.Minus) : T

    fun convert(token : KATETokens.Multiply) : T

    fun convert(token : KATETokens.Divide) : T

    fun convert(token : KATETokens.Modulus) : T

    fun convert(token : KATETokens.LeftParenthesis) : T

    fun convert(token : KATETokens.RightParenthesis) : T

    fun convert(token : KATETokens.LeftBracket) : T

    fun convert(token : KATETokens.RightBracket) : T

    fun convert(token : KATETokens.LeftBrace) : T

    fun convert(token : KATETokens.RightBrace) : T

    fun convert(token : KATETokens.At) : T

    fun convert(token : KATETokens.CommentStart) : T

    fun convert(token : KATETokens.CommentEnd) : T

    fun convert(token : KATETokens.Embed) : T

    fun convert(token : KATETokens.EmbedOnce) : T

    fun convert(token : KATETokens.PlaceholderUse) : T

    fun convert(token : KATETokens.Placeholder) : T

    fun convert(token : KATETokens.DefinePlaceholder) : T

    fun convert(token : KATETokens.EndDefinePlaceholder) : T

    fun convert(token : KATETokens.DefaultNoRaw) : T

    fun convert(token : KATETokens.EndDefaultNoRaw) : T

    fun convert(token : KATETokens.PartialRaw) : T

    fun convert(token : KATETokens.EndPartialRaw) : T

    fun convert(token : KATETokens.Raw) : T

    fun convert(token : KATETokens.EndRaw) : T


}