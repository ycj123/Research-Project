// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.util.Arrays;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.WeakHashMap;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class MethodUtils
{
    private static Log log;
    private static boolean loggedAccessibleWarning;
    private static final Class[] emptyClassArray;
    private static final Object[] emptyObjectArray;
    private static WeakHashMap cache;
    
    public static Object invokeMethod(final Object object, final String methodName, final Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final Object[] args = { arg };
        return invokeMethod(object, methodName, args);
    }
    
    public static Object invokeMethod(final Object object, final String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (args == null) {
            args = MethodUtils.emptyObjectArray;
        }
        final int arguments = args.length;
        final Class[] parameterTypes = new Class[arguments];
        for (int i = 0; i < arguments; ++i) {
            parameterTypes[i] = args[i].getClass();
        }
        return invokeMethod(object, methodName, args, parameterTypes);
    }
    
    public static Object invokeMethod(final Object object, final String methodName, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (parameterTypes == null) {
            parameterTypes = MethodUtils.emptyClassArray;
        }
        if (args == null) {
            args = MethodUtils.emptyObjectArray;
        }
        final Method method = getMatchingAccessibleMethod(object.getClass(), methodName, parameterTypes);
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName());
        }
        return method.invoke(object, args);
    }
    
    public static Object invokeExactMethod(final Object object, final String methodName, final Object arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final Object[] args = { arg };
        return invokeExactMethod(object, methodName, args);
    }
    
    public static Object invokeExactMethod(final Object object, final String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (args == null) {
            args = MethodUtils.emptyObjectArray;
        }
        final int arguments = args.length;
        final Class[] parameterTypes = new Class[arguments];
        for (int i = 0; i < arguments; ++i) {
            parameterTypes[i] = args[i].getClass();
        }
        return invokeExactMethod(object, methodName, args, parameterTypes);
    }
    
    public static Object invokeExactMethod(final Object object, final String methodName, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (args == null) {
            args = MethodUtils.emptyObjectArray;
        }
        if (parameterTypes == null) {
            parameterTypes = MethodUtils.emptyClassArray;
        }
        final Method method = getAccessibleMethod((Class)object.getClass(), methodName, parameterTypes);
        if (method == null) {
            throw new NoSuchMethodException("No such accessible method: " + methodName + "() on object: " + object.getClass().getName());
        }
        return method.invoke(object, args);
    }
    
    public static Method getAccessibleMethod(final Class clazz, final String methodName, final Class parameterType) {
        final Class[] parameterTypes = { parameterType };
        return getAccessibleMethod(clazz, methodName, parameterTypes);
    }
    
    public static Method getAccessibleMethod(final Class clazz, final String methodName, final Class[] parameterTypes) {
        try {
            final MethodDescriptor md = new MethodDescriptor(clazz, methodName, parameterTypes, true);
            Method method = MethodUtils.cache.get(md);
            if (method != null) {
                return method;
            }
            method = getAccessibleMethod(clazz.getMethod(methodName, (Class[])parameterTypes));
            MethodUtils.cache.put(md, method);
            return method;
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }
    
    public static Method getAccessibleMethod(Method method) {
        if (method == null) {
            return null;
        }
        if (!Modifier.isPublic(method.getModifiers())) {
            return null;
        }
        final Class clazz = method.getDeclaringClass();
        if (Modifier.isPublic(clazz.getModifiers())) {
            return method;
        }
        method = getAccessibleMethodFromInterfaceNest(clazz, method.getName(), method.getParameterTypes());
        return method;
    }
    
    private static Method getAccessibleMethodFromInterfaceNest(Class clazz, final String methodName, final Class[] parameterTypes) {
        Method method = null;
        while (clazz != null) {
            final Class[] interfaces = clazz.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i) {
                if (Modifier.isPublic(interfaces[i].getModifiers())) {
                    try {
                        method = interfaces[i].getDeclaredMethod(methodName, (Class[])parameterTypes);
                    }
                    catch (NoSuchMethodException e) {}
                    if (method != null) {
                        break;
                    }
                    method = getAccessibleMethodFromInterfaceNest(interfaces[i], methodName, parameterTypes);
                    if (method != null) {
                        break;
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        if (method != null) {
            return method;
        }
        return null;
    }
    
    public static Method getMatchingAccessibleMethod(final Class clazz, final String methodName, final Class[] parameterTypes) {
        if (MethodUtils.log.isTraceEnabled()) {
            MethodUtils.log.trace("Matching name=" + methodName + " on " + clazz);
        }
        final MethodDescriptor md = new MethodDescriptor(clazz, methodName, parameterTypes, false);
        try {
            Method method = MethodUtils.cache.get(md);
            if (method != null) {
                return method;
            }
            method = clazz.getMethod(methodName, (Class[])parameterTypes);
            if (MethodUtils.log.isTraceEnabled()) {
                MethodUtils.log.trace("Found straight match: " + method);
                MethodUtils.log.trace("isPublic:" + Modifier.isPublic(method.getModifiers()));
            }
            try {
                method.setAccessible(true);
            }
            catch (SecurityException se) {
                if (!MethodUtils.loggedAccessibleWarning) {
                    boolean vunerableJVM = false;
                    try {
                        final String specVersion = System.getProperty("java.specification.version");
                        if (specVersion.charAt(0) == '1' && (specVersion.charAt(0) == '0' || specVersion.charAt(0) == '1' || specVersion.charAt(0) == '2' || specVersion.charAt(0) == '3')) {
                            vunerableJVM = true;
                        }
                    }
                    catch (SecurityException e) {
                        vunerableJVM = true;
                    }
                    if (vunerableJVM) {
                        MethodUtils.log.warn("Current Security Manager restricts use of workarounds for reflection bugs  in pre-1.4 JVMs.");
                    }
                    MethodUtils.loggedAccessibleWarning = true;
                }
                MethodUtils.log.debug("Cannot setAccessible on method. Therefore cannot use jvm access bug workaround.", se);
            }
            MethodUtils.cache.put(md, method);
            return method;
        }
        catch (NoSuchMethodException e2) {
            final int paramSize = parameterTypes.length;
            final Method[] methods = clazz.getMethods();
            for (int i = 0, size = methods.length; i < size; ++i) {
                if (methods[i].getName().equals(methodName)) {
                    if (MethodUtils.log.isTraceEnabled()) {
                        MethodUtils.log.trace("Found matching name:");
                        MethodUtils.log.trace(methods[i]);
                    }
                    final Class[] methodsParams = methods[i].getParameterTypes();
                    final int methodParamSize = methodsParams.length;
                    if (methodParamSize == paramSize) {
                        boolean match = true;
                        for (int n = 0; n < methodParamSize; ++n) {
                            if (MethodUtils.log.isTraceEnabled()) {
                                MethodUtils.log.trace("Param=" + parameterTypes[n].getName());
                                MethodUtils.log.trace("Method=" + methodsParams[n].getName());
                            }
                            if (!isAssignmentCompatible(methodsParams[n], parameterTypes[n])) {
                                if (MethodUtils.log.isTraceEnabled()) {
                                    MethodUtils.log.trace(methodsParams[n] + " is not assignable from " + parameterTypes[n]);
                                }
                                match = false;
                                break;
                            }
                        }
                        if (match) {
                            final Method method2 = getAccessibleMethod(methods[i]);
                            if (method2 != null) {
                                if (MethodUtils.log.isTraceEnabled()) {
                                    MethodUtils.log.trace(method2 + " accessible version of " + methods[i]);
                                }
                                try {
                                    method2.setAccessible(true);
                                }
                                catch (SecurityException se2) {
                                    if (!MethodUtils.loggedAccessibleWarning) {
                                        MethodUtils.log.warn("Cannot use JVM pre-1.4 access bug workaround due to restrictive security manager.");
                                        MethodUtils.loggedAccessibleWarning = true;
                                    }
                                    MethodUtils.log.debug("Cannot setAccessible on method. Therefore cannot use jvm access bug workaround.", se2);
                                }
                                MethodUtils.cache.put(md, method2);
                                return method2;
                            }
                            MethodUtils.log.trace("Couldn't find accessible method.");
                        }
                    }
                }
            }
            MethodUtils.log.trace("No match found.");
            return null;
        }
    }
    
    public static final boolean isAssignmentCompatible(final Class parameterType, final Class parameterization) {
        if (parameterType.isAssignableFrom(parameterization)) {
            return true;
        }
        if (parameterType.isPrimitive()) {
            final Class parameterWrapperClazz = getPrimitiveWrapper(parameterType);
            if (parameterWrapperClazz != null) {
                return parameterWrapperClazz.equals(parameterization);
            }
        }
        return false;
    }
    
    public static Class getPrimitiveWrapper(final Class primitiveType) {
        if (Boolean.TYPE.equals(primitiveType)) {
            return Boolean.class;
        }
        if (Float.TYPE.equals(primitiveType)) {
            return Float.class;
        }
        if (Long.TYPE.equals(primitiveType)) {
            return Long.class;
        }
        if (Integer.TYPE.equals(primitiveType)) {
            return Integer.class;
        }
        if (Short.TYPE.equals(primitiveType)) {
            return Short.class;
        }
        if (Byte.TYPE.equals(primitiveType)) {
            return Byte.class;
        }
        if (Double.TYPE.equals(primitiveType)) {
            return Double.class;
        }
        if (Character.TYPE.equals(primitiveType)) {
            return Character.class;
        }
        return null;
    }
    
    public static Class getPrimitiveType(final Class wrapperType) {
        if (Boolean.class.equals(wrapperType)) {
            return Boolean.TYPE;
        }
        if (Float.class.equals(wrapperType)) {
            return Float.TYPE;
        }
        if (Long.class.equals(wrapperType)) {
            return Long.TYPE;
        }
        if (Integer.class.equals(wrapperType)) {
            return Integer.TYPE;
        }
        if (Short.class.equals(wrapperType)) {
            return Short.TYPE;
        }
        if (Byte.class.equals(wrapperType)) {
            return Byte.TYPE;
        }
        if (Double.class.equals(wrapperType)) {
            return Double.TYPE;
        }
        if (Character.class.equals(wrapperType)) {
            return Character.TYPE;
        }
        if (MethodUtils.log.isDebugEnabled()) {
            MethodUtils.log.debug("Not a known primitive wrapper class: " + wrapperType);
        }
        return null;
    }
    
    public static Class toNonPrimitiveClass(final Class clazz) {
        if (!clazz.isPrimitive()) {
            return clazz;
        }
        final Class primitiveClazz = getPrimitiveWrapper(clazz);
        if (primitiveClazz != null) {
            return primitiveClazz;
        }
        return clazz;
    }
    
    static {
        MethodUtils.log = LogFactory.getLog(MethodUtils.class);
        MethodUtils.loggedAccessibleWarning = false;
        emptyClassArray = new Class[0];
        emptyObjectArray = new Object[0];
        MethodUtils.cache = new WeakHashMap();
    }
    
    private static class MethodDescriptor
    {
        private Class cls;
        private String methodName;
        private Class[] paramTypes;
        private boolean exact;
        private int hashCode;
        
        public MethodDescriptor(final Class cls, final String methodName, Class[] paramTypes, final boolean exact) {
            if (cls == null) {
                throw new IllegalArgumentException("Class cannot be null");
            }
            if (methodName == null) {
                throw new IllegalArgumentException("Method Name cannot be null");
            }
            if (paramTypes == null) {
                paramTypes = MethodUtils.emptyClassArray;
            }
            this.cls = cls;
            this.methodName = methodName;
            this.paramTypes = paramTypes;
            this.exact = exact;
            this.hashCode = methodName.length();
        }
        
        public boolean equals(final Object obj) {
            if (!(obj instanceof MethodDescriptor)) {
                return false;
            }
            final MethodDescriptor md = (MethodDescriptor)obj;
            return this.exact == md.exact && this.methodName.equals(md.methodName) && this.cls.equals(md.cls) && Arrays.equals(this.paramTypes, md.paramTypes);
        }
        
        public int hashCode() {
            return this.hashCode;
        }
    }
}
