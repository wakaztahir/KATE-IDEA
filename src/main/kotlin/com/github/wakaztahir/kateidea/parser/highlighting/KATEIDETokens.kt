package com.github.wakaztahir.kateidea.parser.highlighting

import com.github.wakaztahir.kateidea.lexer.KATEToken


object KATEIDETokens {

    // Language

    object If : KATEIDEToken("If") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object For : KATEIDEToken("For") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object Var : KATEIDEToken("Var") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object SetVar : KATEIDEToken("SetVar") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object Function : KATEIDEToken("Function") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object ValidStringEscape : KATEIDEToken("ValidStringEscape") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.ValidStringEscape
    }

    object InvalidStringEscape : KATEIDEToken("InvalidStringEscape") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.InvalidStringEscape
    }

    // Values

    object StringValue : KATEIDEToken("StringValue") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.String
    }

    object IntValue : KATEIDEToken("IntValue") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Number
    }

    object DoubleValue : KATEIDEToken("DoubleValue") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Number
    }

    object LongValue : KATEIDEToken("LongValue") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Number
    }

    object CharValue : KATEIDEToken("CharValue") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.String
    }

    object BooleanValue : KATEIDEToken("BooleanValue") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object Identifier : KATEIDEToken("Identifier") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Identifier
    }

    // General

    object Text : KATEIDEToken("Text") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Text
    }

    object BadCharacter : KATEIDEToken("BadCharacter") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.BadCharacter
    }

    object Whitespace : KATEIDEToken("Whitespace") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.None
    }

    object ArithmeticOperator : KATEIDEToken("ArithmeticOperator") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.OperationSign
    }

    object OutputString : KATEIDEToken("OutputString") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.String
    }

    object CommentString : KATEIDEToken("CommentString") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.BlockComment
    }

    object ErrorToken : KATEIDEToken("ErrorToken") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.BadCharacter
    }

    object SingleEqual : KATEIDEToken("SingleEqual") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.OperationSign
    }

    object LeftParenthesis : KATEIDEToken("LeftParenthesis") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Parentheses
    }

    object RightParenthesis : KATEIDEToken("RightParenthesis") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Parentheses
    }

    object LeftBracket : KATEIDEToken("LeftBracket") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Brackets
    }

    object RightBracket : KATEIDEToken("RightBracket") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Brackets
    }

    object LeftBrace : KATEIDEToken("LeftBrace") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Braces
    }

    object RightBrace : KATEIDEToken("RightBrace") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Braces
    }

    object At : KATEIDEToken("At") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    // Comment

    object CommentStart : KATEIDEToken("CommentStart") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.BlockComment
    }

    object CommentEnd : KATEIDEToken("CommentEnd") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.BlockComment
    }

    // Embed

    object Embed : KATEIDEToken("Embed") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object EmbedOnce : KATEIDEToken("EmbedOnce") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    // Placeholder

    object PlaceholderUse : KATEIDEToken("PlaceholderUse") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object Placeholder : KATEIDEToken("Placeholder") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object DefinePlaceholder : KATEIDEToken("DefinePlaceholder") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object EndDefinePlaceholder : KATEIDEToken("EndDefinePlaceholder") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    // Modes

    object DefaultNoRaw : KATEIDEToken("DefaultNoRaw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object EndDefaultNoRaw : KATEIDEToken("EndDefaultNoRaw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object PartialRaw : KATEIDEToken("PartialRaw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object EndPartialRaw : KATEIDEToken("EndPartialRaw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object Raw : KATEIDEToken("Raw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object EndRaw : KATEIDEToken("EndRaw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }


}