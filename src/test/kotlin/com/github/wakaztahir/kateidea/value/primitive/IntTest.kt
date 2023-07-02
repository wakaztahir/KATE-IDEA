package com.github.wakaztahir.kateidea.value.primitive

import com.github.wakaztahir.kateidea.assertToken
import com.github.wakaztahir.kateidea.lexCode
import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens
import org.junit.Assert.assertEquals
import org.junit.Test

class IntTest {

    @Test
    fun testLexingInt() {
        val tokens = lexCode("""@var(118)""")
        assertToken<KATETokens.Var>(tokens[0])
        assertToken<KATETokens.LeftParenthesis>(tokens[1])
        assertToken<KATEToken.IntValue>(tokens[2]) { assertEquals(118, it.value) }
        assertToken<KATETokens.RightParenthesis>(tokens[3])
    }

}