// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.cipher;

public class CTRMode implements BlockCipher
{
    byte[] X;
    byte[] Xenc;
    BlockCipher bc;
    int blockSize;
    boolean doEncrypt;
    int count;
    
    public void init(final boolean forEncryption, final byte[] key) {
    }
    
    public CTRMode(final BlockCipher tc, final byte[] iv, final boolean doEnc) throws IllegalArgumentException {
        this.count = 0;
        this.bc = tc;
        this.blockSize = this.bc.getBlockSize();
        this.doEncrypt = doEnc;
        if (this.blockSize != iv.length) {
            throw new IllegalArgumentException("IV must be " + this.blockSize + " bytes long! (currently " + iv.length + ")");
        }
        this.X = new byte[this.blockSize];
        this.Xenc = new byte[this.blockSize];
        System.arraycopy(iv, 0, this.X, 0, this.blockSize);
    }
    
    public final int getBlockSize() {
        return this.blockSize;
    }
    
    public final void transformBlock(final byte[] src, final int srcoff, final byte[] dst, final int dstoff) {
        this.bc.transformBlock(this.X, 0, this.Xenc, 0);
        for (int i = 0; i < this.blockSize; ++i) {
            dst[dstoff + i] = (byte)(src[srcoff + i] ^ this.Xenc[i]);
        }
        for (int i = this.blockSize - 1; i >= 0; --i) {
            final byte[] x = this.X;
            final int n = i;
            ++x[n];
            if (this.X[i] != 0) {
                break;
            }
        }
    }
}
