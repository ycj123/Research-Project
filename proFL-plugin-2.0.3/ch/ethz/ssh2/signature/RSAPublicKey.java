// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.signature;

import java.math.BigInteger;

public class RSAPublicKey
{
    BigInteger e;
    BigInteger n;
    
    public RSAPublicKey(final BigInteger e, final BigInteger n) {
        this.e = e;
        this.n = n;
    }
    
    public BigInteger getE() {
        return this.e;
    }
    
    public BigInteger getN() {
        return this.n;
    }
}
