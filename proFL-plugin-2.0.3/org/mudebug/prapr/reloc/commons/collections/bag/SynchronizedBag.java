// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bag;

import org.mudebug.prapr.reloc.commons.collections.set.SynchronizedSet;
import java.util.Set;
import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Bag;
import org.mudebug.prapr.reloc.commons.collections.collection.SynchronizedCollection;

public class SynchronizedBag extends SynchronizedCollection implements Bag
{
    private static final long serialVersionUID = 8084674570753837109L;
    
    public static Bag decorate(final Bag bag) {
        return new SynchronizedBag(bag);
    }
    
    protected SynchronizedBag(final Bag bag) {
        super(bag);
    }
    
    protected SynchronizedBag(final Bag bag, final Object lock) {
        super(bag, lock);
    }
    
    protected Bag getBag() {
        return (Bag)super.collection;
    }
    
    public boolean add(final Object object, final int count) {
        synchronized (super.lock) {
            return this.getBag().add(object, count);
        }
    }
    
    public boolean remove(final Object object, final int count) {
        synchronized (super.lock) {
            return this.getBag().remove(object, count);
        }
    }
    
    public Set uniqueSet() {
        synchronized (super.lock) {
            final Set set = this.getBag().uniqueSet();
            return new SynchronizedBagSet(set, super.lock);
        }
    }
    
    public int getCount(final Object object) {
        synchronized (super.lock) {
            return this.getBag().getCount(object);
        }
    }
    
    class SynchronizedBagSet extends SynchronizedSet
    {
        SynchronizedBagSet(final Set set, final Object lock) {
            super(set, lock);
        }
    }
}
