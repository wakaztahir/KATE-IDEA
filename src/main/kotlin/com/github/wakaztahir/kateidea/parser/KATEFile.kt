package com.github.wakaztahir.kateidea.parser

import com.github.wakaztahir.kateidea.KATEFileType
import com.github.wakaztahir.kateidea.KATELanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class KATEFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, KATELanguage) {
    override fun getFileType(): FileType = KATEFileType
    override fun toString(): String = "KATE File"
}