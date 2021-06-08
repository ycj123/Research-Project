// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.java;

import groovyjarjarantlr.CharStreamException;
import groovyjarjarantlr.TokenStreamIOException;
import groovyjarjarantlr.CharStreamIOException;
import groovyjarjarantlr.RecognitionException;
import groovyjarjarantlr.TokenStreamRecognitionException;
import groovyjarjarantlr.NoViableAltForCharException;
import groovyjarjarantlr.ANTLRHashString;
import java.util.Hashtable;
import groovyjarjarantlr.LexerSharedInputState;
import groovyjarjarantlr.CharBuffer;
import java.io.Reader;
import groovyjarjarantlr.InputBuffer;
import groovyjarjarantlr.ByteBuffer;
import java.io.InputStream;
import groovyjarjarantlr.TokenStreamException;
import groovyjarjarantlr.Token;
import groovyjarjarantlr.collections.impl.BitSet;
import groovyjarjarantlr.TokenStream;
import groovyjarjarantlr.CharScanner;

public class JavaLexer extends CharScanner implements JavaTokenTypes, TokenStream
{
    protected static final int SCS_TYPE = 3;
    protected static final int SCS_VAL = 4;
    protected static final int SCS_LIT = 8;
    protected static final int SCS_LIMIT = 16;
    protected static final int SCS_SQ_TYPE = 0;
    protected static final int SCS_TQ_TYPE = 1;
    protected static final int SCS_RE_TYPE = 2;
    protected int stringCtorState;
    protected int lastSigTokenType;
    private boolean assertEnabled;
    private boolean enumEnabled;
    private boolean whitespaceIncluded;
    protected JavaRecognizer parser;
    public static final BitSet _tokenSet_0;
    public static final BitSet _tokenSet_1;
    public static final BitSet _tokenSet_2;
    public static final BitSet _tokenSet_3;
    public static final BitSet _tokenSet_4;
    
    public void enableAssert(final boolean shouldEnable) {
        this.assertEnabled = shouldEnable;
    }
    
    public boolean isAssertEnabled() {
        return this.assertEnabled;
    }
    
    public void enableEnum(final boolean shouldEnable) {
        this.enumEnabled = shouldEnable;
    }
    
    public boolean isEnumEnabled() {
        return this.enumEnabled;
    }
    
    public TokenStream plumb() {
        return new TokenStream() {
            public Token nextToken() throws TokenStreamException {
                if (JavaLexer.this.stringCtorState >= 8) {
                    final int quoteType = JavaLexer.this.stringCtorState & 0x3;
                    JavaLexer.this.stringCtorState = 0;
                    JavaLexer.this.resetText();
                }
                final Token token = JavaLexer.this.nextToken();
                int lasttype = token.getType();
                if (JavaLexer.this.whitespaceIncluded) {
                    switch (lasttype) {
                        case 165:
                        case 166:
                        case 167: {
                            lasttype = JavaLexer.this.lastSigTokenType;
                            break;
                        }
                    }
                }
                JavaLexer.this.lastSigTokenType = lasttype;
                return token;
            }
        };
    }
    
    public JavaLexer(final InputStream in) {
        this(new ByteBuffer(in));
    }
    
    public JavaLexer(final Reader in) {
        this(new CharBuffer(in));
    }
    
    public JavaLexer(final InputBuffer ib) {
        this(new LexerSharedInputState(ib));
    }
    
