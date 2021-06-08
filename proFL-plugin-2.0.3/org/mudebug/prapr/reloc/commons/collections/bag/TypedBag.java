// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.bag;

import org.mudebug.prapr.reloc.commons.collections.functors.InstanceofPredicate;
import org.mudebug.prapr.reloc.commons.collections.Bag;

public class TypedBag
{
    public static Bag decorate(final Bag bag, final Class type) {
        return new PredicatedBag(bag, InstanceofPredicate.getInstance(type));
    }
    
    protected TypedBag() {
    }
}
