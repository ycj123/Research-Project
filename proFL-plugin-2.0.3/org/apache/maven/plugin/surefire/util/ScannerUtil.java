// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.util;

import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import org.mudebug.prapr.reloc.commons.lang3.StringUtils;
import javax.annotation.Nonnull;

final class ScannerUtil
{
    private static final String FS;
    private static final String JAVA_SOURCE_FILE_EXTENSION = ".java";
    private static final String JAVA_CLASS_FILE_EXTENSION = ".class";
    private static final boolean IS_NON_UNIX_FS;
    
    private ScannerUtil() {
    }
    
    @Nonnull
    public static String convertToJavaClassName(@Nonnull final String test) {
        return StringUtils.removeEnd(test, ".class").replace(ScannerUtil.FS, ".");
    }
    
    @Nonnull
    public static String convertJarFileResourceToJavaClassName(@Nonnull final String test) {
        return StringUtils.removeEnd(test, ".class").replace("/", ".");
    }
    
    @Nonnull
    public static String convertSlashToSystemFileSeparator(@Nonnull final String path) {
        return ScannerUtil.IS_NON_UNIX_FS ? path.replace("/", ScannerUtil.FS) : path;
    }
    
    @Nonnull
    public static String stripBaseDir(final String basedir, final String test) {
        return StringUtils.removeStart(test, basedir);
    }
    
    @Nonnull
    public static String[] processIncludesExcludes(@Nonnull final List<String> list) {
        final List<String> newList = new ArrayList<String>();
        for (final Object aList : list) {
            final String include = (String)aList;
            final String[] includes = include.split(",");
            Collections.addAll(newList, includes);
        }
        final String[] incs = new String[newList.size()];
        for (int i = 0; i < incs.length; ++i) {
            String inc = newList.get(i);
            if (inc.endsWith(".java")) {
                inc = StringUtils.removeEnd(inc, ".java") + ".class";
            }
            incs[i] = convertSlashToSystemFileSeparator(inc);
        }
        return incs;
    }
    
    static {
        FS = System.getProperty("file.separator");
        IS_NON_UNIX_FS = !ScannerUtil.FS.equals("/");
    }
}
