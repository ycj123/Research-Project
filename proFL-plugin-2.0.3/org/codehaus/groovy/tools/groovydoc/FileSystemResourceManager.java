// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.io.IOException;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.File;
import java.io.Reader;

public class FileSystemResourceManager implements ResourceManager
{
    private String basedir;
    private static final String FS = "/";
    
    public FileSystemResourceManager() {
        this.basedir = "";
    }
    
    public FileSystemResourceManager(final String basedir) {
        this.basedir = basedir + "/";
    }
    
    public Reader getReader(final String resourceName) throws IOException {
        return DefaultGroovyMethods.newReader(new File(this.basedir + resourceName));
    }
}
