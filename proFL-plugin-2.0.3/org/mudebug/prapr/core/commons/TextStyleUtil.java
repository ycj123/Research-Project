// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.commons;

public class TextStyleUtil
{
    private static final String BOLD_FACE = "\u001b[1m";
    private static final String UNDERLINED = "\u001b[4m";
    private static final String NORMAL = "\u001b[0m";
    
    public static String bold(final String text) {
        return "\u001b[1m" + text + "\u001b[0m";
    }
    
    public static String underlined(final String text) {
        return "\u001b[4m" + text + "\u001b[0m";
    }
}
