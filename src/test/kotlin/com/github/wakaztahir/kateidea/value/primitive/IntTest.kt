package com.github.wakaztahir.kateidea.value.primitive

import com.github.wakaztahir.kateidea.lexer.token.KATEToken
import com.github.wakaztahir.kateidea.testVariableReference
import org.junit.Assert.assertEquals
import org.junit.Test

class IntTest {

    @Test
    fun testLexingInt() {
        testVariableReference<KATEToken.IntValue>("""#var(118)""") {
            assertEquals(118, it.value)
        }
    }

}