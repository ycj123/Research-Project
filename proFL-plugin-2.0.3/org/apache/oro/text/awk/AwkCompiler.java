// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.PatternCompiler;

public final class AwkCompiler implements PatternCompiler
{
    public static final int DEFAULT_MASK = 0;
    public static final int CASE_INSENSITIVE_MASK = 1;
    public static final int MULTILINE_MASK = 2;
    static final char _END_OF_INPUT = '\uffff';
    private boolean __inCharacterClass;
    private boolean __caseSensitive;
    private boolean __multiline;
    private boolean __beginAnchor;
    private boolean __endAnchor;
    private char __lookahead;
    private int __position;
    private int __bytesRead;
    private int __expressionLength;
    private char[] __regularExpression;
    private int __openParen;
    private int __closeParen;
    
    private static boolean __isMetachar(final char c) {
        return c == '*' || c == '?' || c == '+' || c == '[' || c == ']' || c == '(' || c == ')' || c == '|' || c == '.';
    }
    
    static boolean _isWordCharacter(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_';
    }
    
    static boolean _isLowerCase(final char c) {
        return c >= 'a' && c <= 'z';
    }
    
    static boolean _isUpperCase(final char c) {
        return c >= 'A' && c <= 'Z';
    }
    
    static char _toggleCase(final char c) {
        if (_isUpperCase(c)) {
            return (char)(c + ' ');
        }
        if (_isLowerCase(c)) {
            return (char)(c - ' ');
        }
        return c;
    }
    
    private void __match(final char c) throws MalformedPatternException {
        if (c == this.__lookahead) {
            if (this.__bytesRead < this.__expressionLength) {
                this.__lookahead = this.__regularExpression[this.__bytesRead++];
            }
            else {
                this.__lookahead = '\uffff';
            }
            return;
        }
        throw new MalformedPatternException("token: " + c + " does not match lookahead: " + this.__lookahead + " at position: " + this.__bytesRead);
    }
    
    private void __putback() {
        if (this.__lookahead != '\uffff') {
            --this.__bytesRead;
        }
        this.__lookahead = this.__regularExpression[this.__bytesRead - 1];
    }
    
    private SyntaxNode __regex() throws MalformedPatternException {
        final SyntaxNode _branch = this.__branch();
        if (this.__lookahead == '|') {
            this.__match('|');
            return new OrNode(_branch, this.__regex());
        }
        return _branch;
    }
    
    private SyntaxNode __branch() throws MalformedPatternException {
        final SyntaxNode _piece = this.__piece();
        if (this.__lookahead == ')') {
            if (this.__openParen > this.__closeParen) {
                return _piece;
            }
            throw new MalformedPatternException("Parse error: close parenthesis without matching open parenthesis at position " + this.__bytesRead);
        }
        else {
            if (this.__lookahead == '|' || this.__lookahead == '\uffff') {
                return _piece;
            }
            final CatNode catNode2;
            CatNode catNode = catNode2 = new CatNode();
            catNode._left = _piece;
            while (true) {
                final SyntaxNode _piece2 = this.__piece();
                if (this.__lookahead == ')') {
                    if (this.__openParen > this.__closeParen) {
                        catNode._right = _piece2;
                        break;
                    }
                    throw new MalformedPatternException("Parse error: close parenthesis without matching open parenthesis at position " + this.__bytesRead);
                }
                else {
                    if (this.__lookahead == '|' || this.__lookahead == '\uffff') {
                        catNode._right = _piece2;
                        break;
                    }
                    catNode._right = new CatNode();
                    catNode = (CatNode)catNode._right;
                    catNode._left = _piece2;
                }
            }
            return catNode2;
        }
    }
    
    private SyntaxNode __piece() throws MalformedPatternException {
        final SyntaxNode _atom = this.__atom();
        switch (this.__lookahead) {
            case '+': {
                this.__match('+');
                return new PlusNode(_atom);
            }
            case '?': {
                this.__match('?');
                return new QuestionNode(_atom);
            }
            case '*': {
                this.__match('*');
                return new StarNode(_atom);
            }
            case '{': {
                return this.__repetition(_atom);
            }
            default: {
                return _atom;
            }
        }
    }
    
