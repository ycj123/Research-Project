// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.parser.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

public abstract class MathUtils
{
    protected static final BigDecimal DECIMAL_ZERO;
    protected static final int BASE_LONG = 0;
    protected static final int BASE_FLOAT = 1;
    protected static final int BASE_DOUBLE = 2;
    protected static final int BASE_BIGINTEGER = 3;
    protected static final int BASE_BIGDECIMAL = 4;
    protected static final Map ints;
    protected static final List typesBySize;
    
    public static BigDecimal toBigDecimal(final Number n) {
        if (n instanceof BigDecimal) {
            return (BigDecimal)n;
        }
        if (n instanceof BigInteger) {
            return new BigDecimal((BigInteger)n);
        }
        return new BigDecimal(n.doubleValue());
    }
    
    public static BigInteger toBigInteger(final Number n) {
        if (n instanceof BigInteger) {
            return (BigInteger)n;
        }
        return BigInteger.valueOf(n.longValue());
    }
    
    public static boolean isZero(final Number n) {
        if (isInteger(n)) {
            if (n instanceof BigInteger) {
                return ((BigInteger)n).compareTo(BigInteger.ZERO) == 0;
            }
            return n.doubleValue() == 0.0;
        }
        else {
            if (n instanceof Float) {
                return n.floatValue() == 0.0f;
            }
            if (n instanceof Double) {
                return n.doubleValue() == 0.0;
            }
            return toBigDecimal(n).compareTo(MathUtils.DECIMAL_ZERO) == 0;
        }
    }
    
    public static boolean isInteger(final Number n) {
        return MathUtils.ints.containsKey(n.getClass());
    }
    
    public static Number wrapPrimitive(final long value, Class type) {
        if (type == Byte.class) {
            if (value <= 127L && value >= -128L) {
                return new Byte((byte)value);
            }
            type = Short.class;
        }
        if (type == Short.class) {
            if (value <= 32767L && value >= -32768L) {
                return new Short((short)value);
            }
            type = Integer.class;
        }
        if (type == Integer.class) {
            if (value <= 2147483647L && value >= -2147483648L) {
                return new Integer((int)value);
            }
            type = Long.class;
        }
        if (type == Long.class) {
            return new Long(value);
        }
        return BigInteger.valueOf(value);
    }
    
    private static Number wrapPrimitive(final long value, final Number op1, final Number op2) {
        if (MathUtils.typesBySize.indexOf(op1.getClass()) > MathUtils.typesBySize.indexOf(op2.getClass())) {
            return wrapPrimitive(value, op1.getClass());
        }
        return wrapPrimitive(value, op2.getClass());
    }
    
    private static int findCalculationBase(final Number op1, final Number op2) {
        final boolean op1Int = isInteger(op1);
        final boolean op2Int = isInteger(op2);
        if (op1 instanceof BigDecimal || op2 instanceof BigDecimal || ((!op1Int || !op2Int) && (op1 instanceof BigInteger || op2 instanceof BigInteger))) {
            return 4;
        }
        if (op1Int && op2Int) {
            if (op1 instanceof BigInteger || op2 instanceof BigInteger) {
                return 3;
            }
            return 0;
        }
        else {
            if (op1 instanceof Double || op2 instanceof Double) {
                return 2;
            }
            return 1;
        }
    }
    
    public static Number add(final Number op1, final Number op2) {
        final int calcBase = findCalculationBase(op1, op2);
        switch (calcBase) {
            case 3: {
                return toBigInteger(op1).add(toBigInteger(op2));
            }
            case 0: {
                final long l1 = op1.longValue();
                final long l2 = op2.longValue();
                final long result = l1 + l2;
                if ((result ^ l1) < 0L && (result ^ l2) < 0L) {
                    return toBigInteger(op1).add(toBigInteger(op2));
                }
                return wrapPrimitive(result, op1, op2);
            }
            case 1: {
                return new Float(op1.floatValue() + op2.floatValue());
            }
            case 2: {
                return new Double(op1.doubleValue() + op2.doubleValue());
            }
            default: {
                return toBigDecimal(op1).add(toBigDecimal(op2));
            }
        }
    }
    
    public static Number subtract(final Number op1, final Number op2) {
        final int calcBase = findCalculationBase(op1, op2);
        switch (calcBase) {
            case 3: {
                return toBigInteger(op1).subtract(toBigInteger(op2));
            }
            case 0: {
                final long l1 = op1.longValue();
                final long l2 = op2.longValue();
                final long result = l1 - l2;
                if ((result ^ l1) < 0L && (result ^ ~l2) < 0L) {
                    return toBigInteger(op1).subtract(toBigInteger(op2));
                }
                return wrapPrimitive(result, op1, op2);
            }
            case 1: {
                return new Float(op1.floatValue() - op2.floatValue());
            }
            case 2: {
                return new Double(op1.doubleValue() - op2.doubleValue());
            }
            default: {
                return toBigDecimal(op1).subtract(toBigDecimal(op2));
            }
        }
    }
    
