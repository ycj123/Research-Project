// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import org.mudebug.prapr.reloc.commons.collections.Transformer;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Closure;

public class TransformerClosure implements Closure, Serializable
{
    private static final long serialVersionUID = -5194992589193388969L;
    private final Transformer iTransformer;
    
    public static Closure getInstance(final Transformer transformer) {
        if (transformer == null) {
            return NOPClosure.INSTANCE;
        }
        return new TransformerClosure(transformer);
    }
    
    public TransformerClosure(final Transformer transformer) {
        this.iTransformer = transformer;
    }
    
    public void execute(final Object input) {
        this.iTransformer.transform(input);
    }
    
    public Transformer getTransformer() {
        return this.iTransformer;
    }
}
