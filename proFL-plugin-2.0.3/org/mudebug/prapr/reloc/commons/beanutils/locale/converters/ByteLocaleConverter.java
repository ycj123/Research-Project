// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.locale.converters;

import java.text.ParseException;
import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;
import java.util.Locale;

public class ByteLocaleConverter extends DecimalLocaleConverter
{
    public ByteLocaleConverter() {
        this(false);
    }
    
    public ByteLocaleConverter(final boolean locPattern) {
        this(Locale.getDefault(), locPattern);
    }
    
    public ByteLocaleConverter(final Locale locale) {
        this(locale, false);
    }
    
    public ByteLocaleConverter(final Locale locale, final boolean locPattern) {
        this(locale, null, locPattern);
    }
    
    public ByteLocaleConverter(final Locale locale, final String pattern) {
        this(locale, pattern, false);
    }
    
    public ByteLocaleConverter(final Locale locale, final String pattern, final boolean locPattern) {
        super(locale, pattern, locPattern);
    }
    
    public ByteLocaleConverter(final Object defaultValue) {
        this(defaultValue, false);
    }
    
    public ByteLocaleConverter(final Object defaultValue, final boolean locPattern) {
        this(defaultValue, Locale.getDefault(), locPattern);
    }
    
    public ByteLocaleConverter(final Object defaultValue, final Locale locale) {
        this(defaultValue, locale, false);
    }
    
    public ByteLocaleConverter(final Object defaultValue, final Locale locale, final boolean locPattern) {
        this(defaultValue, locale, null, locPattern);
    }
    
    public ByteLocaleConverter(final Object defaultValue, final Locale locale, final String pattern) {
        this(defaultValue, locale, pattern, false);
    }
    
    public ByteLocaleConverter(final Object defaultValue, final Locale locale, final String pattern, final boolean locPattern) {
        super(defaultValue, locale, pattern);
    }
    
    protected Object parse(final Object value, final String pattern) throws ParseException {
        final Number parsed = (Number)super.parse(value, pattern);
        if (parsed.longValue() != parsed.byteValue()) {
            throw new ConversionException("Supplied number is not of type Byte: " + parsed.longValue());
        }
        return new Byte(parsed.byteValue());
    }
}
