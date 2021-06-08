// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Predicate;

public final class UniquePredicate implements Predicate, Serializable
{
    private static final long serialVersionUID = -3319417438027438040L;
    private final Set iSet;
    
    public static Predicate getInstance() {
        return new UniquePredicate();
    }
    
    public UniquePredicate() {
        this.iSet = new HashSet();
    }
    
    public boolean evaluate(final Object object) {
        return this.iSet.add(object);
    }
}
