// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.metaclass;

import java.lang.reflect.Method;

public class MethodHelper
{
    public static boolean isStatic(final Method method) {
        final int flags = 8;
        return (method.getModifiers() & flags) == flags;
    }
    
    public static boolean isPublic(final Method method) {
        final int flags = 1;
        return (method.getModifiers() & flags) == flags;
    }
}
