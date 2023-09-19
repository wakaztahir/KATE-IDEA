package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.states.value.DefaultExpressionValueLexer
import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

class VariableAssignmentLexer(
    private val source: SourceStream,
    private val state: LexerState,
    private val isDefaultNoRaw: Boolean
) : Lexer {

    private val valueLexer by lazy { DefaultExpressionValueLexer(source, state, isDefaultNoRaw) }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if (state.isLexingVariableAssignment) {
            if (state.isLexingOperatorOrEqual) {
                if (state.isLexingValue) {
                    source.lexWhitespaces(offset)?.let { return it }
                    valueLexer.lexTokenAtPosition(offset)?.let {
                        return it.alsoOnIncrement(
                            block = {
                                if (!valueLexer.isLexing()) {
                                    state.isLexingValue = false
                                    state.isLexingOperatorOrEqual = false
                                    state.isLexingVariableAssignment = false
                                }
                            }
                        )
                    }
                    return source.lexAsBadCharacters(offset)
                } else {
                    return source.lookAhead(offset)?.let { char ->
                        when (char) {
                            ' ' -> {
                                return source.range(offset, KATEToken.Whitespace(1), null)
                            }

                            '=' -> {
                                return source.range(offset, KATETokens.SingleEqual) {
                                    state.isLexingValue = true
                                }
                            }

                            '+', '-', '*', '/', '%' -> {
                                if (state.isLexingEqual) {
                                    return source.range(offset, KATEToken.BadCharacter(1), null)
                                } else {
                                    return source.range(offset, KATEToken.ArithmeticOperator(char)) {
                                        state.isLexingEqual = true
                                    }
                                }
                            }

                            else -> {
                                return source.range(offset, KATEToken.BadCharacter(1), null)
                            }
                        }
                    }
                }
            } else {
                source.lexWhitespaces(offset = offset)?.let { return it }
                val word = source.readSingleWordAhead(offset = offset)
                return word?.let {
                    source.range(offset, KATEToken.Identifier(it)) {
                        state.isLexingOperatorOrEqual = true
                    }
                }
            }

        } else {
            return source.directiveRangeAtPosition(isDefaultNoRaw, offset, KATETokens.SetVar) {
                state.isLexingVariableAssignment = true
            }
        }
    }
}