// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections;

import java.util.Map;

public interface BoundedMap extends Map
{
    boolean isFull();
    
    int maxSize();
}
