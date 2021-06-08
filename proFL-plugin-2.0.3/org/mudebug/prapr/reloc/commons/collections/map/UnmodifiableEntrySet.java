// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import org.mudebug.prapr.reloc.commons.collections.keyvalue.AbstractMapEntryDecorator;
import org.mudebug.prapr.reloc.commons.collections.iterators.AbstractIteratorDecorator;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Iterator;
import java.util.Collection;
import java.util.Set;
import org.mudebug.prapr.reloc.commons.collections.Unmodifiable;
import org.mudebug.prapr.reloc.commons.collections.set.AbstractSetDecorator;

public final class UnmodifiableEntrySet extends AbstractSetDecorator implements Unmodifiable
{
    public static Set decorate(final Set set) {
        if (set instanceof Unmodifiable) {
            return set;
        }
        return new UnmodifiableEntrySet(set);
    }
    
    private UnmodifiableEntrySet(final Set set) {
        super(set);
    }
    
    public boolean add(final Object object) {
        throw new UnsupportedOperationException();
    }
    
    public boolean addAll(final Collection coll) {
        throw new UnsupportedOperationException();
    }
    
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(final Collection coll) {
        throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(final Collection coll) {
        throw new UnsupportedOperationException();
    }
    
    public Iterator iterator() {
        return new UnmodifiableEntrySetIterator(super.collection.iterator());
    }
    
    public Object[] toArray() {
        final Object[] array = super.collection.toArray();
        for (int i = 0; i < array.length; ++i) {
            array[i] = new UnmodifiableEntry((Map.Entry)array[i]);
        }
        return array;
    }
    
    public Object[] toArray(final Object[] array) {
        Object[] result = array;
        if (array.length > 0) {
            result = (Object[])Array.newInstance(array.getClass().getComponentType(), 0);
        }
        result = super.collection.toArray(result);
        for (int i = 0; i < result.length; ++i) {
            result[i] = new UnmodifiableEntry((Map.Entry)result[i]);
        }
        if (result.length > array.length) {
            return result;
        }
        System.arraycopy(result, 0, array, 0, result.length);
        if (array.length > result.length) {
            array[result.length] = null;
        }
        return array;
    }
    
    static final class UnmodifiableEntrySetIterator extends AbstractIteratorDecorator
    {
        protected UnmodifiableEntrySetIterator(final Iterator iterator) {
            super(iterator);
        }
        
        public Object next() {
            final Map.Entry entry = super.iterator.next();
            return new UnmodifiableEntry(entry);
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    static final class UnmodifiableEntry extends AbstractMapEntryDecorator
    {
        protected UnmodifiableEntry(final Map.Entry entry) {
            super(entry);
        }
        
        public Object setValue(final Object obj) {
            throw new UnsupportedOperationException();
        }
    }
}
