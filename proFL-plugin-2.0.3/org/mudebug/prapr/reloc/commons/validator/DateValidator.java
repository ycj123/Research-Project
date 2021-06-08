// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.validator;

import java.text.DateFormat;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator
{
    private static final DateValidator instance;
    
    public static DateValidator getInstance() {
        return DateValidator.instance;
    }
    
    protected DateValidator() {
    }
    
    public boolean isValid(final String value, final String datePattern, final boolean strict) {
        if (value == null || datePattern == null || datePattern.length() <= 0) {
            return false;
        }
        final SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
        formatter.setLenient(false);
        try {
            formatter.parse(value);
        }
        catch (ParseException e) {
            return false;
        }
        return !strict || datePattern.length() == value.length();
    }
    
    public boolean isValid(final String value, final Locale locale) {
        if (value == null) {
            return false;
        }
        DateFormat formatter = null;
        if (locale != null) {
            formatter = DateFormat.getDateInstance(3, locale);
        }
        else {
            formatter = DateFormat.getDateInstance(3, Locale.getDefault());
        }
        formatter.setLenient(false);
        try {
            formatter.parse(value);
        }
        catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    static {
        instance = new DateValidator();
    }
}
