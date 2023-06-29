package com.github.wakaztahir.kateidea.lexer.states

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.range
import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.wakaztahir.kate.lexer.stream.SourceStream

class DefaultNoRawLexer(
    private val source: SourceStream,
    private val endAtDefaultNoRaw: Boolean
) : Lexer, CompositeLexState() {

    private val lexersList: List<Lexer> = state(
        listOf(
            CommentLexer(source),
            EmbedLexer(source, isDefaultNoRaw = true),
            RawLexer(source, isDefaultNoRaw = true),
            RuntimeWriteLexer(source, isDefaultNoRaw = true),
            PlaceholderUseLexer(source,isDefaultNoRaw = true),
            PlaceholderInvocationLexer(source,isDefaultNoRaw = true),
            VariableDeclarationLexer(source, isDefaultNoRaw = true),
            VariableAssignmentLexer(source, isDefaultNoRaw = true)
        )
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