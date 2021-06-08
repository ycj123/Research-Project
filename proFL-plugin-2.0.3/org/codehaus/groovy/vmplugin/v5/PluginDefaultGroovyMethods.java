// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.vmplugin.v5;

import groovy.lang.EmptyRange;
import groovy.lang.Range;
import groovy.lang.IntRange;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.codehaus.groovy.runtime.DefaultGroovyMethodsSupport;

public class PluginDefaultGroovyMethods extends DefaultGroovyMethodsSupport
{
    private static final Object[] NO_ARGS;
    
    public static Object next(final Enum self) {
        final Method[] methods = self.getClass().getMethods();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            if (method.getName().equals("next") && method.getParameterTypes().length == 0) {
                return InvokerHelper.invokeMethod(self, "next", PluginDefaultGroovyMethods.NO_ARGS);
            }
        }
        final Object[] values = (Object[])InvokerHelper.invokeStaticMethod(self.getClass(), "values", PluginDefaultGroovyMethods.NO_ARGS);
        final int index = Arrays.asList(values).indexOf(self);
        return values[(index < values.length - 1) ? (index + 1) : 0];
    }
    
    public static Object previous(final Enum self) {
        final Method[] methods = self.getClass().getMethods();
        for (int i = 0; i < methods.length; ++i) {
            final Method method = methods[i];
            if (method.getName().equals("previous") && method.getParameterTypes().length == 0) {
                return InvokerHelper.invokeMethod(self, "previous", PluginDefaultGroovyMethods.NO_ARGS);
            }
        }
        final Object[] values = (Object[])InvokerHelper.invokeStaticMethod(self.getClass(), "values", PluginDefaultGroovyMethods.NO_ARGS);
        final int index = Arrays.asList(values).indexOf(self);
        return values[(index > 0) ? (index - 1) : (values.length - 1)];
    }
    
    public static int size(final StringBuilder builder) {
        return builder.length();
    }
    
    public static StringBuilder leftShift(final StringBuilder self, final Object value) {
        if (value instanceof CharSequence) {
            return self.append((CharSequence)value);
        }
        return self.append(value);
    }
    
    public static void putAt(final StringBuilder self, final IntRange range, final Object value) {
        final RangeInfo info = DefaultGroovyMethodsSupport.subListBorders(self.length(), range);
        self.replace(info.from, info.to, value.toString());
    }
    
    public static void putAt(final StringBuilder self, final EmptyRange range, final Object value) {
        final RangeInfo info = DefaultGroovyMethodsSupport.subListBorders(self.length(), range);
        self.replace(info.from, info.to, value.toString());
    }
    
    public static String plus(final StringBuilder self, final String value) {
        return (Object)self + value;
    }
    
    static {
        NO_ARGS = new Object[0];
    }
}
