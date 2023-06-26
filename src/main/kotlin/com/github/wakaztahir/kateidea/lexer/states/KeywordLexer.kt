package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens
import com.github.wakaztahir.kateidea.lexer.range
import com.github.wakaztahir.kateidea.lexer.readSingleWordAhead
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.wakaztahir.kate.lexer.stream.SourceStream

class KeywordLexer(private val source: SourceStream) : CompositeLexState(), Lexer {

    private var parseDirectiveWord by state(false)

    private fun String.asKeyword(): KATEToken? {
        return KATETokens.KeywordsMap[this]
    }

    private fun resetState() {
        parseDirectiveWord = false
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (source.lookAhead(offset) == KATETokens.At.value) {
            return source.range(offset, KATETokens.At) {
                parseDirectiveWord = true
            }
        }
        if (parseDirectiveWord) {
            val word = source.readSingleWordAhead(offset = offset)
            return if(word != null){
                word.asKeyword()?.let {
                    source.range(offset, it) {
                        resetState()
                    }
                } ?: source.range(offset, KATEToken.ErrorToken("$word is not a keyword", word.length))
            } else null
        }
        return null
    }
}