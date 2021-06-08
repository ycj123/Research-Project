// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.util;

public interface StringResourceRepository
{
    StringResource getStringResource(final String p0);
    
    void putStringResource(final String p0, final String p1);
    
    void removeStringResource(final String p0);
    
    void setEncoding(final String p0);
    
    String getEncoding();
}
