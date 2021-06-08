// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.io.input;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CoderResult;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.io.Reader;
import java.io.InputStream;

public class ReaderInputStream extends InputStream
{
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private final Reader reader;
    private final CharsetEncoder encoder;
    private final CharBuffer encoderIn;
    private final ByteBuffer encoderOut;
    private CoderResult lastCoderResult;
    private boolean endOfInput;
    
    public ReaderInputStream(final Reader reader, final Charset charset, final int bufferSize) {
        this.encoderOut = ByteBuffer.allocate(128);
        this.reader = reader;
        this.encoder = charset.newEncoder();
        (this.encoderIn = CharBuffer.allocate(bufferSize)).flip();
    }
    
    public ReaderInputStream(final Reader reader, final Charset charset) {
        this(reader, charset, 1024);
    }
    
    public ReaderInputStream(final Reader reader, final String charsetName, final int bufferSize) {
        this(reader, Charset.forName(charsetName), bufferSize);
    }
    
    public ReaderInputStream(final Reader reader, final String charsetName) {
        this(reader, charsetName, 1024);
    }
    
    public ReaderInputStream(final Reader reader) {
        this(reader, Charset.defaultCharset());
    }
    
    @Override
    public int read(final byte[] b, int off, int len) throws IOException {
        int read = 0;
        while (len > 0) {
            if (this.encoderOut.position() > 0) {
                this.encoderOut.flip();
                final int c = Math.min(this.encoderOut.remaining(), len);
                this.encoderOut.get(b, off, c);
                off += c;
                len -= c;
                read += c;
                this.encoderOut.compact();
            }
            else {
                if (!this.endOfInput && (this.lastCoderResult == null || this.lastCoderResult.isUnderflow())) {
                    this.encoderIn.compact();
                    final int position = this.encoderIn.position();
                    final int c2 = this.reader.read(this.encoderIn.array(), position, this.encoderIn.remaining());
                    if (c2 == -1) {
                        this.endOfInput = true;
                    }
                    else {
                        this.encoderIn.position(position + c2);
                    }
                    this.encoderIn.flip();
                }
                this.lastCoderResult = this.encoder.encode(this.encoderIn, this.encoderOut, this.endOfInput);
                if (this.endOfInput && this.encoderOut.position() == 0) {
                    break;
                }
                continue;
            }
        }
        return (read == 0 && this.endOfInput) ? -1 : read;
    }
    
    @Override
    public int read(final byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }
    
    @Override
    public int read() throws IOException {
        final byte[] b = { 0 };
        return (this.read(b) == -1) ? -1 : (b[0] & 0xFF);
    }
    
    @Override
    public void close() throws IOException {
        this.reader.close();
    }
}
