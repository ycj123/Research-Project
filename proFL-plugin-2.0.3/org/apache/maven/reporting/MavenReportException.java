// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.reporting;

public class MavenReportException extends Exception
{
    public static final long serialVersionUID = -6200353563231163785L;
    
    public MavenReportException(final String msg) {
        super(msg);
    }
    
    public MavenReportException(final String msg, final Exception e) {
        super(msg, e);
    }
}
