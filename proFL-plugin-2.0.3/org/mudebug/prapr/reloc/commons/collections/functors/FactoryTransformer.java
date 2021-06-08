// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import org.mudebug.prapr.reloc.commons.collections.Factory;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Transformer;

public class FactoryTransformer implements Transformer, Serializable
{
    private static final long serialVersionUID = -6817674502475353160L;
    private final Factory iFactory;
    
    public static Transformer getInstance(final Factory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Factory must not be null");
        }
        return new FactoryTransformer(factory);
    }
    
    public FactoryTransformer(final Factory factory) {
        this.iFactory = factory;
    }
    
    public Object transform(final Object input) {
        return this.iFactory.create();
    }
    
    public Factory getFactory() {
        return this.iFactory;
    }
}
