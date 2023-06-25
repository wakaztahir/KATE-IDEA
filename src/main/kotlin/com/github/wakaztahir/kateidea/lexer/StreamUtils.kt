package com.github.wakaztahir.kateidea.lexer

import com.wakaztahir.kate.lexer.stream.SourceStream
import com.wakaztahir.kate.lexer.stream.readTextAheadUntil

fun SourceStream.readSingleWordAhead() : String? {
    return readTextAheadUntil { currChar, _ ->  currChar == ' ' || currChar == null || currChar == '\n' }
}