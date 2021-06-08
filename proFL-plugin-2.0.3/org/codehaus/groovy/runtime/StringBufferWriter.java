// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.io.IOException;
import java.io.Writer;

public class StringBufferWriter extends Writer
{
    private StringBuffer buffer;
    
    public StringBufferWriter(final StringBuffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void write(final int c) {
        this.buffer.append((char)c);
    }
    
    @Override
    public void write(final char[] text, final int offset, final int length) {
        if (offset < 0 || offset > text.length || length < 0 || offset + length > text.length || offset + length < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (length == 0) {
            return;
        }
        this.buffer.append(text, offset, length);
    }
    
    @Override
    public void write(final String text) {
        this.buffer.append(text);
    }
    
    @Override
    public void write(final String text, final int offset, final int length) {
        this.buffer.append(text.substring(offset, offset + length));
    }
    
    @Override
    public String toString() {
        return this.buffer.toString();
    }
    
    @Override
    public void flush() {
    }
    
    @Override
    public void close() throws IOException {
    }
}
