// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.beans.IntrospectionException;
import java.util.Hashtable;
import java.lang.reflect.Method;
import java.beans.PropertyDescriptor;

public class MappedPropertyDescriptor extends PropertyDescriptor
{
    private Class mappedPropertyType;
    private Method mappedReadMethod;
    private Method mappedWriteMethod;
    private static final Class[] stringClassArray;
    private static Hashtable declaredMethodCache;
    
    public MappedPropertyDescriptor(final String propertyName, final Class beanClass) throws IntrospectionException {
        super(propertyName, null, null);
        if (propertyName == null || propertyName.length() == 0) {
            throw new IntrospectionException("bad property name: " + propertyName + " on class: " + beanClass.getClass().getName());
        }
        this.setName(propertyName);
        final String base = capitalizePropertyName(propertyName);
        try {
            this.mappedReadMethod = findMethod(beanClass, "get" + base, 1, MappedPropertyDescriptor.stringClassArray);
            final Class[] params = { String.class, this.mappedReadMethod.getReturnType() };
            this.mappedWriteMethod = findMethod(beanClass, "set" + base, 2, params);
        }
        catch (IntrospectionException e) {}
        if (this.mappedReadMethod == null) {
            this.mappedWriteMethod = findMethod(beanClass, "set" + base, 2);
        }
        if (this.mappedReadMethod == null && this.mappedWriteMethod == null) {
            throw new IntrospectionException("Property '" + propertyName + "' not found on " + beanClass.getName());
        }
        this.findMappedPropertyType();
    }
    
    public MappedPropertyDescriptor(final String propertyName, final Class beanClass, final String mappedGetterName, final String mappedSetterName) throws IntrospectionException {
        super(propertyName, null, null);
        if (propertyName == null || propertyName.length() == 0) {
            throw new IntrospectionException("bad property name: " + propertyName);
        }
        this.setName(propertyName);
        this.mappedReadMethod = findMethod(beanClass, mappedGetterName, 1, MappedPropertyDescriptor.stringClassArray);
        if (this.mappedReadMethod != null) {
            final Class[] params = { String.class, this.mappedReadMethod.getReturnType() };
            this.mappedWriteMethod = findMethod(beanClass, mappedSetterName, 2, params);
        }
        else {
            this.mappedWriteMethod = findMethod(beanClass, mappedSetterName, 2);
        }
        this.findMappedPropertyType();
    }
    
    public MappedPropertyDescriptor(final String propertyName, final Method mappedGetter, final Method mappedSetter) throws IntrospectionException {
        super(propertyName, mappedGetter, mappedSetter);
        if (propertyName == null || propertyName.length() == 0) {
            throw new IntrospectionException("bad property name: " + propertyName);
        }
        this.setName(propertyName);
        this.mappedReadMethod = mappedGetter;
        this.mappedWriteMethod = mappedSetter;
        this.findMappedPropertyType();
    }
    
    public Class getMappedPropertyType() {
        return this.mappedPropertyType;
    }
    
    public Method getMappedReadMethod() {
        return this.mappedReadMethod;
    }
    
    public void setMappedReadMethod(final Method mappedGetter) throws IntrospectionException {
        this.mappedReadMethod = mappedGetter;
        this.findMappedPropertyType();
    }
    
    public Method getMappedWriteMethod() {
        return this.mappedWriteMethod;
    }
    
    public void setMappedWriteMethod(final Method mappedSetter) throws IntrospectionException {
        this.mappedWriteMethod = mappedSetter;
        this.findMappedPropertyType();
    }
    
    private void findMappedPropertyType() throws IntrospectionException {
        try {
            this.mappedPropertyType = null;
            if (this.mappedReadMethod != null) {
                if (this.mappedReadMethod.getParameterTypes().length != 1) {
                    throw new IntrospectionException("bad mapped read method arg count");
                }
                this.mappedPropertyType = this.mappedReadMethod.getReturnType();
                if (this.mappedPropertyType == Void.TYPE) {
                    throw new IntrospectionException("mapped read method " + this.mappedReadMethod.getName() + " returns void");
                }
            }
            if (this.mappedWriteMethod != null) {
                final Class[] params = this.mappedWriteMethod.getParameterTypes();
                if (params.length != 2) {
                    throw new IntrospectionException("bad mapped write method arg count");
                }
                if (this.mappedPropertyType != null && this.mappedPropertyType != params[1]) {
                    throw new IntrospectionException("type mismatch between mapped read and write methods");
                }
                this.mappedPropertyType = params[1];
            }
        }
        catch (IntrospectionException ex) {
            throw ex;
        }
    }
    
