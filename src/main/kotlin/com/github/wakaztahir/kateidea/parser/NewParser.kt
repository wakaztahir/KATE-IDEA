package com.github.wakaztahir.kateidea.parser

import com.github.wakaztahir.kateidea.parser.highlighting.KATETokens
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import com.intellij.lang.ASTNode

class NewParser : PsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        val rootMarker = builder.mark()
        while (!builder.eof()) {
            when (builder.tokenType) {
                KATETokens.VAR -> parseVar(builder)
                KATETokens.IF -> parseIf(builder)
                KATETokens.TEXT -> parseText(builder)
                else -> builder.advanceLexer()
            }
        }
        rootMarker.done(root)
        return builder.treeBuilt
    }

    private fun parseVar(builder: PsiBuilder) {
        val varMarker = builder.mark()
        builder.advanceLexer()
        varMarker.done(KATETokens.VAR)
    }

    private fun parseIf(builder: PsiBuilder) {
        val ifMarker = builder.mark()
        builder.advanceLexer()
        ifMarker.done(KATETokens.IF)
    }

    private fun parseText(builder: PsiBuilder) {
        val textMarker = builder.mark()
        builder.advanceLexer()
        textMarker.done(KATETokens.TEXT)
    }
}