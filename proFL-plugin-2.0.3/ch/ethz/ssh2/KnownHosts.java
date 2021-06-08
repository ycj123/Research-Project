// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2;

import ch.ethz.ssh2.crypto.digest.MD5;
import java.io.RandomAccessFile;
import java.io.FileReader;
import java.io.CharArrayWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.Vector;
import java.util.Iterator;
import ch.ethz.ssh2.crypto.digest.Digest;
import ch.ethz.ssh2.crypto.digest.HMAC;
import ch.ethz.ssh2.crypto.Base64;
import java.security.SecureRandom;
import ch.ethz.ssh2.crypto.digest.SHA1;
import ch.ethz.ssh2.signature.DSAPublicKey;
import ch.ethz.ssh2.signature.RSAPublicKey;
import ch.ethz.ssh2.signature.DSASHA1Verify;
import ch.ethz.ssh2.signature.RSASHA1Verify;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class KnownHosts
{
    public static final int HOSTKEY_IS_OK = 0;
    public static final int HOSTKEY_IS_NEW = 1;
    public static final int HOSTKEY_HAS_CHANGED = 2;
    private LinkedList publicKeys;
    
    public KnownHosts() {
        this.publicKeys = new LinkedList();
    }
    
    public KnownHosts(final char[] knownHostsData) throws IOException {
        this.publicKeys = new LinkedList();
        this.initialize(knownHostsData);
    }
    
    public KnownHosts(final File knownHosts) throws IOException {
        this.publicKeys = new LinkedList();
        this.initialize(knownHosts);
    }
    
    public void addHostkey(final String[] hostnames, final String serverHostKeyAlgorithm, final byte[] serverHostKey) throws IOException {
        if (hostnames == null) {
            throw new IllegalArgumentException("hostnames may not be null");
        }
        if ("ssh-rsa".equals(serverHostKeyAlgorithm)) {
            final RSAPublicKey rpk = RSASHA1Verify.decodeSSHRSAPublicKey(serverHostKey);
            synchronized (this.publicKeys) {
                this.publicKeys.add(new KnownHostsEntry(hostnames, rpk));
                // monitorexit(this.publicKeys)
                return;
            }
        }
        if ("ssh-dss".equals(serverHostKeyAlgorithm)) {
            final DSAPublicKey dpk = DSASHA1Verify.decodeSSHDSAPublicKey(serverHostKey);
            synchronized (this.publicKeys) {
                this.publicKeys.add(new KnownHostsEntry(hostnames, dpk));
                // monitorexit(this.publicKeys)
                return;
            }
        }
        throw new IOException("Unknwon host key type (" + serverHostKeyAlgorithm + ")");
    }
    
    public void addHostkeys(final char[] knownHostsData) throws IOException {
        this.initialize(knownHostsData);
    }
    
    public void addHostkeys(final File knownHosts) throws IOException {
        this.initialize(knownHosts);
    }
    
    public static final String createHashedHostname(final String hostname) {
        final SHA1 sha1 = new SHA1();
        final byte[] salt = new byte[sha1.getDigestLength()];
        new SecureRandom().nextBytes(salt);
        final byte[] hash = hmacSha1Hash(salt, hostname);
        final String base64_salt = new String(Base64.encode(salt));
        final String base64_hash = new String(Base64.encode(hash));
        return new String("|1|" + base64_salt + "|" + base64_hash);
    }
    
    private static final byte[] hmacSha1Hash(final byte[] salt, final String hostname) {
        final SHA1 sha1 = new SHA1();
        if (salt.length != sha1.getDigestLength()) {
            throw new IllegalArgumentException("Salt has wrong length (" + salt.length + ")");
        }
        final HMAC hmac = new HMAC(sha1, salt, salt.length);
        hmac.update(hostname.getBytes());
        final byte[] dig = new byte[hmac.getDigestLength()];
        hmac.digest(dig);
        return dig;
    }
    
    private final boolean checkHashed(final String entry, final String hostname) {
        if (!entry.startsWith("|1|")) {
            return false;
        }
        final int delim_idx = entry.indexOf(124, 3);
        if (delim_idx == -1) {
            return false;
        }
        final String salt_base64 = entry.substring(3, delim_idx);
        final String hash_base64 = entry.substring(delim_idx + 1);
        byte[] salt = null;
        byte[] hash = null;
        try {
            salt = Base64.decode(salt_base64.toCharArray());
            hash = Base64.decode(hash_base64.toCharArray());
        }
        catch (IOException e) {
            return false;
        }
        final SHA1 sha1 = new SHA1();
        if (salt.length != sha1.getDigestLength()) {
            return false;
        }
        final byte[] dig = hmacSha1Hash(salt, hostname);
        for (int i = 0; i < dig.length; ++i) {
            if (dig[i] != hash[i]) {
                return false;
            }
        }
        return true;
    }
    
    private int checkKey(final String remoteHostname, final Object remoteKey) {
        int result = 1;
        synchronized (this.publicKeys) {
            for (final KnownHostsEntry ke : this.publicKeys) {
                if (!this.hostnameMatches(ke.patterns, remoteHostname)) {
                    continue;
                }
                final boolean res = this.matchKeys(ke.key, remoteKey);
                if (res) {
                    // monitorexit(this.publicKeys)
                    return 0;
                }
                result = 2;
            }
        }
        // monitorexit(this.publicKeys)
        return result;
    }
    
    private Vector getAllKeys(final String hostname) {
        final Vector keys = new Vector();
        synchronized (this.publicKeys) {
            for (final KnownHostsEntry ke : this.publicKeys) {
                if (!this.hostnameMatches(ke.patterns, hostname)) {
                    continue;
                }
                keys.addElement(ke.key);
            }
        }
        // monitorexit(this.publicKeys)
        return keys;
    }
    
    public String[] getPreferredServerHostkeyAlgorithmOrder(final String hostname) {
        String[] algos = this.recommendHostkeyAlgorithms(hostname);
        if (algos != null) {
            return algos;
        }
        InetAddress[] ipAdresses = null;
        try {
            ipAdresses = InetAddress.getAllByName(hostname);
        }
        catch (UnknownHostException e) {
            return null;
        }
        for (int i = 0; i < ipAdresses.length; ++i) {
            algos = this.recommendHostkeyAlgorithms(ipAdresses[i].getHostAddress());
            if (algos != null) {
                return algos;
            }
        }
        return null;
    }
    
    private final boolean hostnameMatches(final String[] hostpatterns, String hostname) {
        boolean isMatch = false;
        boolean negate = false;
        hostname = hostname.toLowerCase();
        for (int k = 0; k < hostpatterns.length; ++k) {
            if (hostpatterns[k] != null) {
                String pattern = null;
                if (hostpatterns[k].length() > 0 && hostpatterns[k].charAt(0) == '!') {
                    pattern = hostpatterns[k].substring(1);
                    negate = true;
                }
                else {
                    pattern = hostpatterns[k];
                    negate = false;
                }
                if (!isMatch || negate) {
                    if (pattern.charAt(0) == '|') {
                        if (this.checkHashed(pattern, hostname)) {
                            if (negate) {
                                return false;
                            }
                            isMatch = true;
                        }
                    }
                    else {
                        pattern = pattern.toLowerCase();
                        if (pattern.indexOf(63) != -1 || pattern.indexOf(42) != -1) {
                            if (this.pseudoRegex(pattern.toCharArray(), 0, hostname.toCharArray(), 0)) {
                                if (negate) {
                                    return false;
                                }
                                isMatch = true;
                            }
                        }
                        else if (pattern.compareTo(hostname) == 0) {
                            if (negate) {
                                return false;
                            }
                            isMatch = true;
                        }
                    }
                }
            }
        }
        return isMatch;
    }
    
    private void initialize(final char[] knownHostsData) throws IOException {
        final BufferedReader br = new BufferedReader(new CharArrayReader(knownHostsData));
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            line = line.trim();
            if (line.startsWith("#")) {
                continue;
            }
            final String[] arr = line.split(" ");
            if (arr.length < 3 || (arr[1].compareTo("ssh-rsa") != 0 && arr[1].compareTo("ssh-dss") != 0)) {
                continue;
            }
            final String[] hostnames = arr[0].split(",");
            final byte[] msg = Base64.decode(arr[2].toCharArray());
            this.addHostkey(hostnames, arr[1], msg);
        }
    }
    
    private void initialize(final File knownHosts) throws IOException {
        final char[] buff = new char[512];
        final CharArrayWriter cw = new CharArrayWriter();
        knownHosts.createNewFile();
        final FileReader fr = new FileReader(knownHosts);
        while (true) {
            final int len = fr.read(buff);
            if (len < 0) {
                break;
            }
            cw.write(buff, 0, len);
        }
        fr.close();
        this.initialize(cw.toCharArray());
    }
    
    private final boolean matchKeys(final Object key1, final Object key2) {
        if (key1 instanceof RSAPublicKey && key2 instanceof RSAPublicKey) {
            final RSAPublicKey savedRSAKey = (RSAPublicKey)key1;
            final RSAPublicKey remoteRSAKey = (RSAPublicKey)key2;
            return savedRSAKey.getE().equals(remoteRSAKey.getE()) && savedRSAKey.getN().equals(remoteRSAKey.getN());
        }
        if (key1 instanceof DSAPublicKey && key2 instanceof DSAPublicKey) {
            final DSAPublicKey savedDSAKey = (DSAPublicKey)key1;
            final DSAPublicKey remoteDSAKey = (DSAPublicKey)key2;
            return savedDSAKey.getG().equals(remoteDSAKey.getG()) && savedDSAKey.getP().equals(remoteDSAKey.getP()) && savedDSAKey.getQ().equals(remoteDSAKey.getQ()) && savedDSAKey.getY().equals(remoteDSAKey.getY());
        }
        return false;
    }
    
    private final boolean pseudoRegex(final char[] pattern, int i, final char[] match, int j) {
        while (pattern.length != i) {
            if (pattern[i] == '*') {
                ++i;
                if (pattern.length == i) {
                    return true;
                }
                if (pattern[i] != '*' && pattern[i] != '?') {
                    while (pattern[i] != match[j] || !this.pseudoRegex(pattern, i + 1, match, j + 1)) {
                        ++j;
                        if (match.length == j) {
                            return false;
                        }
                    }
                    return true;
                }
                while (!this.pseudoRegex(pattern, i, match, j)) {
                    ++j;
                    if (match.length == j) {
                        return false;
                    }
                }
                return true;
            }
            else {
                if (match.length == j) {
                    return false;
                }
                if (pattern[i] != '?' && pattern[i] != match[j]) {
                    return false;
                }
                ++i;
                ++j;
            }
        }
        return match.length == j;
    }
    
    private String[] recommendHostkeyAlgorithms(final String hostname) {
        final String preferredAlgo = null;
        final Vector keys = this.getAllKeys(hostname);
        for (int i = 0; i < keys.size(); ++i) {
            String thisAlgo = null;
            if (keys.elementAt(i) instanceof RSAPublicKey) {
                thisAlgo = "ssh-rsa";
            }
            else {
                if (!(keys.elementAt(i) instanceof DSAPublicKey)) {
                    continue;
                }
                thisAlgo = "ssh-dss";
            }
            if (preferredAlgo != null && preferredAlgo.compareTo(thisAlgo) != 0) {
                return null;
            }
        }
        if (preferredAlgo == null) {
            return null;
        }
        if (preferredAlgo.equals("ssh-rsa")) {
            return new String[] { "ssh-rsa", "ssh-dss" };
        }
        return new String[] { "ssh-dss", "ssh-rsa" };
    }
    
    public int verifyHostkey(final String hostname, final String serverHostKeyAlgorithm, final byte[] serverHostKey) throws IOException {
        Object remoteKey = null;
        if ("ssh-rsa".equals(serverHostKeyAlgorithm)) {
            remoteKey = RSASHA1Verify.decodeSSHRSAPublicKey(serverHostKey);
        }
        else {
            if (!"ssh-dss".equals(serverHostKeyAlgorithm)) {
                throw new IllegalArgumentException("Unknown hostkey type " + serverHostKeyAlgorithm);
            }
            remoteKey = DSASHA1Verify.decodeSSHDSAPublicKey(serverHostKey);
        }
        int result = this.checkKey(hostname, remoteKey);
        if (result == 0) {
            return result;
        }
        InetAddress[] ipAdresses = null;
        try {
            ipAdresses = InetAddress.getAllByName(hostname);
        }
        catch (UnknownHostException e) {
            return result;
        }
        for (int i = 0; i < ipAdresses.length; ++i) {
            final int newresult = this.checkKey(ipAdresses[i].getHostAddress(), remoteKey);
            if (newresult == 0) {
                return newresult;
            }
            if (newresult == 2) {
                result = 2;
            }
        }
        return result;
    }
    
    public static final void addHostkeyToFile(final File knownHosts, final String[] hostnames, final String serverHostKeyAlgorithm, final byte[] serverHostKey) throws IOException {
        if (hostnames == null || hostnames.length == 0) {
            throw new IllegalArgumentException("Need at least one hostname specification");
        }
        if (serverHostKeyAlgorithm == null || serverHostKey == null) {
            throw new IllegalArgumentException();
        }
        final CharArrayWriter writer = new CharArrayWriter();
        for (int i = 0; i < hostnames.length; ++i) {
            if (i != 0) {
                writer.write(44);
            }
            writer.write(hostnames[i]);
        }
        writer.write(32);
        writer.write(serverHostKeyAlgorithm);
        writer.write(32);
        writer.write(Base64.encode(serverHostKey));
        writer.write("\n");
        final char[] entry = writer.toCharArray();
        final RandomAccessFile raf = new RandomAccessFile(knownHosts, "rw");
        final long len = raf.length();
        if (len > 0L) {
            raf.seek(len - 1L);
            final int last = raf.read();
            if (last != 10) {
                raf.write(10);
            }
        }
        raf.write(new String(entry).getBytes());
        raf.close();
    }
    
    private static final byte[] rawFingerPrint(final String type, final String keyType, final byte[] hostkey) {
        Digest dig = null;
        if ("md5".equals(type)) {
            dig = new MD5();
        }
        else {
            if (!"sha1".equals(type)) {
                throw new IllegalArgumentException("Unknown hash type " + type);
            }
            dig = new SHA1();
        }
        if (!"ssh-rsa".equals(keyType) && !"ssh-dss".equals(keyType)) {
            throw new IllegalArgumentException("Unknown key type " + keyType);
        }
        if (hostkey == null) {
            throw new IllegalArgumentException("hostkey is null");
        }
        dig.update(hostkey);
        final byte[] res = new byte[dig.getDigestLength()];
        dig.digest(res);
        return res;
    }
    
    private static final String rawToHexFingerprint(final byte[] fingerprint) {
        final char[] alpha = "0123456789abcdef".toCharArray();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < fingerprint.length; ++i) {
            if (i != 0) {
                sb.append(':');
            }
            final int b = fingerprint[i] & 0xFF;
            sb.append(alpha[b >> 4]);
            sb.append(alpha[b & 0xF]);
        }
        return sb.toString();
    }
    
    private static final String rawToBubblebabbleFingerprint(final byte[] raw) {
        final char[] v = "aeiouy".toCharArray();
        final char[] c = "bcdfghklmnprstvzx".toCharArray();
        final StringBuffer sb = new StringBuffer();
        int seed = 1;
        final int rounds = raw.length / 2 + 1;
        sb.append('x');
        for (int i = 0; i < rounds; ++i) {
            if (i + 1 < rounds || raw.length % 2 != 0) {
                sb.append(v[((raw[2 * i] >> 6 & 0x3) + seed) % 6]);
                sb.append(c[raw[2 * i] >> 2 & 0xF]);
                sb.append(v[((raw[2 * i] & 0x3) + seed / 6) % 6]);
                if (i + 1 < rounds) {
                    sb.append(c[raw[2 * i + 1] >> 4 & 0xF]);
                    sb.append('-');
                    sb.append(c[raw[2 * i + 1] & 0xF]);
                    seed = (seed * 5 + ((raw[2 * i] & 0xFF) * 7 + (raw[2 * i + 1] & 0xFF))) % 36;
                }
            }
            else {
                sb.append(v[seed % 6]);
                sb.append('x');
                sb.append(v[seed / 6]);
            }
        }
        sb.append('x');
        return sb.toString();
    }
    
    public static final String createHexFingerprint(final String keytype, final byte[] publickey) {
        final byte[] raw = rawFingerPrint("md5", keytype, publickey);
        return rawToHexFingerprint(raw);
    }
    
    public static final String createBubblebabbleFingerprint(final String keytype, final byte[] publickey) {
        final byte[] raw = rawFingerPrint("sha1", keytype, publickey);
        return rawToBubblebabbleFingerprint(raw);
    }
    
    private class KnownHostsEntry
    {
        String[] patterns;
        Object key;
        
        KnownHostsEntry(final String[] patterns, final Object key) {
            this.patterns = patterns;
            this.key = key;
        }
    }
}
