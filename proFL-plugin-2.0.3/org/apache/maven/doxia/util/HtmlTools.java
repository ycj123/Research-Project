// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.util;

import org.apache.maven.doxia.sink.StructureSink;
import java.io.UnsupportedEncodingException;

public class HtmlTools
{
    public static String escapeHTML(final String text) {
        if (text == null) {
            return "";
        }
        final int length = text.length();
        final StringBuffer buffer = new StringBuffer(length);
        for (int i = 0; i < length; ++i) {
            final char c = text.charAt(i);
            switch (c) {
                case '<': {
                    buffer.append("&lt;");
                    break;
                }
                case '>': {
                    buffer.append("&gt;");
                    break;
                }
                case '&': {
                    buffer.append("&amp;");
                    break;
                }
                case '\"': {
                    buffer.append("&quot;");
                    break;
                }
                default: {
                    buffer.append(c);
                    break;
                }
            }
        }
        return buffer.toString();
    }
    
    public static String encodeURL(final String url) {
        if (url == null) {
            return null;
        }
        final StringBuffer encoded = new StringBuffer();
        final int length = url.length();
        final char[] unicode = { '\0' };
        for (int i = 0; i < length; ++i) {
            final char c = url.charAt(i);
            switch (c) {
                case '!':
                case '#':
                case '$':
                case '&':
                case '\'':
                case '(':
                case ')':
                case '*':
                case '+':
                case ',':
                case '-':
                case '.':
                case '/':
                case ':':
                case ';':
                case '=':
                case '?':
                case '@':
                case '[':
                case ']':
                case '_':
                case '~': {
                    encoded.append(c);
                    break;
                }
                default: {
                    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                        encoded.append(c);
                        break;
                    }
                    byte[] bytes;
                    try {
                        unicode[0] = c;
                        bytes = new String(unicode, 0, 1).getBytes("UTF8");
                    }
                    catch (UnsupportedEncodingException cannotHappen) {
                        bytes = new byte[0];
                    }
                    for (int j = 0; j < bytes.length; ++j) {
                        final String hex = Integer.toHexString(bytes[j] & 0xFF);
                        encoded.append('%');
                        if (hex.length() == 1) {
                            encoded.append('0');
                        }
                        encoded.append(hex);
                    }
                    break;
                }
            }
        }
        return encoded.toString();
    }
    
    public static String encodeFragment(final String text) {
        if (text == null) {
            return null;
        }
        return encodeURL(StructureSink.linkToKey(text));
    }
    
    public static String encodeId(String id) {
        if (id == null) {
            return null;
        }
        id = id.trim();
        final int length = id.length();
        final StringBuffer buffer = new StringBuffer(length);
        for (int i = 0; i < length; ++i) {
            final char c = id.charAt(i);
            if (i == 0 && !Character.isLetter(c)) {
                buffer.append("a");
            }
            if (c == ' ') {
                buffer.append("_");
            }
            else if (Character.isLetterOrDigit(c) || c == '-' || c == '_' || c == ':' || c == '.') {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }
    
    public static boolean isId(final String text) {
        if (text == null || text.length() == 0) {
            return false;
        }
        for (int i = 0; i < text.length(); ++i) {
            final char c = text.charAt(i);
            if (i == 0 && !Character.isLetter(c)) {
                return false;
            }
            if (c == ' ') {
                return false;
            }
            if (!Character.isLetterOrDigit(c) && c != '-' && c != '_' && c != ':' && c != '.') {
                return false;
            }
        }
        return true;
    }
}
