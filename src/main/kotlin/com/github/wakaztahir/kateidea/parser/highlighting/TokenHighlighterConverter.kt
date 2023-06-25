package com.github.wakaztahir.kateidea.parser.highlighting

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.TokenConverter

object TokenHighlighterConverter : TokenConverter<HighlightingAttributeKeys> {

    override fun convert(token: KATEToken.DefaultNoRawString): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.String
    }

    override fun convert(token: KATEToken.ErrorToken): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.BadCharacter
    }

    override fun convertIf(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertFor(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertVar(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertFunction(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertPlus(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.OperationSign
    }

    override fun convertMinus(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.OperationSign
    }

    override fun convertMultiply(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.OperationSign
    }

    override fun convertDivide(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.OperationSign
    }

    override fun convertModulus(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.OperationSign
    }

    override fun convertLeftParenthesis(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Parentheses
    }

    override fun convertRightParenthesis(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Parentheses
    }

    override fun convertLeftBracket(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Brackets
    }

    override fun convertRightBracket(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Brackets
    }

    override fun convertLeftBrace(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Braces
    }

    override fun convertRightBrace(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Braces
    }

    override fun convertAt(token: KATEToken.Char): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertCommentStart(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.BlockComment
    }

    override fun convertCommentEnd(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.BlockComment
    }

    override fun convertEmbed(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertEmbedOnce(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertPlaceholderUse(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertPlaceholder(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertDefinePlaceholder(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertEndDefinePlaceholder(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertDefaultNoRaw(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertEndDefaultNoRaw(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertPartialRaw(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertEndPartialRaw(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertRaw(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convertEndRaw(token: KATEToken.String): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

}