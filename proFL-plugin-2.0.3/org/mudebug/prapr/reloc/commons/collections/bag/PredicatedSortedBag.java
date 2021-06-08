// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bag;

import java.util.Comparator;
import org.mudebug.prapr.reloc.commons.collections.Bag;
import org.mudebug.prapr.reloc.commons.collections.Predicate;
import org.mudebug.prapr.reloc.commons.collections.SortedBag;

public class PredicatedSortedBag extends PredicatedBag implements SortedBag
{
    private static final long serialVersionUID = 3448581314086406616L;
    
    public static SortedBag decorate(final SortedBag bag, final Predicate predicate) {
        return new PredicatedSortedBag(bag, predicate);
    }
    
    protected PredicatedSortedBag(final SortedBag bag, final Predicate predicate) {
        super(bag, predicate);
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
