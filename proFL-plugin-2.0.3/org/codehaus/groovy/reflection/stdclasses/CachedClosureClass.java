// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection.stdclasses;

import org.codehaus.groovy.reflection.CachedMethod;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.reflection.CachedClass;

public class CachedClosureClass extends CachedClass
{
    private final Class[] parameterTypes;
    private final int maximumNumberOfParameters;
    
    public CachedClosureClass(final Class klazz, final ClassInfo classInfo) {
        super(klazz, classInfo);
        final CachedMethod[] methods = this.getMethods();
        int maximumNumberOfParameters = -1;
        Class[] parameterTypes = null;
        for (final CachedMethod method : methods) {
            if ("doCall".equals(method.getName())) {
                final Class[] pt = method.getNativeParameterTypes();
                if (pt.length > maximumNumberOfParameters) {
                    parameterTypes = pt;
                    maximumNumberOfParameters = parameterTypes.length;
                }
            }
        }
        maximumNumberOfParameters = Math.max(maximumNumberOfParameters, 0);
        this.maximumNumberOfParameters = maximumNumberOfParameters;
        this.parameterTypes = parameterTypes;
    }
    
    public Class[] getParameterTypes() {
        return this.parameterTypes;
    }
    
    public int getMaximumNumberOfParameters() {
        return this.maximumNumberOfParameters;
    }
}
