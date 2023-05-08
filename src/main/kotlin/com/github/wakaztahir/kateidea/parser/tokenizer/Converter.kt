package com.github.wakaztahir.kateidea.parser.tokenizer

interface Converter<T> {
    fun <R> convert(item: T): R
}