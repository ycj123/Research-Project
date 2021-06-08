// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import java.math.BigInteger;
import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;
import org.mudebug.prapr.reloc.commons.beanutils.Converter;

public final class BigIntegerConverter implements Converter
{
    private Object defaultValue;
    private boolean useDefault;
    
    public BigIntegerConverter() {
        this.defaultValue = null;
        this.useDefault = true;
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public BigIntegerConverter(final Object defaultValue) {
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
            if (value instanceof BigInteger) {
                return value;
            }
            try {
                return new BigInteger(value.toString());
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
