// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.list;

import org.mudebug.prapr.reloc.commons.collections.iterators.AbstractListIteratorDecorator;
import java.util.ListIterator;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Transformer;
import java.util.List;
import org.mudebug.prapr.reloc.commons.collections.collection.TransformedCollection;

public class TransformedList extends TransformedCollection implements List
{
    private static final long serialVersionUID = 1077193035000013141L;
    
    public static List decorate(final List list, final Transformer transformer) {
        return new TransformedList(list, transformer);
    }
    
    protected TransformedList(final List list, final Transformer transformer) {
        super(list, transformer);
    }
    
    protected List getList() {
        return (List)super.collection;
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
    
    public void add(final int index, Object object) {
        object = this.transform(object);
        this.getList().add(index, object);
    }
    
    public boolean addAll(final int index, Collection coll) {
        coll = this.transform(coll);
        return this.getList().addAll(index, coll);
    }
    
    public ListIterator listIterator() {
        return this.listIterator(0);
    }
    
    public ListIterator listIterator(final int i) {
        return new TransformedListIterator(this.getList().listIterator(i));
    }
    
    public Object set(final int index, Object object) {
        object = this.transform(object);
        return this.getList().set(index, object);
    }
    
    public List subList(final int fromIndex, final int toIndex) {
        final List sub = this.getList().subList(fromIndex, toIndex);
        return new TransformedList(sub, super.transformer);
    }
    
    protected class TransformedListIterator extends AbstractListIteratorDecorator
    {
        protected TransformedListIterator(final ListIterator iterator) {
            super(iterator);
        }
        
        public void add(Object object) {
            object = TransformedCollection.this.transform(object);
            super.iterator.add(object);
        }
        
        public void set(Object object) {
            object = TransformedCollection.this.transform(object);
            super.iterator.set(object);
        }
    }
}
