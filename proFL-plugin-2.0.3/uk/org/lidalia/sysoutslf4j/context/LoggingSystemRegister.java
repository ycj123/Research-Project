// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.Iterator;
import org.slf4j.LoggerFactory;
import java.util.Set;
import org.slf4j.Logger;

class LoggingSystemRegister
{
    private static final Logger LOG;
    private final Set<String> loggingSystemNameFragments;
    
    static {
        LOG = LoggerFactory.getLogger((Class)SysOutOverSLF4J.class);
    }
    
    void registerLoggingSystem(final String packageName) {
        this.loggingSystemNameFragments.add(packageName);
        LoggingSystemRegister.LOG.info("Package {} registered; all classes within it or subpackages of it will be allowed to print to System.out and System.err", (Object)packageName);
    }
    
    void unregisterLoggingSystem(final String packageName) {
        if (this.loggingSystemNameFragments.remove(packageName)) {
            LoggingSystemRegister.LOG.info("Package {} unregistered; all classes within it or subpackages of it will have System.out and System.err redirected to SLF4J", (Object)packageName);
        }
    }
    
    boolean isInLoggingSystem(final String className) {
        boolean isInLoggingSystem = false;
        for (final String packageName : this.loggingSystemNameFragments) {
            if (className.startsWith(packageName)) {
                isInLoggingSystem = true;
                break;
            }
        }
        return isInLoggingSystem;
    }
    
    LoggingSystemRegister() {
        this.loggingSystemNameFragments = new CopyOnWriteArraySet<String>();
    }
}
