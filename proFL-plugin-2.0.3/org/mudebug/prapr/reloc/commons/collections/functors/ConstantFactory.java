// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Factory;

public class ConstantFactory implements Factory, Serializable
{
    private static final long serialVersionUID = -3520677225766901240L;
    public static final Factory NULL_INSTANCE;
    private final Object iConstant;
    
    public static Factory getInstance(final Object constantToReturn) {
        if (constantToReturn == null) {
            return ConstantFactory.NULL_INSTANCE;
        }
        return new ConstantFactory(constantToReturn);
    }
    
    public ConstantFactory(final Object constantToReturn) {
        this.iConstant = constantToReturn;
    }
    
    public Object create() {
        return this.iConstant;
    }
    
    public Object getConstant() {
        return this.iConstant;
    }
    
    static {
        NULL_INSTANCE = new ConstantFactory(null);
    }
}
