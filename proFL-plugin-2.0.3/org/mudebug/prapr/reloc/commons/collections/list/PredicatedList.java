// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.list;

import org.mudebug.prapr.reloc.commons.collections.iterators.AbstractListIteratorDecorator;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Predicate;
import java.util.List;
import org.mudebug.prapr.reloc.commons.collections.collection.PredicatedCollection;

public class PredicatedList extends PredicatedCollection implements List
{
    private static final long serialVersionUID = -5722039223898659102L;
    
    public static List decorate(final List list, final Predicate predicate) {
        return new PredicatedList(list, predicate);
    }
    
    protected PredicatedList(final List list, final Predicate predicate) {
        super(list, predicate);
    }
    
    protected List getList() {
        return (List)this.getCollection();
    }
    
    public Object get(final int index) {
        return this.getList().get(index);
    }
    
    public int indexOf(final Object object) {
        return this.getList().indexOf(object);
    }
    
    public int lastIndexOf(final Object object) {
        return this.getList().lastIndexOf(object);
    }
    
    public Object remove(final int index) {
        return this.getList().remove(index);
    }
    
    public void add(final int index, final Object object) {
        this.validate(object);
        this.getList().add(index, object);
    }
    
    public boolean addAll(final int index, final Collection coll) {
        final Iterator it = coll.iterator();
        while (it.hasNext()) {
            this.validate(it.next());
        }
        return this.getList().addAll(index, coll);
    }
    
    public ListIterator listIterator() {
        return this.listIterator(0);
    }
    
    public ListIterator listIterator(final int i) {
        return new PredicatedListIterator(this.getList().listIterator(i));
    }
    
    public Object set(final int index, final Object object) {
        this.validate(object);
        return this.getList().set(index, object);
    }
    
    public List subList(final int fromIndex, final int toIndex) {
        final List sub = this.getList().subList(fromIndex, toIndex);
        return new PredicatedList(sub, super.predicate);
    }
    
    protected class PredicatedListIterator extends AbstractListIteratorDecorator
    {
        protected PredicatedListIterator(final ListIterator iterator) {
            super(iterator);
        }
        
        public void add(final Object object) {
            PredicatedCollection.this.validate(object);
            super.iterator.add(object);
        }
        
        public void set(final Object object) {
            PredicatedCollection.this.validate(object);
            super.iterator.set(object);
        }
    }
}
