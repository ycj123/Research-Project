// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.loader;

import java.net.URLConnection;
import org.apache.velocity.runtime.resource.Resource;
import java.io.IOException;
import java.net.URL;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import java.util.HashMap;

public class URLResourceLoader extends ResourceLoader
{
    private String[] roots;
    protected HashMap templateRoots;
    
    public URLResourceLoader() {
        this.roots = null;
        this.templateRoots = null;
    }
    
    public void init(final ExtendedProperties configuration) {
        this.log.trace("URLResourceLoader : initialization starting.");
        this.roots = configuration.getStringArray("root");
        if (this.log.isInfoEnabled()) {
            for (int i = 0; i < this.roots.length; ++i) {
                this.log.info("URLResourceLoader : adding root '" + this.roots[i] + "'");
            }
        }
        this.templateRoots = new HashMap();
        this.log.trace("URLResourceLoader : initialization complete.");
    }
    
    public synchronized InputStream getResourceStream(final String name) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(name)) {
            throw new ResourceNotFoundException("URLResourceLoader : No template name provided");
        }
        InputStream inputStream = null;
        Exception exception = null;
        for (int i = 0; i < this.roots.length; ++i) {
            try {
                final URL u = new URL(this.roots[i] + name);
                inputStream = u.openStream();
                if (inputStream != null) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("URLResourceLoader: Found '" + name + "' at '" + this.roots[i] + "'");
                    }
                    this.templateRoots.put(name, this.roots[i]);
                    break;
                }
            }
            catch (IOException ioe) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("URLResourceLoader: Exception when looking for '" + name + "' at '" + this.roots[i] + "'", ioe);
                }
                if (exception == null) {
                    exception = ioe;
                }
            }
        }
        if (inputStream == null) {
            String msg;
            if (exception == null) {
                msg = "URLResourceLoader : Resource '" + name + "' not found.";
            }
            else {
                msg = exception.getMessage();
            }
            throw new ResourceNotFoundException(msg);
        }
        return inputStream;
    }
    
    public boolean isSourceModified(final Resource resource) {
        final long fileLastModified = this.getLastModified(resource);
        return fileLastModified == 0L || fileLastModified != resource.getLastModified();
    }
    
    public long getLastModified(final Resource resource) {
        final String name = resource.getName();
        final String root = this.templateRoots.get(name);
        try {
            final URL u = new URL(root + name);
            final URLConnection conn = u.openConnection();
            return conn.getLastModified();
        }
        catch (IOException ioe) {
            this.log.warn("URLResourceLoader: '" + name + "' is no longer reachable at '" + root + "'", ioe);
            return 0L;
        }
    }
}
