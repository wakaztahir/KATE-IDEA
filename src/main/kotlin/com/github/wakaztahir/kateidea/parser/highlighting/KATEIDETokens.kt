package com.github.wakaztahir.kateidea.parser.highlighting

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens

object KATEIDETokens {

    // Language

    val If = object : KATEIDEToken("if") {
        override val kateToken : KATEToken get() = KATETokens.If
    }

    val For = object : KATEIDEToken("for") {
        override val kateToken : KATEToken get() = KATETokens.For
    }

    val Var = object : KATEIDEToken("var") {
        override val kateToken : KATEToken get() = KATETokens.Var
    }

    val Function = object : KATEIDEToken("function") {
        override val kateToken : KATEToken get() = KATETokens.Function
    }

    // Operators

    val Plus = object : KATEIDEToken('+') {
        override val kateToken : KATEToken get() = KATETokens.Plus
    }

    val Minus = object : KATEIDEToken('-') {
        override val kateToken : KATEToken get() = KATETokens.Minus
    }

    val Multiply = object : KATEIDEToken('*') {
        override val kateToken : KATEToken get() = KATETokens.Multiply
    }

    val Divide = object : KATEIDEToken('/') {
        override val kateToken : KATEToken get() = KATETokens.Divide
    }

    val Modulus = object : KATEIDEToken('%') {
        override val kateToken : KATEToken get() = KATETokens.Modulus
    }

    // General

    val LeftParenthesis = object : KATEIDEToken('(') {
        override val kateToken : KATEToken get() = KATETokens.LeftParenthesis
    }

    val RightParenthesis = object : KATEIDEToken(')') {
        override val kateToken : KATEToken get() = KATETokens.RightParenthesis
    }

    val LeftBracket = object : KATEIDEToken('[') {
        override val kateToken : KATEToken get() = KATETokens.LeftBracket
    }

    val RightBracket = object : KATEIDEToken(']') {
        override val kateToken : KATEToken get() = KATETokens.RightBracket
    }

    val LeftBrace = object : KATEIDEToken('{') {
        override val kateToken : KATEToken get() = KATETokens.LeftBrace
    }

    val RightBrace = object : KATEIDEToken('}') {
        override val kateToken : KATEToken get() = KATETokens.RightBrace
    }

    val At = object : KATEIDEToken('@') {
        override val kateToken : KATEToken get() = KATETokens.At
    }

    // Comment

    val CommentStart = object : KATEIDEToken("<%--") {
        override val kateToken : KATEToken get() = KATETokens.CommentStart
    }

    val CommentEnd = object : KATEIDEToken("--%>") {
        override val kateToken : KATEToken get() = KATETokens.CommentEnd
    }

    // Embed

    val Embed = object : KATEIDEToken("embed") {
        override val kateToken : KATEToken get() = KATETokens.Embed
    }

    val EmbedOnce = object : KATEIDEToken("embed_once") {
        override val kateToken : KATEToken get() = KATETokens.EmbedOnce
    }

    // Placeholder

    val PlaceholderUse = object : KATEIDEToken("placeholder_use") {
        override val kateToken : KATEToken get() = KATETokens.PlaceholderUse
    }

    val Placeholder = object : KATEIDEToken("placeholder") {
        override val kateToken : KATEToken get() = KATETokens.Placeholder
    }

    val DefinePlaceholder = object : KATEIDEToken("define_placeholder") {
        override val kateToken : KATEToken get() = KATETokens.DefinePlaceholder
    }

    val EndDefinePlaceholder = object : KATEIDEToken("end_define_placeholder") {
        override val kateToken : KATEToken get() = KATETokens.EndDefinePlaceholder
    }

    // Modes

    val DefaultNoRaw = object : KATEIDEToken("default_no_raw") {
        override val kateToken : KATEToken get() = KATETokens.DefaultNoRaw
    }

    val EndDefaultNoRaw = object : KATEIDEToken("end_default_no_raw") {
        override val kateToken : KATEToken get() = KATETokens.EndDefaultNoRaw
    }

    val PartialRaw = object : KATEIDEToken("partial_raw") {
        override val kateToken : KATEToken get() = KATETokens.PartialRaw
    }

    val EndPartialRaw = object : KATEIDEToken("end_partial_raw") {
        override val kateToken : KATEToken get() = KATETokens.EndPartialRaw
    }

    val Raw = object : KATEIDEToken("raw") {
        override val kateToken : KATEToken get() = KATETokens.Raw
    }

    val EndRaw = object : KATEIDEToken("endraw") {
        override val kateToken : KATEToken get() = KATETokens.EndRaw
    }


}