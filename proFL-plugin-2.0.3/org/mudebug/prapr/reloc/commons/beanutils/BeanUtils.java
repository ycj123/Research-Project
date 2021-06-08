// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.util.Map;
import java.lang.reflect.InvocationTargetException;
import org.mudebug.prapr.reloc.commons.collections.FastHashMap;

public class BeanUtils
{
    private static FastHashMap dummy;
    private static int debug;
    
    public static int getDebug() {
        return BeanUtils.debug;
    }
    
    public static void setDebug(final int newDebug) {
        BeanUtils.debug = newDebug;
    }
    
    public static Object cloneBean(final Object bean) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        return BeanUtilsBean.getInstance().cloneBean(bean);
    }
    
    public static void copyProperties(final Object dest, final Object orig) throws IllegalAccessException, InvocationTargetException {
        BeanUtilsBean.getInstance().copyProperties(dest, orig);
    }
    
    public static void copyProperty(final Object bean, final String name, final Object value) throws IllegalAccessException, InvocationTargetException {
        BeanUtilsBean.getInstance().copyProperty(bean, name, value);
    }
    
    public static Map describe(final Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BeanUtilsBean.getInstance().describe(bean);
    }
    
    public static String[] getArrayProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BeanUtilsBean.getInstance().getArrayProperty(bean, name);
    }
    
    public static String getIndexedProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BeanUtilsBean.getInstance().getIndexedProperty(bean, name);
    }
    
    public static String getIndexedProperty(final Object bean, final String name, final int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BeanUtilsBean.getInstance().getIndexedProperty(bean, name, index);
    }
    
    public static String getMappedProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BeanUtilsBean.getInstance().getMappedProperty(bean, name);
    }
    
    public static String getMappedProperty(final Object bean, final String name, final String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BeanUtilsBean.getInstance().getMappedProperty(bean, name, key);
    }
    
    public static String getNestedProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BeanUtilsBean.getInstance().getNestedProperty(bean, name);
    }
    
    public static String getProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BeanUtilsBean.getInstance().getProperty(bean, name);
    }
    
    public static String getSimpleProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BeanUtilsBean.getInstance().getSimpleProperty(bean, name);
    }
    
    public static void populate(final Object bean, final Map properties) throws IllegalAccessException, InvocationTargetException {
        BeanUtilsBean.getInstance().populate(bean, properties);
    }
    
    public static void setProperty(final Object bean, final String name, final Object value) throws IllegalAccessException, InvocationTargetException {
        BeanUtilsBean.getInstance().setProperty(bean, name, value);
    }
    
    static {
        BeanUtils.dummy = new FastHashMap();
        BeanUtils.debug = 0;
    }
}
