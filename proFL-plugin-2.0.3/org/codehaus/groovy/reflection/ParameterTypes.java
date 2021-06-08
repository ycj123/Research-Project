// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import org.codehaus.groovy.runtime.wrappers.Wrapper;
import org.codehaus.groovy.GroovyBugError;
import java.lang.reflect.Array;
import org.codehaus.groovy.runtime.MetaClassHelper;

public class ParameterTypes
{
    protected volatile Class[] nativeParamTypes;
    protected volatile CachedClass[] parameterTypes;
    protected boolean isVargsMethod;
    
    public ParameterTypes() {
    }
    
    public ParameterTypes(final Class[] pt) {
        this.nativeParamTypes = pt;
    }
    
    public ParameterTypes(final String[] pt) {
        this.nativeParamTypes = new Class[pt.length];
        for (int i = 0; i != pt.length; ++i) {
            try {
                this.nativeParamTypes[i] = Class.forName(pt[i]);
            }
            catch (ClassNotFoundException e) {
                final NoClassDefFoundError err = new NoClassDefFoundError();
                err.initCause(e);
                throw err;
            }
        }
    }
    
    public ParameterTypes(final CachedClass[] parameterTypes) {
        this.setParametersTypes(parameterTypes);
    }
    
    protected final void setParametersTypes(final CachedClass[] pt) {
        this.parameterTypes = pt;
        this.isVargsMethod = (pt.length > 0 && pt[pt.length - 1].isArray);
    }
    
    public CachedClass[] getParameterTypes() {
        if (this.parameterTypes == null) {
            this.getParametersTypes0();
        }
        return this.parameterTypes;
    }
    
    private synchronized void getParametersTypes0() {
        if (this.parameterTypes != null) {
            return;
        }
        final Class[] npt = (this.nativeParamTypes == null) ? this.getPT() : this.nativeParamTypes;
        final CachedClass[] pt = new CachedClass[npt.length];
        for (int i = 0; i != npt.length; ++i) {
            pt[i] = ReflectionCache.getCachedClass(npt[i]);
        }
        this.nativeParamTypes = npt;
        this.setParametersTypes(pt);
    }
    
    public Class[] getNativeParameterTypes() {
        if (this.nativeParamTypes == null) {
            this.getNativeParameterTypes0();
        }
        return this.nativeParamTypes;
    }
    
    private synchronized void getNativeParameterTypes0() {
        if (this.nativeParamTypes != null) {
            return;
        }
        Class[] npt;
        if (this.parameterTypes != null) {
            npt = new Class[this.parameterTypes.length];
            for (int i = 0; i != this.parameterTypes.length; ++i) {
                npt[i] = this.parameterTypes[i].getTheClass();
            }
        }
        else {
            npt = this.getPT();
        }
        this.nativeParamTypes = npt;
    }
    
