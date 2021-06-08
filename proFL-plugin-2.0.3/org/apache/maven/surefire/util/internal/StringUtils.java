// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util.internal;

import java.util.StringTokenizer;

public class StringUtils
{
    private static final byte[] HEX_CHARS;
    
    public static String[] split(final String text, final String separator) {
        final int max = -1;
        StringTokenizer tok;
        if (separator == null) {
            tok = new StringTokenizer(text);
        }
        else {
            tok = new StringTokenizer(text, separator);
        }
        int listSize = tok.countTokens();
        if (max > 0 && listSize > max) {
            listSize = max;
        }
        final String[] list = new String[listSize];
        int i = 0;
        int lastTokenEnd = 0;
        while (tok.hasMoreTokens()) {
            if (max > 0 && i == listSize - 1) {
                final String endToken = tok.nextToken();
                final int lastTokenBegin = text.indexOf(endToken, lastTokenEnd);
                list[i] = text.substring(lastTokenBegin);
                break;
            }
            list[i] = tok.nextToken();
            final int lastTokenBegin = text.indexOf(list[i], lastTokenEnd);
            lastTokenEnd = lastTokenBegin + list[i].length();
            ++i;
        }
        return list;
    }
    
    public static boolean isBlank(final String str) {
        return str == null || str.trim().length() == 0;
    }
    
    public static void escapeToPrintable(final StringBuilder target, final CharSequence str) {
        if (target == null) {
            throw new IllegalArgumentException("The target buffer must not be null");
        }
        if (str == null) {
            return;
        }
        for (int i = 0; i < str.length(); ++i) {
            final char c = str.charAt(i);
            if (c < ' ' || c > '~' || c == '\\' || c == ',') {
                target.append('\\');
                target.append((char)StringUtils.HEX_CHARS[('\uf000' & c) >> 12]);
                target.append((char)StringUtils.HEX_CHARS[('\u0f00' & c) >> 8]);
                target.append((char)StringUtils.HEX_CHARS[('\u00f0' & c) >> 4]);
                target.append((char)StringUtils.HEX_CHARS['\u000f' & c]);
            }
            else {
                target.append(c);
            }
        }
    }
    
    public static void unescapeString(final StringBuilder target, final CharSequence str) {
        if (target == null) {
            throw new IllegalArgumentException("The target buffer must not be null");
        }
        if (str == null) {
            return;
        }
        for (int i = 0; i < str.length(); ++i) {
            final char ch = str.charAt(i);
            if (ch == '\\') {
                target.append((char)(digit(str.charAt(++i)) << 12 | digit(str.charAt(++i)) << 8 | digit(str.charAt(++i)) << 4 | digit(str.charAt(++i))));
            }
            else {
                target.append(ch);
            }
        }
    }
    
    private static int digit(final char ch) {
        if (ch >= 'a') {
            return '\n' + ch - 97;
        }
        if (ch >= 'A') {
            return '\n' + ch - 65;
        }
        return ch - '0';
    }
    
    public static int escapeBytesToPrintable(final byte[] out, final int outoff, final byte[] input, final int off, final int len) {
        if (out == null) {
            throw new IllegalArgumentException("The output array must not be null");
        }
        if (input == null || input.length == 0) {
            return 0;
        }
        int outputPos = outoff;
        for (int end = off + len, i = off; i < end; ++i) {
            final byte b = input[i];
            if (b < 32 || b > 126 || b == 92 || b == 44) {
                final int upper = (0xF0 & b) >> 4;
                final int lower = 0xF & b;
                out[outputPos++] = 92;
                out[outputPos++] = StringUtils.HEX_CHARS[upper];
                out[outputPos++] = StringUtils.HEX_CHARS[lower];
            }
            else {
                out[outputPos++] = b;
            }
        }
        return outputPos - outoff;
    }
    
    public static int unescapeBytes(final byte[] out, final String str) {
        int outPos = 0;
        if (out == null) {
            throw new IllegalArgumentException("The output array must not be null");
        }
        if (str == null) {
            return 0;
        }
        for (int i = 0; i < str.length(); ++i) {
            final char ch = str.charAt(i);
            if (ch == '\\') {
                final int upper = digit(str.charAt(++i));
                final int lower = digit(str.charAt(++i));
                out[outPos++] = (byte)(upper << 4 | lower);
            }
            else {
                out[outPos++] = (byte)ch;
            }
        }
        return outPos;
    }
    
    static {
        HEX_CHARS = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    }
}
