package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

class EmbedLexer(
    private val source: SourceStream,
    private val state : LexerState,
    private val isDefaultNoRaw: Boolean
) : Lexer {

    private fun resetState() {
        state.isLexingEmbedPath = false
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (state.isLexingEmbedPath) {
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
                    state.isLexingEmbedPath = true
                }
            )
        }
    }

}