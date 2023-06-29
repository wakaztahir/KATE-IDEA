package com.github.wakaztahir.kateidea.parser.highlighting

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens
import com.github.wakaztahir.kateidea.lexer.TokenConverter

object KATEIDETokenConverter : TokenConverter<KATEIDEToken> {

    override fun convert(token: KATETokens.SetVar): KATEIDEToken {
        return KATEIDETokens.SetVar
    }

    override fun convert(token: KATEToken.Text): KATEIDEToken {
        return KATEIDETokens.Text
    }

    override fun convert(token: KATEToken.StringEscape): KATEIDEToken {
        return if (token.isValid) {
            KATEIDETokens.ValidStringEscape
        } else {
            KATEIDETokens.InvalidStringEscape
        }
    }

    override fun convert(token: KATEToken.StringValue): KATEIDEToken {
        return KATEIDETokens.StringValue
    }

    override fun convert(token: KATEToken.IntValue): KATEIDEToken {
        return KATEIDETokens.IntValue
    }

    override fun convert(token: KATEToken.DoubleValue): KATEIDEToken {
        return KATEIDETokens.DoubleValue
    }

    override fun convert(token: KATEToken.LongValue): KATEIDEToken {
        return KATEIDETokens.LongValue
    }

    override fun convert(token: KATEToken.CharValue): KATEIDEToken {
        return KATEIDETokens.CharValue
    }

    override fun convert(token: KATEToken.BooleanValue): KATEIDEToken {
        return KATEIDETokens.BooleanValue
    }

    override fun convert(token: KATEToken.Identifier): KATEIDEToken {
        return KATEIDETokens.Identifier
    }

    override fun convert(token: KATEToken.BadCharacter): KATEIDEToken {
        return KATEIDETokens.BadCharacter
    }

    override fun convert(token: KATEToken.Whitespace): KATEIDEToken {
        return KATEIDETokens.Whitespace
    }

    override fun convert(token: KATEToken.ArithmeticOperator): KATEIDEToken {
        return KATEIDETokens.ArithmeticOperator
    }

    override fun convert(token: KATEToken.OutputString): KATEIDEToken {
        return KATEIDETokens.OutputString
    }

    override fun convert(token: KATEToken.CommentString): KATEIDEToken {
        return KATEIDETokens.CommentString
    }

    override fun convert(token: KATEToken.ErrorToken): KATEIDEToken {
        return KATEIDETokens.ErrorToken
    }

    override fun convert(token: KATETokens.If): KATEIDEToken {
        return KATEIDETokens.If
    }

    override fun convert(token: KATETokens.For): KATEIDEToken {
        return KATEIDETokens.For
    }

    override fun convert(token: KATETokens.Var): KATEIDEToken {
        return KATEIDETokens.Var
    }

    override fun convert(token: KATETokens.Function): KATEIDEToken {
        return KATEIDETokens.Function
    }

    override fun convert(token: KATETokens.SingleEqual): KATEIDEToken {
        return KATEIDETokens.SingleEqual
    }

    override fun convert(token: KATETokens.LeftParenthesis): KATEIDEToken {
        return KATEIDETokens.LeftParenthesis
    }

    override fun convert(token: KATETokens.RightParenthesis): KATEIDEToken {
        return KATEIDETokens.RightParenthesis
    }

    override fun convert(token: KATETokens.LeftBracket): KATEIDEToken {
        return KATEIDETokens.LeftBracket
    }

    override fun convert(token: KATETokens.RightBracket): KATEIDEToken {
        return KATEIDETokens.RightBracket
    }

    override fun convert(token: KATETokens.LeftBrace): KATEIDEToken {
        return KATEIDETokens.LeftBrace
    }

    override fun convert(token: KATETokens.RightBrace): KATEIDEToken {
        return KATEIDETokens.RightBrace
    }

    override fun convert(token: KATETokens.At): KATEIDEToken {
        return KATEIDETokens.At
    }

    override fun convert(token: KATETokens.CommentStart): KATEIDEToken {
        return KATEIDETokens.CommentStart
    }

    override fun convert(token: KATETokens.CommentEnd): KATEIDEToken {
        return KATEIDETokens.CommentEnd
    }

    override fun convert(token: KATETokens.Embed): KATEIDEToken {
        return KATEIDETokens.Embed
    }

    override fun convert(token: KATETokens.EmbedOnce): KATEIDEToken {
        return KATEIDETokens.EmbedOnce
    }

    override fun convert(token: KATETokens.PlaceholderUse): KATEIDEToken {
        return KATEIDETokens.PlaceholderUse
    }

    override fun convert(token: KATETokens.Placeholder): KATEIDEToken {
        return KATEIDETokens.Placeholder
    }

    override fun convert(token: KATETokens.DefinePlaceholder): KATEIDEToken {
        return KATEIDETokens.DefinePlaceholder
    }

    override fun convert(token: KATETokens.EndDefinePlaceholder): KATEIDEToken {
        return KATEIDETokens.EndDefinePlaceholder
    }

    override fun convert(token: KATETokens.DefaultNoRaw): KATEIDEToken {
        return KATEIDETokens.DefaultNoRaw
    }

    override fun convert(token: KATETokens.EndDefaultNoRaw): KATEIDEToken {
        return KATEIDETokens.EndDefaultNoRaw
    }

    override fun convert(token: KATETokens.PartialRaw): KATEIDEToken {
        return KATEIDETokens.PartialRaw
    }

    override fun convert(token: KATETokens.EndPartialRaw): KATEIDEToken {
        return KATEIDETokens.EndPartialRaw
    }

    override fun convert(token: KATETokens.Raw): KATEIDEToken {
        return KATEIDETokens.Raw
    }

    override fun convert(token: KATETokens.EndRaw): KATEIDEToken {
        return KATEIDETokens.EndRaw
    }

}