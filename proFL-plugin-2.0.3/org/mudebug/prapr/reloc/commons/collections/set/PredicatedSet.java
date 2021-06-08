// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.set;

import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Predicate;
import java.util.Set;
import org.mudebug.prapr.reloc.commons.collections.collection.PredicatedCollection;

public class PredicatedSet extends PredicatedCollection implements Set
{
    private static final long serialVersionUID = -684521469108685117L;
    
    public static Set decorate(final Set set, final Predicate predicate) {
        return new PredicatedSet(set, predicate);
    }
    
    protected PredicatedSet(final Set set, final Predicate predicate) {
        super(set, predicate);
    }
    
    protected Set getSet() {
        return (Set)this.getCollection();
    }
}
