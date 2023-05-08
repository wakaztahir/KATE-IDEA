package com.github.wakaztahir.kateidea.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import com.wakaztahir.kate.parser.stream.TextDestinationStream
import com.wakaztahir.kate.parser.stream.TextSourceStream

class KATEParser : PsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        val rootMarker = builder.mark()

        val stream = TextSourceStream(sourceCode = builder.originalText.toString())

        val codeGens = try {
            ParsedBlockParser(stream.block).parseCompletely(TextDestinationStream())
        } catch (e: Exception) {
            builder.error(e.message ?: "Unknown parsing error occurred")
            listOf()
        }

        val codeGensMapped = codeGens.associateBy { it.startPointer }

        while (!builder.eof()) {
            val tokenType = builder.tokenType ?: continue
            codeGensMapped[builder.currentOffset]?.markAndDone(builder)
            builder.mark().done(tokenType)
            builder.advanceLexer()
        }

        rootMarker.done(root)
        return builder.treeBuilt
    }
}