    public static Number multiply(final Number op1, final Number op2) {
        final int calcBase = findCalculationBase(op1, op2);
        switch (calcBase) {
            case 3: {
                return toBigInteger(op1).multiply(toBigInteger(op2));
            }
            case 0: {
                final long l1 = op1.longValue();
                final long l2 = op2.longValue();
                final long result = l1 * l2;
                if (l2 != 0L && result / l2 != l1) {
                    return toBigInteger(op1).multiply(toBigInteger(op2));
                }
                return wrapPrimitive(result, op1, op2);
            }
            case 1: {
                return new Float(op1.floatValue() * op2.floatValue());
            }
            case 2: {
                return new Double(op1.doubleValue() * op2.doubleValue());
            }
            default: {
                return toBigDecimal(op1).multiply(toBigDecimal(op2));
            }
        }
    }
    
    public static Number divide(final Number op1, final Number op2) {
        final int calcBase = findCalculationBase(op1, op2);
        switch (calcBase) {
            case 3: {
                final BigInteger b1 = toBigInteger(op1);
                final BigInteger b2 = toBigInteger(op2);
                return b1.divide(b2);
            }
            case 0: {
                final long l1 = op1.longValue();
                final long l2 = op2.longValue();
                return wrapPrimitive(l1 / l2, op1, op2);
            }
            case 1: {
                return new Float(op1.floatValue() / op2.floatValue());
            }
            case 2: {
                return new Double(op1.doubleValue() / op2.doubleValue());
            }
            default: {
                return toBigDecimal(op1).divide(toBigDecimal(op2), 5);
            }
        }
    }
    
    public static Number modulo(final Number op1, final Number op2) throws ArithmeticException {
        final int calcBase = findCalculationBase(op1, op2);
        switch (calcBase) {
            case 3: {
                return toBigInteger(op1).mod(toBigInteger(op2));
            }
            case 0: {
                return wrapPrimitive(op1.longValue() % op2.longValue(), op1, op2);
            }
            case 1: {
                return new Float(op1.floatValue() % op2.floatValue());
            }
            case 2: {
                return new Double(op1.doubleValue() % op2.doubleValue());
            }
            default: {
                throw new ArithmeticException("Cannot calculate the modulo of BigDecimals.");
            }
        }
    }
    
    public static int compare(final Number op1, final Number op2) {
        final int calcBase = findCalculationBase(op1, op2);
        switch (calcBase) {
            case 3: {
                return toBigInteger(op1).compareTo(toBigInteger(op2));
            }
            case 0: {
                final long l1 = op1.longValue();
                final long l2 = op2.longValue();
                if (l1 < l2) {
                    return -1;
                }
                if (l1 > l2) {
                    return 1;
                }
                return 0;
            }
            case 1: {
                final float f1 = op1.floatValue();
                final float f2 = op2.floatValue();
                if (f1 < f2) {
                    return -1;
                }
                if (f1 > f2) {
                    return 1;
                }
                return 0;
            }
            case 2: {
                final double d1 = op1.doubleValue();
                final double d2 = op2.doubleValue();
                if (d1 < d2) {
                    return -1;
                }
                if (d1 > d2) {
                    return 1;
                }
                return 0;
            }
            default: {
                return toBigDecimal(op1).compareTo(toBigDecimal(op2));
            }
        }
    }
    
    static {
        DECIMAL_ZERO = new BigDecimal(BigInteger.ZERO);
        (ints = new HashMap()).put(Byte.class, BigDecimal.valueOf(127L));
        MathUtils.ints.put(Short.class, BigDecimal.valueOf(32767L));
        MathUtils.ints.put(Integer.class, BigDecimal.valueOf(2147483647L));
        MathUtils.ints.put(Long.class, BigDecimal.valueOf(Long.MAX_VALUE));
        MathUtils.ints.put(BigInteger.class, BigDecimal.valueOf(-1L));
        (typesBySize = new ArrayList()).add(Byte.class);
        MathUtils.typesBySize.add(Short.class);
        MathUtils.typesBySize.add(Integer.class);
        MathUtils.typesBySize.add(Long.class);
        MathUtils.typesBySize.add(Float.class);
        MathUtils.typesBySize.add(Double.class);
    }
}
