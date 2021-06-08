// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import org.mudebug.prapr.reloc.commons.collections.keyvalue.AbstractMapEntryDecorator;
import org.mudebug.prapr.reloc.commons.collections.iterators.AbstractIteratorDecorator;
import java.lang.reflect.Array;
import java.util.Iterator;
import org.mudebug.prapr.reloc.commons.collections.set.AbstractSetDecorator;
import java.util.Set;
import java.util.Map;

abstract class AbstractInputCheckedMapDecorator extends AbstractMapDecorator
{
    protected AbstractInputCheckedMapDecorator() {
    }
    
    protected AbstractInputCheckedMapDecorator(final Map map) {
        super(map);
    }
    
    protected abstract Object checkSetValue(final Object p0);
    
    protected boolean isSetValueChecking() {
        return true;
    }
    
    public Set entrySet() {
        if (this.isSetValueChecking()) {
            return new EntrySet(super.map.entrySet(), this);
        }
        return super.map.entrySet();
    }
    
    static class EntrySet extends AbstractSetDecorator
    {
        private final AbstractInputCheckedMapDecorator parent;
        
        protected EntrySet(final Set set, final AbstractInputCheckedMapDecorator parent) {
            super(set);
            this.parent = parent;
        }
        
        public Iterator iterator() {
            return new EntrySetIterator(super.collection.iterator(), this.parent);
        }
        
        public Object[] toArray() {
            final Object[] array = super.collection.toArray();
            for (int i = 0; i < array.length; ++i) {
                array[i] = new MapEntry((Map.Entry)array[i], this.parent);
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
                result[i] = new MapEntry((Map.Entry)result[i], this.parent);
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
    }
    
    static class EntrySetIterator extends AbstractIteratorDecorator
    {
        private final AbstractInputCheckedMapDecorator parent;
        
        protected EntrySetIterator(final Iterator iterator, final AbstractInputCheckedMapDecorator parent) {
            super(iterator);
            this.parent = parent;
        }
        
        public Object next() {
            final Map.Entry entry = super.iterator.next();
            return new MapEntry(entry, this.parent);
        }
    }
    
    static class MapEntry extends AbstractMapEntryDecorator
    {
        private final AbstractInputCheckedMapDecorator parent;
        
        protected MapEntry(final Map.Entry entry, final AbstractInputCheckedMapDecorator parent) {
            super(entry);
            this.parent = parent;
        }
        
        public Object setValue(Object value) {
            value = this.parent.checkSetValue(value);
            return super.entry.setValue(value);
        }
    }
}
