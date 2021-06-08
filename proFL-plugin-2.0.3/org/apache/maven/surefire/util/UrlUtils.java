// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util;

import java.net.MalformedURLException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.io.File;
import java.util.BitSet;

public class UrlUtils
{
    private static final BitSet UNRESERVED;
    private static final int RADIX = 16;
    private static final int MASK = 15;
    private static final String ENCODING = "UTF-8";
    
    private UrlUtils() {
    }
    
    public static URL getURL(final File file) throws MalformedURLException {
        final URL url = file.toURL();
        try {
            final byte[] bytes = url.toString().getBytes("UTF-8");
            final StringBuilder buf = new StringBuilder(bytes.length);
            for (final byte b : bytes) {
                if (b > 0 && UrlUtils.UNRESERVED.get(b)) {
                    buf.append((char)b);
                }
                else {
                    buf.append('%');
                    buf.append(Character.forDigit(b >>> 4 & 0xF, 16));
                    buf.append(Character.forDigit(b & 0xF, 16));
                }
            }
            return new URL(buf.toString());
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    static {
        UNRESERVED = new BitSet(256);
        try {
            final byte[] arr$;
            final byte[] bytes = arr$ = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'():/".getBytes("UTF-8");
            for (final byte aByte : arr$) {
                UrlUtils.UNRESERVED.set(aByte);
            }
        }
        catch (UnsupportedEncodingException ex) {}
    }
}
