// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.oro.util;

public interface Cache
{
    void addElement(final Object p0, final Object p1);
    
    Object getElement(final Object p0);
    
    int size();
    
    int capacity();
}
