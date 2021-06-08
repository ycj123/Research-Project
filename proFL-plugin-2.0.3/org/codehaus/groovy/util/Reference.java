// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

public interface Reference<T, V extends Finalizable>
{
    T get();
    
    void clear();
    
    V getHandler();
}
