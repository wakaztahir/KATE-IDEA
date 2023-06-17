package com.github.wakaztahir.kateidea.parser

import com.github.wakaztahir.kateidea.parser.highlighting.KATETokens
import com.github.wakaztahir.kateidea.parser.tokenizer.KATEElementType
import com.intellij.psi.tree.IElementType
import java.util.*

class RealLexer(var source: LexSource) {

    val endPosition: Int get() = source.endPosition
    val currentPosition: Int get() = source.currentPosition

    data class KLexToken(val realToken: IElementType, val start: Int, val end: Int)

    private fun isIdentifierStart(ch: Char): Boolean {
        return ch == 'i' || ch == 'v'
    }

    private fun isAtCurrentPosition(text: String): Boolean {
        var x = 0
        while (x < text.length) {
            if (currentPosition + x >= endPosition || source.peakAhead(x) != text[x]) {
                return false
            }
            x++
        }
        return true
    }

    fun lexCurrentToken(): KLexToken? {
        if (currentPosition >= endPosition) return null
        val start = currentPosition
        return when {
            isIdentifierStart(source.currentChar) -> {
                return if (isAtCurrentPosition("if")) {
                    KLexToken(KATETokens.IF, start, start + 2)
                } else if (isAtCurrentPosition("var")) {
                    KLexToken(KATETokens.VAR, start, start + 3)
                } else {
                    KLexToken(KATETokens.TEXT, start, start + 1)
                }
            }

            else -> {
                KLexToken(KATETokens.TEXT, start, start + 1)
            }
        }
    }

}