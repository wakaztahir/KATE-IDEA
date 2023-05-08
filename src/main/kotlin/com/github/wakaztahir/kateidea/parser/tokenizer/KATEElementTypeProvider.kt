package com.github.wakaztahir.kateidea.parser.tokenizer

import com.wakaztahir.kate.model.CodeGen

interface KATEElementTypeProvider {
    fun provide(codeGen : CodeGen) : KATEElementType
}