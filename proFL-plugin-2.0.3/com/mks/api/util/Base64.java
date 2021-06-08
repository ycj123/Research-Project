// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

public class Base64
{
    private static final char[] set;
    
    public static String encode(final String string) {
        return (string == null) ? null : encode(string.getBytes());
    }
    
    public static String encode(final byte[] data) {
        if (data == null) {
            return null;
        }
        final StringBuffer result = new StringBuffer();
        int bytesLeft = data.length;
        for (int index = 0; index < data.length; index += 3) {
            final byte b1 = data[index];
            final byte b2 = (byte)((bytesLeft < 2) ? 0 : data[index + 1]);
            final byte b3 = (byte)((bytesLeft < 3) ? 0 : data[index + 2]);
            result.append(Base64.set[b1 >>> 2 & 0x3F]);
            result.append(Base64.set[(b1 << 4 & 0x30) + (b2 >>> 4 & 0xF)]);
            result.append((bytesLeft < 2) ? '=' : Base64.set[(b2 << 2 & 0x3C) + (b3 >>> 6 & 0x3)]);
            result.append((bytesLeft < 3) ? '=' : Base64.set[b3 & 0x3F]);
            bytesLeft -= 3;
        }
        return result.toString();
    }
    
    public static String decode(final String string) {
        return (string == null) ? null : new String(decodeToBytes(string));
    }
    
    public static byte[] decodeToBytes(final String string) {
        if (string == null) {
            return null;
        }
        final int length = string.length();
        int trim = 0;
        final byte[] result = new byte[(length + 3) / 4 * 3];
        int resultIndex = 0;
        for (int index = 0; index < length; index += 4) {
            final byte[] sextet = new byte[4];
            for (int subindex = 0; subindex < 4; ++subindex) {
                if (index + subindex >= length || string.charAt(index + subindex) == '=') {
                    sextet[subindex] = 0;
                    ++trim;
                }
                else {
                    for (byte setindex = 0; setindex < 64; ++setindex) {
                        if (Base64.set[setindex] == string.charAt(index + subindex)) {
                            sextet[subindex] = setindex;
                            break;
                        }
                    }
                }
            }
            result[resultIndex++] = (byte)(sextet[0] << 2 | sextet[1] >> 4);
            result[resultIndex++] = (byte)((sextet[1] & 0xF) << 4 | sextet[2] >> 2);
            result[resultIndex++] = (byte)((sextet[2] & 0x3) << 6 | sextet[3]);
        }
        if (trim == 0) {
            return result;
        }
        final int trimmedLength = result.length - trim;
        final byte[] trimmedResult = new byte[trimmedLength];
        System.arraycopy(result, 0, trimmedResult, 0, trimmedLength);
        return trimmedResult;
    }
    
    public static void main(final String[] args) {
        if (args.length != 1) {
            System.err.println("usage: Base64 <string>");
            return;
        }
        System.out.println("Encoding: " + args[0]);
        final String encoded = encode(args[0]);
        System.out.println("Got: " + encoded);
        System.out.println("Decoding: " + encoded);
        System.out.println("Got: " + decode(encoded));
    }
    
    static {
        set = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
    }
}
