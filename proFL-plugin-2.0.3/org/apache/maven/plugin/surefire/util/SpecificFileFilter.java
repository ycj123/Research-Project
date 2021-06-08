// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.util;

import java.util.Iterator;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.SelectorUtils;
import java.util.HashSet;
import javax.annotation.Nullable;
import java.util.Set;

public class SpecificFileFilter
{
    private Set<String> names;
    
    public SpecificFileFilter(@Nullable final String[] classNames) {
        if (classNames != null && classNames.length > 0) {
            this.names = new HashSet<String>();
            for (final String name : classNames) {
                this.names.add(ScannerUtil.convertSlashToSystemFileSeparator(name));
            }
        }
    }
    
    public boolean accept(@Nullable final String resourceName) {
        if (this.names != null && !this.names.isEmpty()) {
            for (final String pattern : this.names) {
                if (SelectorUtils.matchPath(pattern, resourceName, true)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
