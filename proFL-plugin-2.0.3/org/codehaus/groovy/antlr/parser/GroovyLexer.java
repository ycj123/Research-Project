// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr.parser;

import groovyjarjarantlr.MismatchedCharException;
import groovyjarjarantlr.NoViableAltForCharException;
import groovyjarjarantlr.ANTLRHashString;
import java.util.Hashtable;
import groovyjarjarantlr.LexerSharedInputState;
import groovyjarjarantlr.CharBuffer;
import java.io.Reader;
import groovyjarjarantlr.InputBuffer;
import groovyjarjarantlr.ByteBuffer;
import java.io.InputStream;
import groovyjarjarantlr.SemanticException;
import java.lang.reflect.Field;
import groovyjarjarantlr.TokenStreamException;
import groovyjarjarantlr.TokenStreamIOException;
import groovyjarjarantlr.CharStreamIOException;
import groovyjarjarantlr.TokenStreamRecognitionException;
import groovyjarjarantlr.CharStreamException;
import groovyjarjarantlr.RecognitionException;
import org.codehaus.groovy.antlr.GroovySourceToken;
import groovyjarjarantlr.Token;
import groovyjarjarantlr.collections.impl.BitSet;
import java.util.HashMap;
import java.util.ArrayList;
import groovyjarjarantlr.TokenStream;
import groovyjarjarantlr.CharScanner;

public class GroovyLexer extends CharScanner implements GroovyTokenTypes, TokenStream
{
    private boolean assertEnabled;
    private boolean enumEnabled;
    private boolean whitespaceIncluded;
    protected int parenLevel;
    protected int suppressNewline;
    protected static final int SCS_TYPE = 3;
    protected static final int SCS_VAL = 4;
    protected static final int SCS_LIT = 8;
    protected static final int SCS_LIMIT = 16;
    protected static final int SCS_SQ_TYPE = 0;
    protected static final int SCS_TQ_TYPE = 1;
    protected static final int SCS_RE_TYPE = 2;
    protected int stringCtorState;
    protected ArrayList parenLevelStack;
    protected int lastSigTokenType;
    public static boolean tracing;
    private static HashMap ttypes;
    protected GroovyRecognizer parser;
    public static final BitSet _tokenSet_0;
    public static final BitSet _tokenSet_1;
    public static final BitSet _tokenSet_2;
    public static final BitSet _tokenSet_3;
    public static final BitSet _tokenSet_4;
    public static final BitSet _tokenSet_5;
    public static final BitSet _tokenSet_6;
    public static final BitSet _tokenSet_7;
    public static final BitSet _tokenSet_8;
    public static final BitSet _tokenSet_9;
    public static final BitSet _tokenSet_10;
    public static final BitSet _tokenSet_11;
    
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
    
    public void setWhitespaceIncluded(final boolean z) {
        this.whitespaceIncluded = z;
    }
    
    public boolean isWhitespaceIncluded() {
        return this.whitespaceIncluded;
    }
    
    @Override
    public void setTokenObjectClass(final String name) {
    }
    
    @Override
    protected Token makeToken(final int t) {
        final GroovySourceToken tok = new GroovySourceToken(t);
        tok.setColumn(this.inputState.getTokenStartColumn());
        tok.setLine(this.inputState.getTokenStartLine());
        tok.setColumnLast(this.inputState.getColumn());
        tok.setLineLast(this.inputState.getLine());
        return tok;
    }
    
    protected void pushParenLevel() {
        this.parenLevelStack.add(this.parenLevel * 16 + this.stringCtorState);
        this.parenLevel = 0;
        this.stringCtorState = 0;
    }
    
    protected void popParenLevel() {
        int npl = this.parenLevelStack.size();
        if (npl == 0) {
            return;
        }
        final int i = this.parenLevelStack.remove(--npl);
        this.parenLevel = i / 16;
        this.stringCtorState = i % 16;
    }
    
    protected void restartStringCtor(final boolean expectLiteral) {
        if (this.stringCtorState != 0) {
            this.stringCtorState = (expectLiteral ? 8 : 4) + (this.stringCtorState & 0x3);
        }
    }
    
    protected boolean allowRegexpLiteral() {
        return !isExpressionEndingToken(this.lastSigTokenType);
    }
    
