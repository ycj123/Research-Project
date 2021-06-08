// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

import java.util.logging.Level;
import org.apache.velocity.runtime.RuntimeServices;
import java.util.logging.Logger;

public class JdkLogChute implements LogChute
{
    public static final String RUNTIME_LOG_JDK_LOGGER = "runtime.log.logsystem.jdk.logger";
    public static final String DEFAULT_LOG_NAME = "org.apache.velocity";
    protected Logger logger;
    
    public JdkLogChute() {
        this.logger = null;
    }
    
    public void init(final RuntimeServices rs) {
        String name = (String)rs.getProperty("runtime.log.logsystem.jdk.logger");
        if (name == null) {
            name = "org.apache.velocity";
        }
        this.logger = Logger.getLogger(name);
        this.log(0, "JdkLogChute will use logger '" + name + '\'');
    }
    
    protected Level getJdkLevel(final int level) {
        switch (level) {
            case 2: {
                return Level.WARNING;
            }
            case 1: {
                return Level.INFO;
            }
            case 0: {
                return Level.FINE;
            }
            case -1: {
                return Level.FINEST;
            }
            case 3: {
                return Level.SEVERE;
            }
            default: {
                return Level.FINER;
            }
        }
    }
    
    public void log(final int level, final String message) {
        this.log(level, message, null);
    }
    
    public void log(final int level, final String message, final Throwable t) {
        final Level jdkLevel = this.getJdkLevel(level);
        if (t == null) {
            this.logger.log(jdkLevel, message);
        }
        else {
            this.logger.log(jdkLevel, message, t);
        }
    }
    
    public boolean isLevelEnabled(final int level) {
        final Level jdkLevel = this.getJdkLevel(level);
        return this.logger.isLoggable(jdkLevel);
    }
}
