// 
// Decompiled by Procyon v0.5.36
// 

package groovy.time;

import java.util.Calendar;
import java.util.Date;

public class TimeDuration extends Duration
{
    public TimeDuration(final int hours, final int minutes, final int seconds, final int millis) {
        super(0, hours, minutes, seconds, millis);
    }
    
    public TimeDuration(final int days, final int hours, final int minutes, final int seconds, final int millis) {
        super(days, hours, minutes, seconds, millis);
    }
    
    @Override
    public Duration plus(final Duration rhs) {
        return new TimeDuration(this.getDays() + rhs.getDays(), this.getHours() + rhs.getHours(), this.getMinutes() + rhs.getMinutes(), this.getSeconds() + rhs.getSeconds(), this.getMillis() + rhs.getMillis());
    }
    
    @Override
    public DatumDependentDuration plus(final DatumDependentDuration rhs) {
        return new TimeDatumDependentDuration(rhs.getYears(), rhs.getMonths(), this.getDays() + rhs.getDays(), this.getHours() + rhs.getHours(), this.getMinutes() + rhs.getMinutes(), this.getSeconds() + rhs.getSeconds(), this.getMillis() + rhs.getMillis());
    }
    
    @Override
    public Duration minus(final Duration rhs) {
        return new TimeDuration(this.getDays() - rhs.getDays(), this.getHours() - rhs.getHours(), this.getMinutes() - rhs.getMinutes(), this.getSeconds() - rhs.getSeconds(), this.getMillis() - rhs.getMillis());
    }
    
    @Override
    public DatumDependentDuration minus(final DatumDependentDuration rhs) {
        return new TimeDatumDependentDuration(-rhs.getYears(), -rhs.getMonths(), this.getDays() - rhs.getDays(), this.getHours() - rhs.getHours(), this.getMinutes() - rhs.getMinutes(), this.getSeconds() - rhs.getSeconds(), this.getMillis() - rhs.getMillis());
    }
    
    @Override
    public Date getAgo() {
        final Calendar cal = Calendar.getInstance();
        cal.add(6, -this.getDays());
        cal.add(11, -this.getHours());
        cal.add(12, -this.getMinutes());
        cal.add(13, -this.getSeconds());
        cal.add(14, -this.getMillis());
        return cal.getTime();
    }
    
    @Override
    public From getFrom() {
        return new From() {
            @Override
            public Date getNow() {
                final Calendar cal = Calendar.getInstance();
                cal.add(6, TimeDuration.this.getDays());
                cal.add(11, TimeDuration.this.getHours());
                cal.add(12, TimeDuration.this.getMinutes());
                cal.add(13, TimeDuration.this.getSeconds());
                cal.add(14, TimeDuration.this.getMillis());
                return cal.getTime();
            }
        };
    }
}
