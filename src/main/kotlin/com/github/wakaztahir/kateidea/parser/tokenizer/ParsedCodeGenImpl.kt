package com.github.wakaztahir.kateidea.parser.tokenizer

import com.intellij.lang.PsiBuilder
import com.wakaztahir.kate.model.CodeGen
import com.wakaztahir.kate.model.model.KATEParsingError

class ParsedCodeGenImpl(val codeGen: CodeGen, val startPointer: Int, val endPointer: Int) : ParsedCodeGen {
    override fun markAndDone(builder: PsiBuilder) {
        if (codeGen is KATEParsingError) {
            builder.error(codeGen.throwable.message ?: "Unknown error occurred")
            return
        }
        val marker = builder.mark()
        var i = startPointer
        val factory = codeGen.selectNode(KATEElementTypesFactory)
        val node = factory.provide(codeGen)
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