// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;
import org.mudebug.prapr.reloc.commons.beanutils.Converter;

public final class ShortConverter implements Converter
{
    private Object defaultValue;
    private boolean useDefault;
    
    public ShortConverter() {
        this.defaultValue = null;
        this.useDefault = true;
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public ShortConverter(final Object defaultValue) {
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
            if (value instanceof Short) {
                return value;
            }
            if (value instanceof Number) {
                return new Short(((Number)value).shortValue());
            }
            try {
                return new Short(value.toString());
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
