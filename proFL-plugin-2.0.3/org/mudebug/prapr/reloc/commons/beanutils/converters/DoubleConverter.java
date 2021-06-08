// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;
import org.mudebug.prapr.reloc.commons.beanutils.Converter;

public final class DoubleConverter implements Converter
{
    private Object defaultValue;
    private boolean useDefault;
    
    public DoubleConverter() {
        this.defaultValue = null;
        this.useDefault = true;
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public DoubleConverter(final Object defaultValue) {
        this.defaultValue = null;
        this.useDefault = true;
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
            if (value instanceof Double) {
                return value;
            }
            if (value instanceof Number) {
                return new Double(((Number)value).doubleValue());
            }
            try {
                return new Double(value.toString());
            }
            catch (Exception e) {
                if (this.useDefault) {
                    return this.defaultValue;
                }
                throw new ConversionException(e);
            }
        }
    }
}
