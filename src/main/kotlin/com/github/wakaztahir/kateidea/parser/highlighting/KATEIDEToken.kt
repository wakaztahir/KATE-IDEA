package com.github.wakaztahir.kateidea.parser.highlighting

import com.github.wakaztahir.kateidea.KATELanguage
import com.github.wakaztahir.kateidea.lexer.KATEToken
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType

abstract class KATEIDEToken(debugName: String) : IElementType(debugName, KATELanguage) {

    constructor(debugName: Char) : this(debugName.toString())

    abstract val highlightingAttributeKeys: HighlightingAttributeKeys

    open fun toPsiElement(astNode: ASTNode): PsiElement = ASTWrapperPsiElement(astNode)

}