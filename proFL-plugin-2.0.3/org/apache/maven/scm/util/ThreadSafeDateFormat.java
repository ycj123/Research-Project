// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.util;

import java.text.ParsePosition;
import java.text.FieldPosition;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.ref.SoftReference;
import java.text.DateFormat;

public class ThreadSafeDateFormat extends DateFormat
{
    private static final long serialVersionUID = 3786090697869963812L;
    private final String dateFormat;
    private final ThreadLocal<SoftReference<SimpleDateFormat>> formatCache;
    
    public ThreadSafeDateFormat(final String sDateFormat) {
        this.formatCache = new ThreadLocal<SoftReference<SimpleDateFormat>>() {
            @Override
            public SoftReference<SimpleDateFormat> get() {
                SoftReference<SimpleDateFormat> softRef = super.get();
                if (softRef == null || softRef.get() == null) {
                    softRef = new SoftReference<SimpleDateFormat>(new SimpleDateFormat(ThreadSafeDateFormat.this.dateFormat));
                    super.set(softRef);
                }
                return softRef;
            }
        };
        this.dateFormat = sDateFormat;
    }
    
    private DateFormat getDateFormat() {
        return this.formatCache.get().get();
    }
    
    @Override
    public StringBuffer format(final Date date, final StringBuffer toAppendTo, final FieldPosition fieldPosition) {
        return this.getDateFormat().format(date, toAppendTo, fieldPosition);
    }
    
    @Override
    public Date parse(final String source, final ParsePosition pos) {
        return this.getDateFormat().parse(source, pos);
    }
}
