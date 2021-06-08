// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire;

import java.util.Iterator;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.SelectorUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.maven.surefire.util.ScannerFilter;

public class SpecificTestClassFilter implements ScannerFilter
{
    private static final char FS;
    private static final String JAVA_CLASS_FILE_EXTENSION = ".class";
    private Set<String> names;
    
    public SpecificTestClassFilter(final String[] classNames) {
        if (classNames != null && classNames.length > 0) {
            Collections.addAll(this.names = new HashSet<String>(), classNames);
        }
    }
    
    public boolean accept(final Class testClass) {
        boolean result = true;
        if (this.names != null && !this.names.isEmpty()) {
            final String className = testClass.getName().replace('.', SpecificTestClassFilter.FS) + ".class";
            boolean found = false;
            for (String pattern : this.names) {
                if ('\\' == SpecificTestClassFilter.FS) {
                    pattern = pattern.replace('/', SpecificTestClassFilter.FS);
                }
                if (SelectorUtils.matchPath(pattern, className, true)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result = false;
            }
        }
        return result;
    }
    
    static {
        FS = System.getProperty("file.separator").charAt(0);
    }
}
