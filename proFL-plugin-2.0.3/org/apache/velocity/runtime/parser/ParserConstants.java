// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser;

public interface ParserConstants
{
    public static final int EOF = 0;
    public static final int LBRACKET = 1;
    public static final int RBRACKET = 2;
    public static final int COMMA = 3;
    public static final int DOUBLEDOT = 4;
    public static final int COLON = 5;
    public static final int LEFT_CURLEY = 6;
    public static final int RIGHT_CURLEY = 7;
    public static final int LPAREN = 8;
    public static final int RPAREN = 9;
    public static final int REFMOD2_RPAREN = 10;
    public static final int ESCAPE_DIRECTIVE = 11;
    public static final int SET_DIRECTIVE = 12;
    public static final int DOLLAR = 13;
    public static final int DOLLARBANG = 14;
    public static final int HASH = 17;
    public static final int SINGLE_LINE_COMMENT_START = 18;
    public static final int DOUBLE_ESCAPE = 19;
    public static final int ESCAPE = 20;
    public static final int TEXT = 21;
    public static final int SINGLE_LINE_COMMENT = 22;
    public static final int FORMAL_COMMENT = 23;
    public static final int MULTI_LINE_COMMENT = 24;
    public static final int WHITESPACE = 26;
    public static final int STRING_LITERAL = 27;
    public static final int TRUE = 28;
    public static final int FALSE = 29;
    public static final int NEWLINE = 30;
    public static final int MINUS = 31;
    public static final int PLUS = 32;
    public static final int MULTIPLY = 33;
    public static final int DIVIDE = 34;
    public static final int MODULUS = 35;
    public static final int LOGICAL_AND = 36;
    public static final int LOGICAL_OR = 37;
    public static final int LOGICAL_LT = 38;
    public static final int LOGICAL_LE = 39;
    public static final int LOGICAL_GT = 40;
    public static final int LOGICAL_GE = 41;
    public static final int LOGICAL_EQUALS = 42;
    public static final int LOGICAL_NOT_EQUALS = 43;
    public static final int LOGICAL_NOT = 44;
    public static final int EQUALS = 45;
    public static final int END = 46;
    public static final int IF_DIRECTIVE = 47;
    public static final int ELSEIF_DIRECTIVE = 48;
    public static final int ELSE_DIRECTIVE = 49;
    public static final int STOP_DIRECTIVE = 50;
    public static final int DIGIT = 51;
    public static final int INTEGER_LITERAL = 52;
    public static final int FLOATING_POINT_LITERAL = 53;
    public static final int EXPONENT = 54;
    public static final int LETTER = 55;
    public static final int DIRECTIVE_CHAR = 56;
    public static final int WORD = 57;
    public static final int BRACKETED_WORD = 58;
    public static final int ALPHA_CHAR = 59;
    public static final int ALPHANUM_CHAR = 60;
    public static final int IDENTIFIER_CHAR = 61;
    public static final int IDENTIFIER = 62;
    public static final int DOT = 63;
    public static final int LCURLY = 64;
    public static final int RCURLY = 65;
    public static final int REFERENCE_TERMINATOR = 66;
    public static final int DIRECTIVE_TERMINATOR = 67;
    public static final int DIRECTIVE = 0;
    public static final int REFMOD2 = 1;
    public static final int REFMODIFIER = 2;
    public static final int DEFAULT = 3;
    public static final int PRE_DIRECTIVE = 4;
    public static final int REFERENCE = 5;
    public static final int IN_MULTI_LINE_COMMENT = 6;
    public static final int IN_FORMAL_COMMENT = 7;
    public static final int IN_SINGLE_LINE_COMMENT = 8;
    public static final String[] tokenImage = { "<EOF>", "\"[\"", "\"]\"", "\",\"", "\"..\"", "\":\"", "\"{\"", "\"}\"", "\"(\"", "<RPAREN>", "\")\"", "<ESCAPE_DIRECTIVE>", "<SET_DIRECTIVE>", "<DOLLAR>", "<DOLLARBANG>", "<token of kind 15>", "\"#*\"", "\"#\"", "\"##\"", "\"\\\\\\\\\"", "\"\\\\\"", "<TEXT>", "<SINGLE_LINE_COMMENT>", "\"*#\"", "\"*#\"", "<token of kind 25>", "<WHITESPACE>", "<STRING_LITERAL>", "\"true\"", "\"false\"", "<NEWLINE>", "\"-\"", "\"+\"", "\"*\"", "\"/\"", "\"%\"", "<LOGICAL_AND>", "<LOGICAL_OR>", "<LOGICAL_LT>", "<LOGICAL_LE>", "<LOGICAL_GT>", "<LOGICAL_GE>", "<LOGICAL_EQUALS>", "<LOGICAL_NOT_EQUALS>", "<LOGICAL_NOT>", "\"=\"", "<END>", "<IF_DIRECTIVE>", "<ELSEIF_DIRECTIVE>", "<ELSE_DIRECTIVE>", "<STOP_DIRECTIVE>", "<DIGIT>", "<INTEGER_LITERAL>", "<FLOATING_POINT_LITERAL>", "<EXPONENT>", "<LETTER>", "<DIRECTIVE_CHAR>", "<WORD>", "<BRACKETED_WORD>", "<ALPHA_CHAR>", "<ALPHANUM_CHAR>", "<IDENTIFIER_CHAR>", "<IDENTIFIER>", "<DOT>", "\"{\"", "\"}\"", "<REFERENCE_TERMINATOR>", "<DIRECTIVE_TERMINATOR>" };
}
