// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.perl;

import java.util.Vector;
import java.util.Collection;
import org.apache.oro.text.regex.Perl5Substitution;
import org.apache.oro.text.regex.Substitution;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Util;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.PatternCacheLRU;
import org.apache.oro.util.CacheLRU;
import java.util.ArrayList;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.util.Cache;
import org.apache.oro.text.PatternCache;
import org.apache.oro.text.regex.MatchResult;

public final class Perl5Util implements MatchResult
{
    private static final String __matchExpression = "m?(\\W)(.*)\\1([imsx]*)";
    private PatternCache __patternCache;
    private Cache __expressionCache;
    private Perl5Matcher __matcher;
    private Pattern __matchPattern;
    private MatchResult __lastMatch;
    private ArrayList __splitList;
    private Object __originalInput;
    private int __inputBeginOffset;
    private int __inputEndOffset;
    private static final String __nullString = "";
    public static final int SPLIT_ALL = 0;
    
    public Perl5Util(final PatternCache _patternCache) {
        this.__splitList = new ArrayList();
        this.__matcher = new Perl5Matcher();
        this.__patternCache = _patternCache;
        this.__expressionCache = new CacheLRU(_patternCache.capacity());
        this.__compilePatterns();
    }
    
    public Perl5Util() {
        this(new PatternCacheLRU());
    }
    
