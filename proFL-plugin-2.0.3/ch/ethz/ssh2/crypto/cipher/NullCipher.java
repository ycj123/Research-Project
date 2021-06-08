// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.cipher;

public class NullCipher implements BlockCipher
{
    private int blockSize;
    
    public NullCipher() {
        this.blockSize = 8;
    }
    
    public NullCipher(final int blockSize) {
        this.blockSize = 8;
        this.blockSize = blockSize;
    }
    
    public void init(final boolean forEncryption, final byte[] key) {
    }
    
    public int getBlockSize() {
        return this.blockSize;
    }
    
    public void transformBlock(final byte[] src, final int srcoff, final byte[] dst, final int dstoff) {
        System.arraycopy(src, srcoff, dst, dstoff, this.blockSize);
    }
}
