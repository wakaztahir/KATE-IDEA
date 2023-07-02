package com.github.wakaztahir.kateidea

import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.RealLexer
import com.wakaztahir.kate.lexer.stream.TextSourceStream

inline fun <reified T : KATEToken> assertToken(token: KATEToken) {
    assert(token is T)
}

inline fun <reified T : KATEToken> assertToken(token: KATEToken, block: (T) -> Unit) {
    assert(token is T) {
        "$token is not of type ${T::class}"
    }
    block(token as T)
}

fun lexCode(code: String): List<KATEToken> {
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