// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.regex;

import java.util.Vector;
import java.util.Collection;

public final class Util
{
    public static final int SUBSTITUTE_ALL = -1;
    public static final int SPLIT_ALL = 0;
    
    private Util() {
    }
    
    public static void split(final Collection collection, final PatternMatcher patternMatcher, final Pattern pattern, final String s, int n) {
        final PatternMatcherInput patternMatcherInput = new PatternMatcherInput(s);
        int endOffset = 0;
        while (--n != 0 && patternMatcher.contains(patternMatcherInput, pattern)) {
            final MatchResult match = patternMatcher.getMatch();
            collection.add(s.substring(endOffset, match.beginOffset(0)));
            endOffset = match.endOffset(0);
        }
        collection.add(s.substring(endOffset, s.length()));
    }
    
    public static void split(final Collection collection, final PatternMatcher patternMatcher, final Pattern pattern, final String s) {
        split(collection, patternMatcher, pattern, s, 0);
    }
    
    public static Vector split(final PatternMatcher patternMatcher, final Pattern pattern, final String s, final int n) {
        final Vector vector = new Vector(20);
        split(vector, patternMatcher, pattern, s, n);
        return vector;
    }
    
    public static Vector split(final PatternMatcher patternMatcher, final Pattern pattern, final String s) {
        return split(patternMatcher, pattern, s, 0);
    }
    
    public static String substitute(final PatternMatcher patternMatcher, final Pattern pattern, final Substitution substitution, final String s, final int n) {
        final StringBuffer sb = new StringBuffer(s.length());
        if (substitute(sb, patternMatcher, pattern, substitution, new PatternMatcherInput(s), n) != 0) {
            return sb.toString();
        }
        return s;
    }
    
    public static String substitute(final PatternMatcher patternMatcher, final Pattern pattern, final Substitution substitution, final String s) {
        return substitute(patternMatcher, pattern, substitution, s, 1);
    }
    
    public static int substitute(final StringBuffer sb, final PatternMatcher patternMatcher, final Pattern pattern, final Substitution substitution, final String s, final int n) {
        return substitute(sb, patternMatcher, pattern, substitution, new PatternMatcherInput(s), n);
    }
    
    public static int substitute(final StringBuffer sb, final PatternMatcher patternMatcher, final Pattern pattern, final Substitution substitution, final PatternMatcherInput patternMatcherInput, int n) {
        int n2 = 0;
        int n3 = patternMatcherInput.getBeginOffset();
        final char[] buffer = patternMatcherInput.getBuffer();
        while (n != 0 && patternMatcher.contains(patternMatcherInput, pattern)) {
            --n;
            ++n2;
            sb.append(buffer, n3, patternMatcherInput.getMatchBeginOffset() - n3);
            substitution.appendSubstitution(sb, patternMatcher.getMatch(), n2, patternMatcherInput, patternMatcher, pattern);
            n3 = patternMatcherInput.getMatchEndOffset();
        }
        sb.append(buffer, n3, patternMatcherInput.length() - n3);
        return n2;
    }
}
