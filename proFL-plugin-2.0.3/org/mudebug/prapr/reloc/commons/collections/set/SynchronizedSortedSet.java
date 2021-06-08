// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.set;

import java.util.Comparator;
import java.util.Collection;
import java.util.SortedSet;
import org.mudebug.prapr.reloc.commons.collections.collection.SynchronizedCollection;

public class SynchronizedSortedSet extends SynchronizedCollection implements SortedSet
{
    private static final long serialVersionUID = 2775582861954500111L;
    
    public static SortedSet decorate(final SortedSet set) {
        return new SynchronizedSortedSet(set);
    }
    
    protected SynchronizedSortedSet(final SortedSet set) {
        super(set);
    }
    
    protected SynchronizedSortedSet(final SortedSet set, final Object lock) {
        super(set, lock);
    }
    
    protected SortedSet getSortedSet() {
        return (SortedSet)super.collection;
    }
    
    public SortedSet subSet(final Object fromElement, final Object toElement) {
        synchronized (super.lock) {
            final SortedSet set = this.getSortedSet().subSet(fromElement, toElement);
            return new SynchronizedSortedSet(set, super.lock);
        }
    }
    
    public SortedSet headSet(final Object toElement) {
        synchronized (super.lock) {
            final SortedSet set = this.getSortedSet().headSet(toElement);
            return new SynchronizedSortedSet(set, super.lock);
        }
    }
    
    public SortedSet tailSet(final Object fromElement) {
        synchronized (super.lock) {
            final SortedSet set = this.getSortedSet().tailSet(fromElement);
            return new SynchronizedSortedSet(set, super.lock);
        }
    }
    
    public Object first() {
        synchronized (super.lock) {
            return this.getSortedSet().first();
        }
    }
    
    public Object last() {
        synchronized (super.lock) {
            return this.getSortedSet().last();
        }
    }
    
    public Comparator comparator() {
        synchronized (super.lock) {
            return this.getSortedSet().comparator();
        }
    }
}
