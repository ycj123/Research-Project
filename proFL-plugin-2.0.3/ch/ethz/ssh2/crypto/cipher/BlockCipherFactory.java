// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.cipher;

import java.util.Vector;

public class BlockCipherFactory
{
    static Vector ciphers;
    
    static {
        (BlockCipherFactory.ciphers = new Vector()).addElement(new CipherEntry("aes256-ctr", 16, 32, "ch.ethz.ssh2.crypto.cipher.AES"));
        BlockCipherFactory.ciphers.addElement(new CipherEntry("aes192-ctr", 16, 24, "ch.ethz.ssh2.crypto.cipher.AES"));
        BlockCipherFactory.ciphers.addElement(new CipherEntry("aes128-ctr", 16, 16, "ch.ethz.ssh2.crypto.cipher.AES"));
        BlockCipherFactory.ciphers.addElement(new CipherEntry("blowfish-ctr", 8, 16, "ch.ethz.ssh2.crypto.cipher.BlowFish"));
        BlockCipherFactory.ciphers.addElement(new CipherEntry("aes256-cbc", 16, 32, "ch.ethz.ssh2.crypto.cipher.AES"));
        BlockCipherFactory.ciphers.addElement(new CipherEntry("aes192-cbc", 16, 24, "ch.ethz.ssh2.crypto.cipher.AES"));
        BlockCipherFactory.ciphers.addElement(new CipherEntry("aes128-cbc", 16, 16, "ch.ethz.ssh2.crypto.cipher.AES"));
        BlockCipherFactory.ciphers.addElement(new CipherEntry("blowfish-cbc", 8, 16, "ch.ethz.ssh2.crypto.cipher.BlowFish"));
        BlockCipherFactory.ciphers.addElement(new CipherEntry("3des-ctr", 8, 24, "ch.ethz.ssh2.crypto.cipher.DESede"));
        BlockCipherFactory.ciphers.addElement(new CipherEntry("3des-cbc", 8, 24, "ch.ethz.ssh2.crypto.cipher.DESede"));
    }
    
    public static String[] getDefaultCipherList() {
        final String[] list = new String[BlockCipherFactory.ciphers.size()];
        for (int i = 0; i < BlockCipherFactory.ciphers.size(); ++i) {
            final CipherEntry ce = BlockCipherFactory.ciphers.elementAt(i);
            list[i] = new String(ce.type);
        }
        return list;
    }
    
    public static void checkCipherList(final String[] cipherCandidates) {
        for (int i = 0; i < cipherCandidates.length; ++i) {
            getEntry(cipherCandidates[i]);
        }
    }
    
    public static BlockCipher createCipher(final String type, final boolean encrypt, final byte[] key, final byte[] iv) {
        try {
            final CipherEntry ce = getEntry(type);
            final Class cc = Class.forName(ce.cipherClass);
            final BlockCipher bc = cc.newInstance();
            if (type.endsWith("-cbc")) {
                bc.init(encrypt, key);
                return new CBCMode(bc, iv, encrypt);
            }
            if (type.endsWith("-ctr")) {
                bc.init(true, key);
                return new CTRMode(bc, iv, encrypt);
            }
            throw new IllegalArgumentException("Cannot instantiate " + type);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Cannot instantiate " + type);
        }
    }
    
    private static CipherEntry getEntry(final String type) {
        for (int i = 0; i < BlockCipherFactory.ciphers.size(); ++i) {
            final CipherEntry ce = BlockCipherFactory.ciphers.elementAt(i);
            if (ce.type.equals(type)) {
                return ce;
            }
        }
        throw new IllegalArgumentException("Unkown algorithm " + type);
    }
    
    public static int getBlockSize(final String type) {
        final CipherEntry ce = getEntry(type);
        return ce.blocksize;
    }
    
    public static int getKeySize(final String type) {
        final CipherEntry ce = getEntry(type);
        return ce.keysize;
    }
    
    static class CipherEntry
    {
        String type;
        int blocksize;
        int keysize;
        String cipherClass;
        
        public CipherEntry(final String type, final int blockSize, final int keySize, final String cipherClass) {
            this.type = type;
            this.blocksize = blockSize;
            this.keysize = keySize;
            this.cipherClass = cipherClass;
        }
    }
}
