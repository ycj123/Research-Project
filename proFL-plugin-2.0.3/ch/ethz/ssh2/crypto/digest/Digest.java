// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.digest;

public interface Digest
{
    int getDigestLength();
    
    void update(final byte p0);
    
    void update(final byte[] p0);
    
    void update(final byte[] p0, final int p1, final int p2);
    
    void reset();
    
    void digest(final byte[] p0);
    
    void digest(final byte[] p0, final int p1);
}
