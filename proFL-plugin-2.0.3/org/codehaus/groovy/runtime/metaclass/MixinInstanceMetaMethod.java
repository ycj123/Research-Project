// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.MixinInMetaClass;
import groovy.lang.MetaMethod;

public class MixinInstanceMetaMethod extends MetaMethod
{
    private final MetaMethod method;
    private final MixinInMetaClass mixinInMetaClass;
    
    public MixinInstanceMetaMethod(final MetaMethod method, final MixinInMetaClass mixinInMetaClass) {
        this.method = method;
        this.mixinInMetaClass = mixinInMetaClass;
    }
    
    @Override
    public int getModifiers() {
        return this.method.getModifiers();
    }
    
    @Override
    public String getName() {
        return this.method.getName();
    }
    
    @Override
    public Class getReturnType() {
        return this.method.getReturnType();
    }
    
    @Override
    public CachedClass getDeclaringClass() {
        return this.mixinInMetaClass.getInstanceClass();
    }
    
    @Override
    public Object invoke(final Object object, final Object[] arguments) {
        this.method.getParameterTypes();
        return this.method.invoke(this.mixinInMetaClass.getMixinInstance(object), this.method.correctArguments(arguments));
    }
    
    @Override
    protected Class[] getPT() {
        return this.method.getNativeParameterTypes();
    }
}
