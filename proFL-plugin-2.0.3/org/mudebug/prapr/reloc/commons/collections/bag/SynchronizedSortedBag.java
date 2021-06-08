// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bag;

import java.util.Comparator;
import org.mudebug.prapr.reloc.commons.collections.Bag;
import org.mudebug.prapr.reloc.commons.collections.SortedBag;

public class SynchronizedSortedBag extends SynchronizedBag implements SortedBag
{
    private static final long serialVersionUID = 722374056718497858L;
    
    public static SortedBag decorate(final SortedBag bag) {
        return new SynchronizedSortedBag(bag);
    }
    
    protected SynchronizedSortedBag(final SortedBag bag) {
        super(bag);
    }
    
    protected SynchronizedSortedBag(final Bag bag, final Object lock) {
        super(bag, lock);
    }
    
    protected SortedBag getSortedBag() {
        return (SortedBag)super.collection;
    }
    
    public synchronized Object first() {
        synchronized (super.lock) {
            return this.getSortedBag().first();
        }
    }
    
    public synchronized Object last() {
        synchronized (super.lock) {
            return this.getSortedBag().last();
        }
    }
    
    public synchronized Comparator comparator() {
        synchronized (super.lock) {
            return this.getSortedBag().comparator();
        }
    }
}
