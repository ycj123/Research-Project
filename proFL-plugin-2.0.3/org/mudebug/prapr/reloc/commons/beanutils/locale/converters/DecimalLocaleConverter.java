// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.locale.converters;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.text.ParseException;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Locale;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.beanutils.locale.BaseLocaleConverter;

public class DecimalLocaleConverter extends BaseLocaleConverter
{
    private static Log log;
    
    public DecimalLocaleConverter() {
        this(false);
    }
    
    public DecimalLocaleConverter(final boolean locPattern) {
        this(Locale.getDefault(), locPattern);
    }
    
    public DecimalLocaleConverter(final Locale locale) {
        this(locale, false);
    }
    
    public DecimalLocaleConverter(final Locale locale, final boolean locPattern) {
        this(locale, null, locPattern);
    }
    
    public DecimalLocaleConverter(final Locale locale, final String pattern) {
        this(locale, pattern, false);
    }
    
    public DecimalLocaleConverter(final Locale locale, final String pattern, final boolean locPattern) {
        this(null, locale, pattern, locPattern);
    }
    
    public DecimalLocaleConverter(final Object defaultValue) {
        this(defaultValue, false);
    }
    
    public DecimalLocaleConverter(final Object defaultValue, final boolean locPattern) {
        this(defaultValue, Locale.getDefault(), locPattern);
    }
    
    public DecimalLocaleConverter(final Object defaultValue, final Locale locale) {
        this(defaultValue, locale, false);
    }
    
    public DecimalLocaleConverter(final Object defaultValue, final Locale locale, final boolean locPattern) {
        this(defaultValue, locale, null, locPattern);
    }
    
    public DecimalLocaleConverter(final Object defaultValue, final Locale locale, final String pattern) {
        this(defaultValue, locale, pattern, false);
    }
    
    public DecimalLocaleConverter(final Object defaultValue, final Locale locale, final String pattern, final boolean locPattern) {
        super(defaultValue, locale, pattern, locPattern);
    }
    
    protected Object parse(final Object value, final String pattern) throws ParseException {
        final DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance(this.locale);
        if (pattern != null) {
            if (this.locPattern) {
                formatter.applyLocalizedPattern(pattern);
            }
            else {
                formatter.applyPattern(pattern);
            }
        }
        else {
            DecimalLocaleConverter.log.warn("No pattern provided, using default.");
        }
        return formatter.parse((String)value);
    }
    
    static {
        DecimalLocaleConverter.log = LogFactory.getLog(DecimalLocaleConverter.class);
    }
}
