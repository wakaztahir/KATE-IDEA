package com.github.wakaztahir.kateidea.lexer

interface TokenConverter<T> {

    fun convert(token : KATEToken.DefaultNoRawString) : T

    fun convert(token : KATEToken.ErrorToken) : T

    fun convertIf(token : KATEToken.String) : T

    fun convertFor(token : KATEToken.String) : T

    fun convertVar(token : KATEToken.String) : T

    fun convertFunction(token : KATEToken.String) : T

    fun convertPlus(token : KATEToken.Char) : T

    fun convertMinus(token : KATEToken.Char) : T

    fun convertMultiply(token : KATEToken.Char) : T

    fun convertDivide(token : KATEToken.Char) : T

    fun convertModulus(token : KATEToken.Char) : T

    fun convertLeftParenthesis(token : KATEToken.Char) : T

    fun convertRightParenthesis(token : KATEToken.Char) : T

    fun convertLeftBracket(token : KATEToken.Char) : T

    fun convertRightBracket(token : KATEToken.Char) : T

    fun convertLeftBrace(token : KATEToken.Char) : T

    fun convertRightBrace(token : KATEToken.Char) : T

    fun convertAt(token : KATEToken.Char) : T

    fun convertCommentStart(token : KATEToken.String) : T

    fun convertCommentEnd(token : KATEToken.String) : T

    fun convertEmbed(token : KATEToken.String) : T

    fun convertEmbedOnce(token : KATEToken.String) : T

    fun convertPlaceholderUse(token : KATEToken.String) : T

    fun convertPlaceholder(token : KATEToken.String) : T

    fun convertDefinePlaceholder(token : KATEToken.String) : T

    fun convertEndDefinePlaceholder(token : KATEToken.String) : T

    fun convertDefaultNoRaw(token : KATEToken.String) : T

    fun convertEndDefaultNoRaw(token : KATEToken.String) : T

    fun convertPartialRaw(token : KATEToken.String) : T

    fun convertEndPartialRaw(token : KATEToken.String) : T

    fun convertRaw(token : KATEToken.String) : T

    fun convertEndRaw(token : KATEToken.String) : T


}