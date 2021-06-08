// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils.locale.converters;

import java.util.Locale;

public class BigIntegerLocaleConverter extends DecimalLocaleConverter
{
    public BigIntegerLocaleConverter() {
        this(false);
    }
    
    public BigIntegerLocaleConverter(final boolean locPattern) {
        this(Locale.getDefault(), locPattern);
    }
    
    public BigIntegerLocaleConverter(final Locale locale) {
        this(locale, false);
    }
    
    public BigIntegerLocaleConverter(final Locale locale, final boolean locPattern) {
        this(locale, null, locPattern);
    }
    
    public BigIntegerLocaleConverter(final Locale locale, final String pattern) {
        this(locale, pattern, false);
    }
    
    public BigIntegerLocaleConverter(final Locale locale, final String pattern, final boolean locPattern) {
        super(locale, pattern, locPattern);
    }
    
    public BigIntegerLocaleConverter(final Object defaultValue) {
        this(defaultValue, false);
    }
    
    public BigIntegerLocaleConverter(final Object defaultValue, final boolean locPattern) {
        this(defaultValue, Locale.getDefault(), locPattern);
    }
    
    public BigIntegerLocaleConverter(final Object defaultValue, final Locale locale) {
        this(defaultValue, locale, false);
    }
    
    public BigIntegerLocaleConverter(final Object defaultValue, final Locale locale, final boolean locPattern) {
        this(defaultValue, locale, null, locPattern);
    }
    
    public BigIntegerLocaleConverter(final Object defaultValue, final Locale locale, final String pattern) {
        this(defaultValue, locale, pattern, false);
    }
    
    public BigIntegerLocaleConverter(final Object defaultValue, final Locale locale, final String pattern, final boolean locPattern) {
        super(defaultValue, locale, pattern);
    }
}
