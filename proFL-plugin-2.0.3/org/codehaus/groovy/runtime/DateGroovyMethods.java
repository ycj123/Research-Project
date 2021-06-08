// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class DateGroovyMethods extends DefaultGroovyMethodsSupport
{
    private static final Map<String, Integer> CAL_MAP;
    
    public static int getAt(final Date self, final int field) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(self);
        return cal.get(field);
    }
    
    public static Calendar toCalendar(final Date self) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(self);
        return cal;
    }
    
    public static int getAt(final Calendar self, final int field) {
        return self.get(field);
    }
    
    public static void putAt(final Calendar self, final int field, final int value) {
        self.set(field, value);
    }
    
    public static void putAt(final Date self, final int field, final int value) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(self);
        putAt(cal, field, value);
        self.setTime(cal.getTimeInMillis());
    }
    
    public static void set(final Calendar self, final Map<Object, Integer> updates) {
        for (final Map.Entry<Object, Integer> entry : updates.entrySet()) {
            Object key = entry.getKey();
            if (key instanceof String) {
                key = DateGroovyMethods.CAL_MAP.get(key);
            }
            if (key instanceof Integer) {
                self.set((int)key, entry.getValue());
            }
        }
    }
    
    public static Calendar updated(final Calendar self, final Map<Object, Integer> updates) {
        final Calendar result = (Calendar)self.clone();
        for (final Map.Entry<Object, Integer> entry : updates.entrySet()) {
            Object key = entry.getKey();
            if (key instanceof String) {
                key = DateGroovyMethods.CAL_MAP.get(key);
            }
            if (key instanceof Integer) {
                result.set((int)key, entry.getValue());
            }
        }
        return result;
    }
    
    public static void set(final Date self, final Map<Object, Integer> updates) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(self);
        set(cal, updates);
        self.setTime(cal.getTimeInMillis());
    }
    
    public static Date updated(final Date self, final Map<Object, Integer> updates) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(self);
        set(cal, updates);
        return cal.getTime();
    }
    
    public static Date next(final Date self) {
        return plus(self, 1);
    }
    
    public static java.sql.Date next(final java.sql.Date self) {
        return new java.sql.Date(next((Date)self).getTime());
    }
    
    public static Date previous(final Date self) {
        return minus(self, 1);
    }
    
    public static java.sql.Date previous(final java.sql.Date self) {
        return new java.sql.Date(previous((Date)self).getTime());
    }
    
    public static Date plus(final Date self, final int days) {
        final Calendar calendar = (Calendar)Calendar.getInstance().clone();
        calendar.setTime(self);
        calendar.add(6, days);
        return calendar.getTime();
    }
    
    public static java.sql.Date plus(final java.sql.Date self, final int days) {
        return new java.sql.Date(plus((Date)self, days).getTime());
    }
    
    public static Date minus(final Date self, final int days) {
        return plus(self, -days);
    }
    
    public static java.sql.Date minus(final java.sql.Date self, final int days) {
        return new java.sql.Date(minus((Date)self, days).getTime());
    }
    
    public static int minus(final Calendar self, final Calendar then) {
        Calendar a = self;
        Calendar b = then;
        final boolean swap = a.before(b);
        if (swap) {
            final Calendar t = a;
            a = b;
            b = t;
        }
        int days = 0;
        b = (Calendar)b.clone();
        while (a.get(1) > b.get(1)) {
            days += 1 + (b.getActualMaximum(6) - b.get(6));
            b.set(6, 1);
            b.add(1, 1);
        }
        days += a.get(6) - b.get(6);
        if (swap) {
            days = -days;
        }
        return days;
    }
    
    public static int minus(final Date self, final Date then) {
        final Calendar a = (Calendar)Calendar.getInstance().clone();
        a.setTime(self);
        final Calendar b = (Calendar)Calendar.getInstance().clone();
        b.setTime(then);
        return minus(a, b);
    }
    
    public static String format(final Date self, final String format) {
        return new SimpleDateFormat(format).format(self);
    }
    
    public static String getDateString(final Date self) {
        return DateFormat.getDateInstance(3).format(self);
    }
    
    public static String getTimeString(final Date self) {
        return DateFormat.getTimeInstance(2).format(self);
    }
    
    public static String getDateTimeString(final Date self) {
        return DateFormat.getDateTimeInstance(3, 2).format(self);
    }
    
    private static void clearTimeCommon(final Calendar self) {
        self.set(11, 0);
        self.clear(12);
        self.clear(13);
        self.clear(14);
    }
    
    public static void clearTime(final Date self) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(self);
        clearTimeCommon(calendar);
        self.setTime(calendar.getTime().getTime());
    }
    
    public static void clearTime(final java.sql.Date self) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(self);
        clearTimeCommon(calendar);
        self.setTime(calendar.getTime().getTime());
    }
    
    public static void clearTime(final Calendar self) {
        clearTimeCommon(self);
    }
    
    public static String format(final Calendar self, final String pattern) {
        final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(self.getTimeZone());
        return sdf.format(self.getTime());
    }
    
    static {
        (CAL_MAP = new HashMap<String, Integer>()).put("year", 1);
        DateGroovyMethods.CAL_MAP.put("month", 2);
        DateGroovyMethods.CAL_MAP.put("date", 5);
        DateGroovyMethods.CAL_MAP.put("hourOfDay", 11);
        DateGroovyMethods.CAL_MAP.put("minute", 12);
        DateGroovyMethods.CAL_MAP.put("second", 13);
    }
}
