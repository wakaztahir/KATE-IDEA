package com.github.wakaztahir.kateidea.parser.tokenizer

import com.wakaztahir.kate.model.CodeGen

interface Converter<T : CodeGen,R> {
    fun convert(item: T): R
}