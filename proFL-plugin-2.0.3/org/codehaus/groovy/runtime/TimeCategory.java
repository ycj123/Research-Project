// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.time.DatumDependentDuration;
import groovy.time.TimeDuration;
import groovy.time.Duration;
import java.util.TimeZone;
import java.util.Calendar;
import groovy.time.BaseDuration;
import java.util.Date;

public class TimeCategory
{
    public static Date plus(final Date date, final BaseDuration duration) {
        return duration.plus(date);
    }
    
    public static Date minus(final Date date, final BaseDuration duration) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(1, -duration.getYears());
        cal.add(2, -duration.getMonths());
        cal.add(6, -duration.getDays());
        cal.add(11, -duration.getHours());
        cal.add(12, -duration.getMinutes());
        cal.add(13, -duration.getSeconds());
        cal.add(14, -duration.getMillis());
        return cal.getTime();
    }
    
    public static TimeZone getTimeZone(final Date self) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(self);
        return calendar.getTimeZone();
    }
    
    public static Duration getDaylightSavingsOffset(final Date self) {
        final TimeZone timeZone = getTimeZone(self);
        final int millis = (timeZone.useDaylightTime() && timeZone.inDaylightTime(self)) ? timeZone.getDSTSavings() : 0;
        return new TimeDuration(0, 0, 0, millis);
    }
    
    public static Duration getDaylightSavingsOffset(final BaseDuration self) {
        return getDaylightSavingsOffset(new Date(self.toMilliseconds() + 1L));
    }
    
    public static Duration getRelativeDaylightSavingsOffset(final Date self, final Date other) {
        final Duration d1 = getDaylightSavingsOffset(self);
        final Duration d2 = getDaylightSavingsOffset(other);
        return new TimeDuration(0, 0, 0, (int)(d2.toMilliseconds() - d1.toMilliseconds()));
    }
    
    public static TimeDuration minus(final Date lhs, final Date rhs) {
        long milliseconds = lhs.getTime() - rhs.getTime();
        final long days = milliseconds / 86400000L;
        milliseconds -= days * 24L * 60L * 60L * 1000L;
        final int hours = (int)(milliseconds / 3600000L);
        milliseconds -= hours * 60 * 60 * 1000;
        final int minutes = (int)(milliseconds / 60000L);
        milliseconds -= minutes * 60 * 1000;
        final int seconds = (int)(milliseconds / 1000L);
        milliseconds -= seconds * 1000;
        return new TimeDuration((int)days, hours, minutes, seconds, (int)milliseconds);
    }
    
    public static DatumDependentDuration getMonths(final Integer self) {
        return new DatumDependentDuration(0, self, 0, 0, 0, 0, 0);
    }
    
    public static DatumDependentDuration getMonth(final Integer self) {
        return getMonths(self);
    }
    
    public static DatumDependentDuration getYears(final Integer self) {
        return new DatumDependentDuration(self, 0, 0, 0, 0, 0, 0);
    }
    
    public static DatumDependentDuration getYear(final Integer self) {
        return getYears(self);
    }
    
    public static Duration getWeeks(final Integer self) {
        return new Duration(self * 7, 0, 0, 0, 0);
    }
    
    public static Duration getWeek(final Integer self) {
        return getWeeks(self);
    }
    
    public static Duration getDays(final Integer self) {
        return new Duration(self, 0, 0, 0, 0);
    }
    
    public static Duration getDay(final Integer self) {
        return getDays(self);
    }
    
    public static TimeDuration getHours(final Integer self) {
        return new TimeDuration(0, self, 0, 0, 0);
    }
    
    public static TimeDuration getHour(final Integer self) {
        return getHours(self);
    }
    
    public static TimeDuration getMinutes(final Integer self) {
        return new TimeDuration(0, 0, self, 0, 0);
    }
    
    public static TimeDuration getMinute(final Integer self) {
        return getMinutes(self);
    }
    
    public static TimeDuration getSeconds(final Integer self) {
        return new TimeDuration(0, 0, 0, self, 0);
    }
    
    public static TimeDuration getSecond(final Integer self) {
        return getSeconds(self);
    }
    
    public static TimeDuration getMilliseconds(final Integer self) {
        return new TimeDuration(0, 0, 0, 0, self);
    }
    
    public static TimeDuration getMillisecond(final Integer self) {
        return getMilliseconds(self);
    }
}
