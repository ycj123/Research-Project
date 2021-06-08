// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.packets;

import ch.ethz.ssh2.util.Tokenizer;
import java.math.BigInteger;
import java.io.IOException;

public class TypesReader
{
    byte[] arr;
    int pos;
    int max;
    
    public TypesReader(final byte[] arr) {
        this.pos = 0;
        this.max = 0;
        this.arr = arr;
        this.pos = 0;
        this.max = arr.length;
    }
    
    public TypesReader(final byte[] arr, final int off) {
        this.pos = 0;
        this.max = 0;
        this.arr = arr;
        this.pos = off;
        this.max = arr.length;
        if (this.pos < 0 || this.pos > arr.length) {
            throw new IllegalArgumentException("Illegal offset.");
        }
    }
    
    public TypesReader(final byte[] arr, final int off, final int len) {
        this.pos = 0;
        this.max = 0;
        this.arr = arr;
        this.pos = off;
        this.max = off + len;
        if (this.pos < 0 || this.pos > arr.length) {
            throw new IllegalArgumentException("Illegal offset.");
        }
        if (this.max < 0 || this.max > arr.length) {
            throw new IllegalArgumentException("Illegal length.");
        }
    }
    
    public int readByte() throws IOException {
        if (this.pos >= this.max) {
            throw new IOException("Packet too short.");
        }
        return this.arr[this.pos++] & 0xFF;
    }
    
    public byte[] readBytes(final int len) throws IOException {
        if (this.pos + len > this.max) {
            throw new IOException("Packet too short.");
        }
        final byte[] res = new byte[len];
        System.arraycopy(this.arr, this.pos, res, 0, len);
        this.pos += len;
        return res;
    }
    
    public void readBytes(final byte[] dst, final int off, final int len) throws IOException {
        if (this.pos + len > this.max) {
            throw new IOException("Packet too short.");
        }
        System.arraycopy(this.arr, this.pos, dst, off, len);
        this.pos += len;
    }
    
    public boolean readBoolean() throws IOException {
        if (this.pos >= this.max) {
            throw new IOException("Packet too short.");
        }
        return this.arr[this.pos++] != 0;
    }
    
    public int readUINT32() throws IOException {
        if (this.pos + 4 > this.max) {
            throw new IOException("Packet too short.");
        }
        return (this.arr[this.pos++] & 0xFF) << 24 | (this.arr[this.pos++] & 0xFF) << 16 | (this.arr[this.pos++] & 0xFF) << 8 | (this.arr[this.pos++] & 0xFF);
    }
    
    public long readUINT64() throws IOException {
        if (this.pos + 8 > this.max) {
            throw new IOException("Packet too short.");
        }
        final long high = (this.arr[this.pos++] & 0xFF) << 24 | (this.arr[this.pos++] & 0xFF) << 16 | (this.arr[this.pos++] & 0xFF) << 8 | (this.arr[this.pos++] & 0xFF);
        final long low = (this.arr[this.pos++] & 0xFF) << 24 | (this.arr[this.pos++] & 0xFF) << 16 | (this.arr[this.pos++] & 0xFF) << 8 | (this.arr[this.pos++] & 0xFF);
        return high << 32 | (low & 0xFFFFFFFFL);
    }
    
    public BigInteger readMPINT() throws IOException {
        final byte[] raw = this.readByteString();
        BigInteger b;
        if (raw.length == 0) {
            b = BigInteger.ZERO;
        }
        else {
            b = new BigInteger(raw);
        }
        return b;
    }
    
    public byte[] readByteString() throws IOException {
        final int len = this.readUINT32();
        if (len + this.pos > this.max) {
            throw new IOException("Malformed SSH byte string.");
        }
        final byte[] res = new byte[len];
        System.arraycopy(this.arr, this.pos, res, 0, len);
        this.pos += len;
        return res;
    }
    
    public String readString(final String charsetName) throws IOException {
        final int len = this.readUINT32();
        if (len + this.pos > this.max) {
            throw new IOException("Malformed SSH string.");
        }
        final String res = (charsetName == null) ? new String(this.arr, this.pos, len) : new String(this.arr, this.pos, len, charsetName);
        this.pos += len;
        return res;
    }
    
    public String readString() throws IOException {
        final int len = this.readUINT32();
        if (len + this.pos > this.max) {
            throw new IOException("Malformed SSH string.");
        }
        final String res = new String(this.arr, this.pos, len);
        this.pos += len;
        return res;
    }
    
    public String[] readNameList() throws IOException {
        return Tokenizer.parseTokens(this.readString(), ',');
    }
    
    public int remain() {
        return this.max - this.pos;
    }
}
