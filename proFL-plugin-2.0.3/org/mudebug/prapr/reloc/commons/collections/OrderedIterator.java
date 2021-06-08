// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections;

import java.util.Iterator;

public interface OrderedIterator extends Iterator
{
    boolean hasPrevious();
    
    Object previous();
}
