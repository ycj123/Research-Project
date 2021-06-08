// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.MethodClosure;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.CachedClass;
import org.codehaus.groovy.reflection.CachedMethod;
import groovy.lang.Closure;
import groovy.lang.ClosureInvokingMethod;
import groovy.lang.MetaMethod;

public class ClosureMetaMethod extends MetaMethod implements ClosureInvokingMethod
{
    private final Closure callable;
    private final CachedMethod doCall;
    private final String name;
    private final CachedClass declaringClass;
    
    public ClosureMetaMethod(final String name, final Closure c, final CachedMethod doCall) {
        this(name, c.getOwner().getClass(), c, doCall);
    }
    
    public ClosureMetaMethod(final String name, final Class declaringClass, final Closure c, final CachedMethod doCall) {
        super(doCall.getNativeParameterTypes());
        this.name = name;
        this.callable = c;
        this.doCall = doCall;
        this.declaringClass = ReflectionCache.getCachedClass(declaringClass);
    }
    
    @Override
    public int getModifiers() {
        return 1;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public Class getReturnType() {
        return Object.class;
    }
    
    @Override
    public CachedClass getDeclaringClass() {
        return this.declaringClass;
    }
    
    @Override
    public Object invoke(final Object object, Object[] arguments) {
        final Closure cloned = (Closure)this.callable.clone();
        cloned.setDelegate(object);
        arguments = this.coerceArgumentsToClasses(arguments);
        return this.doCall.invoke(cloned, arguments);
    }
    
    public Closure getClosure() {
        return this.callable;
    }
    
    public static List<MetaMethod> createMethodList(final String name, final Class declaringClass, final Closure closure) {
        final List<MetaMethod> res = new ArrayList<MetaMethod>();
        if (closure instanceof MethodClosure) {
            final MethodClosure methodClosure = (MethodClosure)closure;
            final Object owner = closure.getOwner();
            final Class ownerClass = (Class)((owner instanceof Class) ? owner : owner.getClass());
            for (final CachedMethod method : ReflectionCache.getCachedClass(ownerClass).getMethods()) {
                if (method.getName().equals(methodClosure.getMethod())) {
                    final MetaMethod metaMethod = new MethodClosureMetaMethod(name, declaringClass, closure, method);
                    res.add(adjustParamTypesForStdMethods(metaMethod, name));
                }
            }
        }
        else if (closure instanceof GeneratedClosure) {
            for (final CachedMethod method2 : ReflectionCache.getCachedClass(closure.getClass()).getMethods()) {
                if (method2.getName().equals("doCall")) {
                    final MetaMethod metaMethod2 = new ClosureMetaMethod(name, declaringClass, closure, method2);
                    res.add(adjustParamTypesForStdMethods(metaMethod2, name));
                }
            }
        }
        else {
            final MetaMethod metaMethod3 = new MetaMethod(closure.getParameterTypes()) {
                @Override
                public int getModifiers() {
                    return 1;
                }
                
                @Override
                public String getName() {
                    return name;
                }
                
                @Override
                public Class getReturnType() {
                    return Object.class;
                }
                
                @Override
                public CachedClass getDeclaringClass() {
                    return ReflectionCache.getCachedClass(declaringClass);
                }
                
                @Override
                public Object invoke(final Object object, Object[] arguments) {
                    final Closure cloned = (Closure)closure.clone();
                    cloned.setDelegate(object);
                    arguments = this.coerceArgumentsToClasses(arguments);
                    return InvokerHelper.invokeMethod(cloned, "call", arguments);
                }
            };
            res.add(adjustParamTypesForStdMethods(metaMethod3, name));
        }
        return res;
    }
    
    private static MetaMethod adjustParamTypesForStdMethods(final MetaMethod metaMethod, final String methodName) {
        Class[] nativeParamTypes = metaMethod.getNativeParameterTypes();
        nativeParamTypes = ((nativeParamTypes != null) ? nativeParamTypes : new Class[0]);
        if ("methodMissing".equals(methodName) && nativeParamTypes.length == 2 && nativeParamTypes[0] != String.class) {
            nativeParamTypes[0] = String.class;
        }
        return metaMethod;
    }
    
    public CachedMethod getDoCall() {
        return this.doCall;
    }
    
    public static ClosureMetaMethod copy(final ClosureMetaMethod closureMethod) {
        if (closureMethod instanceof MethodClosureMetaMethod) {
            return new MethodClosureMetaMethod(closureMethod.getName(), closureMethod.getDeclaringClass().getTheClass(), closureMethod.getClosure(), closureMethod.getDoCall());
        }
        return new ClosureMetaMethod(closureMethod.getName(), closureMethod.getDeclaringClass().getTheClass(), closureMethod.getClosure(), closureMethod.getDoCall());
    }
    
    private static class MethodClosureMetaMethod extends ClosureMetaMethod
    {
        public MethodClosureMetaMethod(final String name, final Class declaringClass, final Closure closure, final CachedMethod method) {
            super(name, declaringClass, closure, method);
        }
        
        @Override
        public Object invoke(final Object object, final Object[] arguments) {
            return this.getDoCall().invoke(this.getClosure().getOwner(), arguments);
        }
    }
}
