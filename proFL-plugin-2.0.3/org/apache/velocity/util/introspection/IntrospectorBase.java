// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

import java.lang.reflect.Method;
import org.apache.velocity.runtime.log.Log;

public abstract class IntrospectorBase implements IntrospectorCacheListener
{
    protected final Log log;
    private final IntrospectorCache introspectorCache;
    
    protected IntrospectorBase(final Log log) {
        this.log = log;
        (this.introspectorCache = new IntrospectorCacheImpl(log)).addListener(this);
    }
    
    public Method getMethod(final Class c, final String name, final Object[] params) throws IllegalArgumentException, MethodMap.AmbiguousException {
        if (c == null) {
            throw new IllegalArgumentException("class object is null!");
        }
        if (params == null) {
            throw new IllegalArgumentException("params object is null!");
        }
        ClassMap classMap = null;
        final IntrospectorCache ic = this.getIntrospectorCache();
        synchronized (ic) {
            classMap = ic.get(c);
            if (classMap == null) {
                classMap = ic.put(c);
            }
        }
        return classMap.findMethod(name, params);
    }
    
    protected IntrospectorCache getIntrospectorCache() {
        return this.introspectorCache;
    }
    
    protected void clearCache() {
        this.getIntrospectorCache().clear();
    }
    
    protected ClassMap createClassMap(final Class c) {
        return this.getIntrospectorCache().put(c);
    }
    
    protected ClassMap lookupClassMap(final Class c) {
        return this.getIntrospectorCache().get(c);
    }
    
    public void triggerClear() {
    }
    
    public void triggerGet(final Class c, final ClassMap classMap) {
    }
    
    public void triggerPut(final Class c, final ClassMap classMap) {
    }
}
