// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Transformer;

public final class StringValueTransformer implements Transformer, Serializable
{
    private static final long serialVersionUID = 7511110693171758606L;
    public static final Transformer INSTANCE;
    
    public static Transformer getInstance() {
        return StringValueTransformer.INSTANCE;
    }
    
    private StringValueTransformer() {
    }
    
    public Object transform(final Object input) {
        return String.valueOf(input);
    }
    
    static {
        INSTANCE = new StringValueTransformer();
    }
}
