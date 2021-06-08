// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

public class WrapDynaBean implements DynaBean
{
    protected WrapDynaClass dynaClass;
    protected Object instance;
    
    public WrapDynaBean(final Object instance) {
        this.dynaClass = null;
        this.instance = null;
        this.instance = instance;
        this.dynaClass = WrapDynaClass.createDynaClass(instance.getClass());
    }
    
    public boolean contains(final String name, final String key) {
        throw new UnsupportedOperationException("WrapDynaBean does not support contains()");
    }
    
    public Object get(final String name) {
        Object value = null;
        try {
            value = PropertyUtils.getSimpleProperty(this.instance, name);
        }
        catch (Throwable t) {
            throw new IllegalArgumentException("Property '" + name + "' has no read method");
        }
        return value;
    }
    
    public Object get(final String name, final int index) {
        Object value = null;
        try {
            value = PropertyUtils.getIndexedProperty(this.instance, name, index);
        }
        catch (IndexOutOfBoundsException e) {
            throw e;
        }
        catch (Throwable t) {
            throw new IllegalArgumentException("Property '" + name + "' has no indexed read method");
        }
        return value;
    }
    
    public Object get(final String name, final String key) {
        Object value = null;
        try {
            value = PropertyUtils.getMappedProperty(this.instance, name, key);
        }
        catch (Throwable t) {
            throw new IllegalArgumentException("Property '" + name + "' has no mapped read method");
        }
        return value;
    }
    
    public DynaClass getDynaClass() {
        return this.dynaClass;
    }
    
    public void remove(final String name, final String key) {
        throw new UnsupportedOperationException("WrapDynaBean does not support remove()");
    }
    
    public void set(final String name, final Object value) {
        try {
            PropertyUtils.setSimpleProperty(this.instance, name, value);
        }
        catch (Throwable t) {
            throw new IllegalArgumentException("Property '" + name + "' has no write method");
        }
    }
    
    public void set(final String name, final int index, final Object value) {
        try {
            PropertyUtils.setIndexedProperty(this.instance, name, index, value);
        }
        catch (IndexOutOfBoundsException e) {
            throw e;
        }
        catch (Throwable t) {
            throw new IllegalArgumentException("Property '" + name + "' has no indexed write method");
        }
    }
    
    public void set(final String name, final String key, final Object value) {
        try {
            PropertyUtils.setMappedProperty(this.instance, name, key, value);
        }
        catch (Throwable t) {
            throw new IllegalArgumentException("Property '" + name + "' has no mapped write method");
        }
    }
    
    public Object getInstance() {
        return this.instance;
    }
    
    protected DynaProperty getDynaProperty(final String name) {
        final DynaProperty descriptor = this.getDynaClass().getDynaProperty(name);
        if (descriptor == null) {
            throw new IllegalArgumentException("Invalid property name '" + name + "'");
        }
        return descriptor;
    }
}
