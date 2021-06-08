// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.lang.reflect.Method;
import java.util.List;
import java.lang.reflect.Array;
import java.beans.IndexedPropertyDescriptor;
import java.util.HashMap;
import java.lang.reflect.InvocationTargetException;
import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.Map;
import java.beans.Introspector;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.collections.FastHashMap;

public class PropertyUtilsBean
{
    private FastHashMap descriptorsCache;
    private FastHashMap mappedDescriptorsCache;
    private Log log;
    
    protected static PropertyUtilsBean getInstance() {
        return BeanUtilsBean.getInstance().getPropertyUtils();
    }
    
    public PropertyUtilsBean() {
        this.descriptorsCache = null;
        this.mappedDescriptorsCache = null;
        this.log = LogFactory.getLog(PropertyUtils.class);
        (this.descriptorsCache = new FastHashMap()).setFast(true);
        (this.mappedDescriptorsCache = new FastHashMap()).setFast(true);
    }
    
    public void clearDescriptors() {
        this.descriptorsCache.clear();
        this.mappedDescriptorsCache.clear();
        Introspector.flushCaches();
    }
    
    public void copyProperties(final Object dest, final Object orig) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }
        if (orig instanceof DynaBean) {
            final DynaProperty[] origDescriptors = ((DynaBean)orig).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; ++i) {
                final String name = origDescriptors[i].getName();
                if (dest instanceof DynaBean) {
                    if (this.isWriteable(dest, name)) {
                        final Object value = ((DynaBean)orig).get(name);
                        ((DynaBean)dest).set(name, value);
                    }
                }
                else if (this.isWriteable(dest, name)) {
                    final Object value = ((DynaBean)orig).get(name);
                    this.setSimpleProperty(dest, name, value);
                }
            }
        }
        else if (orig instanceof Map) {
            for (final String name2 : ((Map)orig).keySet()) {
                if (dest instanceof DynaBean) {
                    if (!this.isWriteable(dest, name2)) {
                        continue;
                    }
                    final Object value2 = ((Map)orig).get(name2);
                    ((DynaBean)dest).set(name2, value2);
                }
                else {
                    if (!this.isWriteable(dest, name2)) {
                        continue;
                    }
                    final Object value2 = ((Map)orig).get(name2);
                    this.setSimpleProperty(dest, name2, value2);
                }
            }
        }
        else {
            final PropertyDescriptor[] origDescriptors2 = this.getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors2.length; ++i) {
                final String name = origDescriptors2[i].getName();
                if (this.isReadable(orig, name)) {
                    if (dest instanceof DynaBean) {
                        if (this.isWriteable(dest, name)) {
                            final Object value = this.getSimpleProperty(orig, name);
                            ((DynaBean)dest).set(name, value);
                        }
                    }
                    else if (this.isWriteable(dest, name)) {
                        final Object value = this.getSimpleProperty(orig, name);
                        this.setSimpleProperty(dest, name, value);
                    }
                }
            }
        }
    }
    
    public Map describe(final Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
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
            final PropertyDescriptor[] descriptors2 = this.getPropertyDescriptors(bean);
            for (int i = 0; i < descriptors2.length; ++i) {
                final String name = descriptors2[i].getName();
                if (descriptors2[i].getReadMethod() != null) {
                    description.put(name, this.getProperty(bean, name));
                }
            }
        }
        return description;
    }
    
    public Object getIndexedProperty(final Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        final int delim = name.indexOf(91);
        final int delim2 = name.indexOf(93);
        if (delim < 0 || delim2 <= delim) {
            throw new IllegalArgumentException("Invalid indexed property '" + name + "'");
        }
        int index = -1;
        try {
            final String subscript = name.substring(delim + 1, delim2);
            index = Integer.parseInt(subscript);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid indexed property '" + name + "'");
        }
        name = name.substring(0, delim);
        return this.getIndexedProperty(bean, name, index);
    }
    
    public Object getIndexedProperty(final Object bean, final String name, final int index) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (bean instanceof DynaBean) {
            final DynaProperty descriptor = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
            if (descriptor == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            return ((DynaBean)bean).get(name, index);
        }
        else {
            final PropertyDescriptor descriptor2 = this.getPropertyDescriptor(bean, name);
            if (descriptor2 == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            if (descriptor2 instanceof IndexedPropertyDescriptor) {
                final Method readMethod = ((IndexedPropertyDescriptor)descriptor2).getIndexedReadMethod();
                if (readMethod != null) {
                    final Object[] subscript = { new Integer(index) };
                    try {
                        return this.invokeMethod(readMethod, bean, subscript);
                    }
                    catch (InvocationTargetException e) {
                        if (e.getTargetException() instanceof ArrayIndexOutOfBoundsException) {
                            throw (ArrayIndexOutOfBoundsException)e.getTargetException();
                        }
                        throw e;
                    }
                }
            }
            final Method readMethod = this.getReadMethod(descriptor2);
            if (readMethod == null) {
                throw new NoSuchMethodException("Property '" + name + "' has no getter method");
            }
            final Object value = this.invokeMethod(readMethod, bean, new Object[0]);
            if (value.getClass().isArray()) {
                return Array.get(value, index);
            }
            if (!(value instanceof List)) {
                throw new IllegalArgumentException("Property '" + name + "' is not indexed");
            }
            return ((List)value).get(index);
        }
    }
    
    public Object getMappedProperty(final Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        final int delim = name.indexOf(40);
        final int delim2 = name.indexOf(41);
        if (delim < 0 || delim2 <= delim) {
            throw new IllegalArgumentException("Invalid mapped property '" + name + "'");
        }
        final String key = name.substring(delim + 1, delim2);
        name = name.substring(0, delim);
        return this.getMappedProperty(bean, name, key);
    }
    
    public Object getMappedProperty(final Object bean, final String name, final String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (key == null) {
            throw new IllegalArgumentException("No key specified");
        }
        if (bean instanceof DynaBean) {
            final DynaProperty descriptor = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
            if (descriptor == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            return ((DynaBean)bean).get(name, key);
        }
        else {
            Object result = null;
            final PropertyDescriptor descriptor2 = this.getPropertyDescriptor(bean, name);
            if (descriptor2 == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            if (descriptor2 instanceof MappedPropertyDescriptor) {
                final Method readMethod = ((MappedPropertyDescriptor)descriptor2).getMappedReadMethod();
                if (readMethod == null) {
                    throw new NoSuchMethodException("Property '" + name + "' has no mapped getter method");
                }
                final Object[] keyArray = { key };
                result = this.invokeMethod(readMethod, bean, keyArray);
            }
            else {
                final Method readMethod = descriptor2.getReadMethod();
                if (readMethod == null) {
                    throw new NoSuchMethodException("Property '" + name + "' has no mapped getter method");
                }
                final Object invokeResult = this.invokeMethod(readMethod, bean, new Object[0]);
                if (invokeResult instanceof Map) {
                    result = ((Map)invokeResult).get(key);
                }
            }
            return result;
        }
    }
    
    public FastHashMap getMappedPropertyDescriptors(final Class beanClass) {
        if (beanClass == null) {
            return null;
        }
        return (FastHashMap)this.mappedDescriptorsCache.get(beanClass);
    }
    
    public FastHashMap getMappedPropertyDescriptors(final Object bean) {
        if (bean == null) {
            return null;
        }
        return this.getMappedPropertyDescriptors(bean.getClass());
    }
    
    public Object getNestedProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        int indexOfINDEXED_DELIM = -1;
        int indexOfMAPPED_DELIM = -1;
        int indexOfMAPPED_DELIM2 = -1;
        int indexOfNESTED_DELIM = -1;
        while (true) {
            indexOfNESTED_DELIM = name.indexOf(46);
            indexOfMAPPED_DELIM = name.indexOf(40);
            indexOfMAPPED_DELIM2 = name.indexOf(41);
            if (indexOfMAPPED_DELIM2 >= 0 && indexOfMAPPED_DELIM >= 0 && (indexOfNESTED_DELIM < 0 || indexOfNESTED_DELIM > indexOfMAPPED_DELIM)) {
                indexOfNESTED_DELIM = name.indexOf(46, indexOfMAPPED_DELIM2);
            }
            else {
                indexOfNESTED_DELIM = name.indexOf(46);
            }
            if (indexOfNESTED_DELIM < 0) {
                indexOfINDEXED_DELIM = name.indexOf(91);
                indexOfMAPPED_DELIM = name.indexOf(40);
                if (bean instanceof Map) {
                    bean = ((Map)bean).get(name);
                }
                else if (indexOfMAPPED_DELIM >= 0) {
                    bean = this.getMappedProperty(bean, name);
                }
                else if (indexOfINDEXED_DELIM >= 0) {
                    bean = this.getIndexedProperty(bean, name);
                }
                else {
                    bean = this.getSimpleProperty(bean, name);
                }
                return bean;
            }
            final String next = name.substring(0, indexOfNESTED_DELIM);
            indexOfINDEXED_DELIM = next.indexOf(91);
            indexOfMAPPED_DELIM = next.indexOf(40);
            if (bean instanceof Map) {
                bean = ((Map)bean).get(next);
            }
            else if (indexOfMAPPED_DELIM >= 0) {
                bean = this.getMappedProperty(bean, next);
            }
            else if (indexOfINDEXED_DELIM >= 0) {
                bean = this.getIndexedProperty(bean, next);
            }
            else {
                bean = this.getSimpleProperty(bean, next);
            }
            if (bean == null) {
                throw new NestedNullException("Null property value for '" + name.substring(0, indexOfNESTED_DELIM) + "'");
            }
            name = name.substring(indexOfNESTED_DELIM + 1);
        }
    }
    
    public Object getProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return this.getNestedProperty(bean, name);
    }
    
    public PropertyDescriptor getPropertyDescriptor(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        while (true) {
            final int period = this.findNextNestedIndex(name);
            if (period < 0) {
                int left = name.indexOf(91);
                if (left >= 0) {
                    name = name.substring(0, left);
                }
                left = name.indexOf(40);
                if (left >= 0) {
                    name = name.substring(0, left);
                }
                if (bean == null || name == null) {
                    return null;
                }
                final PropertyDescriptor[] descriptors = this.getPropertyDescriptors(bean);
                if (descriptors != null) {
                    for (int i = 0; i < descriptors.length; ++i) {
                        if (name.equals(descriptors[i].getName())) {
                            return descriptors[i];
                        }
                    }
                }
                PropertyDescriptor result = null;
                FastHashMap mappedDescriptors = this.getMappedPropertyDescriptors(bean);
                if (mappedDescriptors == null) {
                    mappedDescriptors = new FastHashMap();
                    mappedDescriptors.setFast(true);
                    this.mappedDescriptorsCache.put(bean.getClass(), mappedDescriptors);
                }
                result = (PropertyDescriptor)mappedDescriptors.get(name);
                if (result == null) {
                    try {
                        result = new MappedPropertyDescriptor(name, bean.getClass());
                    }
                    catch (IntrospectionException ie) {}
                    if (result != null) {
                        mappedDescriptors.put(name, result);
                    }
                }
                return result;
            }
            else {
                final String next = name.substring(0, period);
                final int indexOfINDEXED_DELIM = next.indexOf(91);
                final int indexOfMAPPED_DELIM = next.indexOf(40);
                if (indexOfMAPPED_DELIM >= 0 && (indexOfINDEXED_DELIM < 0 || indexOfMAPPED_DELIM < indexOfINDEXED_DELIM)) {
                    bean = this.getMappedProperty(bean, next);
                }
                else if (indexOfINDEXED_DELIM >= 0) {
                    bean = this.getIndexedProperty(bean, next);
                }
                else {
                    bean = this.getSimpleProperty(bean, next);
                }
                if (bean == null) {
                    throw new IllegalArgumentException("Null property value for '" + name.substring(0, period) + "'");
                }
                name = name.substring(period + 1);
            }
        }
    }
    
    private int findNextNestedIndex(final String expression) {
        int bracketCount = 0;
        for (int i = 0, size = expression.length(); i < size; ++i) {
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
                    ++bracketCount;
                    break;
                }
                case ')':
                case ']': {
                    --bracketCount;
                    break;
                }
            }
        }
        return -1;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors(final Class beanClass) {
        if (beanClass == null) {
            throw new IllegalArgumentException("No bean class specified");
        }
        PropertyDescriptor[] descriptors = null;
        descriptors = (PropertyDescriptor[])this.descriptorsCache.get(beanClass);
        if (descriptors != null) {
            return descriptors;
        }
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(beanClass);
        }
        catch (IntrospectionException e) {
            return new PropertyDescriptor[0];
        }
        descriptors = beanInfo.getPropertyDescriptors();
        if (descriptors == null) {
            descriptors = new PropertyDescriptor[0];
        }
        this.descriptorsCache.put(beanClass, descriptors);
        return descriptors;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors(final Object bean) {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        return this.getPropertyDescriptors(bean.getClass());
    }
    
    public Class getPropertyEditorClass(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        final PropertyDescriptor descriptor = this.getPropertyDescriptor(bean, name);
        if (descriptor != null) {
            return descriptor.getPropertyEditorClass();
        }
        return null;
    }
    
    public Class getPropertyType(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (bean instanceof DynaBean) {
            final DynaProperty descriptor = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
            if (descriptor == null) {
                return null;
            }
            final Class type = descriptor.getType();
            if (type == null) {
                return null;
            }
            if (type.isArray()) {
                return type.getComponentType();
            }
            return type;
        }
        else {
            final PropertyDescriptor descriptor2 = this.getPropertyDescriptor(bean, name);
            if (descriptor2 == null) {
                return null;
            }
            if (descriptor2 instanceof IndexedPropertyDescriptor) {
                return ((IndexedPropertyDescriptor)descriptor2).getIndexedPropertyType();
            }
            if (descriptor2 instanceof MappedPropertyDescriptor) {
                return ((MappedPropertyDescriptor)descriptor2).getMappedPropertyType();
            }
            return descriptor2.getPropertyType();
        }
    }
    
    public Method getReadMethod(final PropertyDescriptor descriptor) {
        return MethodUtils.getAccessibleMethod(descriptor.getReadMethod());
    }
    
    public Object getSimpleProperty(final Object bean, final String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (name.indexOf(46) >= 0) {
            throw new IllegalArgumentException("Nested property names are not allowed");
        }
        if (name.indexOf(91) >= 0) {
            throw new IllegalArgumentException("Indexed property names are not allowed");
        }
        if (name.indexOf(40) >= 0) {
            throw new IllegalArgumentException("Mapped property names are not allowed");
        }
        if (bean instanceof DynaBean) {
            final DynaProperty descriptor = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
            if (descriptor == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            return ((DynaBean)bean).get(name);
        }
        else {
            final PropertyDescriptor descriptor2 = this.getPropertyDescriptor(bean, name);
            if (descriptor2 == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            final Method readMethod = this.getReadMethod(descriptor2);
            if (readMethod == null) {
                throw new NoSuchMethodException("Property '" + name + "' has no getter method");
            }
            final Object value = this.invokeMethod(readMethod, bean, new Object[0]);
            return value;
        }
    }
    
    public Method getWriteMethod(final PropertyDescriptor descriptor) {
        return MethodUtils.getAccessibleMethod(descriptor.getWriteMethod());
    }
    
    public boolean isReadable(final Object bean, final String name) {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (bean instanceof DynaBean) {
            return ((DynaBean)bean).getDynaClass().getDynaProperty(name) != null;
        }
        try {
            final PropertyDescriptor desc = this.getPropertyDescriptor(bean, name);
            if (desc != null) {
                Method readMethod = desc.getReadMethod();
                if (readMethod == null && desc instanceof IndexedPropertyDescriptor) {
                    readMethod = ((IndexedPropertyDescriptor)desc).getIndexedReadMethod();
                }
                return readMethod != null;
            }
            return false;
        }
        catch (IllegalAccessException e) {
            return false;
        }
        catch (InvocationTargetException e2) {
            return false;
        }
        catch (NoSuchMethodException e3) {
            return false;
        }
    }
    
    public boolean isWriteable(final Object bean, final String name) {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (bean instanceof DynaBean) {
            return ((DynaBean)bean).getDynaClass().getDynaProperty(name) != null;
        }
        try {
            final PropertyDescriptor desc = this.getPropertyDescriptor(bean, name);
            if (desc != null) {
                Method writeMethod = desc.getWriteMethod();
                if (writeMethod == null && desc instanceof IndexedPropertyDescriptor) {
                    writeMethod = ((IndexedPropertyDescriptor)desc).getIndexedWriteMethod();
                }
                return writeMethod != null;
            }
            return false;
        }
        catch (IllegalAccessException e) {
            return false;
        }
        catch (InvocationTargetException e2) {
            return false;
        }
        catch (NoSuchMethodException e3) {
            return false;
        }
    }
    
    public void setIndexedProperty(final Object bean, String name, final Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        final int delim = name.indexOf(91);
        final int delim2 = name.indexOf(93);
        if (delim < 0 || delim2 <= delim) {
            throw new IllegalArgumentException("Invalid indexed property '" + name + "'");
        }
        int index = -1;
        try {
            final String subscript = name.substring(delim + 1, delim2);
            index = Integer.parseInt(subscript);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid indexed property '" + name + "'");
        }
        name = name.substring(0, delim);
        this.setIndexedProperty(bean, name, index, value);
    }
    
    public void setIndexedProperty(final Object bean, final String name, final int index, final Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (bean instanceof DynaBean) {
            final DynaProperty descriptor = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
            if (descriptor == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            ((DynaBean)bean).set(name, index, value);
        }
        else {
            final PropertyDescriptor descriptor2 = this.getPropertyDescriptor(bean, name);
            if (descriptor2 == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            if (descriptor2 instanceof IndexedPropertyDescriptor) {
                final Method writeMethod = ((IndexedPropertyDescriptor)descriptor2).getIndexedWriteMethod();
                if (writeMethod != null) {
                    final Object[] subscript = { new Integer(index), value };
                    try {
                        if (this.log.isTraceEnabled()) {
                            final String valueClassName = (value == null) ? "<null>" : value.getClass().getName();
                            this.log.trace("setSimpleProperty: Invoking method " + writeMethod + " with index=" + index + ", value=" + value + " (class " + valueClassName + ")");
                        }
                        this.invokeMethod(writeMethod, bean, subscript);
                    }
                    catch (InvocationTargetException e) {
                        if (e.getTargetException() instanceof ArrayIndexOutOfBoundsException) {
                            throw (ArrayIndexOutOfBoundsException)e.getTargetException();
                        }
                        throw e;
                    }
                    return;
                }
            }
            final Method readMethod = descriptor2.getReadMethod();
            if (readMethod == null) {
                throw new NoSuchMethodException("Property '" + name + "' has no getter method");
            }
            final Object array = this.invokeMethod(readMethod, bean, new Object[0]);
            if (!array.getClass().isArray()) {
                if (!(array instanceof List)) {
                    throw new IllegalArgumentException("Property '" + name + "' is not indexed");
                }
                ((List)array).set(index, value);
            }
            else {
                Array.set(array, index, value);
            }
        }
    }
    
    public void setMappedProperty(final Object bean, String name, final Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        final int delim = name.indexOf(40);
        final int delim2 = name.indexOf(41);
        if (delim < 0 || delim2 <= delim) {
            throw new IllegalArgumentException("Invalid mapped property '" + name + "'");
        }
        final String key = name.substring(delim + 1, delim2);
        name = name.substring(0, delim);
        this.setMappedProperty(bean, name, key, value);
    }
    
    public void setMappedProperty(final Object bean, final String name, final String key, final Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (key == null) {
            throw new IllegalArgumentException("No key specified");
        }
        if (bean instanceof DynaBean) {
            final DynaProperty descriptor = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
            if (descriptor == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            ((DynaBean)bean).set(name, key, value);
        }
        else {
            final PropertyDescriptor descriptor2 = this.getPropertyDescriptor(bean, name);
            if (descriptor2 == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            if (descriptor2 instanceof MappedPropertyDescriptor) {
                final Method mappedWriteMethod = ((MappedPropertyDescriptor)descriptor2).getMappedWriteMethod();
                if (mappedWriteMethod == null) {
                    throw new NoSuchMethodException("Property '" + name + "' has no mapped setter method");
                }
                final Object[] params = { key, value };
                if (this.log.isTraceEnabled()) {
                    final String valueClassName = (value == null) ? "<null>" : value.getClass().getName();
                    this.log.trace("setSimpleProperty: Invoking method " + mappedWriteMethod + " with key=" + key + ", value=" + value + " (class " + valueClassName + ")");
                }
                this.invokeMethod(mappedWriteMethod, bean, params);
            }
            else {
                final Method readMethod = descriptor2.getReadMethod();
                if (readMethod == null) {
                    throw new NoSuchMethodException("Property '" + name + "' has no mapped getter method");
                }
                final Object invokeResult = this.invokeMethod(readMethod, bean, new Object[0]);
                if (invokeResult instanceof Map) {
                    ((Map)invokeResult).put(key, value);
                }
            }
        }
    }
    
    public void setNestedProperty(Object bean, String name, final Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        int indexOfINDEXED_DELIM = -1;
        int indexOfMAPPED_DELIM = -1;
        while (true) {
            final int delim = name.indexOf(46);
            if (delim < 0) {
                indexOfINDEXED_DELIM = name.indexOf(91);
                indexOfMAPPED_DELIM = name.indexOf(40);
                if (bean instanceof Map) {
                    final PropertyDescriptor descriptor = this.getPropertyDescriptor(bean, name);
                    if (descriptor == null) {
                        ((Map)bean).put(name, value);
                    }
                    else {
                        this.setSimpleProperty(bean, name, value);
                    }
                }
                else if (indexOfMAPPED_DELIM >= 0) {
                    this.setMappedProperty(bean, name, value);
                }
                else if (indexOfINDEXED_DELIM >= 0) {
                    this.setIndexedProperty(bean, name, value);
                }
                else {
                    this.setSimpleProperty(bean, name, value);
                }
                return;
            }
            final String next = name.substring(0, delim);
            indexOfINDEXED_DELIM = next.indexOf(91);
            indexOfMAPPED_DELIM = next.indexOf(40);
            if (bean instanceof Map) {
                bean = ((Map)bean).get(next);
            }
            else if (indexOfMAPPED_DELIM >= 0) {
                bean = this.getMappedProperty(bean, next);
            }
            else if (indexOfINDEXED_DELIM >= 0) {
                bean = this.getIndexedProperty(bean, next);
            }
            else {
                bean = this.getSimpleProperty(bean, next);
            }
            if (bean == null) {
                throw new IllegalArgumentException("Null property value for '" + name.substring(0, delim) + "'");
            }
            name = name.substring(delim + 1);
        }
    }
    
    public void setProperty(final Object bean, final String name, final Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        this.setNestedProperty(bean, name, value);
    }
    
    public void setSimpleProperty(final Object bean, final String name, final Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (bean == null) {
            throw new IllegalArgumentException("No bean specified");
        }
        if (name == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (name.indexOf(46) >= 0) {
            throw new IllegalArgumentException("Nested property names are not allowed");
        }
        if (name.indexOf(91) >= 0) {
            throw new IllegalArgumentException("Indexed property names are not allowed");
        }
        if (name.indexOf(40) >= 0) {
            throw new IllegalArgumentException("Mapped property names are not allowed");
        }
        if (bean instanceof DynaBean) {
            final DynaProperty descriptor = ((DynaBean)bean).getDynaClass().getDynaProperty(name);
            if (descriptor == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            ((DynaBean)bean).set(name, value);
        }
        else {
            final PropertyDescriptor descriptor2 = this.getPropertyDescriptor(bean, name);
            if (descriptor2 == null) {
                throw new NoSuchMethodException("Unknown property '" + name + "'");
            }
            final Method writeMethod = this.getWriteMethod(descriptor2);
            if (writeMethod == null) {
                throw new NoSuchMethodException("Property '" + name + "' has no setter method");
            }
            final Object[] values = { value };
            if (this.log.isTraceEnabled()) {
                final String valueClassName = (value == null) ? "<null>" : value.getClass().getName();
                this.log.trace("setSimpleProperty: Invoking method " + writeMethod + " with value " + value + " (class " + valueClassName + ")");
            }
            this.invokeMethod(writeMethod, bean, values);
        }
    }
    
    private Object invokeMethod(final Method method, final Object bean, final Object[] values) throws IllegalAccessException, InvocationTargetException {
        try {
            return method.invoke(bean, values);
        }
        catch (IllegalArgumentException e) {
            this.log.error("Method invocation failed.", e);
            throw new IllegalArgumentException("Cannot invoke " + method.getDeclaringClass().getName() + "." + method.getName() + " - " + e.getMessage());
        }
    }
}
