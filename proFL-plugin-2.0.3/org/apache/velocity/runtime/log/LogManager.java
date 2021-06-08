// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

import java.util.Iterator;
import org.apache.velocity.util.ClassUtils;
import java.util.List;
import java.util.ArrayList;
import org.apache.velocity.runtime.RuntimeServices;

public class LogManager
{
    private static LogChute createLogChute(final RuntimeServices rsvc) throws Exception {
        final Log log = rsvc.getLog();
        Object o = rsvc.getProperty("runtime.log.logsystem");
        Label_0161: {
            if (o != null) {
                if (o instanceof LogChute) {
                    try {
                        ((LogChute)o).init(rsvc);
                        return (LogChute)o;
                    }
                    catch (Exception e) {
                        log.error("Could not init runtime.log.logsystem " + o, e);
                        break Label_0161;
                    }
                }
                if (o instanceof LogSystem) {
                    log.info("LogSystem has been deprecated. Please use a LogChute implementation.");
                    try {
                        final LogChute chute = new LogChuteSystem((LogSystem)o);
                        chute.init(rsvc);
                        return chute;
                    }
                    catch (Exception e) {
                        log.error("Could not init runtime.log.logsystem " + o, e);
                        break Label_0161;
                    }
                }
                log.warn(o.getClass().getName() + " object set as runtime.log.logsystem is not a valid log implementation.");
            }
        }
        List classes = new ArrayList();
        final Object obj = rsvc.getProperty("runtime.log.logsystem.class");
        if (obj instanceof List) {
            classes = (List)obj;
        }
        else if (obj instanceof String) {
            classes.add(obj);
        }
        for (final String claz : classes) {
            if (claz != null && claz.length() > 0) {
                log.debug("Trying to use logger class " + claz);
                try {
                    o = ClassUtils.getNewInstance(claz);
                    if (o instanceof LogChute) {
                        ((LogChute)o).init(rsvc);
                        log.debug("Using logger class " + claz);
                        return (LogChute)o;
                    }
                    if (o instanceof LogSystem) {
                        log.info("LogSystem has been deprecated. Please use a LogChute implementation.");
                        final LogChute chute2 = new LogChuteSystem((LogSystem)o);
                        chute2.init(rsvc);
                        return chute2;
                    }
                    log.error("The specifid logger class " + claz + " isn't a valid LogChute implementation.");
                }
                catch (NoClassDefFoundError ncdfe) {
                    log.debug("Couldn't find class " + claz + " or necessary supporting classes in classpath.", ncdfe);
                }
                catch (Exception e2) {
                    log.info("Failed to initialize an instance of " + claz + " with the current runtime configuration.", e2);
                }
            }
        }
        final LogChute slc = new SystemLogChute();
        slc.init(rsvc);
        log.debug("Using SystemLogChute.");
        return slc;
    }
    
    public static void updateLog(final Log log, final RuntimeServices rsvc) throws Exception {
        final LogChute newLogChute = createLogChute(rsvc);
        final LogChute oldLogChute = log.getLogChute();
        if (oldLogChute instanceof HoldingLogChute) {
            final HoldingLogChute hlc = (HoldingLogChute)oldLogChute;
            hlc.transferTo(newLogChute);
        }
        log.setLogChute(newLogChute);
    }
}
