// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.logging;

public interface Log
{
    public static final int LEVEL_DEBUG = 0;
    public static final int LEVEL_INFO = 1;
    public static final int LEVEL_WARN = 2;
    public static final int LEVEL_ERROR = 3;
    public static final int LEVEL_FATAL = 4;
    public static final int LEVEL_DISABLED = 5;
    
    void setLogLevel(final int p0);
    
    boolean isDebugEnabled();
    
    void debug(final CharSequence p0);
    
    void debug(final CharSequence p0, final Throwable p1);
    
    void debug(final Throwable p0);
    
    boolean isInfoEnabled();
    
    void info(final CharSequence p0);
    
    void info(final CharSequence p0, final Throwable p1);
    
    void info(final Throwable p0);
    
    boolean isWarnEnabled();
    
    void warn(final CharSequence p0);
    
    void warn(final CharSequence p0, final Throwable p1);
    
    void warn(final Throwable p0);
    
    boolean isErrorEnabled();
    
    void error(final CharSequence p0);
    
    void error(final CharSequence p0, final Throwable p1);
    
    void error(final Throwable p0);
}
