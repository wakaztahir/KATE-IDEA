package com.github.wakaztahir.kateidea.parser.tokenizer

import com.github.wakaztahir.kateidea.KATELanguage
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType

open class KATEElementType(debugName: String) : IElementType(debugName, KATELanguage) {
    open fun toPsiElement(astNode: ASTNode): PsiElement = ASTWrapperPsiElement(astNode)
}