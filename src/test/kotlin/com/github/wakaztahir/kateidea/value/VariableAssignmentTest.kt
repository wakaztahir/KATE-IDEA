package com.github.wakaztahir.kateidea.value

import com.github.wakaztahir.kateidea.assertNoErrorToken
import com.github.wakaztahir.kateidea.lexTemplateCode
import org.junit.Test
import kotlin.test.assertEquals

class VariableAssignmentTest {

    @Test
    fun testVariableAssignment() {
        val lexed = lexTemplateCode("#set_var x = 5")
        lexed.assertNoErrorToken()
        assertEquals("set_var x = 5", lexed.joinToString(""))
    }

}