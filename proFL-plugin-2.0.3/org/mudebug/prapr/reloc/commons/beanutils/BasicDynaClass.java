// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.lang.reflect.Constructor;
import java.io.Serializable;

public class BasicDynaClass implements DynaClass, Serializable
{
    protected transient Constructor constructor;
    protected static Class[] constructorTypes;
    protected Object[] constructorValues;
    protected Class dynaBeanClass;
    protected String name;
    protected DynaProperty[] properties;
    protected HashMap propertiesMap;
    
    public BasicDynaClass() {
        this(null, null, null);
    }
    
    public BasicDynaClass(final String name, final Class dynaBeanClass) {
        this(name, dynaBeanClass, null);
    }
    
    public BasicDynaClass(final String name, Class dynaBeanClass, final DynaProperty[] properties) {
        this.constructor = null;
        this.constructorValues = new Object[] { this };
        this.dynaBeanClass = BasicDynaBean.class;
        this.name = this.getClass().getName();
        this.properties = new DynaProperty[0];
        this.propertiesMap = new HashMap();
        if (name != null) {
            this.name = name;
        }
        if (dynaBeanClass == null) {
            dynaBeanClass = BasicDynaBean.class;
        }
        this.setDynaBeanClass(dynaBeanClass);
        if (properties != null) {
            this.setProperties(properties);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public DynaProperty getDynaProperty(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("No property name specified");
        }
        return this.propertiesMap.get(name);
    }
    
    public DynaProperty[] getDynaProperties() {
        return this.properties;
    }
    
    public DynaBean newInstance() throws IllegalAccessException, InstantiationException {
        try {
            if (this.constructor == null) {
                this.setDynaBeanClass(this.dynaBeanClass);
            }
            return this.constructor.newInstance(this.constructorValues);
        }
        catch (InvocationTargetException e) {
            throw new InstantiationException(e.getTargetException().getMessage());
        }
    }
    
    public Class getDynaBeanClass() {
        return this.dynaBeanClass;
    }
    
    protected void setDynaBeanClass(final Class dynaBeanClass) {
        if (dynaBeanClass.isInterface()) {
            throw new IllegalArgumentException("Class " + dynaBeanClass.getName() + " is an interface, not a class");
        }
        if (!DynaBean.class.isAssignableFrom(dynaBeanClass)) {
            throw new IllegalArgumentException("Class " + dynaBeanClass.getName() + " does not implement DynaBean");
        }
        try {
            this.constructor = dynaBeanClass.getConstructor((Class[])BasicDynaClass.constructorTypes);
        }
        catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class " + dynaBeanClass.getName() + " does not have an appropriate constructor");
        }
        this.dynaBeanClass = dynaBeanClass;
    }
    
    protected void setProperties(final DynaProperty[] properties) {
        this.properties = properties;
        this.propertiesMap.clear();
        for (int i = 0; i < properties.length; ++i) {
            this.propertiesMap.put(properties[i].getName(), properties[i]);
        }
    }
    
    static {
        BasicDynaClass.constructorTypes = new Class[] { DynaClass.class };
    }
}
