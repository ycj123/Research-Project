// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto;

import ch.ethz.ssh2.crypto.digest.MAC;
import ch.ethz.ssh2.crypto.cipher.BlockCipherFactory;
import ch.ethz.ssh2.transport.KexManager;

public class CryptoWishList
{
    public String[] kexAlgorithms;
    public String[] serverHostKeyAlgorithms;
    public String[] c2s_enc_algos;
    public String[] s2c_enc_algos;
    public String[] c2s_mac_algos;
    public String[] s2c_mac_algos;
    
    public CryptoWishList() {
        this.kexAlgorithms = KexManager.getDefaultKexAlgorithmList();
        this.serverHostKeyAlgorithms = KexManager.getDefaultServerHostkeyAlgorithmList();
        this.c2s_enc_algos = BlockCipherFactory.getDefaultCipherList();
        this.s2c_enc_algos = BlockCipherFactory.getDefaultCipherList();
        this.c2s_mac_algos = MAC.getMacList();
        this.s2c_mac_algos = MAC.getMacList();
    }
}
