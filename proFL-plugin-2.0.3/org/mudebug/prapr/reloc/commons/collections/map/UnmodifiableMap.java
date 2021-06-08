// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import org.mudebug.prapr.reloc.commons.collections.collection.UnmodifiableCollection;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.set.UnmodifiableSet;
import java.util.Set;
import org.mudebug.prapr.reloc.commons.collections.iterators.EntrySetMapIterator;
import org.mudebug.prapr.reloc.commons.collections.iterators.UnmodifiableMapIterator;
import org.mudebug.prapr.reloc.commons.collections.MapIterator;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Unmodifiable;
import org.mudebug.prapr.reloc.commons.collections.IterableMap;

public final class UnmodifiableMap extends AbstractMapDecorator implements IterableMap, Unmodifiable, Serializable
{
    private static final long serialVersionUID = 2737023427269031941L;
    
    public static Map decorate(final Map map) {
        if (map instanceof Unmodifiable) {
            return map;
        }
        return new UnmodifiableMap(map);
    }
    
    private UnmodifiableMap(final Map map) {
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
    
    public MapIterator mapIterator() {
        if (super.map instanceof IterableMap) {
            final MapIterator it = ((IterableMap)super.map).mapIterator();
            return UnmodifiableMapIterator.decorate(it);
        }
        final MapIterator it = new EntrySetMapIterator(super.map);
        return UnmodifiableMapIterator.decorate(it);
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
