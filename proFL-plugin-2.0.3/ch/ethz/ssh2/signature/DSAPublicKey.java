// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.signature;

import java.math.BigInteger;

public class DSAPublicKey
{
    private BigInteger p;
    private BigInteger q;
    private BigInteger g;
    private BigInteger y;
    
    public DSAPublicKey(final BigInteger p, final BigInteger q, final BigInteger g, final BigInteger y) {
        this.p = p;
        this.q = q;
        this.g = g;
        this.y = y;
    }
    
    public BigInteger getP() {
        return this.p;
    }
    
    public BigInteger getQ() {
        return this.q;
    }
    
    public BigInteger getG() {
        return this.g;
    }
    
    public BigInteger getY() {
        return this.y;
    }
}
