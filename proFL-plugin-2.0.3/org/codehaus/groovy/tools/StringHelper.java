// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import java.util.List;
import java.util.LinkedList;

public class StringHelper
{
    private static final char SPACE = ' ';
    private static final char SINGLE_QUOTE = '\'';
    private static final char DOUBLE_QUOTE = '\"';
    
    public static String[] tokenizeUnquoted(final String s) {
        final List tokens = new LinkedList();
        int last;
        for (int first = 0; first < s.length(); first = last) {
            first = skipWhitespace(s, first);
            last = scanToken(s, first);
            if (first < last) {
                tokens.add(s.substring(first, last));
            }
        }
        return tokens.toArray(new String[0]);
    }
    
    private static int scanToken(final String s, final int pos0) {
        int pos = pos0;
        while (pos < s.length()) {
            final char c = s.charAt(pos);
            if (' ' == c) {
                break;
            }
            ++pos;
            if ('\'' == c) {
                pos = scanQuoted(s, pos, '\'');
            }
            else {
                if ('\"' != c) {
                    continue;
                }
                pos = scanQuoted(s, pos, '\"');
            }
        }
        return pos;
    }
    
    private static int scanQuoted(final String s, final int pos0, final char quote) {
        int pos = pos0;
        while (pos < s.length()) {
            final char c = s.charAt(pos++);
            if (quote == c) {
                break;
            }
        }
        return pos;
    }
    
    private static int skipWhitespace(final String s, final int pos0) {
        int pos;
        for (pos = pos0; pos < s.length(); ++pos) {
            final char c = s.charAt(pos);
            if (' ' != c) {
                break;
            }
        }
        return pos;
    }
}
