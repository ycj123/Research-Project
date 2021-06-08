// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.lang.reflect.InvocationTargetException;
import org.apache.velocity.runtime.log.RuntimeLoggerLog;
import org.apache.velocity.runtime.RuntimeLogger;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.util.introspection.Introspector;

public class PropertyExecutor extends AbstractExecutor
{
    private final Introspector introspector;
    
    public PropertyExecutor(final Log log, final Introspector introspector, final Class clazz, final String property) {
        this.log = log;
        this.introspector = introspector;
        if (StringUtils.isNotEmpty(property)) {
            this.discover(clazz, property);
        }
    }
    
    public PropertyExecutor(final RuntimeLogger r, final Introspector introspector, final Class clazz, final String property) {
        this(new RuntimeLoggerLog(r), introspector, clazz, property);
    }
    
    protected Introspector getIntrospector() {
        return this.introspector;
    }
    
    protected void discover(final Class clazz, final String property) {
        try {
            final Object[] params = new Object[0];
            final StringBuffer sb = new StringBuffer("get");
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
            this.log.error("While looking for property getter for '" + property + "':", e2);
        }
    }
    
    public Object execute(final Object o) throws IllegalAccessException, InvocationTargetException {
        return this.isAlive() ? this.getMethod().invoke(o, (Object[])null) : null;
    }
}
