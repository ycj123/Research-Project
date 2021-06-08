// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.buffer;

import org.mudebug.prapr.reloc.commons.collections.functors.InstanceofPredicate;
import org.mudebug.prapr.reloc.commons.collections.Buffer;

public class TypedBuffer
{
    public static Buffer decorate(final Buffer buffer, final Class type) {
        return new PredicatedBuffer(buffer, InstanceofPredicate.getInstance(type));
    }
    
    protected TypedBuffer() {
    }
}
