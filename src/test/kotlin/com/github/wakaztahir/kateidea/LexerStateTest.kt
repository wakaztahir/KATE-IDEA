package com.github.wakaztahir.kateidea

import com.github.wakaztahir.kateidea.lexer.state.CompositeLexState
import com.github.wakaztahir.kateidea.lexer.state.IntLexState
import org.junit.Test

class LexerStateTest {

    @Test
    fun testLexerState() {
        val state = CompositeLexState()
        val expected = listOf(
            IntLexState(91),
            IntLexState(82),
            IntLexState(73),
            IntLexState(64),
            IntLexState(55)
        )
        state.members.addAll(expected)
        val saved = state.toState()
        for(x in expected) x.restoreState(0)
        state.restoreState(saved)
        assert(expected == state.members) {
            "Expected : $expected\nActual : ${state.members}"
        }
    }

}