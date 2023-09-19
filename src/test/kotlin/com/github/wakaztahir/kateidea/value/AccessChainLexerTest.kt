package com.github.wakaztahir.kateidea.value

import com.github.wakaztahir.kateidea.assertNoErrorToken
import com.github.wakaztahir.kateidea.lexTemplateCode
import org.junit.Test
import kotlin.test.assertEquals

class AccessChainLexerTest {

    @Test
    fun testSimpleAccessChain() {
        val tokens = lexTemplateCode("#var(variable[1])")
        tokens.assertNoErrorToken()
        assertEquals("var(variable[1])", tokens.joinToString(""))
    }

}