// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.buffer;

import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Predicate;
import org.mudebug.prapr.reloc.commons.collections.Buffer;
import org.mudebug.prapr.reloc.commons.collections.collection.PredicatedCollection;

public class PredicatedBuffer extends PredicatedCollection implements Buffer
{
    private static final long serialVersionUID = 2307609000539943581L;
    
    public static Buffer decorate(final Buffer buffer, final Predicate predicate) {
        return new PredicatedBuffer(buffer, predicate);
    }
    
    protected PredicatedBuffer(final Buffer buffer, final Predicate predicate) {
        super(buffer, predicate);
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
