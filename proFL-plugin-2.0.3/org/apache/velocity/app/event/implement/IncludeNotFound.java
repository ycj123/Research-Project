// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event.implement;

import org.apache.velocity.util.StringUtils;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.util.RuntimeServicesAware;
import org.apache.velocity.app.event.IncludeEventHandler;

public class IncludeNotFound implements IncludeEventHandler, RuntimeServicesAware
{
    private static final String DEFAULT_NOT_FOUND = "notfound.vm";
    private static final String PROPERTY_NOT_FOUND = "eventhandler.include.notfound";
    private RuntimeServices rs;
    String notfound;
    
    public IncludeNotFound() {
        this.rs = null;
    }
    
    public String includeEvent(final String includeResourcePath, final String currentResourcePath, final String directiveName) {
        final boolean exists = this.rs.getLoaderNameForResource(includeResourcePath) != null;
        if (exists) {
            return includeResourcePath;
        }
        if (this.rs.getLoaderNameForResource(this.notfound) == null) {
            return this.notfound;
        }
        this.rs.getLog().error("Can't find include not found page: " + this.notfound);
        return null;
    }
    
    public void setRuntimeServices(final RuntimeServices rs) {
        this.rs = rs;
        this.notfound = StringUtils.nullTrim(rs.getString("eventhandler.include.notfound", "notfound.vm"));
    }
}
