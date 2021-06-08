// 
// Decompiled by Procyon v0.5.36
// 

package groovy.time;

import java.util.Calendar;
import java.util.Date;

public class TimeDatumDependentDuration extends DatumDependentDuration
{
    public TimeDatumDependentDuration(final int years, final int months, final int days, final int hours, final int minutes, final int seconds, final int millis) {
        super(years, months, days, hours, minutes, seconds, millis);
    }
    
    @Override
    public DatumDependentDuration plus(final Duration rhs) {
        return new TimeDatumDependentDuration(this.getYears(), this.getMonths(), this.getDays() + rhs.getDays(), this.getHours() + rhs.getHours(), this.getMinutes() + rhs.getMinutes(), this.getSeconds() + rhs.getSeconds(), this.getMillis() + rhs.getMillis());
    }
    
    @Override
    public DatumDependentDuration plus(final DatumDependentDuration rhs) {
        return new TimeDatumDependentDuration(this.getYears() + rhs.getYears(), this.getMonths() + rhs.getMonths(), this.getDays() + rhs.getDays(), this.getHours() + rhs.getHours(), this.getMinutes() + rhs.getMinutes(), this.getSeconds() + rhs.getSeconds(), this.getMillis() + rhs.getMillis());
    }
    
    @Override
    public DatumDependentDuration minus(final Duration rhs) {
        return new TimeDatumDependentDuration(this.getYears(), this.getMonths(), this.getDays() - rhs.getDays(), this.getHours() - rhs.getHours(), this.getMinutes() - rhs.getMinutes(), this.getSeconds() - rhs.getSeconds(), this.getMillis() - rhs.getMillis());
    }
    
    @Override
    public DatumDependentDuration minus(final DatumDependentDuration rhs) {
        return new TimeDatumDependentDuration(this.getYears() - rhs.getYears(), this.getMonths() - rhs.getMonths(), this.getDays() - rhs.getDays(), this.getHours() - rhs.getHours(), this.getMinutes() - rhs.getMinutes(), this.getSeconds() - rhs.getSeconds(), this.getMillis() - rhs.getMillis());
    }
    
    @Override
    public From getFrom() {
        return new From() {
            @Override
            public Date getNow() {
                final Calendar cal = Calendar.getInstance();
                cal.add(1, TimeDatumDependentDuration.this.getYears());
                cal.add(2, TimeDatumDependentDuration.this.getMonths());
                cal.add(6, TimeDatumDependentDuration.this.getDays());
                cal.add(11, TimeDatumDependentDuration.this.getHours());
                cal.add(12, TimeDatumDependentDuration.this.getMinutes());
                cal.add(13, TimeDatumDependentDuration.this.getSeconds());
                cal.add(14, TimeDatumDependentDuration.this.getMillis());
                return cal.getTime();
            }
        };
    }
}
