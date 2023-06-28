package com.github.wakaztahir.kateidea.lexer.state

import kotlin.reflect.KProperty

interface LexStateSaver {
    fun toState(): Int
    fun restoreState(state: Int)
}

interface LexState<T : Any?> {
    val state: T
}

interface MutableLexState<T : Any?> : LexState<T> {
    override var state: T
}

interface LexSaveableState<T> : LexState<T>, LexStateSaver

interface MutableLexSaveableState<T> : MutableLexState<T>, LexSaveableState<T>

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> LexState<T>.getValue(container: Any, property: KProperty<*>): T {
    return this.state
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> MutableLexState<T>.setValue(container: Any, property: KProperty<*>, value: T) {
    this.state = value
}

interface LexStateList : LexStateSaver {

    val members: MutableList<LexStateSaver>

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

class EnumState<T : Enum<T>>(override var state: T, private val values: Array<T>) : MutableLexSaveableState<T> {

    override fun toState(): Int {
        return state.ordinal
    }

    override fun restoreState(state: Int) {
        for (value in values) {
            if (value.ordinal == state) {
                this.state = value
                return
            }
        }
        throw IllegalStateException("Enum State couldn't be restored from ordinal $state because of missing ordinal in values $values")
    }

}

//class DerivedState<T : Any?, K : Any?>(
//    initializer: T,
//    private val backing: LexSaveableState<K>,
//    private val convert: (T) -> K,
//    private val restore: (K) -> T,
//) : LexStateSaver, LexState<T> {
//
//    override var state: T = initializer
//        set(value) {
//            field = value
//            backing.state = convert(value)
//        }
//
//    override fun toState(): Int {
//        return backing.toState()
//    }
//
//    override fun restoreState(state: Int) {
//        backing.restoreState(state)
//        this.state = restore(backing.state)
//    }
//}

class LazyLexState<T : LexStateSaver>(creator: () -> T) : LexSaveableState<T> {

    private val value: Lazy<T> = lazy(creator)

    override val state: T get() = value.value

    fun isInitialized() : Boolean {
        return value.isInitialized()
    }

    override fun toState(): Int {
        return value.value.toState()
    }

    override fun restoreState(state: Int) {
        this.value.value.restoreState(state)
    }

}

class IntLexState(override var state: Int) : MutableLexSaveableState<Int> {
    override fun toState() = state
    override fun restoreState(state: Int) {
        this.state = state
    }
}

class BooleanLexState(override var state: Boolean) : MutableLexSaveableState<Boolean> {
    override fun toState(): Int {
        return if (state) 1 else 0
    }

    override fun restoreState(state: Int) {
        this.state = state == 1
    }
}

class CharLexState(override var state: Char) : MutableLexSaveableState<Char> {
    override fun toState(): Int {
        return state.code
    }

    override fun restoreState(state: Int) {
        this.state = Char(state)
    }
}

class FloatLexState(override var state: Float) : MutableLexState<Float>, LexStateSaver {
    override fun toState(): Int {
        return state.toRawBits()
    }

    override fun restoreState(state: Int) {
        this.state = Float.fromBits(state)
    }
}

open class CompositeLexState : LexStateList {

    override val members: MutableList<LexStateSaver> = mutableListOf()

    protected fun <T : LexStateSaver> state(state: T): T {
        members.add(state)
        return state
    }

    protected fun <T : LexStateSaver> state(value: List<T>): List<T> {
        members.addAll(value)
        return value
    }

    fun <T : Enum<T>> state(value: EnumState<T>): EnumState<T> {
        members.add(value)
        return value
    }

    protected fun <T : LexStateSaver> lazyState(value: () -> T): LazyLexState<T> {
        val actual = LazyLexState(value)
        members.add(actual)
        return actual
    }

    protected fun state(value: Boolean) = state(BooleanLexState(value))

    protected fun state(value: Char) = state(CharLexState(value))

    protected fun state(value: Int) = state(IntLexState(value))

    protected fun state(value: Float) = state(FloatLexState(value))

}

inline fun <reified T : Enum<T>> CompositeLexState.state(enum: T): EnumState<T> {
    return state(EnumState(enum, enumValues<T>()))
}