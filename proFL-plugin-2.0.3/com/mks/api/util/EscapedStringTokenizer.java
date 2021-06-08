// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

import java.util.NoSuchElementException;

public class EscapedStringTokenizer
{
    private static char BACKSLASH;
    private boolean DEBUG;
    private int currPos;
    private int maxPos;
    private String str;
    private String delimiters;
    private boolean lastPass;
    private boolean retDelims;
    private boolean allowNullToken;
    private boolean start;
    public static char[] QUOTES;
    private boolean prevBS;
    private boolean currBS;
    private boolean evenBS;
    private char[] bracketingChars;
    private char currBktChar;
    private int lastUpdateBSpos;
    private boolean removeBrackets;
    
    public static String escape(final String str, final String delimiters) {
        if (str == null || str.length() == 0 || delimiters == null || delimiters.length() == 0) {
            return str;
        }
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); ++i) {
            if (isDelimiterChar(delimiters, str, i)) {
                buffer.append(EscapedStringTokenizer.BACKSLASH);
            }
            buffer.append(str.charAt(i));
        }
        return buffer.toString();
    }
    
    public EscapedStringTokenizer(final String str, final String delim, final boolean lastPass) {
        this.DEBUG = false;
        this.retDelims = false;
        this.start = true;
        this.bracketingChars = null;
        this.removeBrackets = false;
        this.str = str;
        this.delimiters = ((delim != null) ? delim : " \t\n\r\f");
        this.lastPass = lastPass;
        this.maxPos = str.length();
        this.rewind();
    }
    
    public EscapedStringTokenizer(final String str, final String delim, final boolean lastPass, final boolean allowNullToken) {
        this(str, delim, lastPass);
        this.allowNullToken = allowNullToken;
    }
    
    public EscapedStringTokenizer(final String str, final String delim, final boolean lastPass, final boolean allowNullToken, final boolean retDelims) {
        this(str, delim, lastPass);
        if (retDelims) {
            if (allowNullToken) {
                throw new UnsupportedOperationException("Sorry, this implementation supports either nullTokens  or retDelim but not both.");
            }
            this.allowNullToken = false;
        }
        this.retDelims = retDelims;
    }
    
    public void defineBracketingChars(final char[] bktChars, final boolean removeBkts) {
        this.bracketingChars = bktChars;
        this.removeBrackets = removeBkts;
    }
    
    public void rewind() {
        this.currPos = 0;
        this.resetBS();
    }
    
    public boolean hasMoreTokens() {
        if ((this.allowNullToken || this.retDelims) && this.currPos < this.maxPos && this.isDelimiter(this.str, this.currPos)) {
            return true;
        }
        this.currPos = this.skipDelimiters(this.str, this.currPos);
        return this.currPos < this.maxPos;
    }
    
    public String nextToken() {
        if (this.allowNullToken) {
            if (this.start && this.currPos == 0 && this.isDelimiter(this.str, this.currPos)) {
                this.start = false;
                return null;
            }
            if (this.currPos == this.maxPos - 1 && this.isDelimiter(this.str, this.currPos)) {
                ++this.currPos;
                return null;
            }
        }
        this.resetBS();
        this.updateBS(this.str, this.currPos);
        this.currPos = this.skipDelimiters(this.str, this.currPos);
        if (this.allowNullToken && this.currPos < this.maxPos && this.isDelimiter(this.str, this.currPos)) {
            return null;
        }
        if (this.currPos >= this.maxPos) {
            throw new NoSuchElementException();
        }
        final int start = this.currPos;
        this.updateBS(this.str, this.currPos);
        while (this.currPos < this.maxPos && !this.isDelimiter(this.str, this.currPos)) {
            ++this.currPos;
            this.updateBS(this.str, this.currPos);
        }
        if (this.retDelims && start == this.currPos && this.isDelimiter(this.str, this.currPos)) {
            ++this.currPos;
            this.updateBS(this.str, this.currPos);
        }
        final String token = this.str.substring(start, this.currPos);
        final StringBuffer sb = new StringBuffer(token.length());
        if (this.retDelims) {
            sb.append(token);
        }
        else {
            if (this.DEBUG) {
                System.out.println(" Unescape delimiter chars in token = " + token);
            }
            this.resetBS();
            for (int i = 0; i < token.length(); ++i) {
                this.updateBS(token, i);
                if (this.DEBUG) {
                    System.out.print("    token[" + i + "]=" + token.charAt(i) + ", isDelim=" + this.isDelimiterChar(token, i) + ", isCurrentCharEscaped=" + this.isCurrentCharEscaped());
                }
                if (this.isDelimiterChar(token, i) && this.isCurrentCharEscaped()) {
                    sb.setCharAt(sb.length() - 1, token.charAt(i));
                    if (this.DEBUG) {
                        System.out.println(", sb.setCharAt(" + (sb.length() - 1) + "," + token.charAt(i) + "), sb=" + sb.toString());
                    }
                }
                else {
                    sb.append(token.charAt(i));
                    if (this.DEBUG) {
                        System.out.println(", sb.append, sb=" + sb.toString());
                    }
                }
            }
        }
        if (this.lastPass) {
            boolean prevBS = false;
            boolean currBS = false;
            char currBktChar = '\0';
            final boolean removeBkts = this.bracketingChars != null && this.removeBrackets;
            int j = 0;
        Label_0650:
            while (j < sb.length()) {
                if (removeBkts && (!prevBS || this.evenBS)) {
                    if (currBktChar != '\0') {
                        if (sb.charAt(j) == currBktChar) {
                            currBktChar = '\0';
                            sb.deleteCharAt(j);
                            continue;
                        }
                    }
                    else {
                        final char c = sb.charAt(j);
                        for (int k = 0; k < this.bracketingChars.length; ++k) {
                            if (c == this.bracketingChars[k]) {
                                currBktChar = c;
                                sb.deleteCharAt(j);
                                continue Label_0650;
                            }
                        }
                    }
                }
                currBS = (sb.charAt(j) == EscapedStringTokenizer.BACKSLASH);
                if (prevBS && currBS) {
                    sb.deleteCharAt(j);
                    prevBS = false;
                }
                else {
                    prevBS = currBS;
                    ++j;
                }
            }
        }
        return sb.toString();
    }
    
    private int skipDelimiters(final String str, int currPos) {
        while (!this.retDelims && currPos < this.maxPos && this.isDelimiter(str, currPos)) {
            ++currPos;
            this.updateBS(str, currPos);
            if (this.allowNullToken) {
                break;
            }
        }
        return currPos;
    }
    
    public int countTokens() {
        final boolean _prevBS = this.prevBS;
        final boolean _currBS = this.currBS;
        final boolean _evenBS = this.evenBS;
        final char _currBktChar = this.currBktChar;
        final int _lastUpdateBSpos = this.lastUpdateBSpos;
        int count = 0;
        int currpos = this.currPos;
        if (this.allowNullToken && this.start && currpos == 0 && this.isDelimiter(this.str, currpos)) {
            ++count;
        }
        while (currpos < this.maxPos) {
            if (this.allowNullToken && currpos == this.maxPos - 1 && this.isDelimiter(this.str, currpos)) {
                ++count;
            }
            currpos = this.skipDelimiters(this.str, currpos);
            if (currpos >= this.maxPos) {
                break;
            }
            final int start = currpos;
            this.updateBS(this.str, currpos);
            while (currpos < this.maxPos && !this.isDelimiter(this.str, currpos)) {
                ++currpos;
                this.updateBS(this.str, currpos);
            }
            if (this.retDelims && start == currpos && this.isDelimiter(this.str, currpos)) {
                ++currpos;
                this.updateBS(this.str, currpos);
            }
            ++count;
        }
        this.prevBS = _prevBS;
        this.currBS = _currBS;
        this.evenBS = _evenBS;
        this.currBktChar = _currBktChar;
        this.lastUpdateBSpos = _lastUpdateBSpos;
        return count;
    }
    
    private void updateBS(final String str, final int currPos) {
        if (this.lastUpdateBSpos == currPos) {
            return;
        }
        if ((this.lastUpdateBSpos = currPos) >= str.length()) {
            return;
        }
        if (this.bracketingChars != null && !this.isCurrentCharEscaped()) {
            if (this.currBktChar != '\0') {
                if (str.charAt(currPos) == this.currBktChar) {
                    this.currBktChar = '\0';
                    return;
                }
            }
            else {
                final char c = str.charAt(currPos);
                for (int i = 0; i < this.bracketingChars.length; ++i) {
                    if (c == this.bracketingChars[i]) {
                        this.currBktChar = c;
                        return;
                    }
                }
            }
        }
        this.prevBS = this.currBS;
        if (str.charAt(currPos) == EscapedStringTokenizer.BACKSLASH) {
            this.currBS = true;
            if (!this.prevBS) {
                this.evenBS = false;
            }
            else {
                this.evenBS = !this.evenBS;
            }
        }
        else {
            this.currBS = false;
        }
    }
    
    private void resetBS() {
        this.prevBS = false;
        this.currBS = false;
        this.evenBS = true;
        this.lastUpdateBSpos = -1;
        this.currBktChar = '\0';
    }
    
    private boolean isCurrentCharEscaped() {
        return this.prevBS && !this.evenBS;
    }
    
    private boolean isWithinBrackets() {
        return this.currBktChar != '\0';
    }
    
    private boolean isDelimiter(final String str, final int currPos) {
        return !this.isWithinBrackets() && !this.isCurrentCharEscaped() && this.isDelimiterChar(str, currPos);
    }
    
    private boolean isDelimiterChar(final String str, final int currPos) {
        return isDelimiterChar(this.delimiters, str, currPos);
    }
    
    private static boolean isDelimiterChar(final String delimiters, final String str, final int currPos) {
        return str.length() != 0 && delimiters.indexOf(str.charAt(currPos)) >= 0;
    }
    
    static {
        EscapedStringTokenizer.BACKSLASH = '\\';
        EscapedStringTokenizer.QUOTES = new char[] { '\'', '\"' };
    }
}
