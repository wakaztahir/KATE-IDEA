package com.github.wakaztahir.kateidea.parser.tokenizer

import com.wakaztahir.kate.model.*
import com.wakaztahir.kate.model.block.DefaultNoRawString
import com.wakaztahir.kate.model.model.KATEUnit
import com.wakaztahir.kate.parser.ForLoop
import com.wakaztahir.kate.parser.function.FunctionDefinition
import com.wakaztahir.kate.tokenizer.NodeTokenizer

interface ConverterNodeTokenizer : NodeTokenizer<Converter<out CodeGen>> {

    override val conditionalFor: Converter<ForLoop.ConditionalFor>

    override val defaultNoRawBlock: Converter<DefaultNoRawBlock>

    override val defaultNoRawString: Converter<DefaultNoRawString>

    override val embeddingDirective: Converter<EmbeddingDirective>

    override val functionDefinition: Converter<FunctionDefinition>

    override val ifStatement: Converter<IfStatement>

    override val iterableFor: Converter<ForLoop.IterableFor>

    override val kateUnit: Converter<KATEUnit>

    override val numberedFor: Converter<ForLoop.NumberedFor>

    override val objectDeclaration: Converter<ObjectDeclaration>

    override val partialRawBlock: Converter<PartialRawBlock>

    override val placeholderDefinition: Converter<PlaceholderDefinition>

    override val placeholderInvocation: Converter<PlaceholderInvocation>

    override val placeholderUse: Converter<PlaceholderUse>

    override val rawBlock: Converter<RawBlock>

    override val runtimeWriteChar: Converter<WriteString>

    override val runtimeWriteString: Converter<WriteString>

    override val singleIf: Converter<SingleIf>

    override val variableAssignment: Converter<Assignment>

    override val variableDeclaration: Converter<VariableDeclaration>

}