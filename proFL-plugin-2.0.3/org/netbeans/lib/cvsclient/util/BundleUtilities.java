// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.util;

import java.util.ResourceBundle;

public class BundleUtilities
{
    public static String getPackageName(final Class clazz) {
        final String name = clazz.getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex < 0) {
            return "";
        }
        return name.substring(0, lastIndex);
    }
    
    public static String getResourceName(final Class clazz, final String str) {
        return getPackageName(clazz).replace('.', '/') + '/' + str;
    }
    
    public static ResourceBundle getResourceBundle(final Class clazz, final String s) {
        return ResourceBundle.getBundle(getResourceName(clazz, s));
    }
}
