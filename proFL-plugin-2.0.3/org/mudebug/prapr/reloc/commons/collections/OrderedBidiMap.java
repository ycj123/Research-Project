// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections;

public interface OrderedBidiMap extends BidiMap, OrderedMap
{
    BidiMap inverseBidiMap();
    
    OrderedBidiMap inverseOrderedBidiMap();
}