    private void __compilePatterns() {
        final Perl5Compiler perl5Compiler = new Perl5Compiler();
        try {
            this.__matchPattern = perl5Compiler.compile("m?(\\W)(.*)\\1([imsx]*)", 16);
        }
        catch (MalformedPatternException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    private Pattern __parseMatchExpression(final String str) throws MalformedPerl5PatternException {
        final Object element = this.__expressionCache.getElement(str);
        try {
            if (element != null) {
                return (Pattern)element;
            }
        }
        catch (ClassCastException ex) {}
        if (!this.__matcher.matches(str, this.__matchPattern)) {
            throw new MalformedPerl5PatternException("Invalid expression: " + str);
        }
        final MatchResult match = this.__matcher.getMatch();
        final String group = match.group(2);
        int n = 0;
        final String group2 = match.group(3);
        if (group2 != null) {
            int length = group2.length();
            while (length-- > 0) {
                switch (group2.charAt(length)) {
                    case 'i': {
                        n |= 0x1;
                        continue;
                    }
                    case 'm': {
                        n |= 0x8;
                        continue;
                    }
                    case 's': {
                        n |= 0x10;
                        continue;
                    }
                    case 'x': {
                        n |= 0x20;
                        continue;
                    }
                    default: {
                        throw new MalformedPerl5PatternException("Invalid options: " + group2);
                    }
                }
            }
        }
        final Pattern pattern = this.__patternCache.getPattern(group, n);
        this.__expressionCache.addElement(str, pattern);
        return pattern;
    }
    
    public synchronized boolean match(final String s, final char[] _originalInput) throws MalformedPerl5PatternException {
        this.__parseMatchExpression(s);
        final boolean contains = this.__matcher.contains(_originalInput, this.__parseMatchExpression(s));
        if (contains) {
            this.__lastMatch = this.__matcher.getMatch();
            this.__originalInput = _originalInput;
            this.__inputBeginOffset = 0;
            this.__inputEndOffset = _originalInput.length;
        }
        return contains;
    }
    
    public synchronized boolean match(final String s, final String s2) throws MalformedPerl5PatternException {
        return this.match(s, s2.toCharArray());
    }
    
    public synchronized boolean match(final String s, final PatternMatcherInput patternMatcherInput) throws MalformedPerl5PatternException {
        final boolean contains = this.__matcher.contains(patternMatcherInput, this.__parseMatchExpression(s));
        if (contains) {
            this.__lastMatch = this.__matcher.getMatch();
            this.__originalInput = patternMatcherInput.getInput();
            this.__inputBeginOffset = patternMatcherInput.getBeginOffset();
            this.__inputEndOffset = patternMatcherInput.getEndOffset();
        }
        return contains;
    }
    
    public synchronized MatchResult getMatch() {
        return this.__lastMatch;
    }
    
    public synchronized int substitute(final StringBuffer sb, final String str, final String s) throws MalformedPerl5PatternException {
        final Object element = this.__expressionCache.getElement(str);
        Label_0072: {
            if (element != null) {
                ParsedSubstitutionEntry parsedSubstitutionEntry;
                try {
                    parsedSubstitutionEntry = (ParsedSubstitutionEntry)element;
                }
                catch (ClassCastException ex) {
                    break Label_0072;
                }
                final int substitute = Util.substitute(sb, this.__matcher, parsedSubstitutionEntry._pattern, parsedSubstitutionEntry._substitution, s, parsedSubstitutionEntry._numSubstitutions);
                this.__lastMatch = this.__matcher.getMatch();
                return substitute;
            }
        }
        final char[] charArray = str.toCharArray();
        if (charArray.length < 4 || charArray[0] != 's' || Character.isLetterOrDigit(charArray[1]) || charArray[1] == '-') {
            throw new MalformedPerl5PatternException("Invalid expression: " + str);
        }
        final char ch = charArray[1];
        final int offset = 2;
        int n2;
        int n = n2 = -1;
        boolean b = false;
        for (int i = offset; i < charArray.length; ++i) {
            if (charArray[i] == '\\') {
                b = !b;
            }
            else {
                if (charArray[i] == ch && !b) {
                    n2 = i;
                    break;
                }
                if (b) {
                    b = !b;
                }
            }
        }
        if (n2 == -1 || n2 == charArray.length - 1) {
            throw new MalformedPerl5PatternException("Invalid expression: " + str);
        }
        int n3 = 0;
        int n4 = 1;
        final StringBuffer sb2 = new StringBuffer(charArray.length - n2);
        for (int j = n2 + 1; j < charArray.length; ++j) {
            if (charArray[j] == '\\') {
                n3 = ((n3 == 0) ? 1 : 0);
                if (n3 != 0 && j + 1 < charArray.length && charArray[j + 1] == ch && str.lastIndexOf(ch, charArray.length - 1) != j + 1) {
                    n4 = 0;
                    continue;
                }
            }
            else {
                if (charArray[j] == ch && n4 != 0) {
                    n = j;
                    break;
                }
                n3 = 0;
                n4 = 1;
            }
            sb2.append(charArray[j]);
        }
        if (n == -1) {
            throw new MalformedPerl5PatternException("Invalid expression: " + str);
        }
        int n5 = 0;
        int n6 = 1;
        int n7;
        if (ch != '\'') {
            n7 = 0;
        }
        else {
            n7 = -1;
        }
        for (int k = n + 1; k < charArray.length; ++k) {
            switch (charArray[k]) {
                case 'i': {
                    n5 |= 0x1;
                    break;
                }
                case 'm': {
                    n5 |= 0x8;
                    break;
                }
                case 's': {
                    n5 |= 0x10;
                    break;
                }
                case 'x': {
                    n5 |= 0x20;
                    break;
                }
                case 'g': {
                    n6 = -1;
                    break;
                }
                case 'o': {
                    n7 = 1;
                    break;
                }
                default: {
                    throw new MalformedPerl5PatternException("Invalid option: " + charArray[k]);
                }
            }
        }
        final Pattern pattern = this.__patternCache.getPattern(new String(charArray, offset, n2 - offset), n5);
        final Perl5Substitution perl5Substitution = new Perl5Substitution(sb2.toString(), n7);
        this.__expressionCache.addElement(str, new ParsedSubstitutionEntry(pattern, perl5Substitution, n6));
        final int substitute2 = Util.substitute(sb, this.__matcher, pattern, perl5Substitution, s, n6);
        this.__lastMatch = this.__matcher.getMatch();
        return substitute2;
    }
    
    public synchronized String substitute(final String s, final String s2) throws MalformedPerl5PatternException {
        final StringBuffer sb = new StringBuffer();
        this.substitute(sb, s, s2);
        return sb.toString();
    }
    
    public synchronized void split(final Collection collection, final String s, final String s2, int n) throws MalformedPerl5PatternException {
        MatchResult match = null;
        final Pattern _parseMatchExpression = this.__parseMatchExpression(s);
        final PatternMatcherInput patternMatcherInput = new PatternMatcherInput(s2);
        int endOffset = 0;
        while (--n != 0 && this.__matcher.contains(patternMatcherInput, _parseMatchExpression)) {
            match = this.__matcher.getMatch();
            this.__splitList.add(s2.substring(endOffset, match.beginOffset(0)));
            final int groups;
            if ((groups = match.groups()) > 1) {
                for (int i = 1; i < groups; ++i) {
                    final String group = match.group(i);
                    if (group != null && group.length() > 0) {
                        this.__splitList.add(group);
                    }
                }
            }
            endOffset = match.endOffset(0);
        }
        this.__splitList.add(s2.substring(endOffset, s2.length()));
        for (int n2 = this.__splitList.size() - 1; n2 >= 0 && ((String)this.__splitList.get(n2)).length() == 0; --n2) {
            this.__splitList.remove(n2);
        }
        collection.addAll(this.__splitList);
        this.__splitList.clear();
        this.__lastMatch = match;
    }
    
    public synchronized void split(final Collection collection, final String s, final String s2) throws MalformedPerl5PatternException {
        this.split(collection, s, s2, 0);
    }
    
    public synchronized void split(final Collection collection, final String s) throws MalformedPerl5PatternException {
        this.split(collection, "/\\s+/", s);
    }
    
    public synchronized Vector split(final String s, final String s2, final int n) throws MalformedPerl5PatternException {
        final Vector vector = new Vector(20);
        this.split(vector, s, s2, n);
        return vector;
    }
    
    public synchronized Vector split(final String s, final String s2) throws MalformedPerl5PatternException {
        return this.split(s, s2, 0);
    }
    
    public synchronized Vector split(final String s) throws MalformedPerl5PatternException {
        return this.split("/\\s+/", s);
    }
    
    public synchronized int length() {
        return this.__lastMatch.length();
    }
    
    public synchronized int groups() {
        return this.__lastMatch.groups();
    }
    
    public synchronized String group(final int n) {
        return this.__lastMatch.group(n);
    }
    
    public synchronized int begin(final int n) {
        return this.__lastMatch.begin(n);
    }
    
    public synchronized int end(final int n) {
        return this.__lastMatch.end(n);
    }
    
    public synchronized int beginOffset(final int n) {
        return this.__lastMatch.beginOffset(n);
    }
    
    public synchronized int endOffset(final int n) {
        return this.__lastMatch.endOffset(n);
    }
    
    public synchronized String toString() {
        if (this.__lastMatch == null) {
            return null;
        }
        return this.__lastMatch.toString();
    }
    
    public synchronized String preMatch() {
        if (this.__originalInput == null) {
            return "";
        }
        int n = this.__lastMatch.beginOffset(0);
        if (n <= 0) {
            return "";
        }
        if (this.__originalInput instanceof char[]) {
            final char[] value = (char[])this.__originalInput;
            if (n > value.length) {
                n = value.length;
            }
            return new String(value, this.__inputBeginOffset, n);
        }
        if (this.__originalInput instanceof String) {
            final String s = (String)this.__originalInput;
            if (n > s.length()) {
                n = s.length();
            }
            return s.substring(this.__inputBeginOffset, n);
        }
        return "";
    }
    
    public synchronized String postMatch() {
        if (this.__originalInput == null) {
            return "";
        }
        final int endOffset = this.__lastMatch.endOffset(0);
        if (endOffset < 0) {
            return "";
        }
        if (this.__originalInput instanceof char[]) {
            final char[] value = (char[])this.__originalInput;
            if (endOffset >= value.length) {
                return "";
            }
            return new String(value, endOffset, this.__inputEndOffset - endOffset);
        }
        else {
            if (!(this.__originalInput instanceof String)) {
                return "";
            }
            final String s = (String)this.__originalInput;
            if (endOffset >= s.length()) {
                return "";
            }
            return s.substring(endOffset, this.__inputEndOffset);
        }
    }
    
    public synchronized char[] preMatchCharArray() {
        char[] dst = null;
        if (this.__originalInput == null) {
            return null;
        }
        int srcEnd = this.__lastMatch.beginOffset(0);
        if (srcEnd <= 0) {
            return null;
        }
        if (this.__originalInput instanceof char[]) {
            final char[] array = (char[])this.__originalInput;
            if (srcEnd >= array.length) {
                srcEnd = array.length;
            }
            dst = new char[srcEnd - this.__inputBeginOffset];
            System.arraycopy(array, this.__inputBeginOffset, dst, 0, dst.length);
        }
        else if (this.__originalInput instanceof String) {
            final String s = (String)this.__originalInput;
            if (srcEnd >= s.length()) {
                srcEnd = s.length();
            }
            dst = new char[srcEnd - this.__inputBeginOffset];
            s.getChars(this.__inputBeginOffset, srcEnd, dst, 0);
        }
        return dst;
    }
    
    public synchronized char[] postMatchCharArray() {
        char[] dst = null;
        if (this.__originalInput == null) {
            return null;
        }
        final int endOffset = this.__lastMatch.endOffset(0);
        if (endOffset < 0) {
            return null;
        }
        if (this.__originalInput instanceof char[]) {
            final char[] array = (char[])this.__originalInput;
            if (endOffset >= array.length) {
                return null;
            }
            final int n = this.__inputEndOffset - endOffset;
            dst = new char[n];
            System.arraycopy(array, endOffset, dst, 0, n);
        }
        else if (this.__originalInput instanceof String) {
            final String s = (String)this.__originalInput;
            if (endOffset >= this.__inputEndOffset) {
                return null;
            }
            dst = new char[this.__inputEndOffset - endOffset];
            s.getChars(endOffset, this.__inputEndOffset, dst, 0);
        }
        return dst;
    }
}
