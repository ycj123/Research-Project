// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.lang.reflect.InvocationTargetException;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.util.introspection.Introspector;

public class PutExecutor extends SetExecutor
{
    private final Introspector introspector;
    private final String property;
    
    public PutExecutor(final Log log, final Introspector introspector, final Class clazz, final Object arg, final String property) {
        this.log = log;
        this.introspector = introspector;
        this.property = property;
        this.discover(clazz, arg);
    }
    
    protected void discover(final Class clazz, final Object arg) {
        Object[] params;
        if (this.property == null) {
            params = new Object[] { arg };
        }
        else {
            params = new Object[] { this.property, arg };
        }
        try {
            this.setMethod(this.introspector.getMethod(clazz, "put", params));
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.log.error("While looking for put('" + params[0] + "') method:", e2);
        }
    }
    
    public Object execute(final Object o, final Object value) throws IllegalAccessException, InvocationTargetException {
        if (this.isAlive()) {
            Object[] params;
            if (this.property == null) {
                params = new Object[] { value };
            }
            else {
                params = new Object[] { this.property, value };
            }
            return this.getMethod().invoke(o, params);
        }
        return null;
    }
}
