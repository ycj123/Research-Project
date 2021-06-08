// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.cipher;

public class DESede extends DES
{
    private int[] key1;
    private int[] key2;
    private int[] key3;
    private boolean encrypt;
    
    public DESede() {
        this.key1 = null;
        this.key2 = null;
        this.key3 = null;
    }
    
    public void init(final boolean encrypting, final byte[] key) {
        this.key1 = this.generateWorkingKey(encrypting, key, 0);
        this.key2 = this.generateWorkingKey(!encrypting, key, 8);
        this.key3 = this.generateWorkingKey(encrypting, key, 16);
        this.encrypt = encrypting;
    }
    
    public String getAlgorithmName() {
        return "DESede";
    }
    
    public int getBlockSize() {
        return 8;
    }
    
    public void transformBlock(final byte[] in, final int inOff, final byte[] out, final int outOff) {
        if (this.key1 == null) {
            throw new IllegalStateException("DESede engine not initialised!");
        }
        if (this.encrypt) {
            this.desFunc(this.key1, in, inOff, out, outOff);
            this.desFunc(this.key2, out, outOff, out, outOff);
            this.desFunc(this.key3, out, outOff, out, outOff);
        }
        else {
            this.desFunc(this.key3, in, inOff, out, outOff);
            this.desFunc(this.key2, out, outOff, out, outOff);
            this.desFunc(this.key1, out, outOff, out, outOff);
        }
    }
    
    public void reset() {
    }
}
