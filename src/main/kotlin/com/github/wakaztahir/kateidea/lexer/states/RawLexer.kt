package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

class RawLexer(
    private val source: SourceStream,
    private val state: LexerState,
    private val isDefaultNoRaw: Boolean
) : Lexer {

    private fun resetState() {
        state.isLexingRaw = false
        state.hasLexedRawText = false
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (state.isLexingRaw) {
            if (state.hasLexedRawText) {
                return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.EndRaw) {
                    resetState()
                }
            }
            val text = source.readTextAheadUntilLambdaOrStreamEnds(offset = offset) { currChar, aheadOffset ->
                source.directiveRangeAtPosition(
                    isDefaultNoRaw = isDefaultNoRaw,
                    offset = aheadOffset,
                    directive = KATETokens.EndRaw,
                    onIncrement = null
                ) != null
            }
            return text?.let { rawText ->
                source.range(offset = offset, KATEToken.OutputString(rawText)) {
                    state.hasLexedRawText = true
                }
            }
        }
        return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.Raw) {
            state.isLexingRaw = true
        }
    }

}