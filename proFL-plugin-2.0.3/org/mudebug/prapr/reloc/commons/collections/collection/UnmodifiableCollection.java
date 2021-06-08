// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.collection;

import org.mudebug.prapr.reloc.commons.collections.iterators.UnmodifiableIterator;
import java.util.Iterator;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Unmodifiable;

public final class UnmodifiableCollection extends AbstractSerializableCollectionDecorator implements Unmodifiable
{
    private static final long serialVersionUID = -239892006883819945L;
    
    public static Collection decorate(final Collection coll) {
        if (coll instanceof Unmodifiable) {
            return coll;
        }
        return new UnmodifiableCollection(coll);
    }
    
    private UnmodifiableCollection(final Collection coll) {
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
}
