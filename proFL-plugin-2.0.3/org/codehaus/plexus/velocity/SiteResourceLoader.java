// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.plexus.velocity;

import org.apache.velocity.runtime.resource.Resource;
import java.io.FileNotFoundException;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.io.FileInputStream;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

public class SiteResourceLoader extends ResourceLoader
{
    private static String resource;
    
    public static void setResource(final String staticResource) {
        SiteResourceLoader.resource = staticResource;
    }
    
    public void init(final ExtendedProperties p) {
    }
    
    public InputStream getResourceStream(final String name) throws ResourceNotFoundException {
        if (SiteResourceLoader.resource != null) {
            try {
                return new FileInputStream(SiteResourceLoader.resource);
            }
            catch (FileNotFoundException e) {
                throw new ResourceNotFoundException("Cannot find resource, make sure you set the right resource.");
            }
        }
        return null;
    }
    
    public boolean isSourceModified(final Resource resource) {
        return false;
    }
    
    public long getLastModified(final Resource resource) {
        return 0L;
    }
}
