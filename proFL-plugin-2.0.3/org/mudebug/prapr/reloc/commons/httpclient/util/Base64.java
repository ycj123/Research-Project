// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.httpclient.util;

import org.mudebug.prapr.reloc.commons.httpclient.HttpConstants;

public final class Base64
{
    private static final int BASELENGTH = 255;
    private static final int LOOKUPLENGTH = 64;
    private static final int TWENTYFOURBITGROUP = 24;
    private static final int EIGHTBIT = 8;
    private static final int SIXTEENBIT = 16;
    private static final int SIXBIT = 6;
    private static final int FOURBYTE = 4;
    private static final int SIGN = -128;
    private static final byte PAD = 61;
    private static final byte[] BASE64_ALPHABET;
    private static final byte[] LOOKUP_BASE64_ALPHABET;
    
    private Base64() {
    }
    
    public static boolean isBase64(final String isValidString) {
        return isArrayByteBase64(HttpConstants.getAsciiBytes(isValidString));
    }
    
    static boolean isBase64(final byte octect) {
        return octect == 61 || Base64.BASE64_ALPHABET[octect] != -1;
    }
    
    public static boolean isArrayByteBase64(final byte[] arrayOctect) {
        final int length = arrayOctect.length;
        if (length == 0) {
            return true;
        }
        for (int i = 0; i < length; ++i) {
            if (!isBase64(arrayOctect[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static byte[] encode(final byte[] binaryData) {
        final int lengthDataBits = binaryData.length * 8;
        final int fewerThan24bits = lengthDataBits % 24;
        final int numberTriplets = lengthDataBits / 24;
        byte[] encodedData = null;
        if (fewerThan24bits != 0) {
            encodedData = new byte[(numberTriplets + 1) * 4];
        }
        else {
            encodedData = new byte[numberTriplets * 4];
        }
        byte k = 0;
        byte l = 0;
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
        int encodedIndex = 0;
        int dataIndex = 0;
        int i;
        byte val1;
        byte val2;
        byte val3;
        for (i = 0, i = 0; i < numberTriplets; ++i) {
            dataIndex = i * 3;
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            b3 = binaryData[dataIndex + 2];
            l = (byte)(b2 & 0xF);
            k = (byte)(b1 & 0x3);
            encodedIndex = i * 4;
            val1 = (((b1 & 0xFFFFFF80) == 0x0) ? ((byte)(b1 >> 2)) : ((byte)(b1 >> 2 ^ 0xC0)));
            val2 = (((b2 & 0xFFFFFF80) == 0x0) ? ((byte)(b2 >> 4)) : ((byte)(b2 >> 4 ^ 0xF0)));
            val3 = (((b3 & 0xFFFFFF80) == 0x0) ? ((byte)(b3 >> 6)) : ((byte)(b3 >> 6 ^ 0xFC)));
            encodedData[encodedIndex] = Base64.LOOKUP_BASE64_ALPHABET[val1];
            encodedData[encodedIndex + 1] = Base64.LOOKUP_BASE64_ALPHABET[val2 | k << 4];
            encodedData[encodedIndex + 2] = Base64.LOOKUP_BASE64_ALPHABET[l << 2 | val3];
            encodedData[encodedIndex + 3] = Base64.LOOKUP_BASE64_ALPHABET[b3 & 0x3F];
        }
        dataIndex = i * 3;
        encodedIndex = i * 4;
        if (fewerThan24bits == 8) {
            b1 = binaryData[dataIndex];
            k = (byte)(b1 & 0x3);
            val1 = (((b1 & 0xFFFFFF80) == 0x0) ? ((byte)(b1 >> 2)) : ((byte)(b1 >> 2 ^ 0xC0)));
            encodedData[encodedIndex] = Base64.LOOKUP_BASE64_ALPHABET[val1];
            encodedData[encodedIndex + 1] = Base64.LOOKUP_BASE64_ALPHABET[k << 4];
            encodedData[encodedIndex + 3] = (encodedData[encodedIndex + 2] = 61);
        }
        else if (fewerThan24bits == 16) {
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            l = (byte)(b2 & 0xF);
            k = (byte)(b1 & 0x3);
            val1 = (((b1 & 0xFFFFFF80) == 0x0) ? ((byte)(b1 >> 2)) : ((byte)(b1 >> 2 ^ 0xC0)));
            val2 = (((b2 & 0xFFFFFF80) == 0x0) ? ((byte)(b2 >> 4)) : ((byte)(b2 >> 4 ^ 0xF0)));
            encodedData[encodedIndex] = Base64.LOOKUP_BASE64_ALPHABET[val1];
            encodedData[encodedIndex + 1] = Base64.LOOKUP_BASE64_ALPHABET[val2 | k << 4];
            encodedData[encodedIndex + 2] = Base64.LOOKUP_BASE64_ALPHABET[l << 2];
            encodedData[encodedIndex + 3] = 61;
        }
        return encodedData;
    }
    
    public static byte[] decode(final byte[] base64Data) {
        if (base64Data.length == 0) {
            return new byte[0];
        }
        final int numberQuadruple = base64Data.length / 4;
        byte[] decodedData = null;
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        byte marker0 = 0;
        byte marker2 = 0;
        int encodedIndex = 0;
        int dataIndex = 0;
        int lastData = base64Data.length;
        while (base64Data[lastData - 1] == 61) {
            if (--lastData == 0) {
                return new byte[0];
            }
        }
        decodedData = new byte[lastData - numberQuadruple];
        for (int i = 0; i < numberQuadruple; ++i) {
            dataIndex = i * 4;
            marker0 = base64Data[dataIndex + 2];
            marker2 = base64Data[dataIndex + 3];
            b1 = Base64.BASE64_ALPHABET[base64Data[dataIndex]];
            b2 = Base64.BASE64_ALPHABET[base64Data[dataIndex + 1]];
            if (marker0 != 61 && marker2 != 61) {
                b3 = Base64.BASE64_ALPHABET[marker0];
                b4 = Base64.BASE64_ALPHABET[marker2];
                decodedData[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte)((b2 & 0xF) << 4 | (b3 >> 2 & 0xF));
                decodedData[encodedIndex + 2] = (byte)(b3 << 6 | b4);
            }
            else if (marker0 == 61) {
                decodedData[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
            }
            else if (marker2 == 61) {
                b3 = Base64.BASE64_ALPHABET[marker0];
                decodedData[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte)((b2 & 0xF) << 4 | (b3 >> 2 & 0xF));
            }
            encodedIndex += 3;
        }
        return decodedData;
    }
    
    static {
        BASE64_ALPHABET = new byte[255];
        LOOKUP_BASE64_ALPHABET = new byte[64];
        for (int i = 0; i < 255; ++i) {
            Base64.BASE64_ALPHABET[i] = -1;
        }
        for (int j = 90; j >= 65; --j) {
            Base64.BASE64_ALPHABET[j] = (byte)(j - 65);
        }
        for (int k = 122; k >= 97; --k) {
            Base64.BASE64_ALPHABET[k] = (byte)(k - 97 + 26);
        }
        for (int l = 57; l >= 48; --l) {
            Base64.BASE64_ALPHABET[l] = (byte)(l - 48 + 52);
        }
        Base64.BASE64_ALPHABET[43] = 62;
        Base64.BASE64_ALPHABET[47] = 63;
        for (int m = 0; m <= 25; ++m) {
            Base64.LOOKUP_BASE64_ALPHABET[m] = (byte)(65 + m);
        }
        for (int i2 = 26, j2 = 0; i2 <= 51; ++i2, ++j2) {
            Base64.LOOKUP_BASE64_ALPHABET[i2] = (byte)(97 + j2);
        }
        for (int i3 = 52, j3 = 0; i3 <= 61; ++i3, ++j3) {
            Base64.LOOKUP_BASE64_ALPHABET[i3] = (byte)(48 + j3);
        }
        Base64.LOOKUP_BASE64_ALPHABET[62] = 43;
        Base64.LOOKUP_BASE64_ALPHABET[63] = 47;
    }
}
