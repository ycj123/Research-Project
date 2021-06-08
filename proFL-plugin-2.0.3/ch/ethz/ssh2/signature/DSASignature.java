// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.signature;

import java.math.BigInteger;

public class DSASignature
{
    private BigInteger r;
    private BigInteger s;
    
    public DSASignature(final BigInteger r, final BigInteger s) {
        this.r = r;
        this.s = s;
    }
    
    public BigInteger getR() {
        return this.r;
    }
    
    public BigInteger getS() {
        return this.s;
    }
}
