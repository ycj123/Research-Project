// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import java.util.List;
import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;

public final class FloatArrayConverter extends AbstractArrayConverter
{
    private static float[] model;
    
    public FloatArrayConverter() {
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public FloatArrayConverter(final Object defaultValue) {
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
            if (FloatArrayConverter.model.getClass() == value.getClass()) {
                return value;
            }
            if (AbstractArrayConverter.strings.getClass() == value.getClass()) {
                try {
                    final String[] values = (String[])value;
                    final float[] results = new float[values.length];
                    for (int i = 0; i < values.length; ++i) {
                        results[i] = Float.parseFloat(values[i]);
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
                final float[] results = new float[list.size()];
                for (int i = 0; i < results.length; ++i) {
                    results[i] = Float.parseFloat(list.get(i));
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
        FloatArrayConverter.model = new float[0];
    }
}
