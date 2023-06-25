package com.github.wakaztahir.kateidea.lexer.state

import kotlin.reflect.KProperty

interface LexStateSaver {
    fun toState(): Int
    fun restoreState(state: Int)
}

interface LexState<T> {
    var state: T
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> LexState<T>.getValue(container: Any, property: KProperty<*>): T {
    return this.state
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> LexState<T>.setValue(container: Any, property: KProperty<*>, value: T) {
    this.state = value
}

interface LexStateList : LexStateSaver {

    var members: MutableList<LexStateSaver>

    override fun toState(): Int {
        var stateValue = 0
        for (member in members) {
            stateValue = stateValue or member.toState()
        }
        return stateValue
    }

    override fun restoreState(state: Int) {
        var tempValue = state
        for (member in members) {
            val componentValue = tempValue and -tempValue
            member.restoreState(componentValue)
            tempValue = tempValue xor componentValue
        }
    }

}

class IntLexState(override var state: Int) : LexState<Int>, LexStateSaver {
    override fun toState() = state
    override fun restoreState(state: Int) {
        this.state = state
    }
}

class BooleanLexState(override var state: Boolean) : LexState<Boolean>, LexStateSaver {
    override fun toState(): Int {
        return if (state) 1 else 0
    }

    override fun restoreState(state: Int) {
        this.state = state == 1
    }
}

class CharLexState(override var state: Char) : LexState<Char>, LexStateSaver {
    override fun toState(): Int {
        return state.code
    }

    override fun restoreState(state: Int) {
        this.state = Char(state)
    }
}

class FloatLexState(override var state: Float) : LexState<Float>, LexStateSaver {
    override fun toState(): Int {
        return state.toRawBits()
    }

    override fun restoreState(state: Int) {
        this.state = Float.fromBits(state)
    }
}

open class CompositeLexState : LexStateList {

    override var members: MutableList<LexStateSaver> = mutableListOf()

    private fun <T : LexStateSaver> makeState(state: T): T {
        members.add(state)
        return state
    }

    protected fun state(value: Boolean) = makeState(BooleanLexState(value))

    protected fun state(value: Char) = makeState(CharLexState(value))

    protected fun state(value: Int) = makeState(IntLexState(value))

    protected fun state(value: Float) = makeState(FloatLexState(value))

}