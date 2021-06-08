// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.set;

import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.iterators.UnmodifiableIterator;
import java.util.Iterator;
import java.util.Set;
import org.mudebug.prapr.reloc.commons.collections.Unmodifiable;

public final class UnmodifiableSet extends AbstractSerializableSetDecorator implements Unmodifiable
{
    private static final long serialVersionUID = 6499119872185240161L;
    
    public static Set decorate(final Set set) {
        if (set instanceof Unmodifiable) {
            return set;
        }
        return new UnmodifiableSet(set);
    }
    
    private UnmodifiableSet(final Set set) {
        super(set);
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
