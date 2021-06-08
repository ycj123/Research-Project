// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.plexus.velocity;

import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

public class ContextClassLoaderResourceLoader extends ResourceLoader
{
    public void init(final ExtendedProperties configuration) {
    }
    
    public synchronized InputStream getResourceStream(final String name) throws ResourceNotFoundException {
        InputStream result = null;
        if (name == null || name.length() == 0) {
            throw new ResourceNotFoundException("No template name provided");
        }
        try {
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            result = classLoader.getResourceAsStream(name);
        }
        catch (Exception fnfe) {
            throw new ResourceNotFoundException(fnfe.getMessage());
        }
        return result;
    }
    
    public boolean isSourceModified(final Resource resource) {
        return false;
    }
    
    public long getLastModified(final Resource resource) {
        return 0L;
    }
}
