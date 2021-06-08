// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import org.mudebug.prapr.reloc.commons.collections.Predicate;

public interface PredicateDecorator extends Predicate
{
    Predicate[] getPredicates();
}
