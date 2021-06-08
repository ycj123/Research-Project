// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.collections.functors;

import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.collections.Closure;

public class NOPClosure implements Closure, Serializable
{
    private static final long serialVersionUID = 3518477308466486130L;
    public static final Closure INSTANCE;
    
    public static Closure getInstance() {
        return NOPClosure.INSTANCE;
    }
    
    private NOPClosure() {
    }
    
    public void execute(final Object input) {
    }
    
    static {
        INSTANCE = new NOPClosure();
    }
}
