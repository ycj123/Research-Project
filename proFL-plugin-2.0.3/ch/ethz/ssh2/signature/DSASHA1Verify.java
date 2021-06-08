// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.signature;

import java.util.Random;
import java.security.SecureRandom;
import ch.ethz.ssh2.crypto.digest.SHA1;
import ch.ethz.ssh2.packets.TypesWriter;
import java.math.BigInteger;
import java.io.IOException;
import ch.ethz.ssh2.packets.TypesReader;
import ch.ethz.ssh2.log.Logger;

public class DSASHA1Verify
{
    private static final Logger log;
    static /* synthetic */ Class class$0;
    
    static {
        Class class$0;
        if ((class$0 = DSASHA1Verify.class$0) == null) {
            try {
                class$0 = (DSASHA1Verify.class$0 = Class.forName("ch.ethz.ssh2.signature.DSASHA1Verify"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        log = Logger.getLogger(class$0);
    }
    
    public static DSAPublicKey decodeSSHDSAPublicKey(final byte[] key) throws IOException {
        final TypesReader tr = new TypesReader(key);
        final String key_format = tr.readString();
        if (!key_format.equals("ssh-dss")) {
            throw new IllegalArgumentException("This is not a ssh-dss public key!");
        }
        final BigInteger p = tr.readMPINT();
        final BigInteger q = tr.readMPINT();
        final BigInteger g = tr.readMPINT();
        final BigInteger y = tr.readMPINT();
        if (tr.remain() != 0) {
            throw new IOException("Padding in DSA public key!");
        }
        return new DSAPublicKey(p, q, g, y);
    }
    
    public static byte[] encodeSSHDSAPublicKey(final DSAPublicKey pk) throws IOException {
        final TypesWriter tw = new TypesWriter();
        tw.writeString("ssh-dss");
        tw.writeMPInt(pk.getP());
        tw.writeMPInt(pk.getQ());
        tw.writeMPInt(pk.getG());
        tw.writeMPInt(pk.getY());
        return tw.getBytes();
    }
    
    public static byte[] encodeSSHDSASignature(final DSASignature ds) {
        final TypesWriter tw = new TypesWriter();
        tw.writeString("ssh-dss");
        final byte[] r = ds.getR().toByteArray();
        final byte[] s = ds.getS().toByteArray();
        final byte[] a40 = new byte[40];
        final int r_copylen = (r.length < 20) ? r.length : 20;
        final int s_copylen = (s.length < 20) ? s.length : 20;
        System.arraycopy(r, r.length - r_copylen, a40, 20 - r_copylen, r_copylen);
        System.arraycopy(s, s.length - s_copylen, a40, 40 - s_copylen, s_copylen);
        tw.writeString(a40, 0, 40);
        return tw.getBytes();
    }
    
    public static DSASignature decodeSSHDSASignature(final byte[] sig) throws IOException {
        final TypesReader tr = new TypesReader(sig);
        final String sig_format = tr.readString();
        if (!sig_format.equals("ssh-dss")) {
            throw new IOException("Peer sent wrong signature format");
        }
        final byte[] rsArray = tr.readByteString();
        if (rsArray.length != 40) {
            throw new IOException("Peer sent corrupt signature");
        }
        if (tr.remain() != 0) {
            throw new IOException("Padding in DSA signature!");
        }
        final byte[] tmp = new byte[20];
        System.arraycopy(rsArray, 0, tmp, 0, 20);
        final BigInteger r = new BigInteger(1, tmp);
        System.arraycopy(rsArray, 20, tmp, 0, 20);
        final BigInteger s = new BigInteger(1, tmp);
        if (DSASHA1Verify.log.isEnabled()) {
            DSASHA1Verify.log.log(30, "decoded ssh-dss signature: first bytes r(" + (rsArray[0] & 0xFF) + "), s(" + (rsArray[20] & 0xFF) + ")");
        }
        return new DSASignature(r, s);
    }
    
    public static boolean verifySignature(final byte[] message, final DSASignature ds, final DSAPublicKey dpk) throws IOException {
        final SHA1 md = new SHA1();
        md.update(message);
        final byte[] sha_message = new byte[md.getDigestLength()];
        md.digest(sha_message);
        final BigInteger m = new BigInteger(1, sha_message);
        final BigInteger r = ds.getR();
        final BigInteger s = ds.getS();
        final BigInteger g = dpk.getG();
        final BigInteger p = dpk.getP();
        final BigInteger q = dpk.getQ();
        final BigInteger y = dpk.getY();
        final BigInteger zero = BigInteger.ZERO;
        if (DSASHA1Verify.log.isEnabled()) {
            DSASHA1Verify.log.log(60, "ssh-dss signature: m: " + m.toString(16));
            DSASHA1Verify.log.log(60, "ssh-dss signature: r: " + r.toString(16));
            DSASHA1Verify.log.log(60, "ssh-dss signature: s: " + s.toString(16));
            DSASHA1Verify.log.log(60, "ssh-dss signature: g: " + g.toString(16));
            DSASHA1Verify.log.log(60, "ssh-dss signature: p: " + p.toString(16));
            DSASHA1Verify.log.log(60, "ssh-dss signature: q: " + q.toString(16));
            DSASHA1Verify.log.log(60, "ssh-dss signature: y: " + y.toString(16));
        }
        if (zero.compareTo(r) >= 0 || q.compareTo(r) <= 0) {
            DSASHA1Verify.log.log(20, "ssh-dss signature: zero.compareTo(r) >= 0 || q.compareTo(r) <= 0");
            return false;
        }
        if (zero.compareTo(s) >= 0 || q.compareTo(s) <= 0) {
            DSASHA1Verify.log.log(20, "ssh-dss signature: zero.compareTo(s) >= 0 || q.compareTo(s) <= 0");
            return false;
        }
        final BigInteger w = s.modInverse(q);
        BigInteger u1 = m.multiply(w).mod(q);
        BigInteger u2 = r.multiply(w).mod(q);
        u1 = g.modPow(u1, p);
        u2 = y.modPow(u2, p);
        final BigInteger v = u1.multiply(u2).mod(p).mod(q);
        return v.equals(r);
    }
    
    public static DSASignature generateSignature(final byte[] message, final DSAPrivateKey pk, final SecureRandom rnd) {
        final SHA1 md = new SHA1();
        md.update(message);
        final byte[] sha_message = new byte[md.getDigestLength()];
        md.digest(sha_message);
        final BigInteger m = new BigInteger(1, sha_message);
        final int qBitLength = pk.getQ().bitLength();
        BigInteger k;
        do {
            k = new BigInteger(qBitLength, rnd);
        } while (k.compareTo(pk.getQ()) >= 0);
        final BigInteger r = pk.getG().modPow(k, pk.getP()).mod(pk.getQ());
        k = k.modInverse(pk.getQ()).multiply(m.add(pk.getX().multiply(r)));
        final BigInteger s = k.mod(pk.getQ());
        return new DSASignature(r, s);
    }
}
