// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.logging.Level;
import java.math.BigDecimal;
import java.math.BigInteger;
import groovy.lang.Closure;
import groovy.lang.GroovyRuntimeException;
import java.util.Set;
import java.util.Collection;
import java.util.Arrays;
import java.util.HashSet;
import org.codehaus.groovy.runtime.wrappers.Wrapper;
import java.util.Iterator;
import groovy.lang.MetaMethod;
import org.codehaus.groovy.util.FastArray;
import org.codehaus.groovy.reflection.ParameterTypes;
import groovy.lang.GString;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.reflection.CachedClass;
import java.lang.reflect.Array;
import java.util.List;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class MetaClassHelper
{
    public static final Object[] EMPTY_ARRAY;
    public static final Class[] EMPTY_TYPE_ARRAY;
    public static final Object[] ARRAY_WITH_NULL;
    protected static final Logger LOG;
    private static final int MAX_ARG_LEN = 12;
    private static final int OBJECT_SHIFT = 23;
    private static final int INTERFACE_SHIFT = 0;
    private static final int PRIMITIVE_SHIFT = 21;
    private static final int VARGS_SHIFT = 44;
    public static final Class[] EMPTY_CLASS_ARRAY;
    private static final Class[] PRIMITIVES;
    private static final int[][] PRIMITIVE_DISTANCE_TABLE;
    
    public static boolean accessibleToConstructor(final Class at, final Constructor constructor) {
        boolean accessible = false;
        final int modifiers = constructor.getModifiers();
        if (Modifier.isPublic(modifiers)) {
            accessible = true;
        }
        else if (Modifier.isPrivate(modifiers)) {
            accessible = at.getName().equals(constructor.getName());
        }
        else if (Modifier.isProtected(modifiers)) {
            final Boolean isAccessible = checkCompatiblePackages(at, constructor);
            if (isAccessible != null) {
                accessible = isAccessible;
            }
            else {
                boolean flag = false;
                for (Class clazz = at; !flag && clazz != null; clazz = clazz.getSuperclass()) {
                    if (clazz.equals(constructor.getDeclaringClass())) {
                        flag = true;
                        break;
                    }
                    if (clazz.equals(Object.class)) {
                        break;
                    }
                }
                accessible = flag;
            }
        }
        else {
            final Boolean isAccessible = checkCompatiblePackages(at, constructor);
            if (isAccessible != null) {
                accessible = isAccessible;
            }
        }
        return accessible;
    }
    
    private static Boolean checkCompatiblePackages(final Class at, final Constructor constructor) {
        if (at.getPackage() == null && constructor.getDeclaringClass().getPackage() == null) {
            return Boolean.TRUE;
        }
        if (at.getPackage() == null && constructor.getDeclaringClass().getPackage() != null) {
            return Boolean.FALSE;
        }
        if (at.getPackage() != null && constructor.getDeclaringClass().getPackage() == null) {
            return Boolean.FALSE;
        }
        if (at.getPackage().equals(constructor.getDeclaringClass().getPackage())) {
            return Boolean.TRUE;
        }
        return null;
    }
    
    public static Object[] asWrapperArray(final Object parameters, final Class componentType) {
        Object[] ret = null;
        if (componentType == Boolean.TYPE) {
            final boolean[] array = (boolean[])parameters;
            ret = new Object[array.length];
            for (int i = 0; i < array.length; ++i) {
                ret[i] = array[i];
            }
        }
        else if (componentType == Character.TYPE) {
            final char[] array2 = (char[])parameters;
            ret = new Object[array2.length];
            for (int i = 0; i < array2.length; ++i) {
                ret[i] = array2[i];
            }
        }
        else if (componentType == Byte.TYPE) {
            final byte[] array3 = (byte[])parameters;
            ret = new Object[array3.length];
            for (int i = 0; i < array3.length; ++i) {
                ret[i] = array3[i];
            }
        }
        else if (componentType == Integer.TYPE) {
            final int[] array4 = (int[])parameters;
            ret = new Object[array4.length];
            for (int i = 0; i < array4.length; ++i) {
                ret[i] = array4[i];
            }
        }
        else if (componentType == Short.TYPE) {
            final short[] array5 = (short[])parameters;
            ret = new Object[array5.length];
            for (int i = 0; i < array5.length; ++i) {
                ret[i] = array5[i];
            }
        }
        else if (componentType == Long.TYPE) {
            final long[] array6 = (long[])parameters;
            ret = new Object[array6.length];
            for (int i = 0; i < array6.length; ++i) {
                ret[i] = array6[i];
            }
        }
        else if (componentType == Double.TYPE) {
            final double[] array7 = (double[])parameters;
            ret = new Object[array7.length];
            for (int i = 0; i < array7.length; ++i) {
                ret[i] = array7[i];
            }
        }
        else if (componentType == Float.TYPE) {
            final float[] array8 = (float[])parameters;
            ret = new Object[array8.length];
            for (int i = 0; i < array8.length; ++i) {
                ret[i] = array8[i];
            }
        }
        return ret;
    }
    
    public static Object asPrimitiveArray(final List list, final Class parameterType) {
        final Class arrayType = parameterType.getComponentType();
        final Object objArray = Array.newInstance(arrayType, list.size());
        for (int i = 0; i < list.size(); ++i) {
            final Object obj = list.get(i);
            if (arrayType.isPrimitive()) {
                if (obj instanceof Integer) {
                    Array.setInt(objArray, i, (int)obj);
                }
                else if (obj instanceof Double) {
                    Array.setDouble(objArray, i, (double)obj);
                }
                else if (obj instanceof Boolean) {
                    Array.setBoolean(objArray, i, (boolean)obj);
                }
                else if (obj instanceof Long) {
                    Array.setLong(objArray, i, (long)obj);
                }
                else if (obj instanceof Float) {
                    Array.setFloat(objArray, i, (float)obj);
                }
                else if (obj instanceof Character) {
                    Array.setChar(objArray, i, (char)obj);
                }
                else if (obj instanceof Byte) {
                    Array.setByte(objArray, i, (byte)obj);
                }
                else if (obj instanceof Short) {
                    Array.setShort(objArray, i, (short)obj);
                }
            }
            else {
                Array.set(objArray, i, obj);
            }
        }
        return objArray;
    }
    
    private static int getPrimitiveIndex(final Class c) {
        for (byte i = 0; i < MetaClassHelper.PRIMITIVES.length; ++i) {
            if (MetaClassHelper.PRIMITIVES[i] == c) {
                return i;
            }
        }
        return -1;
    }
    
    private static int getPrimitiveDistance(final Class from, final Class to) {
        final int fromIndex = getPrimitiveIndex(from);
        final int toIndex = getPrimitiveIndex(to);
        if (fromIndex == -1 || toIndex == -1) {
            return -1;
        }
        return MetaClassHelper.PRIMITIVE_DISTANCE_TABLE[toIndex][fromIndex];
    }
    
    private static int getMaximumInterfaceDistance(final Class c, final Class interfaceClass) {
        if (c == null) {
            return -1;
        }
        if (c == interfaceClass) {
            return 0;
        }
        final Class[] interfaces = c.getInterfaces();
        int max = -1;
        for (final Class anInterface : interfaces) {
            int sub = getMaximumInterfaceDistance(anInterface, interfaceClass);
            if (sub != -1) {
                ++sub;
            }
            max = Math.max(max, sub);
        }
        final int superClassMax = getMaximumInterfaceDistance(c.getSuperclass(), interfaceClass);
        return Math.max(max, superClassMax);
    }
    
    private static long calculateParameterDistance(final Class argument, final CachedClass parameter) {
        if (parameter.getTheClass() == argument) {
            return 0L;
        }
        if (parameter.isInterface()) {
            return getMaximumInterfaceDistance(argument, parameter.getTheClass()) << 0;
        }
        long objectDistance = 0L;
        if (argument != null) {
            final long pd = getPrimitiveDistance(parameter.getTheClass(), argument);
            if (pd != -1L) {
                return pd << 21;
            }
            objectDistance += MetaClassHelper.PRIMITIVES.length + 1;
            for (Class clazz = ReflectionCache.autoboxType(argument); clazz != null; clazz = clazz.getSuperclass(), objectDistance += 3L) {
                if (clazz == parameter.getTheClass()) {
                    break;
                }
                if (clazz == GString.class && parameter.getTheClass() == String.class) {
                    objectDistance += 2L;
                    break;
                }
            }
        }
        else {
            Class clazz2 = parameter.getTheClass();
            if (clazz2.isPrimitive()) {
                objectDistance += 2L;
            }
            else {
                while (clazz2 != Object.class) {
                    clazz2 = clazz2.getSuperclass();
                    objectDistance += 2L;
                }
            }
        }
        return objectDistance << 23;
    }
    
    public static long calculateParameterDistance(final Class[] arguments, final ParameterTypes pt) {
        final CachedClass[] parameters = pt.getParameterTypes();
        if (parameters.length == 0) {
            return 0L;
        }
        long ret = 0L;
        final int noVargsLength = parameters.length - 1;
        for (int i = 0; i < noVargsLength; ++i) {
            ret += calculateParameterDistance(arguments[i], parameters[i]);
        }
        if (arguments.length == parameters.length) {
            CachedClass baseType = parameters[noVargsLength];
            if (!parameters[noVargsLength].isAssignableFrom(arguments[noVargsLength])) {
                baseType = ReflectionCache.getCachedClass(baseType.getTheClass().getComponentType());
                ret += 35184372088832L;
            }
            ret += calculateParameterDistance(arguments[noVargsLength], baseType);
        }
        else if (arguments.length > parameters.length) {
            ret += 2L + arguments.length - parameters.length << 44;
            final CachedClass vargsType = ReflectionCache.getCachedClass(parameters[noVargsLength].getTheClass().getComponentType());
            for (int j = noVargsLength; j < arguments.length; ++j) {
                ret += calculateParameterDistance(arguments[j], vargsType);
            }
        }
        else {
            ret += 17592186044416L;
        }
        return ret;
    }
    
    public static String capitalize(final String property) {
        final String rest = property.substring(1);
        if (Character.isLowerCase(property.charAt(0)) && rest.length() > 0 && Character.isUpperCase(rest.charAt(0))) {
            return property;
        }
        return property.substring(0, 1).toUpperCase() + rest;
    }
    
    public static Object chooseEmptyMethodParams(final FastArray methods) {
        Object vargsMethod = null;
        final int len = methods.size();
        final Object[] data = methods.getArray();
        for (int i = 0; i != len; ++i) {
            final Object method = data[i];
            final ParameterTypes pt = (ParameterTypes)method;
            final CachedClass[] paramTypes = pt.getParameterTypes();
            final int paramLength = paramTypes.length;
            if (paramLength == 0) {
                return method;
            }
            if (paramLength == 1 && pt.isVargsMethod(MetaClassHelper.EMPTY_ARRAY)) {
                vargsMethod = method;
            }
        }
        return vargsMethod;
    }
    
    public static Object chooseMostGeneralMethodWith1NullParam(final FastArray methods) {
        CachedClass closestClass = null;
        CachedClass closestVargsClass = null;
        Object answer = null;
        int closestDist = -1;
        for (int len = methods.size(), i = 0; i != len; ++i) {
            final Object[] data = methods.getArray();
            final Object method = data[i];
            final ParameterTypes pt = (ParameterTypes)method;
            final CachedClass[] paramTypes = pt.getParameterTypes();
            final int paramLength = paramTypes.length;
            if (paramLength != 0) {
                if (paramLength <= 2) {
                    final CachedClass theType = paramTypes[0];
                    if (!theType.isPrimitive) {
                        if (paramLength == 2) {
                            if (pt.isVargsMethod(MetaClassHelper.ARRAY_WITH_NULL)) {
                                if (closestClass == null) {
                                    closestVargsClass = paramTypes[1];
                                    closestClass = theType;
                                    answer = method;
                                }
                                else if (closestClass.getTheClass() == theType.getTheClass()) {
                                    if (closestVargsClass != null) {
                                        final CachedClass newVargsClass = paramTypes[1];
                                        if (isAssignableFrom(newVargsClass.getTheClass(), closestVargsClass.getTheClass())) {
                                            closestVargsClass = newVargsClass;
                                            answer = method;
                                        }
                                    }
                                }
                                else if (isAssignableFrom(theType.getTheClass(), closestClass.getTheClass())) {
                                    closestVargsClass = paramTypes[1];
                                    closestClass = theType;
                                    answer = method;
                                }
                            }
                        }
                        else if (closestClass == null || isAssignableFrom(theType.getTheClass(), closestClass.getTheClass())) {
                            closestVargsClass = null;
                            closestClass = theType;
                            answer = method;
                            closestDist = -1;
                        }
                        else {
                            if (closestDist == -1) {
                                closestDist = closestClass.getSuperClassDistance();
                            }
                            final int newDist = theType.getSuperClassDistance();
                            if (newDist < closestDist) {
                                closestDist = newDist;
                                closestVargsClass = null;
                                closestClass = theType;
                                answer = method;
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
    
    private static int calculateSimplifiedClassDistanceToObject(Class clazz) {
        int objectDistance;
        for (objectDistance = 0; clazz != null; clazz = clazz.getSuperclass(), ++objectDistance) {}
        return objectDistance;
    }
    
    public static boolean containsMatchingMethod(final List list, final MetaMethod method) {
        for (final Object aList : list) {
            final MetaMethod aMethod = (MetaMethod)aList;
            final CachedClass[] params1 = aMethod.getParameterTypes();
            final CachedClass[] params2 = method.getParameterTypes();
            if (params1.length == params2.length) {
                boolean matches = true;
                for (int i = 0; i < params1.length; ++i) {
                    if (params1[i] != params2[i]) {
                        matches = false;
                        break;
                    }
                }
                if (matches) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }
    
    public static Class[] convertToTypeArray(final Object[] args) {
        if (args == null) {
            return null;
        }
        final int s = args.length;
        final Class[] ans = new Class[s];
        for (int i = 0; i < s; ++i) {
            final Object o = args[i];
            if (o == null) {
                ans[i] = null;
            }
            else if (o instanceof Wrapper) {
                ans[i] = ((Wrapper)o).getType();
            }
            else {
                ans[i] = o.getClass();
            }
        }
        return ans;
    }
    
    public static Object makeCommonArray(final Object[] arguments, final int offset, final Class fallback) {
        Class baseClass = null;
        for (int i = offset; i < arguments.length; ++i) {
            if (arguments[i] != null) {
                final Class argClass = arguments[i].getClass();
                if (baseClass == null) {
                    baseClass = argClass;
                }
                else {
                    while (baseClass != Object.class) {
                        if (baseClass.isAssignableFrom(argClass)) {
                            break;
                        }
                        baseClass = baseClass.getSuperclass();
                    }
                }
            }
        }
        if (baseClass == null) {
            baseClass = fallback;
        }
        if (baseClass == Object.class && fallback.isInterface()) {
            int tmpCount = 0;
            for (int j = offset; j < arguments.length; ++j) {
                if (arguments[j] != null) {
                    final Set<Class> intfs = new HashSet<Class>();
                    Class tmpClass;
                    for (Class argClass2 = tmpClass = arguments[j].getClass(); tmpClass != Object.class; tmpClass = tmpClass.getSuperclass()) {
                        intfs.addAll(Arrays.asList(tmpClass.getInterfaces()));
                    }
                    if (intfs.contains(fallback)) {
                        ++tmpCount;
                    }
                }
            }
            if (tmpCount == arguments.length - offset) {
                baseClass = fallback;
            }
        }
        final Object result = makeArray(null, baseClass, arguments.length - offset);
        System.arraycopy(arguments, offset, result, 0, arguments.length - offset);
        return result;
    }
    
    public static Object makeArray(final Object obj, final Class secondary, final int length) {
        Class baseClass = secondary;
        if (obj != null) {
            baseClass = obj.getClass();
        }
        return Array.newInstance(baseClass, length);
    }
    
    public static GroovyRuntimeException createExceptionText(final String init, final MetaMethod method, final Object object, final Object[] args, final Throwable reason, final boolean setReason) {
        return new GroovyRuntimeException(init + method + " on: " + object + " with arguments: " + InvokerHelper.toString(args) + " reason: " + reason, setReason ? reason : null);
    }
    
    protected static String getClassName(final Object object) {
        if (object == null) {
            return null;
        }
        return (object instanceof Class) ? ((Class)object).getName() : object.getClass().getName();
    }
    
    public static Closure getMethodPointer(final Object object, final String methodName) {
        return new MethodClosure(object, methodName);
    }
    
    public static boolean isAssignableFrom(Class classToTransformTo, Class classToTransformFrom) {
        if (classToTransformTo == classToTransformFrom) {
            return true;
        }
        if (classToTransformFrom == null) {
            return true;
        }
        if (classToTransformTo == Object.class) {
            return true;
        }
        classToTransformTo = ReflectionCache.autoboxType(classToTransformTo);
        classToTransformFrom = ReflectionCache.autoboxType(classToTransformFrom);
        if (classToTransformTo == classToTransformFrom) {
            return true;
        }
        if (classToTransformTo == Integer.class) {
            if (classToTransformFrom == Integer.class || classToTransformFrom == Short.class || classToTransformFrom == Byte.class || classToTransformFrom == BigInteger.class) {
                return true;
            }
        }
        else if (classToTransformTo == Double.class) {
            if (classToTransformFrom == Double.class || classToTransformFrom == Integer.class || classToTransformFrom == Long.class || classToTransformFrom == Short.class || classToTransformFrom == Byte.class || classToTransformFrom == Float.class || classToTransformFrom == BigDecimal.class || classToTransformFrom == BigInteger.class) {
                return true;
            }
        }
        else if (classToTransformTo == BigDecimal.class) {
            if (classToTransformFrom == Double.class || classToTransformFrom == Integer.class || classToTransformFrom == Long.class || classToTransformFrom == Short.class || classToTransformFrom == Byte.class || classToTransformFrom == Float.class || classToTransformFrom == BigDecimal.class || classToTransformFrom == BigInteger.class) {
                return true;
            }
        }
        else if (classToTransformTo == BigInteger.class) {
            if (classToTransformFrom == Integer.class || classToTransformFrom == Long.class || classToTransformFrom == Short.class || classToTransformFrom == Byte.class || classToTransformFrom == BigInteger.class) {
                return true;
            }
        }
        else if (classToTransformTo == Long.class) {
            if (classToTransformFrom == Long.class || classToTransformFrom == Integer.class || classToTransformFrom == Short.class || classToTransformFrom == Byte.class) {
                return true;
            }
        }
        else if (classToTransformTo == Float.class) {
            if (classToTransformFrom == Float.class || classToTransformFrom == Integer.class || classToTransformFrom == Long.class || classToTransformFrom == Short.class || classToTransformFrom == Byte.class) {
                return true;
            }
        }
        else if (classToTransformTo == Short.class) {
            if (classToTransformFrom == Short.class || classToTransformFrom == Byte.class) {
                return true;
            }
        }
        else if (classToTransformTo == String.class && (classToTransformFrom == String.class || GString.class.isAssignableFrom(classToTransformFrom))) {
            return true;
        }
        return ReflectionCache.isAssignableFrom(classToTransformTo, classToTransformFrom);
    }
    
    public static boolean isGenericSetMethod(final MetaMethod method) {
        return method.getName().equals("set") && method.getParameterTypes().length == 2;
    }
    
    protected static boolean isSuperclass(Class claszz, final Class superclass) {
        while (claszz != null) {
            if (claszz == superclass) {
                return true;
            }
            claszz = claszz.getSuperclass();
        }
        return false;
    }
    
    public static boolean parametersAreCompatible(final Class[] arguments, final Class[] parameters) {
        if (arguments.length != parameters.length) {
            return false;
        }
        for (int i = 0; i < arguments.length; ++i) {
            if (!isAssignableFrom(parameters[i], arguments[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static void logMethodCall(final Object object, final String methodName, final Object[] arguments) {
        final String className = getClassName(object);
        final String logname = "methodCalls." + className + "." + methodName;
        final Logger objLog = Logger.getLogger(logname);
        if (!objLog.isLoggable(Level.FINER)) {
            return;
        }
        final StringBuffer msg = new StringBuffer(methodName);
        msg.append("(");
        if (arguments != null) {
            int i = 0;
            while (i < arguments.length) {
                msg.append(normalizedValue(arguments[i]));
                if (++i < arguments.length) {
                    msg.append(",");
                }
            }
        }
        msg.append(")");
        objLog.logp(Level.FINER, className, msg.toString(), "called from MetaClass.invokeMethod");
    }
    
    protected static String normalizedValue(final Object argument) {
        String value;
        try {
            value = argument.toString();
            if (value.length() > 12) {
                value = value.substring(0, 10) + "..";
            }
            if (argument instanceof String) {
                value = "'" + value + "'";
            }
        }
        catch (Exception e) {
            value = shortName(argument);
        }
        return value;
    }
    
    protected static String shortName(final Object object) {
        if (object == null || object.getClass() == null) {
            return "unknownClass";
        }
        final String name = getClassName(object);
        if (name == null) {
            return "unknownClassName";
        }
        final int lastDotPos = name.lastIndexOf(46);
        if (lastDotPos < 0 || lastDotPos >= name.length() - 1) {
            return name;
        }
        return name.substring(lastDotPos + 1);
    }
    
    public static Class[] wrap(final Class[] classes) {
        final Class[] wrappedArguments = new Class[classes.length];
        for (int i = 0; i < wrappedArguments.length; ++i) {
            Class c = classes[i];
            if (c != null) {
                if (c.isPrimitive()) {
                    if (c == Integer.TYPE) {
                        c = Integer.class;
                    }
                    else if (c == Byte.TYPE) {
                        c = Byte.class;
                    }
                    else if (c == Long.TYPE) {
                        c = Long.class;
                    }
                    else if (c == Double.TYPE) {
                        c = Double.class;
                    }
                    else if (c == Float.TYPE) {
                        c = Float.class;
                    }
                }
                else if (isSuperclass(c, GString.class)) {
                    c = String.class;
                }
                wrappedArguments[i] = c;
            }
        }
        return wrappedArguments;
    }
    
    public static boolean sameClasses(final Class[] params, final Object[] arguments, final boolean weakNullCheck) {
        if (params.length != arguments.length) {
            return false;
        }
        for (int i = params.length - 1; i >= 0; --i) {
            final Object arg = arguments[i];
            if (arg == null) {
                if (!weakNullCheck) {
                    return false;
                }
            }
            else if (params[i] != arg.getClass() && (!(arg instanceof Wrapper) || params[i] != ((Wrapper)arg).getType())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean sameClasses(final Class[] params, final Object[] arguments) {
        if (params.length != arguments.length) {
            return false;
        }
        for (int i = params.length - 1; i >= 0; --i) {
            final Object arg = arguments[i];
            if (arg == null) {
                if (params[i] != null) {
                    return false;
                }
            }
            else if (params[i] != arg.getClass() && (!(arg instanceof Wrapper) || params[i] != ((Wrapper)arg).getType())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean sameClasses(final Class[] params) {
        return params.length == 0;
    }
    
    public static boolean sameClasses(final Class[] params, final Object arg1) {
        return params.length == 1 && arg1 != null && (params[0] == arg1.getClass() || (arg1 instanceof Wrapper && params[0] == ((Wrapper)arg1).getType()));
    }
    
    public static boolean sameClasses(final Class[] params, final Object arg1, final Object arg2) {
        return params.length == 2 && arg1 != null && (params[0] == arg1.getClass() || (arg1 instanceof Wrapper && params[0] == ((Wrapper)arg1).getType())) && arg2 != null && (params[1] == arg2.getClass() || (arg2 instanceof Wrapper && params[1] == ((Wrapper)arg2).getType()));
    }
    
    public static boolean sameClasses(final Class[] params, final Object arg1, final Object arg2, final Object arg3) {
        return params.length == 3 && arg1 != null && (params[0] == arg1.getClass() || (arg1 instanceof Wrapper && params[0] == ((Wrapper)arg1).getType())) && arg2 != null && (params[1] == arg2.getClass() || (arg2 instanceof Wrapper && params[1] == ((Wrapper)arg2).getType())) && arg3 != null && (params[2] == arg3.getClass() || (arg3 instanceof Wrapper && params[2] == ((Wrapper)arg3).getType()));
    }
    
    public static boolean sameClasses(final Class[] params, final Object arg1, final Object arg2, final Object arg3, final Object arg4) {
        return params.length == 4 && arg1 != null && (params[0] == arg1.getClass() || (arg1 instanceof Wrapper && params[0] == ((Wrapper)arg1).getType())) && arg2 != null && (params[1] == arg2.getClass() || (arg2 instanceof Wrapper && params[1] == ((Wrapper)arg2).getType())) && arg3 != null && (params[2] == arg3.getClass() || (arg3 instanceof Wrapper && params[2] == ((Wrapper)arg3).getType())) && arg4 != null && (params[3] == arg4.getClass() || (arg4 instanceof Wrapper && params[3] == ((Wrapper)arg4).getType()));
    }
    
    public static boolean sameClass(final Class[] params, final Object arg) {
        return arg != null && (params[0] == arg.getClass() || (arg instanceof Wrapper && params[0] == ((Wrapper)arg).getType()));
    }
    
    public static Class[] castArgumentsToClassArray(final Object[] argTypes) {
        if (argTypes == null) {
            return MetaClassHelper.EMPTY_CLASS_ARRAY;
        }
        final Class[] classes = new Class[argTypes.length];
        for (int i = 0; i < argTypes.length; ++i) {
            final Object argType = argTypes[i];
            if (argType instanceof Class) {
                classes[i] = (Class)argType;
            }
            else if (argType == null) {
                classes[i] = null;
            }
            else {
                classes[i] = argType.getClass();
            }
        }
        return classes;
    }
    
    public static void unwrap(final Object[] arguments) {
        for (int i = 0; i != arguments.length; ++i) {
            if (arguments[i] instanceof Wrapper) {
                arguments[i] = ((Wrapper)arguments[i]).unwrap();
            }
        }
    }
    
    static {
        EMPTY_ARRAY = new Object[0];
        EMPTY_TYPE_ARRAY = new Class[0];
        ARRAY_WITH_NULL = new Object[] { null };
        LOG = Logger.getLogger(MetaClassHelper.class.getName());
        EMPTY_CLASS_ARRAY = new Class[0];
        PRIMITIVES = new Class[] { Byte.TYPE, Byte.class, Short.TYPE, Short.class, Integer.TYPE, Integer.class, Long.TYPE, Long.class, BigInteger.class, Float.TYPE, Float.class, Double.TYPE, Double.class, BigDecimal.class, Number.class, Object.class };
        PRIMITIVE_DISTANCE_TABLE = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 1, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 14, 15, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 }, { 14, 15, 1, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 }, { 14, 15, 12, 13, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 }, { 14, 15, 12, 13, 1, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 }, { 14, 15, 12, 13, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 14, 15, 12, 13, 10, 11, 1, 0, 2, 3, 4, 5, 6, 7, 8, 9 }, { 9, 10, 7, 8, 5, 6, 3, 4, 0, 14, 15, 12, 13, 11, 1, 2 }, { 14, 15, 12, 13, 10, 11, 8, 9, 7, 0, 1, 2, 3, 4, 5, 6 }, { 14, 15, 12, 13, 10, 11, 8, 9, 7, 1, 0, 2, 3, 4, 5, 6 }, { 14, 15, 12, 13, 10, 11, 8, 9, 7, 5, 6, 0, 1, 2, 3, 4 }, { 14, 15, 12, 13, 10, 11, 8, 9, 7, 5, 6, 1, 0, 2, 3, 4 }, { 14, 15, 12, 13, 10, 11, 8, 9, 7, 5, 6, 3, 4, 0, 1, 2 }, { 14, 15, 12, 13, 10, 11, 8, 9, 7, 5, 6, 3, 4, 2, 0, 1 }, { 14, 15, 12, 13, 10, 11, 8, 9, 7, 5, 6, 3, 4, 2, 1, 0 } };
    }
}
