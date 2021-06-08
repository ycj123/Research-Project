// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import java.util.Collections;
import java.io.File;
import org.pitest.functional.F;

class PathToJavaClassConverter implements F<String, Iterable<String>>
{
    private final String sourceRoot;
    
    PathToJavaClassConverter(final String sourceRoot) {
        this.sourceRoot = sourceRoot;
    }
    
    @Override
    public Iterable<String> apply(final String a) {
        final File f = new File(a);
        final String modifiedFilePath = f.getAbsolutePath();
        final String fileName = f.getName();
        if (modifiedFilePath.startsWith(this.sourceRoot) && fileName.indexOf(46) != -1) {
            return this.createClassGlobFromFilePath(this.sourceRoot, modifiedFilePath);
        }
        return (Iterable<String>)Collections.emptyList();
    }
    
    private Iterable<String> createClassGlobFromFilePath(final String sourceRoot, final String modifiedFilePath) {
        final String rootedPath = modifiedFilePath.substring(sourceRoot.length() + 1, modifiedFilePath.length());
        return Collections.singleton(this.stripFileExtension(rootedPath).replace('/', '.').replace('\\', '.') + "*");
    }
    
    private String stripFileExtension(final String rootedPath) {
        return rootedPath.substring(0, rootedPath.lastIndexOf("."));
    }
}
