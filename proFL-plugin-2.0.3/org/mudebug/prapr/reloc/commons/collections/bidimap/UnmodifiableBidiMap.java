// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bidimap;

import org.mudebug.prapr.reloc.commons.collections.iterators.UnmodifiableMapIterator;
import org.mudebug.prapr.reloc.commons.collections.MapIterator;
import org.mudebug.prapr.reloc.commons.collections.collection.UnmodifiableCollection;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.set.UnmodifiableSet;
import org.mudebug.prapr.reloc.commons.collections.map.UnmodifiableEntrySet;
import java.util.Set;
import java.util.Map;
import org.mudebug.prapr.reloc.commons.collections.BidiMap;
import org.mudebug.prapr.reloc.commons.collections.Unmodifiable;

public final class UnmodifiableBidiMap extends AbstractBidiMapDecorator implements Unmodifiable
{
    private UnmodifiableBidiMap inverse;
    
    public static BidiMap decorate(final BidiMap map) {
        if (map instanceof Unmodifiable) {
            return map;
        }
        return new UnmodifiableBidiMap(map);
    }
    
    private UnmodifiableBidiMap(final BidiMap map) {
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
        final MapIterator it = this.getBidiMap().mapIterator();
        return UnmodifiableMapIterator.decorate(it);
    }
    
    public BidiMap inverseBidiMap() {
        if (this.inverse == null) {
            this.inverse = new UnmodifiableBidiMap(this.getBidiMap().inverseBidiMap());
            this.inverse.inverse = this;
        }
        return this.inverse;
    }
}
