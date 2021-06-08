// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.typehandling;

import java.math.BigDecimal;
import java.math.MathContext;

public final class BigDecimalMath extends NumberMath
{
    public static final int DIVISION_EXTRA_PRECISION = 10;
    public static final int DIVISION_MIN_SCALE = 10;
    public static final BigDecimalMath INSTANCE;
    
    private BigDecimalMath() {
    }
    
    @Override
    protected Number absImpl(final Number number) {
        return NumberMath.toBigDecimal(number).abs();
    }
    
    @Override
    public Number addImpl(final Number left, final Number right) {
        return NumberMath.toBigDecimal(left).add(NumberMath.toBigDecimal(right));
    }
    
    @Override
    public Number subtractImpl(final Number left, final Number right) {
        return NumberMath.toBigDecimal(left).subtract(NumberMath.toBigDecimal(right));
    }
    
    @Override
    public Number multiplyImpl(final Number left, final Number right) {
        return NumberMath.toBigDecimal(left).multiply(NumberMath.toBigDecimal(right));
    }
    
    @Override
    public Number divideImpl(final Number left, final Number right) {
        final BigDecimal bigLeft = NumberMath.toBigDecimal(left);
        final BigDecimal bigRight = NumberMath.toBigDecimal(right);
        try {
            return bigLeft.divide(bigRight);
        }
        catch (ArithmeticException e) {
            final int precision = Math.max(bigLeft.precision(), bigRight.precision()) + 10;
            BigDecimal result = bigLeft.divide(bigRight, new MathContext(precision));
            final int scale = Math.max(Math.max(bigLeft.scale(), bigRight.scale()), 10);
            if (result.scale() > scale) {
                result = result.setScale(scale, 4);
            }
            return result;
        }
    }
    
    @Override
    public int compareToImpl(final Number left, final Number right) {
        return NumberMath.toBigDecimal(left).compareTo(NumberMath.toBigDecimal(right));
    }
    
    @Override
    protected Number unaryMinusImpl(final Number left) {
        return NumberMath.toBigDecimal(left).negate();
    }
    
    static {
        INSTANCE = new BigDecimalMath();
    }
}
