package com.github.wakaztahir.kateidea.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import com.wakaztahir.kate.model.block.ParsedBlockParser
import com.wakaztahir.kate.parser.stream.TextDestinationStream
import com.wakaztahir.kate.parser.stream.TextSourceStream

class KATEParser : PsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        val rootMarker = builder.mark()

        while (!builder.eof()) {
            val tokenType = builder.tokenType ?: continue

            val stream = TextSourceStream(sourceCode = builder.originalText.toString())

            val codeGens = ParsedBlockParser(stream.block).parseCompletely(TextDestinationStream())

            val astNode = builder.mark().done(tokenType)
            println("Parsed token: ${builder.originalText}")

            builder.advanceLexer()
        }

        rootMarker.done(root)
        return builder.treeBuilt
    }
}