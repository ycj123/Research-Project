// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.iterators;

import org.mudebug.prapr.reloc.commons.collections.functors.UniquePredicate;
import java.util.Iterator;

public class UniqueFilterIterator extends FilterIterator
{
    public UniqueFilterIterator(final Iterator iterator) {
        super(iterator, UniquePredicate.getInstance());
    }
}
