package com.github.wakaztahir.kateidea.value.primitive

import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.github.wakaztahir.kateidea.lexer.token.TypedToken
import com.github.wakaztahir.kateidea.testVariableReference
import org.junit.Test
import kotlin.test.assertEquals

class CharTest {

    private fun assertLexedCharEquals(charValue: String, expected: Char) {
        testVariableReference<TypedToken.Char>("""#var('$charValue')""") {
            assertEquals(it.value, expected)
        }
    }

    @Test
    fun testLexingChar() {
        assertLexedCharEquals("x", 'x')
        assertLexedCharEquals("\\n", '\n')
        assertLexedCharEquals("\\t", '\t')
        assertLexedCharEquals("\\r", '\r')
        assertLexedCharEquals("\\b", '\b')
        assertLexedCharEquals("\\'", '\'')
        assertLexedCharEquals("\\\"", '\"')
        assertLexedCharEquals("\\\\", '\\')
    }

}