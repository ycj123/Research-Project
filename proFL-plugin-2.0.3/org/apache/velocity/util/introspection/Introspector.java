// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

import java.lang.reflect.Method;
import org.apache.velocity.runtime.log.RuntimeLoggerLog;
import org.apache.velocity.runtime.RuntimeLogger;
import org.apache.velocity.runtime.log.Log;

public class Introspector extends IntrospectorBase
{
    public static final String CACHEDUMP_MSG = "Introspector: detected classloader change. Dumping cache.";
    
    public Introspector(final Log log) {
        super(log);
    }
    
    public Introspector(final RuntimeLogger logger) {
        this(new RuntimeLoggerLog(logger));
    }
    
    public Method getMethod(final Class c, final String name, final Object[] params) throws IllegalArgumentException {
        try {
            return super.getMethod(c, name, params);
        }
        catch (MethodMap.AmbiguousException ae) {
            final StringBuffer msg = new StringBuffer("Introspection Error : Ambiguous method invocation ").append(name).append("(");
            for (int i = 0; i < params.length; ++i) {
                if (i > 0) {
                    msg.append(", ");
                }
                if (params[i] == null) {
                    msg.append("null");
                }
                else {
                    msg.append(params[i].getClass().getName());
                }
            }
            msg.append(") for class ").append(c);
            this.log.error(msg.toString());
            return null;
        }
    }
    
    public void triggerClear() {
        super.triggerClear();
        this.log.info("Introspector: detected classloader change. Dumping cache.");
    }
}
