// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import java.util.List;
import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;

public final class StringArrayConverter extends AbstractArrayConverter
{
    private static String[] model;
    private static int[] ints;
    
    public StringArrayConverter() {
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public StringArrayConverter(final Object defaultValue) {
        this.defaultValue = defaultValue;
        this.useDefault = true;
    }
    
    public Object convert(final Class type, final Object value) {
        if (value == null) {
            if (this.useDefault) {
                return this.defaultValue;
            }
            throw new ConversionException("No value specified");
        }
        else {
            if (StringArrayConverter.model.getClass() == value.getClass()) {
                return value;
            }
            if (StringArrayConverter.ints.getClass() == value.getClass()) {
                final int[] values = (int[])value;
                final String[] results = new String[values.length];
                for (int i = 0; i < values.length; ++i) {
                    results[i] = Integer.toString(values[i]);
                }
                return results;
            }
            try {
                final List list = this.parseElements(value.toString());
                final String[] results = new String[list.size()];
                for (int i = 0; i < results.length; ++i) {
                    results[i] = list.get(i);
                }
                return results;
            }
            catch (Exception e) {
                if (this.useDefault) {
                    return this.defaultValue;
                }
                throw new ConversionException(value.toString(), e);
            }
        }
    }
    
    static {
        StringArrayConverter.model = new String[0];
        StringArrayConverter.ints = new int[0];
    }
}
