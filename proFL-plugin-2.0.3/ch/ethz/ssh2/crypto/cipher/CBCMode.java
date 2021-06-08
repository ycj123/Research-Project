// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.cipher;

public class CBCMode implements BlockCipher
{
    BlockCipher tc;
    int blockSize;
    boolean doEncrypt;
    byte[] cbc_vector;
    byte[] tmp_vector;
    
    public void init(final boolean forEncryption, final byte[] key) {
    }
    
    public CBCMode(final BlockCipher tc, final byte[] iv, final boolean doEncrypt) throws IllegalArgumentException {
        this.tc = tc;
        this.blockSize = tc.getBlockSize();
        this.doEncrypt = doEncrypt;
        if (this.blockSize != iv.length) {
            throw new IllegalArgumentException("IV must be " + this.blockSize + " bytes long! (currently " + iv.length + ")");
        }
        this.cbc_vector = new byte[this.blockSize];
        this.tmp_vector = new byte[this.blockSize];
        System.arraycopy(iv, 0, this.cbc_vector, 0, this.blockSize);
    }
    
    public int getBlockSize() {
        return this.blockSize;
    }
    
    private void encryptBlock(final byte[] src, final int srcoff, final byte[] dst, final int dstoff) {
        for (int i = 0; i < this.blockSize; ++i) {
            final byte[] cbc_vector = this.cbc_vector;
            final int n = i;
            cbc_vector[n] ^= src[srcoff + i];
        }
        this.tc.transformBlock(this.cbc_vector, 0, dst, dstoff);
        System.arraycopy(dst, dstoff, this.cbc_vector, 0, this.blockSize);
    }
    
    private void decryptBlock(final byte[] src, final int srcoff, final byte[] dst, final int dstoff) {
        System.arraycopy(src, srcoff, this.tmp_vector, 0, this.blockSize);
        this.tc.transformBlock(src, srcoff, dst, dstoff);
        for (int i = 0; i < this.blockSize; ++i) {
            final int n = dstoff + i;
            dst[n] ^= this.cbc_vector[i];
        }
        final byte[] swap = this.cbc_vector;
        this.cbc_vector = this.tmp_vector;
        this.tmp_vector = swap;
    }
    
    public void transformBlock(final byte[] src, final int srcoff, final byte[] dst, final int dstoff) {
        if (this.doEncrypt) {
            this.encryptBlock(src, srcoff, dst, dstoff);
        }
        else {
            this.decryptBlock(src, srcoff, dst, dstoff);
        }
    }
}
