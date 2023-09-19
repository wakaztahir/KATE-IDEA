package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.wakaztahir.kate.lexer.stream.SourceStream

class ForLoopParser(
    private val source: SourceStream,
    private val state : LexerState,
    private val isDefaultNoRaw: Boolean
) : Lexer {

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (state.isLexingFor) {
            if(state.hasLexedLParen){
                return if(state.isLexingEndFor){
                    source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.EndFor) {
                        state.isLexingFor = false
                        state.isLexingEndFor = false
                    }
                } else {
                    source.range(offset, KATETokens.RightParenthesis) {
                        state.hasLexedLParen = false
                        state.isLexingEndFor = true
                    }
                }
            } else {
                return source.range(offset, KATETokens.LeftParenthesis) {
                    state.isLexingExpression = true
                    state.hasLexedLParen = true
                }
            }
        }
        return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.For) {
            state.isLexingFor = true
        }
    }
}