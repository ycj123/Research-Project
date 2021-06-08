// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.util.Map;
import org.apache.velocity.runtime.log.Log;

public class MapSetExecutor extends SetExecutor
{
    private final String property;
    
    public MapSetExecutor(final Log log, final Class clazz, final String property) {
        this.log = log;
        this.property = property;
        this.discover(clazz);
    }
    
    protected void discover(final Class clazz) {
        final Class[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            if (interfaces[i].equals(Map.class)) {
                try {
                    if (this.property != null) {
                        this.setMethod(Map.class.getMethod("put", Object.class, Object.class));
                    }
                    break;
                }
                catch (RuntimeException e) {
                    throw e;
                }
                catch (Exception e2) {
                    this.log.error("While looking for get('" + this.property + "') method:", e2);
                    break;
                }
            }
        }
    }
    
    public Object execute(final Object o, final Object arg) {
        return ((Map)o).put(this.property, arg);
    }
}
