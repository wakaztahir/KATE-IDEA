package com.github.wakaztahir.kateidea.value.primitive

import com.github.wakaztahir.kateidea.assertToken
import com.github.wakaztahir.kateidea.lexCode
import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens
import org.junit.Test
import kotlin.test.assertEquals

class CharTest {

    @Test
    fun testLexingChar() {
        val tokens = lexCode("""@var('x')""")
        assertToken<KATETokens.Var>(tokens[0])
        assertToken<KATETokens.LeftParenthesis>(tokens[1])
        assertToken<KATEToken.CharValue>(tokens[2]) { assertEquals('x', it.value) }
        assertToken<KATETokens.RightParenthesis>(tokens[3])
    }

    @Test
    fun testEscapesChar() {
        val tokens = lexCode("""@var('\n')""")
        assertToken<KATETokens.Var>(tokens[0])
        assertToken<KATETokens.LeftParenthesis>(tokens[1])
        assertToken<KATEToken.StringEscape>(tokens[2]) { assertEquals('\n', it.value) }
        assertToken<KATETokens.RightParenthesis>(tokens[3])
    }

}