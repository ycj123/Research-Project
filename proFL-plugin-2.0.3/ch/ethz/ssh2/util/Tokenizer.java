// 
// Decompiled by Procyon v0.5.36
// 

package ch.ethz.ssh2.util;

public class Tokenizer
{
    public static String[] parseTokens(final String source, final char delimiter) {
        int numtoken = 1;
        for (int i = 0; i < source.length(); ++i) {
            if (source.charAt(i) == delimiter) {
                ++numtoken;
            }
        }
        final String[] list = new String[numtoken];
        int nextfield = 0;
        for (int j = 0; j < numtoken; ++j) {
            if (nextfield >= source.length()) {
                list[j] = "";
            }
            else {
                int idx = source.indexOf(delimiter, nextfield);
                if (idx == -1) {
                    idx = source.length();
                }
                list[j] = source.substring(nextfield, idx);
                nextfield = idx + 1;
            }
        }
        return list;
    }
}
