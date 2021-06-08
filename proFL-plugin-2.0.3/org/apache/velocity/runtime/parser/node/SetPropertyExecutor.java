// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.lang.reflect.InvocationTargetException;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.util.introspection.Introspector;

public class SetPropertyExecutor extends SetExecutor
{
    private final Introspector introspector;
    
    public SetPropertyExecutor(final Log log, final Introspector introspector, final Class clazz, final String property, final Object arg) {
        this.log = log;
        this.introspector = introspector;
        if (StringUtils.isNotEmpty(property)) {
            this.discover(clazz, property, arg);
        }
    }
    
    protected Introspector getIntrospector() {
        return this.introspector;
    }
    
    protected void discover(final Class clazz, final String property, final Object arg) {
        final Object[] params = { arg };
        try {
            final StringBuffer sb = new StringBuffer("set");
            sb.append(property);
            this.setMethod(this.introspector.getMethod(clazz, sb.toString(), params));
            if (!this.isAlive()) {
                final char c = sb.charAt(3);
                if (Character.isLowerCase(c)) {
                    sb.setCharAt(3, Character.toUpperCase(c));
                }
                else {
                    sb.setCharAt(3, Character.toLowerCase(c));
                }
                this.setMethod(this.introspector.getMethod(clazz, sb.toString(), params));
            }
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            this.log.error("While looking for property setter for '" + property + "':", e2);
        }
    }
    
    public Object execute(final Object o, final Object value) throws IllegalAccessException, InvocationTargetException {
        final Object[] params = { value };
        return this.isAlive() ? this.getMethod().invoke(o, params) : null;
    }
}