    private int __parseUnsignedInteger(final int n, final int n2, final int n3) throws MalformedPatternException {
        int n4 = 0;
        final StringBuffer sb = new StringBuffer(4);
        while (Character.digit(this.__lookahead, n) != -1 && n4 < n3) {
            sb.append(this.__lookahead);
            this.__match(this.__lookahead);
            ++n4;
        }
        if (n4 < n2 || n4 > n3) {
            throw new MalformedPatternException("Parse error: unexpected number of digits at position " + this.__bytesRead);
        }
        int int1;
        try {
            int1 = Integer.parseInt(sb.toString(), n);
        }
        catch (NumberFormatException ex) {
            throw new MalformedPatternException("Parse error: numeric value at position " + this.__bytesRead + " is invalid");
        }
        return int1;
    }
    
    private SyntaxNode __repetition(SyntaxNode left) throws MalformedPatternException {
        this.__match('{');
        int _parseUnsignedInteger = this.__parseUnsignedInteger(10, 1, Integer.MAX_VALUE);
        final int[] array = { this.__position };
        CatNode catNode2;
        if (this.__lookahead == '}') {
            this.__match('}');
            if (_parseUnsignedInteger == 0) {
                throw new MalformedPatternException("Parse error: Superfluous interval specified at position " + this.__bytesRead + ".  Number of occurences was set to zero.");
            }
            if (_parseUnsignedInteger == 1) {
                return left;
            }
            CatNode catNode = catNode2 = new CatNode();
            catNode._left = left;
            while (--_parseUnsignedInteger > 1) {
                left = left._clone(array);
                catNode._right = new CatNode();
                catNode = (CatNode)catNode._right;
                catNode._left = left;
            }
            catNode._right = left._clone(array);
        }
        else {
            if (this.__lookahead != ',') {
                throw new MalformedPatternException("Parse error: unexpected character " + this.__lookahead + " in interval at position " + this.__bytesRead);
            }
            this.__match(',');
            if (this.__lookahead == '}') {
                this.__match('}');
                if (_parseUnsignedInteger == 0) {
                    return new StarNode(left);
                }
                if (_parseUnsignedInteger == 1) {
                    return new PlusNode(left);
                }
                CatNode catNode3 = catNode2 = new CatNode();
                catNode3._left = left;
                while (--_parseUnsignedInteger > 0) {
                    left = left._clone(array);
                    catNode3._right = new CatNode();
                    catNode3 = (CatNode)catNode3._right;
                    catNode3._left = left;
                }
                catNode3._right = new StarNode(left._clone(array));
            }
            else {
                int _parseUnsignedInteger2 = this.__parseUnsignedInteger(10, 1, Integer.MAX_VALUE);
                this.__match('}');
                if (_parseUnsignedInteger2 < _parseUnsignedInteger) {
                    throw new MalformedPatternException("Parse error: invalid interval; " + _parseUnsignedInteger2 + " is less than " + _parseUnsignedInteger + " at position " + this.__bytesRead);
                }
                if (_parseUnsignedInteger2 == 0) {
                    throw new MalformedPatternException("Parse error: Superfluous interval specified at position " + this.__bytesRead + ".  Number of occurences was set to zero.");
                }
                if (_parseUnsignedInteger == 0) {
                    if (_parseUnsignedInteger2 == 1) {
                        return new QuestionNode(left);
                    }
                    CatNode catNode4 = catNode2 = new CatNode();
                    left = new QuestionNode(left);
                    catNode4._left = left;
                    while (--_parseUnsignedInteger2 > 1) {
                        left = left._clone(array);
                        catNode4._right = new CatNode();
                        catNode4 = (CatNode)catNode4._right;
                        catNode4._left = left;
                    }
                    catNode4._right = left._clone(array);
                }
                else if (_parseUnsignedInteger == _parseUnsignedInteger2) {
                    if (_parseUnsignedInteger == 1) {
                        return left;
                    }
                    CatNode catNode5 = catNode2 = new CatNode();
                    catNode5._left = left;
                    while (--_parseUnsignedInteger > 1) {
                        left = left._clone(array);
                        catNode5._right = new CatNode();
                        catNode5 = (CatNode)catNode5._right;
                        catNode5._left = left;
                    }
                    catNode5._right = left._clone(array);
                }
                else {
                    CatNode catNode6 = catNode2 = new CatNode();
                    catNode6._left = left;
                    for (int i = 1; i < _parseUnsignedInteger; ++i) {
                        left = left._clone(array);
                        catNode6._right = new CatNode();
                        catNode6 = (CatNode)catNode6._right;
                        catNode6._left = left;
                    }
                    left = new QuestionNode(left._clone(array));
                    int n = _parseUnsignedInteger2 - _parseUnsignedInteger;
                    if (n == 1) {
                        catNode6._right = left;
                    }
                    else {
                        catNode6._right = new CatNode();
                        CatNode catNode7 = (CatNode)catNode6._right;
                        catNode7._left = left;
                        while (--n > 1) {
                            left = left._clone(array);
                            catNode7._right = new CatNode();
                            catNode7 = (CatNode)catNode7._right;
                            catNode7._left = left;
                        }
                        catNode7._right = left._clone(array);
                    }
                }
            }
        }
        this.__position = array[0];
        return catNode2;
    }
    
