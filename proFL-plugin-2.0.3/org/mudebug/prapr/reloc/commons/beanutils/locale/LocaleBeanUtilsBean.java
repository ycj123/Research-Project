// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.locale;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.beanutils.ConvertUtils;
import java.beans.PropertyDescriptor;
import org.mudebug.prapr.reloc.commons.beanutils.DynaProperty;
import org.mudebug.prapr.reloc.commons.beanutils.DynaClass;
import java.beans.IndexedPropertyDescriptor;
import org.mudebug.prapr.reloc.commons.beanutils.MappedPropertyDescriptor;
import org.mudebug.prapr.reloc.commons.beanutils.DynaBean;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import org.mudebug.prapr.reloc.commons.beanutils.PropertyUtilsBean;
import org.mudebug.prapr.reloc.commons.beanutils.ConvertUtilsBean;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.beanutils.ContextClassLoaderLocal;
import org.mudebug.prapr.reloc.commons.beanutils.BeanUtilsBean;

public class LocaleBeanUtilsBean extends BeanUtilsBean
{
    private static final ContextClassLoaderLocal localeBeansByClassLoader;
    private static Log log;
    private LocaleConvertUtilsBean localeConvertUtils;
    
    public static synchronized LocaleBeanUtilsBean getLocaleBeanUtilsInstance() {
        return (LocaleBeanUtilsBean)LocaleBeanUtilsBean.localeBeansByClassLoader.get();
    }
    
    public static synchronized void setInstance(final LocaleBeanUtilsBean newInstance) {
        LocaleBeanUtilsBean.localeBeansByClassLoader.set(newInstance);
    }
    
    public LocaleBeanUtilsBean() {
        this.localeConvertUtils = new LocaleConvertUtilsBean();
    }
    
    public LocaleBeanUtilsBean(final LocaleConvertUtilsBean localeConvertUtils, final ConvertUtilsBean convertUtilsBean, final PropertyUtilsBean propertyUtilsBean) {
        super(convertUtilsBean, propertyUtilsBean);
        this.localeConvertUtils = localeConvertUtils;
    }
    
    public LocaleBeanUtilsBean(final LocaleConvertUtilsBean localeConvertUtils) {
        this.localeConvertUtils = localeConvertUtils;
    }
    
    public LocaleConvertUtilsBean getLocaleConvertUtils() {
        return this.localeConvertUtils;
    }
    
    public Locale getDefaultLocale() {
        return this.getLocaleConvertUtils().getDefaultLocale();
    }
    
    public void setDefaultLocale(final Locale locale) {
        this.getLocaleConvertUtils().setDefaultLocale(locale);
    }
    
    public boolean getApplyLocalized() {
        return this.getLocaleConvertUtils().getApplyLocalized();
    }
    
    public void setApplyLocalized(final boolean newApplyLocalized) {
        this.getLocaleConvertUtils().setApplyLocalized(newApplyLocalized);
    }
    
    public String getIndexedProperty(final Object bean, final String name, final String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getIndexedProperty(bean, name);
        return this.getLocaleConvertUtils().convert(value, pattern);
    }
    
