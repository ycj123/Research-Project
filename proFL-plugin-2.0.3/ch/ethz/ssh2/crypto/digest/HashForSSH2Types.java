// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.digest;

import java.math.BigInteger;

public class HashForSSH2Types
{
    Digest md;
    
    public HashForSSH2Types(final Digest md) {
        this.md = md;
    }
    
    public HashForSSH2Types(final String type) {
        if (type.equals("SHA1")) {
            this.md = new SHA1();
        }
        else {
            if (!type.equals("MD5")) {
                throw new IllegalArgumentException("Unknown algorithm " + type);
            }
            this.md = new MD5();
        }
    }
    
    public void updateByte(final byte b) {
        final byte[] tmp = { b };
        this.md.update(tmp);
    }
    
    public void updateBytes(final byte[] b) {
        this.md.update(b);
    }
    
    public void updateUINT32(final int v) {
        this.md.update((byte)(v >> 24));
        this.md.update((byte)(v >> 16));
        this.md.update((byte)(v >> 8));
        this.md.update((byte)v);
    }
    
    public void updateByteString(final byte[] b) {
        this.updateUINT32(b.length);
        this.updateBytes(b);
    }
    
    public void updateBigInt(final BigInteger b) {
        this.updateByteString(b.toByteArray());
    }
    
    public void reset() {
        this.md.reset();
    }
    
    public int getDigestLength() {
        return this.md.getDigestLength();
    }
    
    public byte[] getDigest() {
        final byte[] tmp = new byte[this.md.getDigestLength()];
        this.getDigest(tmp);
        return tmp;
    }
    
    public void getDigest(final byte[] out) {
        this.getDigest(out, 0);
    }
    
    public void getDigest(final byte[] out, final int off) {
        this.md.digest(out, off);
    }
}
