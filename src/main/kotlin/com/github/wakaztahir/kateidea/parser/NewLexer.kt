package com.github.wakaztahir.kateidea.parser

import com.github.wakaztahir.kateidea.lexer.RealLexer
import com.github.wakaztahir.kateidea.lexer.states.TokenRange
import com.github.wakaztahir.kateidea.parser.highlighting.KATEIDETokenConverter
import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType
import com.wakaztahir.kate.lexer.stream.TextSourceStream

class NewLexer : LexerBase() {

    private var buffer: CharSequence = ""
    private var startOffset: Int = 0
    private var endOffset: Int = 0

    private var currentToken: TokenRange? = null
    private var realLexer: RealLexer = RealLexer(TextSourceStream("", 0, 0))

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.realLexer = RealLexer(TextSourceStream(buffer, startOffset, endOffset))
        this.realLexer.restoreState(initialState)
        this.buffer = buffer
        this.startOffset = startOffset
        this.endOffset = endOffset
        currentToken = realLexer.lexCurrentToken()
    }

    override fun getState(): Int {
        return this.realLexer.getState()
    }

    override fun getTokenType(): IElementType? {
        return currentToken?.token?.convert(KATEIDETokenConverter)
    }

    override fun getTokenStart(): Int {
        return currentToken!!.start
    }

    override fun getTokenEnd(): Int {
        return currentToken!!.end
    }

    override fun advance() {
        realLexer.incrementStream(currentToken!!)
        currentToken = realLexer.lexCurrentToken()
    }

    override fun getBufferSequence(): CharSequence {
        return buffer
    }

    override fun getBufferEnd(): Int {
        return endOffset
    }

}