    public JavaLexer(final LexerSharedInputState state) {
        super(state);
        this.stringCtorState = 0;
        this.lastSigTokenType = 1;
        this.assertEnabled = true;
        this.enumEnabled = true;
        this.whitespaceIncluded = false;
        this.setCaseSensitive(this.caseSensitiveLiterals = true);
        (this.literals = new Hashtable()).put(new ANTLRHashString("byte", this), new Integer(79));
        this.literals.put(new ANTLRHashString("public", this), new Integer(88));
        this.literals.put(new ANTLRHashString("case", this), new Integer(122));
        this.literals.put(new ANTLRHashString("short", this), new Integer(81));
        this.literals.put(new ANTLRHashString("break", this), new Integer(115));
        this.literals.put(new ANTLRHashString("while", this), new Integer(113));
        this.literals.put(new ANTLRHashString("new", this), new Integer(158));
        this.literals.put(new ANTLRHashString("instanceof", this), new Integer(145));
        this.literals.put(new ANTLRHashString("implements", this), new Integer(106));
        this.literals.put(new ANTLRHashString("synchronized", this), new Integer(93));
        this.literals.put(new ANTLRHashString("float", this), new Integer(83));
        this.literals.put(new ANTLRHashString("package", this), new Integer(61));
        this.literals.put(new ANTLRHashString("return", this), new Integer(117));
        this.literals.put(new ANTLRHashString("throw", this), new Integer(119));
        this.literals.put(new ANTLRHashString("null", this), new Integer(157));
        this.literals.put(new ANTLRHashString("threadsafe", this), new Integer(92));
        this.literals.put(new ANTLRHashString("protected", this), new Integer(89));
        this.literals.put(new ANTLRHashString("class", this), new Integer(101));
        this.literals.put(new ANTLRHashString("throws", this), new Integer(108));
        this.literals.put(new ANTLRHashString("do", this), new Integer(114));
        this.literals.put(new ANTLRHashString("strictfp", this), new Integer(40));
        this.literals.put(new ANTLRHashString("super", this), new Integer(71));
        this.literals.put(new ANTLRHashString("transient", this), new Integer(90));
        this.literals.put(new ANTLRHashString("native", this), new Integer(91));
        this.literals.put(new ANTLRHashString("interface", this), new Integer(102));
        this.literals.put(new ANTLRHashString("final", this), new Integer(38));
        this.literals.put(new ANTLRHashString("if", this), new Integer(111));
        this.literals.put(new ANTLRHashString("double", this), new Integer(85));
        this.literals.put(new ANTLRHashString("volatile", this), new Integer(94));
        this.literals.put(new ANTLRHashString("assert", this), new Integer(120));
        this.literals.put(new ANTLRHashString("catch", this), new Integer(125));
        this.literals.put(new ANTLRHashString("try", this), new Integer(123));
        this.literals.put(new ANTLRHashString("enum", this), new Integer(103));
        this.literals.put(new ANTLRHashString("int", this), new Integer(82));
        this.literals.put(new ANTLRHashString("for", this), new Integer(121));
        this.literals.put(new ANTLRHashString("extends", this), new Integer(70));
        this.literals.put(new ANTLRHashString("boolean", this), new Integer(78));
        this.literals.put(new ANTLRHashString("char", this), new Integer(80));
        this.literals.put(new ANTLRHashString("private", this), new Integer(87));
        this.literals.put(new ANTLRHashString("default", this), new Integer(105));
        this.literals.put(new ANTLRHashString("false", this), new Integer(156));
        this.literals.put(new ANTLRHashString("this", this), new Integer(107));
        this.literals.put(new ANTLRHashString("static", this), new Integer(64));
        this.literals.put(new ANTLRHashString("abstract", this), new Integer(39));
        this.literals.put(new ANTLRHashString("continue", this), new Integer(116));
        this.literals.put(new ANTLRHashString("finally", this), new Integer(124));
        this.literals.put(new ANTLRHashString("else", this), new Integer(112));
        this.literals.put(new ANTLRHashString("import", this), new Integer(63));
        this.literals.put(new ANTLRHashString("void", this), new Integer(77));
        this.literals.put(new ANTLRHashString("switch", this), new Integer(118));
        this.literals.put(new ANTLRHashString("true", this), new Integer(155));
        this.literals.put(new ANTLRHashString("long", this), new Integer(84));
    }
    
