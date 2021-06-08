// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.signature;

import java.math.BigInteger;

public class DSAPrivateKey
{
    private BigInteger p;
    private BigInteger q;
    private BigInteger g;
    private BigInteger x;
    private BigInteger y;
    
    public DSAPrivateKey(final BigInteger p, final BigInteger q, final BigInteger g, final BigInteger y, final BigInteger x) {
        this.p = p;
        this.q = q;
        this.g = g;
        this.y = y;
        this.x = x;
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
    
    public BigInteger getX() {
        return this.x;
    }
    
    public DSAPublicKey getPublicKey() {
        return new DSAPublicKey(this.p, this.q, this.g, this.y);
    }
}