    protected Class[] getPT() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }
    
    public boolean isVargsMethod(final Object[] arguments) {
        if (!this.isVargsMethod) {
            return false;
        }
        final int lenMinus1 = this.parameterTypes.length - 1;
        if (lenMinus1 == arguments.length) {
            return true;
        }
        if (lenMinus1 > arguments.length) {
            return false;
        }
        if (arguments.length > this.parameterTypes.length) {
            return true;
        }
        final Object last = arguments[arguments.length - 1];
        if (last == null) {
            return true;
        }
        final Class clazz = last.getClass();
        return !clazz.equals(this.parameterTypes[lenMinus1].getTheClass());
    }
    
    public final Object[] coerceArgumentsToClasses(Object[] argumentArray) {
        argumentArray = this.correctArguments(argumentArray);
        final CachedClass[] pt = this.parameterTypes;
        for (int len = argumentArray.length, i = 0; i < len; ++i) {
            final Object argument = argumentArray[i];
            if (argument != null) {
                argumentArray[i] = pt[i].coerceArgument(argument);
            }
        }
        return argumentArray;
    }
    
    public Object[] correctArguments(final Object[] argumentArray) {
        if (argumentArray == null) {
            return MetaClassHelper.EMPTY_ARRAY;
        }
        final CachedClass[] pt = this.getParameterTypes();
        if (pt.length == 1 && argumentArray.length == 0) {
            if (this.isVargsMethod) {
                return new Object[] { Array.newInstance(pt[0].getTheClass().getComponentType(), 0) };
            }
            return MetaClassHelper.ARRAY_WITH_NULL;
        }
        else {
            if (this.isVargsMethod && this.isVargsMethod(argumentArray)) {
                return fitToVargs(argumentArray, pt);
            }
            return argumentArray;
        }
    }
    
    private static Object[] fitToVargs(final Object[] argumentArray, final CachedClass[] paramTypes) {
        final Class vargsClass = ReflectionCache.autoboxType(paramTypes[paramTypes.length - 1].getTheClass().getComponentType());
        if (argumentArray.length == paramTypes.length - 1) {
            final Object[] newArgs = new Object[paramTypes.length];
            System.arraycopy(argumentArray, 0, newArgs, 0, argumentArray.length);
            final Object vargs = MetaClassHelper.makeArray(null, vargsClass, 0);
            newArgs[newArgs.length - 1] = vargs;
            return newArgs;
        }
        if (argumentArray.length == paramTypes.length) {
            final Object lastArgument = argumentArray[argumentArray.length - 1];
            if (lastArgument != null && !lastArgument.getClass().isArray()) {
                final Object wrapped = MetaClassHelper.makeArray(lastArgument, vargsClass, 1);
                System.arraycopy(argumentArray, argumentArray.length - 1, wrapped, 0, 1);
                final Object[] newArgs2 = new Object[paramTypes.length];
                System.arraycopy(argumentArray, 0, newArgs2, 0, paramTypes.length - 1);
                newArgs2[newArgs2.length - 1] = wrapped;
                return newArgs2;
            }
            return argumentArray;
        }
        else {
            if (argumentArray.length > paramTypes.length) {
                final Object[] newArgs = new Object[paramTypes.length];
                System.arraycopy(argumentArray, 0, newArgs, 0, paramTypes.length - 1);
                final int numberOfVargs = argumentArray.length - paramTypes.length;
                final Object vargs2 = MetaClassHelper.makeCommonArray(argumentArray, paramTypes.length - 1, vargsClass);
                newArgs[newArgs.length - 1] = vargs2;
                return newArgs;
            }
            throw new GroovyBugError("trying to call a vargs method without enough arguments");
        }
    }
    
    public boolean isValidMethod(final Class[] arguments) {
        if (arguments == null) {
            return true;
        }
        final int size = arguments.length;
        final CachedClass[] pt = this.getParameterTypes();
        final int paramMinus1 = pt.length - 1;
        if (this.isVargsMethod && size >= paramMinus1) {
            return this.isValidVarargsMethod(arguments, size, pt, paramMinus1);
        }
        if (pt.length == size) {
            return this.isValidExactMethod(arguments, pt);
        }
        return pt.length == 1 && size == 0 && !pt[0].isPrimitive;
    }
    
    private boolean isValidExactMethod(final Class[] arguments, final CachedClass[] pt) {
        for (int size = pt.length, i = 0; i < size; ++i) {
            if (!pt[i].isAssignableFrom(arguments[i])) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidExactMethod(final Object[] args) {
        this.getParametersTypes0();
        final int size = args.length;
        if (size != this.parameterTypes.length) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            if (args[i] != null && !this.parameterTypes[i].isAssignableFrom(args[i].getClass())) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidExactMethod(final Class[] args) {
        this.getParametersTypes0();
        final int size = args.length;
        if (size != this.parameterTypes.length) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            if (args[i] != null && !this.parameterTypes[i].isAssignableFrom(args[i])) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean testComponentAssignable(final Class toTestAgainst, final Class toTest) {
        final Class component = toTest.getComponentType();
        return component != null && MetaClassHelper.isAssignableFrom(toTestAgainst, component);
    }
    
    private boolean isValidVarargsMethod(final Class[] arguments, final int size, final CachedClass[] pt, final int paramMinus1) {
        for (int i = 0; i < paramMinus1; ++i) {
            if (!pt[i].isAssignableFrom(arguments[i])) {
                return false;
            }
        }
        final CachedClass varg = pt[paramMinus1];
        final Class clazz = varg.getTheClass().getComponentType();
        if (size == pt.length && (varg.isAssignableFrom(arguments[paramMinus1]) || testComponentAssignable(clazz, arguments[paramMinus1]))) {
            return true;
        }
        for (int j = paramMinus1; j < size; ++j) {
            if (!MetaClassHelper.isAssignableFrom(clazz, arguments[j])) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isValidMethod(final Object[] arguments) {
        if (arguments == null) {
            return true;
        }
        final int size = arguments.length;
        final CachedClass[] paramTypes = this.getParameterTypes();
        final int paramMinus1 = paramTypes.length - 1;
        if (size >= paramMinus1 && paramTypes.length > 0 && paramTypes[paramMinus1].isArray) {
            for (int i = 0; i < paramMinus1; ++i) {
                if (!paramTypes[i].isAssignableFrom(this.getArgClass(arguments[i]))) {
                    return false;
                }
            }
            final CachedClass varg = paramTypes[paramMinus1];
            final Class clazz = varg.getTheClass().getComponentType();
            if (size == paramTypes.length && (varg.isAssignableFrom(this.getArgClass(arguments[paramMinus1])) || testComponentAssignable(clazz, this.getArgClass(arguments[paramMinus1])))) {
                return true;
            }
            for (int j = paramMinus1; j < size; ++j) {
                if (!MetaClassHelper.isAssignableFrom(clazz, this.getArgClass(arguments[j]))) {
                    return false;
                }
            }
            return true;
        }
        else {
            if (paramTypes.length == size) {
                for (int i = 0; i < size; ++i) {
                    if (!paramTypes[i].isAssignableFrom(this.getArgClass(arguments[i]))) {
                        return false;
                    }
                }
                return true;
            }
            return paramTypes.length == 1 && size == 0 && !paramTypes[0].isPrimitive;
        }
    }
    
    private Class getArgClass(final Object arg) {
        Class cls;
        if (arg == null) {
            cls = null;
        }
        else if (arg instanceof Wrapper) {
            cls = ((Wrapper)arg).getType();
        }
        else {
            cls = arg.getClass();
        }
        return cls;
    }
}
