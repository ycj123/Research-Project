// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

public interface VelPropertySet
{
    Object invoke(final Object p0, final Object p1) throws Exception;
    
    boolean isCacheable();
    
    String getMethodName();
}
