// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.antlr;

import java.io.IOException;
import groovyjarjarantlr.CharScanner;
import java.io.Reader;

public class UnicodeEscapingReader extends Reader
{
    private final Reader reader;
    private CharScanner lexer;
    private boolean hasNextChar;
    private int nextChar;
    private final SourceBuffer sourceBuffer;
    
    public UnicodeEscapingReader(final Reader reader, final SourceBuffer sourceBuffer) {
        this.hasNextChar = false;
        this.reader = reader;
        this.sourceBuffer = sourceBuffer;
    }
    
    public void setLexer(final CharScanner lexer) {
        this.lexer = lexer;
    }
    
    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        int c = 0;
        int count;
        for (count = 0; count < len && (c = this.read()) != -1; ++count) {
            cbuf[off + count] = (char)c;
        }
        return (count == 0 && c == -1) ? -1 : count;
    }
    
    @Override
    public int read() throws IOException {
        if (this.hasNextChar) {
            this.hasNextChar = false;
            this.write(this.nextChar);
            return this.nextChar;
        }
        int c = this.reader.read();
        if (c != 92) {
            this.write(c);
            return c;
        }
        c = this.reader.read();
        if (c != 117) {
            this.hasNextChar = true;
            this.nextChar = c;
            this.write(92);
            return 92;
        }
        do {
            c = this.reader.read();
        } while (c == 117);
        this.checkHexDigit(c);
        final StringBuffer charNum = new StringBuffer();
        charNum.append((char)c);
        for (int i = 0; i < 3; ++i) {
            c = this.reader.read();
            this.checkHexDigit(c);
            charNum.append((char)c);
        }
        final int rv = Integer.parseInt(charNum.toString(), 16);
        this.write(rv);
        return rv;
    }
    
    private void write(final int c) {
        if (this.sourceBuffer != null) {
            this.sourceBuffer.write(c);
        }
    }
    
    private void checkHexDigit(final int c) throws IOException {
        if (c >= 48 && c <= 57) {
            return;
        }
        if (c >= 97 && c <= 102) {
            return;
        }
        if (c >= 65 && c <= 70) {
            return;
        }
        this.hasNextChar = true;
        this.nextChar = c;
        throw new IOException("Did not find four digit hex character code. line: " + this.lexer.getLine() + " col:" + this.lexer.getColumn());
    }
    
    @Override
    public void close() throws IOException {
        this.reader.close();
    }
}
