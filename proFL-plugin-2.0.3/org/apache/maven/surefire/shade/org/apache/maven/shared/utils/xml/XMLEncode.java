// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.xml;

final class XMLEncode
{
    private static final int CDATA_BLOCK_THRESHOLD_LENGTH = 12;
    private static final char DEFAULT_QUOTE_CHAR = '\"';
    
    public static boolean isWhiteSpace(final String text) {
        for (int i = 0; i < text.length(); ++i) {
            final char c = text.charAt(i);
            if (!Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static String xmlEncodeTextForAttribute(final String text, final char quoteChar) {
        if (text == null) {
            return null;
        }
        return xmlEncodeTextAsPCDATA(text, true, quoteChar);
    }
    
    public static String xmlEncodeText(final String text) {
        if (text == null) {
            return null;
        }
        if (!needsEncoding(text)) {
            return text;
        }
        if (text.length() > 12) {
            final String cdata = xmlEncodeTextAsCDATABlock(text);
            if (cdata != null) {
                return cdata;
            }
        }
        return xmlEncodeTextAsPCDATA(text);
    }
    
    public static String xmlEncodeTextAsPCDATA(final String text) {
        if (text == null) {
            return null;
        }
        return xmlEncodeTextAsPCDATA(text, false);
    }
    
    public static String xmlEncodeTextAsPCDATA(final String text, final boolean forAttribute) {
        return xmlEncodeTextAsPCDATA(text, forAttribute, '\"');
    }
    
    public static String xmlEncodeTextAsPCDATA(final String text, final boolean forAttribute, final char quoteChar) {
        if (text == null) {
            return null;
        }
        final StringBuilder n = new StringBuilder(text.length() * 2);
        for (int i = 0; i < text.length(); ++i) {
            final char c = text.charAt(i);
            switch (c) {
                case '&': {
                    n.append("&amp;");
                    break;
                }
                case '<': {
                    n.append("&lt;");
                    break;
                }
                case '>': {
                    n.append("&gt;");
                    break;
                }
                case '\"': {
                    if (forAttribute) {
                        n.append("&quot;");
                        break;
                    }
                    n.append(c);
                    break;
                }
                case '\'': {
                    if (forAttribute) {
                        n.append("&apos;");
                        break;
                    }
                    n.append(c);
                    break;
                }
                case '\r': {
                    if (!forAttribute) {
                        n.append(c);
                        break;
                    }
                    if (i == text.length() - 1 || text.charAt(i + 1) != '\n') {
                        n.append("&#13;");
                        break;
                    }
                    break;
                }
                case '\n': {
                    if (forAttribute) {
                        n.append("&#10;");
                        break;
                    }
                    break;
                }
                default: {
                    n.append(c);
                    break;
                }
            }
        }
        if (forAttribute) {
            n.append(quoteChar);
            n.insert(0, quoteChar);
        }
        return n.toString();
    }
    
    public static String xmlEncodeTextAsCDATABlock(final String text) {
        if (text == null) {
            return null;
        }
        if (isCompatibleWithCDATABlock(text)) {
            return "<![CDATA[" + text + "]]>";
        }
        return null;
    }
    
    public static boolean needsEncoding(final String text) {
        return needsEncoding(text, false);
    }
    
    public static boolean needsEncoding(final String data, final boolean checkForAttr) {
        if (data == null) {
            return false;
        }
        for (int i = 0; i < data.length(); ++i) {
            final char c = data.charAt(i);
            if (c == '&' || c == '<' || (checkForAttr && (c == '\"' || c == '\''))) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isCompatibleWithCDATABlock(final String text) {
        return text != null && !text.contains("]]>");
    }
    
    public static String xmlDecodeTextToCDATA(final String pcdata) {
        if (pcdata == null) {
            return null;
        }
        final StringBuilder n = new StringBuilder(pcdata.length());
        for (int i = 0; i < pcdata.length(); ++i) {
            final char c = pcdata.charAt(i);
            if (c == '&') {
                final char c2 = lookAhead(1, i, pcdata);
                final char c3 = lookAhead(2, i, pcdata);
                final char c4 = lookAhead(3, i, pcdata);
                final char c5 = lookAhead(4, i, pcdata);
                final char c6 = lookAhead(5, i, pcdata);
                if (c2 == 'a' && c3 == 'm' && c4 == 'p' && c5 == ';') {
                    n.append("&");
                    i += 4;
                }
                else if (c2 == 'l' && c3 == 't' && c4 == ';') {
                    n.append("<");
                    i += 3;
                }
                else if (c2 == 'g' && c3 == 't' && c4 == ';') {
                    n.append(">");
                    i += 3;
                }
                else if (c2 == 'q' && c3 == 'u' && c4 == 'o' && c5 == 't' && c6 == ';') {
                    n.append("\"");
                    i += 5;
                }
                else if (c2 == 'a' && c3 == 'p' && c4 == 'o' && c5 == 's' && c6 == ';') {
                    n.append("'");
                    i += 5;
                }
                else {
                    n.append("&");
                }
            }
            else {
                n.append(c);
            }
        }
        return n.toString();
    }
    
    private static char lookAhead(final int la, final int offset, final String data) {
        try {
            return data.charAt(offset + la);
        }
        catch (StringIndexOutOfBoundsException e) {
            return '\0';
        }
    }
    
    private static boolean contains(final String text, final char[] chars) {
        if (text == null || chars == null || chars.length == 0) {
            return false;
        }
        for (int i = 0; i < text.length(); ++i) {
            final char c = text.charAt(i);
            for (final char aChar : chars) {
                if (aChar == c) {
                    return true;
                }
            }
        }
        return false;
    }
}
