package com.github.wakaztahir.kateidea

import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens
import com.github.wakaztahir.kateidea.lexer.RealLexer
import com.github.wakaztahir.kateidea.lexer.token.TypedToken
import com.wakaztahir.kate.lexer.stream.TextSourceStream

/**
 * assert that [token] is of type [T]
 */
inline fun <reified T : KATEToken> assertToken(token: KATEToken) {
    assert(token is T)
}

/**
 * assert that [token] is of type [T] and run lambda on it
 */
inline fun <reified T : KATEToken> assertToken(token: KATEToken, block: (T) -> Unit) {
    assert(token is T) {
        "$token is not of type ${T::class}"
    }
    block(token as T)
}

/**
 * assert on [tokens] that they are a variable reference
 * meaning assert that at [startIndex] @var and next '(' and at the end ')' exist
 */
@Suppress("NOTHING_TO_INLINE")
inline fun testVariableReference(startIndex: Int = 0, tokens: List<KATEToken>) {
    assertToken<KATETokens.Var>(tokens[startIndex])
    assertToken<KATETokens.LeftParenthesis>(tokens[startIndex + 1])
    assertToken<KATETokens.RightParenthesis>(tokens.last())
}

inline fun <reified T : KATEToken> testVariableReference(code: String, block: (T) -> Unit) {
    val tokens = lexTemplateCode(code)
    testVariableReference(0, tokens)
    assertToken<T>(tokens[2], block)
}

/**
 * lex [code] and return tokens as list
 */
fun lexTemplateCode(code: String): List<KATEToken> {
    val source = TextSourceStream(code)
    val lexer = RealLexer(source)
    val tokens = mutableListOf<KATEToken>()
    while (!source.hasEnded) {
        val range = lexer.lexCurrentToken() ?: return tokens
        tokens.add(range.token)
        range.increment(source)
    }
    return tokens
}

/**
 * extract string from tokens , text is extracted from tokens of type [TypedToken]
 */
fun extractStringFromTokens(tokens: List<KATEToken>, startIndex: Int = 0, stopIndex: Int = tokens.size): String {
    var x = startIndex
    var text = ""
    while (x < stopIndex) {
        if (tokens[x] is TypedToken<*>) {
            text += (tokens[x] as TypedToken<*>).value
        } else {
            error("unexpected token ${tokens[x]} at $x where lexed text is $text")
        }
        x++
    }
    return text
}