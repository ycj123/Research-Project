// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import org.mudebug.prapr.reloc.commons.collections.collection.UnmodifiableCollection;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.set.UnmodifiableSet;
import java.util.Set;
import org.mudebug.prapr.reloc.commons.collections.iterators.UnmodifiableOrderedMapIterator;
import org.mudebug.prapr.reloc.commons.collections.OrderedMapIterator;
import org.mudebug.prapr.reloc.commons.collections.iterators.UnmodifiableMapIterator;
import org.mudebug.prapr.reloc.commons.collections.MapIterator;
import java.util.Map;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.mudebug.prapr.reloc.commons.collections.OrderedMap;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Unmodifiable;

public final class UnmodifiableOrderedMap extends AbstractOrderedMapDecorator implements Unmodifiable, Serializable
{
    private static final long serialVersionUID = 8136428161720526266L;
    
    public static OrderedMap decorate(final OrderedMap map) {
        if (map instanceof Unmodifiable) {
            return map;
        }
        return new UnmodifiableOrderedMap(map);
    }
    
    private UnmodifiableOrderedMap(final OrderedMap map) {
        super(map);
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(super.map);
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        super.map = (Map)in.readObject();
    }
    
    public MapIterator mapIterator() {
        final MapIterator it = this.getOrderedMap().mapIterator();
        return UnmodifiableMapIterator.decorate(it);
    }
    
    public OrderedMapIterator orderedMapIterator() {
        final OrderedMapIterator it = this.getOrderedMap().orderedMapIterator();
        return UnmodifiableOrderedMapIterator.decorate(it);
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
}
