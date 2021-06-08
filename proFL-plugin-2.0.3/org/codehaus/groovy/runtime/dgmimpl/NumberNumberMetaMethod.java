// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.dgmimpl;

import groovy.lang.MetaMethod;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.NumberMath;
import org.codehaus.groovy.runtime.callsite.PojoMetaMethodSite;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.runtime.callsite.CallSiteAwareMetaMethod;

public abstract class NumberNumberMetaMethod extends CallSiteAwareMetaMethod
{
    private static final CachedClass NUMBER_CLASS;
    private static final CachedClass[] NUMBER_CLASS_ARR;
    
    protected NumberNumberMetaMethod() {
        this.parameterTypes = NumberNumberMetaMethod.NUMBER_CLASS_ARR;
    }
    
    @Override
    public int getModifiers() {
        return 1;
    }
    
    @Override
    public Class getReturnType() {
        return NumberNumberMetaMethod.NUMBER_CLASS.getTheClass();
    }
    
    @Override
    public final CachedClass getDeclaringClass() {
        return NumberNumberMetaMethod.NUMBER_CLASS;
    }
    
    static {
        NUMBER_CLASS = ReflectionCache.getCachedClass(Number.class);
        NUMBER_CLASS_ARR = new CachedClass[] { NumberNumberMetaMethod.NUMBER_CLASS };
    }
    
    public abstract static class NumberNumberCallSite extends PojoMetaMethodSite
    {
        final NumberMath math;
        
        public NumberNumberCallSite(final CallSite site, final MetaClassImpl metaClass, final MetaMethod metaMethod, final Class[] params, final Number receiver, final Number arg) {
            super(site, metaClass, metaMethod, params);
            this.math = NumberMath.getMath(receiver, arg);
        }
    }
}
