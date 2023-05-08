package com.github.wakaztahir.kateidea.parser.tokenizer

import com.intellij.psi.PsiElement
import com.wakaztahir.kate.model.CodeGen
import com.wakaztahir.kate.tokenizer.NodeTokenizer

object KATEElementTypesFactory : NodeTokenizer<KATEElementTypeProvider> {

    override val kateParsingError: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("ParsingError")
    }
    override val conditionalFor: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("ConditionalFor")
    }
    override val defaultNoRawBlock: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("DefaultNoRawBlock")
    }
    override val defaultNoRawString: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("DefaultNoRawString")
    }
    override val embeddingDirective: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("EmbeddingDirective")
    }
    override val functionDefinition: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("FunctionDefinition")
    }
    override val ifStatement: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("IfStatement")
    }
    override val iterableFor: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("IterableFor")
    }
    override val kateUnit: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("KateUnit")
    }
    override val multilineComment: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("MultilineComment")
    }
    override val numberedFor: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("NumberedFor")
    }
    override val objectDeclaration: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("ObjectDeclaration")
    }
    override val partialRawBlock: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("PartialRawBlock")
    }
    override val placeholderDefinition: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("PlaceholderDefinition")
    }
    override val placeholderInvocation: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("PlaceholderInvocation")
    }
    override val placeholderUse: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("PlaceholderUse")
    }
    override val rawBlock: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("RawBlock")
    }
    override val runtimeWriteChar: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("RuntimeWriteChar")
    }
    override val runtimeWriteString: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("RuntimeWriteString")
    }
    override val singleIf: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("SingleIf")
    }
    override val variableAssignment: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("VariableAssignment")
    }
    override val variableDeclaration: KATEElementTypeProvider = object : KATEElementTypeProvider {
        override fun provide(codeGen: CodeGen): KATEElementType = KATEElementType("VariableDeclaration")
    }
}