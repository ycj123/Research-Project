// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.logging.impl;

import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.Logger;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class Log4JLogger implements Log, Serializable
{
    private static final String FQCN;
    private static final boolean is12;
    private transient Logger logger;
    private String name;
    
    public Log4JLogger() {
        this.logger = null;
        this.name = null;
    }
    
    public Log4JLogger(final String name) {
        this.logger = null;
        this.name = null;
        this.name = name;
        this.logger = this.getLogger();
    }
    
    public Log4JLogger(final Logger logger) {
        this.logger = null;
        this.name = null;
        this.name = ((Category)logger).getName();
        this.logger = logger;
    }
    
    public void trace(final Object message) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.DEBUG, message, (Throwable)null);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.DEBUG, message, (Throwable)null);
        }
    }
    
    public void trace(final Object message, final Throwable t) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.DEBUG, message, t);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.DEBUG, message, t);
        }
    }
    
    public void debug(final Object message) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.DEBUG, message, (Throwable)null);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.DEBUG, message, (Throwable)null);
        }
    }
    
    public void debug(final Object message, final Throwable t) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.DEBUG, message, t);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.DEBUG, message, t);
        }
    }
    
    public void info(final Object message) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.INFO, message, (Throwable)null);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.INFO, message, (Throwable)null);
        }
    }
    
    public void info(final Object message, final Throwable t) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.INFO, message, t);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.INFO, message, t);
        }
    }
    
    public void warn(final Object message) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.WARN, message, (Throwable)null);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.WARN, message, (Throwable)null);
        }
    }
    
    public void warn(final Object message, final Throwable t) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.WARN, message, t);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.WARN, message, t);
        }
    }
    
    public void error(final Object message) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.ERROR, message, (Throwable)null);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.ERROR, message, (Throwable)null);
        }
    }
    
    public void error(final Object message, final Throwable t) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.ERROR, message, t);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.ERROR, message, t);
        }
    }
    
    public void fatal(final Object message) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.FATAL, message, (Throwable)null);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.FATAL, message, (Throwable)null);
        }
    }
    
    public void fatal(final Object message, final Throwable t) {
        if (Log4JLogger.is12) {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, (Priority)Level.FATAL, message, t);
        }
        else {
            ((Category)this.getLogger()).log(Log4JLogger.FQCN, Level.FATAL, message, t);
        }
    }
    
    public Logger getLogger() {
        if (this.logger == null) {
            this.logger = Logger.getLogger(this.name);
        }
        return this.logger;
    }
    
    public boolean isDebugEnabled() {
        return ((Category)this.getLogger()).isDebugEnabled();
    }
    
    public boolean isErrorEnabled() {
        if (Log4JLogger.is12) {
            return ((Category)this.getLogger()).isEnabledFor((Priority)Level.ERROR);
        }
        return ((Category)this.getLogger()).isEnabledFor(Level.ERROR);
    }
    
    public boolean isFatalEnabled() {
        if (Log4JLogger.is12) {
            return ((Category)this.getLogger()).isEnabledFor((Priority)Level.FATAL);
        }
        return ((Category)this.getLogger()).isEnabledFor(Level.FATAL);
    }
    
    public boolean isInfoEnabled() {
        return ((Category)this.getLogger()).isInfoEnabled();
    }
    
    public boolean isTraceEnabled() {
        return ((Category)this.getLogger()).isDebugEnabled();
    }
    
    public boolean isWarnEnabled() {
        if (Log4JLogger.is12) {
            return ((Category)this.getLogger()).isEnabledFor((Priority)Level.WARN);
        }
        return ((Category)this.getLogger()).isEnabledFor(Level.WARN);
    }
    
    static {
        FQCN = Log4JLogger.class.getName();
        is12 = Priority.class.isAssignableFrom(Level.class);
    }
}