    private SyntaxNode __backslashToken() throws MalformedPatternException {
        this.__match('\\');
        SyntaxNode syntaxNode;
        if (this.__lookahead == 'x') {
            this.__match('x');
            syntaxNode = this._newTokenNode((char)this.__parseUnsignedInteger(16, 2, 2), this.__position++);
        }
        else if (this.__lookahead == 'c') {
            this.__match('c');
            final char upperCase = Character.toUpperCase(this.__lookahead);
            syntaxNode = new TokenNode((char)((upperCase > '?') ? (upperCase - '@') : (upperCase + '@')), this.__position++);
            this.__match(this.__lookahead);
        }
        else if (this.__lookahead >= '0' && this.__lookahead <= '9') {
            this.__match(this.__lookahead);
            if (this.__lookahead >= '0' && this.__lookahead <= '9') {
                this.__putback();
                syntaxNode = this._newTokenNode((char)Integer.parseInt(Integer.toString(this.__parseUnsignedInteger(10, 2, 3)), 8), this.__position++);
            }
            else {
                this.__putback();
                if (this.__lookahead == '0') {
                    this.__match('0');
                    syntaxNode = new TokenNode('\0', this.__position++);
                }
                else {
                    Character.digit(this.__lookahead, 10);
                    syntaxNode = this._newTokenNode(this.__lookahead, this.__position++);
                    this.__match(this.__lookahead);
                }
            }
        }
        else if (this.__lookahead == 'b') {
            syntaxNode = new TokenNode('\b', this.__position++);
            this.__match('b');
        }
        else {
            char _lookahead = this.__lookahead;
            switch (this.__lookahead) {
                case 'n': {
                    _lookahead = '\n';
                    break;
                }
                case 'r': {
                    _lookahead = '\r';
                    break;
                }
                case 't': {
                    _lookahead = '\t';
                    break;
                }
                case 'f': {
                    _lookahead = '\f';
                    break;
                }
            }
            switch (_lookahead) {
                case 100: {
                    final CharacterClassNode characterClassNode = new CharacterClassNode(this.__position++);
                    characterClassNode._addTokenRange(48, 57);
                    syntaxNode = characterClassNode;
                    break;
                }
                case 68: {
                    final NegativeCharacterClassNode negativeCharacterClassNode = new NegativeCharacterClassNode(this.__position++);
                    negativeCharacterClassNode._addTokenRange(48, 57);
                    syntaxNode = negativeCharacterClassNode;
                    break;
                }
                case 119: {
                    final CharacterClassNode characterClassNode2 = new CharacterClassNode(this.__position++);
                    characterClassNode2._addTokenRange(48, 57);
                    characterClassNode2._addTokenRange(97, 122);
                    characterClassNode2._addTokenRange(65, 90);
                    characterClassNode2._addToken(95);
                    syntaxNode = characterClassNode2;
                    break;
                }
                case 87: {
                    final NegativeCharacterClassNode negativeCharacterClassNode2 = new NegativeCharacterClassNode(this.__position++);
                    negativeCharacterClassNode2._addTokenRange(48, 57);
                    negativeCharacterClassNode2._addTokenRange(97, 122);
                    negativeCharacterClassNode2._addTokenRange(65, 90);
                    negativeCharacterClassNode2._addToken(95);
                    syntaxNode = negativeCharacterClassNode2;
                    break;
                }
                case 115: {
                    final CharacterClassNode characterClassNode3 = new CharacterClassNode(this.__position++);
                    characterClassNode3._addToken(32);
                    characterClassNode3._addToken(12);
                    characterClassNode3._addToken(10);
                    characterClassNode3._addToken(13);
                    characterClassNode3._addToken(9);
                    syntaxNode = characterClassNode3;
                    break;
                }
                case 83: {
                    final NegativeCharacterClassNode negativeCharacterClassNode3 = new NegativeCharacterClassNode(this.__position++);
                    negativeCharacterClassNode3._addToken(32);
                    negativeCharacterClassNode3._addToken(12);
                    negativeCharacterClassNode3._addToken(10);
                    negativeCharacterClassNode3._addToken(13);
                    negativeCharacterClassNode3._addToken(9);
                    syntaxNode = negativeCharacterClassNode3;
                    break;
                }
                default: {
                    syntaxNode = this._newTokenNode(_lookahead, this.__position++);
                    break;
                }
            }
            this.__match(this.__lookahead);
        }
        return syntaxNode;
    }
    
