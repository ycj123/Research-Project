// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.set;

import org.mudebug.prapr.reloc.commons.collections.iterators.AbstractIteratorDecorator;
import java.util.Iterator;
import org.mudebug.prapr.reloc.commons.collections.list.UnmodifiableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListOrderedSet extends AbstractSerializableSetDecorator implements Set
{
    private static final long serialVersionUID = -228664372470420141L;
    protected final List setOrder;
    
    public static ListOrderedSet decorate(final Set set, final List list) {
        if (set == null) {
            throw new IllegalArgumentException("Set must not be null");
        }
        if (list == null) {
            throw new IllegalArgumentException("List must not be null");
        }
        if (set.size() > 0 || list.size() > 0) {
            throw new IllegalArgumentException("Set and List must be empty");
        }
        return new ListOrderedSet(set, list);
    }
    
    public static ListOrderedSet decorate(final Set set) {
        return new ListOrderedSet(set);
    }
    
    public static ListOrderedSet decorate(final List list) {
        if (list == null) {
            throw new IllegalArgumentException("List must not be null");
        }
        final Set set = new HashSet(list);
        list.retainAll(set);
        return new ListOrderedSet(set, list);
    }
    
    public ListOrderedSet() {
        super(new HashSet());
        this.setOrder = new ArrayList();
    }
    
    protected ListOrderedSet(final Set set) {
        super(set);
        this.setOrder = new ArrayList(set);
    }
    
    protected ListOrderedSet(final Set set, final List list) {
        super(set);
        if (list == null) {
            throw new IllegalArgumentException("List must not be null");
        }
        this.setOrder = list;
    }
    
    public List asList() {
        return UnmodifiableList.decorate(this.setOrder);
    }
    
    public void clear() {
        super.collection.clear();
        this.setOrder.clear();
    }
    
    public Iterator iterator() {
        return new OrderedSetIterator((Iterator)this.setOrder.iterator(), super.collection);
    }
    
    public boolean add(final Object object) {
        if (super.collection.contains(object)) {
            return super.collection.add(object);
        }
        final boolean result = super.collection.add(object);
        this.setOrder.add(object);
        return result;
    }
    
    public boolean addAll(final Collection coll) {
        boolean result = false;
        for (final Object object : coll) {
            result |= this.add(object);
        }
        return result;
    }
    
    public boolean remove(final Object object) {
        final boolean result = super.collection.remove(object);
        this.setOrder.remove(object);
        return result;
    }
    
    public boolean removeAll(final Collection coll) {
        boolean result = false;
        for (final Object object : coll) {
            result |= this.remove(object);
        }
        return result;
    }
    
    public boolean retainAll(final Collection coll) {
        final boolean result = super.collection.retainAll(coll);
        if (!result) {
            return false;
        }
        if (super.collection.size() == 0) {
            this.setOrder.clear();
        }
        else {
            final Iterator it = this.setOrder.iterator();
            while (it.hasNext()) {
                final Object object = it.next();
                if (!super.collection.contains(object)) {
                    it.remove();
                }
            }
        }
        return result;
    }
    
    public Object[] toArray() {
        return this.setOrder.toArray();
    }
    
    public Object[] toArray(final Object[] a) {
        return this.setOrder.toArray(a);
    }
    
    public Object get(final int index) {
        return this.setOrder.get(index);
    }
    
    public int indexOf(final Object object) {
        return this.setOrder.indexOf(object);
    }
    
    public void add(final int index, final Object object) {
        if (!this.contains(object)) {
            super.collection.add(object);
            this.setOrder.add(index, object);
        }
    }
    
    public boolean addAll(int index, final Collection coll) {
        boolean changed = false;
        for (final Object object : coll) {
            if (!this.contains(object)) {
                super.collection.add(object);
                this.setOrder.add(index, object);
                ++index;
                changed = true;
            }
        }
        return changed;
    }
    
    public Object remove(final int index) {
        final Object obj = this.setOrder.remove(index);
        this.remove(obj);
        return obj;
    }
    
    public String toString() {
        return this.setOrder.toString();
    }
    
    static class OrderedSetIterator extends AbstractIteratorDecorator
    {
        protected final Collection set;
        protected Object last;
        
        private OrderedSetIterator(final Iterator iterator, final Collection set) {
            super(iterator);
            this.set = set;
        }
        
        public Object next() {
            return this.last = super.iterator.next();
        }
        
        public void remove() {
            this.set.remove(this.last);
            super.iterator.remove();
            this.last = null;
        }
    }
}
