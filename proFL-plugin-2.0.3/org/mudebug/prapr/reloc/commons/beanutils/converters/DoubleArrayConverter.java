// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import java.util.List;
import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;

public final class DoubleArrayConverter extends AbstractArrayConverter
{
    private static double[] model;
    
    public DoubleArrayConverter() {
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public DoubleArrayConverter(final Object defaultValue) {
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
            if (DoubleArrayConverter.model.getClass() == value.getClass()) {
                return value;
            }
            if (AbstractArrayConverter.strings.getClass() == value.getClass()) {
                try {
                    final String[] values = (String[])value;
                    final double[] results = new double[values.length];
                    for (int i = 0; i < values.length; ++i) {
                        results[i] = Double.parseDouble(values[i]);
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
            try {
                final List list = this.parseElements(value.toString());
                final double[] results = new double[list.size()];
                for (int i = 0; i < results.length; ++i) {
                    results[i] = Double.parseDouble(list.get(i));
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
        DoubleArrayConverter.model = new double[0];
    }
}
