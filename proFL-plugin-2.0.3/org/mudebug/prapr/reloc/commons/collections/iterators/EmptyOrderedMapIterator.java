// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.iterators;

import org.mudebug.prapr.reloc.commons.collections.ResettableIterator;
import org.mudebug.prapr.reloc.commons.collections.OrderedMapIterator;

public class EmptyOrderedMapIterator extends AbstractEmptyIterator implements OrderedMapIterator, ResettableIterator
{
    public static final OrderedMapIterator INSTANCE;
    
    protected EmptyOrderedMapIterator() {
    }
    
    static {
        INSTANCE = new EmptyOrderedMapIterator();
    }
}
