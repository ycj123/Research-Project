// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.io.UnsupportedEncodingException;
import groovy.lang.StringWriterIOException;
import java.io.StringWriter;
import java.io.IOException;
import java.io.Writer;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Writable;

public class EncodingGroovyMethods
{
    private static final char[] T_TABLE;
    private static final String CHUNK_SEPARATOR = "\r\n";
    
    public static Writable encodeBase64(final Byte[] data, final boolean chunked) {
        return encodeBase64(DefaultTypeTransformation.convertToByteArray(data), chunked);
    }
    
    public static Writable encodeBase64(final Byte[] data) {
        return encodeBase64(DefaultTypeTransformation.convertToByteArray(data), false);
    }
    
    public static Writable encodeBase64(final byte[] data, final boolean chunked) {
        return new Writable() {
            public Writer writeTo(final Writer writer) throws IOException {
                int charCount = 0;
                final int dLimit = data.length / 3 * 3;
                for (int dIndex = 0; dIndex != dLimit; dIndex += 3) {
                    final int d = (data[dIndex] & 0xFF) << 16 | (data[dIndex + 1] & 0xFF) << 8 | (data[dIndex + 2] & 0xFF);
                    writer.write(EncodingGroovyMethods.T_TABLE[d >> 18]);
                    writer.write(EncodingGroovyMethods.T_TABLE[d >> 12 & 0x3F]);
                    writer.write(EncodingGroovyMethods.T_TABLE[d >> 6 & 0x3F]);
                    writer.write(EncodingGroovyMethods.T_TABLE[d & 0x3F]);
                    if (chunked && ++charCount == 19) {
                        writer.write("\r\n");
                        charCount = 0;
                    }
                }
                if (dLimit != data.length) {
                    int d2 = (data[dLimit] & 0xFF) << 16;
                    if (dLimit + 1 != data.length) {
                        d2 |= (data[dLimit + 1] & 0xFF) << 8;
                    }
                    writer.write(EncodingGroovyMethods.T_TABLE[d2 >> 18]);
                    writer.write(EncodingGroovyMethods.T_TABLE[d2 >> 12 & 0x3F]);
                    writer.write((dLimit + 1 < data.length) ? EncodingGroovyMethods.T_TABLE[d2 >> 6 & 0x3F] : '=');
                    writer.write(61);
                    if (chunked && charCount != 0) {
                        writer.write("\r\n");
                    }
                }
                return writer;
            }
            
            @Override
            public String toString() {
                final StringWriter buffer = new StringWriter();
                try {
                    this.writeTo(buffer);
                }
                catch (IOException e) {
                    throw new StringWriterIOException(e);
                }
                return buffer.toString();
            }
        };
    }
    
    public static Writable encodeBase64(final byte[] data) {
        return encodeBase64(data, false);
    }
    
    public static byte[] decodeBase64(final String value) {
        int byteShift = 4;
        int tmp = 0;
        boolean done = false;
        final StringBuilder buffer = new StringBuilder();
        for (int i = 0; i != value.length(); ++i) {
            final char c = value.charAt(i);
            final int sixBit = (c < '{') ? EncodingGroovyMethodsSupport.TRANSLATE_TABLE[c] : 66;
            if (sixBit < 64) {
                if (done) {
                    throw new RuntimeException("= character not at end of base64 value");
                }
                tmp = (tmp << 6 | sixBit);
                if (byteShift-- != 4) {
                    buffer.append((char)(tmp >> byteShift * 2 & 0xFF));
                }
            }
            else if (sixBit == 64) {
                --byteShift;
                done = true;
            }
            else if (sixBit == 66) {
                throw new RuntimeException("bad character in base64 value");
            }
            if (byteShift == 0) {
                byteShift = 4;
            }
        }
        try {
            return buffer.toString().getBytes("ISO-8859-1");
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Base 64 decode produced byte values > 255");
        }
    }
    
    static {
        T_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    }
}
