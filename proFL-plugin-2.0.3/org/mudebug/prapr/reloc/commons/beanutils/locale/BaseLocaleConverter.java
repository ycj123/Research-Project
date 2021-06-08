// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.locale;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.beanutils.ConversionException;
import java.text.ParseException;
import java.util.Locale;
import org.mudebug.prapr.reloc.commons.logging.Log;

public abstract class BaseLocaleConverter implements LocaleConverter
{
    private static Log log;
    private Object defaultValue;
    protected boolean useDefault;
    protected Locale locale;
    protected String pattern;
    protected boolean locPattern;
    
    protected BaseLocaleConverter(final Locale locale, final String pattern) {
        this(null, locale, pattern, false, false);
    }
    
    protected BaseLocaleConverter(final Locale locale, final String pattern, final boolean locPattern) {
        this(null, locale, pattern, false, locPattern);
    }
    
    protected BaseLocaleConverter(final Object defaultValue, final Locale locale, final String pattern) {
        this(defaultValue, locale, pattern, false);
    }
    
    protected BaseLocaleConverter(final Object defaultValue, final Locale locale, final String pattern, final boolean locPattern) {
        this(defaultValue, locale, pattern, true, locPattern);
    }
    
    private BaseLocaleConverter(final Object defaultValue, final Locale locale, final String pattern, final boolean useDefault, final boolean locPattern) {
        this.defaultValue = null;
        this.useDefault = false;
        this.locale = Locale.getDefault();
        this.pattern = null;
        this.locPattern = false;
        if (useDefault) {
            this.defaultValue = defaultValue;
            this.useDefault = true;
        }
        if (locale != null) {
            this.locale = locale;
        }
        this.pattern = pattern;
        this.locPattern = locPattern;
    }
    
    protected abstract Object parse(final Object p0, final String p1) throws ParseException;
    
    public Object convert(final Object value) {
        return this.convert(value, null);
    }
    
    public Object convert(final Object value, final String pattern) {
        return this.convert(null, value, pattern);
    }
    
    public Object convert(final Class type, final Object value) {
        return this.convert(type, value, null);
    }
    
    public Object convert(final Class type, final Object value, final String pattern) {
        if (value == null) {
            if (this.useDefault) {
                return this.defaultValue;
            }
            BaseLocaleConverter.log.debug("Null value specified for conversion, returing null");
            return null;
        }
        else {
            try {
                if (pattern != null) {
                    return this.parse(value, pattern);
                }
                return this.parse(value, this.pattern);
            }
            catch (Exception e) {
                if (this.useDefault) {
                    return this.defaultValue;
                }
                throw new ConversionException(e);
            }
        }
    }
    
    static {
        BaseLocaleConverter.log = LogFactory.getLog(BaseLocaleConverter.class);
    }
}
