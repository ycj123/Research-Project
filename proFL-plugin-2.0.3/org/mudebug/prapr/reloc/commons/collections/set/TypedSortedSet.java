// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.set;

import org.mudebug.prapr.reloc.commons.collections.functors.InstanceofPredicate;
import java.util.SortedSet;

public class TypedSortedSet
{
    public static SortedSet decorate(final SortedSet set, final Class type) {
        return new PredicatedSortedSet(set, InstanceofPredicate.getInstance(type));
    }
    
    protected TypedSortedSet() {
    }
}
