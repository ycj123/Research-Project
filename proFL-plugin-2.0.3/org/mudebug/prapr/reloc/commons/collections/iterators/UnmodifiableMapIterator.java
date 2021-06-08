// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.iterators;

import org.mudebug.prapr.reloc.commons.collections.Unmodifiable;
import org.mudebug.prapr.reloc.commons.collections.MapIterator;

public final class UnmodifiableMapIterator implements MapIterator, Unmodifiable
{
    private MapIterator iterator;
    
    public static MapIterator decorate(final MapIterator iterator) {
        if (iterator == null) {
            throw new IllegalArgumentException("MapIterator must not be null");
        }
        if (iterator instanceof Unmodifiable) {
            return iterator;
        }
        return new UnmodifiableMapIterator(iterator);
    }
    
    private UnmodifiableMapIterator(final MapIterator iterator) {
        this.iterator = iterator;
    }
    
    public boolean hasNext() {
        return this.iterator.hasNext();
    }
    
    public Object next() {
        return this.iterator.next();
    }
    
    public Object getKey() {
        return this.iterator.getKey();
    }
    
    public Object getValue() {
        return this.iterator.getValue();
    }
    
    public Object setValue(final Object value) {
        throw new UnsupportedOperationException("setValue() is not supported");
    }
    
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
