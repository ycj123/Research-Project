// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.signature;

import java.math.BigInteger;

public class RSASignature
{
    BigInteger s;
    
    public BigInteger getS() {
        return this.s;
    }
    
    public RSASignature(final BigInteger s) {
        this.s = s;
    }
}
