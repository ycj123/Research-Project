// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bag;

import java.util.Comparator;
import org.mudebug.prapr.reloc.commons.collections.Bag;
import org.mudebug.prapr.reloc.commons.collections.Transformer;
import org.mudebug.prapr.reloc.commons.collections.SortedBag;

public class TransformedSortedBag extends TransformedBag implements SortedBag
{
    private static final long serialVersionUID = -251737742649401930L;
    
    public static SortedBag decorate(final SortedBag bag, final Transformer transformer) {
        return new TransformedSortedBag(bag, transformer);
    }
    
    protected TransformedSortedBag(final SortedBag bag, final Transformer transformer) {
        super(bag, transformer);
    }
    
    protected SortedBag getSortedBag() {
        return (SortedBag)super.collection;
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
