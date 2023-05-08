package com.github.wakaztahir.kateidea

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import com.intellij.util.IconUtil
import javax.swing.Icon

object KATEFileType : LanguageFileType(KATELanguage) {

    private val actualIcon get() = IconLoader.getIcon("/icons/kate-file-icon.png", KATEFileType::class.java)

    private val icon = IconUtil.scale(actualIcon,null,0.065f)

    override fun getName(): String = "KATE"

    override fun getDescription(): String = "Kate language file"

    override fun getDefaultExtension(): String = "kate"

    override fun getIcon(): Icon = icon

}