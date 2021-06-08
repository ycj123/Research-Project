// 
// Decompiled by Procyon v0.5.36
// 

package org.slf4j.bridge;

import java.util.logging.Level;
import java.util.ResourceBundle;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;
import org.slf4j.LoggerFactory;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Handler;

public class SLF4JBridgeHandler extends Handler
{
    private static final String FQCN;
    private static final String UNKNOWN_LOGGER_NAME = "unknown.jul.logger";
    private static final int TRACE_LEVEL_THRESHOLD;
    private static final int DEBUG_LEVEL_THRESHOLD;
    private static final int INFO_LEVEL_THRESHOLD;
    private static final int WARN_LEVEL_THRESHOLD;
    
    public static void install() {
        LogManager.getLogManager().getLogger("").addHandler(new SLF4JBridgeHandler());
    }
    
    private static Logger getRootLogger() {
        return LogManager.getLogManager().getLogger("");
    }
    
    public static void uninstall() throws SecurityException {
        final Logger rootLogger = getRootLogger();
        final Handler[] handlers = rootLogger.getHandlers();
        for (int i = 0; i < handlers.length; ++i) {
            if (handlers[i] instanceof SLF4JBridgeHandler) {
                rootLogger.removeHandler(handlers[i]);
            }
        }
    }
    
    public static boolean isInstalled() throws SecurityException {
        final Logger rootLogger = getRootLogger();
        final Handler[] handlers = rootLogger.getHandlers();
        for (int i = 0; i < handlers.length; ++i) {
            if (handlers[i] instanceof SLF4JBridgeHandler) {
                return true;
            }
        }
        return false;
    }
    
    public static void removeHandlersForRootLogger() {
        final Logger rootLogger = getRootLogger();
        final Handler[] handlers = rootLogger.getHandlers();
        for (int i = 0; i < handlers.length; ++i) {
            rootLogger.removeHandler(handlers[i]);
        }
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public void flush() {
    }
    
    protected org.slf4j.Logger getSLF4JLogger(final LogRecord record) {
        String name = record.getLoggerName();
        if (name == null) {
            name = "unknown.jul.logger";
        }
        return LoggerFactory.getLogger(name);
    }
    
    protected void callLocationAwareLogger(final LocationAwareLogger lal, final LogRecord record) {
        final int julLevelValue = record.getLevel().intValue();
        int slf4jLevel;
        if (julLevelValue <= SLF4JBridgeHandler.TRACE_LEVEL_THRESHOLD) {
            slf4jLevel = 0;
        }
        else if (julLevelValue <= SLF4JBridgeHandler.DEBUG_LEVEL_THRESHOLD) {
            slf4jLevel = 10;
        }
        else if (julLevelValue <= SLF4JBridgeHandler.INFO_LEVEL_THRESHOLD) {
            slf4jLevel = 20;
        }
        else if (julLevelValue <= SLF4JBridgeHandler.WARN_LEVEL_THRESHOLD) {
            slf4jLevel = 30;
        }
        else {
            slf4jLevel = 40;
        }
        final String i18nMessage = this.getMessageI18N(record);
        lal.log((Marker)null, SLF4JBridgeHandler.FQCN, slf4jLevel, i18nMessage, (Object[])null, record.getThrown());
    }
    
    protected void callPlainSLF4JLogger(final org.slf4j.Logger slf4jLogger, final LogRecord record) {
        final String i18nMessage = this.getMessageI18N(record);
        final int julLevelValue = record.getLevel().intValue();
        if (julLevelValue <= SLF4JBridgeHandler.TRACE_LEVEL_THRESHOLD) {
            slf4jLogger.trace(i18nMessage, record.getThrown());
        }
        else if (julLevelValue <= SLF4JBridgeHandler.DEBUG_LEVEL_THRESHOLD) {
            slf4jLogger.debug(i18nMessage, record.getThrown());
        }
        else if (julLevelValue <= SLF4JBridgeHandler.INFO_LEVEL_THRESHOLD) {
            slf4jLogger.info(i18nMessage, record.getThrown());
        }
        else if (julLevelValue <= SLF4JBridgeHandler.WARN_LEVEL_THRESHOLD) {
            slf4jLogger.warn(i18nMessage, record.getThrown());
        }
        else {
            slf4jLogger.error(i18nMessage, record.getThrown());
        }
    }
    
    private String getMessageI18N(final LogRecord record) {
        String message = record.getMessage();
        if (message == null) {
            return null;
        }
        final ResourceBundle bundle = record.getResourceBundle();
        if (bundle != null) {
            try {
                message = bundle.getString(message);
            }
            catch (MissingResourceException ex) {}
        }
        final Object[] params = record.getParameters();
        if (params != null && params.length > 0) {
            message = MessageFormat.format(message, params);
        }
        return message;
    }
    
    @Override
    public void publish(final LogRecord record) {
        if (record == null) {
            return;
        }
        final org.slf4j.Logger slf4jLogger = this.getSLF4JLogger(record);
        String message = record.getMessage();
        if (message == null) {
            message = "";
        }
        if (slf4jLogger instanceof LocationAwareLogger) {
            this.callLocationAwareLogger((LocationAwareLogger)slf4jLogger, record);
        }
        else {
            this.callPlainSLF4JLogger(slf4jLogger, record);
        }
    }
    
    static {
        FQCN = Logger.class.getName();
        TRACE_LEVEL_THRESHOLD = Level.FINEST.intValue();
        DEBUG_LEVEL_THRESHOLD = Level.FINE.intValue();
        INFO_LEVEL_THRESHOLD = Level.INFO.intValue();
        WARN_LEVEL_THRESHOLD = Level.WARNING.intValue();
    }
}
