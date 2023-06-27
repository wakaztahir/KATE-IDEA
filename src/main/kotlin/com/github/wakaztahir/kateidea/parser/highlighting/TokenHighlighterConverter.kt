package com.github.wakaztahir.kateidea.parser.highlighting

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens
import com.github.wakaztahir.kateidea.lexer.TokenConverter

object TokenHighlighterConverter : TokenConverter<HighlightingAttributeKeys> {

    override fun convert(token: KATEToken.OutputString): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.String
    }

    override fun convert(token: KATEToken.CommentString): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.BlockComment
    }

    override fun convert(token: KATEToken.ErrorToken): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.BadCharacter
    }

    override fun convert(token: KATETokens.If): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.For): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.Var): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.Function): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.Plus): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.OperationSign
    }

    override fun convert(token: KATETokens.Minus): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.OperationSign
    }

    override fun convert(token: KATETokens.Multiply): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.OperationSign
    }

    override fun convert(token: KATETokens.Divide): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.OperationSign
    }

    override fun convert(token: KATETokens.Modulus): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.OperationSign
    }

    override fun convert(token: KATETokens.LeftParenthesis): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Parentheses
    }

    override fun convert(token: KATETokens.RightParenthesis): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Parentheses
    }

    override fun convert(token: KATETokens.LeftBracket): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Brackets
    }

    override fun convert(token: KATETokens.RightBracket): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Brackets
    }

    override fun convert(token: KATETokens.LeftBrace): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Braces
    }

    override fun convert(token: KATETokens.RightBrace): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Braces
    }

    override fun convert(token: KATETokens.At): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.CommentStart): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.BlockComment
    }

    override fun convert(token: KATETokens.CommentEnd): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.BlockComment
    }

    override fun convert(token: KATETokens.Embed): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.EmbedOnce): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.PlaceholderUse): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.Placeholder): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.DefinePlaceholder): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.EndDefinePlaceholder): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.DefaultNoRaw): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.EndDefaultNoRaw): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.PartialRaw): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.EndPartialRaw): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.Raw): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

    override fun convert(token: KATETokens.EndRaw): HighlightingAttributeKeys {
        return HighlightingAttributeKeys.Keyword
    }

}