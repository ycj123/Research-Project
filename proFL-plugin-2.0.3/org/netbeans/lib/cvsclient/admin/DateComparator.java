// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.admin;

public class DateComparator
{
    private static final long SECONDS_PER_HOUR = 3600L;
    private static final DateComparator singleton;
    
    public static DateComparator getInstance() {
        return DateComparator.singleton;
    }
    
    private DateComparator() {
    }
    
    public boolean equals(final long n, final long n2) {
        final long abs = Math.abs(n / 1000L - n2 / 1000L);
        return abs < 1L || (abs >= 3599L && abs <= 3601L);
    }
    
    static {
        singleton = new DateComparator();
    }
}
