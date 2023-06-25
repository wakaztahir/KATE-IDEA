package com.github.wakaztahir.kateidea.lexer

sealed interface TokenType {

    object None : TokenType

    object Text : TokenType

    object BadCharacter : TokenType

    object TemplateLanguageColor : TokenType

    object Identifier : TokenType

    object Number : TokenType

    object Keyword : TokenType

    object String : TokenType

    object BlockComment : TokenType

    object LineComment : TokenType

    object DocComment : TokenType

    object OperationSign : TokenType

    object Braces : TokenType

    object Dot : TokenType

    object Semicolon : TokenType

    object Comma : TokenType

    object Parentheses : TokenType

    object Brackets : TokenType

    object Label : TokenType

    object Constant : TokenType

    object LocalVariable : TokenType

    object ReassignedLocalVariable : TokenType

    object GlobalVariable : TokenType

    object FunctionDeclaration : TokenType

    object FunctionCall : TokenType

    object Parameter : TokenType

    object ReassignedParameter : TokenType

    object ClassName : TokenType

    object InterfaceName : TokenType

    object ClassReference : TokenType

    object InstanceMethod : TokenType

    object InstanceField : TokenType

    object StaticMethod : TokenType

    object StaticField : TokenType

    object DocCommentMarkup : TokenType

    object DocCommentTag : TokenType

    object DocCommentTagValue : TokenType

}