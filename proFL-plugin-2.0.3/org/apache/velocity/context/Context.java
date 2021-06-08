// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.context;

public interface Context
{
    Object put(final String p0, final Object p1);
    
    Object get(final String p0);
    
    boolean containsKey(final Object p0);
    
    Object[] getKeys();
    
    Object remove(final Object p0);
}
