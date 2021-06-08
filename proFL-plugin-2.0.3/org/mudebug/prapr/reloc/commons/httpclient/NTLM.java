// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.io.UnsupportedEncodingException;
import org.mudebug.prapr.reloc.commons.httpclient.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import org.mudebug.prapr.reloc.commons.logging.Log;

public final class NTLM
{
    private byte[] currentResponse;
    private int currentPosition;
    private static final Log LOG;
    public static final String DEFAULT_CHARSET = "ASCII";
    
    public NTLM() {
        this.currentPosition = 0;
    }
    
    public final String getResponseFor(final String message, final String username, final String password, final String host, final String domain) throws HttpException {
        String response;
        if (message == null || message.trim().equals("")) {
            response = this.getType1Message(host, domain);
        }
        else {
            response = this.getType3Message(username, password, host, domain, this.parseType2Message(message));
        }
        return response;
    }
    
    private Cipher getCipher(byte[] key) throws HttpException {
        try {
            final Cipher ecipher = Cipher.getInstance("DES/ECB/NoPadding");
            key = this.setupKey(key);
            ecipher.init(1, new SecretKeySpec(key, "DES"));
            return ecipher;
        }
        catch (NoSuchAlgorithmException e) {
            throw new HttpException("DES encryption is not available.");
        }
        catch (InvalidKeyException e2) {
            throw new HttpException("Invalid key for DES encryption.");
        }
        catch (NoSuchPaddingException e3) {
            throw new HttpException("NoPadding option for DES is not available.");
        }
    }
    
    private byte[] setupKey(final byte[] key56) {
        final byte[] key57 = { (byte)(key56[0] >> 1 & 0xFF), (byte)(((key56[0] & 0x1) << 6 | ((key56[1] & 0xFF) >> 2 & 0xFF)) & 0xFF), (byte)(((key56[1] & 0x3) << 5 | ((key56[2] & 0xFF) >> 3 & 0xFF)) & 0xFF), (byte)(((key56[2] & 0x7) << 4 | ((key56[3] & 0xFF) >> 4 & 0xFF)) & 0xFF), (byte)(((key56[3] & 0xF) << 3 | ((key56[4] & 0xFF) >> 5 & 0xFF)) & 0xFF), (byte)(((key56[4] & 0x1F) << 2 | ((key56[5] & 0xFF) >> 6 & 0xFF)) & 0xFF), (byte)(((key56[5] & 0x3F) << 1 | ((key56[6] & 0xFF) >> 7 & 0xFF)) & 0xFF), (byte)(key56[6] & 0x7F) };
        for (int i = 0; i < key57.length; ++i) {
            key57[i] <<= 1;
        }
        return key57;
    }
    
    private byte[] encrypt(final byte[] key, final byte[] bytes) throws HttpException {
        final Cipher ecipher = this.getCipher(key);
        try {
            final byte[] enc = ecipher.doFinal(bytes);
            return enc;
        }
        catch (IllegalBlockSizeException e) {
            throw new HttpException("Invalid block size for DES encryption.");
        }
        catch (BadPaddingException e2) {
            throw new HttpException("Data not padded correctly for DES encryption.");
        }
    }
    
    private void prepareResponse(final int length) {
        this.currentResponse = new byte[length];
        this.currentPosition = 0;
    }
    
    private void addByte(final byte b) {
        this.currentResponse[this.currentPosition] = b;
        ++this.currentPosition;
    }
    
    private void addBytes(final byte[] bytes) {
        for (int i = 0; i < bytes.length; ++i) {
            this.currentResponse[this.currentPosition] = bytes[i];
            ++this.currentPosition;
        }
    }
    
    private String getResponse() {
        byte[] resp;
        if (this.currentResponse.length > this.currentPosition) {
            final byte[] tmp = new byte[this.currentPosition];
            for (int i = 0; i < this.currentPosition; ++i) {
                tmp[i] = this.currentResponse[i];
            }
            resp = tmp;
        }
        else {
            resp = this.currentResponse;
        }
        return HttpConstants.getString(Base64.encode(resp));
    }
    
    private String getType1Message(String host, String domain) {
        host = host.toUpperCase();
        domain = domain.toUpperCase();
        final byte[] hostBytes = getBytes(host);
        final byte[] domainBytes = getBytes(domain);
        final int finalLength = 32 + hostBytes.length + domainBytes.length;
        this.prepareResponse(finalLength);
        final byte[] protocol = getBytes("NTLMSSP");
        this.addBytes(protocol);
        this.addByte((byte)0);
        this.addByte((byte)1);
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addByte((byte)6);
        this.addByte((byte)82);
        this.addByte((byte)0);
        this.addByte((byte)0);
        final int iDomLen = domainBytes.length;
        final byte[] domLen = this.convertShort(iDomLen);
        this.addByte(domLen[0]);
        this.addByte(domLen[1]);
        this.addByte(domLen[0]);
        this.addByte(domLen[1]);
        final byte[] domOff = this.convertShort(hostBytes.length + 32);
        this.addByte(domOff[0]);
        this.addByte(domOff[1]);
        this.addByte((byte)0);
        this.addByte((byte)0);
        final byte[] hostLen = this.convertShort(hostBytes.length);
        this.addByte(hostLen[0]);
        this.addByte(hostLen[1]);
        this.addByte(hostLen[0]);
        this.addByte(hostLen[1]);
        final byte[] hostOff = this.convertShort(32);
        this.addByte(hostOff[0]);
        this.addByte(hostOff[1]);
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addBytes(hostBytes);
        this.addBytes(domainBytes);
        return this.getResponse();
    }
    
