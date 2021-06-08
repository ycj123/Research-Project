// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.iterators;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.ResettableIterator;

public class LoopingIterator implements ResettableIterator
{
    private Collection collection;
    private Iterator iterator;
    
    public LoopingIterator(final Collection coll) {
        if (coll == null) {
            throw new NullPointerException("The collection must not be null");
        }
        this.collection = coll;
        this.reset();
    }
    
    public boolean hasNext() {
        return this.collection.size() > 0;
    }
    
    public Object next() {
        if (this.collection.size() == 0) {
            throw new NoSuchElementException("There are no elements for this iterator to loop on");
        }
        if (!this.iterator.hasNext()) {
            this.reset();
        }
        return this.iterator.next();
    }
    
    public void remove() {
        this.iterator.remove();
    }
    
    public void reset() {
        this.iterator = this.collection.iterator();
    }
    
    public int size() {
        return this.collection.size();
    }
}
