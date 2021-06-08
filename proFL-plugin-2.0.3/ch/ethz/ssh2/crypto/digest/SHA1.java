// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.crypto.digest;

public final class SHA1 implements Digest
{
    private int H0;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private final byte[] msg;
    private final int[] w;
    private int currentPos;
    private long currentLen;
    
    public SHA1() {
        this.msg = new byte[64];
        this.w = new int[80];
        this.reset();
    }
    
    public final int getDigestLength() {
        return 20;
    }
    
    public final void reset() {
        this.H0 = 1732584193;
        this.H1 = -271733879;
        this.H2 = -1732584194;
        this.H3 = 271733878;
        this.H4 = -1009589776;
        this.currentPos = 0;
        this.currentLen = 0L;
    }
    
    public final void update(final byte[] b, final int off, final int len) {
        for (int i = off; i < off + len; ++i) {
            this.update(b[i]);
        }
    }
    
    public final void update(final byte[] b) {
        for (int i = 0; i < b.length; ++i) {
            this.update(b[i]);
        }
    }
    
    public final void update(final byte b) {
        this.msg[this.currentPos++] = b;
        this.currentLen += 8L;
        if (this.currentPos == 64) {
            this.perform();
            this.currentPos = 0;
        }
    }
    
    private static final String toHexString(final byte[] b) {
        final String hexChar = "0123456789ABCDEF";
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            sb.append("0123456789ABCDEF".charAt(b[i] >> 4 & 0xF));
            sb.append("0123456789ABCDEF".charAt(b[i] & 0xF));
        }
        return sb.toString();
    }
    
    private final void putInt(final byte[] b, final int pos, final int val) {
        b[pos] = (byte)(val >> 24);
        b[pos + 1] = (byte)(val >> 16);
        b[pos + 2] = (byte)(val >> 8);
        b[pos + 3] = (byte)val;
    }
    
    public final void digest(final byte[] out) {
        this.digest(out, 0);
    }
    
    public final void digest(final byte[] out, final int off) {
        final long l = this.currentLen;
        this.update((byte)(-128));
        while (this.currentPos != 56) {
            this.update((byte)0);
        }
        this.update((byte)(l >> 56));
        this.update((byte)(l >> 48));
        this.update((byte)(l >> 40));
        this.update((byte)(l >> 32));
        this.update((byte)(l >> 24));
        this.update((byte)(l >> 16));
        this.update((byte)(l >> 8));
        this.update((byte)l);
        this.putInt(out, off, this.H0);
        this.putInt(out, off + 4, this.H1);
        this.putInt(out, off + 8, this.H2);
        this.putInt(out, off + 12, this.H3);
        this.putInt(out, off + 16, this.H4);
        this.reset();
    }
    
    private final void perform() {
        for (int i = 0; i < 16; ++i) {
            this.w[i] = ((this.msg[i * 4] & 0xFF) << 24 | (this.msg[i * 4 + 1] & 0xFF) << 16 | (this.msg[i * 4 + 2] & 0xFF) << 8 | (this.msg[i * 4 + 3] & 0xFF));
        }
        for (int t = 16; t < 80; ++t) {
            final int x = this.w[t - 3] ^ this.w[t - 8] ^ this.w[t - 14] ^ this.w[t - 16];
            this.w[t] = (x << 1 | x >>> 31);
        }
        int A = this.H0;
        int B = this.H1;
        int C = this.H2;
        int D = this.H3;
        int E = this.H4;
        for (int t2 = 0; t2 <= 19; ++t2) {
            final int T = (A << 5 | A >>> 27) + ((B & C) | (~B & D)) + E + this.w[t2] + 1518500249;
            E = D;
            D = C;
            C = (B << 30 | B >>> 2);
            B = A;
            A = T;
        }
        for (int t2 = 20; t2 <= 39; ++t2) {
            final int T = (A << 5 | A >>> 27) + (B ^ C ^ D) + E + this.w[t2] + 1859775393;
            E = D;
            D = C;
            C = (B << 30 | B >>> 2);
            B = A;
            A = T;
        }
        for (int t2 = 40; t2 <= 59; ++t2) {
            final int T = (A << 5 | A >>> 27) + ((B & C) | (B & D) | (C & D)) + E + this.w[t2] - 1894007588;
            E = D;
            D = C;
            C = (B << 30 | B >>> 2);
            B = A;
            A = T;
        }
        for (int t2 = 60; t2 <= 79; ++t2) {
            final int T = (A << 5 | A >>> 27) + (B ^ C ^ D) + E + this.w[t2] - 899497514;
            E = D;
            D = C;
            C = (B << 30 | B >>> 2);
            B = A;
            A = T;
        }
        this.H0 += A;
        this.H1 += B;
        this.H2 += C;
        this.H3 += D;
        this.H4 += E;
    }
    
    public static void main(final String[] args) {
        final SHA1 sha = new SHA1();
        final byte[] dig1 = new byte[20];
        final byte[] dig2 = new byte[20];
        final byte[] dig3 = new byte[20];
        sha.update("abc".getBytes());
        sha.digest(dig1);
        sha.update("abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq".getBytes());
        sha.digest(dig2);
        for (int i = 0; i < 1000000; ++i) {
            sha.update((byte)97);
        }
        sha.digest(dig3);
        final String dig1_res = toHexString(dig1);
        final String dig2_res = toHexString(dig2);
        final String dig3_res = toHexString(dig3);
        final String dig1_ref = "A9993E364706816ABA3E25717850C26C9CD0D89D";
        final String dig2_ref = "84983E441C3BD26EBAAE4AA1F95129E5E54670F1";
        final String dig3_ref = "34AA973CD4C4DAA4F61EEB2BDBAD27316534016F";
        if (dig1_res.equals(dig1_ref)) {
            System.out.println("SHA-1 Test 1 OK.");
        }
        else {
            System.out.println("SHA-1 Test 1 FAILED.");
        }
        if (dig2_res.equals(dig2_ref)) {
            System.out.println("SHA-1 Test 2 OK.");
        }
        else {
            System.out.println("SHA-1 Test 2 FAILED.");
        }
        if (dig3_res.equals(dig3_ref)) {
            System.out.println("SHA-1 Test 3 OK.");
        }
        else {
            System.out.println("SHA-1 Test 3 FAILED.");
        }
    }
}
