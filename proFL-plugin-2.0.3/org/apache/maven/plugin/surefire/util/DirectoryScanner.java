// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.util;

import java.util.ArrayList;
import org.apache.maven.surefire.util.DefaultScanResult;
import java.util.List;
import java.io.File;

public class DirectoryScanner
{
    private final File basedir;
    private final List<String> includes;
    private final List<String> excludes;
    private final List<String> specificTests;
    
    public DirectoryScanner(final File basedir, final List<String> includes, final List<String> excludes, final List<String> specificTests) {
        this.basedir = basedir;
        this.includes = includes;
        this.excludes = excludes;
        this.specificTests = specificTests;
    }
    
    public DefaultScanResult scan() {
        final String[] specific = (this.specificTests == null) ? new String[0] : ScannerUtil.processIncludesExcludes(this.specificTests);
        final SpecificFileFilter specificTestFilter = new SpecificFileFilter(specific);
        final List<String> result = new ArrayList<String>();
        if (this.basedir.exists()) {
            final org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.DirectoryScanner scanner = new org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.DirectoryScanner();
            scanner.setBasedir(this.basedir);
            if (this.includes != null) {
                scanner.setIncludes(ScannerUtil.processIncludesExcludes(this.includes));
            }
            if (this.excludes != null) {
                scanner.setExcludes(ScannerUtil.processIncludesExcludes(this.excludes));
            }
            scanner.scan();
            for (final String test : scanner.getIncludedFiles()) {
                if (specificTestFilter.accept(ScannerUtil.convertSlashToSystemFileSeparator(ScannerUtil.stripBaseDir(this.basedir.getAbsolutePath(), test)))) {
                    result.add(ScannerUtil.convertToJavaClassName(test));
                }
            }
        }
        return new DefaultScanResult(result);
    }
}
