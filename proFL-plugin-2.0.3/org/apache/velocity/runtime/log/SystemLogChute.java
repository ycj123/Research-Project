// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.log;

import org.apache.velocity.runtime.RuntimeServices;

public class SystemLogChute implements LogChute
{
    public static final String RUNTIME_LOG_LEVEL_KEY = "runtime.log.logsystem.system.level";
    public static final String RUNTIME_LOG_SYSTEM_ERR_LEVEL_KEY = "runtime.log.logsystem.system.err.level";
    private int enabled;
    private int errLevel;
    
    public SystemLogChute() {
        this.enabled = -1;
        this.errLevel = -1;
    }
    
    public void init(final RuntimeServices rs) throws Exception {
        final String level = (String)rs.getProperty("runtime.log.logsystem.system.level");
        if (level != null) {
            this.setEnabledLevel(this.toLevel(level));
        }
        final String errLevel = (String)rs.getProperty("runtime.log.logsystem.system.err.level");
        if (errLevel != null) {
            this.setSystemErrLevel(this.toLevel(errLevel));
        }
    }
    
    protected int toLevel(final String level) {
        if (level.equalsIgnoreCase("debug")) {
            return 0;
        }
        if (level.equalsIgnoreCase("info")) {
            return 1;
        }
        if (level.equalsIgnoreCase("warn")) {
            return 2;
        }
        if (level.equalsIgnoreCase("error")) {
            return 3;
        }
        return -1;
    }
    
    protected String getPrefix(final int level) {
        switch (level) {
            case 2: {
                return "  [warn] ";
            }
            case 1: {
                return "  [info] ";
            }
            case 0: {
                return " [debug] ";
            }
            case -1: {
                return " [trace] ";
            }
            case 3: {
                return " [error] ";
            }
            default: {
                return "  [info] ";
            }
        }
    }
    
    public void log(final int level, final String message) {
        this.log(level, message, null);
    }
    
    public void log(final int level, final String message, final Throwable t) {
        if (!this.isLevelEnabled(level)) {
            return;
        }
        final String prefix = this.getPrefix(level);
        if (level >= this.errLevel) {
            System.err.print(prefix);
            System.err.println(message);
            if (t != null) {
                System.err.println(t.getMessage());
                t.printStackTrace();
            }
        }
        else {
            System.out.print(prefix);
            System.out.println(message);
            if (t != null) {
                System.out.println(t.getMessage());
                t.printStackTrace(System.out);
            }
        }
    }
    
    public void setEnabledLevel(final int level) {
        this.enabled = level;
    }
    
    public int getEnabledLevel() {
        return this.enabled;
    }
    
    public void setSystemErrLevel(final int level) {
        this.errLevel = level;
    }
    
    public int getSystemErrLevel() {
        return this.errLevel;
    }
    
    public boolean isLevelEnabled(final int level) {
        return level >= this.enabled;
    }
}
