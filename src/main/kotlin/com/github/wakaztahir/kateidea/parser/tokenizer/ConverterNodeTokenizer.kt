package com.github.wakaztahir.kateidea.parser.tokenizer

import com.wakaztahir.kate.model.*
import com.wakaztahir.kate.model.block.DefaultNoRawString
import com.wakaztahir.kate.model.model.KATEParsingError
import com.wakaztahir.kate.model.model.KATEUnit
import com.wakaztahir.kate.parser.ForLoop
import com.wakaztahir.kate.parser.MultilineComment
import com.wakaztahir.kate.parser.WriteChar
import com.wakaztahir.kate.parser.WriteString
import com.wakaztahir.kate.parser.function.FunctionDefinition
import com.wakaztahir.kate.parser.variable.VariableAssignment
import com.wakaztahir.kate.parser.variable.VariableDeclaration
import com.wakaztahir.kate.tokenizer.NodeTokenizer

interface ConverterNodeTokenizer<R> : NodeTokenizer<Converter<out CodeGen, R>> {

    override val kateParsingError: Converter<KATEParsingError, R>

    override val multilineComment: Converter<MultilineComment, R>

    override val conditionalFor: Converter<ForLoop.ConditionalFor, R>

    override val defaultNoRawBlock: Converter<DefaultNoRawBlock, R>

    override val defaultNoRawString: Converter<DefaultNoRawString, R>

    override val embeddingDirective: Converter<EmbeddingDirective, R>

    override val functionDefinition: Converter<FunctionDefinition, R>

    override val ifStatement: Converter<IfStatement, R>

    override val iterableFor: Converter<ForLoop.IterableFor, R>

    override val kateUnit: Converter<KATEUnit, R>

    override val numberedFor: Converter<ForLoop.NumberedFor, R>

    override val objectDeclaration: Converter<ObjectDeclaration, R>

    override val partialRawBlock: Converter<PartialRawBlock, R>

    override val placeholderDefinition: Converter<PlaceholderDefinition, R>

    override val placeholderInvocation: Converter<PlaceholderInvocation, R>

    override val placeholderUse: Converter<PlaceholderUse, R>

    override val rawBlock: Converter<RawBlock, R>

    override val runtimeWriteChar: Converter<WriteChar, R>

    override val runtimeWriteString: Converter<WriteString, R>

    override val singleIf: Converter<SingleIf, R>

    override val variableAssignment: Converter<VariableAssignment, R>

    override val variableDeclaration: Converter<VariableDeclaration, R>

}