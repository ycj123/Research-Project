// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

public interface VelPropertyGet
{
    Object invoke(final Object p0) throws Exception;
    
    boolean isCacheable();
    
    String getMethodName();
}
