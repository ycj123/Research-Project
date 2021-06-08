// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

import org.codehaus.plexus.util.StringUtils;
import java.util.Date;

public class AccuRevVersion
{
    private String basisStream;
    private String timeSpec;
    
    public AccuRevVersion(final String basisStream, final String tran) {
        this.basisStream = basisStream;
        this.timeSpec = tran;
    }
    
    public String getBasisStream() {
        return this.basisStream;
    }
    
    public String getTimeSpec() {
        return this.timeSpec;
    }
    
    public AccuRevVersion(final String basis, final Date startDate) {
        this(basis, AccuRev.ACCUREV_TIME_SPEC.format(startDate));
    }
    
    public AccuRevVersion(final String basis, final long transactionId) {
        this(basis, Long.toString(transactionId));
    }
    
    public boolean isNow() {
        return isNow(this.timeSpec);
    }
    
    @Override
    public String toString() {
        return String.format("AccuRevVersion: stream = %s, transaction= %s", this.basisStream, this.timeSpec);
    }
    
    public static boolean isNow(final String timeSpec) {
        return StringUtils.isBlank(timeSpec) || "highest".equalsIgnoreCase(timeSpec) || "now".equalsIgnoreCase(timeSpec);
    }
}
