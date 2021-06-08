// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context;

import org.slf4j.Marker;
import org.slf4j.Logger;

public enum LogLevel
{
    TRACE(0) {
        @Override
        public void log(final Logger logger, final String message) {
            logger.trace(message);
        }
        
        @Override
        public void log(final Logger logger, final Marker marker, final String message) {
            logger.trace(marker, message);
        }
    }, 
    DEBUG(1) {
        @Override
        public void log(final Logger logger, final String message) {
            logger.debug(message);
        }
        
        @Override
        public void log(final Logger logger, final Marker marker, final String message) {
            logger.debug(marker, message);
        }
    }, 
    INFO(2) {
        @Override
        public void log(final Logger logger, final String message) {
            logger.info(message);
        }
        
        @Override
        public void log(final Logger logger, final Marker marker, final String message) {
            logger.info(marker, message);
        }
    }, 
    WARN(3) {
        @Override
        public void log(final Logger logger, final String message) {
            logger.warn(message);
        }
        
        @Override
        public void log(final Logger logger, final Marker marker, final String message) {
            logger.warn(marker, message);
        }
    }, 
    ERROR(4) {
        @Override
        public void log(final Logger logger, final String message) {
            logger.error(message);
        }
        
        @Override
        public void log(final Logger logger, final Marker marker, final String message) {
            logger.error(marker, message);
        }
    };
    
    private LogLevel(final String name, final int ordinal) {
    }
    
    public abstract void log(final Logger p0, final String p1);
    
    public abstract void log(final Logger p0, final Marker p1, final String p2);
}
