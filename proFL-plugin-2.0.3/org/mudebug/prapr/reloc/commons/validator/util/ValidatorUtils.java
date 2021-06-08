// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.validator.util;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import org.mudebug.prapr.reloc.commons.validator.Var;
import org.mudebug.prapr.reloc.commons.validator.Arg;
import org.mudebug.prapr.reloc.commons.validator.Msg;
import org.mudebug.prapr.reloc.commons.collections.FastHashMap;
import java.util.Collection;
import java.lang.reflect.InvocationTargetException;
import org.mudebug.prapr.reloc.commons.beanutils.PropertyUtils;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class ValidatorUtils
{
    private static final Log log;
    
    public static String replace(String value, final String key, final String replaceValue) {
        if (value == null || key == null || replaceValue == null) {
            return value;
        }
        final int pos = value.indexOf(key);
        if (pos < 0) {
            return value;
        }
        final int length = value.length();
        final int start = pos;
        final int end = pos + key.length();
        if (length == key.length()) {
            value = replaceValue;
        }
        else if (end == length) {
            value = value.substring(0, start) + replaceValue;
        }
        else {
            value = value.substring(0, start) + replaceValue + replace(value.substring(end), key, replaceValue);
        }
        return value;
    }
    
    public static String getValueAsString(final Object bean, final String property) {
        Object value = null;
        try {
            value = PropertyUtils.getProperty(bean, property);
        }
        catch (IllegalAccessException e) {
            ValidatorUtils.log.error(e.getMessage(), e);
        }
        catch (InvocationTargetException e2) {
            ValidatorUtils.log.error(e2.getMessage(), e2);
        }
        catch (NoSuchMethodException e3) {
            ValidatorUtils.log.error(e3.getMessage(), e3);
        }
        if (value == null) {
            return null;
        }
        if (value instanceof String[]) {
            return (((String[])value).length > 0) ? value.toString() : "";
        }
        if (value instanceof Collection) {
            return ((Collection)value).isEmpty() ? "" : value.toString();
        }
        return value.toString();
    }
    
    public static FastHashMap copyFastHashMap(final FastHashMap map) {
        final FastHashMap results = new FastHashMap();
        for (final String key : map.keySet()) {
            final Object value = map.get(key);
            if (value instanceof Msg) {
                results.put(key, ((Msg)value).clone());
            }
            else if (value instanceof Arg) {
                results.put(key, ((Arg)value).clone());
            }
            else if (value instanceof Var) {
                results.put(key, ((Var)value).clone());
            }
            else {
                results.put(key, value);
            }
        }
        results.setFast(true);
        return results;
    }
    
    public static Map copyMap(final Map map) {
        final Map results = new HashMap();
        for (final String key : map.keySet()) {
            final Object value = map.get(key);
            if (value instanceof Msg) {
                results.put(key, ((Msg)value).clone());
            }
            else if (value instanceof Arg) {
                results.put(key, ((Arg)value).clone());
            }
            else if (value instanceof Var) {
                results.put(key, ((Var)value).clone());
            }
            else {
                results.put(key, value);
            }
        }
        return results;
    }
    
    static {
        log = LogFactory.getLog(ValidatorUtils.class);
    }
}
