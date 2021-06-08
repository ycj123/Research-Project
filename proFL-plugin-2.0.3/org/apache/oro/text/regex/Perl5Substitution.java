// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.regex;

public class Perl5Substitution extends StringSubstitution
{
    public static final int INTERPOLATE_ALL = 0;
    public static final int INTERPOLATE_NONE = -1;
    private static final int __OPCODE_STORAGE_SIZE = 32;
    private static final int __MAX_GROUPS = 65535;
    static final int _OPCODE_COPY = -1;
    static final int _OPCODE_LOWERCASE_CHAR = -2;
    static final int _OPCODE_UPPERCASE_CHAR = -3;
    static final int _OPCODE_LOWERCASE_MODE = -4;
    static final int _OPCODE_UPPERCASE_MODE = -5;
    static final int _OPCODE_ENDCASE_MODE = -6;
    int _numInterpolations;
    int[] _subOpcodes;
    int _subOpcodesCount;
    char[] _substitutionChars;
    transient String _lastInterpolation;
    
    private static final boolean __isInterpolationCharacter(final char ch) {
        return Character.isDigit(ch) || ch == '&';
    }
    
    private void __addElement(final int n) {
        final int length = this._subOpcodes.length;
        if (this._subOpcodesCount == length) {
            final int[] subOpcodes = new int[length + 32];
            System.arraycopy(this._subOpcodes, 0, subOpcodes, 0, length);
            this._subOpcodes = subOpcodes;
        }
        this._subOpcodes[this._subOpcodesCount++] = n;
    }
    
    private void __parseSubs(final String s) {
        final char[] charArray = s.toCharArray();
        this._substitutionChars = charArray;
        final char[] array = charArray;
        final int length = array.length;
        this._subOpcodes = new int[32];
        this._subOpcodesCount = 0;
        int n = 0;
        int n2 = -1;
        int _isInterpolationCharacter = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < length; ++i) {
            final char ch = array[i];
            final int n5 = i + 1;
            if (_isInterpolationCharacter != 0) {
                final int digit = Character.digit(ch, 10);
                if (digit > -1) {
                    if (n <= 65535) {
                        n = n * 10 + digit;
                    }
                    if (n5 == length) {
                        this.__addElement(n);
                    }
                    continue;
                }
                else {
                    if (ch == '&' && array[i - 1] == '$') {
                        this.__addElement(0);
                        n = 0;
                        _isInterpolationCharacter = 0;
                        continue;
                    }
                    this.__addElement(n);
                    n = 0;
                    _isInterpolationCharacter = 0;
                }
            }
            if ((ch != '$' && ch != '\\') || n3 != 0) {
                n3 = 0;
                if (n2 < 0) {
                    n2 = i;
                    this.__addElement(-1);
                    this.__addElement(n2);
                }
                if (n5 == length) {
                    this.__addElement(n5 - n2);
                }
            }
            else {
                if (n2 >= 0) {
                    this.__addElement(i - n2);
                    n2 = -1;
                }
                if (n5 != length) {
                    final char c = array[n5];
                    if (ch == '$') {
                        _isInterpolationCharacter = (__isInterpolationCharacter(c) ? 1 : 0);
                    }
                    else if (ch == '\\') {
                        if (c == 'l') {
                            if (n4 == 0) {
                                this.__addElement(-2);
                                ++i;
                            }
                        }
                        else if (c == 'u') {
                            if (n4 == 0) {
                                this.__addElement(-3);
                                ++i;
                            }
                        }
                        else if (c == 'L') {
                            this.__addElement(-4);
                            ++i;
                            n4 = 1;
                        }
                        else if (c == 'U') {
                            this.__addElement(-5);
                            ++i;
                            n4 = 1;
                        }
                        else if (c == 'E') {
                            this.__addElement(-6);
                            ++i;
                            n4 = 0;
                        }
                        else {
                            n3 = 1;
                        }
                    }
                }
            }
        }
    }
    
    String _finalInterpolatedSub(final MatchResult matchResult) {
        final StringBuffer sb = new StringBuffer(10);
        this._calcSub(sb, matchResult);
        return sb.toString();
    }
    
    void _calcSub(final StringBuffer sb, final MatchResult matchResult) {
        final int[] subOpcodes = this._subOpcodes;
        int n = 0;
        final char[] substitutionChars = this._substitutionChars;
        final char[] charArray = matchResult.group(0).toCharArray();
        for (int subOpcodesCount = this._subOpcodesCount, i = 0; i < subOpcodesCount; ++i) {
            final int n2 = subOpcodes[i];
            int j;
            int len;
            char[] str;
            if (n2 >= 0 && n2 < matchResult.groups()) {
                j = matchResult.begin(n2);
                if (j < 0) {
                    continue;
                }
                final int end = matchResult.end(n2);
                if (end < 0) {
                    continue;
                }
                final int length = matchResult.length();
                if (j >= length || end > length) {
                    continue;
                }
                if (j >= end) {
                    continue;
                }
                len = end - j;
                str = charArray;
            }
            else if (n2 == -1) {
                if (++i >= subOpcodesCount) {
                    continue;
                }
                j = subOpcodes[i];
                if (++i >= subOpcodesCount) {
                    continue;
                }
                len = subOpcodes[i];
                str = substitutionChars;
            }
            else if (n2 == -2 || n2 == -3) {
                if (n != -4 && n != -5) {
                    n = n2;
                }
                continue;
            }
            else {
                if (n2 == -4 || n2 == -5) {
                    n = n2;
                    continue;
                }
                if (n2 == -6) {
                    n = 0;
                }
                continue;
            }
            if (n == -2) {
                sb.append(Character.toLowerCase(str[j++]));
                sb.append(str, j, --len);
                n = 0;
            }
            else if (n == -3) {
                sb.append(Character.toUpperCase(str[j++]));
                sb.append(str, j, --len);
                n = 0;
            }
            else if (n == -4) {
                while (j < j + len) {
                    sb.append(Character.toLowerCase(str[j++]));
                }
            }
            else if (n == -5) {
                while (j < j + len) {
                    sb.append(Character.toUpperCase(str[j++]));
                }
            }
            else {
                sb.append(str, j, len);
            }
        }
    }
    
    public Perl5Substitution() {
        this("", 0);
    }
    
    public Perl5Substitution(final String s) {
        this(s, 0);
    }
    
    public Perl5Substitution(final String s, final int n) {
        this.setSubstitution(s, n);
    }
    
    public void setSubstitution(final String s) {
        this.setSubstitution(s, 0);
    }
    
    public void setSubstitution(final String substitution, final int numInterpolations) {
        super.setSubstitution(substitution);
        this._numInterpolations = numInterpolations;
        if (numInterpolations != -1 && (substitution.indexOf(36) != -1 || substitution.indexOf(92) != -1)) {
            this.__parseSubs(substitution);
        }
        else {
            this._subOpcodes = null;
        }
        this._lastInterpolation = null;
    }
    
    public void appendSubstitution(final StringBuffer sb, final MatchResult matchResult, final int n, final PatternMatcherInput patternMatcherInput, final PatternMatcher patternMatcher, final Pattern pattern) {
        if (this._subOpcodes == null) {
            super.appendSubstitution(sb, matchResult, n, patternMatcherInput, patternMatcher, pattern);
            return;
        }
        if (this._numInterpolations < 1 || n < this._numInterpolations) {
            this._calcSub(sb, matchResult);
        }
        else {
            if (n == this._numInterpolations) {
                this._lastInterpolation = this._finalInterpolatedSub(matchResult);
            }
            sb.append(this._lastInterpolation);
        }
    }
}
