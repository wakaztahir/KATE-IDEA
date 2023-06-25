package com.github.wakaztahir.kateidea.parser.highlighting

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.TokenConverter

object KATEIDETokenConverter : TokenConverter<KATEIDEToken> {

    override fun convert(token: KATEToken.DefaultNoRawString): KATEIDEToken {
        return KATEIDEToken.Encapsulated(token)
    }

    override fun convert(token: KATEToken.ErrorToken): KATEIDEToken {
        return KATEIDEToken.Encapsulated(token)
    }

    override fun convertIf(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.If
    }

    override fun convertFor(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.For
    }

    override fun convertVar(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.Var
    }

    override fun convertFunction(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.Function
    }

    override fun convertPlus(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.Plus
    }

    override fun convertMinus(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.Minus
    }

    override fun convertMultiply(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.Multiply
    }

    override fun convertDivide(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.Divide
    }

    override fun convertModulus(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.Modulus
    }

    override fun convertLeftParenthesis(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.LeftParenthesis
    }

    override fun convertRightParenthesis(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.RightParenthesis
    }

    override fun convertLeftBracket(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.LeftBracket
    }

    override fun convertRightBracket(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.RightBracket
    }

    override fun convertLeftBrace(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.LeftBrace
    }

    override fun convertRightBrace(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.RightBrace
    }

    override fun convertAt(token: KATEToken.Char): KATEIDEToken {
        return KATEIDETokens.At
    }

    override fun convertCommentStart(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.CommentStart
    }

    override fun convertCommentEnd(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.CommentEnd
    }

    override fun convertEmbed(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.Embed
    }

    override fun convertEmbedOnce(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.EmbedOnce
    }

    override fun convertPlaceholderUse(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.PlaceholderUse
    }

    override fun convertPlaceholder(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.Placeholder
    }

    override fun convertDefinePlaceholder(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.DefinePlaceholder
    }

    override fun convertEndDefinePlaceholder(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.EndDefinePlaceholder
    }

    override fun convertDefaultNoRaw(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.DefaultNoRaw
    }

    override fun convertEndDefaultNoRaw(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.EndDefaultNoRaw
    }

    override fun convertPartialRaw(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.PartialRaw
    }

    override fun convertEndPartialRaw(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.EndPartialRaw
    }

    override fun convertRaw(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.Raw
    }

    override fun convertEndRaw(token: KATEToken.String): KATEIDEToken {
        return KATEIDETokens.EndRaw
    }

}