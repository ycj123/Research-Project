// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.io.input;

import org.mudebug.prapr.reloc.commons.io.EndianUtils;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInput;

public class SwappedDataInputStream extends ProxyInputStream implements DataInput
{
    public SwappedDataInputStream(final InputStream input) {
        super(input);
    }
    
    public boolean readBoolean() throws IOException, EOFException {
        return 0 != this.readByte();
    }
    
    public byte readByte() throws IOException, EOFException {
        return (byte)this.in.read();
    }
    
    public char readChar() throws IOException, EOFException {
        return (char)this.readShort();
    }
    
    public double readDouble() throws IOException, EOFException {
        return EndianUtils.readSwappedDouble(this.in);
    }
    
    public float readFloat() throws IOException, EOFException {
        return EndianUtils.readSwappedFloat(this.in);
    }
    
    public void readFully(final byte[] data) throws IOException, EOFException {
        this.readFully(data, 0, data.length);
    }
    
    public void readFully(final byte[] data, final int offset, final int length) throws IOException, EOFException {
        int count;
        for (int remaining = length; remaining > 0; remaining -= count) {
            final int location = offset + (length - remaining);
            count = this.read(data, location, remaining);
            if (-1 == count) {
                throw new EOFException();
            }
        }
    }
    
    public int readInt() throws IOException, EOFException {
        return EndianUtils.readSwappedInteger(this.in);
    }
    
    public String readLine() throws IOException, EOFException {
        throw new UnsupportedOperationException("Operation not supported: readLine()");
    }
    
    public long readLong() throws IOException, EOFException {
        return EndianUtils.readSwappedLong(this.in);
    }
    
    public short readShort() throws IOException, EOFException {
        return EndianUtils.readSwappedShort(this.in);
    }
    
    public int readUnsignedByte() throws IOException, EOFException {
        return this.in.read();
    }
    
    public int readUnsignedShort() throws IOException, EOFException {
        return EndianUtils.readSwappedUnsignedShort(this.in);
    }
    
    public String readUTF() throws IOException, EOFException {
        throw new UnsupportedOperationException("Operation not supported: readUTF()");
    }
    
    public int skipBytes(final int count) throws IOException, EOFException {
        return (int)this.in.skip(count);
    }
}
