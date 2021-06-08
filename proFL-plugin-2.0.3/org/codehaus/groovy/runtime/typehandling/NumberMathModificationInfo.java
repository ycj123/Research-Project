// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.typehandling;

import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.metaclass.NewInstanceMetaMethod;
import groovy.lang.MetaMethod;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class NumberMathModificationInfo
{
    public static final NumberMathModificationInfo instance;
    private final HashSet<String> names;
    public boolean byte_plus;
    public boolean short_plus;
    public boolean int_plus;
    public boolean long_plus;
    public boolean float_plus;
    public boolean double_plus;
    public boolean byte_minus;
    public boolean short_minus;
    public boolean int_minus;
    public boolean long_minus;
    public boolean float_minus;
    public boolean double_minus;
    public boolean byte_multiply;
    public boolean short_multiply;
    public boolean int_multiply;
    public boolean long_multiply;
    public boolean float_multiply;
    public boolean double_multiply;
    public boolean byte_div;
    public boolean short_div;
    public boolean int_div;
    public boolean long_div;
    public boolean float_div;
    public boolean double_div;
    public boolean byte_or;
    public boolean short_or;
    public boolean int_or;
    public boolean long_or;
    public boolean float_or;
    public boolean double_or;
    public boolean byte_and;
    public boolean short_and;
    public boolean int_and;
    public boolean long_and;
    public boolean float_and;
    public boolean double_and;
    public boolean byte_xor;
    public boolean short_xor;
    public boolean int_xor;
    public boolean long_xor;
    public boolean float_xor;
    public boolean double_xor;
    public boolean byte_intdiv;
    public boolean short_intdiv;
    public boolean int_intdiv;
    public boolean long_intdiv;
    public boolean float_intdiv;
    public boolean double_intdiv;
    public boolean byte_mod;
    public boolean short_mod;
    public boolean int_mod;
    public boolean long_mod;
    public boolean float_mod;
    public boolean double_mod;
    public boolean byte_leftShift;
    public boolean short_leftShift;
    public boolean int_leftShift;
    public boolean long_leftShift;
    public boolean float_leftShift;
    public boolean double_leftShift;
    public boolean byte_rightShift;
    public boolean short_rightShift;
    public boolean int_rightShift;
    public boolean long_rightShift;
    public boolean float_rightShift;
    public boolean double_rightShift;
    public boolean byte_rightShiftUnsigned;
    public boolean short_rightShiftUnsigned;
    public boolean int_rightShiftUnsigned;
    public boolean long_rightShiftUnsigned;
    public boolean float_rightShiftUnsigned;
    public boolean double_rightShiftUnsigned;
    
    private NumberMathModificationInfo() {
        Collections.addAll(this.names = new HashSet<String>(), new String[] { "plus", "minus", "multiply", "div", "compareTo", "or", "and", "xor", "intdiv", "mod", "leftShift", "rightShift", "rightShiftUnsigned" });
    }
    
    public void checkIfStdMethod(final MetaMethod method) {
        if (method.getClass() != NewInstanceMetaMethod.class) {
            final String name = method.getName();
            if (method.getParameterTypes().length != 1) {
                return;
            }
            if (!method.getParameterTypes()[0].isNumber && method.getParameterTypes()[0].getTheClass() != Object.class) {
                return;
            }
            if (!this.names.contains(name)) {
                return;
            }
            this.checkNumberOps(name, method.getDeclaringClass().getTheClass());
        }
    }
    
    private void checkNumberOps(final String name, final Class klazz) {
        if ("plus".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_plus = true;
            }
            if (klazz == Short.class) {
                this.short_plus = true;
            }
            if (klazz == Integer.class) {
                this.int_plus = true;
            }
            if (klazz == Long.class) {
                this.long_plus = true;
            }
            if (klazz == Float.class) {
                this.float_plus = true;
            }
            if (klazz == Double.class) {
                this.double_plus = true;
            }
            if (klazz == Object.class) {
                this.byte_plus = true;
                this.short_plus = true;
                this.int_plus = true;
                this.long_plus = true;
                this.float_plus = true;
                this.double_plus = true;
            }
        }
        if ("minus".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_minus = true;
            }
            if (klazz == Short.class) {
                this.short_minus = true;
            }
            if (klazz == Integer.class) {
                this.int_minus = true;
            }
            if (klazz == Long.class) {
                this.long_minus = true;
            }
            if (klazz == Float.class) {
                this.float_minus = true;
            }
            if (klazz == Double.class) {
                this.double_minus = true;
            }
            if (klazz == Object.class) {
                this.byte_minus = true;
                this.short_minus = true;
                this.int_minus = true;
                this.long_minus = true;
                this.float_minus = true;
                this.double_minus = true;
            }
        }
        if ("multiply".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_multiply = true;
            }
            if (klazz == Short.class) {
                this.short_multiply = true;
            }
            if (klazz == Integer.class) {
                this.int_multiply = true;
            }
            if (klazz == Long.class) {
                this.long_multiply = true;
            }
            if (klazz == Float.class) {
                this.float_multiply = true;
            }
            if (klazz == Double.class) {
                this.double_multiply = true;
            }
            if (klazz == Object.class) {
                this.byte_multiply = true;
                this.short_multiply = true;
                this.int_multiply = true;
                this.long_multiply = true;
                this.float_multiply = true;
                this.double_multiply = true;
            }
        }
        if ("div".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_div = true;
            }
            if (klazz == Short.class) {
                this.short_div = true;
            }
            if (klazz == Integer.class) {
                this.int_div = true;
            }
            if (klazz == Long.class) {
                this.long_div = true;
            }
            if (klazz == Float.class) {
                this.float_div = true;
            }
            if (klazz == Double.class) {
                this.double_div = true;
            }
            if (klazz == Object.class) {
                this.byte_div = true;
                this.short_div = true;
                this.int_div = true;
                this.long_div = true;
                this.float_div = true;
                this.double_div = true;
            }
        }
        if ("or".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_or = true;
            }
            if (klazz == Short.class) {
                this.short_or = true;
            }
            if (klazz == Integer.class) {
                this.int_or = true;
            }
            if (klazz == Long.class) {
                this.long_or = true;
            }
            if (klazz == Float.class) {
                this.float_or = true;
            }
            if (klazz == Double.class) {
                this.double_or = true;
            }
            if (klazz == Object.class) {
                this.byte_or = true;
                this.short_or = true;
                this.int_or = true;
                this.long_or = true;
                this.float_or = true;
                this.double_or = true;
            }
        }
        if ("and".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_and = true;
            }
            if (klazz == Short.class) {
                this.short_and = true;
            }
            if (klazz == Integer.class) {
                this.int_and = true;
            }
            if (klazz == Long.class) {
                this.long_and = true;
            }
            if (klazz == Float.class) {
                this.float_and = true;
            }
            if (klazz == Double.class) {
                this.double_and = true;
            }
            if (klazz == Object.class) {
                this.byte_and = true;
                this.short_and = true;
                this.int_and = true;
                this.long_and = true;
                this.float_and = true;
                this.double_and = true;
            }
        }
        if ("xor".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_xor = true;
            }
            if (klazz == Short.class) {
                this.short_xor = true;
            }
            if (klazz == Integer.class) {
                this.int_xor = true;
            }
            if (klazz == Long.class) {
                this.long_xor = true;
            }
            if (klazz == Float.class) {
                this.float_xor = true;
            }
            if (klazz == Double.class) {
                this.double_xor = true;
            }
            if (klazz == Object.class) {
                this.byte_xor = true;
                this.short_xor = true;
                this.int_xor = true;
                this.long_xor = true;
                this.float_xor = true;
                this.double_xor = true;
            }
        }
        if ("intdiv".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_intdiv = true;
            }
            if (klazz == Short.class) {
                this.short_intdiv = true;
            }
            if (klazz == Integer.class) {
                this.int_intdiv = true;
            }
            if (klazz == Long.class) {
                this.long_intdiv = true;
            }
            if (klazz == Float.class) {
                this.float_intdiv = true;
            }
            if (klazz == Double.class) {
                this.double_intdiv = true;
            }
            if (klazz == Object.class) {
                this.byte_intdiv = true;
                this.short_intdiv = true;
                this.int_intdiv = true;
                this.long_intdiv = true;
                this.float_intdiv = true;
                this.double_intdiv = true;
            }
        }
        if ("mod".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_mod = true;
            }
            if (klazz == Short.class) {
                this.short_mod = true;
            }
            if (klazz == Integer.class) {
                this.int_mod = true;
            }
            if (klazz == Long.class) {
                this.long_mod = true;
            }
            if (klazz == Float.class) {
                this.float_mod = true;
            }
            if (klazz == Double.class) {
                this.double_mod = true;
            }
            if (klazz == Object.class) {
                this.byte_mod = true;
                this.short_mod = true;
                this.int_mod = true;
                this.long_mod = true;
                this.float_mod = true;
                this.double_mod = true;
            }
        }
        if ("leftShift".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_leftShift = true;
            }
            if (klazz == Short.class) {
                this.short_leftShift = true;
            }
            if (klazz == Integer.class) {
                this.int_leftShift = true;
            }
            if (klazz == Long.class) {
                this.long_leftShift = true;
            }
            if (klazz == Float.class) {
                this.float_leftShift = true;
            }
            if (klazz == Double.class) {
                this.double_leftShift = true;
            }
            if (klazz == Object.class) {
                this.byte_leftShift = true;
                this.short_leftShift = true;
                this.int_leftShift = true;
                this.long_leftShift = true;
                this.float_leftShift = true;
                this.double_leftShift = true;
            }
        }
        if ("rightShift".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_rightShift = true;
            }
            if (klazz == Short.class) {
                this.short_rightShift = true;
            }
            if (klazz == Integer.class) {
                this.int_rightShift = true;
            }
            if (klazz == Long.class) {
                this.long_rightShift = true;
            }
            if (klazz == Float.class) {
                this.float_rightShift = true;
            }
            if (klazz == Double.class) {
                this.double_rightShift = true;
            }
            if (klazz == Object.class) {
                this.byte_rightShift = true;
                this.short_rightShift = true;
                this.int_rightShift = true;
                this.long_rightShift = true;
                this.float_rightShift = true;
                this.double_rightShift = true;
            }
        }
        if ("rightShiftUnsigned".equals(name)) {
            if (klazz == Byte.class) {
                this.byte_rightShiftUnsigned = true;
            }
            if (klazz == Short.class) {
                this.short_rightShiftUnsigned = true;
            }
            if (klazz == Integer.class) {
                this.int_rightShiftUnsigned = true;
            }
            if (klazz == Long.class) {
                this.long_rightShiftUnsigned = true;
            }
            if (klazz == Float.class) {
                this.float_rightShiftUnsigned = true;
            }
            if (klazz == Double.class) {
                this.double_rightShiftUnsigned = true;
            }
            if (klazz == Object.class) {
                this.byte_rightShiftUnsigned = true;
                this.short_rightShiftUnsigned = true;
                this.int_rightShiftUnsigned = true;
                this.long_rightShiftUnsigned = true;
                this.float_rightShiftUnsigned = true;
                this.double_rightShiftUnsigned = true;
            }
        }
    }
    
    public static int plus(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static int plusSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).intValue();
    }
    
    public static int plus(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static int plusSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).intValue();
    }
    
    public static int plus(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static int plusSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).intValue();
    }
    
    public static long plus(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static long plusSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).longValue();
    }
    
    public static double plus(final byte op1, final float op2) {
        if (NumberMathModificationInfo.instance.byte_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + (double)op2;
    }
    
    private static double plusSlow(final byte op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final byte op1, final double op2) {
        if (NumberMathModificationInfo.instance.byte_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final byte op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static int plus(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static int plusSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).intValue();
    }
    
    public static int plus(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static int plusSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).intValue();
    }
    
    public static int plus(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static int plusSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).intValue();
    }
    
    public static long plus(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static long plusSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).longValue();
    }
    
    public static double plus(final short op1, final float op2) {
        if (NumberMathModificationInfo.instance.short_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + (double)op2;
    }
    
    private static double plusSlow(final short op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final short op1, final double op2) {
        if (NumberMathModificationInfo.instance.short_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final short op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static int plus(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static int plusSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).intValue();
    }
    
    public static int plus(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static int plusSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).intValue();
    }
    
    public static int plus(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static int plusSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).intValue();
    }
    
    public static long plus(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static long plusSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).longValue();
    }
    
    public static double plus(final int op1, final float op2) {
        if (NumberMathModificationInfo.instance.int_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + (double)op2;
    }
    
    private static double plusSlow(final int op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final int op1, final double op2) {
        if (NumberMathModificationInfo.instance.int_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final int op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static long plus(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static long plusSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).longValue();
    }
    
    public static long plus(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static long plusSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).longValue();
    }
    
    public static long plus(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static long plusSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).longValue();
    }
    
    public static long plus(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static long plusSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).longValue();
    }
    
    public static double plus(final long op1, final float op2) {
        if (NumberMathModificationInfo.instance.long_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + (double)op2;
    }
    
    private static double plusSlow(final long op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final long op1, final double op2) {
        if (NumberMathModificationInfo.instance.long_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final long op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final float op1, final byte op2) {
        if (NumberMathModificationInfo.instance.float_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + (double)op2;
    }
    
    private static double plusSlow(final float op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final float op1, final short op2) {
        if (NumberMathModificationInfo.instance.float_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + (double)op2;
    }
    
    private static double plusSlow(final float op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final float op1, final int op2) {
        if (NumberMathModificationInfo.instance.float_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + (double)op2;
    }
    
    private static double plusSlow(final float op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final float op1, final long op2) {
        if (NumberMathModificationInfo.instance.float_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + (double)op2;
    }
    
    private static double plusSlow(final float op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final float op1, final float op2) {
        if (NumberMathModificationInfo.instance.float_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + (double)op2;
    }
    
    private static double plusSlow(final float op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final float op1, final double op2) {
        if (NumberMathModificationInfo.instance.float_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final float op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final double op1, final byte op2) {
        if (NumberMathModificationInfo.instance.double_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final double op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final double op1, final short op2) {
        if (NumberMathModificationInfo.instance.double_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final double op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final double op1, final int op2) {
        if (NumberMathModificationInfo.instance.double_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final double op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final double op1, final long op2) {
        if (NumberMathModificationInfo.instance.double_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final double op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final double op1, final float op2) {
        if (NumberMathModificationInfo.instance.double_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final double op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static double plus(final double op1, final double op2) {
        if (NumberMathModificationInfo.instance.double_plus) {
            return plusSlow(op1, op2);
        }
        return op1 + op2;
    }
    
    private static double plusSlow(final double op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "plus", op2)).doubleValue();
    }
    
    public static int minus(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static int minusSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).intValue();
    }
    
    public static int minus(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static int minusSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).intValue();
    }
    
    public static int minus(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static int minusSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).intValue();
    }
    
    public static long minus(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static long minusSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).longValue();
    }
    
    public static double minus(final byte op1, final float op2) {
        if (NumberMathModificationInfo.instance.byte_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - (double)op2;
    }
    
    private static double minusSlow(final byte op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final byte op1, final double op2) {
        if (NumberMathModificationInfo.instance.byte_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final byte op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static int minus(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static int minusSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).intValue();
    }
    
    public static int minus(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static int minusSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).intValue();
    }
    
    public static int minus(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static int minusSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).intValue();
    }
    
    public static long minus(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static long minusSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).longValue();
    }
    
    public static double minus(final short op1, final float op2) {
        if (NumberMathModificationInfo.instance.short_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - (double)op2;
    }
    
    private static double minusSlow(final short op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final short op1, final double op2) {
        if (NumberMathModificationInfo.instance.short_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final short op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static int minus(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static int minusSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).intValue();
    }
    
    public static int minus(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static int minusSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).intValue();
    }
    
    public static int minus(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static int minusSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).intValue();
    }
    
    public static long minus(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static long minusSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).longValue();
    }
    
    public static double minus(final int op1, final float op2) {
        if (NumberMathModificationInfo.instance.int_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - (double)op2;
    }
    
    private static double minusSlow(final int op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final int op1, final double op2) {
        if (NumberMathModificationInfo.instance.int_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final int op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static long minus(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static long minusSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).longValue();
    }
    
    public static long minus(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static long minusSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).longValue();
    }
    
    public static long minus(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static long minusSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).longValue();
    }
    
    public static long minus(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static long minusSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).longValue();
    }
    
    public static double minus(final long op1, final float op2) {
        if (NumberMathModificationInfo.instance.long_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - (double)op2;
    }
    
    private static double minusSlow(final long op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final long op1, final double op2) {
        if (NumberMathModificationInfo.instance.long_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final long op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final float op1, final byte op2) {
        if (NumberMathModificationInfo.instance.float_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - (double)op2;
    }
    
    private static double minusSlow(final float op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final float op1, final short op2) {
        if (NumberMathModificationInfo.instance.float_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - (double)op2;
    }
    
    private static double minusSlow(final float op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final float op1, final int op2) {
        if (NumberMathModificationInfo.instance.float_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - (double)op2;
    }
    
    private static double minusSlow(final float op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final float op1, final long op2) {
        if (NumberMathModificationInfo.instance.float_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - (double)op2;
    }
    
    private static double minusSlow(final float op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final float op1, final float op2) {
        if (NumberMathModificationInfo.instance.float_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - (double)op2;
    }
    
    private static double minusSlow(final float op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final float op1, final double op2) {
        if (NumberMathModificationInfo.instance.float_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final float op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final double op1, final byte op2) {
        if (NumberMathModificationInfo.instance.double_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final double op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final double op1, final short op2) {
        if (NumberMathModificationInfo.instance.double_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final double op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final double op1, final int op2) {
        if (NumberMathModificationInfo.instance.double_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final double op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final double op1, final long op2) {
        if (NumberMathModificationInfo.instance.double_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final double op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final double op1, final float op2) {
        if (NumberMathModificationInfo.instance.double_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final double op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static double minus(final double op1, final double op2) {
        if (NumberMathModificationInfo.instance.double_minus) {
            return minusSlow(op1, op2);
        }
        return op1 - op2;
    }
    
    private static double minusSlow(final double op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "minus", op2)).doubleValue();
    }
    
    public static int multiply(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static int multiplySlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).intValue();
    }
    
    public static int multiply(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static int multiplySlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).intValue();
    }
    
    public static int multiply(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static int multiplySlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).intValue();
    }
    
    public static long multiply(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static long multiplySlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).longValue();
    }
    
    public static double multiply(final byte op1, final float op2) {
        if (NumberMathModificationInfo.instance.byte_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * (double)op2;
    }
    
    private static double multiplySlow(final byte op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final byte op1, final double op2) {
        if (NumberMathModificationInfo.instance.byte_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final byte op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static int multiply(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static int multiplySlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).intValue();
    }
    
    public static int multiply(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static int multiplySlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).intValue();
    }
    
    public static int multiply(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static int multiplySlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).intValue();
    }
    
    public static long multiply(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static long multiplySlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).longValue();
    }
    
    public static double multiply(final short op1, final float op2) {
        if (NumberMathModificationInfo.instance.short_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * (double)op2;
    }
    
    private static double multiplySlow(final short op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final short op1, final double op2) {
        if (NumberMathModificationInfo.instance.short_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final short op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static int multiply(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static int multiplySlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).intValue();
    }
    
    public static int multiply(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static int multiplySlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).intValue();
    }
    
    public static int multiply(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static int multiplySlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).intValue();
    }
    
    public static long multiply(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static long multiplySlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).longValue();
    }
    
    public static double multiply(final int op1, final float op2) {
        if (NumberMathModificationInfo.instance.int_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * (double)op2;
    }
    
    private static double multiplySlow(final int op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final int op1, final double op2) {
        if (NumberMathModificationInfo.instance.int_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final int op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static long multiply(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static long multiplySlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).longValue();
    }
    
    public static long multiply(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static long multiplySlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).longValue();
    }
    
    public static long multiply(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static long multiplySlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).longValue();
    }
    
    public static long multiply(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static long multiplySlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).longValue();
    }
    
    public static double multiply(final long op1, final float op2) {
        if (NumberMathModificationInfo.instance.long_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * (double)op2;
    }
    
    private static double multiplySlow(final long op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final long op1, final double op2) {
        if (NumberMathModificationInfo.instance.long_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final long op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final float op1, final byte op2) {
        if (NumberMathModificationInfo.instance.float_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * (double)op2;
    }
    
    private static double multiplySlow(final float op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final float op1, final short op2) {
        if (NumberMathModificationInfo.instance.float_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * (double)op2;
    }
    
    private static double multiplySlow(final float op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final float op1, final int op2) {
        if (NumberMathModificationInfo.instance.float_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * (double)op2;
    }
    
    private static double multiplySlow(final float op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final float op1, final long op2) {
        if (NumberMathModificationInfo.instance.float_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * (double)op2;
    }
    
    private static double multiplySlow(final float op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final float op1, final float op2) {
        if (NumberMathModificationInfo.instance.float_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * (double)op2;
    }
    
    private static double multiplySlow(final float op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final float op1, final double op2) {
        if (NumberMathModificationInfo.instance.float_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final float op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final double op1, final byte op2) {
        if (NumberMathModificationInfo.instance.double_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final double op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final double op1, final short op2) {
        if (NumberMathModificationInfo.instance.double_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final double op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final double op1, final int op2) {
        if (NumberMathModificationInfo.instance.double_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final double op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final double op1, final long op2) {
        if (NumberMathModificationInfo.instance.double_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final double op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final double op1, final float op2) {
        if (NumberMathModificationInfo.instance.double_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final double op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static double multiply(final double op1, final double op2) {
        if (NumberMathModificationInfo.instance.double_multiply) {
            return multiplySlow(op1, op2);
        }
        return op1 * op2;
    }
    
    private static double multiplySlow(final double op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "multiply", op2)).doubleValue();
    }
    
    public static int div(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int divSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).intValue();
    }
    
    public static int div(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int divSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).intValue();
    }
    
    public static int div(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int divSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).intValue();
    }
    
    public static long div(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long divSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).longValue();
    }
    
    public static double div(final byte op1, final float op2) {
        if (NumberMathModificationInfo.instance.byte_div) {
            return divSlow(op1, op2);
        }
        return op1 / (double)op2;
    }
    
    private static double divSlow(final byte op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final byte op1, final double op2) {
        if (NumberMathModificationInfo.instance.byte_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final byte op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static int div(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int divSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).intValue();
    }
    
    public static int div(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int divSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).intValue();
    }
    
    public static int div(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int divSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).intValue();
    }
    
    public static long div(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long divSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).longValue();
    }
    
    public static double div(final short op1, final float op2) {
        if (NumberMathModificationInfo.instance.short_div) {
            return divSlow(op1, op2);
        }
        return op1 / (double)op2;
    }
    
    private static double divSlow(final short op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final short op1, final double op2) {
        if (NumberMathModificationInfo.instance.short_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final short op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static int div(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int divSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).intValue();
    }
    
    public static int div(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int divSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).intValue();
    }
    
    public static int div(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int divSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).intValue();
    }
    
    public static long div(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long divSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).longValue();
    }
    
    public static double div(final int op1, final float op2) {
        if (NumberMathModificationInfo.instance.int_div) {
            return divSlow(op1, op2);
        }
        return op1 / (double)op2;
    }
    
    private static double divSlow(final int op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final int op1, final double op2) {
        if (NumberMathModificationInfo.instance.int_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final int op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static long div(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long divSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).longValue();
    }
    
    public static long div(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long divSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).longValue();
    }
    
    public static long div(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long divSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).longValue();
    }
    
    public static long div(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long divSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).longValue();
    }
    
    public static double div(final long op1, final float op2) {
        if (NumberMathModificationInfo.instance.long_div) {
            return divSlow(op1, op2);
        }
        return op1 / (double)op2;
    }
    
    private static double divSlow(final long op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final long op1, final double op2) {
        if (NumberMathModificationInfo.instance.long_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final long op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final float op1, final byte op2) {
        if (NumberMathModificationInfo.instance.float_div) {
            return divSlow(op1, op2);
        }
        return op1 / (double)op2;
    }
    
    private static double divSlow(final float op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final float op1, final short op2) {
        if (NumberMathModificationInfo.instance.float_div) {
            return divSlow(op1, op2);
        }
        return op1 / (double)op2;
    }
    
    private static double divSlow(final float op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final float op1, final int op2) {
        if (NumberMathModificationInfo.instance.float_div) {
            return divSlow(op1, op2);
        }
        return op1 / (double)op2;
    }
    
    private static double divSlow(final float op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final float op1, final long op2) {
        if (NumberMathModificationInfo.instance.float_div) {
            return divSlow(op1, op2);
        }
        return op1 / (double)op2;
    }
    
    private static double divSlow(final float op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final float op1, final float op2) {
        if (NumberMathModificationInfo.instance.float_div) {
            return divSlow(op1, op2);
        }
        return op1 / (double)op2;
    }
    
    private static double divSlow(final float op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final float op1, final double op2) {
        if (NumberMathModificationInfo.instance.float_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final float op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final double op1, final byte op2) {
        if (NumberMathModificationInfo.instance.double_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final double op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final double op1, final short op2) {
        if (NumberMathModificationInfo.instance.double_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final double op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final double op1, final int op2) {
        if (NumberMathModificationInfo.instance.double_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final double op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final double op1, final long op2) {
        if (NumberMathModificationInfo.instance.double_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final double op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final double op1, final float op2) {
        if (NumberMathModificationInfo.instance.double_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final double op1, final float op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static double div(final double op1, final double op2) {
        if (NumberMathModificationInfo.instance.double_div) {
            return divSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static double divSlow(final double op1, final double op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "div", op2)).doubleValue();
    }
    
    public static int or(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_or) {
            return orSlow(op1, op2);
        }
        return op1 | op2;
    }
    
    private static int orSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).intValue();
    }
    
    public static int or(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_or) {
            return orSlow(op1, op2);
        }
        return op1 | op2;
    }
    
    private static int orSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).intValue();
    }
    
    public static int or(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_or) {
            return orSlow(op1, op2);
        }
        return op1 | op2;
    }
    
    private static int orSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).intValue();
    }
    
    public static long or(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_or) {
            return orSlow(op1, op2);
        }
        return (long)op1 | op2;
    }
    
    private static long orSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).longValue();
    }
    
    public static int or(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_or) {
            return orSlow(op1, op2);
        }
        return op1 | op2;
    }
    
    private static int orSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).intValue();
    }
    
    public static int or(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_or) {
            return orSlow(op1, op2);
        }
        return op1 | op2;
    }
    
    private static int orSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).intValue();
    }
    
    public static int or(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_or) {
            return orSlow(op1, op2);
        }
        return op1 | op2;
    }
    
    private static int orSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).intValue();
    }
    
    public static long or(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_or) {
            return orSlow(op1, op2);
        }
        return (long)op1 | op2;
    }
    
    private static long orSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).longValue();
    }
    
    public static int or(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_or) {
            return orSlow(op1, op2);
        }
        return op1 | op2;
    }
    
    private static int orSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).intValue();
    }
    
    public static int or(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_or) {
            return orSlow(op1, op2);
        }
        return op1 | op2;
    }
    
    private static int orSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).intValue();
    }
    
    public static int or(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_or) {
            return orSlow(op1, op2);
        }
        return op1 | op2;
    }
    
    private static int orSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).intValue();
    }
    
    public static long or(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_or) {
            return orSlow(op1, op2);
        }
        return (long)op1 | op2;
    }
    
    private static long orSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).longValue();
    }
    
    public static long or(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_or) {
            return orSlow(op1, op2);
        }
        return op1 | (long)op2;
    }
    
    private static long orSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).longValue();
    }
    
    public static long or(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_or) {
            return orSlow(op1, op2);
        }
        return op1 | (long)op2;
    }
    
    private static long orSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).longValue();
    }
    
    public static long or(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_or) {
            return orSlow(op1, op2);
        }
        return op1 | (long)op2;
    }
    
    private static long orSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).longValue();
    }
    
    public static long or(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_or) {
            return orSlow(op1, op2);
        }
        return op1 | op2;
    }
    
    private static long orSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "or", op2)).longValue();
    }
    
    public static int and(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_and) {
            return andSlow(op1, op2);
        }
        return op1 & op2;
    }
    
    private static int andSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).intValue();
    }
    
    public static int and(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_and) {
            return andSlow(op1, op2);
        }
        return op1 & op2;
    }
    
    private static int andSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).intValue();
    }
    
    public static int and(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_and) {
            return andSlow(op1, op2);
        }
        return op1 & op2;
    }
    
    private static int andSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).intValue();
    }
    
    public static long and(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_and) {
            return andSlow(op1, op2);
        }
        return (long)op1 & op2;
    }
    
    private static long andSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).longValue();
    }
    
    public static int and(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_and) {
            return andSlow(op1, op2);
        }
        return op1 & op2;
    }
    
    private static int andSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).intValue();
    }
    
    public static int and(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_and) {
            return andSlow(op1, op2);
        }
        return op1 & op2;
    }
    
    private static int andSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).intValue();
    }
    
    public static int and(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_and) {
            return andSlow(op1, op2);
        }
        return op1 & op2;
    }
    
    private static int andSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).intValue();
    }
    
    public static long and(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_and) {
            return andSlow(op1, op2);
        }
        return (long)op1 & op2;
    }
    
    private static long andSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).longValue();
    }
    
    public static int and(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_and) {
            return andSlow(op1, op2);
        }
        return op1 & op2;
    }
    
    private static int andSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).intValue();
    }
    
    public static int and(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_and) {
            return andSlow(op1, op2);
        }
        return op1 & op2;
    }
    
    private static int andSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).intValue();
    }
    
    public static int and(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_and) {
            return andSlow(op1, op2);
        }
        return op1 & op2;
    }
    
    private static int andSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).intValue();
    }
    
    public static long and(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_and) {
            return andSlow(op1, op2);
        }
        return (long)op1 & op2;
    }
    
    private static long andSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).longValue();
    }
    
    public static long and(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_and) {
            return andSlow(op1, op2);
        }
        return op1 & (long)op2;
    }
    
    private static long andSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).longValue();
    }
    
    public static long and(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_and) {
            return andSlow(op1, op2);
        }
        return op1 & (long)op2;
    }
    
    private static long andSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).longValue();
    }
    
    public static long and(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_and) {
            return andSlow(op1, op2);
        }
        return op1 & (long)op2;
    }
    
    private static long andSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).longValue();
    }
    
    public static long and(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_and) {
            return andSlow(op1, op2);
        }
        return op1 & op2;
    }
    
    private static long andSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "and", op2)).longValue();
    }
    
    public static int xor(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ op2;
    }
    
    private static int xorSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).intValue();
    }
    
    public static int xor(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ op2;
    }
    
    private static int xorSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).intValue();
    }
    
    public static int xor(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ op2;
    }
    
    private static int xorSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).intValue();
    }
    
    public static long xor(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_xor) {
            return xorSlow(op1, op2);
        }
        return (long)op1 ^ op2;
    }
    
    private static long xorSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).longValue();
    }
    
    public static int xor(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ op2;
    }
    
    private static int xorSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).intValue();
    }
    
    public static int xor(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ op2;
    }
    
    private static int xorSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).intValue();
    }
    
    public static int xor(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ op2;
    }
    
    private static int xorSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).intValue();
    }
    
    public static long xor(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_xor) {
            return xorSlow(op1, op2);
        }
        return (long)op1 ^ op2;
    }
    
    private static long xorSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).longValue();
    }
    
    public static int xor(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ op2;
    }
    
    private static int xorSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).intValue();
    }
    
    public static int xor(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ op2;
    }
    
    private static int xorSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).intValue();
    }
    
    public static int xor(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ op2;
    }
    
    private static int xorSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).intValue();
    }
    
    public static long xor(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_xor) {
            return xorSlow(op1, op2);
        }
        return (long)op1 ^ op2;
    }
    
    private static long xorSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).longValue();
    }
    
    public static long xor(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ (long)op2;
    }
    
    private static long xorSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).longValue();
    }
    
    public static long xor(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ (long)op2;
    }
    
    private static long xorSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).longValue();
    }
    
    public static long xor(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ (long)op2;
    }
    
    private static long xorSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).longValue();
    }
    
    public static long xor(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_xor) {
            return xorSlow(op1, op2);
        }
        return op1 ^ op2;
    }
    
    private static long xorSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "xor", op2)).longValue();
    }
    
    public static int intdiv(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int intdivSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).intValue();
    }
    
    public static int intdiv(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int intdivSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).intValue();
    }
    
    public static int intdiv(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int intdivSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).intValue();
    }
    
    public static long intdiv(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long intdivSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).longValue();
    }
    
    public static int intdiv(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int intdivSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).intValue();
    }
    
    public static int intdiv(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int intdivSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).intValue();
    }
    
    public static int intdiv(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int intdivSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).intValue();
    }
    
    public static long intdiv(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long intdivSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).longValue();
    }
    
    public static int intdiv(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int intdivSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).intValue();
    }
    
    public static int intdiv(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int intdivSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).intValue();
    }
    
    public static int intdiv(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static int intdivSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).intValue();
    }
    
    public static long intdiv(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long intdivSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).longValue();
    }
    
    public static long intdiv(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long intdivSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).longValue();
    }
    
    public static long intdiv(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long intdivSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).longValue();
    }
    
    public static long intdiv(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long intdivSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).longValue();
    }
    
    public static long intdiv(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_intdiv) {
            return intdivSlow(op1, op2);
        }
        return op1 / op2;
    }
    
    private static long intdivSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "intdiv", op2)).longValue();
    }
    
    public static int mod(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static int modSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).intValue();
    }
    
    public static int mod(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static int modSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).intValue();
    }
    
    public static int mod(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static int modSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).intValue();
    }
    
    public static long mod(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static long modSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).longValue();
    }
    
    public static int mod(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static int modSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).intValue();
    }
    
    public static int mod(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static int modSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).intValue();
    }
    
    public static int mod(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static int modSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).intValue();
    }
    
    public static long mod(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static long modSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).longValue();
    }
    
    public static int mod(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static int modSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).intValue();
    }
    
    public static int mod(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static int modSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).intValue();
    }
    
    public static int mod(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static int modSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).intValue();
    }
    
    public static long mod(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static long modSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).longValue();
    }
    
    public static long mod(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static long modSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).longValue();
    }
    
    public static long mod(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static long modSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).longValue();
    }
    
    public static long mod(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static long modSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).longValue();
    }
    
    public static long mod(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_mod) {
            return modSlow(op1, op2);
        }
        return op1 % op2;
    }
    
    private static long modSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "mod", op2)).longValue();
    }
    
    public static int leftShift(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << op2;
    }
    
    private static int leftShiftSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).intValue();
    }
    
    public static int leftShift(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << op2;
    }
    
    private static int leftShiftSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).intValue();
    }
    
    public static int leftShift(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << op2;
    }
    
    private static int leftShiftSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).intValue();
    }
    
    public static long leftShift(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return (long)op1 << (int)op2;
    }
    
    private static long leftShiftSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).longValue();
    }
    
    public static int leftShift(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << op2;
    }
    
    private static int leftShiftSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).intValue();
    }
    
    public static int leftShift(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << op2;
    }
    
    private static int leftShiftSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).intValue();
    }
    
    public static int leftShift(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << op2;
    }
    
    private static int leftShiftSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).intValue();
    }
    
    public static long leftShift(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return (long)op1 << (int)op2;
    }
    
    private static long leftShiftSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).longValue();
    }
    
    public static int leftShift(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << op2;
    }
    
    private static int leftShiftSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).intValue();
    }
    
    public static int leftShift(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << op2;
    }
    
    private static int leftShiftSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).intValue();
    }
    
    public static int leftShift(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << op2;
    }
    
    private static int leftShiftSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).intValue();
    }
    
    public static long leftShift(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return (long)op1 << (int)op2;
    }
    
    private static long leftShiftSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).longValue();
    }
    
    public static long leftShift(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << (int)op2;
    }
    
    private static long leftShiftSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).longValue();
    }
    
    public static long leftShift(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << (int)op2;
    }
    
    private static long leftShiftSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).longValue();
    }
    
    public static long leftShift(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << (int)op2;
    }
    
    private static long leftShiftSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).longValue();
    }
    
    public static long leftShift(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_leftShift) {
            return leftShiftSlow(op1, op2);
        }
        return op1 << (int)op2;
    }
    
    private static long leftShiftSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "leftShift", op2)).longValue();
    }
    
    public static int rightShift(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> op2;
    }
    
    private static int rightShiftSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).intValue();
    }
    
    public static int rightShift(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> op2;
    }
    
    private static int rightShiftSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).intValue();
    }
    
    public static int rightShift(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> op2;
    }
    
    private static int rightShiftSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).intValue();
    }
    
    public static long rightShift(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return (long)op1 >> (int)op2;
    }
    
    private static long rightShiftSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).longValue();
    }
    
    public static int rightShift(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> op2;
    }
    
    private static int rightShiftSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).intValue();
    }
    
    public static int rightShift(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> op2;
    }
    
    private static int rightShiftSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).intValue();
    }
    
    public static int rightShift(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> op2;
    }
    
    private static int rightShiftSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).intValue();
    }
    
    public static long rightShift(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return (long)op1 >> (int)op2;
    }
    
    private static long rightShiftSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).longValue();
    }
    
    public static int rightShift(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> op2;
    }
    
    private static int rightShiftSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).intValue();
    }
    
    public static int rightShift(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> op2;
    }
    
    private static int rightShiftSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).intValue();
    }
    
    public static int rightShift(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> op2;
    }
    
    private static int rightShiftSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).intValue();
    }
    
    public static long rightShift(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return (long)op1 >> (int)op2;
    }
    
    private static long rightShiftSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).longValue();
    }
    
    public static long rightShift(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> (int)op2;
    }
    
    private static long rightShiftSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).longValue();
    }
    
    public static long rightShift(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> (int)op2;
    }
    
    private static long rightShiftSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).longValue();
    }
    
    public static long rightShift(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> (int)op2;
    }
    
    private static long rightShiftSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).longValue();
    }
    
    public static long rightShift(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_rightShift) {
            return rightShiftSlow(op1, op2);
        }
        return op1 >> (int)op2;
    }
    
    private static long rightShiftSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShift", op2)).longValue();
    }
    
    public static int rightShiftUnsigned(final byte op1, final byte op2) {
        if (NumberMathModificationInfo.instance.byte_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> op2;
    }
    
    private static int rightShiftUnsignedSlow(final byte op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).intValue();
    }
    
    public static int rightShiftUnsigned(final byte op1, final short op2) {
        if (NumberMathModificationInfo.instance.byte_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> op2;
    }
    
    private static int rightShiftUnsignedSlow(final byte op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).intValue();
    }
    
    public static int rightShiftUnsigned(final byte op1, final int op2) {
        if (NumberMathModificationInfo.instance.byte_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> op2;
    }
    
    private static int rightShiftUnsignedSlow(final byte op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).intValue();
    }
    
    public static long rightShiftUnsigned(final byte op1, final long op2) {
        if (NumberMathModificationInfo.instance.byte_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return (long)op1 >>> (int)op2;
    }
    
    private static long rightShiftUnsignedSlow(final byte op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).longValue();
    }
    
    public static int rightShiftUnsigned(final short op1, final byte op2) {
        if (NumberMathModificationInfo.instance.short_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> op2;
    }
    
    private static int rightShiftUnsignedSlow(final short op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).intValue();
    }
    
    public static int rightShiftUnsigned(final short op1, final short op2) {
        if (NumberMathModificationInfo.instance.short_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> op2;
    }
    
    private static int rightShiftUnsignedSlow(final short op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).intValue();
    }
    
    public static int rightShiftUnsigned(final short op1, final int op2) {
        if (NumberMathModificationInfo.instance.short_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> op2;
    }
    
    private static int rightShiftUnsignedSlow(final short op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).intValue();
    }
    
    public static long rightShiftUnsigned(final short op1, final long op2) {
        if (NumberMathModificationInfo.instance.short_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return (long)op1 >>> (int)op2;
    }
    
    private static long rightShiftUnsignedSlow(final short op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).longValue();
    }
    
    public static int rightShiftUnsigned(final int op1, final byte op2) {
        if (NumberMathModificationInfo.instance.int_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> op2;
    }
    
    private static int rightShiftUnsignedSlow(final int op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).intValue();
    }
    
    public static int rightShiftUnsigned(final int op1, final short op2) {
        if (NumberMathModificationInfo.instance.int_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> op2;
    }
    
    private static int rightShiftUnsignedSlow(final int op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).intValue();
    }
    
    public static int rightShiftUnsigned(final int op1, final int op2) {
        if (NumberMathModificationInfo.instance.int_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> op2;
    }
    
    private static int rightShiftUnsignedSlow(final int op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).intValue();
    }
    
    public static long rightShiftUnsigned(final int op1, final long op2) {
        if (NumberMathModificationInfo.instance.int_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return (long)op1 >>> (int)op2;
    }
    
    private static long rightShiftUnsignedSlow(final int op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).longValue();
    }
    
    public static long rightShiftUnsigned(final long op1, final byte op2) {
        if (NumberMathModificationInfo.instance.long_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> (int)op2;
    }
    
    private static long rightShiftUnsignedSlow(final long op1, final byte op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).longValue();
    }
    
    public static long rightShiftUnsigned(final long op1, final short op2) {
        if (NumberMathModificationInfo.instance.long_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> (int)op2;
    }
    
    private static long rightShiftUnsignedSlow(final long op1, final short op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).longValue();
    }
    
    public static long rightShiftUnsigned(final long op1, final int op2) {
        if (NumberMathModificationInfo.instance.long_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> (int)op2;
    }
    
    private static long rightShiftUnsignedSlow(final long op1, final int op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).longValue();
    }
    
    public static long rightShiftUnsigned(final long op1, final long op2) {
        if (NumberMathModificationInfo.instance.long_rightShiftUnsigned) {
            return rightShiftUnsignedSlow(op1, op2);
        }
        return op1 >>> (int)op2;
    }
    
    private static long rightShiftUnsignedSlow(final long op1, final long op2) {
        return ((Number)InvokerHelper.invokeMethod(op1, "rightShiftUnsigned", op2)).longValue();
    }
    
    static {
        instance = new NumberMathModificationInfo();
    }
}
