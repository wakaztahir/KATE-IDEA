package com.github.wakaztahir.kateidea.lexer

class LexerState {

    private var boolArray: BooleanArray = BooleanArray(32) { false }

    var isLexingFor: Boolean
        get() = boolArray[0]
        set(value) {
            boolArray[0] = value
        }

    var isLexingExpression: Boolean
        get() = boolArray[1]
        set(value) {
            boolArray[1] = value
        }

    var hasLexedLParen: Boolean
        get() = boolArray[2]
        set(value) {
            boolArray[2] = value
        }

    var isLexingEndFor: Boolean
        get() = boolArray[3]
        set(value) {
            boolArray[3] = value
        }

    var isLexingEmbedPath: Boolean
        get() = boolArray[4]
        set(value) {
            boolArray[4] = value
        }

    var isLexingComment: Boolean
        get() = boolArray[5]
        set(value) {
            boolArray[5] = value
        }

    var hasLexedCommentContent: Boolean
        get() = boolArray[6]
        set(value) {
            boolArray[6] = value
        }

    var isLexingRaw: Boolean
        get() = boolArray[7]
        set(value) {
            boolArray[7] = value
        }

    var hasLexedRawText: Boolean
        get() = boolArray[8]
        set(value) {
            boolArray[8] = value
        }

    var isLexingRuntimeWrite: Boolean
        get() = boolArray[9]
        set(value) {
            boolArray[9] = value
        }

    var isLexingPlaceholderUse: Boolean
        get() = boolArray[10]
        set(value) {
            boolArray[10] = value
        }

    var isLexingCommaOrRightParenthesis: Boolean
        get() = boolArray[11]
        set(value) {
            boolArray[11] = value
        }

    var isLexingPlaceholderInvocation: Boolean
        get() = boolArray[12]
        set(value) {
            boolArray[12] = value
        }

    var isLexingVariableNameOrParen: Boolean
        get() = boolArray[13]
        set(value) {
            boolArray[13] = value
        }

    var isLexingEqual: Boolean
        get() = boolArray[14]
        set(value) {
            boolArray[14] = value
        }

    var isLexingRefValue: Boolean
        get() = boolArray[15]
        set(value) {
            boolArray[15] = value
        }

    var isLexingValue: Boolean
        get() = boolArray[16]
        set(value) {
            boolArray[16] = value
        }

    var isLexingRightParen: Boolean
        get() = boolArray[17]
        set(value) {
            boolArray[17] = value
        }

    var isLexingVariableAssignment: Boolean
        get() = boolArray[18]
        set(value) {
            boolArray[18] = value
        }

    var isLexingOperatorOrEqual: Boolean
        get() = boolArray[19]
        set(value) {
            boolArray[19] = value
        }

    var isLexingDotParenBracket: Boolean
        get() = boolArray[20]
        set(value) {
            boolArray[20] = value
        }

    var isLexingIdentifier : Boolean
        get() = boolArray[21]
        set(value){
            boolArray[21] = value
        }

    var isLexingNestedValue : Boolean
        get() = boolArray[22]
        set(value){
            boolArray[22] = value
        }

    var isLexingSingleParameter : Boolean
        get() = boolArray[23]
        set(value){
            boolArray[23] = value
        }

    var isLexingRightBracket : Boolean
        get() = boolArray[24]
        set(value){
            boolArray[24] = value
        }

    var isLexingParameter : Boolean
        get() = boolArray[25]
        set(value){
            boolArray[25] = value
        }

    var isLexingParameterOrRightParen : Boolean
        get() = boolArray[26]
        set(value){
            boolArray[26] = value
        }

    var isLexingString : Boolean
        get() = boolArray[27]
        set(value){
            boolArray[27] = value
        }

    private fun intToBooleanArray(value: Int): BooleanArray {
        val booleanArray = BooleanArray(32)
        for (i in 0 until 32) {
            booleanArray[i] = (value and (1 shl i)) != 0
        }
        return booleanArray
    }

    private fun booleanArrayToInt(booleanArray: BooleanArray): Int {
        var result = 0
        for (i in 0 until 32) {
            if (booleanArray[i]) {
                result = result or (1 shl i)
            }
        }
        return result
    }

    fun save(): Int {
        return booleanArrayToInt(boolArray)
    }

    fun restore(fromInt: Int) {
        this.boolArray = intToBooleanArray(fromInt)
    }

}