package com.github.wakaztahir.kateidea.parser.highlighting

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens
import com.github.wakaztahir.kateidea.lexer.TokenConverter

object KATEIDETokenConverter : TokenConverter<KATEIDEToken> {

    override fun convert(token: KATEToken.Identifier): KATEIDEToken {
        return KATEIDEToken.Encapsulated(token)
    }

    override fun convert(token: KATEToken.BadCharacter): KATEIDEToken {
        return KATEIDEToken.Encapsulated(token)
    }

    override fun convert(token: KATEToken.Whitespace): KATEIDEToken {
        return KATEIDEToken.Encapsulated(token)
    }

    override fun convert(token: KATEToken.ArithmeticOperator): KATEIDEToken {
        return KATEIDEToken.Encapsulated(token)
    }

    override fun convert(token: KATEToken.OutputString): KATEIDEToken {
        return KATEIDEToken.Encapsulated(token)
    }

    override fun convert(token: KATEToken.CommentString): KATEIDEToken {
        return KATEIDEToken.Encapsulated(token)
    }

    override fun convert(token: KATEToken.ErrorToken): KATEIDEToken {
        return KATEIDEToken.Encapsulated(token)
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

//    override fun convert(token: KATETokens.Plus): KATEIDEToken {
//        return KATEIDETokens.Plus
//    }
//
//    override fun convert(token: KATETokens.Minus): KATEIDEToken {
//        return KATEIDETokens.Minus
//    }
//
//    override fun convert(token: KATETokens.Multiply): KATEIDEToken {
//        return KATEIDETokens.Multiply
//    }
//
//    override fun convert(token: KATETokens.Divide): KATEIDEToken {
//        return KATEIDETokens.Divide
//    }
//
//    override fun convert(token: KATETokens.Modulus): KATEIDEToken {
//        return KATEIDETokens.Modulus
//    }

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