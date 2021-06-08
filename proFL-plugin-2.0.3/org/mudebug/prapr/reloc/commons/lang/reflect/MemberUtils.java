// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.lang.reflect;

import org.mudebug.prapr.reloc.commons.lang.ArrayUtils;
import org.mudebug.prapr.reloc.commons.lang.SystemUtils;
import org.mudebug.prapr.reloc.commons.lang.ClassUtils;
import java.lang.reflect.Modifier;
import java.lang.reflect.Member;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

abstract class MemberUtils
{
    private static final int ACCESS_TEST = 7;
    private static final Method IS_SYNTHETIC;
    private static final Class[] ORDERED_PRIMITIVE_TYPES;
    
    static void setAccessibleWorkaround(final AccessibleObject o) {
        if (o == null || o.isAccessible()) {
            return;
        }
        final Member m = (Member)o;
        if (Modifier.isPublic(m.getModifiers()) && isPackageAccess(m.getDeclaringClass().getModifiers())) {
            try {
                o.setAccessible(true);
            }
            catch (SecurityException ex) {}
        }
    }
    
    static boolean isPackageAccess(final int modifiers) {
        return (modifiers & 0x7) == 0x0;
    }
    
    static boolean isAccessible(final Member m) {
        return m != null && Modifier.isPublic(m.getModifiers()) && !isSynthetic(m);
    }
    
    static boolean isSynthetic(final Member m) {
        if (MemberUtils.IS_SYNTHETIC != null) {
            try {
                return (boolean)MemberUtils.IS_SYNTHETIC.invoke(m, (Object[])null);
            }
            catch (Exception ex) {}
        }
        return false;
    }
    
    static int compareParameterTypes(final Class[] left, final Class[] right, final Class[] actual) {
        final float leftCost = getTotalTransformationCost(actual, left);
        final float rightCost = getTotalTransformationCost(actual, right);
        return (leftCost < rightCost) ? -1 : ((rightCost < leftCost) ? 1 : 0);
    }
    
    private static float getTotalTransformationCost(final Class[] srcArgs, final Class[] destArgs) {
        float totalCost = 0.0f;
        for (int i = 0; i < srcArgs.length; ++i) {
            final Class srcClass = srcArgs[i];
            final Class destClass = destArgs[i];
            totalCost += getObjectTransformationCost(srcClass, destClass);
        }
        return totalCost;
    }
    
    private static float getObjectTransformationCost(Class srcClass, final Class destClass) {
        if (destClass.isPrimitive()) {
            return getPrimitivePromotionCost(srcClass, destClass);
        }
        float cost = 0.0f;
        while (srcClass != null && !destClass.equals(srcClass)) {
            if (destClass.isInterface() && ClassUtils.isAssignable(srcClass, destClass)) {
                cost += 0.25f;
                break;
            }
            ++cost;
            srcClass = srcClass.getSuperclass();
        }
        if (srcClass == null) {
            cost += 1.5f;
        }
        return cost;
    }
    
    private static float getPrimitivePromotionCost(final Class srcClass, final Class destClass) {
        float cost = 0.0f;
        Class cls = srcClass;
        if (!cls.isPrimitive()) {
            cost += 0.1f;
            cls = ClassUtils.wrapperToPrimitive(cls);
        }
        for (int i = 0; cls != destClass && i < MemberUtils.ORDERED_PRIMITIVE_TYPES.length; ++i) {
            if (cls == MemberUtils.ORDERED_PRIMITIVE_TYPES[i]) {
                cost += 0.1f;
                if (i < MemberUtils.ORDERED_PRIMITIVE_TYPES.length - 1) {
                    cls = MemberUtils.ORDERED_PRIMITIVE_TYPES[i + 1];
                }
            }
        }
        return cost;
    }
    
    static {
        Method isSynthetic = null;
        if (SystemUtils.isJavaVersionAtLeast(1.5f)) {
            try {
                isSynthetic = Member.class.getMethod("isSynthetic", (Class[])ArrayUtils.EMPTY_CLASS_ARRAY);
            }
            catch (Exception ex) {}
        }
        IS_SYNTHETIC = isSynthetic;
        ORDERED_PRIMITIVE_TYPES = new Class[] { Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE };
    }
}
