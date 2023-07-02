package com.github.wakaztahir.kateidea.value.primitive

import com.github.wakaztahir.kateidea.assertToken
import com.github.wakaztahir.kateidea.lexCode
import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.github.wakaztahir.kateidea.lexer.KATETokens
import org.junit.Assert.assertEquals
import org.junit.Test

class StringTest {

    @Test
    fun testLexingString() {
        val tokens = lexCode("""@var("Hello World\nHello\n\nWorld")""")
        assertToken<KATETokens.Var>(tokens[0])
        assertToken<KATETokens.LeftParenthesis>(tokens[1])
        assertToken<KATEToken.String>(tokens[2]) { assertEquals("Hello World", it.value) }
        assertToken<KATEToken.StringEscape>(tokens[3]) { assertEquals('\n',it.value) }
        assertToken<KATEToken.String>(tokens[4]){ assertEquals("Hello",it.value) }
        assertToken<KATEToken.StringEscape>(tokens[5]) { assertEquals('\n',it.value) }
        assertToken<KATEToken.StringEscape>(tokens[6]) { assertEquals('\n',it.value) }
        assertToken<KATEToken.String>(tokens[7]){ assertEquals("World",it.value) }
        assertToken<KATETokens.RightParenthesis>(tokens[8])
    }

}