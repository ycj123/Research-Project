// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.velocity.runtime.log.Log;

public abstract class AbstractExecutor
{
    protected Log log;
    private Method method;
    
    public AbstractExecutor() {
        this.log = null;
        this.method = null;
    }
    
    public abstract Object execute(final Object p0) throws IllegalAccessException, InvocationTargetException;
    
    public boolean isAlive() {
        return this.method != null;
    }
    
    public Method getMethod() {
        return this.method;
    }
    
    protected void setMethod(final Method method) {
        this.method = method;
    }
}
