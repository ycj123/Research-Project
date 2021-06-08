// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.util;

import java.io.IOException;
import java.io.File;

public class WorkspaceUtils
{
    public static boolean isSameFile(final File file1, final String filename2) {
        return isSameFile(file1, (filename2 == null) ? null : new File(filename2));
    }
    
    public static boolean isSameFile(File file1, File file2) {
        if (file1 == file2 || (file1 == null && file2 == null)) {
            return true;
        }
        if (file1 == null || file2 == null) {
            return false;
        }
        try {
            file1 = file1.getCanonicalFile();
        }
        catch (IOException ex) {}
        try {
            file2 = file2.getCanonicalFile();
        }
        catch (IOException ex2) {}
        return file1.equals(file2);
    }
    
    private WorkspaceUtils() {
    }
}
