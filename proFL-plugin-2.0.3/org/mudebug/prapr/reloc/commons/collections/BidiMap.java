// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections;

public interface BidiMap extends IterableMap
{
    MapIterator mapIterator();
    
    Object put(final Object p0, final Object p1);
    
    Object getKey(final Object p0);
    
    Object removeValue(final Object p0);
    
    BidiMap inverseBidiMap();
}
