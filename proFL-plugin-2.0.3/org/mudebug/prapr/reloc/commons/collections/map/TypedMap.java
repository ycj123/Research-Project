// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import org.mudebug.prapr.reloc.commons.collections.functors.InstanceofPredicate;
import java.util.Map;

public class TypedMap
{
    public static Map decorate(final Map map, final Class keyType, final Class valueType) {
        return new PredicatedMap(map, InstanceofPredicate.getInstance(keyType), InstanceofPredicate.getInstance(valueType));
    }
    
    protected TypedMap() {
    }
}
