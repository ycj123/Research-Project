// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.validator;

import java.util.Locale;
import org.apache.oro.text.perl.Perl5Util;
import java.io.Serializable;

public class GenericValidator implements Serializable
{
    private static final UrlValidator urlValidator;
    private static final CreditCardValidator creditCardValidator;
    
    public static boolean isBlankOrNull(final String value) {
        return value == null || value.trim().length() == 0;
    }
    
    public static boolean matchRegexp(final String value, final String regexp) {
        if (regexp == null || regexp.length() <= 0) {
            return false;
        }
        final Perl5Util matcher = new Perl5Util();
        return matcher.match("/" + regexp + "/", value);
    }
    
    public static boolean isByte(final String value) {
        return GenericTypeValidator.formatByte(value) != null;
    }
    
    public static boolean isShort(final String value) {
        return GenericTypeValidator.formatShort(value) != null;
    }
    
    public static boolean isInt(final String value) {
        return GenericTypeValidator.formatInt(value) != null;
    }
    
    public static boolean isLong(final String value) {
        return GenericTypeValidator.formatLong(value) != null;
    }
    
    public static boolean isFloat(final String value) {
        return GenericTypeValidator.formatFloat(value) != null;
    }
    
    public static boolean isDouble(final String value) {
        return GenericTypeValidator.formatDouble(value) != null;
    }
    
    public static boolean isDate(final String value, final Locale locale) {
        return DateValidator.getInstance().isValid(value, locale);
    }
    
    public static boolean isDate(final String value, final String datePattern, final boolean strict) {
        return DateValidator.getInstance().isValid(value, datePattern, strict);
    }
    
    public static boolean isInRange(final byte value, final byte min, final byte max) {
        return value >= min && value <= max;
    }
    
    public static boolean isInRange(final int value, final int min, final int max) {
        return value >= min && value <= max;
    }
    
    public static boolean isInRange(final float value, final float min, final float max) {
        return value >= min && value <= max;
    }
    
    public static boolean isInRange(final short value, final short min, final short max) {
        return value >= min && value <= max;
    }
    
    public static boolean isInRange(final long value, final long min, final long max) {
        return value >= min && value <= max;
    }
    
    public static boolean isInRange(final double value, final double min, final double max) {
        return value >= min && value <= max;
    }
    
    public static boolean isCreditCard(final String value) {
        return GenericValidator.creditCardValidator.isValid(value);
    }
    
    public static boolean isEmail(final String value) {
        return EmailValidator.getInstance().isValid(value);
    }
    
    public static boolean isUrl(final String value) {
        return GenericValidator.urlValidator.isValid(value);
    }
    
    public static boolean maxLength(final String value, final int max) {
        return value.length() <= max;
    }
    
    public static boolean minLength(final String value, final int min) {
        return value.length() >= min;
    }
    
    public static boolean minValue(final int value, final int min) {
        return value >= min;
    }
    
    public static boolean minValue(final long value, final long min) {
        return value >= min;
    }
    
    public static boolean minValue(final double value, final double min) {
        return value >= min;
    }
    
    public static boolean minValue(final float value, final float min) {
        return value >= min;
    }
    
    public static boolean maxValue(final int value, final int max) {
        return value <= max;
    }
    
    public static boolean maxValue(final long value, final long max) {
        return value <= max;
    }
    
    public static boolean maxValue(final double value, final double max) {
        return value <= max;
    }
    
    public static boolean maxValue(final float value, final float max) {
        return value <= max;
    }
    
    static {
        urlValidator = new UrlValidator();
        creditCardValidator = new CreditCardValidator();
    }
}
