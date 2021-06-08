// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.lang3.concurrent;

public interface ConcurrentInitializer<T>
{
    T get() throws ConcurrentException;
}
