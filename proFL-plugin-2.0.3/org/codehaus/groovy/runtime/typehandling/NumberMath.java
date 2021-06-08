// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.typehandling;

import java.math.BigInteger;
import java.math.BigDecimal;

public abstract class NumberMath
{
    public static Number abs(final Number number) {
        return getMath(number).absImpl(number);
    }
    
    public static Number add(final Number left, final Number right) {
        return getMath(left, right).addImpl(left, right);
    }
    
    public static Number subtract(final Number left, final Number right) {
        return getMath(left, right).subtractImpl(left, right);
    }
    
    public static Number multiply(final Number left, final Number right) {
        return getMath(left, right).multiplyImpl(left, right);
    }
    
    public static Number divide(final Number left, final Number right) {
        return getMath(left, right).divideImpl(left, right);
    }
    
    public static int compareTo(final Number left, final Number right) {
        return getMath(left, right).compareToImpl(left, right);
    }
    
    public static Number or(final Number left, final Number right) {
        return getMath(left, right).orImpl(left, right);
    }
    
    public static Number and(final Number left, final Number right) {
        return getMath(left, right).andImpl(left, right);
    }
    
    public static Number xor(final Number left, final Number right) {
        return getMath(left, right).xorImpl(left, right);
    }
    
    public static Number intdiv(final Number left, final Number right) {
        return getMath(left, right).intdivImpl(left, right);
    }
    
    public static Number mod(final Number left, final Number right) {
        return getMath(left, right).modImpl(left, right);
    }
    
    public static Number leftShift(final Number left, final Number right) {
        if (isFloatingPoint(right) || isBigDecimal(right)) {
            throw new UnsupportedOperationException("Shift distance must be an integral type, but " + right + " (" + right.getClass().getName() + ") was supplied");
        }
        return getMath(left).leftShiftImpl(left, right);
    }
    
    public static Number rightShift(final Number left, final Number right) {
        if (isFloatingPoint(right) || isBigDecimal(right)) {
            throw new UnsupportedOperationException("Shift distance must be an integral type, but " + right + " (" + right.getClass().getName() + ") was supplied");
        }
        return getMath(left).rightShiftImpl(left, right);
    }
    
    public static Number rightShiftUnsigned(final Number left, final Number right) {
        if (isFloatingPoint(right) || isBigDecimal(right)) {
            throw new UnsupportedOperationException("Shift distance must be an integral type, but " + right + " (" + right.getClass().getName() + ") was supplied");
        }
        return getMath(left).rightShiftUnsignedImpl(left, right);
    }
    
    public static Number unaryMinus(final Number left) {
        return getMath(left).unaryMinusImpl(left);
    }
    
    public static boolean isFloatingPoint(final Number number) {
        return number instanceof Double || number instanceof Float;
    }
    
    public static boolean isInteger(final Number number) {
        return number instanceof Integer;
    }
    
    public static boolean isLong(final Number number) {
        return number instanceof Long;
    }
    
    public static boolean isBigDecimal(final Number number) {
        return number instanceof BigDecimal;
    }
    
    public static boolean isBigInteger(final Number number) {
        return number instanceof BigInteger;
    }
    
    public static BigDecimal toBigDecimal(final Number n) {
        return (BigDecimal)((n instanceof BigDecimal) ? n : new BigDecimal(n.toString()));
    }
    
    public static BigInteger toBigInteger(final Number n) {
        return (BigInteger)((n instanceof BigInteger) ? n : new BigInteger(n.toString()));
    }
    
    public static NumberMath getMath(final Number left, final Number right) {
        if (isFloatingPoint(left) || isFloatingPoint(right)) {
            return FloatingPointMath.INSTANCE;
        }
        if (isBigDecimal(left) || isBigDecimal(right)) {
            return BigDecimalMath.INSTANCE;
        }
        if (isBigInteger(left) || isBigInteger(right)) {
            return BigIntegerMath.INSTANCE;
        }
        if (isLong(left) || isLong(right)) {
            return LongMath.INSTANCE;
        }
        return IntegerMath.INSTANCE;
    }
    
    private static NumberMath getMath(final Number number) {
        if (isLong(number)) {
            return LongMath.INSTANCE;
        }
        if (isFloatingPoint(number)) {
            return FloatingPointMath.INSTANCE;
        }
        if (isBigDecimal(number)) {
            return BigDecimalMath.INSTANCE;
        }
        if (isBigInteger(number)) {
            return BigIntegerMath.INSTANCE;
        }
        return IntegerMath.INSTANCE;
    }
    
    protected abstract Number absImpl(final Number p0);
    
    public abstract Number addImpl(final Number p0, final Number p1);
    
    public abstract Number subtractImpl(final Number p0, final Number p1);
    
    public abstract Number multiplyImpl(final Number p0, final Number p1);
    
    public abstract Number divideImpl(final Number p0, final Number p1);
    
    public abstract int compareToImpl(final Number p0, final Number p1);
    
    protected abstract Number unaryMinusImpl(final Number p0);
    
    protected Number orImpl(final Number left, final Number right) {
        throw this.createUnsupportedException("or()", left);
    }
    
    protected Number andImpl(final Number left, final Number right) {
        throw this.createUnsupportedException("and()", left);
    }
    
    protected Number xorImpl(final Number left, final Number right) {
        throw this.createUnsupportedException("xor()", left);
    }
    
    protected Number modImpl(final Number left, final Number right) {
        throw this.createUnsupportedException("mod()", left);
    }
    
    protected Number intdivImpl(final Number left, final Number right) {
        throw this.createUnsupportedException("intdiv()", left);
    }
    
    protected Number leftShiftImpl(final Number left, final Number right) {
        throw this.createUnsupportedException("leftShift()", left);
    }
    
    protected Number rightShiftImpl(final Number left, final Number right) {
        throw this.createUnsupportedException("rightShift()", left);
    }
    
    protected Number rightShiftUnsignedImpl(final Number left, final Number right) {
        throw this.createUnsupportedException("rightShiftUnsigned()", left);
    }
    
    protected UnsupportedOperationException createUnsupportedException(final String operation, final Number left) {
        return new UnsupportedOperationException("Cannot use " + operation + " on this number type: " + left.getClass().getName() + " with value: " + left);
    }
}
