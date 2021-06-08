// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

import java.util.HashMap;
import java.io.IOException;
import org.apache.log.LogTarget;
import org.apache.log.format.Formatter;
import org.apache.log.output.io.FileTarget;
import java.io.File;
import org.apache.log.Priority;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import org.apache.log.Hierarchy;
import java.util.Map;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.log.Logger;

public class AvalonLogChute implements LogChute
{
    public static final String AVALON_LOGGER = "runtime.log.logsystem.avalon.logger";
    public static final String AVALON_LOGGER_FORMAT = "runtime.log.logsystem.avalon.format";
    public static final String AVALON_LOGGER_LEVEL = "runtime.log.logsystem.avalon.level";
    private Logger logger;
    private RuntimeServices rsvc;
    private static final Map logLevels;
    
    public AvalonLogChute() {
        this.logger = null;
        this.rsvc = null;
    }
    
    public void init(final RuntimeServices rs) throws Exception {
        this.rsvc = rs;
        final String name = (String)this.rsvc.getProperty("runtime.log.logsystem.avalon.logger");
        if (name != null) {
            this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(name);
        }
        else {
            this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(this.rsvc.toString());
            final String file = (String)this.rsvc.getProperty("runtime.log");
            if (StringUtils.isNotEmpty(file)) {
                this.initTarget(file, this.rsvc);
            }
        }
    }
    
    private void initTarget(final String file, final RuntimeServices rsvc) throws Exception {
        try {
            String format = null;
            Priority level = null;
            if (rsvc != null) {
                format = rsvc.getString("runtime.log.logsystem.avalon.format", "%{time} %{message}\\n%{throwable}");
                level = AvalonLogChute.logLevels.get(rsvc.getString("runtime.log.logsystem.avalon.level", "debug"));
            }
            final VelocityFormatter vf = new VelocityFormatter(format);
            final FileTarget target = new FileTarget(new File(file), false, (Formatter)vf);
            this.logger.setPriority(level);
            this.logger.setLogTargets(new LogTarget[] { (LogTarget)target });
            this.log(0, "AvalonLogChute initialized using file '" + file + '\'');
        }
        catch (IOException ioe) {
            rsvc.getLog().warn("Unable to create log file for AvalonLogChute", ioe);
            throw new Exception("Error configuring AvalonLogChute : " + ioe);
        }
    }
    
    public void init(final String file) throws Exception {
        this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(this.rsvc.toString());
        this.initTarget(file, null);
        this.log(2, "You shouldn't be using the init(String file) method!");
    }
    
    public void log(final int level, final String message) {
        switch (level) {
            case 2: {
                this.logger.warn("  [warn] " + message);
                break;
            }
            case 1: {
                this.logger.info("  [info] " + message);
                break;
            }
            case 0: {
                this.logger.debug(" [debug] " + message);
                break;
            }
            case -1: {
                this.logger.debug(" [trace] " + message);
                break;
            }
            case 3: {
                this.logger.error(" [error] " + message);
                break;
            }
            default: {
                this.logger.info(message);
                break;
            }
        }
    }
    
    public void log(final int level, final String message, final Throwable t) {
        switch (level) {
            case 2: {
                this.logger.warn("  [warn] " + message, t);
                break;
            }
            case 1: {
                this.logger.info("  [info] " + message, t);
                break;
            }
            case 0: {
                this.logger.debug(" [debug] " + message, t);
                break;
            }
            case -1: {
                this.logger.debug(" [trace] " + message, t);
                break;
            }
            case 3: {
                this.logger.error(" [error] " + message, t);
                break;
            }
            default: {
                this.logger.info(message, t);
                break;
            }
        }
    }
    
    public boolean isLevelEnabled(final int level) {
        switch (level) {
            case -1:
            case 0: {
                return this.logger.isDebugEnabled();
            }
            case 1: {
                return this.logger.isInfoEnabled();
            }
            case 2: {
                return this.logger.isWarnEnabled();
            }
            case 3: {
                return this.logger.isErrorEnabled();
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
        this.logger.unsetLogTargets();
    }
    
    static {
        (logLevels = new HashMap()).put("trace", Priority.DEBUG);
        AvalonLogChute.logLevels.put("debug", Priority.DEBUG);
        AvalonLogChute.logLevels.put("info", Priority.INFO);
        AvalonLogChute.logLevels.put("warn", Priority.WARN);
        AvalonLogChute.logLevels.put("error", Priority.ERROR);
    }
}
