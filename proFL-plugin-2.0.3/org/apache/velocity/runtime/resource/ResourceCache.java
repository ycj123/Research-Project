// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource;

import java.util.Iterator;
import org.apache.velocity.runtime.RuntimeServices;

public interface ResourceCache
{
    void initialize(final RuntimeServices p0);
    
    Resource get(final Object p0);
    
    Resource put(final Object p0, final Resource p1);
    
    Resource remove(final Object p0);
    
    Iterator enumerateKeys();
}
