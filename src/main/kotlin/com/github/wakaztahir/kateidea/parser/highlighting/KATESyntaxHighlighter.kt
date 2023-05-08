package com.github.wakaztahir.kateidea.parser.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class KATESyntaxHighlighter : SyntaxHighlighterBase() {

    override fun getHighlightingLexer(): Lexer {
        TODO("Not yet implemented")
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey?> {

        if (tokenType.equals(SimpleTypes.SEPARATOR)) {
            return SEPARATOR_KEYS
        }
        if (tokenType.equals(SimpleTypes.KEY)) {
            return KEY_KEYS
        }
        if (tokenType.equals(SimpleTypes.VALUE)) {
            return VALUE_KEYS
        }
        if (tokenType.equals(SimpleTypes.COMMENT)) {
            return COMMENT_KEYS
        }
        return if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            BAD_CHAR_KEYS
        } else EMPTY_KEYS
    }

    companion object {
        val SEPARATOR: TextAttributesKey =
            createTextAttributesKey("SIMPLE_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val KEY: TextAttributesKey = createTextAttributesKey("SIMPLE_KEY", DefaultLanguageHighlighterColors.KEYWORD)
        val VALUE: TextAttributesKey = createTextAttributesKey("SIMPLE_VALUE", DefaultLanguageHighlighterColors.STRING)
        val COMMENT: TextAttributesKey =
            createTextAttributesKey("SIMPLE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BAD_CHARACTER: TextAttributesKey =
            createTextAttributesKey("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
        private val BAD_CHAR_KEYS: Array<TextAttributesKey> = arrayOf<TextAttributesKey>(BAD_CHARACTER)
        private val SEPARATOR_KEYS: Array<TextAttributesKey> = arrayOf<TextAttributesKey>(SEPARATOR)
        private val KEY_KEYS: Array<TextAttributesKey> = arrayOf<TextAttributesKey>(KEY)
        private val VALUE_KEYS: Array<TextAttributesKey> = arrayOf<TextAttributesKey>(VALUE)
        private val COMMENT_KEYS: Array<TextAttributesKey> = arrayOf<TextAttributesKey>(COMMENT)
        private val EMPTY_KEYS: Array<TextAttributesKey?> = arrayOfNulls<TextAttributesKey>(0)
    }
}