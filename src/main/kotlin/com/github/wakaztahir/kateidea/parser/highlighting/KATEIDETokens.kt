package com.github.wakaztahir.kateidea.parser.highlighting

import com.github.wakaztahir.kateidea.lexer.KATEToken


object KATEIDETokens {

    // Language

    object If : KATEIDEToken("if") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object For : KATEIDEToken("for") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object Var : KATEIDEToken("var") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object Function : KATEIDEToken("function") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object ValidStringEscape : KATEIDEToken("string_escape") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.ValidStringEscape
    }

    object InvalidStringEscape : KATEIDEToken("string_escape") {
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

    object SingleEqual : KATEIDEToken("=") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.OperationSign
    }

    object LeftParenthesis : KATEIDEToken('(') {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Parentheses
    }

    object RightParenthesis : KATEIDEToken(')') {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Parentheses
    }

    object LeftBracket : KATEIDEToken('[') {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Brackets
    }

    object RightBracket : KATEIDEToken(']') {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Brackets
    }

    object LeftBrace : KATEIDEToken('{') {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Braces
    }

    object RightBrace : KATEIDEToken('}') {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Braces
    }

    object At : KATEIDEToken('@') {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    // Comment

    object CommentStart : KATEIDEToken("<%--") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.BlockComment
    }

    object CommentEnd : KATEIDEToken("--%>") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.BlockComment
    }

    // Embed

    object Embed : KATEIDEToken("embed") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object EmbedOnce : KATEIDEToken("embed_once") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    // Placeholder

    object PlaceholderUse : KATEIDEToken("placeholder_use") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object Placeholder : KATEIDEToken("placeholder") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object DefinePlaceholder : KATEIDEToken("define_placeholder") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object EndDefinePlaceholder : KATEIDEToken("end_define_placeholder") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    // Modes

    object DefaultNoRaw : KATEIDEToken("default_no_raw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object EndDefaultNoRaw : KATEIDEToken("end_default_no_raw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object PartialRaw : KATEIDEToken("partial_raw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object EndPartialRaw : KATEIDEToken("end_partial_raw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object Raw : KATEIDEToken("raw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }

    object EndRaw : KATEIDEToken("endraw") {
        override val highlightingAttributeKeys: HighlightingAttributeKeys
            get() = HighlightingAttributeKeys.Keyword
    }


}