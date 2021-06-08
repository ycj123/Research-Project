// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.map;

import java.util.Comparator;
import java.util.Map;
import org.mudebug.prapr.reloc.commons.collections.Predicate;
import java.util.SortedMap;

public class PredicatedSortedMap extends PredicatedMap implements SortedMap
{
    private static final long serialVersionUID = 3359846175935304332L;
    
    public static SortedMap decorate(final SortedMap map, final Predicate keyPredicate, final Predicate valuePredicate) {
        return new PredicatedSortedMap(map, keyPredicate, valuePredicate);
    }
    
    protected PredicatedSortedMap(final SortedMap map, final Predicate keyPredicate, final Predicate valuePredicate) {
        super(map, keyPredicate, valuePredicate);
    }
    
    protected SortedMap getSortedMap() {
        return (SortedMap)super.map;
    }
    
    public Object firstKey() {
        return this.getSortedMap().firstKey();
    }
    
    public Object lastKey() {
        return this.getSortedMap().lastKey();
    }
    
    public Comparator comparator() {
        return this.getSortedMap().comparator();
    }
    
    public SortedMap subMap(final Object fromKey, final Object toKey) {
        final SortedMap map = this.getSortedMap().subMap(fromKey, toKey);
        return new PredicatedSortedMap(map, super.keyPredicate, super.valuePredicate);
    }
    
    public SortedMap headMap(final Object toKey) {
        final SortedMap map = this.getSortedMap().headMap(toKey);
        return new PredicatedSortedMap(map, super.keyPredicate, super.valuePredicate);
    }
    
    public SortedMap tailMap(final Object fromKey) {
        final SortedMap map = this.getSortedMap().tailMap(fromKey);
        return new PredicatedSortedMap(map, super.keyPredicate, super.valuePredicate);
    }
}
