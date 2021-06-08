// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.net.URL;
import java.io.File;
import java.sql.Timestamp;
import java.sql.Time;
import java.sql.Date;
import java.math.BigInteger;
import java.math.BigDecimal;
import org.mudebug.prapr.reloc.commons.beanutils.converters.URLConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.FileConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.SqlTimestampConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.SqlTimeConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.SqlDateConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.StringArrayConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.StringConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.ShortArrayConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.LongArrayConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.IntegerArrayConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.FloatArrayConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.DoubleArrayConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.ClassConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.CharacterArrayConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.ByteArrayConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.BooleanArrayConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.BigIntegerConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.BigDecimalConverter;
import java.lang.reflect.Array;
import org.mudebug.prapr.reloc.commons.beanutils.converters.ShortConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.LongConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.IntegerConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.FloatConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.DoubleConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.CharacterConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.ByteConverter;
import org.mudebug.prapr.reloc.commons.beanutils.converters.BooleanConverter;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.collections.FastHashMap;

public class ConvertUtilsBean
{
    private FastHashMap converters;
    private Log log;
    private Boolean defaultBoolean;
    private Byte defaultByte;
    private Character defaultCharacter;
    private Double defaultDouble;
    private Float defaultFloat;
    private Integer defaultInteger;
    private Long defaultLong;
    private static Short defaultShort;
    
    protected static ConvertUtilsBean getInstance() {
        return BeanUtilsBean.getInstance().getConvertUtils();
    }
    
    public ConvertUtilsBean() {
        this.converters = new FastHashMap();
        this.log = LogFactory.getLog(ConvertUtils.class);
        this.defaultBoolean = Boolean.FALSE;
        this.defaultByte = new Byte((byte)0);
        this.defaultCharacter = new Character(' ');
        this.defaultDouble = new Double(0.0);
        this.defaultFloat = new Float(0.0f);
        this.defaultInteger = new Integer(0);
        this.defaultLong = new Long(0L);
        this.converters.setFast(false);
        this.deregister();
        this.converters.setFast(true);
    }
    
    public boolean getDefaultBoolean() {
        return this.defaultBoolean;
    }
    
    public void setDefaultBoolean(final boolean newDefaultBoolean) {
        this.defaultBoolean = new Boolean(newDefaultBoolean);
        this.register(new BooleanConverter(this.defaultBoolean), Boolean.TYPE);
        this.register(new BooleanConverter(this.defaultBoolean), Boolean.class);
    }
    
    public byte getDefaultByte() {
        return this.defaultByte;
    }
    
    public void setDefaultByte(final byte newDefaultByte) {
        this.defaultByte = new Byte(newDefaultByte);
        this.register(new ByteConverter(this.defaultByte), Byte.TYPE);
        this.register(new ByteConverter(this.defaultByte), Byte.class);
    }
    
    public char getDefaultCharacter() {
        return this.defaultCharacter;
    }
    
    public void setDefaultCharacter(final char newDefaultCharacter) {
        this.defaultCharacter = new Character(newDefaultCharacter);
        this.register(new CharacterConverter(this.defaultCharacter), Character.TYPE);
        this.register(new CharacterConverter(this.defaultCharacter), Character.class);
    }
    
    public double getDefaultDouble() {
        return this.defaultDouble;
    }
    
    public void setDefaultDouble(final double newDefaultDouble) {
        this.defaultDouble = new Double(newDefaultDouble);
        this.register(new DoubleConverter(this.defaultDouble), Double.TYPE);
        this.register(new DoubleConverter(this.defaultDouble), Double.class);
    }
    
    public float getDefaultFloat() {
        return this.defaultFloat;
    }
    
    public void setDefaultFloat(final float newDefaultFloat) {
        this.defaultFloat = new Float(newDefaultFloat);
        this.register(new FloatConverter(this.defaultFloat), Float.TYPE);
        this.register(new FloatConverter(this.defaultFloat), Float.class);
    }
    
    public int getDefaultInteger() {
        return this.defaultInteger;
    }
    
    public void setDefaultInteger(final int newDefaultInteger) {
        this.defaultInteger = new Integer(newDefaultInteger);
        this.register(new IntegerConverter(this.defaultInteger), Integer.TYPE);
        this.register(new IntegerConverter(this.defaultInteger), Integer.class);
    }
    
    public long getDefaultLong() {
        return this.defaultLong;
    }
    
    public void setDefaultLong(final long newDefaultLong) {
        this.defaultLong = new Long(newDefaultLong);
        this.register(new LongConverter(this.defaultLong), Long.TYPE);
        this.register(new LongConverter(this.defaultLong), Long.class);
    }
    
    public short getDefaultShort() {
        return ConvertUtilsBean.defaultShort;
    }
    
    public void setDefaultShort(final short newDefaultShort) {
        ConvertUtilsBean.defaultShort = new Short(newDefaultShort);
        this.register(new ShortConverter(ConvertUtilsBean.defaultShort), Short.TYPE);
        this.register(new ShortConverter(ConvertUtilsBean.defaultShort), Short.class);
    }
    
