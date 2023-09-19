package com.github.wakaztahir.kateidea.value

import com.github.wakaztahir.kateidea.assertNoErrorToken
import com.github.wakaztahir.kateidea.lexTemplateCode
import org.junit.Test
import kotlin.test.assertEquals

class VariableDeclarationTest {

    @Test
    fun testVariableDeclaration() {
        val lexed = lexTemplateCode("#var x = 5")
        lexed.assertNoErrorToken()
        assertEquals("var x = 5", lexed.joinToString(""))
    }

}