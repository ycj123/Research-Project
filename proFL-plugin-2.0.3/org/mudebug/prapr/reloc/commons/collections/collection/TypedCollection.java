// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.collection;

import org.mudebug.prapr.reloc.commons.collections.functors.InstanceofPredicate;
import java.util.Collection;

public class TypedCollection
{
    public static Collection decorate(final Collection coll, final Class type) {
        return new PredicatedCollection(coll, InstanceofPredicate.getInstance(type));
    }
    
    protected TypedCollection() {
    }
}
