// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;
import org.mudebug.prapr.reloc.commons.beanutils.Converter;

public final class IntegerConverter implements Converter
{
    private Object defaultValue;
    private boolean useDefault;
    
    public IntegerConverter() {
        this.defaultValue = null;
        this.useDefault = true;
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public IntegerConverter(final Object defaultValue) {
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
            if (value instanceof Integer) {
                return value;
            }
            if (value instanceof Number) {
                return new Integer(((Number)value).intValue());
            }
            try {
                return new Integer(value.toString());
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
