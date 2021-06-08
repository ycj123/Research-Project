// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.regex;

import java.util.Stack;

public final class Perl5Matcher implements PatternMatcher
{
    private static final char __EOS = '\uffff';
    private static final int __INITIAL_NUM_OFFSETS = 20;
    private boolean __multiline;
    private boolean __lastSuccess;
    private boolean __caseInsensitive;
    private char __previousChar;
    private char[] __input;
    private char[] __originalInput;
    private Perl5Repetition __currentRep;
    private int __numParentheses;
    private int __bol;
    private int __eol;
    private int __currentOffset;
    private int __endOffset;
    private char[] __program;
    private int __expSize;
    private int __inputOffset;
    private int __lastParen;
    private int[] __beginMatchOffsets;
    private int[] __endMatchOffsets;
    private Stack __stack;
    private Perl5MatchResult __lastMatchResult;
    private static final int __DEFAULT_LAST_MATCH_END_OFFSET = -100;
    private int __lastMatchInputEndOffset;
    
    public Perl5Matcher() {
        this.__multiline = false;
        this.__lastSuccess = false;
        this.__caseInsensitive = false;
        this.__stack = new Stack();
        this.__lastMatchResult = null;
        this.__lastMatchInputEndOffset = -100;
    }
    
    private static boolean __compare(final char[] array, int n, final char[] array2, int n2, final int n3) {
        for (int i = 0; i < n3; ++i, ++n, ++n2) {
            if (n >= array.length) {
                return false;
            }
            if (n2 >= array2.length) {
                return false;
            }
            if (array[n] != array2[n2]) {
                return false;
            }
        }
        return true;
    }
    
    private static int __findFirst(final char[] array, int i, final int n, final char[] array2) {
        if (array.length == 0) {
            return n;
        }
        final char c = array2[0];
        while (i < n) {
            if (c == array[i]) {
                final int n2 = i;
                int n3;
                for (n3 = 0; i < n && n3 < array2.length && array2[n3] == array[i]; ++n3, ++i) {}
                i = n2;
                if (n3 >= array2.length) {
                    break;
                }
            }
            ++i;
        }
        return i;
    }
    
    private void __pushState(final int n) {
        int n2 = 3 * (this.__expSize - n);
        int[] item;
        if (n2 <= 0) {
            item = new int[3];
        }
        else {
            item = new int[n2 + 3];
        }
        item[0] = this.__expSize;
        item[1] = this.__lastParen;
        item[2] = this.__inputOffset;
        for (int i = this.__expSize; i > n; --i, n2 -= 3) {
            item[n2] = this.__endMatchOffsets[i];
            item[n2 + 1] = this.__beginMatchOffsets[i];
            item[n2 + 2] = i;
        }
        this.__stack.push(item);
    }
    
    private void __popState() {
        final int[] array = this.__stack.pop();
        this.__expSize = array[0];
        this.__lastParen = array[1];
        this.__inputOffset = array[2];
        for (int i = 3; i < array.length; i += 3) {
            final int n = array[i + 2];
            this.__beginMatchOffsets[n] = array[i + 1];
            if (n <= this.__lastParen) {
                this.__endMatchOffsets[n] = array[i];
            }
        }
        for (int j = this.__lastParen + 1; j <= this.__numParentheses; ++j) {
            if (j > this.__expSize) {
                this.__beginMatchOffsets[j] = -1;
            }
            this.__endMatchOffsets[j] = -1;
        }
    }
    
    private void __initInterpreterGlobals(final Perl5Pattern perl5Pattern, final char[] _input, final int _bol, int n, final int _currentOffset) {
        this.__caseInsensitive = perl5Pattern._isCaseInsensitive;
        this.__input = _input;
        this.__endOffset = n;
        this.__currentRep = new Perl5Repetition();
        this.__currentRep._numInstances = 0;
        this.__currentRep._lastRepetition = null;
        this.__program = perl5Pattern._program;
        this.__stack.setSize(0);
        if (_currentOffset == _bol || _currentOffset <= 0) {
            this.__previousChar = '\n';
        }
        else {
            this.__previousChar = _input[_currentOffset - 1];
            if (!this.__multiline && this.__previousChar == '\n') {
                this.__previousChar = '\0';
            }
        }
        this.__numParentheses = perl5Pattern._numParentheses;
        this.__currentOffset = _currentOffset;
        this.__bol = _bol;
        this.__eol = n;
        n = this.__numParentheses + 1;
        if (this.__beginMatchOffsets == null || n > this.__beginMatchOffsets.length) {
            if (n < 20) {
                n = 20;
            }
            this.__beginMatchOffsets = new int[n];
            this.__endMatchOffsets = new int[n];
        }
    }
    
