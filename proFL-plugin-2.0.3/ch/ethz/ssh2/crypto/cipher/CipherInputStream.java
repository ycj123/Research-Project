// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.cipher;

import java.io.IOException;
import java.io.InputStream;

public class CipherInputStream
{
    BlockCipher currentCipher;
    InputStream bi;
    byte[] buffer;
    byte[] enc;
    int blockSize;
    int pos;
    final int BUFF_SIZE = 2048;
    byte[] input_buffer;
    int input_buffer_pos;
    int input_buffer_size;
    
    public CipherInputStream(final BlockCipher tc, final InputStream bi) {
        this.input_buffer = new byte[2048];
        this.input_buffer_pos = 0;
        this.input_buffer_size = 0;
        this.bi = bi;
        this.changeCipher(tc);
    }
    
    private int fill_buffer() throws IOException {
        this.input_buffer_pos = 0;
        return this.input_buffer_size = this.bi.read(this.input_buffer, 0, 2048);
    }
    
    private int internal_read(final byte[] b, final int off, final int len) throws IOException {
        if (this.input_buffer_size < 0) {
            return -1;
        }
        if (this.input_buffer_pos >= this.input_buffer_size && this.fill_buffer() <= 0) {
            return -1;
        }
        final int avail = this.input_buffer_size - this.input_buffer_pos;
        final int thiscopy = (len > avail) ? avail : len;
        System.arraycopy(this.input_buffer, this.input_buffer_pos, b, off, thiscopy);
        this.input_buffer_pos += thiscopy;
        return thiscopy;
    }
    
    public void changeCipher(final BlockCipher bc) {
        this.currentCipher = bc;
        this.blockSize = bc.getBlockSize();
        this.buffer = new byte[this.blockSize];
        this.enc = new byte[this.blockSize];
        this.pos = this.blockSize;
    }
    
    private void getBlock() throws IOException {
        int len;
        for (int n = 0; n < this.blockSize; n += len) {
            len = this.internal_read(this.enc, n, this.blockSize - n);
            if (len < 0) {
                throw new IOException("Cannot read full block, EOF reached.");
            }
        }
        try {
            this.currentCipher.transformBlock(this.enc, 0, this.buffer, 0);
        }
        catch (Exception e) {
            throw new IOException("Error while decrypting block.");
        }
        this.pos = 0;
    }
    
    public int read(final byte[] dst) throws IOException {
        return this.read(dst, 0, dst.length);
    }
    
    public int read(final byte[] dst, int off, int len) throws IOException {
        int count;
        int copy;
        for (count = 0; len > 0; len -= copy, count += copy) {
            if (this.pos >= this.blockSize) {
                this.getBlock();
            }
            final int avail = this.blockSize - this.pos;
            copy = Math.min(avail, len);
            System.arraycopy(this.buffer, this.pos, dst, off, copy);
            this.pos += copy;
            off += copy;
        }
        return count;
    }
    
    public int read() throws IOException {
        if (this.pos >= this.blockSize) {
            this.getBlock();
        }
        return this.buffer[this.pos++] & 0xFF;
    }
    
    public int readPlain(final byte[] b, final int off, final int len) throws IOException {
        if (this.pos != this.blockSize) {
            throw new IOException("Cannot read plain since crypto buffer is not aligned.");
        }
        int n;
        int cnt;
        for (n = 0; n < len; n += cnt) {
            cnt = this.internal_read(b, off + n, len - n);
            if (cnt < 0) {
                throw new IOException("Cannot fill buffer, EOF reached.");
            }
        }
        return n;
    }
}
