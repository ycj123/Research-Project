// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.log;

public interface ScmLogger
{
    boolean isDebugEnabled();
    
    void debug(final String p0);
    
    void debug(final String p0, final Throwable p1);
    
    void debug(final Throwable p0);
    
    boolean isInfoEnabled();
    
    void info(final String p0);
    
    void info(final String p0, final Throwable p1);
    
    void info(final Throwable p0);
    
    boolean isWarnEnabled();
    
    void warn(final String p0);
    
    void warn(final String p0, final Throwable p1);
    
    void warn(final Throwable p0);
    
    boolean isErrorEnabled();
    
    void error(final String p0);
    
    void error(final String p0, final Throwable p1);
    
    void error(final Throwable p0);
}
