// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import java.util.List;
import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;

public final class ShortArrayConverter extends AbstractArrayConverter
{
    private static short[] model;
    
    public ShortArrayConverter() {
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public ShortArrayConverter(final Object defaultValue) {
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
            if (ShortArrayConverter.model.getClass() == value.getClass()) {
                return value;
            }
            if (AbstractArrayConverter.strings.getClass() == value.getClass()) {
                try {
                    final String[] values = (String[])value;
                    final short[] results = new short[values.length];
                    for (int i = 0; i < values.length; ++i) {
                        results[i] = Short.parseShort(values[i]);
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
                final short[] results = new short[list.size()];
                for (int i = 0; i < results.length; ++i) {
                    results[i] = Short.parseShort(list.get(i));
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
        ShortArrayConverter.model = new short[0];
    }
}
