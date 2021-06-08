// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.module.site.manager;

public class SiteModuleNotFoundException extends Exception
{
    public SiteModuleNotFoundException(final String message) {
        super(message);
    }
    
    public SiteModuleNotFoundException(final Throwable cause) {
        super(cause);
    }
    
    public SiteModuleNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
