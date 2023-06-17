package com.github.wakaztahir.kateidea.parser

import com.github.wakaztahir.kateidea.parser.tokenizer.ParsedCodeGenImpl
import com.wakaztahir.kate.model.CodeGen
import com.wakaztahir.kate.model.LazyBlock
import com.wakaztahir.kate.model.block.DefaultNoRawString
import com.wakaztahir.kate.parser.stream.DestinationStream
import com.wakaztahir.kate.parser.stream.TextDestinationStream

class ParsedBlockParser(private val block: LazyBlock) : LazyBlock by block {

    val codeGens = mutableListOf<ParsedCodeGenImpl>()

    fun parseCompletely(destination: TextDestinationStream = TextDestinationStream()): List<ParsedCodeGenImpl> {
        parse().generateTo(destination)
        return codeGens
    }

    override fun writeDirective(previous : Int,directive: CodeGen, destination: DestinationStream) {
        codeGens.add(ParsedCodeGenImpl(codeGen = directive, startPointer = previous, endPointer = source.pointer))
    }

    override fun writeCurrentChar(destination: DestinationStream) {
        if (codeGens.isEmpty() || codeGens.last().codeGen !is DefaultNoRawString) {
            codeGens.add(
                ParsedCodeGenImpl(
                    codeGen = DefaultNoRawString("${source.currentChar}"),
                    startPointer = source.pointer,
                    endPointer = source.pointer + 1
                )
            )
        } else {
            // todo kinda hacky
            val last = codeGens.removeLast()
            codeGens.add(
                ParsedCodeGenImpl(
                    codeGen = DefaultNoRawString((last.codeGen as DefaultNoRawString).stringValue + source.currentChar),
                    startPointer = last.startPointer,
                    endPointer = last.endPointer + 1
                )
            )
        }
    }

}