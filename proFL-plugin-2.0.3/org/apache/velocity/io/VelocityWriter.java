// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.io;

import java.io.IOException;
import java.io.Writer;

public final class VelocityWriter extends Writer
{
    public static final int NO_BUFFER = 0;
    public static final int DEFAULT_BUFFER = -1;
    public static final int UNBOUNDED_BUFFER = -2;
    private int bufferSize;
    private boolean autoFlush;
    private Writer writer;
    private char[] cb;
    private int nextChar;
    private static int defaultCharBufferSize;
    
    public VelocityWriter(final Writer writer) {
        this(writer, VelocityWriter.defaultCharBufferSize, true);
    }
    
    private VelocityWriter(final int bufferSize, final boolean autoFlush) {
        this.bufferSize = bufferSize;
        this.autoFlush = autoFlush;
    }
    
    public int getBufferSize() {
        return this.bufferSize;
    }
    
    public boolean isAutoFlush() {
        return this.autoFlush;
    }
    
    public VelocityWriter(final Writer writer, final int sz, final boolean autoFlush) {
        this(sz, autoFlush);
        if (sz < 0) {
            throw new IllegalArgumentException("Buffer size <= 0");
        }
        this.writer = writer;
        this.cb = (char[])((sz == 0) ? null : new char[sz]);
        this.nextChar = 0;
    }
    
    private final void flushBuffer() throws IOException {
        if (this.bufferSize == 0) {
            return;
        }
        if (this.nextChar == 0) {
            return;
        }
        this.writer.write(this.cb, 0, this.nextChar);
        this.nextChar = 0;
    }
    
    public final void clear() {
        this.nextChar = 0;
    }
    
    private final void bufferOverflow() throws IOException {
        throw new IOException("overflow");
    }
    
    public final void flush() throws IOException {
        this.flushBuffer();
        if (this.writer != null) {
            this.writer.flush();
        }
    }
    
    public final void close() throws IOException {
        if (this.writer == null) {
            return;
        }
        this.flush();
    }
    
    public final int getRemaining() {
        return this.bufferSize - this.nextChar;
    }
    
    public final void write(final int c) throws IOException {
        if (this.bufferSize == 0) {
            this.writer.write(c);
        }
        else {
            if (this.nextChar >= this.bufferSize) {
                if (this.autoFlush) {
                    this.flushBuffer();
                }
                else {
                    this.bufferOverflow();
                }
            }
            this.cb[this.nextChar++] = (char)c;
        }
    }
    
    private final int min(final int a, final int b) {
        return (a < b) ? a : b;
    }
    
    public final void write(final char[] cbuf, final int off, final int len) throws IOException {
        if (this.bufferSize == 0) {
            this.writer.write(cbuf, off, len);
            return;
        }
        if (len == 0) {
            return;
        }
        if (len >= this.bufferSize) {
            if (this.autoFlush) {
                this.flushBuffer();
            }
            else {
                this.bufferOverflow();
            }
            this.writer.write(cbuf, off, len);
            return;
        }
        int b = off;
        final int t = off + len;
        while (b < t) {
            final int d = this.min(this.bufferSize - this.nextChar, t - b);
            System.arraycopy(cbuf, b, this.cb, this.nextChar, d);
            b += d;
            this.nextChar += d;
            if (this.nextChar >= this.bufferSize) {
                if (this.autoFlush) {
                    this.flushBuffer();
                }
                else {
                    this.bufferOverflow();
                }
            }
        }
    }
    
    public final void write(final char[] buf) throws IOException {
        this.write(buf, 0, buf.length);
    }
    
    public final void write(final String s, final int off, final int len) throws IOException {
        if (this.bufferSize == 0) {
            this.writer.write(s, off, len);
            return;
        }
        int b = off;
        final int t = off + len;
        while (b < t) {
            final int d = this.min(this.bufferSize - this.nextChar, t - b);
            s.getChars(b, b + d, this.cb, this.nextChar);
            b += d;
            this.nextChar += d;
            if (this.nextChar >= this.bufferSize) {
                if (this.autoFlush) {
                    this.flushBuffer();
                }
                else {
                    this.bufferOverflow();
                }
            }
        }
    }
    
    public final void write(final String s) throws IOException {
        if (s != null) {
            this.write(s, 0, s.length());
        }
    }
    
    public final void recycle(final Writer writer) {
        this.writer = writer;
        this.clear();
    }
    
    static {
        VelocityWriter.defaultCharBufferSize = 8192;
    }
}
