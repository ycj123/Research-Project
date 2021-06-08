// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.text.awk;

import java.io.IOException;
import java.io.Reader;

public final class AwkStreamInput
{
    static final int _DEFAULT_BUFFER_INCREMENT = 2048;
    private Reader __searchStream;
    private int __bufferIncrementUnit;
    boolean _endOfStreamReached;
    int _bufferSize;
    int _bufferOffset;
    int _currentOffset;
    char[] _buffer;
    
    AwkStreamInput() {
        this._currentOffset = 0;
    }
    
    public AwkStreamInput(final Reader _searchStream, final int _bufferIncrementUnit) {
        this.__searchStream = _searchStream;
        this.__bufferIncrementUnit = _bufferIncrementUnit;
        this._buffer = new char[_bufferIncrementUnit];
        final int bufferOffset = 0;
        this._currentOffset = bufferOffset;
        this._bufferSize = bufferOffset;
        this._bufferOffset = bufferOffset;
        this._endOfStreamReached = false;
    }
    
    public AwkStreamInput(final Reader reader) {
        this(reader, 2048);
    }
    
    int _reallocate(final int n) throws IOException {
        if (this._endOfStreamReached) {
            return this._bufferSize;
        }
        final int n2 = this._bufferSize - n;
        final char[] buffer = new char[n2 + this.__bufferIncrementUnit];
        final int read = this.__searchStream.read(buffer, n2, this.__bufferIncrementUnit);
        if (read > 0) {
            this._bufferOffset += n;
            this._bufferSize = n2 + read;
            System.arraycopy(this._buffer, n, buffer, 0, n2);
            this._buffer = buffer;
            return n2;
        }
        this._endOfStreamReached = true;
        if (read == 0) {
            throw new IOException("read from input stream returned 0 bytes.");
        }
        return this._bufferSize;
    }
    
    boolean read() throws IOException {
        this._bufferOffset += this._bufferSize;
        this._bufferSize = this.__searchStream.read(this._buffer);
        this._endOfStreamReached = (this._bufferSize == -1);
        return !this._endOfStreamReached;
    }
    
    public boolean endOfStream() {
        return this._endOfStreamReached;
    }
}
