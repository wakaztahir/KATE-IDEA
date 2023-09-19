package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.LexerState
import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.github.wakaztahir.kateidea.lexer.range
import com.wakaztahir.kate.lexer.stream.SourceStream

class DefaultNoRawLexer(
    private val source: SourceStream,
    private val state: LexerState,
    private val endAtDefaultNoRaw: Boolean,
) : Lexer {

    private val lexersList: List<Lexer> = listOf(
        CommentLexer(source, state),
        EmbedLexer(source, state, isDefaultNoRaw = true),
        RawLexer(source, state, isDefaultNoRaw = true),
        RuntimeWriteLexer(source, state, isDefaultNoRaw = true),
        PlaceholderUseLexer(source, state, isDefaultNoRaw = true),
        PlaceholderInvocationLexer(source, state, isDefaultNoRaw = true),
        VariableDeclarationLexer(source, state, isDefaultNoRaw = true),
        VariableAssignmentLexer(source, state, isDefaultNoRaw = true),
    )

    override fun lexTokenAtPosition(offset: Int): TokenRange? {
        var x = offset
        var text = ""
        do {
            // Checking if other lexers can handle this
            var hasToken: TokenRange? = null
            for (lexer in lexersList) {
                val token = lexer.lexTokenAtPosition(x)
                if (token != null) {
                    if (x == offset) return token
                    hasToken = token
                    break
                }
            }
            if (hasToken != null) break
            val currChar = source.lookAhead(x)
            if (currChar != null) {
                text += currChar
                x++
            }
        } while (currChar != null)
        if (source.hasEnded) return null
        return source.range(offset, KATEToken.OutputString(text))
    }

}