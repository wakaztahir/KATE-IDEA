package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.states.value.DefaultExpressionValueLexer
import com.github.wakaztahir.kateidea.lexer.states.value.PrimitiveValueLexer
import com.wakaztahir.kate.lexer.stream.SourceStream
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor

class RuntimeWriteLexer(
    private val source: SourceStream,
    private val state : LexerState,
    private val isDefaultNoRaw: Boolean
) : Lexer {

    private val valueLexer by lazy { DefaultExpressionValueLexer(source, state, isDefaultNoRaw) }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if(state.isLexingRuntimeWrite){
            if(state.hasLexedLParen){
                if(state.isLexingExpression) {
                    valueLexer.lexTokenAtPosition(offset)?.let {
                        return it.alsoOnIncrement(
                            block = {
                                if (!valueLexer.isLexing()) {
                                    state.isLexingExpression = false
                                }
                            }
                        )
                    }
                    return source.lexAsBadCharacters(offset)
                } else {
                    if (source.lookAhead(offset) == KATETokens.RightParenthesis.value) {
                        return source.range(offset = offset, KATETokens.RightParenthesis) {
                            state.isLexingRuntimeWrite = false
                            state.hasLexedLParen = false
                        }
                    }
                    return source.lexAsBadCharacters(offset)
                }
            } else {
                if (source.lookAhead(offset) == KATETokens.LeftParenthesis.value) {
                    return source.range(offset = offset, KATETokens.LeftParenthesis) {
                        state.hasLexedLParen = true
                        state.isLexingExpression = true
                    }
                }
                return source.lexAsBadCharacters(offset)
            }
        } else {
            return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.Write) {
                state.isLexingRuntimeWrite = true
            }
        }
    }

}