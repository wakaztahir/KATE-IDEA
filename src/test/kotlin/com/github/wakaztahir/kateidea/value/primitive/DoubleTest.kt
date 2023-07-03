package com.github.wakaztahir.kateidea.value.primitive

import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.github.wakaztahir.kateidea.testVariableReference
import org.junit.Test
import kotlin.test.assertEquals

class DoubleTest {

    @Test
    fun testLexingInt() {
        testVariableReference<KATEToken.DoubleValue>("""@var(118.567)""") {
            assertEquals(118.567, it.value)
        }
    }

}