    public String getIndexedProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.getIndexedProperty(bean, name, null);
    }
    
    public String getIndexedProperty(final Object bean, final String name, final int index, final String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getIndexedProperty(bean, name, index);
        return this.getLocaleConvertUtils().convert(value, pattern);
    }
    
    public String getIndexedProperty(final Object bean, final String name, final int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.getIndexedProperty(bean, name, index, null);
    }
    
    public String getSimpleProperty(final Object bean, final String name, final String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getSimpleProperty(bean, name);
        return this.getLocaleConvertUtils().convert(value, pattern);
    }
    
    public String getSimpleProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.getSimpleProperty(bean, name, null);
    }
    
    public String getMappedProperty(final Object bean, final String name, final String key, final String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getMappedProperty(bean, name, key);
        return this.getLocaleConvertUtils().convert(value, pattern);
    }
    
    public String getMappedProperty(final Object bean, final String name, final String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.getMappedProperty(bean, name, key, null);
    }
    
    public String getMappedPropertyLocale(final Object bean, final String name, final String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getMappedProperty(bean, name);
        return this.getLocaleConvertUtils().convert(value, pattern);
    }
    
    public String getMappedProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.getMappedPropertyLocale(bean, name, null);
    }
    
    public String getNestedProperty(final Object bean, final String name, final String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getNestedProperty(bean, name);
        return this.getLocaleConvertUtils().convert(value, pattern);
    }
    
    public String getNestedProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.getNestedProperty(bean, name, null);
    }
    
    public String getProperty(final Object bean, final String name, final String pattern) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.getNestedProperty(bean, name, pattern);
    }
    
    public String getProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.getNestedProperty(bean, name);
    }
    
    public void setProperty(final Object bean, final String name, final Object value) throws IllegalAccessException, InvocationTargetException {
        this.setProperty(bean, name, value, null);
    }
    
    public void setProperty(final Object bean, final String name, final Object value, final String pattern) throws IllegalAccessException, InvocationTargetException {
        if (LocaleBeanUtilsBean.log.isTraceEnabled()) {
            final StringBuffer sb = new StringBuffer("  setProperty(");
            sb.append(bean);
            sb.append(", ");
            sb.append(name);
            sb.append(", ");
            if (value == null) {
                sb.append("<NULL>");
            }
            else if (value instanceof String) {
                sb.append((String)value);
            }
            else if (value instanceof String[]) {
                final String[] values = (String[])value;
                sb.append('[');
                for (int i = 0; i < values.length; ++i) {
                    if (i > 0) {
                        sb.append(',');
                    }
                    sb.append(values[i]);
                }
                sb.append(']');
            }
            else {
                sb.append(value.toString());
            }
            sb.append(')');
            LocaleBeanUtilsBean.log.trace(sb.toString());
        }
        final Descriptor propInfo = this.calculate(bean, name);
        if (propInfo != null) {
            final Class type = this.definePropertyType(propInfo.getTarget(), name, propInfo.getPropName());
            if (type != null) {
                final Object newValue = this.convert(type, propInfo.getIndex(), value, pattern);
                this.invokeSetter(propInfo.getTarget(), propInfo.getPropName(), propInfo.getKey(), propInfo.getIndex(), newValue);
            }
        }
    }
    
    protected Class definePropertyType(final Object target, final String name, final String propName) throws IllegalAccessException, InvocationTargetException {
        Class type = null;
        if (target instanceof DynaBean) {
            final DynaClass dynaClass = ((DynaBean)target).getDynaClass();
            final DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
            if (dynaProperty == null) {
                return null;
            }
            type = dynaProperty.getType();
        }
        else {
            PropertyDescriptor descriptor = null;
            try {
                descriptor = this.getPropertyUtils().getPropertyDescriptor(target, name);
                if (descriptor == null) {
                    return null;
                }
            }
            catch (NoSuchMethodException e) {
                return null;
            }
            if (descriptor instanceof MappedPropertyDescriptor) {
                type = ((MappedPropertyDescriptor)descriptor).getMappedPropertyType();
            }
            else if (descriptor instanceof IndexedPropertyDescriptor) {
                type = ((IndexedPropertyDescriptor)descriptor).getIndexedPropertyType();
            }
            else {
                type = descriptor.getPropertyType();
            }
        }
        return type;
    }
    
    protected Object convert(final Class type, final int index, final Object value, final String pattern) {
        if (LocaleBeanUtilsBean.log.isTraceEnabled()) {
            LocaleBeanUtilsBean.log.trace("Converting value '" + value + "' to type:" + type);
        }
        Object newValue = null;
        if (type.isArray() && index < 0) {
            if (value instanceof String) {
                final String[] values = { (String)value };
                newValue = this.getLocaleConvertUtils().convert(values, type, pattern);
            }
            else if (value instanceof String[]) {
                newValue = this.getLocaleConvertUtils().convert((String[])value, type, pattern);
            }
            else {
                newValue = value;
            }
        }
        else if (type.isArray()) {
            if (value instanceof String) {
                newValue = this.getLocaleConvertUtils().convert((String)value, type.getComponentType(), pattern);
            }
            else if (value instanceof String[]) {
                newValue = this.getLocaleConvertUtils().convert(((String[])value)[0], type.getComponentType(), pattern);
            }
            else {
                newValue = value;
            }
        }
        else if (value instanceof String) {
            newValue = this.getLocaleConvertUtils().convert((String)value, type, pattern);
        }
        else if (value instanceof String[]) {
            newValue = this.getLocaleConvertUtils().convert(((String[])value)[0], type, pattern);
        }
        else {
            newValue = value;
        }
        return newValue;
    }
    
    protected Object convert(final Class type, final int index, final Object value) {
        Object newValue = null;
        if (type.isArray() && index < 0) {
            if (value instanceof String) {
                final String[] values = { (String)value };
                newValue = ConvertUtils.convert(values, type);
            }
            else if (value instanceof String[]) {
                newValue = ConvertUtils.convert((String[])value, type);
            }
            else {
                newValue = value;
            }
        }
        else if (type.isArray()) {
            if (value instanceof String) {
                newValue = ConvertUtils.convert((String)value, type.getComponentType());
            }
            else if (value instanceof String[]) {
                newValue = ConvertUtils.convert(((String[])value)[0], type.getComponentType());
            }
            else {
                newValue = value;
            }
        }
        else if (value instanceof String) {
            newValue = ConvertUtils.convert((String)value, type);
        }
        else if (value instanceof String[]) {
            newValue = ConvertUtils.convert(((String[])value)[0], type);
        }
        else {
            newValue = value;
        }
        return newValue;
    }
    
    protected void invokeSetter(final Object target, final String propName, final String key, final int index, final Object newValue) throws IllegalAccessException, InvocationTargetException {
        try {
            if (index >= 0) {
                this.getPropertyUtils().setIndexedProperty(target, propName, index, newValue);
            }
            else if (key != null) {
                this.getPropertyUtils().setMappedProperty(target, propName, key, newValue);
            }
            else {
                this.getPropertyUtils().setProperty(target, propName, newValue);
            }
        }
        catch (NoSuchMethodException e) {
            throw new InvocationTargetException(e, "Cannot set " + propName);
        }
    }
    
    protected Descriptor calculate(final Object bean, String name) throws IllegalAccessException, InvocationTargetException {
        String propName = null;
        int index = -1;
        String key = null;
        Object target = bean;
        final int delim = name.lastIndexOf(46);
        if (delim >= 0) {
            try {
                target = this.getPropertyUtils().getProperty(bean, name.substring(0, delim));
            }
            catch (NoSuchMethodException e) {
                return null;
            }
            name = name.substring(delim + 1);
            if (LocaleBeanUtilsBean.log.isTraceEnabled()) {
                LocaleBeanUtilsBean.log.trace("    Target bean = " + target);
                LocaleBeanUtilsBean.log.trace("    Target name = " + name);
            }
        }
        propName = name;
        final int i = propName.indexOf(91);
        if (i >= 0) {
            final int k = propName.indexOf(93);
            try {
                index = Integer.parseInt(propName.substring(i + 1, k));
            }
            catch (NumberFormatException e2) {}
            propName = propName.substring(0, i);
        }
        final int j = propName.indexOf(40);
        if (j >= 0) {
            final int l = propName.indexOf(41);
            try {
                key = propName.substring(j + 1, l);
            }
            catch (IndexOutOfBoundsException e3) {}
            propName = propName.substring(0, j);
        }
        return new Descriptor(target, name, propName, key, index);
    }
    
    static {
        localeBeansByClassLoader = new ContextClassLoaderLocal() {
            protected Object initialValue() {
                return new LocaleBeanUtilsBean();
            }
        };
        LocaleBeanUtilsBean.log = LogFactory.getLog(LocaleBeanUtilsBean.class);
    }
    
    protected class Descriptor
    {
        private int index;
        private String name;
        private String propName;
        private String key;
        private Object target;
        
        public Descriptor(final Object target, final String name, final String propName, final String key, final int index) {
            this.index = -1;
            this.setTarget(target);
            this.setName(name);
            this.setPropName(propName);
            this.setKey(key);
            this.setIndex(index);
        }
        
        public Object getTarget() {
            return this.target;
        }
        
        public void setTarget(final Object target) {
            this.target = target;
        }
        
        public String getKey() {
            return this.key;
        }
        
        public void setKey(final String key) {
            this.key = key;
        }
        
        public int getIndex() {
            return this.index;
        }
        
        public void setIndex(final int index) {
            this.index = index;
        }
        
        public String getName() {
            return this.name;
        }
        
        public void setName(final String name) {
            this.name = name;
        }
        
        public String getPropName() {
            return this.propName;
        }
        
        public void setPropName(final String propName) {
            this.propName = propName;
        }
    }
}
