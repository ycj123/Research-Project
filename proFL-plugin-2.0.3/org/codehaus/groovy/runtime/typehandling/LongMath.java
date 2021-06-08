// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.typehandling;

public final class LongMath extends NumberMath
{
    public static final LongMath INSTANCE;
    
    private LongMath() {
    }
    
    @Override
    protected Number absImpl(final Number number) {
        return new Long(Math.abs(number.longValue()));
    }
    
    @Override
    public Number addImpl(final Number left, final Number right) {
        return new Long(left.longValue() + right.longValue());
    }
    
    @Override
    public Number subtractImpl(final Number left, final Number right) {
        return new Long(left.longValue() - right.longValue());
    }
    
    @Override
    public Number multiplyImpl(final Number left, final Number right) {
        return new Long(left.longValue() * right.longValue());
    }
    
    @Override
    public Number divideImpl(final Number left, final Number right) {
        return BigDecimalMath.INSTANCE.divideImpl(left, right);
    }
    
    @Override
    public int compareToImpl(final Number left, final Number right) {
        final long leftVal = left.longValue();
        final long rightVal = right.longValue();
        return (leftVal < rightVal) ? -1 : ((leftVal == rightVal) ? 0 : 1);
    }
    
    @Override
    protected Number intdivImpl(final Number left, final Number right) {
        return new Long(left.longValue() / right.longValue());
    }
    
    @Override
    protected Number modImpl(final Number left, final Number right) {
        return new Long(left.longValue() % right.longValue());
    }
    
    @Override
    protected Number unaryMinusImpl(final Number left) {
        return new Long(-left.longValue());
    }
    
    protected Number bitwiseNegateImpl(final Number left) {
        return new Long(~left.longValue());
    }
    
    @Override
    protected Number orImpl(final Number left, final Number right) {
        return new Long(left.longValue() | right.longValue());
    }
    
    @Override
    protected Number andImpl(final Number left, final Number right) {
        return new Long(left.longValue() & right.longValue());
    }
    
    @Override
    protected Number xorImpl(final Number left, final Number right) {
        return new Long(left.longValue() ^ right.longValue());
    }
    
    @Override
    protected Number leftShiftImpl(final Number left, final Number right) {
        return new Long(left.longValue() << (int)right.longValue());
    }
    
    @Override
    protected Number rightShiftImpl(final Number left, final Number right) {
        return new Long(left.longValue() >> (int)right.longValue());
    }
    
    @Override
    protected Number rightShiftUnsignedImpl(final Number left, final Number right) {
        return new Long(left.longValue() >>> (int)right.longValue());
    }
    
    protected Number bitAndImpl(final Number left, final Number right) {
        return new Long(left.longValue() & right.longValue());
    }
    
    static {
        INSTANCE = new LongMath();
    }
}
