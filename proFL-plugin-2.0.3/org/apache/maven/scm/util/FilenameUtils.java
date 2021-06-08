// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.util;

import org.codehaus.plexus.util.StringUtils;
import java.io.File;

public final class FilenameUtils
{
    private FilenameUtils() {
    }
    
    public static String normalizeFilename(final File file) {
        return normalizeFilename(file.getName());
    }
    
    public static String normalizeFilename(final String filename) {
        return StringUtils.replace(StringUtils.replace(filename, "\\", "/"), "//", "/");
    }
}
