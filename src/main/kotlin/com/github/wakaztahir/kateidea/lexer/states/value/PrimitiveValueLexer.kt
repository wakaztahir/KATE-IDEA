package com.github.wakaztahir.kateidea.lexer.states.value

import com.github.wakaztahir.kateidea.lexer.LexerState
import com.github.wakaztahir.kateidea.lexer.states.Lexer
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.wakaztahir.kate.lexer.stream.SourceStream

class PrimitiveValueLexer(private val source: SourceStream, private val state: LexerState) : Lexer {

    private val stringLexer = StringValueLexer(stream = source, state = state)

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        stringLexer.lexTokenAtPosition(offset)?.let {
            return it
        }
        CharValueLexer.lexTokenAtPosition(this.source, offset)?.let {
            return it
        }
        BooleanValueLexer.lexTokenAtPosition(this.source, offset)?.let {
            return it
        }
        NumberValueLexer.lexTokenAtPosition(this.source, offset)?.let {
            return it
        }
        return null
    }

    fun isLexing(): Boolean = stringLexer.isLexing()

}