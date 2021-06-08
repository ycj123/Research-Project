// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.lang.reflect.InvocationTargetException;
import org.apache.velocity.runtime.log.RuntimeLoggerLog;
import org.apache.velocity.runtime.RuntimeLogger;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.util.introspection.Introspector;

public class GetExecutor extends AbstractExecutor
{
    private final Introspector introspector;
    private Object[] params;
    
    public GetExecutor(final Log log, final Introspector introspector, final Class clazz, final String property) {
        this.params = new Object[0];
        this.log = log;
        this.introspector = introspector;
        if (property != null) {
            this.params = new Object[] { property };
        }
        this.discover(clazz);
    }
    
    public GetExecutor(final RuntimeLogger rlog, final Introspector introspector, final Class clazz, final String property) {
        this(new RuntimeLoggerLog(rlog), introspector, clazz, property);
    }
    
    protected void discover(final Class clazz) {
        try {
            this.setMethod(this.introspector.getMethod(clazz, "get", this.params));
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.log.error("While looking for get('" + this.params[0] + "') method:", e2);
        }
    }
    
    public Object execute(final Object o) throws IllegalAccessException, InvocationTargetException {
        return this.isAlive() ? this.getMethod().invoke(o, this.params) : null;
    }
}
