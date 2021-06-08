// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bidimap;

import java.util.SortedMap;
import java.util.Comparator;
import org.mudebug.prapr.reloc.commons.collections.OrderedBidiMap;
import org.mudebug.prapr.reloc.commons.collections.SortedBidiMap;

public abstract class AbstractSortedBidiMapDecorator extends AbstractOrderedBidiMapDecorator implements SortedBidiMap
{
    public AbstractSortedBidiMapDecorator(final SortedBidiMap map) {
        super(map);
    }
    
    protected SortedBidiMap getSortedBidiMap() {
        return (SortedBidiMap)super.map;
    }
    
    public SortedBidiMap inverseSortedBidiMap() {
        return this.getSortedBidiMap().inverseSortedBidiMap();
    }
    
    public Comparator comparator() {
        return this.getSortedBidiMap().comparator();
    }
    
    public SortedMap subMap(final Object fromKey, final Object toKey) {
        return this.getSortedBidiMap().subMap(fromKey, toKey);
    }
    
    public SortedMap headMap(final Object toKey) {
        return this.getSortedBidiMap().headMap(toKey);
    }
    
    public SortedMap tailMap(final Object fromKey) {
        return this.getSortedBidiMap().tailMap(fromKey);
    }
}
