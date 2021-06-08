// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.Closure;
import java.lang.reflect.Method;
import java.util.Map;

public class ConvertedMap extends ConversionHandler
{
    protected ConvertedMap(final Map closures) {
        super(closures);
    }
    
    @Override
    public Object invokeCustom(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final Map m = (Map)this.getDelegate();
        final Closure cl = m.get(method.getName());
        if (cl == null) {
            throw new UnsupportedOperationException();
        }
        return cl.call(args);
    }
    
    @Override
    public String toString() {
        return DefaultGroovyMethods.toString(this.getDelegate());
    }
    
    @Override
    protected boolean checkMethod(final Method method) {
        return isCoreObjectMethod(method);
    }
    
    public static boolean isCoreObjectMethod(final Method method) {
        return ConversionHandler.isCoreObjectMethod(method) && !"toString".equals(method.getName());
    }
}