    private static String capitalizePropertyName(final String s) {
        if (s.length() == 0) {
            return s;
        }
        final char[] chars = s.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }
    
    private static synchronized Method[] getPublicDeclaredMethods(final Class clz) {
        final Class fclz = clz;
        Method[] result = MappedPropertyDescriptor.declaredMethodCache.get(fclz);
        if (result != null) {
            return result;
        }
        result = AccessController.doPrivileged((PrivilegedAction<Method[]>)new PrivilegedAction() {
            public Object run() {
                try {
                    return fclz.getDeclaredMethods();
                }
                catch (SecurityException ex) {
                    final Method[] methods = fclz.getMethods();
                    for (int i = 0, size = methods.length; i < size; ++i) {
                        final Method method = methods[i];
                        if (!fclz.equals(method.getDeclaringClass())) {
                            methods[i] = null;
                        }
                    }
                    return methods;
                }
            }
        });
        for (int i = 0; i < result.length; ++i) {
            final Method method = result[i];
            if (method != null) {
                final int mods = method.getModifiers();
                if (!Modifier.isPublic(mods)) {
                    result[i] = null;
                }
            }
        }
        MappedPropertyDescriptor.declaredMethodCache.put(clz, result);
        return result;
    }
    
    private static Method internalFindMethod(final Class start, final String methodName, final int argCount) {
        for (Class cl = start; cl != null; cl = cl.getSuperclass()) {
            final Method[] methods = getPublicDeclaredMethods(cl);
            for (int i = 0; i < methods.length; ++i) {
                final Method method = methods[i];
                if (method != null) {
                    final int mods = method.getModifiers();
                    if (!Modifier.isStatic(mods)) {
                        if (method.getName().equals(methodName) && method.getParameterTypes().length == argCount) {
                            return method;
                        }
                    }
                }
            }
        }
        final Class[] ifcs = start.getInterfaces();
        for (int i = 0; i < ifcs.length; ++i) {
            final Method m = internalFindMethod(ifcs[i], methodName, argCount);
            if (m != null) {
                return m;
            }
        }
        return null;
    }
    
    private static Method internalFindMethod(final Class start, final String methodName, final int argCount, final Class[] args) {
        for (Class cl = start; cl != null; cl = cl.getSuperclass()) {
            final Method[] methods = getPublicDeclaredMethods(cl);
            for (int i = 0; i < methods.length; ++i) {
                final Method method = methods[i];
                if (method != null) {
                    final int mods = method.getModifiers();
                    if (!Modifier.isStatic(mods)) {
                        final Class[] params = method.getParameterTypes();
                        if (method.getName().equals(methodName) && params.length == argCount) {
                            boolean different = false;
                            if (argCount > 0) {
                                for (int j = 0; j < argCount; ++j) {
                                    if (params[j] != args[j]) {
                                        different = true;
                                    }
                                }
                                if (different) {
                                    continue;
                                }
                            }
                            return method;
                        }
                    }
                }
            }
        }
        final Class[] ifcs = start.getInterfaces();
        for (int i = 0; i < ifcs.length; ++i) {
            final Method m = internalFindMethod(ifcs[i], methodName, argCount);
            if (m != null) {
                return m;
            }
        }
        return null;
    }
    
    static Method findMethod(final Class cls, final String methodName, final int argCount) throws IntrospectionException {
        if (methodName == null) {
            return null;
        }
        final Method m = internalFindMethod(cls, methodName, argCount);
        if (m != null) {
            return m;
        }
        throw new IntrospectionException("No method \"" + methodName + "\" with " + argCount + " arg(s)");
    }
    
    static Method findMethod(final Class cls, final String methodName, final int argCount, final Class[] args) throws IntrospectionException {
        if (methodName == null) {
            return null;
        }
        final Method m = internalFindMethod(cls, methodName, argCount, args);
        if (m != null) {
            return m;
        }
        throw new IntrospectionException("No method \"" + methodName + "\" with " + argCount + " arg(s) of matching types.");
    }
    
    static boolean isSubclass(final Class a, final Class b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        for (Class x = a; x != null; x = x.getSuperclass()) {
            if (x == b) {
                return true;
            }
            if (b.isInterface()) {
                final Class[] interfaces = x.getInterfaces();
                for (int i = 0; i < interfaces.length; ++i) {
                    if (isSubclass(interfaces[i], b)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean throwsException(final Method method, final Class exception) {
        final Class[] exs = method.getExceptionTypes();
        for (int i = 0; i < exs.length; ++i) {
            if (exs[i] == exception) {
                return true;
            }
        }
        return false;
    }
    
    static {
        stringClassArray = new Class[] { String.class };
        MappedPropertyDescriptor.declaredMethodCache = new Hashtable();
    }
}
