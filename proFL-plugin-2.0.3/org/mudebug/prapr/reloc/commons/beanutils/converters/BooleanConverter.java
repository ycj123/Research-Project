// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;
import org.mudebug.prapr.reloc.commons.beanutils.Converter;

public final class BooleanConverter implements Converter
{
    private Object defaultValue;
    private boolean useDefault;
    
    public BooleanConverter() {
        this.defaultValue = null;
        this.useDefault = true;
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public BooleanConverter(final Object defaultValue) {
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
            if (value instanceof Boolean) {
                return value;
            }
            try {
                final String stringValue = value.toString();
                if (stringValue.equalsIgnoreCase("yes") || stringValue.equalsIgnoreCase("y") || stringValue.equalsIgnoreCase("true") || stringValue.equalsIgnoreCase("on") || stringValue.equalsIgnoreCase("1")) {
                    return Boolean.TRUE;
                }
                if (stringValue.equalsIgnoreCase("no") || stringValue.equalsIgnoreCase("n") || stringValue.equalsIgnoreCase("false") || stringValue.equalsIgnoreCase("off") || stringValue.equalsIgnoreCase("0")) {
                    return Boolean.FALSE;
                }
                if (this.useDefault) {
                    return this.defaultValue;
                }
                throw new ConversionException(stringValue);
            }
            catch (ClassCastException e) {
                if (this.useDefault) {
                    return this.defaultValue;
                }
                throw new ConversionException(e);
            }
        }
    }
}
