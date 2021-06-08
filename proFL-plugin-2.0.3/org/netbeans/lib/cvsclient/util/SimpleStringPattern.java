// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleStringPattern implements StringPattern
{
    private static final char MATCH_EACH = '*';
    private static final char MATCH_ONE = '?';
    private final List subPatterns;
    
    public SimpleStringPattern(final String s) {
        this.subPatterns = new LinkedList();
        this.splitInSubPattern(s);
    }
    
    public boolean doesMatch(final String s) {
        int doesMatch = 0;
        SubPattern subPattern = null;
        final Iterator<SubPattern> iterator = this.subPatterns.iterator();
        while (iterator.hasNext()) {
            subPattern = iterator.next();
            doesMatch = subPattern.doesMatch(s, doesMatch);
            if (doesMatch < 0) {
                return false;
            }
        }
        return doesMatch == s.length() || (subPattern != null && subPattern.checkEnding(s, doesMatch));
    }
    
    private void splitInSubPattern(final String s) {
        char c = ' ';
        int beginIndex = 0;
        int i = 0;
        while (i >= 0) {
            beginIndex = i;
            i = s.indexOf(42, beginIndex);
            if (i >= 0) {
                this.addSubPattern(s.substring(beginIndex, i), c);
                c = '*';
                ++i;
            }
            else {
                i = s.indexOf(63, beginIndex);
                if (i < 0) {
                    continue;
                }
                this.addSubPattern(s.substring(beginIndex, i), c);
                c = '?';
                ++i;
            }
        }
        this.addSubPattern(s.substring(beginIndex), c);
    }
    
    private void addSubPattern(final String s, final char c) {
        SubPattern subPattern = null;
        switch (c) {
            case '*': {
                subPattern = new MatchEachCharPattern(s);
                break;
            }
            case '?': {
                subPattern = new MatchOneCharPattern(s);
                break;
            }
            default: {
                subPattern = new MatchExactSubPattern(s);
                break;
            }
        }
        this.subPatterns.add(subPattern);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final Iterator<SubPattern> iterator = this.subPatterns.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toString());
        }
        return sb.toString();
    }
    
    public boolean equals(final Object o) {
        return o instanceof SimpleStringPattern && this.subPatterns.equals(((SimpleStringPattern)o).subPatterns);
    }
    
    public int hashCode() {
        return -this.subPatterns.hashCode();
    }
    
    public static void main(final String[] array) {
        final SimpleStringPattern simpleStringPattern = new SimpleStringPattern("a*b");
        test(simpleStringPattern, "ab", true);
        test(simpleStringPattern, "aab", true);
        test(simpleStringPattern, "ba", false);
        test(simpleStringPattern, "abc", false);
        final SimpleStringPattern simpleStringPattern2 = new SimpleStringPattern("*.txt");
        test(simpleStringPattern2, "datei.txt", true);
        test(simpleStringPattern2, ".txt", true);
        test(simpleStringPattern2, "datei.tx", false);
        test(simpleStringPattern2, "datei.txt.txt", true);
        final SimpleStringPattern simpleStringPattern3 = new SimpleStringPattern("datei*1*");
        test(simpleStringPattern3, "datei0.txt", false);
        test(simpleStringPattern3, "datei1.txt", true);
        test(simpleStringPattern3, "datei.tx", false);
        test(simpleStringPattern3, "datei1.txt.txt", true);
        final SimpleStringPattern simpleStringPattern4 = new SimpleStringPattern("Makefile");
        test(simpleStringPattern4, "Makefile", true);
        test(simpleStringPattern4, "Makefile.mak", false);
        test(simpleStringPattern4, "Makefile1", false);
        test(simpleStringPattern4, ".Makefile", false);
        test(simpleStringPattern4, ".Makefile.", false);
        final SimpleStringPattern simpleStringPattern5 = new SimpleStringPattern("*~");
        test(simpleStringPattern5, "datei~", true);
        test(simpleStringPattern5, "datei~1", false);
        test(simpleStringPattern5, "datei~1~", true);
        final SimpleStringPattern obj = new SimpleStringPattern("*.class");
        final SimpleStringPattern obj2 = new SimpleStringPattern("*.class");
        System.err.println(obj + ".equals(" + obj2 + ") = " + obj.equals(obj2));
        final SimpleStringPattern obj3 = new SimpleStringPattern("?.class");
        final SimpleStringPattern obj4 = new SimpleStringPattern("*.class");
        System.err.println(obj3 + ".equals(" + obj4 + ") = " + obj3.equals(obj4));
        final SimpleStringPattern obj5 = new SimpleStringPattern("*.clazz");
        final SimpleStringPattern obj6 = new SimpleStringPattern("*.class");
        System.err.println(obj5 + ".equals(" + obj6 + ") = " + obj5.equals(obj6));
    }
    
    private static void test(final StringPattern stringPattern, final String str, final boolean b) {
        System.err.print('\"' + stringPattern.toString() + '\"' + ": " + str + " " + b);
        if (stringPattern.doesMatch(str) == b) {
            System.err.println(" proved");
        }
        else {
            System.err.println(" **denied**");
        }
    }
    
    private abstract static class SubPattern
    {
        protected final String match;
        
        protected SubPattern(final String match) {
            this.match = match;
        }
        
        public abstract int doesMatch(final String p0, final int p1);
        
        public boolean checkEnding(final String s, final int n) {
            return false;
        }
        
        public boolean equals(final Object o) {
            return this.getClass().isInstance(o) && this.match.equals(((SubPattern)o).match);
        }
        
        public int hashCode() {
            return -this.match.hashCode();
        }
    }
    
    private static class MatchExactSubPattern extends SubPattern
    {
        public MatchExactSubPattern(final String s) {
            super(s);
        }
        
        public int doesMatch(final String s, final int toffset) {
            if (!s.startsWith(this.match, toffset)) {
                return -1;
            }
            return toffset + this.match.length();
        }
        
        public String toString() {
            return this.match;
        }
    }
    
    private static class MatchEachCharPattern extends SubPattern
    {
        public MatchEachCharPattern(final String s) {
            super(s);
        }
        
        public int doesMatch(final String s, final int fromIndex) {
            final int index = s.indexOf(this.match, fromIndex);
            if (index < 0) {
                return -1;
            }
            return index + this.match.length();
        }
        
        public boolean checkEnding(final String s, final int n) {
            return s.endsWith(this.match);
        }
        
        public String toString() {
            return '*' + this.match;
        }
    }
    
    private static class MatchOneCharPattern extends MatchExactSubPattern
    {
        public MatchOneCharPattern(final String s) {
            super(s);
        }
        
        public int doesMatch(final String s, int n) {
            ++n;
            if (s.length() < n) {
                return -1;
            }
            return super.doesMatch(s, n);
        }
        
        public String toString() {
            return '?' + this.match;
        }
    }
}
