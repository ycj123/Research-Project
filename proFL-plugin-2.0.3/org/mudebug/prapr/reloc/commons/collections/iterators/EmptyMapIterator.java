// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.iterators;

import org.mudebug.prapr.reloc.commons.collections.ResettableIterator;
import org.mudebug.prapr.reloc.commons.collections.MapIterator;

public class EmptyMapIterator extends AbstractEmptyIterator implements MapIterator, ResettableIterator
{
    public static final MapIterator INSTANCE;
    
    protected EmptyMapIterator() {
    }
    
    static {
        INSTANCE = new EmptyMapIterator();
    }
}
