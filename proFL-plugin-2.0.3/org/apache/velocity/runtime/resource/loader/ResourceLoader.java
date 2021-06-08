// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.loader;

import org.apache.velocity.runtime.resource.ResourceCacheImpl;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.runtime.RuntimeServices;

public abstract class ResourceLoader
{
    protected boolean isCachingOn;
    protected long modificationCheckInterval;
    protected String className;
    protected RuntimeServices rsvc;
    protected Log log;
    
    public ResourceLoader() {
        this.isCachingOn = false;
        this.modificationCheckInterval = 2L;
        this.className = null;
        this.rsvc = null;
        this.log = null;
    }
    
    public void commonInit(final RuntimeServices rs, final ExtendedProperties configuration) {
        this.rsvc = rs;
        this.log = this.rsvc.getLog();
        try {
            this.isCachingOn = configuration.getBoolean("cache", false);
        }
        catch (Exception e) {
            this.isCachingOn = false;
            this.log.error("Exception using default of '" + this.isCachingOn + '\'', e);
        }
        try {
            this.modificationCheckInterval = configuration.getLong("modificationCheckInterval", 0L);
        }
        catch (Exception e) {
            this.modificationCheckInterval = 0L;
            this.log.error("Exception using default of '" + this.modificationCheckInterval + '\'', e);
        }
        this.className = ResourceCacheImpl.class.getName();
        try {
            this.className = configuration.getString("class", this.className);
        }
        catch (Exception e) {
            this.log.error("Exception using default of '" + this.className + '\'', e);
        }
    }
    
    public abstract void init(final ExtendedProperties p0);
    
    public abstract InputStream getResourceStream(final String p0) throws ResourceNotFoundException;
    
    public abstract boolean isSourceModified(final Resource p0);
    
    public abstract long getLastModified(final Resource p0);
    
    public String getClassName() {
        return this.className;
    }
    
    public void setCachingOn(final boolean value) {
        this.isCachingOn = value;
    }
    
    public boolean isCachingOn() {
        return this.isCachingOn;
    }
    
    public void setModificationCheckInterval(final long modificationCheckInterval) {
        this.modificationCheckInterval = modificationCheckInterval;
    }
    
    public long getModificationCheckInterval() {
        return this.modificationCheckInterval;
    }
}