    protected static boolean isExpressionEndingToken(final int ttype) {
        switch (ttype) {
            case 78:
            case 79:
            case 80:
            case 81:
            case 83:
            case 84:
            case 85:
            case 89:
            case 90:
            case 91:
            case 94:
            case 95:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 123:
            case 125:
            case 126:
            case 127:
            case 128:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 145:
            case 146:
            case 147:
            case 148:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 186:
            case 189:
            case 194:
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            case 200: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected void newlineCheck(final boolean check) throws RecognitionException {
        if (check && this.suppressNewline > 0) {
            this.require(this.suppressNewline == 0, "end of line reached within a simple string 'x' or \"x\" or /x/", "for multi-line literals, use triple quotes '''x''' or \"\"\"x\"\"\"");
            this.suppressNewline = 0;
        }
        this.newline();
    }
    
    protected boolean atValidDollarEscape() throws CharStreamException {
        int k = 1;
        char lc = this.LA(k++);
        if (lc != '$') {
            return false;
        }
        lc = this.LA(k++);
        if (lc == '*') {
            lc = this.LA(k++);
        }
        return lc == '{' || (lc != '$' && Character.isJavaIdentifierStart(lc));
    }
    
    public TokenStream plumb() {
        return new TokenStream() {
            public Token nextToken() throws TokenStreamException {
                if (GroovyLexer.this.stringCtorState >= 8) {
                    final int quoteType = GroovyLexer.this.stringCtorState & 0x3;
                    GroovyLexer.this.stringCtorState = 0;
                    GroovyLexer.this.resetText();
                    try {
                        switch (quoteType) {
                            case 0: {
                                GroovyLexer.this.mSTRING_CTOR_END(true, false, false);
                                break;
                            }
                            case 1: {
                                GroovyLexer.this.mSTRING_CTOR_END(true, false, true);
                                break;
                            }
                            case 2: {
                                GroovyLexer.this.mREGEXP_CTOR_END(true, false);
                                break;
                            }
                            default: {
                                throw new AssertionError(false);
                            }
                        }
                        GroovyLexer.this.lastSigTokenType = GroovyLexer.this._returnToken.getType();
                        return GroovyLexer.this._returnToken;
                    }
                    catch (RecognitionException e) {
                        throw new TokenStreamRecognitionException(e);
                    }
                    catch (CharStreamException cse) {
                        if (cse instanceof CharStreamIOException) {
                            throw new TokenStreamIOException(((CharStreamIOException)cse).io);
                        }
                        throw new TokenStreamException(cse.getMessage());
                    }
                }
                final Token token = GroovyLexer.this.nextToken();
                int lasttype = token.getType();
                if (GroovyLexer.this.whitespaceIncluded) {
                    switch (lasttype) {
                        case 203:
                        case 204:
                        case 205:
                        case 206: {
                            lasttype = GroovyLexer.this.lastSigTokenType;
                            break;
                        }
                    }
                }
                GroovyLexer.this.lastSigTokenType = lasttype;
                return token;
            }
        };
    }
    
    @Override
    public void traceIn(final String rname) throws CharStreamException {
        if (!GroovyLexer.tracing) {
            return;
        }
        super.traceIn(rname);
    }
    
    @Override
    public void traceOut(String rname) throws CharStreamException {
        if (!GroovyLexer.tracing) {
            return;
        }
        if (this._returnToken != null) {
            rname += tokenStringOf(this._returnToken);
        }
        super.traceOut(rname);
    }
    
    private static String tokenStringOf(final Token t) {
        if (GroovyLexer.ttypes == null) {
            final HashMap map = new HashMap();
            final Field[] fields = GroovyTokenTypes.class.getDeclaredFields();
            for (int i = 0; i < fields.length; ++i) {
                if (fields[i].getType() == Integer.TYPE) {
                    try {
                        map.put(fields[i].get(null), fields[i].getName());
                    }
                    catch (IllegalAccessException ex) {}
                }
            }
            GroovyLexer.ttypes = map;
        }
        final Integer tt = t.getType();
        Object ttn = GroovyLexer.ttypes.get(tt);
        if (ttn == null) {
            ttn = "<" + tt + ">";
        }
        return "[" + ttn + ",\"" + t.getText() + "\"]";
    }
    
    private void require(final boolean z, final String problem, final String solution) throws SemanticException {
        if (!z) {
            this.parser.requireFailed(problem, solution);
        }
    }
    
    public GroovyLexer(final InputStream in) {
        this(new ByteBuffer(in));
    }
    
    public GroovyLexer(final Reader in) {
        this(new CharBuffer(in));
    }
    
    public GroovyLexer(final InputBuffer ib) {
        this(new LexerSharedInputState(ib));
    }
    
    public GroovyLexer(final LexerSharedInputState state) {
        super(state);
        this.assertEnabled = true;
        this.enumEnabled = true;
        this.whitespaceIncluded = false;
        this.setTabSize(1);
        this.parenLevel = 0;
        this.suppressNewline = 0;
        this.stringCtorState = 0;
        this.parenLevelStack = new ArrayList();
        this.lastSigTokenType = 1;
        this.setCaseSensitive(this.caseSensitiveLiterals = true);
        (this.literals = new Hashtable()).put(new ANTLRHashString("byte", this), new Integer(102));
        this.literals.put(new ANTLRHashString("public", this), new Integer(112));
        this.literals.put(new ANTLRHashString("case", this), new Integer(145));
        this.literals.put(new ANTLRHashString("short", this), new Integer(104));
        this.literals.put(new ANTLRHashString("break", this), new Integer(139));
        this.literals.put(new ANTLRHashString("while", this), new Integer(134));
        this.literals.put(new ANTLRHashString("new", this), new Integer(154));
        this.literals.put(new ANTLRHashString("instanceof", this), new Integer(153));
        this.literals.put(new ANTLRHashString("implements", this), new Integer(127));
        this.literals.put(new ANTLRHashString("synchronized", this), new Integer(117));
        this.literals.put(new ANTLRHashString("const", this), new Integer(40));
        this.literals.put(new ANTLRHashString("float", this), new Integer(106));
        this.literals.put(new ANTLRHashString("package", this), new Integer(78));
        this.literals.put(new ANTLRHashString("return", this), new Integer(138));
        this.literals.put(new ANTLRHashString("throw", this), new Integer(141));
        this.literals.put(new ANTLRHashString("null", this), new Integer(155));
        this.literals.put(new ANTLRHashString("def", this), new Integer(81));
        this.literals.put(new ANTLRHashString("threadsafe", this), new Integer(116));
        this.literals.put(new ANTLRHashString("protected", this), new Integer(113));
        this.literals.put(new ANTLRHashString("class", this), new Integer(89));
        this.literals.put(new ANTLRHashString("throws", this), new Integer(126));
        this.literals.put(new ANTLRHashString("do", this), new Integer(41));
        this.literals.put(new ANTLRHashString("strictfp", this), new Integer(42));
        this.literals.put(new ANTLRHashString("super", this), new Integer(95));
        this.literals.put(new ANTLRHashString("transient", this), new Integer(114));
        this.literals.put(new ANTLRHashString("native", this), new Integer(115));
        this.literals.put(new ANTLRHashString("interface", this), new Integer(90));
        this.literals.put(new ANTLRHashString("final", this), new Integer(37));
        this.literals.put(new ANTLRHashString("if", this), new Integer(132));
        this.literals.put(new ANTLRHashString("double", this), new Integer(108));
        this.literals.put(new ANTLRHashString("volatile", this), new Integer(118));
        this.literals.put(new ANTLRHashString("as", this), new Integer(110));
        this.literals.put(new ANTLRHashString("assert", this), new Integer(142));
        this.literals.put(new ANTLRHashString("catch", this), new Integer(148));
        this.literals.put(new ANTLRHashString("try", this), new Integer(146));
        this.literals.put(new ANTLRHashString("goto", this), new Integer(39));
        this.literals.put(new ANTLRHashString("enum", this), new Integer(91));
        this.literals.put(new ANTLRHashString("int", this), new Integer(105));
        this.literals.put(new ANTLRHashString("for", this), new Integer(136));
        this.literals.put(new ANTLRHashString("extends", this), new Integer(94));
        this.literals.put(new ANTLRHashString("boolean", this), new Integer(101));
        this.literals.put(new ANTLRHashString("char", this), new Integer(103));
        this.literals.put(new ANTLRHashString("private", this), new Integer(111));
        this.literals.put(new ANTLRHashString("default", this), new Integer(125));
        this.literals.put(new ANTLRHashString("false", this), new Integer(152));
        this.literals.put(new ANTLRHashString("this", this), new Integer(128));
        this.literals.put(new ANTLRHashString("static", this), new Integer(80));
        this.literals.put(new ANTLRHashString("abstract", this), new Integer(38));
        this.literals.put(new ANTLRHashString("continue", this), new Integer(140));
        this.literals.put(new ANTLRHashString("finally", this), new Integer(147));
        this.literals.put(new ANTLRHashString("else", this), new Integer(133));
        this.literals.put(new ANTLRHashString("import", this), new Integer(79));
        this.literals.put(new ANTLRHashString("in", this), new Integer(137));
        this.literals.put(new ANTLRHashString("void", this), new Integer(100));
        this.literals.put(new ANTLRHashString("switch", this), new Integer(135));
        this.literals.put(new ANTLRHashString("true", this), new Integer(156));
        this.literals.put(new ANTLRHashString("long", this), new Integer(107));
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
                        case '\f':
                        case ' ':
                        case '\\': {
                            this.mWS(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '\n':
                        case '\r': {
                            this.mNLS(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case '\"':
                        case '\'': {
                            this.mSTRING_LITERAL(true);
                            theRetToken = this._returnToken;
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
                            if (this.LA(1) == '<' && this.LA(2) == '=' && this.LA(3) == '>') {
                                this.mCOMPARE_TO(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '=' && this.LA(2) == '=' && this.LA(3) == '=') {
                                this.mIDENTICAL(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '!' && this.LA(2) == '=' && this.LA(3) == '=') {
                                this.mNOT_IDENTICAL(true);
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
                            if (this.LA(1) == '.' && this.LA(2) == '.' && this.LA(3) == '<') {
                                this.mRANGE_EXCLUSIVE(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '.' && this.LA(2) == '.' && this.LA(3) == '.') {
                                this.mTRIPLE_DOT(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '=' && this.LA(2) == '=' && this.LA(3) == '~') {
                                this.mREGEX_MATCH(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '*' && this.LA(2) == '*' && this.LA(3) == '=') {
                                this.mSTAR_STAR_ASSIGN(true);
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
                            if (this.LA(1) == '.' && this.LA(2) == '.') {
                                this.mRANGE_INCLUSIVE(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '*' && this.LA(2) == '.') {
                                this.mSPREAD_DOT(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '?' && this.LA(2) == '.') {
                                this.mOPTIONAL_DOT(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '?' && this.LA(2) == ':') {
                                this.mELVIS_OPERATOR(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '.' && this.LA(2) == '&') {
                                this.mMEMBER_POINTER(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '=' && this.LA(2) == '~') {
                                this.mREGEX_FIND(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '*' && this.LA(2) == '*') {
                                this.mSTAR_STAR(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '-' && this.LA(2) == '>') {
                                this.mCLOSABLE_BLOCK_OP(true);
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
                            if (this.LA(1) == '?') {
                                this.mQUESTION(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '.') {
                                this.mDOT(true);
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
                            if (this.LA(1) == '#' && this.getLine() == 1 && this.getColumn() == 1) {
                                this.mSH_COMMENT(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (this.LA(1) == '/') {
                                this.mREGEXP_LITERAL(true);
                                theRetToken = this._returnToken;
                                break;
                            }
                            if (GroovyLexer._tokenSet_0.member(this.LA(1))) {
                                this.mIDENT(true);
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
        final int _ttype = 93;
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
        final int _ttype = 88;
        this.match('(');
        if (this.inputState.guessing == 0) {
            ++this.parenLevel;
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mRPAREN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 119;
        this.match(')');
        if (this.inputState.guessing == 0) {
            --this.parenLevel;
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLBRACK(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 82;
        this.match('[');
        if (this.inputState.guessing == 0) {
            ++this.parenLevel;
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mRBRACK(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 83;
        this.match(']');
        if (this.inputState.guessing == 0) {
            --this.parenLevel;
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLCURLY(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 122;
        this.match('{');
        if (this.inputState.guessing == 0) {
            this.pushParenLevel();
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mRCURLY(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 123;
        this.match('}');
        if (this.inputState.guessing == 0) {
            this.popParenLevel();
            if (this.stringCtorState != 0) {
                this.restartStringCtor(true);
            }
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mCOLON(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 131;
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
        final int _ttype = 96;
        this.match(',');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mDOT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 87;
        this.match('.');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 120;
        this.match('=');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mCOMPARE_TO(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 180;
        this.match("<=>");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mEQUAL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 177;
        this.match("==");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mIDENTICAL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 178;
        this.match("===");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mLNOT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 192;
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
        final int _ttype = 191;
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
        final int _ttype = 176;
        this.match("!=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mNOT_IDENTICAL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 179;
        this.match("!==");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mDIV(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 187;
        this.match('/');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mDIV_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 160;
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
        final int _ttype = 143;
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
        final int _ttype = 157;
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
        final int _ttype = 186;
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
        final int _ttype = 144;
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
        final int _ttype = 158;
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
        final int _ttype = 189;
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
        final int _ttype = 109;
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
        final int _ttype = 159;
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
        final int _ttype = 188;
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
        final int _ttype = 161;
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
        final int _ttype = 98;
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
        final int _ttype = 162;
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
        final int _ttype = 99;
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
        final int _ttype = 163;
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
        final int _ttype = 182;
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
        final int _ttype = 97;
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
        final int _ttype = 183;
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
        final int _ttype = 164;
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
        final int _ttype = 181;
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
        final int _ttype = 86;
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
        final int _ttype = 173;
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
        final int _ttype = 166;
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
        final int _ttype = 172;
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
        final int _ttype = 167;
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
        final int _ttype = 170;
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
        final int _ttype = 121;
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
        final int _ttype = 165;
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
        final int _ttype = 171;
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
        final int _ttype = 124;
        this.match(';');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mDOLLAR(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 202;
        this.match('$');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mRANGE_INCLUSIVE(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 184;
        this.match("..");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mRANGE_EXCLUSIVE(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 185;
        this.match("..<");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mTRIPLE_DOT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 129;
        this.match("...");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSPREAD_DOT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 149;
        this.match("*.");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mOPTIONAL_DOT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 150;
        this.match("?.");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mELVIS_OPERATOR(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 169;
        this.match("?:");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mMEMBER_POINTER(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 151;
        this.match(".&");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mREGEX_FIND(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 174;
        this.match("=~");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mREGEX_MATCH(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 175;
        this.match("==~");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSTAR_STAR(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 190;
        this.match("**");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSTAR_STAR_ASSIGN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 168;
        this.match("**=");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mCLOSABLE_BLOCK_OP(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 130;
        this.match("->");
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mWS(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 203;
        int _cnt617 = 0;
        while (true) {
            if (this.LA(1) == '\\' && (this.LA(2) == '\n' || this.LA(2) == '\r')) {
                this.match('\\');
                this.mONE_NL(false, false);
            }
            else if (this.LA(1) == ' ') {
                this.match(' ');
            }
            else if (this.LA(1) == '\t') {
                this.match('\t');
            }
            else {
                if (this.LA(1) != '\f') {
                    break;
                }
                this.match('\f');
            }
            ++_cnt617;
        }
        if (_cnt617 >= 1) {
            if (this.inputState.guessing == 0 && !this.whitespaceIncluded) {
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
    
    protected final void mONE_NL(final boolean _createToken, final boolean check) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 204;
        if (this.LA(1) == '\r' && this.LA(2) == '\n') {
            final int _saveIndex = this.text.length();
            this.match("\r\n");
            this.text.setLength(_saveIndex);
        }
        else if (this.LA(1) == '\r') {
            final int _saveIndex = this.text.length();
            this.match('\r');
            this.text.setLength(_saveIndex);
        }
        else {
            if (this.LA(1) != '\n') {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
            final int _saveIndex = this.text.length();
            this.match('\n');
            this.text.setLength(_saveIndex);
        }
        if (this.inputState.guessing == 0) {
            this.newlineCheck(check);
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mNLS(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 201;
        this.mONE_NL(false, true);
        if ((this.LA(1) == '\t' || this.LA(1) == '\n' || this.LA(1) == '\f' || this.LA(1) == '\r' || this.LA(1) == ' ' || this.LA(1) == '/' || this.LA(1) == '\\') && !this.whitespaceIncluded) {
            int _cnt623 = 0;
        Label_0237:
            while (true) {
                switch (this.LA(1)) {
                    case '\n':
                    case '\r': {
                        this.mONE_NL(false, true);
                        break;
                    }
                    case '\t':
                    case '\f':
                    case ' ':
                    case '\\': {
                        this.mWS(false);
                        break;
                    }
                    default: {
                        if (this.LA(1) == '/' && this.LA(2) == '/') {
                            this.mSL_COMMENT(false);
                            break;
                        }
                        if (this.LA(1) == '/' && this.LA(2) == '*') {
                            this.mML_COMMENT(false);
                            break;
                        }
                        break Label_0237;
                    }
                }
                ++_cnt623;
            }
            if (_cnt623 < 1) {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        if (this.inputState.guessing == 0) {
            if (!this.whitespaceIncluded) {
                if (this.parenLevel != 0) {
                    _ttype = -1;
                }
                else {
                    this.text.setLength(_begin);
                    this.text.append("<newline>");
                }
            }
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSL_COMMENT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 205;
        this.match("//");
        while (GroovyLexer._tokenSet_1.member(this.LA(1))) {
            this.match(GroovyLexer._tokenSet_1);
        }
        if (this.inputState.guessing == 0 && !this.whitespaceIncluded) {
            _ttype = -1;
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
        int _ttype = 206;
        this.match("/*");
        while (true) {
            boolean synPredMatched635 = false;
            if (this.LA(1) == '*' && this.LA(2) >= '\0' && this.LA(2) <= '\ufffe' && this.LA(3) >= '\0' && this.LA(3) <= '\ufffe') {
                final int _m635 = this.mark();
                synPredMatched635 = true;
                final LexerSharedInputState inputState = this.inputState;
                ++inputState.guessing;
                try {
                    this.match('*');
                    this.matchNot('/');
                }
                catch (RecognitionException pe) {
                    synPredMatched635 = false;
                }
                this.rewind(_m635);
                final LexerSharedInputState inputState2 = this.inputState;
                --inputState2.guessing;
            }
            if (synPredMatched635) {
                this.match('*');
            }
            else if (this.LA(1) == '\n' || this.LA(1) == '\r') {
                this.mONE_NL(false, true);
            }
            else {
                if (!GroovyLexer._tokenSet_2.member(this.LA(1))) {
                    break;
                }
                this.match(GroovyLexer._tokenSet_2);
            }
        }
        this.match("*/");
        if (this.inputState.guessing == 0 && !this.whitespaceIncluded) {
            _ttype = -1;
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSH_COMMENT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 77;
        if (this.getLine() != 1 || this.getColumn() != 1) {
            throw new SemanticException("getLine() == 1 && getColumn() == 1");
        }
        this.match("#!");
        while (GroovyLexer._tokenSet_1.member(this.LA(1))) {
            this.match(GroovyLexer._tokenSet_1);
        }
        if (this.inputState.guessing == 0 && !this.whitespaceIncluded) {
            _ttype = -1;
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
        int _ttype = 85;
        int tt = 0;
        boolean synPredMatched640 = false;
        if (this.LA(1) == '\'' && this.LA(2) == '\'' && this.LA(3) == '\'' && this.LA(4) >= '\0' && this.LA(4) <= '\ufffe') {
            final int _m640 = this.mark();
            synPredMatched640 = true;
            final LexerSharedInputState inputState = this.inputState;
            ++inputState.guessing;
            try {
                this.match("'''");
            }
            catch (RecognitionException pe) {
                synPredMatched640 = false;
            }
            this.rewind(_m640);
            final LexerSharedInputState inputState2 = this.inputState;
            --inputState2.guessing;
        }
        if (synPredMatched640) {
            int _saveIndex = this.text.length();
            this.match("'''");
            this.text.setLength(_saveIndex);
        Label_0481:
            while (true) {
                switch (this.LA(1)) {
                    case '\\': {
                        this.mESC(false);
                        continue;
                    }
                    case '\"': {
                        this.match('\"');
                        continue;
                    }
                    case '$': {
                        this.match('$');
                        continue;
                    }
                    case '\n':
                    case '\r': {
                        this.mSTRING_NL(false, true);
                        continue;
                    }
                    default: {
                        boolean synPredMatched641 = false;
                        if (this.LA(1) == '\'' && this.LA(2) >= '\0' && this.LA(2) <= '\ufffe' && this.LA(3) >= '\0' && this.LA(3) <= '\ufffe' && this.LA(4) >= '\0' && this.LA(4) <= '\ufffe') {
                            final int _m641 = this.mark();
                            synPredMatched641 = true;
                            final LexerSharedInputState inputState3 = this.inputState;
                            ++inputState3.guessing;
                            try {
                                this.match('\'');
                                if (GroovyLexer._tokenSet_3.member(this.LA(1))) {
                                    this.matchNot('\'');
                                }
                                else {
                                    if (this.LA(1) != '\'') {
                                        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                                    }
                                    this.match('\'');
                                    this.matchNot('\'');
                                }
                            }
                            catch (RecognitionException pe2) {
                                synPredMatched641 = false;
                            }
                            this.rewind(_m641);
                            final LexerSharedInputState inputState4 = this.inputState;
                            --inputState4.guessing;
                        }
                        if (synPredMatched641) {
                            this.match('\'');
                            continue;
                        }
                        if (GroovyLexer._tokenSet_4.member(this.LA(1))) {
                            this.mSTRING_CH(false);
                            continue;
                        }
                        break Label_0481;
                    }
                }
            }
            _saveIndex = this.text.length();
            this.match("'''");
            this.text.setLength(_saveIndex);
        }
        else {
            boolean synPredMatched642 = false;
            if (this.LA(1) == '\"' && this.LA(2) == '\"' && this.LA(3) == '\"' && this.LA(4) >= '\0' && this.LA(4) <= '\ufffe') {
                final int _m642 = this.mark();
                synPredMatched642 = true;
                final LexerSharedInputState inputState5 = this.inputState;
                ++inputState5.guessing;
                try {
                    this.match("\"\"\"");
                }
                catch (RecognitionException pe2) {
                    synPredMatched642 = false;
                }
                this.rewind(_m642);
                final LexerSharedInputState inputState6 = this.inputState;
                --inputState6.guessing;
            }
            if (synPredMatched642) {
                final int _saveIndex = this.text.length();
                this.match("\"\"\"");
                this.text.setLength(_saveIndex);
                tt = this.mSTRING_CTOR_END(false, true, true);
                if (this.inputState.guessing == 0) {
                    _ttype = tt;
                }
            }
            else if (this.LA(1) == '\'' && GroovyLexer._tokenSet_1.member(this.LA(2))) {
                int _saveIndex = this.text.length();
                this.match('\'');
                this.text.setLength(_saveIndex);
                if (this.inputState.guessing == 0) {
                    ++this.suppressNewline;
                }
            Label_0828:
                while (true) {
                    switch (this.LA(1)) {
                        case '\\': {
                            this.mESC(false);
                            continue;
                        }
                        case '\"': {
                            this.match('\"');
                            continue;
                        }
                        case '$': {
                            this.match('$');
                            continue;
                        }
                        default: {
                            if (GroovyLexer._tokenSet_4.member(this.LA(1))) {
                                this.mSTRING_CH(false);
                                continue;
                            }
                            break Label_0828;
                        }
                    }
                }
                if (this.inputState.guessing == 0) {
                    --this.suppressNewline;
                }
                _saveIndex = this.text.length();
                this.match('\'');
                this.text.setLength(_saveIndex);
            }
            else {
                if (this.LA(1) != '\"' || this.LA(2) < '\0' || this.LA(2) > '\ufffe') {
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                }
                final int _saveIndex = this.text.length();
                this.match('\"');
                this.text.setLength(_saveIndex);
                if (this.inputState.guessing == 0) {
                    ++this.suppressNewline;
                }
                tt = this.mSTRING_CTOR_END(false, true, false);
                if (this.inputState.guessing == 0) {
                    _ttype = tt;
                }
            }
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mSTRING_CH(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 207;
        this.match(GroovyLexer._tokenSet_4);
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mESC(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 211;
        if (this.LA(1) == '\\' && (this.LA(2) == '\"' || this.LA(2) == '$' || this.LA(2) == '\'' || this.LA(2) == '0' || this.LA(2) == '1' || this.LA(2) == '2' || this.LA(2) == '3' || this.LA(2) == '4' || this.LA(2) == '5' || this.LA(2) == '6' || this.LA(2) == '7' || this.LA(2) == '\\' || this.LA(2) == 'b' || this.LA(2) == 'f' || this.LA(2) == 'n' || this.LA(2) == 'r' || this.LA(2) == 't' || this.LA(2) == 'u')) {
            final int _saveIndex = this.text.length();
            this.match('\\');
            this.text.setLength(_saveIndex);
            switch (this.LA(1)) {
                case 'n': {
                    this.match('n');
                    if (this.inputState.guessing == 0) {
                        this.text.setLength(_begin);
                        this.text.append("\n");
                        break;
                    }
                    break;
                }
                case 'r': {
                    this.match('r');
                    if (this.inputState.guessing == 0) {
                        this.text.setLength(_begin);
                        this.text.append("\r");
                        break;
                    }
                    break;
                }
                case 't': {
                    this.match('t');
                    if (this.inputState.guessing == 0) {
                        this.text.setLength(_begin);
                        this.text.append("\t");
                        break;
                    }
                    break;
                }
                case 'b': {
                    this.match('b');
                    if (this.inputState.guessing == 0) {
                        this.text.setLength(_begin);
                        this.text.append("\b");
                        break;
                    }
                    break;
                }
                case 'f': {
                    this.match('f');
                    if (this.inputState.guessing == 0) {
                        this.text.setLength(_begin);
                        this.text.append("\f");
                        break;
                    }
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
                case '$': {
                    this.match('$');
                    break;
                }
                case 'u': {
                    int _cnt674 = 0;
                    while (this.LA(1) == 'u') {
                        this.match('u');
                        ++_cnt674;
                    }
                    if (_cnt674 < 1) {
                        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                    }
                    if (this.inputState.guessing == 0) {
                        this.text.setLength(_begin);
                        this.text.append("");
                    }
                    this.mHEX_DIGIT(false);
                    this.mHEX_DIGIT(false);
                    this.mHEX_DIGIT(false);
                    this.mHEX_DIGIT(false);
                    if (this.inputState.guessing == 0) {
                        final char ch = (char)Integer.parseInt(new String(this.text.getBuffer(), _begin, this.text.length() - _begin), 16);
                        this.text.setLength(_begin);
                        this.text.append(ch);
                        break;
                    }
                    break;
                }
                case '0':
                case '1':
                case '2':
                case '3': {
                    this.matchRange('0', '3');
                    if (this.LA(1) >= '0' && this.LA(1) <= '7' && this.LA(2) >= '\0' && this.LA(2) <= '\ufffe') {
                        this.matchRange('0', '7');
                        if (this.LA(1) >= '0' && this.LA(1) <= '7' && this.LA(2) >= '\0' && this.LA(2) <= '\ufffe') {
                            this.matchRange('0', '7');
                        }
                        else if (this.LA(1) < '\0' || this.LA(1) > '\ufffe') {
                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                        }
                    }
                    else if (this.LA(1) < '\0' || this.LA(1) > '\ufffe') {
                        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                    }
                    if (this.inputState.guessing == 0) {
                        final char ch = (char)Integer.parseInt(new String(this.text.getBuffer(), _begin, this.text.length() - _begin), 8);
                        this.text.setLength(_begin);
                        this.text.append(ch);
                        break;
                    }
                    break;
                }
                case '4':
                case '5':
                case '6':
                case '7': {
                    this.matchRange('4', '7');
                    if (this.LA(1) >= '0' && this.LA(1) <= '7' && this.LA(2) >= '\0' && this.LA(2) <= '\ufffe') {
                        this.matchRange('0', '7');
                    }
                    else if (this.LA(1) < '\0' || this.LA(1) > '\ufffe') {
                        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                    }
                    if (this.inputState.guessing == 0) {
                        final char ch = (char)Integer.parseInt(new String(this.text.getBuffer(), _begin, this.text.length() - _begin), 8);
                        this.text.setLength(_begin);
                        this.text.append(ch);
                        break;
                    }
                    break;
                }
                default: {
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                }
            }
        }
        else {
            if (this.LA(1) != '\\' || (this.LA(2) != '\n' && this.LA(2) != '\r')) {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
            int _saveIndex = this.text.length();
            this.match('\\');
            this.text.setLength(_saveIndex);
            _saveIndex = this.text.length();
            this.mONE_NL(false, false);
            this.text.setLength(_saveIndex);
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mSTRING_NL(final boolean _createToken, final boolean allowNewline) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 212;
        if (this.inputState.guessing == 0 && !allowNewline) {
            throw new MismatchedCharException('\n', '\n', true, this);
        }
        this.mONE_NL(false, false);
        if (this.inputState.guessing == 0) {
            this.text.setLength(_begin);
            this.text.append('\n');
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final int mSTRING_CTOR_END(final boolean _createToken, final boolean fromStart, final boolean tripleQuote) throws RecognitionException, CharStreamException, TokenStreamException {
        int tt = 194;
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 194;
        boolean dollarOK = false;
    Label_0298:
        while (true) {
            switch (this.LA(1)) {
                case '\\': {
                    this.mESC(false);
                    continue;
                }
                case '\'': {
                    this.match('\'');
                    continue;
                }
                case '\n':
                case '\r': {
                    this.mSTRING_NL(false, tripleQuote);
                    continue;
                }
                default: {
                    boolean synPredMatched654 = false;
                    if (this.LA(1) == '\"' && this.LA(2) >= '\0' && this.LA(2) <= '\ufffe' && tripleQuote) {
                        final int _m654 = this.mark();
                        synPredMatched654 = true;
                        final LexerSharedInputState inputState = this.inputState;
                        ++inputState.guessing;
                        try {
                            this.match('\"');
                            if (GroovyLexer._tokenSet_5.member(this.LA(1))) {
                                this.matchNot('\"');
                            }
                            else {
                                if (this.LA(1) != '\"') {
                                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                                }
                                this.match('\"');
                                this.matchNot('\"');
                            }
                        }
                        catch (RecognitionException pe) {
                            synPredMatched654 = false;
                        }
                        this.rewind(_m654);
                        final LexerSharedInputState inputState2 = this.inputState;
                        --inputState2.guessing;
                    }
                    if (synPredMatched654) {
                        this.match('\"');
                        continue;
                    }
                    if (GroovyLexer._tokenSet_4.member(this.LA(1))) {
                        this.mSTRING_CH(false);
                        continue;
                    }
                    break Label_0298;
                }
            }
        }
        switch (this.LA(1)) {
            case '\"': {
                if (this.LA(1) == '\"' && this.LA(2) == '\"' && tripleQuote) {
                    final int _saveIndex = this.text.length();
                    this.match("\"\"\"");
                    this.text.setLength(_saveIndex);
                }
                else {
                    if (this.LA(1) != '\"' || tripleQuote) {
                        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                    }
                    final int _saveIndex = this.text.length();
                    this.match("\"");
                    this.text.setLength(_saveIndex);
                }
                if (this.inputState.guessing != 0) {
                    break;
                }
                if (fromStart) {
                    tt = 85;
                }
                if (!tripleQuote) {
                    --this.suppressNewline;
                    break;
                }
                break;
            }
            case '$': {
                if (this.inputState.guessing == 0) {
                    dollarOK = this.atValidDollarEscape();
                }
                final int _saveIndex = this.text.length();
                this.match('$');
                this.text.setLength(_saveIndex);
                if (this.inputState.guessing == 0) {
                    this.require(dollarOK, "illegal string body character after dollar sign", "either escape a literal dollar sign \"\\$5\" or bracket the value expression \"${5}\"");
                    tt = (fromStart ? 193 : 48);
                    this.stringCtorState = 4 + (tripleQuote ? 1 : 0);
                    break;
                }
                break;
            }
            default: {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        if (this.inputState.guessing == 0) {
            _ttype = tt;
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
        return tt;
    }
    
    public final void mREGEXP_LITERAL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 208;
        int tt = 0;
        if (this.LA(1) == '/' && GroovyLexer._tokenSet_6.member(this.LA(2)) && this.allowRegexpLiteral()) {
            int _saveIndex = this.text.length();
            this.match('/');
            this.text.setLength(_saveIndex);
            if (this.inputState.guessing == 0) {
                ++this.suppressNewline;
            }
            if (this.LA(1) == '$' && GroovyLexer._tokenSet_2.member(this.LA(2)) && !this.atValidDollarEscape()) {
                this.match('$');
                tt = this.mREGEXP_CTOR_END(false, true);
            }
            else if (GroovyLexer._tokenSet_7.member(this.LA(1))) {
                this.mREGEXP_SYMBOL(false);
                tt = this.mREGEXP_CTOR_END(false, true);
            }
            else {
                if (this.LA(1) != '$') {
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                }
                _saveIndex = this.text.length();
                this.match('$');
                this.text.setLength(_saveIndex);
                if (this.inputState.guessing == 0) {
                    tt = 193;
                    this.stringCtorState = 6;
                }
            }
            if (this.inputState.guessing == 0) {
                _ttype = tt;
            }
        }
        else if (this.LA(1) == '/' && this.LA(2) == '=') {
            this.mDIV_ASSIGN(false);
            if (this.inputState.guessing == 0) {
                _ttype = 160;
            }
        }
        else {
            if (this.LA(1) != '/') {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
            this.mDIV(false);
            if (this.inputState.guessing == 0) {
                _ttype = 187;
            }
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mREGEXP_SYMBOL(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 210;
        if (this.LA(1) == '\\' && this.LA(2) == '/' && GroovyLexer._tokenSet_1.member(this.LA(3))) {
            this.match('\\');
            this.match('/');
            if (this.inputState.guessing == 0) {
                this.text.setLength(_begin);
                this.text.append('/');
            }
        }
        else if (this.LA(1) == '\\' && GroovyLexer._tokenSet_1.member(this.LA(2)) && this.LA(2) != '/' && this.LA(2) != '\n' && this.LA(2) != '\r') {
            this.match('\\');
        }
        else if (this.LA(1) == '\\' && (this.LA(2) == '\n' || this.LA(2) == '\r')) {
            int _saveIndex = this.text.length();
            this.match('\\');
            this.text.setLength(_saveIndex);
            _saveIndex = this.text.length();
            this.mONE_NL(false, false);
            this.text.setLength(_saveIndex);
            if (this.inputState.guessing == 0) {
                this.text.setLength(_begin);
                this.text.append('\n');
            }
        }
        else {
            if (!GroovyLexer._tokenSet_8.member(this.LA(1))) {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
            this.match(GroovyLexer._tokenSet_8);
        }
        while (this.LA(1) == '*') {
            this.match('*');
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final int mREGEXP_CTOR_END(final boolean _createToken, final boolean fromStart) throws RecognitionException, CharStreamException, TokenStreamException {
        int tt = 194;
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 209;
        while (true) {
            if (this.LA(1) == '$' && GroovyLexer._tokenSet_2.member(this.LA(2)) && !this.atValidDollarEscape()) {
                this.match('$');
            }
            else {
                if (!GroovyLexer._tokenSet_7.member(this.LA(1))) {
                    break;
                }
                this.mREGEXP_SYMBOL(false);
            }
        }
        switch (this.LA(1)) {
            case '/': {
                final int _saveIndex = this.text.length();
                this.match('/');
                this.text.setLength(_saveIndex);
                if (this.inputState.guessing == 0) {
                    if (fromStart) {
                        tt = 85;
                    }
                    --this.suppressNewline;
                    break;
                }
                break;
            }
            case '$': {
                final int _saveIndex = this.text.length();
                this.match('$');
                this.text.setLength(_saveIndex);
                if (this.inputState.guessing == 0) {
                    tt = (fromStart ? 193 : 48);
                    this.stringCtorState = 6;
                    break;
                }
                break;
            }
            default: {
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        if (this.inputState.guessing == 0) {
            _ttype = tt;
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
        return tt;
    }
    
    protected final void mHEX_DIGIT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 213;
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
        final int _ttype = 214;
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
        int _ttype = 84;
        Label_0340: {
            if (GroovyLexer._tokenSet_0.member(this.LA(1)) && this.stringCtorState == 0) {
                if (this.LA(1) == '$') {
                    this.mDOLLAR(false);
                }
                else {
                    if (!GroovyLexer._tokenSet_9.member(this.LA(1))) {
                        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                    }
                    this.mLETTER(false);
                }
                while (true) {
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
                            this.mDIGIT(false);
                            continue;
                        }
                        case '$': {
                            this.mDOLLAR(false);
                            continue;
                        }
                        default: {
                            if (GroovyLexer._tokenSet_9.member(this.LA(1))) {
                                this.mLETTER(false);
                                continue;
                            }
                            break Label_0340;
                        }
                    }
                }
            }
            else {
                if (!GroovyLexer._tokenSet_9.member(this.LA(1))) {
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                }
                this.mLETTER(false);
                while (true) {
                    if (GroovyLexer._tokenSet_9.member(this.LA(1))) {
                        this.mLETTER(false);
                    }
                    else {
                        if (this.LA(1) < '0' || this.LA(1) > '9') {
                            break;
                        }
                        this.mDIGIT(false);
                    }
                }
            }
        }
        if (this.inputState.guessing == 0) {
            if (this.stringCtorState != 0) {
                if (this.LA(1) == '.' && this.LA(2) != '$' && Character.isJavaIdentifierStart(this.LA(2))) {
                    this.restartStringCtor(false);
                }
                else {
                    this.restartStringCtor(true);
                }
            }
            int ttype = this.testLiteralsTable(84);
            if ((ttype == 110 || ttype == 81 || ttype == 137) && (this.LA(1) == '.' || this.lastSigTokenType == 87 || this.lastSigTokenType == 78)) {
                ttype = 84;
            }
            if (ttype == 80 && this.LA(1) == '.') {
                ttype = 84;
            }
            _ttype = ttype;
            if (this.assertEnabled && "assert".equals(new String(this.text.getBuffer(), _begin, this.text.length() - _begin))) {
                _ttype = 142;
            }
            if (this.enumEnabled && "enum".equals(new String(this.text.getBuffer(), _begin, this.text.length() - _begin))) {
                _ttype = 91;
            }
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mLETTER(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 215;
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
            case '\u00c0':
            case '\u00c1':
            case '\u00c2':
            case '\u00c3':
            case '\u00c4':
            case '\u00c5':
            case '\u00c6':
            case '\u00c7':
            case '\u00c8':
            case '\u00c9':
            case '\u00ca':
            case '\u00cb':
            case '\u00cc':
            case '\u00cd':
            case '\u00ce':
            case '\u00cf':
            case '\u00d0':
            case '\u00d1':
            case '\u00d2':
            case '\u00d3':
            case '\u00d4':
            case '\u00d5':
            case '\u00d6': {
                this.matchRange('\u00c0', '\u00d6');
                break;
            }
            case '\u00d8':
            case '\u00d9':
            case '\u00da':
            case '\u00db':
            case '\u00dc':
            case '\u00dd':
            case '\u00de':
            case '\u00df':
            case '\u00e0':
            case '\u00e1':
            case '\u00e2':
            case '\u00e3':
            case '\u00e4':
            case '\u00e5':
            case '\u00e6':
            case '\u00e7':
            case '\u00e8':
            case '\u00e9':
            case '\u00ea':
            case '\u00eb':
            case '\u00ec':
            case '\u00ed':
            case '\u00ee':
            case '\u00ef':
            case '\u00f0':
            case '\u00f1':
            case '\u00f2':
            case '\u00f3':
            case '\u00f4':
            case '\u00f5':
            case '\u00f6': {
                this.matchRange('\u00d8', '\u00f6');
                break;
            }
            case '\u00f8':
            case '\u00f9':
            case '\u00fa':
            case '\u00fb':
            case '\u00fc':
            case '\u00fd':
            case '\u00fe':
            case '\u00ff': {
                this.matchRange('\u00f8', '\u00ff');
                break;
            }
            case '_': {
                this.match('_');
                break;
            }
            default: {
                if (this.LA(1) >= '\u0100' && this.LA(1) <= '\ufffe') {
                    this.matchRange('\u0100', '\ufffe');
                    break;
                }
                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
            }
        }
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    protected final void mDIGIT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 216;
        this.matchRange('0', '9');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mNUM_INT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 195;
        Token f2 = null;
        Token g2 = null;
        Token f3 = null;
        Token g3 = null;
        Token f4 = null;
        boolean isDecimal = false;
        Token t = null;
        switch (this.LA(1)) {
            case '0': {
                this.match('0');
                if (this.inputState.guessing == 0) {
                    isDecimal = true;
                }
                if (this.LA(1) != 'X' && this.LA(1) != 'x') {
                    boolean synPredMatched702 = false;
                    if (this.LA(1) >= '0' && this.LA(1) <= '9') {
                        final int _m702 = this.mark();
                        synPredMatched702 = true;
                        final LexerSharedInputState inputState = this.inputState;
                        ++inputState.guessing;
                        try {
                            int _cnt699 = 0;
                            while (this.LA(1) >= '0' && this.LA(1) <= '9') {
                                this.matchRange('0', '9');
                                ++_cnt699;
                            }
                            if (_cnt699 < 1) {
                                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                            }
                            switch (this.LA(1)) {
                                case '.': {
                                    this.match('.');
                                    this.matchRange('0', '9');
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
                            synPredMatched702 = false;
                        }
                        this.rewind(_m702);
                        final LexerSharedInputState inputState2 = this.inputState;
                        --inputState2.guessing;
                    }
                    if (synPredMatched702) {
                        int _cnt700 = 0;
                        while (this.LA(1) >= '0' && this.LA(1) <= '9') {
                            this.matchRange('0', '9');
                            ++_cnt700;
                        }
                        if (_cnt700 < 1) {
                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                        }
                    }
                    else if (this.LA(1) >= '0' && this.LA(1) <= '7') {
                        int _cnt701 = 0;
                        while (this.LA(1) >= '0' && this.LA(1) <= '7') {
                            this.matchRange('0', '7');
                            ++_cnt701;
                        }
                        if (_cnt701 < 1) {
                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                        }
                        if (this.inputState.guessing == 0) {
                            isDecimal = false;
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
                if (this.inputState.guessing == 0) {
                    isDecimal = false;
                }
                int _cnt702 = 0;
                while (GroovyLexer._tokenSet_10.member(this.LA(1))) {
                    this.mHEX_DIGIT(false);
                    ++_cnt702;
                }
                if (_cnt702 >= 1) {
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
        switch (this.LA(1)) {
            case 'L':
            case 'l': {
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
                    _ttype = 197;
                    break;
                }
                break;
            }
            case 'I':
            case 'i': {
                switch (this.LA(1)) {
                    case 'i': {
                        this.match('i');
                        break;
                    }
                    case 'I': {
                        this.match('I');
                        break;
                    }
                    default: {
                        throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                    }
                }
                if (this.inputState.guessing == 0) {
                    _ttype = 195;
                    break;
                }
                break;
            }
            case 'G':
            case 'g': {
                this.mBIG_SUFFIX(false);
                if (this.inputState.guessing == 0) {
                    _ttype = 199;
                    break;
                }
                break;
            }
            default: {
                boolean synPredMatched703 = false;
                if ((this.LA(1) == '.' || this.LA(1) == 'D' || this.LA(1) == 'E' || this.LA(1) == 'F' || this.LA(1) == 'd' || this.LA(1) == 'e' || this.LA(1) == 'f') && isDecimal) {
                    final int _m703 = this.mark();
                    synPredMatched703 = true;
                    final LexerSharedInputState inputState3 = this.inputState;
                    ++inputState3.guessing;
                    try {
                        if (GroovyLexer._tokenSet_11.member(this.LA(1))) {
                            this.matchNot('.');
                        }
                        else {
                            if (this.LA(1) != '.') {
                                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                            }
                            this.match('.');
                            this.matchRange('0', '9');
                        }
                    }
                    catch (RecognitionException pe) {
                        synPredMatched703 = false;
                    }
                    this.rewind(_m703);
                    final LexerSharedInputState inputState4 = this.inputState;
                    --inputState4.guessing;
                }
                if (!synPredMatched703) {
                    break;
                }
                Label_1800: {
                    switch (this.LA(1)) {
                        case '.': {
                            this.match('.');
                            int _cnt703 = 0;
                            while (this.LA(1) >= '0' && this.LA(1) <= '9') {
                                this.matchRange('0', '9');
                                ++_cnt703;
                            }
                            if (_cnt703 < 1) {
                                throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                            }
                            if (this.LA(1) == 'E' || this.LA(1) == 'e') {
                                this.mEXPONENT(false);
                            }
                            switch (this.LA(1)) {
                                case 'D':
                                case 'F':
                                case 'd':
                                case 'f': {
                                    this.mFLOAT_SUFFIX(true);
                                    f2 = this._returnToken;
                                    if (this.inputState.guessing == 0) {
                                        t = f2;
                                        break Label_1800;
                                    }
                                    break Label_1800;
                                }
                                case 'G':
                                case 'g': {
                                    this.mBIG_SUFFIX(true);
                                    g2 = this._returnToken;
                                    if (this.inputState.guessing == 0) {
                                        t = g2;
                                        break Label_1800;
                                    }
                                    break Label_1800;
                                }
                                default: {
                                    break Label_1800;
                                }
                            }
                            break;
                        }
                        case 'E':
                        case 'e': {
                            this.mEXPONENT(false);
                            switch (this.LA(1)) {
                                case 'D':
                                case 'F':
                                case 'd':
                                case 'f': {
                                    this.mFLOAT_SUFFIX(true);
                                    f3 = this._returnToken;
                                    if (this.inputState.guessing == 0) {
                                        t = f3;
                                        break Label_1800;
                                    }
                                    break Label_1800;
                                }
                                case 'G':
                                case 'g': {
                                    this.mBIG_SUFFIX(true);
                                    g3 = this._returnToken;
                                    if (this.inputState.guessing == 0) {
                                        t = g3;
                                        break Label_1800;
                                    }
                                    break Label_1800;
                                }
                                default: {
                                    break Label_1800;
                                }
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
                }
                if (this.inputState.guessing != 0) {
                    break;
                }
                final String txt = (t == null) ? "" : t.getText().toUpperCase();
                if (txt.indexOf(70) >= 0) {
                    _ttype = 196;
                    break;
                }
                if (txt.indexOf(71) >= 0) {
                    _ttype = 200;
                    break;
                }
                _ttype = 198;
                break;
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
        final int _ttype = 217;
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
        int _cnt727 = 0;
        while (this.LA(1) >= '0' && this.LA(1) <= '9') {
            this.matchRange('0', '9');
            ++_cnt727;
        }
        if (_cnt727 >= 1) {
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
        final int _ttype = 218;
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
    
    protected final void mBIG_SUFFIX(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 219;
        switch (this.LA(1)) {
            case 'g': {
                this.match('g');
                break;
            }
            case 'G': {
                this.match('G');
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
        final int _ttype = 92;
        this.match('@');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    private static final long[] mk_tokenSet_0() {
        final long[] data = new long[2560];
        data[0] = 68719476736L;
        data[1] = 576460745995190270L;
        data[3] = -36028797027352577L;
        for (int i = 4; i <= 1022; ++i) {
            data[i] = -1L;
        }
        data[1023] = Long.MAX_VALUE;
        return data;
    }
    
    private static final long[] mk_tokenSet_1() {
        final long[] data = new long[2048];
        data[0] = -9217L;
        for (int i = 1; i <= 1022; ++i) {
            data[i] = -1L;
        }
        data[1023] = Long.MAX_VALUE;
        return data;
    }
    
    private static final long[] mk_tokenSet_2() {
        final long[] data = new long[2048];
        data[0] = -4398046520321L;
        for (int i = 1; i <= 1022; ++i) {
            data[i] = -1L;
        }
        data[1023] = Long.MAX_VALUE;
        return data;
    }
    
    private static final long[] mk_tokenSet_3() {
        final long[] data = new long[2048];
        data[0] = -549755813889L;
        for (int i = 1; i <= 1023; ++i) {
            data[i] = -1L;
        }
        return data;
    }
    
    private static final long[] mk_tokenSet_4() {
        final long[] data = new long[2048];
        data[0] = -635655169025L;
        data[1] = -268435457L;
        for (int i = 2; i <= 1022; ++i) {
            data[i] = -1L;
        }
        data[1023] = Long.MAX_VALUE;
        return data;
    }
    
    private static final long[] mk_tokenSet_5() {
        final long[] data = new long[2048];
        data[0] = -17179869185L;
        for (int i = 1; i <= 1023; ++i) {
            data[i] = -1L;
        }
        return data;
    }
    
    private static final long[] mk_tokenSet_6() {
        final long[] data = new long[2048];
        data[0] = -145135534875649L;
        for (int i = 1; i <= 1022; ++i) {
            data[i] = -1L;
        }
        data[1023] = Long.MAX_VALUE;
        return data;
    }
    
    private static final long[] mk_tokenSet_7() {
        final long[] data = new long[2048];
        data[0] = -145204254352385L;
        for (int i = 1; i <= 1022; ++i) {
            data[i] = -1L;
        }
        data[1023] = Long.MAX_VALUE;
        return data;
    }
    
    private static final long[] mk_tokenSet_8() {
        final long[] data = new long[2048];
        data[0] = -145204254352385L;
        data[1] = -268435457L;
        for (int i = 2; i <= 1022; ++i) {
            data[i] = -1L;
        }
        data[1023] = Long.MAX_VALUE;
        return data;
    }
    
    private static final long[] mk_tokenSet_9() {
        final long[] data = new long[2560];
        data[1] = 576460745995190270L;
        data[3] = -36028797027352577L;
        for (int i = 4; i <= 1022; ++i) {
            data[i] = -1L;
        }
        data[1023] = Long.MAX_VALUE;
        return data;
    }
    
    private static final long[] mk_tokenSet_10() {
        final long[] data = new long[1025];
        data[0] = 287948901175001088L;
        data[1] = 541165879422L;
        return data;
    }
    
    private static final long[] mk_tokenSet_11() {
        final long[] data = new long[2048];
        data[0] = -70368744177665L;
        for (int i = 1; i <= 1023; ++i) {
            data[i] = -1L;
        }
        return data;
    }
    
    static {
        GroovyLexer.tracing = false;
        _tokenSet_0 = new BitSet(mk_tokenSet_0());
        _tokenSet_1 = new BitSet(mk_tokenSet_1());
        _tokenSet_2 = new BitSet(mk_tokenSet_2());
        _tokenSet_3 = new BitSet(mk_tokenSet_3());
        _tokenSet_4 = new BitSet(mk_tokenSet_4());
        _tokenSet_5 = new BitSet(mk_tokenSet_5());
        _tokenSet_6 = new BitSet(mk_tokenSet_6());
        _tokenSet_7 = new BitSet(mk_tokenSet_7());
        _tokenSet_8 = new BitSet(mk_tokenSet_8());
        _tokenSet_9 = new BitSet(mk_tokenSet_9());
        _tokenSet_10 = new BitSet(mk_tokenSet_10());
        _tokenSet_11 = new BitSet(mk_tokenSet_11());
    }
}
