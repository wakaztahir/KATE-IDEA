package com.github.wakaztahir.kateidea.parser

class LexSource(
    internal val buffer: CharSequence,
    startPosition: Int,
    val endPosition: Int,
) {

    init {
        println("LexSource Buffer : $buffer")
    }

    var currentPosition: Int = startPosition

    val currentChar : Char get() = buffer[currentPosition]

    fun peakAhead(ahead : Int) : Char {
        return buffer[currentPosition + ahead]
    }

    fun advanceLexer(token: RealLexer.KLexToken) {
        currentPosition = token.end
    }


}