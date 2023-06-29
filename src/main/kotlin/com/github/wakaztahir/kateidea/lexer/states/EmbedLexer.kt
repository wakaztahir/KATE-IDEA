package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.wakaztahir.kate.lexer.stream.SourceStream

class EmbedLexer(private val source: SourceStream, private val isDefaultNoRaw: Boolean) : Lexer, CompositeLexState() {

    private var isLexingPath by state(false)

    private fun resetState() {
        isLexingPath = false
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (isLexingPath) {
            source.lexWhitespaces(offset, null)?.let { return it }
            val text = source.readTextAheadUntil(offset = offset, '\n')
            return text?.let { rawText ->
                source.range(offset = offset, KATEToken.Text(rawText)) {
                    resetState()
                }
            }
        }
        return source.directiveOffsetAtPosition(isDefaultNoRaw, offset, KATETokens.Embed)?.let { startOffset ->
            val token = if (source.isAtCurrentPositionText(offset = offset + startOffset + KATETokens.Embed.length, "_once")) {
                KATETokens.EmbedOnce
            } else KATETokens.Embed
            source.range(
                offset = offset,
                token = token,
                lengthOffset = startOffset,
                onIncrement = {
                    isLexingPath = true
                }
            )
        }
    }

}