    public String convert(Object value) {
        if (value == null) {
            return null;
        }
        if (!value.getClass().isArray()) {
            final Converter converter = this.lookup(String.class);
            return (String)converter.convert(String.class, value);
        }
        if (Array.getLength(value) < 1) {
            return null;
        }
        value = Array.get(value, 0);
        if (value == null) {
            return null;
        }
        final Converter converter = this.lookup(String.class);
        return (String)converter.convert(String.class, value);
    }
    
    public Object convert(final String value, final Class clazz) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Convert string '" + value + "' to class '" + clazz.getName() + "'");
        }
        Converter converter = this.lookup(clazz);
        if (converter == null) {
            converter = this.lookup(String.class);
        }
        if (this.log.isTraceEnabled()) {
            this.log.trace("  Using converter " + converter);
        }
        return converter.convert(clazz, value);
    }
    
    public Object convert(final String[] values, final Class clazz) {
        Class type = clazz;
        if (clazz.isArray()) {
            type = clazz.getComponentType();
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug("Convert String[" + values.length + "] to class '" + type.getName() + "[]'");
        }
        Converter converter = this.lookup(type);
        if (converter == null) {
            converter = this.lookup(String.class);
        }
        if (this.log.isTraceEnabled()) {
            this.log.trace("  Using converter " + converter);
        }
        final Object array = Array.newInstance(type, values.length);
        for (int i = 0; i < values.length; ++i) {
            Array.set(array, i, converter.convert(type, values[i]));
        }
        return array;
    }
    
    public void deregister() {
        final boolean[] booleanArray = new boolean[0];
        final byte[] byteArray = new byte[0];
        final char[] charArray = new char[0];
        final double[] doubleArray = new double[0];
        final float[] floatArray = new float[0];
        final int[] intArray = new int[0];
        final long[] longArray = new long[0];
        final short[] shortArray = new short[0];
        final String[] stringArray = new String[0];
        this.converters.clear();
        this.register(BigDecimal.class, new BigDecimalConverter());
        this.register(BigInteger.class, new BigIntegerConverter());
        this.register(Boolean.TYPE, new BooleanConverter(this.defaultBoolean));
        this.register(Boolean.class, new BooleanConverter(this.defaultBoolean));
        this.register(booleanArray.getClass(), new BooleanArrayConverter(booleanArray));
        this.register(Byte.TYPE, new ByteConverter(this.defaultByte));
        this.register(Byte.class, new ByteConverter(this.defaultByte));
        this.register(byteArray.getClass(), new ByteArrayConverter(byteArray));
        this.register(Character.TYPE, new CharacterConverter(this.defaultCharacter));
        this.register(Character.class, new CharacterConverter(this.defaultCharacter));
        this.register(charArray.getClass(), new CharacterArrayConverter(charArray));
        this.register(Class.class, new ClassConverter());
        this.register(Double.TYPE, new DoubleConverter(this.defaultDouble));
        this.register(Double.class, new DoubleConverter(this.defaultDouble));
        this.register(doubleArray.getClass(), new DoubleArrayConverter(doubleArray));
        this.register(Float.TYPE, new FloatConverter(this.defaultFloat));
        this.register(Float.class, new FloatConverter(this.defaultFloat));
        this.register(floatArray.getClass(), new FloatArrayConverter(floatArray));
        this.register(Integer.TYPE, new IntegerConverter(this.defaultInteger));
        this.register(Integer.class, new IntegerConverter(this.defaultInteger));
        this.register(intArray.getClass(), new IntegerArrayConverter(intArray));
        this.register(Long.TYPE, new LongConverter(this.defaultLong));
        this.register(Long.class, new LongConverter(this.defaultLong));
        this.register(longArray.getClass(), new LongArrayConverter(longArray));
        this.register(Short.TYPE, new ShortConverter(ConvertUtilsBean.defaultShort));
        this.register(Short.class, new ShortConverter(ConvertUtilsBean.defaultShort));
        this.register(shortArray.getClass(), new ShortArrayConverter(shortArray));
        this.register(String.class, new StringConverter());
        this.register(stringArray.getClass(), new StringArrayConverter(stringArray));
        this.register(Date.class, new SqlDateConverter());
        this.register(Time.class, new SqlTimeConverter());
        this.register(Timestamp.class, new SqlTimestampConverter());
        this.register(File.class, new FileConverter());
        this.register(URL.class, new URLConverter());
    }
    
    private void register(final Class clazz, final Converter converter) {
        this.register(converter, clazz);
    }
    
    public void deregister(final Class clazz) {
        this.converters.remove(clazz);
    }
    
    public Converter lookup(final Class clazz) {
        return (Converter)this.converters.get(clazz);
    }
    
    public void register(final Converter converter, final Class clazz) {
        this.converters.put(clazz, converter);
    }
    
    static {
        ConvertUtilsBean.defaultShort = new Short((short)0);
    }
}
