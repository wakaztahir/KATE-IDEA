package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens
import com.github.wakaztahir.kateidea.lexer.TokenType
import com.github.wakaztahir.kateidea.lexer.readSingleWordAhead
import com.wakaztahir.kate.lexer.stream.SourceStream
import com.wakaztahir.kate.lexer.stream.isAtCurrentPosition
import com.wakaztahir.kate.lexer.stream.readTextAheadUntil

class DefaultNoRawLexer(
    private val source: SourceStream,
    private val endAtDefaultNoRaw: Boolean
) : Lexer {

    override val hasLexed: Boolean get() = source.hasEnded

    private var parseDirectiveWord = false

    private fun String.asKeyword(): KATEToken.Static? {
        return KATETokens.KeywordsMap[this]
    }

    override fun lexTokenAtCurrentPosition(): TokenRange? {
        if (source.hasEnded) return null
        if (source.currentChar == KATETokens.At.value) {
            parseDirectiveWord = true
            return KATETokens.At.range(source)
        }
        return if (parseDirectiveWord) {
            val word = source.readSingleWordAhead()!!
            parseDirectiveWord = false
            val keyword = word.asKeyword()
            if(keyword != null){
                return keyword.range(source)
            } else {
                return TokenRange(
                    start = source.pointer,
                    token = KATEToken.ErrorToken("$word is not a keyword"),
                    length = word.length
                )
            }
        } else {
            val text = source.readTextAheadUntil { currChar, _ -> currChar == '@' || currChar == null }
            KATEToken.DefaultNoRawString(text!!).range(source)
        }
    }

    fun getState(): Int {
        return if (parseDirectiveWord) 1 else 0
    }

    fun restoreState(state: Int) {
        parseDirectiveWord = state == 1
    }

}