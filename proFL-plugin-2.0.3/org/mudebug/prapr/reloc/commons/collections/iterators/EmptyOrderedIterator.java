// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.iterators;

import org.mudebug.prapr.reloc.commons.collections.ResettableIterator;
import org.mudebug.prapr.reloc.commons.collections.OrderedIterator;

public class EmptyOrderedIterator extends AbstractEmptyIterator implements OrderedIterator, ResettableIterator
{
    public static final OrderedIterator INSTANCE;
    
    protected EmptyOrderedIterator() {
    }
    
    static {
        INSTANCE = new EmptyOrderedIterator();
    }
}
