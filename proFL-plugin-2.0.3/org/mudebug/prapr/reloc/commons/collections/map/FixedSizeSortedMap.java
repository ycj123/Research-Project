// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import org.mudebug.prapr.reloc.commons.collections.collection.UnmodifiableCollection;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.set.UnmodifiableSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.BoundedMap;
import java.util.SortedMap;

public class FixedSizeSortedMap extends AbstractSortedMapDecorator implements SortedMap, BoundedMap, Serializable
{
    private static final long serialVersionUID = 3126019624511683653L;
    
    public static SortedMap decorate(final SortedMap map) {
        return new FixedSizeSortedMap(map);
    }
    
    protected FixedSizeSortedMap(final SortedMap map) {
        super(map);
    }
    
    protected SortedMap getSortedMap() {
        return (SortedMap)super.map;
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(super.map);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        super.map = (Map)in.readObject();
    }
    
    public Object put(final Object key, final Object value) {
        if (!super.map.containsKey(key)) {
            throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
        }
        return super.map.put(key, value);
    }
    
    public void putAll(final Map mapToCopy) {
        final Iterator it = mapToCopy.keySet().iterator();
        while (it.hasNext()) {
            if (!mapToCopy.containsKey(it.next())) {
                throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
            }
        }
        super.map.putAll(mapToCopy);
    }
    
    public void clear() {
        throw new UnsupportedOperationException("Map is fixed size");
    }
    
    public Object remove(final Object key) {
        throw new UnsupportedOperationException("Map is fixed size");
    }
    
    public Set entrySet() {
        final Set set = super.map.entrySet();
        return UnmodifiableSet.decorate(set);
    }
    
    public Set keySet() {
        final Set set = super.map.keySet();
        return UnmodifiableSet.decorate(set);
    }
    
    public Collection values() {
        final Collection coll = super.map.values();
        return UnmodifiableCollection.decorate(coll);
    }
    
    public SortedMap subMap(final Object fromKey, final Object toKey) {
        final SortedMap map = this.getSortedMap().subMap(fromKey, toKey);
        return new FixedSizeSortedMap(map);
    }
    
    public SortedMap headMap(final Object toKey) {
        final SortedMap map = this.getSortedMap().headMap(toKey);
        return new FixedSizeSortedMap(map);
    }
    
    public SortedMap tailMap(final Object fromKey) {
        final SortedMap map = this.getSortedMap().tailMap(fromKey);
        return new FixedSizeSortedMap(map);
    }
    
    public boolean isFull() {
        return true;
    }
    
    public int maxSize() {
        return this.size();
    }
}
