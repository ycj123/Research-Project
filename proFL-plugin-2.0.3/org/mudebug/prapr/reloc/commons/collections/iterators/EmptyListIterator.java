// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.iterators;

import java.util.ListIterator;
import org.mudebug.prapr.reloc.commons.collections.ResettableListIterator;

public class EmptyListIterator extends AbstractEmptyIterator implements ResettableListIterator
{
    public static final ResettableListIterator RESETTABLE_INSTANCE;
    public static final ListIterator INSTANCE;
    
    protected EmptyListIterator() {
    }
    
    static {
        RESETTABLE_INSTANCE = new EmptyListIterator();
        INSTANCE = EmptyListIterator.RESETTABLE_INSTANCE;
    }
}
