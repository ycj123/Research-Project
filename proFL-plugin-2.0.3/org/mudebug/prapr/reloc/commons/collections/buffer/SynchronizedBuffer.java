// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.buffer;

import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Buffer;
import org.mudebug.prapr.reloc.commons.collections.collection.SynchronizedCollection;

public class SynchronizedBuffer extends SynchronizedCollection implements Buffer
{
    private static final long serialVersionUID = -6859936183953626253L;
    
    public static Buffer decorate(final Buffer buffer) {
        return new SynchronizedBuffer(buffer);
    }
    
    protected SynchronizedBuffer(final Buffer buffer) {
        super(buffer);
    }
    
    protected SynchronizedBuffer(final Buffer buffer, final Object lock) {
        super(buffer, lock);
    }
    
    protected Buffer getBuffer() {
        return (Buffer)super.collection;
    }
    
    public Object get() {
        synchronized (super.lock) {
            return this.getBuffer().get();
        }
    }
    
    public Object remove() {
        synchronized (super.lock) {
            return this.getBuffer().remove();
        }
    }
}
