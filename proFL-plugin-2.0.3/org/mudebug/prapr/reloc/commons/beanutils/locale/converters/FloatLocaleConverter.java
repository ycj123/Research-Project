// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.locale.converters;

import java.text.ParseException;
import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;
import java.util.Locale;

public class FloatLocaleConverter extends DecimalLocaleConverter
{
    public FloatLocaleConverter() {
        this(false);
    }
    
    public FloatLocaleConverter(final boolean locPattern) {
        this(Locale.getDefault(), locPattern);
    }
    
    public FloatLocaleConverter(final Locale locale) {
        this(locale, false);
    }
    
    public FloatLocaleConverter(final Locale locale, final boolean locPattern) {
        this(locale, null, locPattern);
    }
    
    public FloatLocaleConverter(final Locale locale, final String pattern) {
        this(locale, pattern, false);
    }
    
    public FloatLocaleConverter(final Locale locale, final String pattern, final boolean locPattern) {
        super(locale, pattern, locPattern);
    }
    
    public FloatLocaleConverter(final Object defaultValue) {
        this(defaultValue, false);
    }
    
    public FloatLocaleConverter(final Object defaultValue, final boolean locPattern) {
        this(defaultValue, Locale.getDefault(), locPattern);
    }
    
    public FloatLocaleConverter(final Object defaultValue, final Locale locale) {
        this(defaultValue, locale, false);
    }
    
    public FloatLocaleConverter(final Object defaultValue, final Locale locale, final boolean locPattern) {
        this(defaultValue, locale, null, locPattern);
    }
    
    public FloatLocaleConverter(final Object defaultValue, final Locale locale, final String pattern) {
        this(defaultValue, locale, pattern, false);
    }
    
    public FloatLocaleConverter(final Object defaultValue, final Locale locale, final String pattern, final boolean locPattern) {
        super(defaultValue, locale, pattern);
    }
    
    protected Object parse(final Object value, final String pattern) throws ParseException {
        final Number parsed = (Number)super.parse(value, pattern);
        if (Math.abs(parsed.doubleValue() - parsed.floatValue()) > parsed.floatValue() * 1.0E-5) {
            throw new ConversionException("Suplied number is not of type Float: " + parsed.longValue());
        }
        return new Float(parsed.floatValue());
    }
}
