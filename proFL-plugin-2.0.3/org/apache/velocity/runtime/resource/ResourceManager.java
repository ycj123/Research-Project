// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource;

import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeServices;

public interface ResourceManager
{
    public static final int RESOURCE_TEMPLATE = 1;
    public static final int RESOURCE_CONTENT = 2;
    
    void initialize(final RuntimeServices p0) throws Exception;
    
    Resource getResource(final String p0, final int p1, final String p2) throws ResourceNotFoundException, ParseErrorException, Exception;
    
    String getLoaderNameForResource(final String p0);
}
