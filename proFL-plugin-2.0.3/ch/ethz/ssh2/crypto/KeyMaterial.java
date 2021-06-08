// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto;

import java.math.BigInteger;
import ch.ethz.ssh2.crypto.digest.HashForSSH2Types;

public class KeyMaterial
{
    public byte[] initial_iv_client_to_server;
    public byte[] initial_iv_server_to_client;
    public byte[] enc_key_client_to_server;
    public byte[] enc_key_server_to_client;
    public byte[] integrity_key_client_to_server;
    public byte[] integrity_key_server_to_client;
    
    private static byte[] calculateKey(final HashForSSH2Types sh, final BigInteger K, final byte[] H, final byte type, final byte[] SessionID, int keyLength) {
        final byte[] res = new byte[keyLength];
        final int dglen = sh.getDigestLength();
        final int numRounds = (keyLength + dglen - 1) / dglen;
        final byte[][] tmp = new byte[numRounds][];
        sh.reset();
        sh.updateBigInt(K);
        sh.updateBytes(H);
        sh.updateByte(type);
        sh.updateBytes(SessionID);
        tmp[0] = sh.getDigest();
        int off = 0;
        int produced = Math.min(dglen, keyLength);
        System.arraycopy(tmp[0], 0, res, off, produced);
        keyLength -= produced;
        off += produced;
        for (int i = 1; i < numRounds; ++i) {
            sh.updateBigInt(K);
            sh.updateBytes(H);
            for (int j = 0; j < i; ++j) {
                sh.updateBytes(tmp[j]);
            }
            tmp[i] = sh.getDigest();
            produced = Math.min(dglen, keyLength);
            System.arraycopy(tmp[i], 0, res, off, produced);
            keyLength -= produced;
            off += produced;
        }
        return res;
    }
    
    public static KeyMaterial create(final String hashType, final byte[] H, final BigInteger K, final byte[] SessionID, final int keyLengthCS, final int blockSizeCS, final int macLengthCS, final int keyLengthSC, final int blockSizeSC, final int macLengthSC) throws IllegalArgumentException {
        final KeyMaterial km = new KeyMaterial();
        final HashForSSH2Types sh = new HashForSSH2Types(hashType);
        km.initial_iv_client_to_server = calculateKey(sh, K, H, (byte)65, SessionID, blockSizeCS);
        km.initial_iv_server_to_client = calculateKey(sh, K, H, (byte)66, SessionID, blockSizeSC);
        km.enc_key_client_to_server = calculateKey(sh, K, H, (byte)67, SessionID, keyLengthCS);
        km.enc_key_server_to_client = calculateKey(sh, K, H, (byte)68, SessionID, keyLengthSC);
        km.integrity_key_client_to_server = calculateKey(sh, K, H, (byte)69, SessionID, macLengthCS);
        km.integrity_key_server_to_client = calculateKey(sh, K, H, (byte)70, SessionID, macLengthSC);
        return km;
    }
}
