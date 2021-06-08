// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Predicate;

public final class TruePredicate implements Predicate, Serializable
{
    private static final long serialVersionUID = 3374767158756189740L;
    public static final Predicate INSTANCE;
    
    public static Predicate getInstance() {
        return TruePredicate.INSTANCE;
    }
    
    private TruePredicate() {
    }
    
    public boolean evaluate(final Object object) {
        return true;
    }
    
    static {
        INSTANCE = new TruePredicate();
    }
}
