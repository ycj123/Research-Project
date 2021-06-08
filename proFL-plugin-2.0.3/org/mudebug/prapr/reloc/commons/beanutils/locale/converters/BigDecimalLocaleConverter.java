// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.locale.converters;

import java.util.Locale;

public class BigDecimalLocaleConverter extends DecimalLocaleConverter
{
    public BigDecimalLocaleConverter() {
        this(false);
    }
    
    public BigDecimalLocaleConverter(final boolean locPattern) {
        this(Locale.getDefault(), locPattern);
    }
    
    public BigDecimalLocaleConverter(final Locale locale) {
        this(locale, false);
    }
    
    public BigDecimalLocaleConverter(final Locale locale, final boolean locPattern) {
        this(locale, null, locPattern);
    }
    
    public BigDecimalLocaleConverter(final Locale locale, final String pattern) {
        this(locale, pattern, false);
    }
    
    public BigDecimalLocaleConverter(final Locale locale, final String pattern, final boolean locPattern) {
        super(locale, pattern, locPattern);
    }
    
    public BigDecimalLocaleConverter(final Object defaultValue) {
        this(defaultValue, false);
    }
    
    public BigDecimalLocaleConverter(final Object defaultValue, final boolean locPattern) {
        this(defaultValue, Locale.getDefault(), locPattern);
    }
    
    public BigDecimalLocaleConverter(final Object defaultValue, final Locale locale) {
        this(defaultValue, locale, false);
    }
    
    public BigDecimalLocaleConverter(final Object defaultValue, final Locale locale, final boolean locPattern) {
        this(defaultValue, locale, null, locPattern);
    }
    
    public BigDecimalLocaleConverter(final Object defaultValue, final Locale locale, final String pattern) {
        this(defaultValue, locale, pattern, false);
    }
    
    public BigDecimalLocaleConverter(final Object defaultValue, final Locale locale, final String pattern, final boolean locPattern) {
        super(defaultValue, locale, pattern);
    }
}
