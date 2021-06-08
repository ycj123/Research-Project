// 
// Decompiled by Procyon v0.5.36
// 

package groovy.time;

import java.util.Calendar;
import java.util.Date;

public class Duration extends BaseDuration
{
    public Duration(final int days, final int hours, final int minutes, final int seconds, final int millis) {
        super(days, hours, minutes, seconds, millis);
    }
    
    public Duration plus(final Duration rhs) {
        return new Duration(this.getDays() + rhs.getDays(), this.getHours() + rhs.getHours(), this.getMinutes() + rhs.getMinutes(), this.getSeconds() + rhs.getSeconds(), this.getMillis() + rhs.getMillis());
    }
    
    public Duration plus(final TimeDuration rhs) {
        return rhs.plus(this);
    }
    
    public DatumDependentDuration plus(final DatumDependentDuration rhs) {
        return rhs.plus(this);
    }
    
    public Duration minus(final Duration rhs) {
        return new Duration(this.getDays() - rhs.getDays(), this.getHours() - rhs.getHours(), this.getMinutes() - rhs.getMinutes(), this.getSeconds() - rhs.getSeconds(), this.getMillis() - rhs.getMillis());
    }
    
    public TimeDuration minus(final TimeDuration rhs) {
        return new TimeDuration(this.getDays() - rhs.getDays(), this.getHours() - rhs.getHours(), this.getMinutes() - rhs.getMinutes(), this.getSeconds() - rhs.getSeconds(), this.getMillis() - rhs.getMillis());
    }
    
    public DatumDependentDuration minus(final DatumDependentDuration rhs) {
        return new DatumDependentDuration(-rhs.getYears(), -rhs.getMonths(), this.getDays() - rhs.getDays(), this.getHours() - rhs.getHours(), this.getMinutes() - rhs.getMinutes(), this.getSeconds() - rhs.getSeconds(), this.getMillis() - rhs.getMillis());
    }
    
    public TimeDatumDependentDuration minus(final TimeDatumDependentDuration rhs) {
        return new TimeDatumDependentDuration(-rhs.getYears(), -rhs.getMonths(), this.getDays() - rhs.getDays(), this.getHours() - rhs.getHours(), this.getMinutes() - rhs.getMinutes(), this.getSeconds() - rhs.getSeconds(), this.getMillis() - rhs.getMillis());
    }
    
    @Override
    public long toMilliseconds() {
        return (((this.getDays() * 24 + (long)this.getHours()) * 60L + this.getMinutes()) * 60L + this.getSeconds()) * 1000L + this.getMillis();
    }
    
    @Override
    public Date getAgo() {
        final Calendar cal = Calendar.getInstance();
        cal.add(6, -this.getDays());
        cal.add(11, -this.getHours());
        cal.add(12, -this.getMinutes());
        cal.add(13, -this.getSeconds());
        cal.add(14, -this.getMillis());
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return new java.sql.Date(cal.getTimeInMillis());
    }
    
    @Override
    public From getFrom() {
        return new From() {
            @Override
            public Date getNow() {
                final Calendar cal = Calendar.getInstance();
                cal.add(6, Duration.this.getDays());
                cal.set(11, 0);
                cal.set(12, 0);
                cal.set(13, 0);
                cal.set(14, 0);
                return new java.sql.Date(cal.getTimeInMillis());
            }
        };
    }
}
