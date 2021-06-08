// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto;

import java.math.BigInteger;
import java.io.IOException;

public class SimpleDERReader
{
    byte[] buffer;
    int pos;
    int count;
    
    public SimpleDERReader(final byte[] b) {
        this.resetInput(b);
    }
    
    public SimpleDERReader(final byte[] b, final int off, final int len) {
        this.resetInput(b, off, len);
    }
    
    public void resetInput(final byte[] b) {
        this.resetInput(b, 0, b.length);
    }
    
    public void resetInput(final byte[] b, final int off, final int len) {
        this.buffer = b;
        this.pos = off;
        this.count = len;
    }
    
    private byte readByte() throws IOException {
        if (this.count <= 0) {
            throw new IOException("DER byte array: out of data");
        }
        --this.count;
        return this.buffer[this.pos++];
    }
    
    private byte[] readBytes(final int len) throws IOException {
        if (len > this.count) {
            throw new IOException("DER byte array: out of data");
        }
        final byte[] b = new byte[len];
        System.arraycopy(this.buffer, this.pos, b, 0, len);
        this.pos += len;
        this.count -= len;
        return b;
    }
    
    public int available() {
        return this.count;
    }
    
    private int readLength() throws IOException {
        int len = this.readByte() & 0xFF;
        if ((len & 0x80) == 0x0) {
            return len;
        }
        int remain = len & 0x7F;
        if (remain == 0) {
            return -1;
        }
        len = 0;
        while (remain > 0) {
            len <<= 8;
            len |= (this.readByte() & 0xFF);
            --remain;
        }
        return len;
    }
    
    public int ignoreNextObject() throws IOException {
        final int type = this.readByte() & 0xFF;
        final int len = this.readLength();
        if (len < 0 || len > this.available()) {
            throw new IOException("Illegal len in DER object (" + len + ")");
        }
        this.readBytes(len);
        return type;
    }
    
    public BigInteger readInt() throws IOException {
        final int type = this.readByte() & 0xFF;
        if (type != 2) {
            throw new IOException("Expected DER Integer, but found type " + type);
        }
        final int len = this.readLength();
        if (len < 0 || len > this.available()) {
            throw new IOException("Illegal len in DER object (" + len + ")");
        }
        final byte[] b = this.readBytes(len);
        final BigInteger bi = new BigInteger(b);
        return bi;
    }
    
    public byte[] readSequenceAsByteArray() throws IOException {
        final int type = this.readByte() & 0xFF;
        if (type != 48) {
            throw new IOException("Expected DER Sequence, but found type " + type);
        }
        final int len = this.readLength();
        if (len < 0 || len > this.available()) {
            throw new IOException("Illegal len in DER object (" + len + ")");
        }
        final byte[] b = this.readBytes(len);
        return b;
    }
    
    public byte[] readOctetString() throws IOException {
        final int type = this.readByte() & 0xFF;
        if (type != 4) {
            throw new IOException("Expected DER Octetstring, but found type " + type);
        }
        final int len = this.readLength();
        if (len < 0 || len > this.available()) {
            throw new IOException("Illegal len in DER object (" + len + ")");
        }
        final byte[] b = this.readBytes(len);
        return b;
    }
}
