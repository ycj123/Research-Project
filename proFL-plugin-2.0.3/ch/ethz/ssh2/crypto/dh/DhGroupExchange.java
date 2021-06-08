// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.dh;

import ch.ethz.ssh2.crypto.digest.HashForSSH2Types;
import ch.ethz.ssh2.DHGexParameters;
import java.util.Random;
import java.security.SecureRandom;
import java.math.BigInteger;

public class DhGroupExchange
{
    private BigInteger p;
    private BigInteger g;
    private BigInteger e;
    private BigInteger x;
    private BigInteger f;
    private BigInteger k;
    
    public DhGroupExchange(final BigInteger p, final BigInteger g) {
        this.p = p;
        this.g = g;
    }
    
    public void init(final SecureRandom rnd) {
        this.k = null;
        this.x = new BigInteger(this.p.bitLength() - 1, rnd);
        this.e = this.g.modPow(this.x, this.p);
    }
    
    public BigInteger getE() {
        if (this.e == null) {
            throw new IllegalStateException("Not initialized!");
        }
        return this.e;
    }
    
    public BigInteger getK() {
        if (this.k == null) {
            throw new IllegalStateException("Shared secret not yet known, need f first!");
        }
        return this.k;
    }
    
    public void setF(final BigInteger f) {
        if (this.e == null) {
            throw new IllegalStateException("Not initialized!");
        }
        final BigInteger zero = BigInteger.valueOf(0L);
        if (zero.compareTo(f) >= 0 || this.p.compareTo(f) <= 0) {
            throw new IllegalArgumentException("Invalid f specified!");
        }
        this.f = f;
        this.k = f.modPow(this.x, this.p);
    }
    
    public byte[] calculateH(final byte[] clientversion, final byte[] serverversion, final byte[] clientKexPayload, final byte[] serverKexPayload, final byte[] hostKey, final DHGexParameters para) {
        final HashForSSH2Types hash = new HashForSSH2Types("SHA1");
        hash.updateByteString(clientversion);
        hash.updateByteString(serverversion);
        hash.updateByteString(clientKexPayload);
        hash.updateByteString(serverKexPayload);
        hash.updateByteString(hostKey);
        if (para.getMin_group_len() > 0) {
            hash.updateUINT32(para.getMin_group_len());
        }
        hash.updateUINT32(para.getPref_group_len());
        if (para.getMax_group_len() > 0) {
            hash.updateUINT32(para.getMax_group_len());
        }
        hash.updateBigInt(this.p);
        hash.updateBigInt(this.g);
        hash.updateBigInt(this.e);
        hash.updateBigInt(this.f);
        hash.updateBigInt(this.k);
        return hash.getDigest();
    }
}
