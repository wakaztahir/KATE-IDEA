package com.github.wakaztahir.kateidea.parser.highlighting

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey

enum class HighlightingAttributeKeys {

    None {
        override val colorKeys by lazy { arrayOf(HighlighterColors.NO_HIGHLIGHTING) }
    },

    Text {
        override val colorKeys by lazy { arrayOf(HighlighterColors.TEXT) }
    },

    BadCharacter {
        override val colorKeys by lazy { arrayOf(HighlighterColors.BAD_CHARACTER) }
    },

    TemplateLanguage {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.TEMPLATE_LANGUAGE_COLOR) }
    },

    Identifier {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.IDENTIFIER) }
    },

    Number {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.NUMBER) }
    },

    Keyword {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.KEYWORD) }
    },

    String {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.STRING) }
    },

    BlockComment {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.BLOCK_COMMENT) }
    },

    LineComment {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT) }
    },

    DocComment {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.DOC_COMMENT) }
    },

    OperationSign {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.OPERATION_SIGN) }
    },

    Braces {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.BRACES) }
    },

    Dot {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.DOT) }
    },

    Semicolon {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.SEMICOLON) }
    },

    Comma {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.COMMA) }
    },

    Parentheses {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.PARENTHESES) }
    },

    Brackets {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.BRACKETS) }
    },

    Label {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.LABEL) }
    },

    Constant {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.CONSTANT) }
    },

    LocalVariable {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.LOCAL_VARIABLE) }
    },

    ReassignedLoc {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.REASSIGNED_LOCAL_VARIABLE) }
    },

    GlobalVariable {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.GLOBAL_VARIABLE) }
    },

    FunctionDeclaration {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.FUNCTION_DECLARATION) }
    },

    FunctionCall {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.FUNCTION_CALL) }
    },

    Parameter {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.PARAMETER) }
    },

    ReassignedPar {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.REASSIGNED_PARAMETER) }
    },

    ClassName {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.CLASS_NAME) }
    },

    InterfaceName {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.INTERFACE_NAME) }
    },

    ClassReference {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.CLASS_REFERENCE) }
    },

    InstanceMethod {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.INSTANCE_METHOD) }
    },

    InstanceField {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.INSTANCE_FIELD) }
    },

    StaticMethod {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.STATIC_METHOD) }
    },

    StaticField {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.STATIC_FIELD) }
    },

    DocCommentMar {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.DOC_COMMENT_MARKUP) }
    },

    DocCommentTag {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.DOC_COMMENT_TAG) }
    },

    DocCommentTagValue {
        override val colorKeys by lazy { arrayOf(DefaultLanguageHighlighterColors.DOC_COMMENT_TAG_VALUE) }
    };

    abstract val colorKeys: Array<TextAttributesKey>

}