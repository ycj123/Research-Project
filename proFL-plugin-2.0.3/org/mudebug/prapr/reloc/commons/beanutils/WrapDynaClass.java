// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.beans.PropertyDescriptor;

public class WrapDynaClass implements DynaClass
{
    protected Class beanClass;
    protected PropertyDescriptor[] descriptors;
    protected HashMap descriptorsMap;
    protected DynaProperty[] properties;
    protected HashMap propertiesMap;
    protected static HashMap dynaClasses;
    
    private WrapDynaClass(final Class beanClass) {
        this.beanClass = null;
        this.descriptors = null;
        this.descriptorsMap = new HashMap();
        this.properties = null;
        this.propertiesMap = new HashMap();
        this.beanClass = beanClass;
        this.introspect();
    }
    
    public String getName() {
        return this.beanClass.getName();
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
        return new WrapDynaBean(this.beanClass.newInstance());
    }
    
    public PropertyDescriptor getPropertyDescriptor(final String name) {
        return this.descriptorsMap.get(name);
    }
    
    public static void clear() {
        synchronized (WrapDynaClass.dynaClasses) {
            WrapDynaClass.dynaClasses.clear();
        }
    }
    
    public static WrapDynaClass createDynaClass(final Class beanClass) {
        synchronized (WrapDynaClass.dynaClasses) {
            WrapDynaClass dynaClass = WrapDynaClass.dynaClasses.get(beanClass);
            if (dynaClass == null) {
                dynaClass = new WrapDynaClass(beanClass);
                WrapDynaClass.dynaClasses.put(beanClass, dynaClass);
            }
            return dynaClass;
        }
    }
    
    protected void introspect() {
        PropertyDescriptor[] regulars = PropertyUtils.getPropertyDescriptors(this.beanClass);
        if (regulars == null) {
            regulars = new PropertyDescriptor[0];
        }
        HashMap mappeds = PropertyUtils.getMappedPropertyDescriptors(this.beanClass);
        if (mappeds == null) {
            mappeds = new HashMap();
        }
        this.properties = new DynaProperty[regulars.length + mappeds.size()];
        for (int i = 0; i < regulars.length; ++i) {
            this.descriptorsMap.put(regulars[i].getName(), regulars[i]);
            this.properties[i] = new DynaProperty(regulars[i].getName(), regulars[i].getPropertyType());
            this.propertiesMap.put(this.properties[i].getName(), this.properties[i]);
        }
        int j = regulars.length;
        for (final String name : mappeds.keySet()) {
            final PropertyDescriptor descriptor = mappeds.get(name);
            this.properties[j] = new DynaProperty(descriptor.getName(), Map.class);
            this.propertiesMap.put(this.properties[j].getName(), this.properties[j]);
            ++j;
        }
    }
    
    static {
        WrapDynaClass.dynaClasses = new HashMap();
    }
}