    private byte[] parseType2Message(final String message) {
        final byte[] msg = Base64.decode(getBytes(message));
        final byte[] nonce = new byte[8];
        for (int i = 0; i < 8; ++i) {
            nonce[i] = msg[i + 24];
        }
        return nonce;
    }
    
    private String getType3Message(String user, final String password, String host, String domain, final byte[] nonce) throws HttpException {
        final int ntRespLen = 0;
        final int lmRespLen = 24;
        domain = domain.toUpperCase();
        host = host.toUpperCase();
        user = user.toUpperCase();
        final byte[] domainBytes = getBytes(domain);
        final byte[] hostBytes = getBytes(host);
        final byte[] userBytes = getBytes(user);
        final int domainLen = domainBytes.length;
        final int hostLen = hostBytes.length;
        final int userLen = userBytes.length;
        final int finalLength = 64 + ntRespLen + lmRespLen + domainLen + userLen + hostLen;
        this.prepareResponse(finalLength);
        final byte[] ntlmssp = getBytes("NTLMSSP");
        this.addBytes(ntlmssp);
        this.addByte((byte)0);
        this.addByte((byte)3);
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addBytes(this.convertShort(24));
        this.addBytes(this.convertShort(24));
        this.addBytes(this.convertShort(finalLength - 24));
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addBytes(this.convertShort(0));
        this.addBytes(this.convertShort(0));
        this.addBytes(this.convertShort(finalLength));
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addBytes(this.convertShort(domainLen));
        this.addBytes(this.convertShort(domainLen));
        this.addBytes(this.convertShort(64));
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addBytes(this.convertShort(userLen));
        this.addBytes(this.convertShort(userLen));
        this.addBytes(this.convertShort(64 + domainLen));
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addBytes(this.convertShort(hostLen));
        this.addBytes(this.convertShort(hostLen));
        this.addBytes(this.convertShort(64 + domainLen + userLen));
        for (int i = 0; i < 6; ++i) {
            this.addByte((byte)0);
        }
        this.addBytes(this.convertShort(finalLength));
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addByte((byte)6);
        this.addByte((byte)82);
        this.addByte((byte)0);
        this.addByte((byte)0);
        this.addBytes(domainBytes);
        this.addBytes(userBytes);
        this.addBytes(hostBytes);
        this.addBytes(this.hashPassword(password, nonce));
        return this.getResponse();
    }
    
    private byte[] hashPassword(final String password, final byte[] nonce) throws HttpException {
        final byte[] passw = getBytes(password.toUpperCase());
        final byte[] lmPw1 = new byte[7];
        final byte[] lmPw2 = new byte[7];
        int len = passw.length;
        if (len > 7) {
            len = 7;
        }
        int idx;
        for (idx = 0; idx < len; ++idx) {
            lmPw1[idx] = passw[idx];
        }
        while (idx < 7) {
            lmPw1[idx] = 0;
            ++idx;
        }
        len = passw.length;
        if (len > 14) {
            len = 14;
        }
        for (idx = 7; idx < len; ++idx) {
            lmPw2[idx - 7] = passw[idx];
        }
        while (idx < 14) {
            lmPw2[idx - 7] = 0;
            ++idx;
        }
        final byte[] magic = { 75, 71, 83, 33, 64, 35, 36, 37 };
        final byte[] lmHpw1 = this.encrypt(lmPw1, magic);
        final byte[] lmHpw2 = this.encrypt(lmPw2, magic);
        final byte[] lmHpw3 = new byte[21];
        for (int i = 0; i < lmHpw1.length; ++i) {
            lmHpw3[i] = lmHpw1[i];
        }
        for (int j = 0; j < lmHpw2.length; ++j) {
            lmHpw3[j + 8] = lmHpw2[j];
        }
        for (int k = 0; k < 5; ++k) {
            lmHpw3[k + 16] = 0;
        }
        final byte[] lmResp = new byte[24];
        this.calcResp(lmHpw3, nonce, lmResp);
        return lmResp;
    }
    
    private void calcResp(final byte[] keys, final byte[] plaintext, final byte[] results) throws HttpException {
        final byte[] keys2 = new byte[7];
        final byte[] keys3 = new byte[7];
        final byte[] keys4 = new byte[7];
        for (int i = 0; i < 7; ++i) {
            keys2[i] = keys[i];
        }
        for (int j = 0; j < 7; ++j) {
            keys3[j] = keys[j + 7];
        }
        for (int k = 0; k < 7; ++k) {
            keys4[k] = keys[k + 14];
        }
        final byte[] results2 = this.encrypt(keys2, plaintext);
        final byte[] results3 = this.encrypt(keys3, plaintext);
        final byte[] results4 = this.encrypt(keys4, plaintext);
        for (int l = 0; l < 8; ++l) {
            results[l] = results2[l];
        }
        for (int m = 0; m < 8; ++m) {
            results[m + 8] = results3[m];
        }
        for (int i2 = 0; i2 < 8; ++i2) {
            results[i2 + 16] = results4[i2];
        }
    }
    
    private byte[] convertShort(final int num) {
        final byte[] val = new byte[2];
        String hex;
        for (hex = Integer.toString(num, 16); hex.length() < 4; hex = "0" + hex) {}
        final String low = hex.substring(2, 4);
        final String high = hex.substring(0, 2);
        val[0] = (byte)Integer.parseInt(low, 16);
        val[1] = (byte)Integer.parseInt(high, 16);
        return val;
    }
    
    private static byte[] getBytes(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }
        try {
            return s.getBytes("ASCII");
        }
        catch (UnsupportedEncodingException unexpectedEncodingException) {
            throw new RuntimeException("NTLM requires ASCII support");
        }
    }
    
    static {
        LOG = LogFactory.getLog(NTLM.class);
    }
}
