// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.syntax;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Numbers
{
    private static final BigInteger MAX_LONG;
    private static final BigInteger MIN_LONG;
    private static final BigInteger MAX_INTEGER;
    private static final BigInteger MIN_INTEGER;
    private static final BigDecimal MAX_DOUBLE;
    private static final BigDecimal MIN_DOUBLE;
    private static final BigDecimal MAX_FLOAT;
    private static final BigDecimal MIN_FLOAT;
    
    public static boolean isDigit(final char c) {
        return c >= '0' && c <= '9';
    }
    
    public static boolean isOctalDigit(final char c) {
        return c >= '0' && c <= '7';
    }
    
    public static boolean isHexDigit(final char c) {
        return isDigit(c) || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }
    
    public static boolean isNumericTypeSpecifier(final char c, final boolean isDecimal) {
        if (isDecimal) {
            switch (c) {
                case 'D':
                case 'F':
                case 'G':
                case 'd':
                case 'f':
                case 'g': {
                    return true;
                }
            }
        }
        else {
            switch (c) {
                case 'G':
                case 'I':
                case 'L':
                case 'g':
                case 'i':
                case 'l': {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Number parseInteger(String text) {
        char c = ' ';
        int length = text.length();
        boolean negative = false;
        if ((c = text.charAt(0)) == '-' || c == '+') {
            negative = (c == '-');
            text = text.substring(1, length);
            --length;
        }
        int radix = 10;
        if (text.charAt(0) == '0' && length > 1) {
            if ((c = text.charAt(1)) == 'X' || c == 'x') {
                radix = 16;
                text = text.substring(2, length);
                length -= 2;
            }
            else {
                radix = 8;
            }
        }
        char type = 'x';
        if (isNumericTypeSpecifier(text.charAt(length - 1), false)) {
            type = Character.toLowerCase(text.charAt(length - 1));
            text = text.substring(0, length - 1);
            --length;
        }
        if (negative) {
            text = "-" + text;
        }
        switch (type) {
            case 'i': {
                return Integer.parseInt(text, radix);
            }
            case 'l': {
                return new Long(Long.parseLong(text, radix));
            }
            case 'g': {
                return new BigInteger(text, radix);
            }
            default: {
                final BigInteger value = new BigInteger(text, radix);
                if (value.compareTo(Numbers.MAX_INTEGER) <= 0 && value.compareTo(Numbers.MIN_INTEGER) >= 0) {
                    return value.intValue();
                }
                if (value.compareTo(Numbers.MAX_LONG) <= 0 && value.compareTo(Numbers.MIN_LONG) >= 0) {
                    return new Long(value.longValue());
                }
                return value;
            }
        }
    }
    
    public static Number parseDecimal(String text) {
        int length = text.length();
        char type = 'x';
        if (isNumericTypeSpecifier(text.charAt(length - 1), true)) {
            type = Character.toLowerCase(text.charAt(length - 1));
            text = text.substring(0, length - 1);
            --length;
        }
        final BigDecimal value = new BigDecimal(text);
        switch (type) {
            case 'f': {
                if (value.compareTo(Numbers.MAX_FLOAT) <= 0 && value.compareTo(Numbers.MIN_FLOAT) >= 0) {
                    return new Float(text);
                }
                throw new NumberFormatException("out of range");
            }
            case 'd': {
                if (value.compareTo(Numbers.MAX_DOUBLE) <= 0 && value.compareTo(Numbers.MIN_DOUBLE) >= 0) {
                    return new Double(text);
                }
                throw new NumberFormatException("out of range");
            }
            default: {
                return value;
            }
        }
    }
    
    static {
        MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
        MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        MAX_INTEGER = BigInteger.valueOf(2147483647L);
        MIN_INTEGER = BigInteger.valueOf(-2147483648L);
        MAX_DOUBLE = new BigDecimal(String.valueOf(Double.MAX_VALUE));
        MIN_DOUBLE = Numbers.MAX_DOUBLE.negate();
        MAX_FLOAT = new BigDecimal(String.valueOf(Float.MAX_VALUE));
        MIN_FLOAT = Numbers.MAX_FLOAT.negate();
    }
}
