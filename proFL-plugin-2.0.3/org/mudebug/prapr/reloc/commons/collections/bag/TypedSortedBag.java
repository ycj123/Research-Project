// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bag;

import org.mudebug.prapr.reloc.commons.collections.functors.InstanceofPredicate;
import org.mudebug.prapr.reloc.commons.collections.SortedBag;

public class TypedSortedBag
{
    public static SortedBag decorate(final SortedBag bag, final Class type) {
        return new PredicatedSortedBag(bag, InstanceofPredicate.getInstance(type));
    }
    
    protected TypedSortedBag() {
    }
}
