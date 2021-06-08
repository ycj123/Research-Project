// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.sink;

public class StructureSink
{
    public static boolean isExternalLink(final String link) {
        final String text = link.toLowerCase();
        return text.indexOf("http:/") == 0 || text.indexOf("https:/") == 0 || text.indexOf("ftp:/") == 0 || text.indexOf("mailto:") == 0 || text.indexOf("file:/") == 0 || text.indexOf("../") == 0 || text.indexOf("./") == 0;
    }
    
    public static String linkToKey(final String text) {
        final int length = text.length();
        final StringBuffer buffer = new StringBuffer(length);
        for (int i = 0; i < length; ++i) {
            final char c = text.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                buffer.append(Character.toLowerCase(c));
            }
        }
        return buffer.toString();
    }
}
