// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.texen.util;

import java.io.File;

public class FileUtil
{
    public static String mkdir(final String s) {
        try {
            if (new File(s).mkdirs()) {
                return "Created dir: " + s;
            }
            return "Failed to create dir or dir already exists: " + s;
        }
        catch (Exception e) {
            return e.toString();
        }
    }
    
    public static File file(final String s) {
        final File f = new File(s);
        return f;
    }
    
    public static File file(final String base, final String s) {
        final File f = new File(base, s);
        return f;
    }
}
