// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bag;

import java.util.Comparator;
import org.mudebug.prapr.reloc.commons.collections.Bag;
import org.mudebug.prapr.reloc.commons.collections.SortedBag;

public abstract class AbstractSortedBagDecorator extends AbstractBagDecorator implements SortedBag
{
    protected AbstractSortedBagDecorator() {
    }
    
    protected AbstractSortedBagDecorator(final SortedBag bag) {
        super(bag);
    }
    
    protected SortedBag getSortedBag() {
        return (SortedBag)this.getCollection();
    }
    
    public Object first() {
        return this.getSortedBag().first();
    }
    
    public Object last() {
        return this.getSortedBag().last();
    }
    
    public Comparator comparator() {
        return this.getSortedBag().comparator();
    }
}
