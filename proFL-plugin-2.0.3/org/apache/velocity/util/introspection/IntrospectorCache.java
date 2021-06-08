// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

public interface IntrospectorCache
{
    void clear();
    
    ClassMap get(final Class p0);
    
    ClassMap put(final Class p0);
    
    void addListener(final IntrospectorCacheListener p0);
    
    void removeListener(final IntrospectorCacheListener p0);
}
