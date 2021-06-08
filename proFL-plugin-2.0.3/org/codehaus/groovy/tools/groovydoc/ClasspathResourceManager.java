// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStream;

public class ClasspathResourceManager implements ResourceManager
{
    ClassLoader classLoader;
    
    public ClasspathResourceManager() {
        this.classLoader = this.getClass().getClassLoader();
    }
    
    public ClasspathResourceManager(final ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    
    public InputStream getInputStream(final String resourceName) throws IOException {
        return this.classLoader.getResourceAsStream(resourceName);
    }
    
    public Reader getReader(final String resourceName) throws IOException {
        return DefaultGroovyMethods.newReader(this.getInputStream(resourceName));
    }
}
