package com.github.wakaztahir.kateidea.value.primitive

import com.github.wakaztahir.kateidea.extractStringFromTokens
import com.github.wakaztahir.kateidea.lexTemplateCode
import com.github.wakaztahir.kateidea.testVariableReference
import org.junit.Assert.assertEquals
import org.junit.Test

class StringTest {

    private fun assertLexedStringEquals(expected: String, actual: String) {
        val tokens = lexTemplateCode("""@var("$expected")""")
        testVariableReference(0, tokens)
        assertEquals(extractStringFromTokens(tokens, 2, tokens.size - 1), actual)
    }

    @Test
    fun testLexingString() {
        assertLexedStringEquals("Hello World\\nHello\\n\\nWorld", "Hello World\nHello\n\nWorld")
        assertLexedStringEquals("\\n\\tCheck\\r\\t\\n", "\n\tCheck\r\t\n")
        assertLexedStringEquals("\\nA\\tCheck\\rB\\tC\\nD", "\nA\tCheck\rB\tC\nD")
        assertLexedStringEquals("\\n", "\n")
        assertLexedStringEquals("\\n\\n", "\n\n")
        assertLexedStringEquals("\\n\\n\\'\\'", "\n\n''")
        assertLexedStringEquals("\\\"\\\"", "\"\"")
        assertLexedStringEquals("a\\nb\\n", "a\nb\n")
        assertLexedStringEquals("a\\nb\\n\\n", "a\nb\n\n")
    }

}