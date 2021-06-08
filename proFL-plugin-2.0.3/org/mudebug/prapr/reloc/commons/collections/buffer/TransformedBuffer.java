// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.buffer;

import java.util.Collection;
import org.mudebug.prapr.reloc.commons.collections.Transformer;
import org.mudebug.prapr.reloc.commons.collections.Buffer;
import org.mudebug.prapr.reloc.commons.collections.collection.TransformedCollection;

public class TransformedBuffer extends TransformedCollection implements Buffer
{
    private static final long serialVersionUID = -7901091318986132033L;
    
    public static Buffer decorate(final Buffer buffer, final Transformer transformer) {
        return new TransformedBuffer(buffer, transformer);
    }
    
    protected TransformedBuffer(final Buffer buffer, final Transformer transformer) {
        super(buffer, transformer);
    }
    
    protected Buffer getBuffer() {
        return (Buffer)super.collection;
    }
    
    public Object get() {
        return this.getBuffer().get();
    }
    
    public Object remove() {
        return this.getBuffer().remove();
    }
}
