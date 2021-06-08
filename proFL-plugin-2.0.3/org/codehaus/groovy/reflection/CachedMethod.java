// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import org.codehaus.groovy.runtime.callsite.StaticMetaMethodSite;
import org.codehaus.groovy.runtime.callsite.PojoMetaMethodSite;
import org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite;
import org.codehaus.groovy.runtime.callsite.CallSiteGenerator;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.metaclass.MethodHelper;
import java.lang.reflect.InvocationTargetException;
import groovy.lang.MissingMethodException;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.codehaus.groovy.classgen.BytecodeHelper;
import java.util.Comparator;
import java.util.Arrays;
import java.lang.reflect.Constructor;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import groovy.lang.MetaMethod;

public class CachedMethod extends MetaMethod implements Comparable
{
    public final CachedClass cachedClass;
    private final Method cachedMethod;
    private int hashCode;
    private static MyComparator comparator;
    private SoftReference<Constructor> pogoCallSiteConstructor;
    private SoftReference<Constructor> pojoCallSiteConstructor;
    private SoftReference<Constructor> staticCallSiteConstructor;
    
    public CachedMethod(final CachedClass clazz, final Method method) {
        this.cachedMethod = method;
        this.cachedClass = clazz;
    }
    
    public CachedMethod(final Method method) {
        this(ReflectionCache.getCachedClass(method.getDeclaringClass()), method);
    }
    
    public static CachedMethod find(final Method method) {
        final CachedMethod[] methods = ReflectionCache.getCachedClass(method.getDeclaringClass()).getMethods();
        final int i = Arrays.binarySearch(methods, method, CachedMethod.comparator);
        if (i < 0) {
            return null;
        }
        return methods[i];
    }
    
    @Override
    protected Class[] getPT() {
        return this.cachedMethod.getParameterTypes();
    }
    
    @Override
    public String getName() {
        return this.cachedMethod.getName();
    }
    
    @Override
    public String getDescriptor() {
        return BytecodeHelper.getMethodDescriptor(this.getReturnType(), this.getNativeParameterTypes());
    }
    
    @Override
    public CachedClass getDeclaringClass() {
        return this.cachedClass;
    }
    
    @Override
    public final Object invoke(final Object object, final Object[] arguments) {
        try {
            return this.cachedMethod.invoke(object, arguments);
        }
        catch (IllegalArgumentException e) {
            throw new InvokerInvocationException(e);
        }
        catch (IllegalAccessException e2) {
            throw new InvokerInvocationException(e2);
        }
        catch (InvocationTargetException e3) {
            final Throwable cause = e3.getCause();
            throw (cause instanceof RuntimeException && !(cause instanceof MissingMethodException)) ? cause : new InvokerInvocationException(e3);
        }
    }
    
    public ParameterTypes getParamTypes() {
        return null;
    }
    
    @Override
    public Class getReturnType() {
        return this.cachedMethod.getReturnType();
    }
    
    public int getParamsCount() {
        return this.getParameterTypes().length;
    }
    
    @Override
    public int getModifiers() {
        return this.cachedMethod.getModifiers();
    }
    
    @Override
    public String getSignature() {
        return this.getName() + this.getDescriptor();
    }
    
    public final Method setAccessible() {
        return this.cachedMethod;
    }
    
    @Override
    public boolean isStatic() {
        return MethodHelper.isStatic(this.cachedMethod);
    }
    
    public int compareTo(final Object o) {
        if (o instanceof CachedMethod) {
            return this.compareToCachedMethod((CachedMethod)o);
        }
        return this.compareToMethod((Method)o);
    }
    
    private int compareToCachedMethod(final CachedMethod m) {
        if (m == null) {
            return -1;
        }
        final int strComp = this.getName().compareTo(m.getName());
        if (strComp != 0) {
            return strComp;
        }
        final int retComp = this.getReturnType().getName().compareTo(m.getReturnType().getName());
        if (retComp != 0) {
            return retComp;
        }
        final CachedClass[] params = this.getParameterTypes();
        final CachedClass[] mparams = m.getParameterTypes();
        final int pd = params.length - mparams.length;
        if (pd != 0) {
            return pd;
        }
        for (int i = 0; i != params.length; ++i) {
            final int nameComp = params[i].getName().compareTo(mparams[i].getName());
            if (nameComp != 0) {
                return nameComp;
            }
        }
        throw new RuntimeException("Should never happen");
    }
    