    private SyntaxNode __atom() throws MalformedPatternException {
        SyntaxNode syntaxNode;
        if (this.__lookahead == '(') {
            this.__match('(');
            ++this.__openParen;
            syntaxNode = this.__regex();
            this.__match(')');
            ++this.__closeParen;
        }
        else if (this.__lookahead == '[') {
            syntaxNode = this.__characterClass();
        }
        else if (this.__lookahead == '.') {
            this.__match('.');
            final NegativeCharacterClassNode negativeCharacterClassNode = new NegativeCharacterClassNode(this.__position++);
            if (this.__multiline) {
                negativeCharacterClassNode._addToken(10);
            }
            syntaxNode = negativeCharacterClassNode;
        }
        else if (this.__lookahead == '\\') {
            syntaxNode = this.__backslashToken();
        }
        else {
            if (__isMetachar(this.__lookahead)) {
                throw new MalformedPatternException("Parse error: unexpected character " + this.__lookahead + " at position " + this.__bytesRead);
            }
            syntaxNode = this._newTokenNode(this.__lookahead, this.__position++);
            this.__match(this.__lookahead);
        }
        return syntaxNode;
    }
    
    private SyntaxNode __characterClass() throws MalformedPatternException {
        this.__match('[');
        this.__inCharacterClass = true;
        CharacterClassNode characterClassNode;
        if (this.__lookahead == '^') {
            this.__match('^');
            characterClassNode = new NegativeCharacterClassNode(this.__position++);
        }
        else {
            characterClassNode = new CharacterClassNode(this.__position++);
        }
        while (this.__lookahead != ']' && this.__lookahead != '\uffff') {
            char c2;
            if (this.__lookahead == '\\') {
                final SyntaxNode _backslashToken = this.__backslashToken();
                --this.__position;
                if (!(_backslashToken instanceof TokenNode)) {
                    final CharacterClassNode characterClassNode2 = (CharacterClassNode)_backslashToken;
                    for (char c = '\0'; c < '\u0100'; ++c) {
                        if (characterClassNode2._matches(c)) {
                            characterClassNode._addToken(c);
                        }
                    }
                    continue;
                }
                c2 = ((TokenNode)_backslashToken)._token;
                characterClassNode._addToken(c2);
                if (!this.__caseSensitive) {
                    characterClassNode._addToken(_toggleCase(c2));
                }
            }
            else {
                c2 = this.__lookahead;
                characterClassNode._addToken(this.__lookahead);
                if (!this.__caseSensitive) {
                    characterClassNode._addToken(_toggleCase(this.__lookahead));
                }
                this.__match(this.__lookahead);
            }
            if (this.__lookahead == '-') {
                this.__match('-');
                if (this.__lookahead == ']') {
                    characterClassNode._addToken(45);
                    break;
                }
                char c3;
                if (this.__lookahead == '\\') {
                    final SyntaxNode _backslashToken2 = this.__backslashToken();
                    --this.__position;
                    if (!(_backslashToken2 instanceof TokenNode)) {
                        throw new MalformedPatternException("Parse error: invalid range specified at position " + this.__bytesRead);
                    }
                    c3 = ((TokenNode)_backslashToken2)._token;
                }
                else {
                    c3 = this.__lookahead;
                    this.__match(this.__lookahead);
                }
                if (c3 < c2) {
                    throw new MalformedPatternException("Parse error: invalid range specified at position " + this.__bytesRead);
                }
                characterClassNode._addTokenRange(c2 + '\u0001', c3);
                if (this.__caseSensitive) {
                    continue;
                }
                characterClassNode._addTokenRange(_toggleCase((char)(c2 + '\u0001')), _toggleCase(c3));
            }
        }
        this.__match(']');
        this.__inCharacterClass = false;
        return characterClassNode;
    }
    
