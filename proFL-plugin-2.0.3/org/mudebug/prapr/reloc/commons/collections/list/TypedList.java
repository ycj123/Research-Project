// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.list;

import org.mudebug.prapr.reloc.commons.collections.functors.InstanceofPredicate;
import java.util.List;

public class TypedList
{
    public static List decorate(final List list, final Class type) {
        return new PredicatedList(list, InstanceofPredicate.getInstance(type));
    }
    
    protected TypedList() {
    }
}
