// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.typehandling;

public final class BigIntegerMath extends NumberMath
{
    public static final BigIntegerMath INSTANCE;
    
    private BigIntegerMath() {
    }
    
    @Override
    protected Number absImpl(final Number number) {
        return NumberMath.toBigInteger(number).abs();
    }
    
    @Override
    public Number addImpl(final Number left, final Number right) {
        return NumberMath.toBigInteger(left).add(NumberMath.toBigInteger(right));
    }
    
    @Override
    public Number subtractImpl(final Number left, final Number right) {
        return NumberMath.toBigInteger(left).subtract(NumberMath.toBigInteger(right));
    }
    
    @Override
    public Number multiplyImpl(final Number left, final Number right) {
        return NumberMath.toBigInteger(left).multiply(NumberMath.toBigInteger(right));
    }
    
    @Override
    public Number divideImpl(final Number left, final Number right) {
        return BigDecimalMath.INSTANCE.divideImpl(left, right);
    }
    
    @Override
    public int compareToImpl(final Number left, final Number right) {
        return NumberMath.toBigInteger(left).compareTo(NumberMath.toBigInteger(right));
    }
    
    @Override
    protected Number intdivImpl(final Number left, final Number right) {
        return NumberMath.toBigInteger(left).divide(NumberMath.toBigInteger(right));
    }
    
    @Override
    protected Number modImpl(final Number left, final Number right) {
        return NumberMath.toBigInteger(left).mod(NumberMath.toBigInteger(right));
    }
    
    @Override
    protected Number unaryMinusImpl(final Number left) {
        return NumberMath.toBigInteger(left).negate();
    }
    
    protected Number bitwiseNegateImpl(final Number left) {
        return NumberMath.toBigInteger(left).not();
    }
    
    @Override
    protected Number orImpl(final Number left, final Number right) {
        return NumberMath.toBigInteger(left).or(NumberMath.toBigInteger(right));
    }
    
    @Override
    protected Number andImpl(final Number left, final Number right) {
        return NumberMath.toBigInteger(left).and(NumberMath.toBigInteger(right));
    }
    
    @Override
    protected Number xorImpl(final Number left, final Number right) {
        return NumberMath.toBigInteger(left).xor(NumberMath.toBigInteger(right));
    }
    
    static {
        INSTANCE = new BigIntegerMath();
    }
}
