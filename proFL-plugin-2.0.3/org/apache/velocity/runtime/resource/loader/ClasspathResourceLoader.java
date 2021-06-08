// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.loader;

import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.util.ExceptionUtils;
import org.apache.velocity.util.ClassUtils;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;

public class ClasspathResourceLoader extends ResourceLoader
{
    public void init(final ExtendedProperties configuration) {
        if (this.log.isTraceEnabled()) {
            this.log.trace("ClasspathResourceLoader : initialization complete.");
        }
    }
    
    public InputStream getResourceStream(final String name) throws ResourceNotFoundException {
        InputStream result = null;
        if (StringUtils.isEmpty(name)) {
            throw new ResourceNotFoundException("No template name provided");
        }
        try {
            result = ClassUtils.getResourceAsStream(this.getClass(), name);
        }
        catch (Exception fnfe) {
            throw (ResourceNotFoundException)ExceptionUtils.createWithCause(ResourceNotFoundException.class, "problem with template: " + name, fnfe);
        }
        if (result == null) {
            final String msg = "ClasspathResourceLoader Error: cannot find resource " + name;
            throw new ResourceNotFoundException(msg);
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
