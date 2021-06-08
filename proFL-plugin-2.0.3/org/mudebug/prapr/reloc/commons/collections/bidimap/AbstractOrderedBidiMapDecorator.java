// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bidimap;

import org.mudebug.prapr.reloc.commons.collections.OrderedMapIterator;
import org.mudebug.prapr.reloc.commons.collections.BidiMap;
import org.mudebug.prapr.reloc.commons.collections.OrderedBidiMap;

public abstract class AbstractOrderedBidiMapDecorator extends AbstractBidiMapDecorator implements OrderedBidiMap
{
    protected AbstractOrderedBidiMapDecorator(final OrderedBidiMap map) {
        super(map);
    }
    
    protected OrderedBidiMap getOrderedBidiMap() {
        return (OrderedBidiMap)super.map;
    }
    
    public OrderedMapIterator orderedMapIterator() {
        return this.getOrderedBidiMap().orderedMapIterator();
    }
    
    public Object firstKey() {
        return this.getOrderedBidiMap().firstKey();
    }
    
    public Object lastKey() {
        return this.getOrderedBidiMap().lastKey();
    }
    
    public Object nextKey(final Object key) {
        return this.getOrderedBidiMap().nextKey(key);
    }
    
    public Object previousKey(final Object key) {
        return this.getOrderedBidiMap().previousKey(key);
    }
    
    public OrderedBidiMap inverseOrderedBidiMap() {
        return this.getOrderedBidiMap().inverseOrderedBidiMap();
    }
}
