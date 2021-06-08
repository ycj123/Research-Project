// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.set;

import org.mudebug.prapr.reloc.commons.collections.functors.InstanceofPredicate;
import java.util.Set;

public class TypedSet
{
    public static Set decorate(final Set set, final Class type) {
        return new PredicatedSet(set, InstanceofPredicate.getInstance(type));
    }
    
    protected TypedSet() {
    }
}
