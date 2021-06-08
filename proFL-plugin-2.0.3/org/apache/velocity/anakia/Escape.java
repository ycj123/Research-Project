// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.anakia;

public class Escape
{
    public static final String LINE_SEPARATOR;
    
    public static final String getText(final String st) {
        final StringBuffer buff = new StringBuffer();
        final char[] block = st.toCharArray();
        String stEntity = null;
        int i = 0;
        int last = 0;
        while (i < block.length) {
            switch (block[i]) {
                case '<': {
                    stEntity = "&lt;";
                    break;
                }
                case '>': {
                    stEntity = "&gt;";
                    break;
                }
                case '&': {
                    stEntity = "&amp;";
                    break;
                }
                case '\"': {
                    stEntity = "&quot;";
                    break;
                }
                case '\n': {
                    stEntity = Escape.LINE_SEPARATOR;
                    break;
                }
            }
            if (stEntity != null) {
                buff.append(block, last, i - last);
                buff.append(stEntity);
                stEntity = null;
                last = i + 1;
            }
            ++i;
        }
        if (last < block.length) {
            buff.append(block, last, i - last);
        }
        return buff.toString();
    }
    
    static {
        LINE_SEPARATOR = System.getProperty("line.separator");
    }
}
