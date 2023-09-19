package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.states.value.DefaultExpressionValueLexer
import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

class PlaceholderInvocationLexer(
    private val source: SourceStream,
    private val state : LexerState,
    private val isDefaultNoRaw: Boolean
) : Lexer {

    private val valueLexer by lazy { DefaultExpressionValueLexer(source,state, isDefaultNoRaw) }


    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if(state.isLexingPlaceholderInvocation){
            if(state.hasLexedLParen){
                if(state.isLexingCommaOrRightParenthesis){
                    return source.lookAhead(offset)?.let {
                        when (it) {
                            ')' -> source.range(offset = offset, KATETokens.RightParenthesis) {
                                state.isLexingPlaceholderInvocation = false
                                state.hasLexedLParen = false
                                state.isLexingCommaOrRightParenthesis = false
                            }

                            ',' -> source.range(offset = offset, KATETokens.Comma) {
                                state.isLexingCommaOrRightParenthesis = false
                            }

                            else -> source.range(offset = offset, KATEToken.BadCharacter(1))
                        }
                    }
                } else {
                    val text = source.readTextAheadUntilLambdaOrStreamEnds(offset) { currChar, _ -> currChar == ',' || currChar == ')' }
                    return text?.let {
                        source.range(
                            offset = offset,
                            token = KATEToken.Identifier(text),
                            onIncrement = {
                                state.isLexingCommaOrRightParenthesis = true
                            }
                        )
                    }
                }
            } else {
                if (source.lookAhead(offset) == KATETokens.LeftParenthesis.value) {
                    return source.range(offset = offset, KATETokens.LeftParenthesis) {
                        state.hasLexedLParen = true
                    }
                }
                return source.lexAsBadCharacters(offset)
            }
        } else {
            return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.Placeholder) {
                state.isLexingPlaceholderInvocation = true
            }
        }
    }

}