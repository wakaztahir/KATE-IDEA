package com.github.wakaztahir.kateidea.parser

import com.github.wakaztahir.kateidea.KATELanguage
import com.github.wakaztahir.kateidea.parser.highlighting.KATEIDEToken
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class KATEParserDefinition : ParserDefinition {

    private val kateFileElementType = IFileElementType(KATELanguage)

    override fun createLexer(project: Project?): Lexer = NewLexer()

    override fun createParser(project: Project?): PsiParser = NewParser()

    override fun getFileNodeType(): IFileElementType = kateFileElementType

    override fun getCommentTokens(): TokenSet = TokenSet.EMPTY

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createElement(node: ASTNode): PsiElement = (node.elementType as? KATEIDEToken)?.toPsiElement(node)
        ?: throw IllegalStateException("element type must always be KATEElementType for KATEParserDefinition")

    override fun createFile(viewProvider: FileViewProvider): PsiFile = KATEFile(viewProvider)

}