    private void __setLastMatchResult() {
        int n = 0;
        this.__lastMatchResult = new Perl5MatchResult(this.__numParentheses + 1);
        if (this.__endMatchOffsets[0] > this.__originalInput.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.__lastMatchResult._matchBeginOffset = this.__beginMatchOffsets[0];
        while (this.__numParentheses >= 0) {
            final int n2 = this.__beginMatchOffsets[this.__numParentheses];
            if (n2 >= 0) {
                this.__lastMatchResult._beginGroupOffset[this.__numParentheses] = n2 - this.__lastMatchResult._matchBeginOffset;
            }
            else {
                this.__lastMatchResult._beginGroupOffset[this.__numParentheses] = -1;
            }
            final int n3 = this.__endMatchOffsets[this.__numParentheses];
            if (n3 >= 0) {
                this.__lastMatchResult._endGroupOffset[this.__numParentheses] = n3 - this.__lastMatchResult._matchBeginOffset;
                if (n3 > n && n3 <= this.__originalInput.length) {
                    n = n3;
                }
            }
            else {
                this.__lastMatchResult._endGroupOffset[this.__numParentheses] = -1;
            }
            --this.__numParentheses;
        }
        this.__lastMatchResult._match = new String(this.__originalInput, this.__beginMatchOffsets[0], n - this.__beginMatchOffsets[0]);
        this.__originalInput = null;
    }
    
    private boolean __interpret(final Perl5Pattern perl5Pattern, final char[] array, final int n, int n2, final int _currentOffset) {
        int length = 0;
        int n3 = 0;
        this.__initInterpreterGlobals(perl5Pattern, array, n, n2, _currentOffset);
        boolean _lastSuccess = false;
        final char[] mustString = perl5Pattern._mustString;
        Label_1711: {
            if (mustString != null && ((perl5Pattern._anchor & 0x3) == 0x0 || ((this.__multiline || (perl5Pattern._anchor & 0x2) != 0x0) && perl5Pattern._back >= 0))) {
                this.__currentOffset = __findFirst(this.__input, this.__currentOffset, n2, mustString);
                if (this.__currentOffset >= n2) {
                    if ((perl5Pattern._options & 0x8000) == 0x0) {
                        ++perl5Pattern._mustUtility;
                    }
                    _lastSuccess = false;
                    break Label_1711;
                }
                if (perl5Pattern._back >= 0) {
                    this.__currentOffset -= perl5Pattern._back;
                    if (this.__currentOffset < _currentOffset) {
                        this.__currentOffset = _currentOffset;
                    }
                    length = perl5Pattern._back + mustString.length;
                }
                else if (!perl5Pattern._isExpensive && (perl5Pattern._options & 0x8000) == 0x0 && --perl5Pattern._mustUtility < 0) {
                    perl5Pattern._mustString = null;
                    this.__currentOffset = _currentOffset;
                }
                else {
                    this.__currentOffset = _currentOffset;
                    length = mustString.length;
                }
            }
            if ((perl5Pattern._anchor & 0x3) != 0x0) {
                if (this.__currentOffset == n && this.__tryExpression(n)) {
                    _lastSuccess = true;
                }
                else if (this.__multiline || (perl5Pattern._anchor & 0x2) != 0x0 || (perl5Pattern._anchor & 0x8) != 0x0) {
                    if (length > 0) {
                        n3 = length - 1;
                    }
                    n2 -= n3;
                    if (this.__currentOffset > _currentOffset) {
                        --this.__currentOffset;
                    }
                    while (this.__currentOffset < n2) {
                        if (this.__input[this.__currentOffset++] == '\n' && this.__currentOffset < n2 && this.__tryExpression(this.__currentOffset)) {
                            _lastSuccess = true;
                            break;
                        }
                    }
                }
            }
            else if (perl5Pattern._startString != null) {
                final char[] startString = perl5Pattern._startString;
                if ((perl5Pattern._anchor & 0x4) != 0x0) {
                    final char c = startString[0];
                    while (this.__currentOffset < n2) {
                        if (c == this.__input[this.__currentOffset]) {
                            if (this.__tryExpression(this.__currentOffset)) {
                                _lastSuccess = true;
                                break;
                            }
                            ++this.__currentOffset;
                            while (this.__currentOffset < n2 && this.__input[this.__currentOffset] == c) {
                                ++this.__currentOffset;
                            }
                        }
                        ++this.__currentOffset;
                    }
                }
                else {
                    while ((this.__currentOffset = __findFirst(this.__input, this.__currentOffset, n2, startString)) < n2) {
                        if (this.__tryExpression(this.__currentOffset)) {
                            _lastSuccess = true;
                            break;
                        }
                        ++this.__currentOffset;
                    }
                }
            }
            else {
                final int startClassOffset;
                if ((startClassOffset = perl5Pattern._startClassOffset) != -1) {
                    final boolean b = (perl5Pattern._anchor & 0x4) == 0x0;
                    if (length > 0) {
                        n3 = length - 1;
                    }
                    n2 -= n3;
                    int n4 = 1;
                    final char c2;
                    switch (c2 = this.__program[startClassOffset]) {
                        case '\t': {
                            final int getOperand = OpCode._getOperand(startClassOffset);
                            while (this.__currentOffset < n2) {
                                final char c3 = this.__input[this.__currentOffset];
                                if (c3 < '\u0100' && (this.__program[getOperand + (c3 >> 4)] & 1 << (c3 & '\u000f')) == 0x0) {
                                    if (n4 != 0 && this.__tryExpression(this.__currentOffset)) {
                                        _lastSuccess = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.__currentOffset;
                            }
                            break;
                        }
                        case '#':
                        case '$': {
                            final int getOperand2 = OpCode._getOperand(startClassOffset);
                            while (this.__currentOffset < n2) {
                                if (this.__matchUnicodeClass(this.__input[this.__currentOffset], this.__program, getOperand2, c2)) {
                                    if (n4 != 0 && this.__tryExpression(this.__currentOffset)) {
                                        _lastSuccess = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.__currentOffset;
                            }
                            break;
                        }
                        case '\u0014': {
                            if (length > 0) {
                                ++n3;
                                --n2;
                            }
                            boolean b2;
                            if (this.__currentOffset != n) {
                                b2 = OpCode._isWordCharacter(this.__input[this.__currentOffset - 1]);
                            }
                            else {
                                b2 = OpCode._isWordCharacter(this.__previousChar);
                            }
                            while (this.__currentOffset < n2) {
                                if (b2 != OpCode._isWordCharacter(this.__input[this.__currentOffset])) {
                                    b2 = !b2;
                                    if (this.__tryExpression(this.__currentOffset)) {
                                        _lastSuccess = true;
                                        break Label_1711;
                                    }
                                }
                                ++this.__currentOffset;
                            }
                            if ((length > 0 || b2) && this.__tryExpression(this.__currentOffset)) {
                                _lastSuccess = true;
                                break;
                            }
                            break;
                        }
                        case '\u0015': {
                            if (length > 0) {
                                ++n3;
                                --n2;
                            }
                            boolean b3;
                            if (this.__currentOffset != n) {
                                b3 = OpCode._isWordCharacter(this.__input[this.__currentOffset - 1]);
                            }
                            else {
                                b3 = OpCode._isWordCharacter(this.__previousChar);
                            }
                            while (this.__currentOffset < n2) {
                                if (b3 != OpCode._isWordCharacter(this.__input[this.__currentOffset])) {
                                    b3 = !b3;
                                }
                                else if (this.__tryExpression(this.__currentOffset)) {
                                    _lastSuccess = true;
                                    break Label_1711;
                                }
                                ++this.__currentOffset;
                            }
                            if ((length > 0 || !b3) && this.__tryExpression(this.__currentOffset)) {
                                _lastSuccess = true;
                                break;
                            }
                            break;
                        }
                        case '\u0012': {
                            while (this.__currentOffset < n2) {
                                if (OpCode._isWordCharacter(this.__input[this.__currentOffset])) {
                                    if (n4 != 0 && this.__tryExpression(this.__currentOffset)) {
                                        _lastSuccess = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.__currentOffset;
                            }
                            break;
                        }
                        case '\u0013': {
                            while (this.__currentOffset < n2) {
                                if (!OpCode._isWordCharacter(this.__input[this.__currentOffset])) {
                                    if (n4 != 0 && this.__tryExpression(this.__currentOffset)) {
                                        _lastSuccess = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.__currentOffset;
                            }
                            break;
                        }
                        case '\u0016': {
                            while (this.__currentOffset < n2) {
                                if (Character.isWhitespace(this.__input[this.__currentOffset])) {
                                    if (n4 != 0 && this.__tryExpression(this.__currentOffset)) {
                                        _lastSuccess = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.__currentOffset;
                            }
                            break;
                        }
                        case '\u0017': {
                            while (this.__currentOffset < n2) {
                                if (!Character.isWhitespace(this.__input[this.__currentOffset])) {
                                    if (n4 != 0 && this.__tryExpression(this.__currentOffset)) {
                                        _lastSuccess = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.__currentOffset;
                            }
                            break;
                        }
                        case '\u0018': {
                            while (this.__currentOffset < n2) {
                                if (Character.isDigit(this.__input[this.__currentOffset])) {
                                    if (n4 != 0 && this.__tryExpression(this.__currentOffset)) {
                                        _lastSuccess = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.__currentOffset;
                            }
                            break;
                        }
                        case '\u0019': {
                            while (this.__currentOffset < n2) {
                                if (!Character.isDigit(this.__input[this.__currentOffset])) {
                                    if (n4 != 0 && this.__tryExpression(this.__currentOffset)) {
                                        _lastSuccess = true;
                                        break;
                                    }
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    n4 = 1;
                                }
                                ++this.__currentOffset;
                            }
                            break;
                        }
                    }
                }
                else {
                    if (length > 0) {
                        n3 = length - 1;
                    }
                    n2 -= n3;
                    while (!this.__tryExpression(this.__currentOffset)) {
                        if (this.__currentOffset++ >= n2) {
                            break Label_1711;
                        }
                    }
                    _lastSuccess = true;
                }
            }
        }
        this.__lastSuccess = _lastSuccess;
        this.__lastMatchResult = null;
        return _lastSuccess;
    }
    
    private boolean __matchUnicodeClass(final char ch, final char[] array, int n, final char c) {
        boolean b = c == '#';
        while (array[n] != '\0') {
            if (array[n] == '%') {
                ++n;
                if (ch >= array[n] && ch <= array[n + 1]) {
                    return b;
                }
                n += 2;
            }
            else if (array[n] == '1') {
                ++n;
                if (array[n++] == ch) {
                    return b;
                }
                continue;
            }
            else {
                b = ((array[n] == '/') ? b : (!b));
                ++n;
                switch (array[n++]) {
                    case '\u0012': {
                        if (OpCode._isWordCharacter(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '\u0013': {
                        if (!OpCode._isWordCharacter(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '\u0016': {
                        if (Character.isWhitespace(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '\u0017': {
                        if (!Character.isWhitespace(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '\u0018': {
                        if (Character.isDigit(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '\u0019': {
                        if (!Character.isDigit(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '2': {
                        if (Character.isLetterOrDigit(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '&': {
                        if (Character.isLetter(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '\'': {
                        if (Character.isSpaceChar(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '(': {
                        if (Character.isISOControl(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '*': {
                        if (Character.isLowerCase(ch)) {
                            return b;
                        }
                        if (this.__caseInsensitive && Character.isUpperCase(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '-': {
                        if (Character.isUpperCase(ch)) {
                            return b;
                        }
                        if (this.__caseInsensitive && Character.isLowerCase(ch)) {
                            return b;
                        }
                        continue;
                    }
                    case '+': {
                        if (Character.isSpaceChar(ch)) {
                            return b;
                        }
                    }
                    case ')': {
                        if (Character.isLetterOrDigit(ch)) {
                            return b;
                        }
                    }
                    case ',': {
                        switch (Character.getType(ch)) {
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27: {
                                return b;
                            }
                            default: {
                                continue;
                            }
                        }
                        break;
                    }
                    case '.': {
                        if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f') || (ch >= 'A' && ch <= 'F')) {
                            return b;
                        }
                        continue;
                    }
                    case '3': {
                        if (ch < '\u0080') {
                            return b;
                        }
                        continue;
                    }
                }
            }
        }
        return !b;
    }
    
    private boolean __tryExpression(final int _inputOffset) {
        this.__inputOffset = _inputOffset;
        this.__lastParen = 0;
        this.__expSize = 0;
        if (this.__numParentheses > 0) {
            for (int i = 0; i <= this.__numParentheses; ++i) {
                this.__beginMatchOffsets[i] = -1;
                this.__endMatchOffsets[i] = -1;
            }
        }
        if (this.__match(1)) {
            this.__beginMatchOffsets[0] = _inputOffset;
            this.__endMatchOffsets[0] = this.__inputOffset;
            return true;
        }
        return false;
    }
    
    private int __repeat(final int n, final int n2) {
        int _inputOffset = this.__inputOffset;
        int _eol = this.__eol;
        if (n2 != 65535 && n2 < _eol - _inputOffset) {
            _eol = _inputOffset + n2;
        }
        int getOperand = OpCode._getOperand(n);
        final char c;
        switch (c = this.__program[n]) {
            case '\u0007': {
                while (_inputOffset < _eol && this.__input[_inputOffset] != '\n') {
                    ++_inputOffset;
                }
                break;
            }
            case '\b': {
                _inputOffset = _eol;
                break;
            }
            case '\u000e': {
                ++getOperand;
                while (_inputOffset < _eol && this.__program[getOperand] == this.__input[_inputOffset]) {
                    ++_inputOffset;
                }
                break;
            }
            case '\t': {
                char c2;
                if (_inputOffset < _eol && (c2 = this.__input[_inputOffset]) < '\u0100') {
                    while (c2 < '\u0100' && (this.__program[getOperand + (c2 >> 4)] & 1 << (c2 & '\u000f')) == 0x0 && ++_inputOffset < _eol) {
                        c2 = this.__input[_inputOffset];
                    }
                    break;
                }
                break;
            }
            case '#':
            case '$': {
                if (_inputOffset < _eol) {
                    for (char c3 = this.__input[_inputOffset]; this.__matchUnicodeClass(c3, this.__program, getOperand, c) && ++_inputOffset < _eol; c3 = this.__input[_inputOffset]) {}
                    break;
                }
                break;
            }
            case '\u0012': {
                while (_inputOffset < _eol && OpCode._isWordCharacter(this.__input[_inputOffset])) {
                    ++_inputOffset;
                }
                break;
            }
            case '\u0013': {
                while (_inputOffset < _eol && !OpCode._isWordCharacter(this.__input[_inputOffset])) {
                    ++_inputOffset;
                }
                break;
            }
            case '\u0016': {
                while (_inputOffset < _eol && Character.isWhitespace(this.__input[_inputOffset])) {
                    ++_inputOffset;
                }
                break;
            }
            case '\u0017': {
                while (_inputOffset < _eol && !Character.isWhitespace(this.__input[_inputOffset])) {
                    ++_inputOffset;
                }
                break;
            }
            case '\u0018': {
                while (_inputOffset < _eol && Character.isDigit(this.__input[_inputOffset])) {
                    ++_inputOffset;
                }
                break;
            }
            case '\u0019': {
                while (_inputOffset < _eol && !Character.isDigit(this.__input[_inputOffset])) {
                    ++_inputOffset;
                }
                break;
            }
        }
        final int n3 = _inputOffset - this.__inputOffset;
        this.__inputOffset = _inputOffset;
        return n3;
    }
    
    private boolean __match(final int n) {
        boolean minMod = false;
        int _inputOffset = this.__inputOffset;
        boolean b = _inputOffset < this.__endOffset;
        char c = b ? this.__input[_inputOffset] : '\uffff';
        int next;
        for (int i = n; i < this.__program.length; i = next) {
            next = OpCode._getNext(this.__program, i);
            final char c2;
            switch (c2 = this.__program[i]) {
                case '\u0001': {
                    if (_inputOffset == this.__bol) {
                        if (this.__previousChar == '\n') {
                            break;
                        }
                    }
                    else if (this.__multiline && (b || _inputOffset < this.__eol) && this.__input[_inputOffset - '\u0001'] == '\n') {
                        break;
                    }
                    return false;
                }
                case '\u0002': {
                    if (_inputOffset == this.__bol) {
                        if (this.__previousChar == '\n') {
                            break;
                        }
                    }
                    else if ((b || _inputOffset < this.__eol) && this.__input[_inputOffset - '\u0001'] == '\n') {
                        break;
                    }
                    return false;
                }
                case '\u0003': {
                    if (_inputOffset == this.__bol && this.__previousChar == '\n') {
                        break;
                    }
                    return false;
                }
                case '\u001e': {
                    if (_inputOffset == this.__bol) {
                        break;
                    }
                    return true;
                }
                case '\u0004': {
                    if ((b || _inputOffset < this.__eol) && c != '\n') {
                        return false;
                    }
                    if (!this.__multiline && this.__eol - _inputOffset > 1) {
                        return false;
                    }
                    break;
                }
                case '\u0005': {
                    if ((b || _inputOffset < this.__eol) && c != '\n') {
                        return false;
                    }
                    break;
                }
                case '\u0006': {
                    if ((b || _inputOffset < this.__eol) && c != '\n') {
                        return false;
                    }
                    if (this.__eol - _inputOffset > 1) {
                        return false;
                    }
                    break;
                }
                case '\b': {
                    if (!b && _inputOffset >= this.__eol) {
                        return false;
                    }
                    b = (++_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\u0007': {
                    if ((!b && _inputOffset >= this.__eol) || c == '\n') {
                        return false;
                    }
                    b = (++_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\u000e': {
                    int getOperand = OpCode._getOperand(i);
                    final char c3 = this.__program[getOperand++];
                    if (this.__program[getOperand] != c) {
                        return false;
                    }
                    if (this.__eol - _inputOffset < c3) {
                        return false;
                    }
                    if (c3 > '\u0001' && !__compare(this.__program, getOperand, this.__input, _inputOffset, c3)) {
                        return false;
                    }
                    _inputOffset += c3;
                    b = (_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\t': {
                    final int getOperand2 = OpCode._getOperand(i);
                    if (c == '\uffff' && b) {
                        c = this.__input[_inputOffset];
                    }
                    if (c >= '\u0100' || (this.__program[getOperand2 + (c >> 4)] & 1 << (c & '\u000f')) != 0x0) {
                        return false;
                    }
                    if (!b && _inputOffset >= this.__eol) {
                        return false;
                    }
                    b = (++_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '#':
                case '$': {
                    final int getOperand3 = OpCode._getOperand(i);
                    if (c == '\uffff' && b) {
                        c = this.__input[_inputOffset];
                    }
                    if (!this.__matchUnicodeClass(c, this.__program, getOperand3, c2)) {
                        return false;
                    }
                    if (!b && _inputOffset >= this.__eol) {
                        return false;
                    }
                    b = (++_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\u0012': {
                    if (!b) {
                        return false;
                    }
                    if (!OpCode._isWordCharacter(c)) {
                        return false;
                    }
                    b = (++_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\u0013': {
                    if (!b && _inputOffset >= this.__eol) {
                        return false;
                    }
                    if (OpCode._isWordCharacter(c)) {
                        return false;
                    }
                    b = (++_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\u0014':
                case '\u0015': {
                    boolean b2;
                    if (_inputOffset == this.__bol) {
                        b2 = OpCode._isWordCharacter(this.__previousChar);
                    }
                    else {
                        b2 = OpCode._isWordCharacter(this.__input[_inputOffset - '\u0001']);
                    }
                    if (b2 == OpCode._isWordCharacter(c) == (this.__program[i] == '\u0014')) {
                        return false;
                    }
                    break;
                }
                case '\u0016': {
                    if (!b && _inputOffset >= this.__eol) {
                        return false;
                    }
                    if (!Character.isWhitespace(c)) {
                        return false;
                    }
                    b = (++_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\u0017': {
                    if (!b) {
                        return false;
                    }
                    if (Character.isWhitespace(c)) {
                        return false;
                    }
                    b = (++_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\u0018': {
                    if (!Character.isDigit(c)) {
                        return false;
                    }
                    b = (++_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\u0019': {
                    if (!b && _inputOffset >= this.__eol) {
                        return false;
                    }
                    if (Character.isDigit(c)) {
                        return false;
                    }
                    b = (++_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\u001a': {
                    final char getArg1 = OpCode._getArg1(this.__program, i);
                    final int n2 = this.__beginMatchOffsets[getArg1];
                    if (n2 == -1) {
                        return false;
                    }
                    if (this.__endMatchOffsets[getArg1] == -1) {
                        return false;
                    }
                    if (n2 == this.__endMatchOffsets[getArg1]) {
                        break;
                    }
                    if (this.__input[n2] != c) {
                        return false;
                    }
                    final int n3 = this.__endMatchOffsets[getArg1] - n2;
                    if (_inputOffset + n3 > this.__eol) {
                        return false;
                    }
                    if (n3 > '\u0001' && !__compare(this.__input, n2, this.__input, _inputOffset, n3)) {
                        return false;
                    }
                    _inputOffset += n3;
                    b = (_inputOffset < this.__endOffset);
                    c = (b ? this.__input[_inputOffset] : '\uffff');
                    break;
                }
                case '\u000f': {}
                case '\u001b': {
                    final char getArg2 = OpCode._getArg1(this.__program, i);
                    this.__beginMatchOffsets[getArg2] = _inputOffset;
                    if (getArg2 > this.__expSize) {
                        this.__expSize = getArg2;
                        break;
                    }
                    break;
                }
                case '\u001c': {
                    final char getArg3 = OpCode._getArg1(this.__program, i);
                    this.__endMatchOffsets[getArg3] = _inputOffset;
                    if (getArg3 > this.__lastParen) {
                        this.__lastParen = getArg3;
                        break;
                    }
                    break;
                }
                case '\u000b': {
                    final Perl5Repetition _currentRep = new Perl5Repetition();
                    _currentRep._lastRepetition = this.__currentRep;
                    this.__currentRep = _currentRep;
                    _currentRep._parenFloor = this.__lastParen;
                    _currentRep._numInstances = -1;
                    _currentRep._min = OpCode._getArg1(this.__program, i);
                    _currentRep._max = OpCode._getArg2(this.__program, i);
                    _currentRep._scan = OpCode._getNextOperator(i) + 2;
                    _currentRep._next = next;
                    _currentRep._minMod = minMod;
                    _currentRep._lastLocation = -1;
                    this.__inputOffset = _inputOffset;
                    final boolean _match = this.__match(OpCode._getPrevOperator(next));
                    this.__currentRep = _currentRep._lastRepetition;
                    return _match;
                }
                case '\"': {
                    final Perl5Repetition _currentRep2 = this.__currentRep;
                    final int numInstances = _currentRep2._numInstances + 1;
                    this.__inputOffset = _inputOffset;
                    if (_inputOffset == _currentRep2._lastLocation) {
                        this.__currentRep = _currentRep2._lastRepetition;
                        final int numInstances2 = this.__currentRep._numInstances;
                        if (this.__match(_currentRep2._next)) {
                            return true;
                        }
                        this.__currentRep._numInstances = numInstances2;
                        this.__currentRep = _currentRep2;
                        return false;
                    }
                    else if (numInstances < _currentRep2._min) {
                        _currentRep2._numInstances = numInstances;
                        _currentRep2._lastLocation = _inputOffset;
                        if (this.__match(_currentRep2._scan)) {
                            return true;
                        }
                        _currentRep2._numInstances = numInstances - 1;
                        return false;
                    }
                    else if (_currentRep2._minMod) {
                        this.__currentRep = _currentRep2._lastRepetition;
                        final int numInstances3 = this.__currentRep._numInstances;
                        if (this.__match(_currentRep2._next)) {
                            return true;
                        }
                        this.__currentRep._numInstances = numInstances3;
                        this.__currentRep = _currentRep2;
                        if (numInstances >= _currentRep2._max) {
                            return false;
                        }
                        this.__inputOffset = _inputOffset;
                        _currentRep2._numInstances = numInstances;
                        _currentRep2._lastLocation = _inputOffset;
                        if (this.__match(_currentRep2._scan)) {
                            return true;
                        }
                        _currentRep2._numInstances = numInstances - 1;
                        return false;
                    }
                    else {
                        if (numInstances < _currentRep2._max) {
                            this.__pushState(_currentRep2._parenFloor);
                            _currentRep2._numInstances = numInstances;
                            _currentRep2._lastLocation = _inputOffset;
                            if (this.__match(_currentRep2._scan)) {
                                return true;
                            }
                            this.__popState();
                            this.__inputOffset = _inputOffset;
                        }
                        this.__currentRep = _currentRep2._lastRepetition;
                        final int numInstances4 = this.__currentRep._numInstances;
                        if (this.__match(_currentRep2._next)) {
                            return true;
                        }
                        _currentRep2._numInstances = numInstances4;
                        this.__currentRep = _currentRep2;
                        _currentRep2._numInstances = numInstances - 1;
                        return false;
                    }
                    break;
                }
                case '\f': {
                    if (this.__program[next] != '\f') {
                        next = OpCode._getNextOperator(i);
                        break;
                    }
                    final int _lastParen = this.__lastParen;
                    do {
                        this.__inputOffset = _inputOffset;
                        if (this.__match(OpCode._getNextOperator(i))) {
                            return true;
                        }
                        int j;
                        for (j = this.__lastParen; j > _lastParen; --j) {
                            this.__endMatchOffsets[j] = -1;
                        }
                        this.__lastParen = j;
                        i = OpCode._getNext(this.__program, i);
                    } while (i != -1 && this.__program[i] == '\f');
                    return false;
                }
                case '\u001d': {
                    minMod = true;
                    break;
                }
                case '\n':
                case '\u0010':
                case '\u0011': {
                    char getArg4;
                    char getArg5;
                    int n4;
                    if (c2 == '\n') {
                        getArg4 = OpCode._getArg1(this.__program, i);
                        getArg5 = OpCode._getArg2(this.__program, i);
                        n4 = OpCode._getNextOperator(i) + 2;
                    }
                    else if (c2 == '\u0010') {
                        getArg4 = '\0';
                        getArg5 = '\uffff';
                        n4 = OpCode._getNextOperator(i);
                    }
                    else {
                        getArg4 = '\u0001';
                        getArg5 = '\uffff';
                        n4 = OpCode._getNextOperator(i);
                    }
                    char c4;
                    int n5;
                    if (this.__program[next] == '\u000e') {
                        c4 = this.__program[OpCode._getOperand(next) + 1];
                        n5 = 0;
                    }
                    else {
                        c4 = '\uffff';
                        n5 = -1000;
                    }
                    this.__inputOffset = _inputOffset;
                    if (minMod) {
                        if (getArg4 > '\0' && this.__repeat(n4, getArg4) < getArg4) {
                            return false;
                        }
                        while (getArg5 >= getArg4 || (getArg5 == '\uffff' && getArg4 > '\0')) {
                            if ((n5 == -1000 || this.__inputOffset >= this.__endOffset || this.__input[this.__inputOffset] == c4) && this.__match(next)) {
                                return true;
                            }
                            this.__inputOffset = _inputOffset + getArg4;
                            if (this.__repeat(n4, 1) == 0) {
                                return false;
                            }
                            ++getArg4;
                            this.__inputOffset = _inputOffset + getArg4;
                        }
                    }
                    else {
                        int k = this.__repeat(n4, getArg5);
                        if (getArg4 < k && OpCode._opType[this.__program[next]] == '\u0004' && ((!this.__multiline && this.__program[next] != '\u0005') || this.__program[next] == '\u0006')) {
                            getArg4 = (char)k;
                        }
                        while (k >= getArg4) {
                            if ((n5 == -1000 || this.__inputOffset >= this.__endOffset || this.__input[this.__inputOffset] == c4) && this.__match(next)) {
                                return true;
                            }
                            --k;
                            this.__inputOffset = _inputOffset + k;
                        }
                    }
                    return false;
                }
                case '\0':
                case '!': {
                    this.__inputOffset = _inputOffset;
                    return this.__inputOffset != this.__lastMatchInputEndOffset;
                }
                case '\u001f': {
                    this.__inputOffset = _inputOffset;
                    if (!this.__match(OpCode._getNextOperator(i))) {
                        return false;
                    }
                    break;
                }
                case ' ': {
                    this.__inputOffset = _inputOffset;
                    if (this.__match(OpCode._getNextOperator(i))) {
                        return false;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    public void setMultiline(final boolean _multiline) {
        this.__multiline = _multiline;
    }
    
    public boolean isMultiline() {
        return this.__multiline;
    }
    
    char[] _toLower(char[] array) {
        final char[] array2 = new char[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        array = array2;
        for (int i = 0; i < array.length; ++i) {
            if (Character.isUpperCase(array[i])) {
                array[i] = Character.toLowerCase(array[i]);
            }
        }
        return array;
    }
    
    public boolean matchesPrefix(char[] toLower, final Pattern pattern, final int n) {
        final Perl5Pattern perl5Pattern = (Perl5Pattern)pattern;
        this.__originalInput = toLower;
        if (perl5Pattern._isCaseInsensitive) {
            toLower = this._toLower(toLower);
        }
        this.__initInterpreterGlobals(perl5Pattern, toLower, 0, toLower.length, n);
        this.__lastSuccess = this.__tryExpression(n);
        this.__lastMatchResult = null;
        return this.__lastSuccess;
    }
    
    public boolean matchesPrefix(final char[] array, final Pattern pattern) {
        return this.matchesPrefix(array, pattern, 0);
    }
    
    public boolean matchesPrefix(final String s, final Pattern pattern) {
        return this.matchesPrefix(s.toCharArray(), pattern, 0);
    }
    
    public boolean matchesPrefix(final PatternMatcherInput patternMatcherInput, final Pattern pattern) {
        final Perl5Pattern perl5Pattern = (Perl5Pattern)pattern;
        this.__originalInput = patternMatcherInput._originalBuffer;
        char[] array;
        if (perl5Pattern._isCaseInsensitive) {
            if (patternMatcherInput._toLowerBuffer == null) {
                patternMatcherInput._toLowerBuffer = this._toLower(this.__originalInput);
            }
            array = patternMatcherInput._toLowerBuffer;
        }
        else {
            array = this.__originalInput;
        }
        this.__initInterpreterGlobals(perl5Pattern, array, patternMatcherInput._beginOffset, patternMatcherInput._endOffset, patternMatcherInput._currentOffset);
        this.__lastSuccess = this.__tryExpression(patternMatcherInput._currentOffset);
        this.__lastMatchResult = null;
        return this.__lastSuccess;
    }
    
    public boolean matches(char[] toLower, final Pattern pattern) {
        final Perl5Pattern perl5Pattern = (Perl5Pattern)pattern;
        this.__originalInput = toLower;
        if (perl5Pattern._isCaseInsensitive) {
            toLower = this._toLower(toLower);
        }
        this.__initInterpreterGlobals(perl5Pattern, toLower, 0, toLower.length, 0);
        this.__lastSuccess = (this.__tryExpression(0) && this.__endMatchOffsets[0] == toLower.length);
        this.__lastMatchResult = null;
        return this.__lastSuccess;
    }
    
    public boolean matches(final String s, final Pattern pattern) {
        return this.matches(s.toCharArray(), pattern);
    }
    
    public boolean matches(final PatternMatcherInput patternMatcherInput, final Pattern pattern) {
        final Perl5Pattern perl5Pattern = (Perl5Pattern)pattern;
        this.__originalInput = patternMatcherInput._originalBuffer;
        char[] array;
        if (perl5Pattern._isCaseInsensitive) {
            if (patternMatcherInput._toLowerBuffer == null) {
                patternMatcherInput._toLowerBuffer = this._toLower(this.__originalInput);
            }
            array = patternMatcherInput._toLowerBuffer;
        }
        else {
            array = this.__originalInput;
        }
        this.__initInterpreterGlobals(perl5Pattern, array, patternMatcherInput._beginOffset, patternMatcherInput._endOffset, patternMatcherInput._beginOffset);
        this.__lastMatchResult = null;
        if (this.__tryExpression(patternMatcherInput._beginOffset) && (this.__endMatchOffsets[0] == patternMatcherInput._endOffset || patternMatcherInput.length() == 0 || patternMatcherInput._beginOffset == patternMatcherInput._endOffset)) {
            return this.__lastSuccess = true;
        }
        return this.__lastSuccess = false;
    }
    
    public boolean contains(final String s, final Pattern pattern) {
        return this.contains(s.toCharArray(), pattern);
    }
    
    public boolean contains(char[] toLower, final Pattern pattern) {
        final Perl5Pattern perl5Pattern = (Perl5Pattern)pattern;
        this.__originalInput = toLower;
        if (perl5Pattern._isCaseInsensitive) {
            toLower = this._toLower(toLower);
        }
        return this.__interpret(perl5Pattern, toLower, 0, toLower.length, 0);
    }
    
    public boolean contains(final PatternMatcherInput patternMatcherInput, final Pattern pattern) {
        if (patternMatcherInput._currentOffset > patternMatcherInput._endOffset) {
            return false;
        }
        final Perl5Pattern perl5Pattern = (Perl5Pattern)pattern;
        this.__originalInput = patternMatcherInput._originalBuffer;
        this.__originalInput = patternMatcherInput._originalBuffer;
        char[] array;
        if (perl5Pattern._isCaseInsensitive) {
            if (patternMatcherInput._toLowerBuffer == null) {
                patternMatcherInput._toLowerBuffer = this._toLower(this.__originalInput);
            }
            array = patternMatcherInput._toLowerBuffer;
        }
        else {
            array = this.__originalInput;
        }
        this.__lastMatchInputEndOffset = patternMatcherInput.getMatchEndOffset();
        final boolean _interpret = this.__interpret(perl5Pattern, array, patternMatcherInput._beginOffset, patternMatcherInput._endOffset, patternMatcherInput._currentOffset);
        if (_interpret) {
            patternMatcherInput.setCurrentOffset(this.__endMatchOffsets[0]);
            patternMatcherInput.setMatchOffsets(this.__beginMatchOffsets[0], this.__endMatchOffsets[0]);
        }
        else {
            patternMatcherInput.setCurrentOffset(patternMatcherInput._endOffset + 1);
        }
        this.__lastMatchInputEndOffset = -100;
        return _interpret;
    }
    
    public MatchResult getMatch() {
        if (!this.__lastSuccess) {
            return null;
        }
        if (this.__lastMatchResult == null) {
            this.__setLastMatchResult();
        }
        return this.__lastMatchResult;
    }
}
