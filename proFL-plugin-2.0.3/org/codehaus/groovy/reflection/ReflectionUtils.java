// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;
import java.lang.reflect.Method;
import java.util.Set;

public class ReflectionUtils
{
    private static final Set<String> IGNORED_PACKAGES;
    private static final Method MAGIC_METHOD;
    
    public static boolean isCallingClassReflectionAvailable() {
        return ReflectionUtils.MAGIC_METHOD != null;
    }
    
    public static Class getCallingClass() {
        return getCallingClass(1);
    }
    
    public static Class getCallingClass(final int matchLevel) {
        return getCallingClass(matchLevel, Collections.EMPTY_SET);
    }
    
    public static Class getCallingClass(int matchLevel, final Collection<String> extraIgnoredPackages) {
        if (ReflectionUtils.MAGIC_METHOD == null) {
            return null;
        }
        int depth = 0;
        try {
            Class c;
            Class sc;
            do {
                c = (Class)ReflectionUtils.MAGIC_METHOD.invoke(null, depth++);
                if (c != null) {
                    sc = c.getSuperclass();
                }
                else {
                    sc = null;
                }
            } while (classShouldBeIgnored(c, extraIgnoredPackages) || superClassShouldBeIgnored(sc) || (c != null && matchLevel-- > 0));
            return c;
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    private static boolean superClassShouldBeIgnored(final Class sc) {
        return sc != null && sc.getPackage() != null && "org.codehaus.groovy.runtime.callsite".equals(sc.getPackage().getName());
    }
    
    private static boolean classShouldBeIgnored(final Class c, final Collection<String> extraIgnoredPackages) {
        return c != null && (c.isSynthetic() || (c.getPackage() != null && (ReflectionUtils.IGNORED_PACKAGES.contains(c.getPackage().getName()) || extraIgnoredPackages.contains(c.getPackage().getName()))));
    }
    
    static {
        (IGNORED_PACKAGES = new HashSet<String>()).add("groovy.lang");
        ReflectionUtils.IGNORED_PACKAGES.add("org.codehaus.groovy.reflection");
        ReflectionUtils.IGNORED_PACKAGES.add("org.codehaus.groovy.runtime.callsite");
        ReflectionUtils.IGNORED_PACKAGES.add("org.codehaus.groovy.runtime.metaclass");
        ReflectionUtils.IGNORED_PACKAGES.add("org.codehaus.groovy.runtime");
        ReflectionUtils.IGNORED_PACKAGES.add("sun.reflect");
        Method meth;
        try {
            final Class srr = Class.forName("sun.reflect.Reflection");
            meth = srr.getMethod("getCallerClass", Integer.TYPE);
        }
        catch (Throwable t) {
            meth = null;
        }
        MAGIC_METHOD = meth;
    }
}
