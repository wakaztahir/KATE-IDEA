package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.wakaztahir.kate.lexer.stream.SourceStream

class RawLexer(private val source: SourceStream, private val isDefaultNoRaw: Boolean) : Lexer, CompositeLexState() {

    private var isLexingRaw by state(false)
    private var hasLexedRawText by state(false)

    private fun resetState() {
        isLexingRaw = false
        hasLexedRawText = false
    }

    private fun SourceStream.directiveRangeAtPosition(
        offset: Int,
        directive: KATEToken.String,
        onIncrement: (() -> Unit)?
    ): TokenRange? {
        val startOffset = if (isAtCurrentPosition(offset = offset, KATETokens.At.value)) 1 else {
            if (isDefaultNoRaw) return null else 0
        }
        if (isAtCurrentPositionText(offset = offset + startOffset, directive.value)) {
            return source.range(
                offset = offset,
                lengthOffset = startOffset,
                token = directive,
                onIncrement = onIncrement
            )
        }
        return null
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (isLexingRaw) {
            if (hasLexedRawText) {
                return source.directiveRangeAtPosition(offset, KATETokens.EndRaw) {
                    resetState()
                }
            }
            val text = source.readTextAheadUntilLambdaOrStreamEnds(offset = offset) { currChar, aheadOffset ->
                source.directiveRangeAtPosition(
                    offset = aheadOffset,
                    directive = KATETokens.EndRaw,
                    onIncrement = null
                ) != null
            }
            return text?.let { rawText ->
                source.range(offset = offset, KATEToken.OutputString(rawText)) {
                    hasLexedRawText = true
                }
            }
        }
        return source.directiveRangeAtPosition(offset, KATETokens.Raw) {
            isLexingRaw = true
        }
    }

}