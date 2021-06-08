// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.typehandling;

public final class IntegerMath extends NumberMath
{
    public static final IntegerMath INSTANCE;
    
    private IntegerMath() {
    }
    
    @Override
    protected Number absImpl(final Number number) {
        return Math.abs(number.intValue());
    }
    
    @Override
    public Number addImpl(final Number left, final Number right) {
        return left.intValue() + right.intValue();
    }
    
    @Override
    public Number subtractImpl(final Number left, final Number right) {
        return left.intValue() - right.intValue();
    }
    
    @Override
    public Number multiplyImpl(final Number left, final Number right) {
        return left.intValue() * right.intValue();
    }
    
    @Override
    public Number divideImpl(final Number left, final Number right) {
        return BigDecimalMath.INSTANCE.divideImpl(left, right);
    }
    
    @Override
    public int compareToImpl(final Number left, final Number right) {
        final int leftVal = left.intValue();
        final int rightVal = right.intValue();
        return (leftVal < rightVal) ? -1 : ((leftVal == rightVal) ? 0 : 1);
    }
    
    @Override
    protected Number orImpl(final Number left, final Number right) {
        return left.intValue() | right.intValue();
    }
    
    @Override
    protected Number andImpl(final Number left, final Number right) {
        return left.intValue() & right.intValue();
    }
    
    @Override
    protected Number xorImpl(final Number left, final Number right) {
        return left.intValue() ^ right.intValue();
    }
    
    @Override
    protected Number intdivImpl(final Number left, final Number right) {
        return left.intValue() / right.intValue();
    }
    
    @Override
    protected Number modImpl(final Number left, final Number right) {
        return left.intValue() % right.intValue();
    }
    
    @Override
    protected Number unaryMinusImpl(final Number left) {
        return -left.intValue();
    }
    
    protected Number bitwiseNegateImpl(final Number left) {
        return ~left.intValue();
    }
    
    @Override
    protected Number leftShiftImpl(final Number left, final Number right) {
        return left.intValue() << right.intValue();
    }
    
    @Override
    protected Number rightShiftImpl(final Number left, final Number right) {
        return left.intValue() >> right.intValue();
    }
    
    @Override
    protected Number rightShiftUnsignedImpl(final Number left, final Number right) {
        return left.intValue() >>> right.intValue();
    }
    
    static {
        INSTANCE = new IntegerMath();
    }
}
