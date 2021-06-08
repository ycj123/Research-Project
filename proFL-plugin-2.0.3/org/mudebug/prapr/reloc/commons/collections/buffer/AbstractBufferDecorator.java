// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.buffer;

import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Buffer;
import org.mudebug.prapr.reloc.commons.collections.collection.AbstractCollectionDecorator;

public abstract class AbstractBufferDecorator extends AbstractCollectionDecorator implements Buffer
{
    protected AbstractBufferDecorator() {
    }
    
    protected AbstractBufferDecorator(final Buffer buffer) {
        super(buffer);
    }
    
    protected Buffer getBuffer() {
        return (Buffer)this.getCollection();
    }
    
    public Object get() {
        return this.getBuffer().get();
    }
    
    public Object remove() {
        return this.getBuffer().remove();
    }
}
