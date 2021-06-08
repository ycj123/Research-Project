// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

public interface IntrospectorCacheListener
{
    void triggerClear();
    
    void triggerGet(final Class p0, final ClassMap p1);
    
    void triggerPut(final Class p0, final ClassMap p1);
}
