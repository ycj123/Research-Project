// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.signature;

import ch.ethz.ssh2.crypto.SimpleDERReader;
import ch.ethz.ssh2.crypto.digest.SHA1;
import ch.ethz.ssh2.packets.TypesWriter;
import java.math.BigInteger;
import java.io.IOException;
import ch.ethz.ssh2.packets.TypesReader;
import ch.ethz.ssh2.log.Logger;

public class RSASHA1Verify
{
    private static final Logger log;
    static /* synthetic */ Class class$0;
    
    static {
        Class class$0;
        if ((class$0 = RSASHA1Verify.class$0) == null) {
            try {
                class$0 = (RSASHA1Verify.class$0 = Class.forName("ch.ethz.ssh2.signature.RSASHA1Verify"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        log = Logger.getLogger(class$0);
    }
    
    public static RSAPublicKey decodeSSHRSAPublicKey(final byte[] key) throws IOException {
        final TypesReader tr = new TypesReader(key);
        final String key_format = tr.readString();
        if (!key_format.equals("ssh-rsa")) {
            throw new IllegalArgumentException("This is not a ssh-rsa public key");
        }
        final BigInteger e = tr.readMPINT();
        final BigInteger n = tr.readMPINT();
        if (tr.remain() != 0) {
            throw new IOException("Padding in RSA public key!");
        }
        return new RSAPublicKey(e, n);
    }
    
    public static byte[] encodeSSHRSAPublicKey(final RSAPublicKey pk) throws IOException {
        final TypesWriter tw = new TypesWriter();
        tw.writeString("ssh-rsa");
        tw.writeMPInt(pk.getE());
        tw.writeMPInt(pk.getN());
        return tw.getBytes();
    }
    
    public static RSASignature decodeSSHRSASignature(final byte[] sig) throws IOException {
        final TypesReader tr = new TypesReader(sig);
        final String sig_format = tr.readString();
        if (!sig_format.equals("ssh-rsa")) {
            throw new IOException("Peer sent wrong signature format");
        }
        final byte[] s = tr.readByteString();
        if (s.length == 0) {
            throw new IOException("Error in RSA signature, S is empty.");
        }
        if (RSASHA1Verify.log.isEnabled()) {
            RSASHA1Verify.log.log(80, "Decoding ssh-rsa signature string (length: " + s.length + ")");
        }
        if (tr.remain() != 0) {
            throw new IOException("Padding in RSA signature!");
        }
        return new RSASignature(new BigInteger(1, s));
    }
    
    public static byte[] encodeSSHRSASignature(final RSASignature sig) throws IOException {
        final TypesWriter tw = new TypesWriter();
        tw.writeString("ssh-rsa");
        final byte[] s = sig.getS().toByteArray();
        if (s.length > 1 && s[0] == 0) {
            tw.writeString(s, 1, s.length - 1);
        }
        else {
            tw.writeString(s, 0, s.length);
        }
        return tw.getBytes();
    }
    
    public static RSASignature generateSignature(final byte[] message, final RSAPrivateKey pk) throws IOException {
        final SHA1 md = new SHA1();
        md.update(message);
        final byte[] sha_message = new byte[md.getDigestLength()];
        md.digest(sha_message);
        final byte[] der_header = { 48, 33, 48, 9, 6, 5, 43, 14, 3, 2, 26, 5, 0, 4, 20 };
        final int rsa_block_len = (pk.getN().bitLength() + 7) / 8;
        final int num_pad = rsa_block_len - (2 + der_header.length + sha_message.length) - 1;
        if (num_pad < 8) {
            throw new IOException("Cannot sign with RSA, message too long");
        }
        final byte[] sig = new byte[der_header.length + sha_message.length + 2 + num_pad];
        sig[0] = 1;
        for (int i = 0; i < num_pad; ++i) {
            sig[i + 1] = -1;
        }
        sig[num_pad + 1] = 0;
        System.arraycopy(der_header, 0, sig, 2 + num_pad, der_header.length);
        System.arraycopy(sha_message, 0, sig, 2 + num_pad + der_header.length, sha_message.length);
        final BigInteger m = new BigInteger(1, sig);
        final BigInteger s = m.modPow(pk.getD(), pk.getN());
        return new RSASignature(s);
    }
    
    public static boolean verifySignature(final byte[] message, final RSASignature ds, final RSAPublicKey dpk) throws IOException {
        final SHA1 md = new SHA1();
        md.update(message);
        final byte[] sha_message = new byte[md.getDigestLength()];
        md.digest(sha_message);
        final BigInteger n = dpk.getN();
        final BigInteger e = dpk.getE();
        final BigInteger s = ds.getS();
        if (n.compareTo(s) <= 0) {
            RSASHA1Verify.log.log(20, "ssh-rsa signature: n.compareTo(s) <= 0");
            return false;
        }
        final int rsa_block_len = (n.bitLength() + 7) / 8;
        if (rsa_block_len < 1) {
            RSASHA1Verify.log.log(20, "ssh-rsa signature: rsa_block_len < 1");
            return false;
        }
        final byte[] v = s.modPow(e, n).toByteArray();
        int startpos = 0;
        if (v.length > 0 && v[0] == 0) {
            ++startpos;
        }
        if (v.length - startpos != rsa_block_len - 1) {
            RSASHA1Verify.log.log(20, "ssh-rsa signature: (v.length - startpos) != (rsa_block_len - 1)");
            return false;
        }
        if (v[startpos] != 1) {
            RSASHA1Verify.log.log(20, "ssh-rsa signature: v[startpos] != 0x01");
            return false;
        }
        int pos = startpos + 1;
        while (pos < v.length) {
            if (v[pos] == 0) {
                final int num_pad = pos - (startpos + 1);
                if (num_pad < 8) {
                    RSASHA1Verify.log.log(20, "ssh-rsa signature: num_pad < 8");
                    return false;
                }
                if (++pos >= v.length) {
                    RSASHA1Verify.log.log(20, "ssh-rsa signature: pos >= v.length");
                    return false;
                }
                final SimpleDERReader dr = new SimpleDERReader(v, pos, v.length - pos);
                final byte[] seq = dr.readSequenceAsByteArray();
                if (dr.available() != 0) {
                    RSASHA1Verify.log.log(20, "ssh-rsa signature: dr.available() != 0");
                    return false;
                }
                dr.resetInput(seq);
                final byte[] digestAlgorithm = dr.readSequenceAsByteArray();
                if (digestAlgorithm.length < 8 || digestAlgorithm.length > 9) {
                    RSASHA1Verify.log.log(20, "ssh-rsa signature: (digestAlgorithm.length < 8) || (digestAlgorithm.length > 9)");
                    return false;
                }
                final byte[] digestAlgorithm_sha1 = { 6, 5, 43, 14, 3, 2, 26, 5, 0 };
                for (int i = 0; i < digestAlgorithm.length; ++i) {
                    if (digestAlgorithm[i] != digestAlgorithm_sha1[i]) {
                        RSASHA1Verify.log.log(20, "ssh-rsa signature: digestAlgorithm[i] != digestAlgorithm_sha1[i]");
                        return false;
                    }
                }
                final byte[] digest = dr.readOctetString();
                if (dr.available() != 0) {
                    RSASHA1Verify.log.log(20, "ssh-rsa signature: dr.available() != 0 (II)");
                    return false;
                }
                if (digest.length != sha_message.length) {
                    RSASHA1Verify.log.log(20, "ssh-rsa signature: digest.length != sha_message.length");
                    return false;
                }
                for (int j = 0; j < sha_message.length; ++j) {
                    if (sha_message[j] != digest[j]) {
                        RSASHA1Verify.log.log(20, "ssh-rsa signature: sha_message[i] != digest[i]");
                        return false;
                    }
                }
                return true;
            }
            else {
                if (v[pos] != -1) {
                    RSASHA1Verify.log.log(20, "ssh-rsa signature: v[pos] != (byte) 0xff");
                    return false;
                }
                ++pos;
            }
        }
        RSASHA1Verify.log.log(20, "ssh-rsa signature: pos >= v.length");
        return false;
    }
}