    SyntaxNode _newTokenNode(final char c, final int n) {
        if (!this.__inCharacterClass && !this.__caseSensitive && (_isUpperCase(c) || _isLowerCase(c))) {
            final CharacterClassNode characterClassNode = new CharacterClassNode(n);
            characterClassNode._addToken(c);
            characterClassNode._addToken(_toggleCase(c));
            return characterClassNode;
        }
        return new TokenNode(c, n);
    }
    
    SyntaxTree _parse(final char[] _regularExpression) throws MalformedPatternException {
        final int n = 0;
        this.__closeParen = n;
        this.__openParen = n;
        this.__regularExpression = _regularExpression;
        this.__bytesRead = 0;
        this.__expressionLength = _regularExpression.length;
        this.__inCharacterClass = false;
        this.__position = 0;
        this.__match(this.__lookahead);
        if (this.__lookahead == '^') {
            this.__beginAnchor = true;
            this.__match(this.__lookahead);
        }
        if (this.__expressionLength > 0 && _regularExpression[this.__expressionLength - 1] == '$') {
            --this.__expressionLength;
            this.__endAnchor = true;
        }
        SyntaxTree syntaxTree;
        if (this.__expressionLength > 1 || (this.__expressionLength == 1 && !this.__beginAnchor)) {
            final CatNode catNode = new CatNode();
            catNode._left = this.__regex();
            catNode._right = new TokenNode('\u0100', this.__position++);
            syntaxTree = new SyntaxTree(catNode, this.__position);
        }
        else {
            syntaxTree = new SyntaxTree(new TokenNode('\u0100', 0), 1);
        }
        syntaxTree._computeFollowPositions();
        return syntaxTree;
    }
    
    public Pattern compile(final char[] value, final int options) throws MalformedPatternException {
        final boolean b = false;
        this.__endAnchor = b;
        this.__beginAnchor = b;
        this.__caseSensitive = ((options & 0x1) == 0x0);
        this.__multiline = ((options & 0x2) != 0x0);
        final AwkPattern awkPattern = new AwkPattern(new String(value), this._parse(value));
        awkPattern._options = options;
        awkPattern._hasBeginAnchor = this.__beginAnchor;
        awkPattern._hasEndAnchor = this.__endAnchor;
        return awkPattern;
    }
    
    public Pattern compile(final String s, final int options) throws MalformedPatternException {
        final boolean b = false;
        this.__endAnchor = b;
        this.__beginAnchor = b;
        this.__caseSensitive = ((options & 0x1) == 0x0);
        this.__multiline = ((options & 0x2) != 0x0);
        final AwkPattern awkPattern = new AwkPattern(s, this._parse(s.toCharArray()));
        awkPattern._options = options;
        awkPattern._hasBeginAnchor = this.__beginAnchor;
        awkPattern._hasEndAnchor = this.__endAnchor;
        return awkPattern;
    }
    
    public Pattern compile(final char[] array) throws MalformedPatternException {
        return this.compile(array, 0);
    }
    
    public Pattern compile(final String s) throws MalformedPatternException {
        return this.compile(s, 0);
    }
}
