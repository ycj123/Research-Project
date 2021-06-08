// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import java.util.Arrays;

public class HashCodeHelper
{
    private static final int SEED = 127;
    private static final int MULT = 31;
    
    public static int initHash() {
        return 127;
    }
    
    public static int updateHash(final int current, final boolean var) {
        return shift(current) + (var ? 1 : 0);
    }
    
    public static int updateHash(final int current, final char var) {
        return shift(current) + var;
    }
    
    public static int updateHash(final int current, final int var) {
        return shift(current) + var;
    }
    
    public static int updateHash(final int current, final long var) {
        return shift(current) + (int)(var ^ var >>> 32);
    }
    
    public static int updateHash(final int current, final float var) {
        return updateHash(current, Float.floatToIntBits(var));
    }
    
    public static int updateHash(final int current, final double var) {
        return updateHash(current, Double.doubleToLongBits(var));
    }
    
    public static int updateHash(final int current, final Object var) {
        if (var == null) {
            return updateHash(current, 0);
        }
        if (var.getClass().isArray()) {
            return shift(current) + Arrays.hashCode((Object[])var);
        }
        return updateHash(current, var.hashCode());
    }
    
    public static int updateHash(final int current, final boolean[] var) {
        if (var == null) {
            return updateHash(current, 0);
        }
        return shift(current) + Arrays.hashCode(var);
    }
    
    public static int updateHash(final int current, final char[] var) {
        if (var == null) {
            return updateHash(current, 0);
        }
        return shift(current) + Arrays.hashCode(var);
    }
    
    public static int updateHash(final int current, final byte[] var) {
        if (var == null) {
            return updateHash(current, 0);
        }
        return shift(current) + Arrays.hashCode(var);
    }
    
    public static int updateHash(final int current, final short[] var) {
        if (var == null) {
            return updateHash(current, 0);
        }
        return shift(current) + Arrays.hashCode(var);
    }
    
    public static int updateHash(final int current, final int[] var) {
        if (var == null) {
            return updateHash(current, 0);
        }
        return shift(current) + Arrays.hashCode(var);
    }
    
    public static int updateHash(final int current, final long[] var) {
        if (var == null) {
            return updateHash(current, 0);
        }
        return shift(current) + Arrays.hashCode(var);
    }
    
    public static int updateHash(final int current, final float[] var) {
        if (var == null) {
            return updateHash(current, 0);
        }
        return shift(current) + Arrays.hashCode(var);
    }
    
    public static int updateHash(final int current, final double[] var) {
        if (var == null) {
            return updateHash(current, 0);
        }
        return shift(current) + Arrays.hashCode(var);
    }
    
    private static int shift(final int current) {
        return 31 * current;
    }
}
