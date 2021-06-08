// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import java.util.List;

public class CommandUtils
{
    public static String getExaminedDirectory(final String s, final String str) {
        final int index = s.indexOf(str);
        final int beginIndex = index + str.length() + 1;
        if (index < 0 || s.length() < beginIndex + 1) {
            return null;
        }
        return s.substring(beginIndex);
    }
    
    public static String findUniqueString(final String original, final List list) {
        if (original == null) {
            return null;
        }
        final int index = list.indexOf(original);
        if (index >= 0) {
            return list.get(index);
        }
        final String s = new String(original);
        list.add(s);
        return s;
    }
}
