// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.CachedClass;
import groovy.lang.MetaMethod;

public class TransformMetaMethod extends MetaMethod
{
    private MetaMethod metaMethod;
    
    public TransformMetaMethod(final MetaMethod metaMethod) {
        this.metaMethod = metaMethod;
        this.setParametersTypes(metaMethod.getParameterTypes());
        this.nativeParamTypes = metaMethod.getNativeParameterTypes();
    }
    
    @Override
    public int getModifiers() {
        return this.metaMethod.getModifiers();
    }
    
    @Override
    public String getName() {
        return this.metaMethod.getName();
    }
    
    @Override
    public Class getReturnType() {
        return this.metaMethod.getReturnType();
    }
    
    @Override
    public CachedClass getDeclaringClass() {
        return this.metaMethod.getDeclaringClass();
    }
    
    @Override
    public Object invoke(final Object object, final Object[] arguments) {
        return this.metaMethod.invoke(object, arguments);
    }
}
