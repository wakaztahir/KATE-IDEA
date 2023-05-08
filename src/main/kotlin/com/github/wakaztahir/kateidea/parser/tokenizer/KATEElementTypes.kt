package com.github.wakaztahir.kateidea.parser.tokenizer

import com.github.wakaztahir.kateidea.KATELanguage
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.wakaztahir.kate.tokenizer.NodeTokenizer

object KATEElementTypes : NodeTokenizer<KATEElementType> {

    override val conditionalFor: KATEElementType = KATEElementType("ConditionalFor")
    override val defaultNoRawBlock: KATEElementType = KATEElementType("DefaultNoRawBlock")
    override val defaultNoRawString: KATEElementType = KATEElementType("DefaultNoRawString")
    override val embeddingDirective: KATEElementType = KATEElementType("EmbeddingDirective")
    override val functionDefinition: KATEElementType = KATEElementType("FunctionDefinition")
    override val ifStatement: KATEElementType = KATEElementType("IfStatement")
    override val iterableFor: KATEElementType = KATEElementType("IterableFor")
    override val kateUnit: KATEElementType = KATEElementType("KateUnit")
    override val multilineComment: KATEElementType = KATEElementType("MultilineComment")
    override val numberedFor: KATEElementType = KATEElementType("NumberedFor")
    override val objectDeclaration: KATEElementType = KATEElementType("ObjectDeclaration")
    override val partialRawBlock: KATEElementType = KATEElementType("PartialRawBlock")
    override val placeholderDefinition: KATEElementType = KATEElementType("PlaceholderDefinition")
    override val placeholderInvocation: KATEElementType = KATEElementType("PlaceholderInvocation")
    override val placeholderUse: KATEElementType = KATEElementType("PlaceholderUse")
    override val rawBlock: KATEElementType = KATEElementType("RawBlock")
    override val runtimeWriteChar: KATEElementType = KATEElementType("RuntimeWriteChar")
    override val runtimeWriteString: KATEElementType = KATEElementType("RuntimeWriteString")
    override val singleIf: KATEElementType = KATEElementType("SingleIf")
    override val variableAssignment: KATEElementType = KATEElementType("VariableAssignment")
    override val variableDeclaration: KATEElementType = KATEElementType("VariableDeclaration")
}