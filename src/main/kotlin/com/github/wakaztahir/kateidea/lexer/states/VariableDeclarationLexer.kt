package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.states.value.DefaultExpressionValueLexer
import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

class VariableDeclarationLexer(
    private val source: SourceStream,
    private val state : LexerState,
    private val isDefaultNoRaw: Boolean
) : Lexer {

    private val valueLexer by lazy { DefaultExpressionValueLexer(source, state, isDefaultNoRaw = isDefaultNoRaw) }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if(state.isLexingVariableNameOrParen){
            if(state.isLexingEqual){
                return source.lookAhead(offset)?.let { char ->
                    when (char) {
                        ' ' -> {
                            return source.range(offset, KATEToken.Whitespace(1), null)
                        }

                        '=' -> {
                            return source.range(offset, KATETokens.SingleEqual) {
                                state.isLexingEqual = false
                                state.isLexingValue = true
                            }
                        }

                        else -> {
                            return source.range(offset, KATEToken.BadCharacter(1), null)
                        }
                    }
                }
            } else if(state.isLexingRightParen){
                source.lookAhead(offset)?.let {
                    if (it == ')') {
                        return source.range(offset, KATETokens.RightParenthesis) {
                            state.isLexingVariableNameOrParen = false
                            state.isLexingRefValue = false
                            state.isLexingValue = false
                            state.isLexingRightParen = false
                        }
                    }
                    return source.lexAsBadCharacters(offset)
                }
            } else if(state.isLexingRefValue || state.isLexingValue) {
                if (!state.isLexingRefValue) {
                    source.lexWhitespaces(offset)?.let { return it }
                }
                valueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement(
                        block = if(state.isLexingRefValue) {
                            {
                                if (!valueLexer.isLexing()) state.isLexingRightParen = true
                            }
                        } else if(state.isLexingValue) {
                            {
                                if (!valueLexer.isLexing()) {
                                    state.isLexingVariableNameOrParen = false
                                    state.isLexingRefValue = false
                                    state.isLexingValue = false
                                }
                            }
                        } else {
                            error("Not supposed to be here")
                        }
                    )
                }
                return source.lexAsBadCharacters(offset)
            } else {
                if (isDefaultNoRaw && source.lookAhead(offset) == '(') {
                    return source.range(offset, KATETokens.LeftParenthesis) {
                        state.isLexingRefValue = true
                    }
                }
                source.lexWhitespaces(offset = offset)?.let { return it }
                val word = source.readSingleWordAhead(offset = offset)
                return word?.let {
                    source.range(offset, KATEToken.Identifier(it)) {
                        state.isLexingEqual = true
                    }
                }
            }
        } else {
            return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.Var) {
                state.isLexingVariableNameOrParen = true
            }
        }
        return null
    }
}