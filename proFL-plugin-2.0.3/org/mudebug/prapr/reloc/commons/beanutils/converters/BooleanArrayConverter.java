// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.converters;

import java.util.List;
import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;

public final class BooleanArrayConverter extends AbstractArrayConverter
{
    private static boolean[] model;
    
    public BooleanArrayConverter() {
        this.defaultValue = null;
        this.useDefault = false;
    }
    
    public BooleanArrayConverter(final Object defaultValue) {
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
            if (BooleanArrayConverter.model.getClass() == value.getClass()) {
                return value;
            }
            if (AbstractArrayConverter.strings.getClass() == value.getClass()) {
                try {
                    final String[] values = (String[])value;
                    final boolean[] results = new boolean[values.length];
                    for (int i = 0; i < values.length; ++i) {
                        final String stringValue = values[i];
                        if (stringValue.equalsIgnoreCase("yes") || stringValue.equalsIgnoreCase("y") || stringValue.equalsIgnoreCase("true") || stringValue.equalsIgnoreCase("on") || stringValue.equalsIgnoreCase("1")) {
                            results[i] = true;
                        }
                        else if (stringValue.equalsIgnoreCase("no") || stringValue.equalsIgnoreCase("n") || stringValue.equalsIgnoreCase("false") || stringValue.equalsIgnoreCase("off") || stringValue.equalsIgnoreCase("0")) {
                            results[i] = false;
                        }
                        else {
                            if (this.useDefault) {
                                return this.defaultValue;
                            }
                            throw new ConversionException(value.toString());
                        }
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
                final boolean[] results = new boolean[list.size()];
                for (int i = 0; i < results.length; ++i) {
                    final String stringValue = list.get(i);
                    if (stringValue.equalsIgnoreCase("yes") || stringValue.equalsIgnoreCase("y") || stringValue.equalsIgnoreCase("true") || stringValue.equalsIgnoreCase("on") || stringValue.equalsIgnoreCase("1")) {
                        results[i] = true;
                    }
                    else if (stringValue.equalsIgnoreCase("no") || stringValue.equalsIgnoreCase("n") || stringValue.equalsIgnoreCase("false") || stringValue.equalsIgnoreCase("off") || stringValue.equalsIgnoreCase("0")) {
                        results[i] = false;
                    }
                    else {
                        if (this.useDefault) {
                            return this.defaultValue;
                        }
                        throw new ConversionException(value.toString());
                    }
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
        BooleanArrayConverter.model = new boolean[0];
    }
}
