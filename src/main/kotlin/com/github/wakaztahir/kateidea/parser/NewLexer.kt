package com.github.wakaztahir.kateidea.parser

import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType

class NewLexer : LexerBase() {

    private var buffer: CharSequence? = null
    private var startOffset: Int = 0
    private var endOffset: Int = 0

    private var currentToken: RealLexer.KLexToken? = null
    private val realLexer: RealLexer = RealLexer(LexSource("", 0, 0))

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.realLexer.source = LexSource(buffer, startOffset, endOffset)
        this.buffer = buffer
        this.startOffset = startOffset
        this.endOffset = endOffset
        currentToken = realLexer.lexCurrentToken()
    }

    override fun getState(): Int {
        return 0
    }

    override fun getTokenType(): IElementType? {
        return currentToken?.realToken
    }

    override fun getTokenStart(): Int {
        return currentToken!!.start
    }

    override fun getTokenEnd(): Int {
        return currentToken!!.end
    }

    override fun advance() {
        realLexer.source.advanceLexer(currentToken!!)
        currentToken = realLexer.lexCurrentToken()
    }

    override fun getBufferSequence(): CharSequence {
        return realLexer.source.buffer
    }

    override fun getBufferEnd(): Int {
        return endOffset
    }

}