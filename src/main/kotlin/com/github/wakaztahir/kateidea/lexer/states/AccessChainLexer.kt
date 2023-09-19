package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.*
import com.github.wakaztahir.kateidea.lexer.states.value.DefaultExpressionValueLexer
import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.wakaztahir.kate.lexer.stream.SourceStream

class AccessChainLexer(
    private val source: SourceStream,
    private val state : LexerState,
    private val isDefaultNoRaw: Boolean,
    private val createNestedValueLexer : () -> DefaultExpressionValueLexer
) : Lexer {

    private val nestedValueLexerLazy = lazy(createNestedValueLexer)
    private val nestedValueLexer by nestedValueLexerLazy

    private fun Char.isDPB() = this == '(' || this == '[' || this == '.'

    fun isLexing(): Boolean = state.isLexingDotParenBracket || (nestedValueLexerLazy.isInitialized() && nestedValueLexer.isLexing())

    private fun lexDPB(offset: Int): TokenRange? {
        val current = source.lookAhead(offset) ?: return null
        when (current) {
            '.' -> {
                return source.range(offset, KATETokens.Dot) {
                    state.isLexingIdentifier = true
                }
            }

            '(' -> {
                return source.range(offset, KATETokens.LeftParenthesis) {
                    state.isLexingParameterOrRightParen = true
                }
            }

            '[' -> {
                return source.range(offset, KATETokens.LeftBracket) {
                    state.isLexingSingleParameter = true
                }
            }

            else -> return null
        }
    }

    private fun dpbStateIncrementerIfExistsAt(offset: Int): () -> Unit {
        val isDPBNext = source.lookAhead(offset)?.isDPB() == true
        return if (isDPBNext) {
            { state.isLexingDotParenBracket = true }
        } else {
            {
                state.isLexingDotParenBracket = false
                state.isLexingIdentifier = false
                state.isLexingNestedValue = false
            }
        }
    }

    private fun lexIdentifier(offset: Int): TokenRange? {
        if (source.lookAhead(offset)?.isLetter() == true) {
            val text =
                source.readTextAheadUntilLambdaOrStreamEnds(offset) { currChar, _ -> !currChar.isLetterOrDigit() }
            return text?.let {
                source.range(
                    offset = offset,
                    token = KATEToken.Identifier(it),
                    onIncrement = dpbStateIncrementerIfExistsAt(offset + it.length)
                )
            }
        }
        return null
    }

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        if(state.isLexingDotParenBracket) {
            if(state.isLexingIdentifier){
                return lexIdentifier(offset) ?: source.lexAsBadCharacters(offset)
            } else if(state.isLexingNestedValue){
                return nestedValueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement {
                        if (!nestedValueLexer.isLexing()) {
                            state.isLexingNestedValue = false
                            state.isLexingRightBracket = true
                        }
                    }
                } ?: source.lexAsBadCharacters(offset)
            } else if(state.isLexingRightBracket){
                return if (source.lookAhead(offset) == ']') {
                    source.range(
                        offset,
                        KATETokens.RightBracket,
                        onIncrement = dpbStateIncrementerIfExistsAt(offset + 1)
                    )
                } else {
                    source.lexAsBadCharacters(offset)
                }
            } else if(state.isLexingCommaOrRightParenthesis){
                source.lookAhead(offset)?.let {
                    when (it) {
                        ')' -> {
                            return source.range(
                                offset,
                                KATETokens.RightParenthesis,
                                onIncrement = dpbStateIncrementerIfExistsAt(offset + 1)
                            )
                        }

                        ',' -> return source.range(offset, KATETokens.Comma) {
                            state.isLexingParameter = true
                        }

                        else -> {}
                    }
                }
                return null
            }else if(state.isLexingParameter){
                nestedValueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement(
                        block = {
                            if (!nestedValueLexer.isLexing()) {
                                state.isLexingParameter = false
                                state.isLexingCommaOrRightParenthesis = true
                            }
                        }
                    )
                }
                return source.lexAsBadCharacters(offset)
            } else if(state.isLexingParameterOrRightParen){
                if (source.lookAhead(offset) == ')') {
                    return source.range(
                        offset,
                        KATETokens.RightParenthesis,
                        onIncrement = dpbStateIncrementerIfExistsAt(offset + 1)
                    )
                }
                nestedValueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement {
                        if (!nestedValueLexer.isLexing()) {
                            state.isLexingCommaOrRightParenthesis = true
                        }
                    }
                }
                return null
            }else if(state.isLexingSingleParameter){
                return nestedValueLexer.lexTokenAtPosition(offset)?.let {
                    return it.alsoOnIncrement {
                        if (!nestedValueLexer.isLexing()) {
                            state.isLexingRightBracket = true
                        }
                    }
                } ?: source.lexAsBadCharacters(offset)
            }else {
                return lexDPB(offset) ?: source.lexAsBadCharacters(offset)
            }

        } else {
            return lexIdentifier(offset)
        }
    }

}