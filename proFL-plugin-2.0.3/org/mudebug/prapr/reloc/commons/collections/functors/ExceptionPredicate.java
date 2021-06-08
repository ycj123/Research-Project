// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import org.mudebug.prapr.reloc.commons.collections.FunctorException;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Predicate;

public final class ExceptionPredicate implements Predicate, Serializable
{
    private static final long serialVersionUID = 7179106032121985545L;
    public static final Predicate INSTANCE;
    
    public static Predicate getInstance() {
        return ExceptionPredicate.INSTANCE;
    }
    
    private ExceptionPredicate() {
    }
    
    public boolean evaluate(final Object object) {
        throw new FunctorException("ExceptionPredicate invoked");
    }
    
    static {
        INSTANCE = new ExceptionPredicate();
    }
}
