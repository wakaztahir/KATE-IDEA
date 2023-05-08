package com.github.wakaztahir.kateidea.parser.tokenizer

import com.intellij.lang.PsiBuilder

interface ParsedCodeGen {
    fun markAndDone(builder: PsiBuilder)
}