    private int compareToMethod(final Method m) {
        if (m == null) {
            return -1;
        }
        final int strComp = this.getName().compareTo(m.getName());
        if (strComp != 0) {
            return strComp;
        }
        final int retComp = this.getReturnType().getName().compareTo(m.getReturnType().getName());
        if (retComp != 0) {
            return retComp;
        }
        final CachedClass[] params = this.getParameterTypes();
        final Class[] mparams = m.getParameterTypes();
        final int pd = params.length - mparams.length;
        if (pd != 0) {
            return pd;
        }
        for (int i = 0; i != params.length; ++i) {
            final int nameComp = params[i].getName().compareTo(mparams[i].getName());
            if (nameComp != 0) {
                return nameComp;
            }
        }
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return (o instanceof CachedMethod && this.cachedMethod.equals(((CachedMethod)o).cachedMethod)) || (o instanceof Method && this.cachedMethod.equals(o));
    }
    
    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.cachedMethod.hashCode();
            if (this.hashCode == 0) {
                this.hashCode = -889274690;
            }
        }
        return this.hashCode;
    }
    
    @Override
    public String toString() {
        return this.cachedMethod.toString();
    }
    
    public CallSite createPogoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final Class[] params) {
        if (!this.hasPogoCallSiteConstructor()) {
            Constructor constr = null;
            if (CallSiteGenerator.isCompilable(this)) {
                constr = CallSiteGenerator.compilePogoMethod(this);
                if (constr != null) {
                    this.pogoCallSiteConstructor = new SoftReference<Constructor>(constr);
                }
            }
        }
        if (this.hasPogoCallSiteConstructor()) {
            final Constructor constructor = this.pogoCallSiteConstructor.get();
            if (constructor != null) {
                try {
                    return constructor.newInstance(site, metaClass, this, params);
                }
                catch (Throwable t) {}
            }
        }
        return new PogoMetaMethodSite.PogoCachedMethodSiteNoUnwrapNoCoerce(site, metaClass, this, params);
    }
    
    public CallSite createPojoMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final Class[] params) {
        if (!this.hasPojoCallSiteConstructor()) {
            Constructor constr = null;
            if (CallSiteGenerator.isCompilable(this)) {
                constr = CallSiteGenerator.compilePojoMethod(this);
                if (constr != null) {
                    this.pojoCallSiteConstructor = new SoftReference<Constructor>(constr);
                }
            }
        }
        if (this.hasPogoCallSiteConstructor()) {
            final Constructor constructor = this.pojoCallSiteConstructor.get();
            if (constructor != null) {
                try {
                    return constructor.newInstance(site, metaClass, this, params);
                }
                catch (Throwable t) {}
            }
        }
        return new PojoMetaMethodSite.PojoCachedMethodSiteNoUnwrapNoCoerce(site, metaClass, this, params);
    }
    
    public CallSite createStaticMetaMethodSite(final CallSite site, final MetaClassImpl metaClass, final Class[] params) {
        if (!this.hasStaticCallSiteConstructor()) {
            Constructor constr = null;
            if (CallSiteGenerator.isCompilable(this)) {
                constr = CallSiteGenerator.compileStaticMethod(this);
                if (constr != null) {
                    this.staticCallSiteConstructor = new SoftReference<Constructor>(constr);
                }
            }
        }
        if (this.hasStaticCallSiteConstructor()) {
            final Constructor constructor = this.staticCallSiteConstructor.get();
            if (constructor != null) {
                try {
                    return constructor.newInstance(site, metaClass, this, params);
                }
                catch (Throwable t) {}
            }
        }
        return new StaticMetaMethodSite.StaticMetaMethodSiteNoUnwrapNoCoerce(site, metaClass, this, params);
    }
    
    public boolean hasPogoCallSiteConstructor() {
        return this.pogoCallSiteConstructor != null && this.pogoCallSiteConstructor.get() != null;
    }
    
    public boolean hasPojoCallSiteConstructor() {
        return this.pojoCallSiteConstructor != null && this.pojoCallSiteConstructor.get() != null;
    }
    
    public boolean hasStaticCallSiteConstructor() {
        return this.staticCallSiteConstructor != null && this.staticCallSiteConstructor.get() != null;
    }
    
    public Method getCachedMethod() {
        return this.cachedMethod;
    }
    
    static {
        CachedMethod.comparator = new MyComparator();
    }
    
    private static class MyComparator implements Comparator
    {
        public int compare(final Object o1, final Object o2) {
            if (o1 instanceof CachedMethod) {
                return ((CachedMethod)o1).compareTo(o2);
            }
            if (o2 instanceof CachedMethod) {
                return -((CachedMethod)o2).compareTo(o1);
            }
            throw new ClassCastException("One of the two comperables must be a CachedMethod");
        }
    }
}
