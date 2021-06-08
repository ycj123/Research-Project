// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

public abstract class Utilities
{
    private static final Set<String> INVALID_JAVA_IDENTIFIERS;
    private static String eol;
    
    public static String repeatString(final String pattern, final int repeats) {
        final StringBuffer buffer = new StringBuffer(pattern.length() * repeats);
        for (int i = 0; i < repeats; ++i) {
            buffer.append(pattern);
        }
        return new String(buffer);
    }
    
    public static String eol() {
        return Utilities.eol;
    }
    
    public static boolean isJavaIdentifier(final String name) {
        if (name.length() == 0 || Utilities.INVALID_JAVA_IDENTIFIERS.contains(name)) {
            return false;
        }
        final char[] chars = name.toCharArray();
        if (!Character.isJavaIdentifierStart(chars[0])) {
            return false;
        }
        for (int i = 1; i < chars.length; ++i) {
            if (!Character.isJavaIdentifierPart(chars[i])) {
                return false;
            }
        }
        return true;
    }
    
    static {
        INVALID_JAVA_IDENTIFIERS = new HashSet<String>(Arrays.asList("abstract assert boolean break byte case catch char class const continue default do double else enum extends final finally float for goto if implements import instanceof int interface long native new package private protected public short static strictfp super switch synchronized this throw throws transient try void volatile while true false null".split(" ")));
        Utilities.eol = System.getProperty("line.separator", "\n");
    }
}
