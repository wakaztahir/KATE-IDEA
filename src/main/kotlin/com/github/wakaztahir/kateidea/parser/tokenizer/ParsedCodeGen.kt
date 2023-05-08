package com.github.wakaztahir.kateidea.parser.tokenizer

import com.intellij.lang.PsiBuilder
import com.wakaztahir.kate.model.CodeGen

class ParsedCodeGen(val codeGen: CodeGen, val startPointer: Int, val endPointer: Int) {
    fun markAndDone(builder: PsiBuilder) {
        val marker = builder.mark()
        var i = startPointer
        val node = codeGen.selectNode(KATEElementTypes)
        while (i < endPointer) {
            val elem = builder.tokenType ?: continue
            if (builder.eof()) {
                builder.error("unexpected eof , node not yet completed $node")
            }
            builder.mark().done(elem)
            builder.advanceLexer()
            i++
        }
        marker.done(node)
    }
}