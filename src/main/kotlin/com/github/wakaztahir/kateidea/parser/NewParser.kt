package com.github.wakaztahir.kateidea.parser

import com.github.wakaztahir.kateidea.parser.highlighting.KATEIDEToken
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import com.intellij.lang.ASTNode

class NewParser : PsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        val rootMarker = builder.mark()
        while (!builder.eof()) {
            val token = builder.tokenType
            when (token) {
                is KATEIDEToken -> {
                    parseToken(token,builder)
                }
                else -> builder.advanceLexer()
            }
        }
        rootMarker.done(root)
        return builder.treeBuilt
    }

    private fun parseToken(token : KATEIDEToken,builder : PsiBuilder){
        val varMarker = builder.mark()
        builder.advanceLexer()
        varMarker.done(token)
    }

}