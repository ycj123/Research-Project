// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.reloc.antlr.stringtemplate.language;

import org.pitest.reloc.antlr.common.CharStreamException;
import org.pitest.reloc.antlr.common.TokenStreamException;
import org.pitest.reloc.antlr.common.TokenStreamIOException;
import org.pitest.reloc.antlr.common.CharStreamIOException;
import org.pitest.reloc.antlr.common.RecognitionException;
import org.pitest.reloc.antlr.common.TokenStreamRecognitionException;
import org.pitest.reloc.antlr.common.NoViableAltForCharException;
import org.pitest.reloc.antlr.common.Token;
import org.pitest.reloc.antlr.common.ANTLRHashString;
import java.util.Hashtable;
import org.pitest.reloc.antlr.common.LexerSharedInputState;
import org.pitest.reloc.antlr.common.CharBuffer;
import java.io.Reader;
import org.pitest.reloc.antlr.common.InputBuffer;
import org.pitest.reloc.antlr.common.ByteBuffer;
import java.io.InputStream;
import org.pitest.reloc.antlr.common.collections.impl.BitSet;
import org.pitest.reloc.antlr.common.TokenStream;
import org.pitest.reloc.antlr.common.CharScanner;

public class InterfaceLexer extends CharScanner implements InterfaceParserTokenTypes, TokenStream
{
    public static final BitSet _tokenSet_0;
    
    public InterfaceLexer(final InputStream in) {
        this(new ByteBuffer(in));
    }
    
    public InterfaceLexer(final Reader in) {
        this(new CharBuffer(in));
    }
    
    public InterfaceLexer(final InputBuffer ib) {
        this(new LexerSharedInputState(ib));
    }
    
    public InterfaceLexer(final LexerSharedInputState state) {
        super(state);
        this.setCaseSensitive(this.caseSensitiveLiterals = true);
        (this.literals = new Hashtable()).put(new ANTLRHashString("interface", this), new Integer(4));
        this.literals.put(new ANTLRHashString("optional", this), new Integer(7));
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
                            this.mID(true);
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
                        case ',': {
                            this.mCOMMA(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case ';': {
                            this.mSEMI(true);
                            theRetToken = this._returnToken;
                            break;
                        }
                        case ':': {
                            this.mCOLON(true);
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
                        default: {
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
                    _ttype = this.testLiteralsTable(_ttype);
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
    
    public final void mID(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 5;
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
                case '-': {
                    this.match('-');
                    continue;
                }
                case '_': {
                    this.match('_');
                    continue;
                }
                default: {
                    if (_createToken && _token == null && _ttype != -1) {
                        _token = this.makeToken(_ttype);
                        _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
                    }
                    this._returnToken = _token;
                }
            }
        }
    }
    
    public final void mLPAREN(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 8;
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
        final int _ttype = 9;
        this.match(')');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mCOMMA(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 10;
        this.match(',');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSEMI(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 6;
        this.match(';');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mCOLON(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        final int _ttype = 11;
        this.match(':');
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mSL_COMMENT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 12;
        this.match("//");
        while (InterfaceLexer._tokenSet_0.member(this.LA(1))) {
            this.match(InterfaceLexer._tokenSet_0);
        }
        if (this.LA(1) == '\n' || this.LA(1) == '\r') {
            switch (this.LA(1)) {
                case '\r': {
                    this.match('\r');
                    break;
                }
                case '\n': {
                    break;
                }
                default: {
                    throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                }
            }
            this.match('\n');
        }
        _ttype = -1;
        this.newline();
        if (_createToken && _token == null && _ttype != -1) {
            _token = this.makeToken(_ttype);
            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
        }
        this._returnToken = _token;
    }
    
    public final void mML_COMMENT(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 13;
        this.match("/*");
        while (true) {
            while (this.LA(1) != '*' || this.LA(2) != '/') {
                if ((this.LA(1) == '\n' || this.LA(1) == '\r') && this.LA(2) >= '\0' && this.LA(2) <= '\ufffe') {
                    switch (this.LA(1)) {
                        case '\r': {
                            this.match('\r');
                            break;
                        }
                        case '\n': {
                            break;
                        }
                        default: {
                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                        }
                    }
                    this.match('\n');
                    this.newline();
                }
                else {
                    if (this.LA(1) < '\0' || this.LA(1) > '\ufffe' || this.LA(2) < '\0' || this.LA(2) > '\ufffe') {
                        this.match("*/");
                        _ttype = -1;
                        if (_createToken && _token == null && _ttype != -1) {
                            _token = this.makeToken(_ttype);
                            _token.setText(new String(this.text.getBuffer(), _begin, this.text.length() - _begin));
                        }
                        this._returnToken = _token;
                        return;
                    }
                    this.matchNot('\uffff');
                }
            }
            continue;
        }
    }
    
    public final void mWS(final boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
        Token _token = null;
        final int _begin = this.text.length();
        int _ttype = 14;
        int _cnt32 = 0;
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
                    switch (this.LA(1)) {
                        case '\r': {
                            this.match('\r');
                            break;
                        }
                        case '\n': {
                            break;
                        }
                        default: {
                            throw new NoViableAltForCharException(this.LA(1), this.getFilename(), this.getLine(), this.getColumn());
                        }
                    }
                    this.match('\n');
                    this.newline();
                    break;
                }
                default: {
                    if (_cnt32 >= 1) {
                        _ttype = -1;
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
            ++_cnt32;
        }
    }
    
    private static final long[] mk_tokenSet_0() {
        final long[] data = new long[2048];
        data[0] = -9217L;
        for (int i = 1; i <= 1022; ++i) {
            data[i] = -1L;
        }
        data[1023] = Long.MAX_VALUE;
        return data;
    }
    
    static {
        _tokenSet_0 = new BitSet(mk_tokenSet_0());
    }
}
