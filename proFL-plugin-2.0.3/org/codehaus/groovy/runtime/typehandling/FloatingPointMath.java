// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.typehandling;

public final class FloatingPointMath extends NumberMath
{
    public static final FloatingPointMath INSTANCE;
    
    private FloatingPointMath() {
    }
    
    @Override
    protected Number absImpl(final Number number) {
        return new Double(Math.abs(number.doubleValue()));
    }
    
    @Override
    public Number addImpl(final Number left, final Number right) {
        return new Double(left.doubleValue() + right.doubleValue());
    }
    
    @Override
    public Number subtractImpl(final Number left, final Number right) {
        return new Double(left.doubleValue() - right.doubleValue());
    }
    
    @Override
    public Number multiplyImpl(final Number left, final Number right) {
        return new Double(left.doubleValue() * right.doubleValue());
    }
    
    @Override
    public Number divideImpl(final Number left, final Number right) {
        return new Double(left.doubleValue() / right.doubleValue());
    }
    
    @Override
    public int compareToImpl(final Number left, final Number right) {
        return Double.compare(left.doubleValue(), right.doubleValue());
    }
    
    @Override
    protected Number modImpl(final Number left, final Number right) {
        return new Double(left.doubleValue() % right.doubleValue());
    }
    
    @Override
    protected Number unaryMinusImpl(final Number left) {
        return new Double(-left.doubleValue());
    }
    
    static {
        INSTANCE = new FloatingPointMath();
    }
}
