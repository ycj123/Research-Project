// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

import org.apache.log4j.Priority;
import java.io.IOException;
import org.apache.velocity.util.ExceptionUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import java.lang.reflect.Field;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.apache.velocity.runtime.RuntimeServices;

public class Log4JLogChute implements LogChute
{
    public static final String RUNTIME_LOG_LOG4J_LOGGER = "runtime.log.logsystem.log4j.logger";
    private RuntimeServices rsvc;
    private boolean hasTrace;
    private RollingFileAppender appender;
    protected Logger logger;
    
    public Log4JLogChute() {
        this.rsvc = null;
        this.hasTrace = false;
        this.appender = null;
        this.logger = null;
    }
    
    public void init(final RuntimeServices rs) throws Exception {
        this.rsvc = rs;
        final String name = (String)this.rsvc.getProperty("runtime.log.logsystem.log4j.logger");
        if (name != null) {
            this.logger = Logger.getLogger(name);
            this.log(0, "Log4JLogChute using logger '" + name + '\'');
        }
        else {
            this.logger = Logger.getLogger(this.getClass().getName());
            final String file = this.rsvc.getString("runtime.log");
            if (file != null && file.length() > 0) {
                this.initAppender(file);
            }
        }
        try {
            final Field traceLevel = Level.class.getField("TRACE");
            this.hasTrace = true;
        }
        catch (NoSuchFieldException e) {
            this.log(0, "The version of log4j being used does not support the \"trace\" level.");
        }
    }
    
    private void initAppender(final String file) throws Exception {
        try {
            final PatternLayout layout = new PatternLayout("%d - %m%n");
            (this.appender = new RollingFileAppender((Layout)layout, file, true)).setMaxBackupIndex(1);
            this.appender.setMaximumFileSize(100000L);
            this.logger.setAdditivity(false);
            this.logger.setLevel(Level.DEBUG);
            this.logger.addAppender((Appender)this.appender);
            this.log(0, "Log4JLogChute initialized using file '" + file + '\'');
        }
        catch (IOException ioe) {
            this.rsvc.getLog().warn("Could not create file appender '" + file + '\'', ioe);
            throw ExceptionUtils.createRuntimeException("Error configuring Log4JLogChute : ", ioe);
        }
    }
    
    public void log(final int level, final String message) {
        switch (level) {
            case 2: {
                this.logger.warn((Object)message);
                break;
            }
            case 1: {
                this.logger.info((Object)message);
                break;
            }
            case 0: {
                this.logger.debug((Object)message);
                break;
            }
            case -1: {
                if (this.hasTrace) {
                    this.logger.trace((Object)message);
                    break;
                }
                this.logger.debug((Object)message);
                break;
            }
            case 3: {
                this.logger.error((Object)message);
                break;
            }
            default: {
                this.logger.debug((Object)message);
                break;
            }
        }
    }
    
    public void log(final int level, final String message, final Throwable t) {
        switch (level) {
            case 2: {
                this.logger.warn((Object)message, t);
                break;
            }
            case 1: {
                this.logger.info((Object)message, t);
                break;
            }
            case 0: {
                this.logger.debug((Object)message, t);
                break;
            }
            case -1: {
                if (this.hasTrace) {
                    this.logger.trace((Object)message, t);
                    break;
                }
                this.logger.debug((Object)message, t);
                break;
            }
            case 3: {
                this.logger.error((Object)message, t);
                break;
            }
            default: {
                this.logger.debug((Object)message, t);
                break;
            }
        }
    }
    
    public boolean isLevelEnabled(final int level) {
        switch (level) {
            case 0: {
                return this.logger.isDebugEnabled();
            }
            case 1: {
                return this.logger.isInfoEnabled();
            }
            case -1: {
                if (this.hasTrace) {
                    return this.logger.isTraceEnabled();
                }
                return this.logger.isDebugEnabled();
            }
            case 2: {
                return this.logger.isEnabledFor((Priority)Level.WARN);
            }
            case 3: {
                return this.logger.isEnabledFor((Priority)Level.ERROR);
            }
            default: {
                return true;
            }
        }
    }
    
    protected void finalize() throws Throwable {
        this.shutdown();
    }
    
    public void shutdown() {
        if (this.appender != null) {
            this.logger.removeAppender((Appender)this.appender);
            this.appender.close();
            this.appender = null;
        }
    }
}
