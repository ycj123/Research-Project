// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.collection;

import org.mudebug.prapr.reloc.commons.collections.iterators.UnmodifiableIterator;
import java.util.Iterator;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.BoundedCollection;

public final class UnmodifiableBoundedCollection extends AbstractSerializableCollectionDecorator implements BoundedCollection
{
    private static final long serialVersionUID = -7112672385450340330L;
    
    public static BoundedCollection decorate(final BoundedCollection coll) {
        return new UnmodifiableBoundedCollection(coll);
    }
    
    public static BoundedCollection decorateUsing(Collection coll) {
        if (coll == null) {
            throw new IllegalArgumentException("The collection must not be null");
        }
        for (int i = 0; i < 1000; ++i) {
            if (coll instanceof BoundedCollection) {
                break;
            }
            if (coll instanceof AbstractCollectionDecorator) {
                coll = ((AbstractCollectionDecorator)coll).collection;
            }
            else {
                if (!(coll instanceof SynchronizedCollection)) {
                    break;
                }
                coll = ((SynchronizedCollection)coll).collection;
            }
        }
        if (!(coll instanceof BoundedCollection)) {
            throw new IllegalArgumentException("The collection is not a bounded collection");
        }
        return new UnmodifiableBoundedCollection((BoundedCollection)coll);
    }
    
    private UnmodifiableBoundedCollection(final BoundedCollection coll) {
        super(coll);
    }
    
    public Iterator iterator() {
        return UnmodifiableIterator.decorate(this.getCollection().iterator());
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
    
    public boolean isFull() {
        return ((BoundedCollection)super.collection).isFull();
    }
    
    public int maxSize() {
        return ((BoundedCollection)super.collection).maxSize();
    }
}