    public Token nextToken() throws TokenStreamException {
        Token theRetToken = null;
        while (true) {
            final Token _token = null;
            int _ttype = 0;
            this.resetText();
            try {
                try {
                    switch (this.LA(1)) {
                        case '?': {
                            this.mQUESTION(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '(': {
                            this.mLPAREN(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case ')': {
                            this.mRPAREN(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '[': {
                            this.mLBRACK(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case ']': {
                            this.mRBRACK(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '{': {
                            this.mLCURLY(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '}': {
                            this.mRCURLY(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case ':': {
                            this.mCOLON(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case ',': {
                            this.mCOMMA(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '~': {
                            this.mBNOT(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case ';': {
                            this.mSEMI(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '\t':
                        case '\n':
                        case '\f':
                        case '\r':
                        case ' ': {
                            this.mWS(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '\'': {
                            this.mCHAR_LITERAL(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '\"': {
                            this.mSTRING_LITERAL(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '$':
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                        case 'G':
                        case 'H':
                        case 'I':
                        case 'J':
                        case 'K':
                        case 'L':
                        case 'M':
                        case 'N':
                        case 'O':
                        case 'P':
                        case 'Q':
                        case 'R':
                        case 'S':
                        case 'T':
                        case 'U':
                        case 'V':
                        case 'W':
                        case 'X':
                        case 'Y':
                        case 'Z':
                        case '_':
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                        case 'g':
                        case 'h':
                        case 'i':
                        case 'j':
                        case 'k':
                        case 'l':
                        case 'm':
                        case 'n':
                        case 'o':
                        case 'p':
                        case 'q':
                        case 'r':
                        case 's':
                        case 't':
                        case 'u':
                        case 'v':
                        case 'w':
                        case 'x':
                        case 'y':
                        case 'z': {
                            this.mIDENT(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '.':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            this.mNUM_INT(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '@': {
                            this.mAT(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        default: {
                            if (this.LA(1) == '>' && this.LA(2) == '>' && this.LA(3) == '>' && this.LA(4) == '=') {
                                this.mBSR_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '>' && this.LA(2) == '>' && this.LA(3) == '=') {
                                this.mSR_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '>' && this.LA(2) == '>' && this.LA(3) == '>') {
                                this.mBSR(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '<' && this.LA(2) == '<' && this.LA(3) == '=') {
                                this.mSL_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '=' && this.LA(2) == '=') {
                                this.mEQUAL(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '!' && this.LA(2) == '=') {
                                this.mNOT_EQUAL(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '/' && this.LA(2) == '=') {
                                this.mDIV_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '+' && this.LA(2) == '=') {
                                this.mPLUS_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '+' && this.LA(2) == '+') {
                                this.mINC(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '-' && this.LA(2) == '=') {
                                this.mMINUS_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '-' && this.LA(2) == '-') {
                                this.mDEC(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '*' && this.LA(2) == '=') {
                                this.mSTAR_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '%' && this.LA(2) == '=') {
                                this.mMOD_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '>' && this.LA(2) == '>') {
                                this.mSR(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '>' && this.LA(2) == '=') {
                                this.mGE(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '<' && this.LA(2) == '<') {
                                this.mSL(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '<' && this.LA(2) == '=') {
                                this.mLE(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '^' && this.LA(2) == '=') {
                                this.mBXOR_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '|' && this.LA(2) == '=') {
                                this.mBOR_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '|' && this.LA(2) == '|') {
                                this.mLOR(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '&' && this.LA(2) == '=') {
                                this.mBAND_ASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '&' && this.LA(2) == '&') {
                                this.mLAND(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '/' && this.LA(2) == '/') {
                                this.mSL_COMMENT(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '/' && this.LA(2) == '*') {
                                this.mML_COMMENT(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '=') {
                                this.mASSIGN(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '!') {
                                this.mLNOT(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '/') {
                                this.mDIV(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '+') {
                                this.mPLUS(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '-') {
                                this.mMINUS(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '*') {
                                this.mSTAR(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '%') {
                                this.mMOD(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '>') {
                                this.mGT(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '<') {
                                this.mLT(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '^') {
                                this.mBXOR(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '|') {
                                this.mBOR(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '&') {
                                this.mBAND(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '\uffff') {
                                this.uponEOF();
                                this._returnToken = this.makeToken(1);
                                break;
                            }
                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                        }
                    }
                    if (this._returnToken == null) {
                        continue;
                    }
                    _ttype = this._returnToken.getType();
                    this._returnToken.setType(_ttype);
                    return this._returnToken;
                }
                catch (RecognitionException e) {
                    throw new TokenStreamRecognitionException(e);
                }
            }
            catch (CharStreamException cse) {
                if (cse instanceof CharStreamIOException) {
                    throw new TokenStreamIOException(((CharStreamIOException)cse).io);
                }
                throw new TokenStreamException(cse.getMessage());
            }
        }
    }
    
    public final void mQUESTION(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 69;
        this.match('?');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLPAREN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 96;
        this.match('(');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mRPAREN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 97;
        this.match(')');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLBRACK(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 65;
        this.match('[');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mRBRACK(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 66;
        this.match(']');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLCURLY(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 99;
        this.match('{');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mRCURLY(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 100;
        this.match('}');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mCOLON(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 110;
        this.match(':');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mCOMMA(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 73;
        this.match(',');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 98;
        this.match('=');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mEQUAL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 142;
        this.match("==");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLNOT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 154;
        this.match('!');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mBNOT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 153;
        this.match('~');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mNOT_EQUAL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 141;
        this.match("!=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mDIV(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 149;
        this.match('/');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mDIV_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 129;
        this.match("/=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mPLUS(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 147;
        this.match('+');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mPLUS_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 126;
        this.match("+=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mINC(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 151;
        this.match("++");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mMINUS(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 148;
        this.match('-');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mMINUS_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 127;
        this.match("-=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mDEC(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 152;
        this.match("--");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSTAR(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 86;
        this.match('*');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSTAR_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 128;
        this.match("*=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mMOD(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 150;
        this.match('%');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mMOD_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 130;
        this.match("%=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSR(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 75;
        this.match(">>");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSR_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 131;
        this.match(">>=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mBSR(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 76;
        this.match(">>>");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mBSR_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 132;
        this.match(">>>=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mGE(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 144;
        this.match(">=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mGT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 74;
        this.match(">");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 146;
        this.match("<<");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSL_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 133;
        this.match("<<=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLE(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 143;
        this.match("<=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 72;
        this.match('<');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mBXOR(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 140;
        this.match('^');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mBXOR_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 135;
        this.match("^=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mBOR(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 139;
        this.match('|');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mBOR_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 136;
        this.match("|=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLOR(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 137;
        this.match("||");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mBAND(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 104;
        this.match('&');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mBAND_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 134;
        this.match("&=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLAND(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 138;
        this.match("&&");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSEMI(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 62;
        this.match(';');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mWS(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 165;
        int _cnt369 = 0;
        while (true) {
            switch (this.LA(1)) {
                case ' ': {
                    this.match(' ');
                    break;
                }
                case '\t': {
                    this.match('\t');
                    break;
                }
                case '\f': {
                    this.match('\f');
                    break;
                }
                case '\n':
                case '\r': {
                    if (this.LA(1) == '\r' && this.LA(2) == '\n') {
                        this.match("\r\n");
                    }
                    else if (this.LA(1) == '\r') {
                        this.match('\r');
                    }
                    else {
                        if (this.LA(1) != '\n') {
                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                        }
                        this.match('\n');
                    }
                    if (this.inputState.guessing == 0) {
                        this.newline();
                        break;
                    }
                    break;
                }
                default: {
                    if (_cnt369 >= 1) {
                        if (this.inputState.guessing == 0) {
                            _ttype = -1;
                        }
                        if (_createToken && _token == null && _ttype != -1) {
                            _token = this.makeToken(_ttype);
                            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
                        }
                        this._returnToken = _token;
                        return;
                    }
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                }
            }
            ++_cnt369;
        }
    }
    
    public final void mSL_COMMENT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 166;
        this.match("//");
        while (JavaLexer._tokenSet_0.member(this.LA(1))) {
            this.match(JavaLexer._tokenSet_0);
        }
        switch (this.LA(1)) {
            case '\n': {
                this.match('\n');
                break;
            }
            case '\r': {
                this.match('\r');
                if (this.LA(1) == '\n') {
                    this.match('\n');
                    break;
                }
                break;
            }
            default: {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        if (this.inputState.guessing == 0) {
            _ttype = -1;
            this.newline();
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mML_COMMENT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 167;
        this.match("/*");
        while (true) {
            if (this.LA(1) == '\r' && this.LA(2) == '\n' && this.LA(3) >= '\u0003' && this.LA(3) <= '\uffff' && this.LA(4) >= '\u0003' && this.LA(4) <= '\uffff') {
                this.match('\r');
                this.match('\n');
                if (this.inputState.guessing != 0) {
                    continue;
                }
                this.newline();
            }
            else if (this.LA(1) == '*' && this.LA(2) >= '\u0003' && this.LA(2) <= '\uffff' && this.LA(3) >= '\u0003' && this.LA(3) <= '\uffff' && this.LA(2) != '/') {
                this.match('*');
            }
            else if (this.LA(1) == '\r' && this.LA(2) >= '\u0003' && this.LA(2) <= '\uffff' && this.LA(3) >= '\u0003' && this.LA(3) <= '\uffff') {
                this.match('\r');
                if (this.inputState.guessing != 0) {
                    continue;
                }
                this.newline();
            }
            else if (this.LA(1) == '\n') {
                this.match('\n');
                if (this.inputState.guessing != 0) {
                    continue;
                }
                this.newline();
            }
            else {
                if (!JavaLexer._tokenSet_1.member(this.LA(1))) {
                    break;
                }
                this.match(JavaLexer._tokenSet_1);
            }
        }
        this.match("*/");
        if (this.inputState.guessing == 0) {
            _ttype = -1;
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mCHAR_LITERAL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 160;
        this.match('\'');
        if (this.LA(1) == '\\') {
            this.mESC(false);
        }
        else {
            if (!JavaLexer._tokenSet_2.member(this.LA(1))) {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
            this.match(JavaLexer._tokenSet_2);
        }
        this.match('\'');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mESC(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 168;
        this.match('\\');
        switch (this.LA(1)) {
            case 'n': {
                this.match('n');
                break;
            }
            case 'r': {
                this.match('r');
                break;
            }
            case 't': {
                this.match('t');
                break;
            }
            case 'b': {
                this.match('b');
                break;
            }
            case 'f': {
                this.match('f');
                break;
            }
            case '\"': {
                this.match('\"');
                break;
            }
            case '\'': {
                this.match('\'');
                break;
            }
            case '\\': {
                this.match('\\');
                break;
            }
            case 'u': {
                int _cnt390 = 0;
                while (this.LA(1) == 'u') {
                    this.match('u');
                    ++_cnt390;
                }
                if (_cnt390 >= 1) {
                    this.mHEX_DIGIT(false);
                    this.mHEX_DIGIT(false);
                    this.mHEX_DIGIT(false);
                    this.mHEX_DIGIT(false);
                    break;
                }
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
            case '0':
            case '1':
            case '2':
            case '3': {
                this.matchRange('0', '3');
                if (this.LA(1) >= '0' && this.LA(1) <= '7' && JavaLexer._tokenSet_0.member(this.LA(2))) {
                    this.matchRange('0', '7');
                    if (this.LA(1) >= '0' && this.LA(1) <= '7' && JavaLexer._tokenSet_0.member(this.LA(2))) {
                        this.matchRange('0', '7');
                        break;
                    }
                    if (JavaLexer._tokenSet_0.member(this.LA(1))) {
                        break;
                    }
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                }
                else {
                    if (JavaLexer._tokenSet_0.member(this.LA(1))) {
                        break;
                    }
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                }
                break;
            }
            case '4':
            case '5':
            case '6':
            case '7': {
                this.matchRange('4', '7');
                if (this.LA(1) >= '0' && this.LA(1) <= '7' && JavaLexer._tokenSet_0.member(this.LA(2))) {
                    this.matchRange('0', '7');
                    break;
                }
                if (JavaLexer._tokenSet_0.member(this.LA(1))) {
                    break;
                }
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
            default: {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSTRING_LITERAL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 161;
        this.match('\"');
        while (true) {
            if (this.LA(1) == '\\') {
                this.mESC(false);
            }
            else {
                if (!JavaLexer._tokenSet_3.member(this.LA(1))) {
                    break;
                }
                this.match(JavaLexer._tokenSet_3);
            }
        }
        this.match('\"');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mHEX_DIGIT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 169;
        switch (this.LA(1)) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': {
                this.matchRange('0', '9');
                break;
            }
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F': {
                this.matchRange('A', 'F');
                break;
            }
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f': {
                this.matchRange('a', 'f');
                break;
            }
            default: {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mVOCAB(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 170;
        this.matchRange('\u0003', '\u00ff');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mIDENT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 67;
        switch (this.LA(1)) {
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z': {
                this.matchRange('a', 'z');
                break;
            }
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z': {
                this.matchRange('A', 'Z');
                break;
            }
            case '_': {
                this.match('_');
                break;
            }
            case '$': {
                this.match('$');
                break;
            }
            default: {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        while (true) {
            switch (this.LA(1)) {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z': {
                    this.matchRange('a', 'z');
                    continue;
                }
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z': {
                    this.matchRange('A', 'Z');
                    continue;
                }
                case '_': {
                    this.match('_');
                    continue;
                }
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    this.matchRange('0', '9');
                    continue;
                }
                case '$': {
                    this.match('$');
                    continue;
                }
                default: {
                    if (this.inputState.guessing == 0) {
                        if (this.assertEnabled && "assert".equals(new String(this.text.getBuffer(), _begin, this.text.length() - _begin))) {
                            _ttype = 120;
                        }
                        if (this.enumEnabled && "enum".equals(new String(this.text.getBuffer(), _begin, this.text.length() - _begin))) {
                            _ttype = 103;
                        }
                    }
                    _ttype = this.testLiteralsTable(_ttype);
                    if (_createToken && _token == null && _ttype != -1) {
                        _token = this.makeToken(_ttype);
                        _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
                    }
                    this._returnToken = _token;
                }
            }
        }
    }
    
    public final void mNUM_INT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 159;
        Token f1 = null;
        Token f2 = null;
        Token f3 = null;
        Token f4 = null;
        boolean isDecimal = false;
        Token t = null;
        Label_1780: {
            switch (this.LA(1)) {
                case '.': {
                    this.match('.');
                    if (this.inputState.guessing == 0) {
                        _ttype = 68;
                    }
                    switch (this.LA(1)) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            int _cnt405 = 0;
                            while (this.LA(1) >= '0' && this.LA(1) <= '9') {
                                this.matchRange('0', '9');
                                ++_cnt405;
                            }
                            if (_cnt405 < 1) {
                                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                            }
                            if (this.LA(1) == 'E' || this.LA(1) == 'e') {
                                this.mEXPONENT(false);
                            }
                            if (this.LA(1) == 'D' || this.LA(1) == 'F' || this.LA(1) == 'd' || this.LA(1) == 'f') {
                                this.mFLOAT_SUFFIX(true);
                                f1 = this._returnToken;
                                if (this.inputState.guessing == 0) {
                                    t = f1;
                                }
                            }
                            if (this.inputState.guessing != 0) {
                                break Label_1780;
                            }
                            if (t != null && t.getText().toUpperCase().indexOf(70) >= 0) {
                                _ttype = 162;
                                break Label_1780;
                            }
                            _ttype = 164;
                            break Label_1780;
                        }
                        case '.': {
                            this.match("..");
                            if (this.inputState.guessing == 0) {
                                _ttype = 109;
                                break Label_1780;
                            }
                            break Label_1780;
                        }
                        default: {
                            break Label_1780;
                        }
                    }
                    break;
                }
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    switch (this.LA(1)) {
                        case '0': {
                            this.match('0');
                            if (this.inputState.guessing == 0) {
                                isDecimal = true;
                            }
                            if (this.LA(1) != 'X' && this.LA(1) != 'x') {
                                boolean synPredMatched418 = false;
                                if (this.LA(1) >= '0' && this.LA(1) <= '9') {
                                    final int _m418 = this.mark();
                                    synPredMatched418 = true;
                                    final LexerSharedInputState inputState = this.inputState;
                                    ++inputState.guessing;
                                    try {
                                        int _cnt406 = 0;
                                        while (this.LA(1) >= '0' && this.LA(1) <= '9') {
                                            this.matchRange('0', '9');
                                            ++_cnt406;
                                        }
                                        if (_cnt406 < 1) {
                                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                                        }
                                        switch (this.LA(1)) {
                                            case '.': {
                                                this.match('.');
                                                break;
                                            }
                                            case 'E':
                                            case 'e': {
                                                this.mEXPONENT(false);
                                                break;
                                            }
                                            case 'D':
                                            case 'F':
                                            case 'd':
                                            case 'f': {
                                                this.mFLOAT_SUFFIX(false);
                                                break;
                                            }
                                            default: {
                                                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                                            }
                                        }
                                    }
                                    catch (RecognitionException pe) {
                                        synPredMatched418 = false;
                                    }
                                    this.rewind(_m418);
                                    final LexerSharedInputState inputState2 = this.inputState;
                                    --inputState2.guessing;
                                }
                                if (synPredMatched418) {
                                    int _cnt407 = 0;
                                    while (this.LA(1) >= '0' && this.LA(1) <= '9') {
                                        this.matchRange('0', '9');
                                        ++_cnt407;
                                    }
                                    if (_cnt407 < 1) {
                                        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                                    }
                                }
                                else if (this.LA(1) >= '0' && this.LA(1) <= '7') {
                                    int _cnt408 = 0;
                                    while (this.LA(1) >= '0' && this.LA(1) <= '7') {
                                        this.matchRange('0', '7');
                                        ++_cnt408;
                                    }
                                    if (_cnt408 < 1) {
                                        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                                    }
                                }
                                break;
                            }
                            switch (this.LA(1)) {
                                case 'x': {
                                    this.match('x');
                                    break;
                                }
                                case 'X': {
                                    this.match('X');
                                    break;
                                }
                                default: {
                                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                                }
                            }
                            int _cnt409 = 0;
                            while (JavaLexer._tokenSet_4.member(this.LA(1))) {
                                this.mHEX_DIGIT(false);
                                ++_cnt409;
                            }
                            if (_cnt409 >= 1) {
                                break;
                            }
                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                        }
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            this.matchRange('1', '9');
                            while (this.LA(1) >= '0' && this.LA(1) <= '9') {
                                this.matchRange('0', '9');
                            }
                            if (this.inputState.guessing == 0) {
                                isDecimal = true;
                                break;
                            }
                            break;
                        }
                        default: {
                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                        }
                    }
                    if (this.LA(1) == 'L' || this.LA(1) == 'l') {
                        switch (this.LA(1)) {
                            case 'l': {
                                this.match('l');
                                break;
                            }
                            case 'L': {
                                this.match('L');
                                break;
                            }
                            default: {
                                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                            }
                        }
                        if (this.inputState.guessing == 0) {
                            _ttype = 163;
                            break;
                        }
                        break;
                    }
                    else {
                        if ((this.LA(1) != '.' && this.LA(1) != 'D' && this.LA(1) != 'E' && this.LA(1) != 'F' && this.LA(1) != 'd' && this.LA(1) != 'e' && this.LA(1) != 'f') || !isDecimal) {
                            break;
                        }
                        switch (this.LA(1)) {
                            case '.': {
                                this.match('.');
                                while (this.LA(1) >= '0' && this.LA(1) <= '9') {
                                    this.matchRange('0', '9');
                                }
                                if (this.LA(1) == 'E' || this.LA(1) == 'e') {
                                    this.mEXPONENT(false);
                                }
                                if (this.LA(1) != 'D' && this.LA(1) != 'F' && this.LA(1) != 'd' && this.LA(1) != 'f') {
                                    break;
                                }
                                this.mFLOAT_SUFFIX(true);
                                f2 = this._returnToken;
                                if (this.inputState.guessing == 0) {
                                    t = f2;
                                    break;
                                }
                                break;
                            }
                            case 'E':
                            case 'e': {
                                this.mEXPONENT(false);
                                if (this.LA(1) != 'D' && this.LA(1) != 'F' && this.LA(1) != 'd' && this.LA(1) != 'f') {
                                    break;
                                }
                                this.mFLOAT_SUFFIX(true);
                                f3 = this._returnToken;
                                if (this.inputState.guessing == 0) {
                                    t = f3;
                                    break;
                                }
                                break;
                            }
                            case 'D':
                            case 'F':
                            case 'd':
                            case 'f': {
                                this.mFLOAT_SUFFIX(true);
                                f4 = this._returnToken;
                                if (this.inputState.guessing == 0) {
                                    t = f4;
                                    break;
                                }
                                break;
                            }
                            default: {
                                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                            }
                        }
                        if (this.inputState.guessing != 0) {
                            break;
                        }
                        if (t != null && t.getText().toUpperCase().indexOf(70) >= 0) {
                            _ttype = 162;
                            break;
                        }
                        _ttype = 164;
                        break;
                    }
                    break;
                }
                default: {
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                }
            }
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mEXPONENT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 171;
        switch (this.LA(1)) {
            case 'e': {
                this.match('e');
                break;
            }
            case 'E': {
                this.match('E');
                break;
            }
            default: {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        switch (this.LA(1)) {
            case '+': {
                this.match('+');
                break;
            }
            case '-': {
                this.match('-');
                break;
            }
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': {
                break;
            }
            default: {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        int _cnt439 = 0;
        while (this.LA(1) >= '0' && this.LA(1) <= '9') {
            this.matchRange('0', '9');
            ++_cnt439;
        }
        if (_cnt439 >= 1) {
            if (_createToken && _token == null && _ttype != -1) {
                _token = this.makeToken(_ttype);
                _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
            }
            this._returnToken = _token;
            return;
        }
        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
    }
    
    protected final void mFLOAT_SUFFIX(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 172;
        switch (this.LA(1)) {
            case 'f': {
                this.match('f');
                break;
            }
            case 'F': {
                this.match('F');
                break;
            }
            case 'd': {
                this.match('d');
                break;
            }
            case 'D': {
                this.match('D');
                break;
            }
            default: {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mAT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 95;
        this.match('@');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    private static final long[] mk_tokenSet_0() {
        final long[] data = new long[2048];
        data[0] = -9224L;
        for (int i = 1; i <= 1023; ++i) {
            data[i] = -1L;
        }
        return data;
    }
    
    private static final long[] mk_tokenSet_1() {
        final long[] data = new long[2048];
        data[0] = -4398046520328L;
        for (int i = 1; i <= 1023; ++i) {
            data[i] = -1L;
        }
        return data;
    }
    
    private static final long[] mk_tokenSet_2() {
        final long[] data = new long[2048];
        data[0] = -549755823112L;
        data[1] = -268435457L;
        for (int i = 2; i <= 1023; ++i) {
            data[i] = -1L;
        }
        return data;
    }
    
    private static final long[] mk_tokenSet_3() {
        final long[] data = new long[2048];
        data[0] = -17179878408L;
        data[1] = -268435457L;
        for (int i = 2; i <= 1023; ++i) {
            data[i] = -1L;
        }
        return data;
    }
    
    private static final long[] mk_tokenSet_4() {
        final long[] data = new long[1025];
        data[0] = 287948901175001088L;
        data[1] = 541165879422L;
        return data;
    }
    
    static {
        _tokenSet_0 = new BitSet(mk_tokenSet_0());
        _tokenSet_1 = new BitSet(mk_tokenSet_1());
        _tokenSet_2 = new BitSet(mk_tokenSet_2());
        _tokenSet_3 = new BitSet(mk_tokenSet_3());
        _tokenSet_4 = new BitSet(mk_tokenSet_4());
    }
}
