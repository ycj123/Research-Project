// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource;

import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.apache.velocity.runtime.RuntimeServices;

public abstract class Resource
{
    protected RuntimeServices rsvc;
    protected ResourceLoader resourceLoader;
    protected static final long MILLIS_PER_SECOND = 1000L;
    protected long modificationCheckInterval;
    protected long lastModified;
    protected long nextCheck;
    protected String name;
    protected String encoding;
    protected Object data;
    
    public Resource() {
        this.rsvc = null;
        this.modificationCheckInterval = 0L;
        this.lastModified = 0L;
        this.nextCheck = 0L;
        this.encoding = "ISO-8859-1";
        this.data = null;
    }
    
    public void setRuntimeServices(final RuntimeServices rs) {
        this.rsvc = rs;
    }
    
    public abstract boolean process() throws ResourceNotFoundException, ParseErrorException, Exception;
    
    public boolean isSourceModified() {
        return this.resourceLoader.isSourceModified(this);
    }
    
    public void setModificationCheckInterval(final long modificationCheckInterval) {
        this.modificationCheckInterval = modificationCheckInterval;
    }
    
    public boolean requiresChecking() {
        return this.modificationCheckInterval > 0L && System.currentTimeMillis() >= this.nextCheck;
    }
    
    public void touch() {
        this.nextCheck = System.currentTimeMillis() + 1000L * this.modificationCheckInterval;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public long getLastModified() {
        return this.lastModified;
    }
    
    public void setLastModified(final long lastModified) {
        this.lastModified = lastModified;
    }
    
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
    
    public void setResourceLoader(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    public void setData(final Object data) {
        this.data = data;
    }
    
    public Object getData() {
        return this.data;
    }
}
