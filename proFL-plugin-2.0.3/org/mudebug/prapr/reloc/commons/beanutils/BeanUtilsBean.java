// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.beans.IndexedPropertyDescriptor;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class BeanUtilsBean
{
    private static final ContextClassLoaderLocal beansByClassLoader;
    private Log log;
    private ConvertUtilsBean convertUtilsBean;
    private PropertyUtilsBean propertyUtilsBean;
    
    public static synchronized BeanUtilsBean getInstance() {
        return (BeanUtilsBean)BeanUtilsBean.beansByClassLoader.get();
    }
    
    public static synchronized void setInstance(final BeanUtilsBean newInstance) {
        BeanUtilsBean.beansByClassLoader.set(newInstance);
    }
    
    public BeanUtilsBean() {
        this(new ConvertUtilsBean(), new PropertyUtilsBean());
    }
    
    public BeanUtilsBean(final ConvertUtilsBean convertUtilsBean, final PropertyUtilsBean propertyUtilsBean) {
        this.log = LogFactory.getLog(BeanUtils.class);
        this.convertUtilsBean = convertUtilsBean;
        this.propertyUtilsBean = propertyUtilsBean;
    }
    
    public Object cloneBean(final Object bean) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        if (this.log.isDebugEnabled()) {
            this.log.debug("Cloning bean: " + bean.getClass().getName());
        }
        final Class clazz = bean.getClass();
        Object newBean = null;
        if (bean instanceof DynaBean) {
            newBean = ((DynaBean)bean).getDynaClass().newInstance();
        }
        else {
            newBean = bean.getClass().newInstance();
        }
        this.getPropertyUtils().copyProperties(newBean, bean);
        return newBean;
    }
    
    public void copyProperties(final Object dest, final Object orig) throws IllegalAccessException, InvocationTargetException {
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
        }
        if (orig instanceof DynaBean) {
            final DynaProperty[] origDescriptors = ((DynaBean)orig).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; ++i) {
                final String name = origDescriptors[i].getName();
                if (this.getPropertyUtils().isWriteable(dest, name)) {
                    final Object value = ((DynaBean)orig).get(name);
                    this.copyProperty(dest, name, value);
                }
            }
        }
        else if (orig instanceof Map) {
            for (final String name2 : ((Map)orig).keySet()) {
                if (this.getPropertyUtils().isWriteable(dest, name2)) {
                    final Object value2 = ((Map)orig).get(name2);
                    this.copyProperty(dest, name2, value2);
                }
            }
        }
        else {
            final PropertyDescriptor[] origDescriptors2 = this.getPropertyUtils().getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors2.length; ++i) {
                final String name = origDescriptors2[i].getName();
                if (!"class".equals(name)) {
                    if (this.getPropertyUtils().isReadable(orig, name) && this.getPropertyUtils().isWriteable(dest, name)) {
                        try {
                            final Object value = this.getPropertyUtils().getSimpleProperty(orig, name);
                            this.copyProperty(dest, name, value);
                        }
                        catch (NoSuchMethodException e) {}
                    }
                }
            }
        }
    }
    
    public void copyProperty(final Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
        if (this.log.isTraceEnabled()) {
            final StringBuffer sb = new StringBuffer("  copyProperty(");
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
            this.log.trace(sb.toString());
        }
        Object target = bean;
        final int delim = name.lastIndexOf(46);
        if (delim >= 0) {
            try {
                target = this.getPropertyUtils().getProperty(bean, name.substring(0, delim));
            }
            catch (NoSuchMethodException e3) {
                return;
            }
            name = name.substring(delim + 1);
            if (this.log.isTraceEnabled()) {
                this.log.trace("    Target bean = " + target);
                this.log.trace("    Target name = " + name);
            }
        }
        String propName = null;
        Class type = null;
        int index = -1;
        String key = null;
        propName = name;
        final int j = propName.indexOf(91);
        if (j >= 0) {
            final int k = propName.indexOf(93);
            try {
                index = Integer.parseInt(propName.substring(j + 1, k));
            }
            catch (NumberFormatException e4) {}
            propName = propName.substring(0, j);
        }
        final int l = propName.indexOf(40);
        if (l >= 0) {
            final int m = propName.indexOf(41);
            try {
                key = propName.substring(l + 1, m);
            }
            catch (IndexOutOfBoundsException e5) {}
            propName = propName.substring(0, l);
        }
        if (target instanceof DynaBean) {
            final DynaClass dynaClass = ((DynaBean)target).getDynaClass();
            final DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
            if (dynaProperty == null) {
                return;
            }
            type = dynaProperty.getType();
        }
        else {
            PropertyDescriptor descriptor = null;
            try {
                descriptor = this.getPropertyUtils().getPropertyDescriptor(target, name);
                if (descriptor == null) {
                    return;
                }
            }
            catch (NoSuchMethodException e) {
                return;
            }
            type = descriptor.getPropertyType();
            if (type == null) {
                if (this.log.isTraceEnabled()) {
                    this.log.trace("    target type for property '" + propName + "' is null, so skipping ths setter");
                }
                return;
            }
        }
        if (this.log.isTraceEnabled()) {
            this.log.trace("    target propName=" + propName + ", type=" + type + ", index=" + index + ", key=" + key);
        }
        if (index >= 0) {
            final Converter converter = this.getConvertUtils().lookup(type.getComponentType());
            if (converter != null) {
                this.log.trace("        USING CONVERTER " + converter);
                value = converter.convert(type, value);
            }
            try {
                this.getPropertyUtils().setIndexedProperty(target, propName, index, value);
            }
            catch (NoSuchMethodException e) {
                throw new InvocationTargetException(e, "Cannot set " + propName);
            }
        }
        else if (key != null) {
            try {
                this.getPropertyUtils().setMappedProperty(target, propName, key, value);
            }
            catch (NoSuchMethodException e2) {
                throw new InvocationTargetException(e2, "Cannot set " + propName);
            }
        }
        else {
            final Converter converter = this.getConvertUtils().lookup(type);
            if (converter != null) {
                this.log.trace("        USING CONVERTER " + converter);
                value = converter.convert(type, value);
            }
            try {
                this.getPropertyUtils().setSimpleProperty(target, propName, value);
            }
            catch (NoSuchMethodException e) {
                throw new InvocationTargetException(e, "Cannot set " + propName);
            }
        }
    }
    
    public Map describe(final Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            return new HashMap();
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug("Describing bean: " + bean.getClass().getName());
        }
        final Map description = new HashMap();
        if (bean instanceof DynaBean) {
            final DynaProperty[] descriptors = ((DynaBean)bean).getDynaClass().getDynaProperties();
            for (int i = 0; i < descriptors.length; ++i) {
                final String name = descriptors[i].getName();
                description.put(name, this.getProperty(bean, name));
            }
        }
        else {
            final PropertyDescriptor[] descriptors2 = this.getPropertyUtils().getPropertyDescriptors(bean);
            for (int i = 0; i < descriptors2.length; ++i) {
                final String name = descriptors2[i].getName();
                if (descriptors2[i].getReadMethod() != null) {
                    description.put(name, this.getProperty(bean, name));
                }
            }
        }
        return description;
    }
    
    public String[] getArrayProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getProperty(bean, name);
        if (value == null) {
            return null;
        }
        if (value instanceof Collection) {
            final ArrayList values = new ArrayList();
            for (final Object item : (Collection)value) {
                if (item == null) {
                    values.add(null);
                }
                else {
                    values.add(this.getConvertUtils().convert(item));
                }
            }
            return values.toArray(new String[values.size()]);
        }
        if (value.getClass().isArray()) {
            final int n = Array.getLength(value);
            final String[] results = new String[n];
            for (int i = 0; i < n; ++i) {
                final Object item2 = Array.get(value, i);
                if (item2 == null) {
                    results[i] = null;
                }
                else {
                    results[i] = this.getConvertUtils().convert(item2);
                }
            }
            return results;
        }
        final String[] results2 = { value.toString() };
        return results2;
    }
    
    public String getIndexedProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getIndexedProperty(bean, name);
        return this.getConvertUtils().convert(value);
    }
    
    public String getIndexedProperty(final Object bean, final String name, final int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getIndexedProperty(bean, name, index);
        return this.getConvertUtils().convert(value);
    }
    
    public String getMappedProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getMappedProperty(bean, name);
        return this.getConvertUtils().convert(value);
    }
    
    public String getMappedProperty(final Object bean, final String name, final String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getMappedProperty(bean, name, key);
        return this.getConvertUtils().convert(value);
    }
    
    public String getNestedProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getNestedProperty(bean, name);
        return this.getConvertUtils().convert(value);
    }
    
    public String getProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.getNestedProperty(bean, name);
    }
    
    public String getSimpleProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final Object value = this.getPropertyUtils().getSimpleProperty(bean, name);
        return this.getConvertUtils().convert(value);
    }
    
    public void populate(final Object bean, final Map properties) throws IllegalAccessException, InvocationTargetException {
        if (bean == null || properties == null) {
            return;
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug("BeanUtils.populate(" + bean + ", " + properties + ")");
        }
        for (final String name : properties.keySet()) {
            if (name == null) {
                continue;
            }
            final Object value = properties.get(name);
            this.setProperty(bean, name, value);
        }
    }
    
    public void setProperty(final Object bean, String name, final Object value) throws IllegalAccessException, InvocationTargetException {
        if (this.log.isTraceEnabled()) {
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
            this.log.trace(sb.toString());
        }
        Object target = bean;
        final int delim = this.findLastNestedIndex(name);
        if (delim >= 0) {
            try {
                target = this.getPropertyUtils().getProperty(bean, name.substring(0, delim));
            }
            catch (NoSuchMethodException e2) {
                return;
            }
            name = name.substring(delim + 1);
            if (this.log.isTraceEnabled()) {
                this.log.trace("    Target bean = " + target);
                this.log.trace("    Target name = " + name);
            }
        }
        String propName = null;
        Class type = null;
        int index = -1;
        String key = null;
        propName = name;
        final int j = propName.indexOf(91);
        if (j >= 0) {
            final int k = propName.indexOf(93);
            try {
                index = Integer.parseInt(propName.substring(j + 1, k));
            }
            catch (NumberFormatException e3) {}
            propName = propName.substring(0, j);
        }
        final int l = propName.indexOf(40);
        if (l >= 0) {
            final int m = propName.indexOf(41);
            try {
                key = propName.substring(l + 1, m);
            }
            catch (IndexOutOfBoundsException e4) {}
            propName = propName.substring(0, l);
        }
        if (target instanceof DynaBean) {
            final DynaClass dynaClass = ((DynaBean)target).getDynaClass();
            final DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
            if (dynaProperty == null) {
                return;
            }
            type = dynaProperty.getType();
        }
        else {
            PropertyDescriptor descriptor = null;
            try {
                descriptor = this.getPropertyUtils().getPropertyDescriptor(target, name);
                if (descriptor == null) {
                    return;
                }
            }
            catch (NoSuchMethodException e) {
                return;
            }
            if (descriptor instanceof MappedPropertyDescriptor) {
                if (((MappedPropertyDescriptor)descriptor).getMappedWriteMethod() == null) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Skipping read-only property");
                    }
                    return;
                }
                type = ((MappedPropertyDescriptor)descriptor).getMappedPropertyType();
            }
            else if (descriptor instanceof IndexedPropertyDescriptor) {
                if (((IndexedPropertyDescriptor)descriptor).getIndexedWriteMethod() == null) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Skipping read-only property");
                    }
                    return;
                }
                type = ((IndexedPropertyDescriptor)descriptor).getIndexedPropertyType();
            }
            else {
                if (descriptor.getWriteMethod() == null) {
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("Skipping read-only property");
                    }
                    return;
                }
                type = descriptor.getPropertyType();
            }
        }
        Object newValue = null;
        if (type.isArray() && index < 0) {
            if (value == null) {
                final String[] values2 = { (String)value };
                newValue = this.getConvertUtils().convert(values2, type);
            }
            else if (value instanceof String) {
                final String[] values2 = { (String)value };
                newValue = this.getConvertUtils().convert(values2, type);
            }
            else if (value instanceof String[]) {
                newValue = this.getConvertUtils().convert((String[])value, type);
            }
            else {
                newValue = value;
            }
        }
        else if (type.isArray()) {
            if (value instanceof String) {
                newValue = this.getConvertUtils().convert((String)value, type.getComponentType());
            }
            else if (value instanceof String[]) {
                newValue = this.getConvertUtils().convert(((String[])value)[0], type.getComponentType());
            }
            else {
                newValue = value;
            }
        }
        else if (value instanceof String || value == null) {
            newValue = this.getConvertUtils().convert((String)value, type);
        }
        else if (value instanceof String[]) {
            newValue = this.getConvertUtils().convert(((String[])value)[0], type);
        }
        else if (this.getConvertUtils().lookup(value.getClass()) != null) {
            newValue = this.getConvertUtils().convert(value.toString(), type);
        }
        else {
            newValue = value;
        }
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
    
    private int findLastNestedIndex(final String expression) {
        int bracketCount = 0;
        for (int i = expression.length() - 1; i >= 0; --i) {
            final char at = expression.charAt(i);
            switch (at) {
                case '.': {
                    if (bracketCount < 1) {
                        return i;
                    }
                    break;
                }
                case '(':
                case '[': {
                    --bracketCount;
                    break;
                }
                case ')':
                case ']': {
                    ++bracketCount;
                    break;
                }
            }
        }
        return -1;
    }
    
    public ConvertUtilsBean getConvertUtils() {
        return this.convertUtilsBean;
    }
    
    public PropertyUtilsBean getPropertyUtils() {
        return this.propertyUtilsBean;
    }
    
    static {
        beansByClassLoader = new ContextClassLoaderLocal() {
            protected Object initialValue() {
                return new BeanUtilsBean();
            }
        };
    }
}
