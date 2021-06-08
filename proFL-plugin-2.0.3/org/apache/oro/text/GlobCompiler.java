// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.PatternCompiler;

public final class GlobCompiler implements PatternCompiler
{
    public static final int DEFAULT_MASK = 0;
    public static final int CASE_INSENSITIVE_MASK = 1;
    public static final int STAR_CANNOT_MATCH_NULL_MASK = 2;
    public static final int QUESTION_MATCHES_ZERO_OR_ONE_MASK = 4;
    public static final int READ_ONLY_MASK = 8;
    private Perl5Compiler __perl5Compiler;
    
    private static boolean __isPerl5MetaCharacter(final char c) {
        return c == '*' || c == '?' || c == '+' || c == '[' || c == ']' || c == '(' || c == ')' || c == '|' || c == '^' || c == '$' || c == '.' || c == '{' || c == '}' || c == '\\';
    }
    
    private static boolean __isGlobMetaCharacter(final char c) {
        return c == '*' || c == '?' || c == '[' || c == ']';
    }
    
    public static String globToPerl5(final char[] array, final int n) {
        final StringBuffer sb = new StringBuffer(2 * array.length);
        int n2 = 0;
        final boolean b = (n & 0x4) != 0x0;
        final boolean b2 = (n & 0x2) != 0x0;
        for (int i = 0; i < array.length; ++i) {
            switch (array[i]) {
                case '*': {
                    if (n2 != 0) {
                        sb.append('*');
                        break;
                    }
                    if (b2) {
                        sb.append(".+");
                        break;
                    }
                    sb.append(".*");
                    break;
                }
                case '?': {
                    if (n2 != 0) {
                        sb.append('?');
                        break;
                    }
                    if (b) {
                        sb.append(".?");
                        break;
                    }
                    sb.append('.');
                    break;
                }
                case '[': {
                    n2 = 1;
                    sb.append(array[i]);
                    if (i + 1 >= array.length) {
                        break;
                    }
                    switch (array[i + 1]) {
                        case '!':
                        case '^': {
                            sb.append('^');
                            ++i;
                            continue;
                        }
                        case ']': {
                            sb.append(']');
                            ++i;
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                    break;
                }
                case ']': {
                    n2 = 0;
                    sb.append(array[i]);
                    break;
                }
                case '\\': {
                    sb.append('\\');
                    if (i == array.length - 1) {
                        sb.append('\\');
                        break;
                    }
                    if (__isGlobMetaCharacter(array[i + 1])) {
                        sb.append(array[++i]);
                        break;
                    }
                    sb.append('\\');
                    break;
                }
                default: {
                    if (n2 == 0 && __isPerl5MetaCharacter(array[i])) {
                        sb.append('\\');
                    }
                    sb.append(array[i]);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public GlobCompiler() {
        this.__perl5Compiler = new Perl5Compiler();
    }
    
    public Pattern compile(final char[] array, final int n) throws MalformedPatternException {
        int n2 = 0;
        if ((n & 0x1) != 0x0) {
            n2 |= 0x1;
        }
        if ((n & 0x8) != 0x0) {
            n2 |= 0x8000;
        }
        return this.__perl5Compiler.compile(globToPerl5(array, n), n2);
    }
    
    public Pattern compile(final char[] array) throws MalformedPatternException {
        return this.compile(array, 0);
    }
    
    public Pattern compile(final String s) throws MalformedPatternException {
        return this.compile(s.toCharArray(), 0);
    }
    
    public Pattern compile(final String s, final int n) throws MalformedPatternException {
        return this.compile(s.toCharArray(), n);
    }
}
