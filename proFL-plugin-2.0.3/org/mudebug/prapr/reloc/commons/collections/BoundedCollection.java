// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections;

import java.util.Collection;

public interface BoundedCollection extends Collection
{
    boolean isFull();
    
    int maxSize();
}
