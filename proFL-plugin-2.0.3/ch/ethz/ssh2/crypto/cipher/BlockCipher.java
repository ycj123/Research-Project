// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.cipher;

public interface BlockCipher
{
    void init(final boolean p0, final byte[] p1);
    
    int getBlockSize();
    
    void transformBlock(final byte[] p0, final int p1, final byte[] p2, final int p3);
}
