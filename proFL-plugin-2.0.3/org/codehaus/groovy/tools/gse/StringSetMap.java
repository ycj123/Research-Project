// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.gse;

import java.util.TreeSet;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Collection;
import java.util.Set;
import java.util.LinkedHashMap;

public class StringSetMap extends LinkedHashMap<String, Set<String>>
{
    public StringSetMap() {
    }
    
    public StringSetMap(final StringSetMap other) {
        for (final String key : ((LinkedHashMap<String, V>)other).keySet()) {
            this.get(key).addAll(other.get(key));
        }
    }
    
    @Override
    public Set<String> get(final Object o) {
        final String name = (String)o;
        Set<String> set = super.get(name);
        if (set == null) {
            set = new LinkedHashSet<String>();
            this.put(name, set);
        }
        return set;
    }
    
    public void makeTransitiveHull() {
        final Set<String> nameSet = new TreeSet<String>(this.keySet());
        final StringSetMap ret = new StringSetMap(this);
        for (final String k : nameSet) {
            final StringSetMap delta = new StringSetMap();
            for (final String i : nameSet) {
                for (final String j : nameSet) {
                    final Set<String> iSet = this.get(i);
                    if (iSet.contains(k) && this.get(k).contains(j)) {
                        delta.get(i).add(j);
                    }
                }
            }
            for (final String i : nameSet) {
                this.get(i).addAll(delta.get(i));
            }
        }
    }
}
