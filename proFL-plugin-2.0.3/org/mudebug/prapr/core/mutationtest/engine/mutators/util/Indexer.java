// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine.mutators.util;

import java.util.HashMap;
import java.util.Map;

public class Indexer<T>
{
    private final Map<T, Integer> indexMap;
    private int index;
    
    public Indexer() {
        this.indexMap = new HashMap<T, Integer>();
        this.index = 0;
    }
    
    public int index(final T t) {
        final int i = this.index++;
        this.indexMap.put(t, i);
        return i;
    }
    
    public int indexOf(final T t) {
        final Integer i = this.indexMap.get(t);
        return (i == null) ? -1 : i;
    }
}
