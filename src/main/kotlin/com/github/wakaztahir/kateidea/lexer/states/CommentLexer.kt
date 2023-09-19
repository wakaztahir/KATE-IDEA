package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

class CommentLexer(
    private val source: SourceStream,
    private val state: LexerState
) : Lexer {

    private fun resetState() {
        state.hasLexedCommentContent = false
        state.isLexingComment = false
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (state.isLexingComment) {
            if (state.hasLexedCommentContent) {
                if (source.isAtCurrentPositionFast(offset, KATETokens.CommentEnd.value)) {
                    return source.range(offset, KATETokens.CommentEnd) {
                        resetState()
                    }
                }
            } else {
                val commentText = source.readTextAheadUntil(offset, KATETokens.CommentEnd.value)
                return if (commentText != null) {
                    source.range(offset, KATEToken.CommentString(commentText)) {
                        state.hasLexedCommentContent = true
                    }
                } else {
                    null
                }
            }
        } else {
            if (source.isAtCurrentPositionFast(offset, KATETokens.CommentStart.value)) {
                return source.range(offset, KATETokens.CommentStart) {
                    state.isLexingComment = true
                }
            }
        }
        return null
    }

}