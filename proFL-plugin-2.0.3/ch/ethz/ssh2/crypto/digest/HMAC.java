// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.digest;

public final class HMAC implements Digest
{
    Digest md;
    byte[] k_xor_ipad;
    byte[] k_xor_opad;
    byte[] tmp;
    int size;
    
    public HMAC(final Digest md, byte[] key, final int size) {
        this.md = md;
        this.size = size;
        this.tmp = new byte[md.getDigestLength()];
        final int BLOCKSIZE = 64;
        this.k_xor_ipad = new byte[BLOCKSIZE];
        this.k_xor_opad = new byte[BLOCKSIZE];
        if (key.length > BLOCKSIZE) {
            md.reset();
            md.update(key);
            md.digest(this.tmp);
            key = this.tmp;
        }
        System.arraycopy(key, 0, this.k_xor_ipad, 0, key.length);
        System.arraycopy(key, 0, this.k_xor_opad, 0, key.length);
        for (int i = 0; i < BLOCKSIZE; ++i) {
            final byte[] k_xor_ipad = this.k_xor_ipad;
            final int n = i;
            k_xor_ipad[n] ^= 0x36;
            final byte[] k_xor_opad = this.k_xor_opad;
            final int n2 = i;
            k_xor_opad[n2] ^= 0x5C;
        }
        md.update(this.k_xor_ipad);
    }
    
    public final int getDigestLength() {
        return this.size;
    }
    
    public final void update(final byte b) {
        this.md.update(b);
    }
    
    public final void update(final byte[] b) {
        this.md.update(b);
    }
    
    public final void update(final byte[] b, final int off, final int len) {
        this.md.update(b, off, len);
    }
    
    public final void reset() {
        this.md.reset();
        this.md.update(this.k_xor_ipad);
    }
    
    public final void digest(final byte[] out) {
        this.digest(out, 0);
    }
    
    public final void digest(final byte[] out, final int off) {
        this.md.digest(this.tmp);
        this.md.update(this.k_xor_opad);
        this.md.update(this.tmp);
        this.md.digest(this.tmp);
        System.arraycopy(this.tmp, 0, out, off, this.size);
        this.md.update(this.k_xor_ipad);
    }
}
