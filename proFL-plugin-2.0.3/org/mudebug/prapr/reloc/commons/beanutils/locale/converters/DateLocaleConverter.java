// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.locale.converters;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.beanutils.locale.BaseLocaleConverter;

public class DateLocaleConverter extends BaseLocaleConverter
{
    private static Log log;
    boolean isLenient;
    
    public DateLocaleConverter() {
        this(false);
    }
    
    public DateLocaleConverter(final boolean locPattern) {
        this(Locale.getDefault(), locPattern);
    }
    
    public DateLocaleConverter(final Locale locale) {
        this(locale, false);
    }
    
    public DateLocaleConverter(final Locale locale, final boolean locPattern) {
        this(locale, null, locPattern);
    }
    
    public DateLocaleConverter(final Locale locale, final String pattern) {
        this(locale, pattern, false);
    }
    
    public DateLocaleConverter(final Locale locale, final String pattern, final boolean locPattern) {
        super(locale, pattern, locPattern);
        this.isLenient = false;
    }
    
    public DateLocaleConverter(final Object defaultValue) {
        this(defaultValue, false);
    }
    
    public DateLocaleConverter(final Object defaultValue, final boolean locPattern) {
        this(defaultValue, Locale.getDefault(), locPattern);
    }
    
    public DateLocaleConverter(final Object defaultValue, final Locale locale) {
        this(defaultValue, locale, false);
    }
    
    public DateLocaleConverter(final Object defaultValue, final Locale locale, final boolean locPattern) {
        this(defaultValue, locale, null, locPattern);
    }
    
    public DateLocaleConverter(final Object defaultValue, final Locale locale, final String pattern) {
        this(defaultValue, locale, pattern, false);
    }
    
    public DateLocaleConverter(final Object defaultValue, final Locale locale, final String pattern, final boolean locPattern) {
        super(defaultValue, locale, pattern, locPattern);
        this.isLenient = false;
    }
    
    public boolean isLenient() {
        return this.isLenient;
    }
    
    public void setLenient(final boolean lenient) {
        this.isLenient = lenient;
    }
    
    protected Object parse(final Object value, final String pattern) throws ParseException {
        final SimpleDateFormat formatter = this.getFormatter(pattern, this.locale);
        if (this.locPattern) {
            formatter.applyLocalizedPattern(pattern);
        }
        else {
            formatter.applyPattern(pattern);
        }
        return formatter.parse((String)value);
    }
    
    private SimpleDateFormat getFormatter(String pattern, final Locale locale) {
        if (pattern == null) {
            pattern = (this.locPattern ? new SimpleDateFormat().toLocalizedPattern() : new SimpleDateFormat().toPattern());
            DateLocaleConverter.log.warn("Null pattern was provided, defaulting to: " + pattern);
        }
        final SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
        format.setLenient(this.isLenient);
        return format;
    }
    
    static {
        DateLocaleConverter.log = LogFactory.getLog(DateLocaleConverter.class);
    }
}
