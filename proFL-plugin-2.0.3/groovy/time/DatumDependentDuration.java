// 
// Decompiled by Procyon v0.5.36
// 

package groovy.time;

import java.util.Calendar;
import java.util.Date;

public class DatumDependentDuration extends BaseDuration
{
    public DatumDependentDuration(final int years, final int months, final int days, final int hours, final int minutes, final int seconds, final int millis) {
        super(years, months, days, hours, minutes, seconds, millis);
    }
    
    @Override
    public int getMonths() {
        return this.months;
    }
    
    @Override
    public int getYears() {
        return this.years;
    }
    
    public DatumDependentDuration plus(final DatumDependentDuration rhs) {
        return new DatumDependentDuration(this.getYears() + rhs.getYears(), this.getMonths() + rhs.getMonths(), this.getDays() + rhs.getDays(), this.getHours() + rhs.getHours(), this.getMinutes() + rhs.getMinutes(), this.getSeconds() + rhs.getSeconds(), this.getMillis() + rhs.getMillis());
    }
    
    public DatumDependentDuration plus(final TimeDatumDependentDuration rhs) {
        return rhs.plus(this);
    }
    
    public DatumDependentDuration plus(final Duration rhs) {
        return new DatumDependentDuration(this.getYears(), this.getMonths(), this.getDays() + rhs.getDays(), this.getHours() + rhs.getHours(), this.getMinutes() + rhs.getMinutes(), this.getSeconds() + rhs.getSeconds(), this.getMillis() + rhs.getMillis());
    }
    
    public DatumDependentDuration plus(final TimeDuration rhs) {
        return rhs.plus(this);
    }
    
    public DatumDependentDuration minus(final DatumDependentDuration rhs) {
        return new DatumDependentDuration(this.getYears() - rhs.getYears(), this.getMonths() - rhs.getMonths(), this.getDays() - rhs.getDays(), this.getHours() - rhs.getHours(), this.getMinutes() - rhs.getMinutes(), this.getSeconds() - rhs.getSeconds(), this.getMillis() - rhs.getMillis());
    }
    
    public DatumDependentDuration minus(final Duration rhs) {
        return new DatumDependentDuration(this.getYears(), this.getMonths(), this.getDays() - rhs.getDays(), this.getHours() - rhs.getHours(), this.getMinutes() - rhs.getMinutes(), this.getSeconds() - rhs.getSeconds(), this.getMillis() - rhs.getMillis());
    }
    
    @Override
    public long toMilliseconds() {
        final Date now = new Date();
        return TimeCategory.minus(this.plus(now), now).toMilliseconds();
    }
    
    @Override
    public Date getAgo() {
        final Calendar cal = Calendar.getInstance();
        cal.add(1, -this.getYears());
        cal.add(2, -this.getMonths());
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
                cal.add(1, DatumDependentDuration.this.getYears());
                cal.add(2, DatumDependentDuration.this.getMonths());
                cal.add(6, DatumDependentDuration.this.getDays());
                cal.add(11, DatumDependentDuration.this.getHours());
                cal.add(12, DatumDependentDuration.this.getMinutes());
                cal.add(13, DatumDependentDuration.this.getSeconds());
                cal.add(14, DatumDependentDuration.this.getMillis());
                cal.set(11, 0);
                cal.set(12, 0);
                cal.set(13, 0);
                cal.set(14, 0);
                return new java.sql.Date(cal.getTimeInMillis());
            }
        };
    }
}
