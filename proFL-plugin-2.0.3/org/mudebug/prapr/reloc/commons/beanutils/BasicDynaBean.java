// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.util.List;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class BasicDynaBean implements DynaBean, Serializable
{
    protected DynaClass dynaClass;
    protected HashMap values;
    
    public BasicDynaBean(final DynaClass dynaClass) {
        this.dynaClass = null;
        this.values = new HashMap();
        this.dynaClass = dynaClass;
    }
    
    public boolean contains(final String name, final String key) {
        final Object value = this.values.get(name);
        if (value == null) {
            throw new NullPointerException("No mapped value for '" + name + "(" + key + ")'");
        }
        if (value instanceof Map) {
            return ((Map)value).containsKey(key);
        }
        throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'");
    }
    
    public Object get(final String name) {
        final Object value = this.values.get(name);
        if (value != null) {
            return value;
        }
        final Class type = this.getDynaProperty(name).getType();
        if (!type.isPrimitive()) {
            return value;
        }
        if (type == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (type == Byte.TYPE) {
            return new Byte((byte)0);
        }
        if (type == Character.TYPE) {
            return new Character('\0');
        }
        if (type == Double.TYPE) {
            return new Double(0.0);
        }
        if (type == Float.TYPE) {
            return new Float(0.0f);
        }
        if (type == Integer.TYPE) {
            return new Integer(0);
        }
        if (type == Long.TYPE) {
            return new Long(0L);
        }
        if (type == Short.TYPE) {
            return new Short((short)0);
        }
        return null;
    }
    
    public Object get(final String name, final int index) {
        final Object value = this.values.get(name);
        if (value == null) {
            throw new NullPointerException("No indexed value for '" + name + "[" + index + "]'");
        }
        if (value.getClass().isArray()) {
            return Array.get(value, index);
        }
        if (value instanceof List) {
            return ((List)value).get(index);
        }
        throw new IllegalArgumentException("Non-indexed property for '" + name + "[" + index + "]'");
    }
    
    public Object get(final String name, final String key) {
        final Object value = this.values.get(name);
        if (value == null) {
            throw new NullPointerException("No mapped value for '" + name + "(" + key + ")'");
        }
        if (value instanceof Map) {
            return ((Map)value).get(key);
        }
        throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'");
    }
    
    public DynaClass getDynaClass() {
        return this.dynaClass;
    }
    
    public void remove(final String name, final String key) {
        final Object value = this.values.get(name);
        if (value == null) {
            throw new NullPointerException("No mapped value for '" + name + "(" + key + ")'");
        }
        if (value instanceof Map) {
            ((Map)value).remove(key);
            return;
        }
        throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'");
    }
    
    public void set(final String name, final Object value) {
        final DynaProperty descriptor = this.getDynaProperty(name);
        if (value == null) {
            if (descriptor.getType().isPrimitive()) {
                throw new NullPointerException("Primitive value for '" + name + "'");
            }
        }
        else if (!this.isAssignable(descriptor.getType(), value.getClass())) {
            throw new ConversionException("Cannot assign value of type '" + value.getClass().getName() + "' to property '" + name + "' of type '" + descriptor.getType().getName() + "'");
        }
        this.values.put(name, value);
    }
    
    public void set(final String name, final int index, final Object value) {
        final Object prop = this.values.get(name);
        if (prop == null) {
            throw new NullPointerException("No indexed value for '" + name + "[" + index + "]'");
        }
        if (prop.getClass().isArray()) {
            Array.set(prop, index, value);
        }
        else {
            if (!(prop instanceof List)) {
                throw new IllegalArgumentException("Non-indexed property for '" + name + "[" + index + "]'");
            }
            try {
                ((List)prop).set(index, value);
            }
            catch (ClassCastException e) {
                throw new ConversionException(e.getMessage());
            }
        }
    }
    
    public void set(final String name, final String key, final Object value) {
        final Object prop = this.values.get(name);
        if (prop == null) {
            throw new NullPointerException("No mapped value for '" + name + "(" + key + ")'");
        }
        if (prop instanceof Map) {
            ((Map)prop).put(key, value);
            return;
        }
        throw new IllegalArgumentException("Non-mapped property for '" + name + "(" + key + ")'");
    }
    
    protected DynaProperty getDynaProperty(final String name) {
        final DynaProperty descriptor = this.getDynaClass().getDynaProperty(name);
        if (descriptor == null) {
            throw new IllegalArgumentException("Invalid property name '" + name + "'");
        }
        return descriptor;
    }
    
    protected boolean isAssignable(final Class dest, final Class source) {
        return dest.isAssignableFrom(source) || (dest == Boolean.TYPE && source == Boolean.class) || (dest == Byte.TYPE && source == Byte.class) || (dest == Character.TYPE && source == Character.class) || (dest == Double.TYPE && source == Double.class) || (dest == Float.TYPE && source == Float.class) || (dest == Integer.TYPE && source == Integer.class) || (dest == Long.TYPE && source == Long.class) || (dest == Short.TYPE && source == Short.class);
    }
}
