// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import org.mudebug.prapr.reloc.commons.collections.functors.InstanceofPredicate;
import java.util.SortedMap;

public class TypedSortedMap
{
    public static SortedMap decorate(final SortedMap map, final Class keyType, final Class valueType) {
        return new PredicatedSortedMap(map, InstanceofPredicate.getInstance(keyType), InstanceofPredicate.getInstance(valueType));
    }
    
    protected TypedSortedMap() {
    }
}
