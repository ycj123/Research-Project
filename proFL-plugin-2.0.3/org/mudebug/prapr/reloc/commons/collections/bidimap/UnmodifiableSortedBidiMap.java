// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bidimap;

import org.mudebug.prapr.reloc.commons.collections.map.UnmodifiableSortedMap;
import java.util.SortedMap;
import org.mudebug.prapr.reloc.commons.collections.OrderedBidiMap;
import org.mudebug.prapr.reloc.commons.collections.iterators.UnmodifiableOrderedMapIterator;
import org.mudebug.prapr.reloc.commons.collections.OrderedMapIterator;
import org.mudebug.prapr.reloc.commons.collections.BidiMap;
import org.mudebug.prapr.reloc.commons.collections.MapIterator;
import org.mudebug.prapr.reloc.commons.collections.collection.UnmodifiableCollection;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.set.UnmodifiableSet;
import org.mudebug.prapr.reloc.commons.collections.map.UnmodifiableEntrySet;
import java.util.Set;
import java.util.Map;
import org.mudebug.prapr.reloc.commons.collections.SortedBidiMap;
import org.mudebug.prapr.reloc.commons.collections.Unmodifiable;

public final class UnmodifiableSortedBidiMap extends AbstractSortedBidiMapDecorator implements Unmodifiable
{
    private UnmodifiableSortedBidiMap inverse;
    
    public static SortedBidiMap decorate(final SortedBidiMap map) {
        if (map instanceof Unmodifiable) {
            return map;
        }
        return new UnmodifiableSortedBidiMap(map);
    }
    
    private UnmodifiableSortedBidiMap(final SortedBidiMap map) {
        super(map);
    }
    
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    public Object put(final Object key, final Object value) {
        throw new UnsupportedOperationException();
    }
    
    public void putAll(final Map mapToCopy) {
        throw new UnsupportedOperationException();
    }
    
    public Object remove(final Object key) {
        throw new UnsupportedOperationException();
    }
    
    public Set entrySet() {
        final Set set = super.entrySet();
        return UnmodifiableEntrySet.decorate(set);
    }
    
    public Set keySet() {
        final Set set = super.keySet();
        return UnmodifiableSet.decorate(set);
    }
    
    public Collection values() {
        final Collection coll = super.values();
        return UnmodifiableCollection.decorate(coll);
    }
    
    public Object removeValue(final Object value) {
        throw new UnsupportedOperationException();
    }
    
    public MapIterator mapIterator() {
        return this.orderedMapIterator();
    }
    
    public BidiMap inverseBidiMap() {
        return this.inverseSortedBidiMap();
    }
    
    public OrderedMapIterator orderedMapIterator() {
        final OrderedMapIterator it = this.getSortedBidiMap().orderedMapIterator();
        return UnmodifiableOrderedMapIterator.decorate(it);
    }
    
    public OrderedBidiMap inverseOrderedBidiMap() {
        return this.inverseSortedBidiMap();
    }
    
    public SortedBidiMap inverseSortedBidiMap() {
        if (this.inverse == null) {
            this.inverse = new UnmodifiableSortedBidiMap(this.getSortedBidiMap().inverseSortedBidiMap());
            this.inverse.inverse = this;
        }
        return this.inverse;
    }
    
    public SortedMap subMap(final Object fromKey, final Object toKey) {
        final SortedMap sm = this.getSortedBidiMap().subMap(fromKey, toKey);
        return UnmodifiableSortedMap.decorate(sm);
    }
    
    public SortedMap headMap(final Object toKey) {
        final SortedMap sm = this.getSortedBidiMap().headMap(toKey);
        return UnmodifiableSortedMap.decorate(sm);
    }
    
    public SortedMap tailMap(final Object fromKey) {
        final SortedMap sm = this.getSortedBidiMap().tailMap(fromKey);
        return UnmodifiableSortedMap.decorate(sm);
    }
}
