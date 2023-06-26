package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.getValue
import com.github.wakaztahir.kateidea.lexer.state.setValue
import com.wakaztahir.kate.lexer.stream.SourceStream

class CommentLexer(private val source: SourceStream) : CompositeLexState(), Lexer {

    private var hasLexedComment by state(false)
    private var isLexingComment by state(false)

    private fun resetState() {
        hasLexedComment = false
        isLexingComment = false
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (source.isAtCurrentPositionFast(offset, KATETokens.CommentStart.value)) {
            return source.range(offset, KATETokens.CommentStart) {
                isLexingComment = true
            }
        }
        if (isLexingComment) {
            if (hasLexedComment) {
                if (source.isAtCurrentPositionFast(offset, KATETokens.CommentEnd.value)) {
                    return source.range(offset, KATETokens.CommentEnd) {
                        resetState()
                    }
                }
            } else {
                val commentText = source.readTextAheadUntil(offset, KATETokens.CommentEnd.value)
                return if (commentText != null) {
                    source.range(offset, KATEToken.CommentString(commentText)) {
                        hasLexedComment = true
                    }
                } else {
                    null
                }
            }
        }
        return null
    }

}