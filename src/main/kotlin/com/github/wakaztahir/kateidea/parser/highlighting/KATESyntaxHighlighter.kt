package com.github.wakaztahir.kateidea.parser.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class KATESyntaxHighlighter(val lexer: Lexer) : SyntaxHighlighterBase() {

    private val emptyKeys: Array<TextAttributesKey> = arrayOf(HighlighterColors.NO_HIGHLIGHTING)

    override fun getHighlightingLexer(): Lexer {
        return lexer
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey?> {
        if (tokenType is KATEIDEToken) {
            return tokenType.highlightingAttributeKeys.colorKeys
        }
        return emptyKeys
    }

}