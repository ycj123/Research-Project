// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto;

import ch.ethz.ssh2.signature.RSAPrivateKey;
import ch.ethz.ssh2.signature.DSAPrivateKey;
import java.math.BigInteger;
import ch.ethz.ssh2.crypto.cipher.AES;
import ch.ethz.ssh2.crypto.cipher.DES;
import ch.ethz.ssh2.crypto.cipher.BlockCipher;
import ch.ethz.ssh2.crypto.cipher.CBCMode;
import ch.ethz.ssh2.crypto.cipher.DESede;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import ch.ethz.ssh2.crypto.digest.MD5;

public class PEMDecoder
{
    private static final int PEM_RSA_PRIVATE_KEY = 1;
    private static final int PEM_DSA_PRIVATE_KEY = 2;
    
    private static final int hexToInt(final char c) {
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        throw new IllegalArgumentException("Need hex char");
    }
    
    private static byte[] hexToByteArray(final String hex) {
        if (hex == null) {
            throw new IllegalArgumentException("null argument");
        }
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Uneven string length in hex encoding.");
        }
        final byte[] decoded = new byte[hex.length() / 2];
        for (int i = 0; i < decoded.length; ++i) {
            final int hi = hexToInt(hex.charAt(i * 2));
            final int lo = hexToInt(hex.charAt(i * 2 + 1));
            decoded[i] = (byte)(hi * 16 + lo);
        }
        return decoded;
    }
    
    private static byte[] generateKeyFromPasswordSaltWithMD5(final byte[] password, final byte[] salt, int keyLen) throws IOException {
        if (salt.length < 8) {
            throw new IllegalArgumentException("Salt needs to be at least 8 bytes for key generation.");
        }
        final MD5 md5 = new MD5();
        final byte[] key = new byte[keyLen];
        final byte[] tmp = new byte[md5.getDigestLength()];
        while (true) {
            md5.update(password, 0, password.length);
            md5.update(salt, 0, 8);
            final int copy = (keyLen < tmp.length) ? keyLen : tmp.length;
            md5.digest(tmp, 0);
            System.arraycopy(tmp, 0, key, key.length - keyLen, copy);
            keyLen -= copy;
            if (keyLen == 0) {
                break;
            }
            md5.update(tmp, 0, tmp.length);
        }
        return key;
    }
    
    private static byte[] removePadding(final byte[] buff, final int blockSize) throws IOException {
        final int rfc_1423_padding = buff[buff.length - 1] & 0xFF;
        if (rfc_1423_padding < 1 || rfc_1423_padding > blockSize) {
            throw new IOException("Decrypted PEM has wrong padding, did you specify the correct password?");
        }
        for (int i = 2; i <= rfc_1423_padding; ++i) {
            if (buff[buff.length - i] != rfc_1423_padding) {
                throw new IOException("Decrypted PEM has wrong padding, did you specify the correct password?");
            }
        }
        final byte[] tmp = new byte[buff.length - rfc_1423_padding];
        System.arraycopy(buff, 0, tmp, 0, buff.length - rfc_1423_padding);
        return tmp;
    }
    
    private static final PEMStructure parsePEM(final char[] pem) throws IOException {
        final PEMStructure ps = new PEMStructure();
        String line = null;
        final BufferedReader br = new BufferedReader(new CharArrayReader(pem));
        String endLine = null;
        while (true) {
            do {
                line = br.readLine();
                if (line == null) {
                    throw new IOException("Invalid PEM structure, '-----BEGIN...' missing");
                }
                line = line.trim();
                if (!line.startsWith("-----BEGIN DSA PRIVATE KEY-----")) {
                    continue;
                }
                endLine = "-----END DSA PRIVATE KEY-----";
                ps.pemType = 2;
                while (true) {
                    line = br.readLine();
                    if (line == null) {
                        throw new IOException("Invalid PEM structure, " + endLine + " missing");
                    }
                    line = line.trim();
                    final int sem_idx = line.indexOf(58);
                    if (sem_idx == -1) {
                        final StringBuffer keyData = new StringBuffer();
                        while (line != null) {
                            line = line.trim();
                            if (line.startsWith(endLine)) {
                                final char[] pem_chars = new char[keyData.length()];
                                keyData.getChars(0, pem_chars.length, pem_chars, 0);
                                ps.data = Base64.decode(pem_chars);
                                if (ps.data.length == 0) {
                                    throw new IOException("Invalid PEM structure, no data available");
                                }
                                return ps;
                            }
                            else {
                                keyData.append(line);
                                line = br.readLine();
                            }
                        }
                        throw new IOException("Invalid PEM structure, " + endLine + " missing");
                    }
                    final String name = line.substring(0, sem_idx + 1);
                    final String value = line.substring(sem_idx + 1);
                    final String[] values = value.split(",");
                    for (int i = 0; i < values.length; ++i) {
                        values[i] = values[i].trim();
                    }
                    if ("Proc-Type:".equals(name)) {
                        ps.procType = values;
                    }
                    else {
                        if (!"DEK-Info:".equals(name)) {
                            continue;
                        }
                        ps.dekInfo = values;
                    }
                }
            } while (!line.startsWith("-----BEGIN RSA PRIVATE KEY-----"));
            endLine = "-----END RSA PRIVATE KEY-----";
            ps.pemType = 1;
            continue;
        }
    }
    
    private static final void decryptPEM(final PEMStructure ps, final byte[] pw) throws IOException {
        if (ps.dekInfo == null) {
            throw new IOException("Broken PEM, no mode and salt given, but encryption enabled");
        }
        if (ps.dekInfo.length != 2) {
            throw new IOException("Broken PEM, DEK-Info is incomplete!");
        }
        final String algo = ps.dekInfo[0];
        final byte[] salt = hexToByteArray(ps.dekInfo[1]);
        BlockCipher bc = null;
        if (algo.equals("DES-EDE3-CBC")) {
            final DESede des3 = new DESede();
            des3.init(false, generateKeyFromPasswordSaltWithMD5(pw, salt, 24));
            bc = new CBCMode(des3, salt, false);
        }
        else if (algo.equals("DES-CBC")) {
            final DES des4 = new DES();
            des4.init(false, generateKeyFromPasswordSaltWithMD5(pw, salt, 8));
            bc = new CBCMode(des4, salt, false);
        }
        else if (algo.equals("AES-128-CBC")) {
            final AES aes = new AES();
            aes.init(false, generateKeyFromPasswordSaltWithMD5(pw, salt, 16));
            bc = new CBCMode(aes, salt, false);
        }
        else if (algo.equals("AES-192-CBC")) {
            final AES aes = new AES();
            aes.init(false, generateKeyFromPasswordSaltWithMD5(pw, salt, 24));
            bc = new CBCMode(aes, salt, false);
        }
        else {
            if (!algo.equals("AES-256-CBC")) {
                throw new IOException("Cannot decrypt PEM structure, unknown cipher " + algo);
            }
            final AES aes = new AES();
            aes.init(false, generateKeyFromPasswordSaltWithMD5(pw, salt, 32));
            bc = new CBCMode(aes, salt, false);
        }
        if (ps.data.length % bc.getBlockSize() != 0) {
            throw new IOException("Invalid PEM structure, size of encrypted block is not a multiple of " + bc.getBlockSize());
        }
        byte[] dz = new byte[ps.data.length];
        for (int i = 0; i < ps.data.length / bc.getBlockSize(); ++i) {
            bc.transformBlock(ps.data, i * bc.getBlockSize(), dz, i * bc.getBlockSize());
        }
        dz = removePadding(dz, bc.getBlockSize());
        ps.data = dz;
        ps.dekInfo = null;
        ps.procType = null;
    }
    
    public static final boolean isPEMEncrypted(final PEMStructure ps) throws IOException {
        if (ps.procType == null) {
            return false;
        }
        if (ps.procType.length != 2) {
            throw new IOException("Unknown Proc-Type field.");
        }
        if (!"4".equals(ps.procType[0])) {
            throw new IOException("Unknown Proc-Type field (" + ps.procType[0] + ")");
        }
        return "ENCRYPTED".equals(ps.procType[1]);
    }
    
    public static Object decode(final char[] pem, final String password) throws IOException {
        final PEMStructure ps = parsePEM(pem);
        if (isPEMEncrypted(ps)) {
            if (password == null) {
                throw new IOException("PEM is encrypted, but no password was specified");
            }
            decryptPEM(ps, password.getBytes());
        }
        if (ps.pemType == 2) {
            final SimpleDERReader dr = new SimpleDERReader(ps.data);
            final byte[] seq = dr.readSequenceAsByteArray();
            if (dr.available() != 0) {
                throw new IOException("Padding in DSA PRIVATE KEY DER stream.");
            }
            dr.resetInput(seq);
            final BigInteger version = dr.readInt();
            if (version.compareTo(BigInteger.ZERO) != 0) {
                throw new IOException("Wrong version (" + version + ") in DSA PRIVATE KEY DER stream.");
            }
            final BigInteger p = dr.readInt();
            final BigInteger q = dr.readInt();
            final BigInteger g = dr.readInt();
            final BigInteger y = dr.readInt();
            final BigInteger x = dr.readInt();
            if (dr.available() != 0) {
                throw new IOException("Padding in DSA PRIVATE KEY DER stream.");
            }
            return new DSAPrivateKey(p, q, g, y, x);
        }
        else {
            if (ps.pemType != 1) {
                throw new IOException("PEM problem: it is of unknown type");
            }
            final SimpleDERReader dr = new SimpleDERReader(ps.data);
            final byte[] seq = dr.readSequenceAsByteArray();
            if (dr.available() != 0) {
                throw new IOException("Padding in RSA PRIVATE KEY DER stream.");
            }
            dr.resetInput(seq);
            final BigInteger version = dr.readInt();
            if (version.compareTo(BigInteger.ZERO) != 0 && version.compareTo(BigInteger.ONE) != 0) {
                throw new IOException("Wrong version (" + version + ") in RSA PRIVATE KEY DER stream.");
            }
            final BigInteger n = dr.readInt();
            final BigInteger e = dr.readInt();
            final BigInteger d = dr.readInt();
            return new RSAPrivateKey(d, e, n);
        }
    }
}
