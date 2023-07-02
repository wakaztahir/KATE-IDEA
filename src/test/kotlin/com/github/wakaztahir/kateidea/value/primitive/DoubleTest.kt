package com.github.wakaztahir.kateidea.value.primitive

import com.github.wakaztahir.kateidea.assertToken
import com.github.wakaztahir.kateidea.lexCode
import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens
import org.junit.Test
import kotlin.test.assertEquals

class DoubleTest {

    @Test
    fun testLexingInt() {
        val tokens = lexCode("""@var(118.567)""")
        assertToken<KATETokens.Var>(tokens[0])
        assertToken<KATETokens.LeftParenthesis>(tokens[1])
        assertToken<KATEToken.DoubleValue>(tokens[2]) { assertEquals(118.567, it.value) }
        assertToken<KATETokens.RightParenthesis>(tokens[3])
    }

}