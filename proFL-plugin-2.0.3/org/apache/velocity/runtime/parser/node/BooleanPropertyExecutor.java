// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.lang.reflect.Method;
import org.apache.velocity.runtime.log.RuntimeLoggerLog;
import org.apache.velocity.runtime.RuntimeLogger;
import org.apache.velocity.util.introspection.Introspector;
import org.apache.velocity.runtime.log.Log;

public class BooleanPropertyExecutor extends PropertyExecutor
{
    public BooleanPropertyExecutor(final Log log, final Introspector introspector, final Class clazz, final String property) {
        super(log, introspector, clazz, property);
    }
    
    public BooleanPropertyExecutor(final RuntimeLogger rlog, final Introspector introspector, final Class clazz, final String property) {
        super(new RuntimeLoggerLog(rlog), introspector, clazz, property);
    }
    
    protected void discover(final Class clazz, final String property) {
        try {
            final Object[] params = new Object[0];
            final StringBuffer sb = new StringBuffer("is");
            sb.append(property);
            this.setMethod(this.getIntrospector().getMethod(clazz, sb.toString(), params));
            if (!this.isAlive()) {
                final char c = sb.charAt(2);
                if (Character.isLowerCase(c)) {
                    sb.setCharAt(2, Character.toUpperCase(c));
                }
                else {
                    sb.setCharAt(2, Character.toLowerCase(c));
                }
                this.setMethod(this.getIntrospector().getMethod(clazz, sb.toString(), params));
            }
            if (this.isAlive() && this.getMethod().getReturnType() != Boolean.TYPE) {
                this.setMethod(null);
            }
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.log.error("While looking for boolean property getter for '" + property + "':", e2);
        }
    }
}
