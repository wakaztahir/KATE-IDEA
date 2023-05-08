package com.github.wakaztahir.kateidea.parser

import com.github.wakaztahir.kateidea.parser.tokenizer.KATEElementTypes
import com.intellij.ide.highlighter.custom.AbstractCustomLexer
import com.intellij.ide.highlighter.custom.tokens.TokenParser
import com.intellij.lexer.Lexer
import com.intellij.lexer.LexerBase
import com.intellij.lexer.LexerPosition
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.jetbrains.rd.util.LogLevel
import com.jetbrains.rd.util.Logger

class KATELexer  : LexerBase() {
    private lateinit var buffer: CharSequence
    private var startOffset: Int = 0
    private var endOffset: Int = 0
    private var currentPosition: Int = 0

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.startOffset = startOffset
        this.endOffset = endOffset
        this.currentPosition = startOffset
    }

    override fun getState(): Int {
        return 0 // We don't have states in this simple lexer
    }

    override fun getTokenType(): IElementType? {
        return if (currentPosition < endOffset) {
            TokenType.BAD_CHARACTER // Treat everything as a code character
        } else {
            null // We reached the end of the buffer
        }
    }

    override fun getTokenStart(): Int {
        return currentPosition
    }

    override fun getTokenEnd(): Int {
        return currentPosition
    }

    override fun advance() {
        currentPosition++
    }

    override fun getBufferSequence(): CharSequence {
        return buffer
    }

    override fun getBufferEnd(): Int {
        return endOffset
    }
}