// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import java.net.MalformedURLException;
import java.net.URL;
import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;
import org.mudebug.prapr.reloc.commons.beanutils.Converter;

public final class URLConverter implements Converter
{
    private Object defaultValue;
    private boolean useDefault;
    
    public URLConverter() {
        this.defaultValue = null;
        this.useDefault = true;
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public URLConverter(final Object defaultValue) {
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
            if (value instanceof URL) {
                return value;
            }
            try {
                return new URL(value.toString());
            }
            catch (MalformedURLException murle) {
                if (this.useDefault) {
                    return this.defaultValue;
                }
                throw new ConversionException(murle);
            }
        }
    }
}
