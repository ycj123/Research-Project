// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

class SysOutOverSLF4JInitialiser
{
    private static final Logger LOG;
    private static final String UNKNOWN_LOGGING_SYSTEM_MESSAGE = "Your logging framework {} is not known - if it needs access to the standard println methods on the console you will need to register it by calling registerLoggingSystemPackage";
    private static final String LOGGING_SYSTEM_DOES_NOT_NEED_PRINTLN_MESSAGE = "Your logging framework {} should not need access to the standard println methods on the console, so you should not need to register a logging system package.";
    private static final String[] LOGGING_SYSTEMS_THAT_DO_NOT_ACCESS_CONSOLE;
    private static final String[] LOGGING_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE;
    private final LoggingSystemRegister loggingSystemRegister;
    
    static {
        LOG = LoggerFactory.getLogger((Class)SysOutOverSLF4JInitialiser.class);
        LOGGING_SYSTEMS_THAT_DO_NOT_ACCESS_CONSOLE = new String[] { "ch.qos.logback.", "org.slf4j.impl.Log4jLoggerAdapter", "org.slf4j.impl.JDK14LoggerAdapter", "org.apache.log4j." };
        LOGGING_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE = new String[] { "org.x4juli.", "org.grlea.log.", "org.slf4j.impl.SimpleLogger" };
    }
    
    SysOutOverSLF4JInitialiser(final LoggingSystemRegister loggingSystemRegister) {
        this.loggingSystemRegister = loggingSystemRegister;
    }
    
    void initialise(final Logger currentLoggerImplementation) {
        if (this.loggingSystemKnownAndMightAccessConsoleViaPrintln(currentLoggerImplementation)) {
            this.registerCurrentLoggingSystemPackage(currentLoggerImplementation);
        }
        else if (this.loggingSystemDoesNotAccessConsoleViaPrintln(currentLoggerImplementation)) {
            SysOutOverSLF4JInitialiser.LOG.debug("Your logging framework {} should not need access to the standard println methods on the console, so you should not need to register a logging system package.", (Object)currentLoggerImplementation.getClass());
        }
        else {
            SysOutOverSLF4JInitialiser.LOG.warn("Your logging framework {} is not known - if it needs access to the standard println methods on the console you will need to register it by calling registerLoggingSystemPackage", (Object)currentLoggerImplementation.getClass());
        }
    }
    
    private boolean loggingSystemDoesNotAccessConsoleViaPrintln(final Logger currentLoggerImplementation) {
        boolean loggingSystemDoesNotAccessConsoleViaPrintln = false;
        String[] logging_SYSTEMS_THAT_DO_NOT_ACCESS_CONSOLE;
        for (int length = (logging_SYSTEMS_THAT_DO_NOT_ACCESS_CONSOLE = SysOutOverSLF4JInitialiser.LOGGING_SYSTEMS_THAT_DO_NOT_ACCESS_CONSOLE).length, i = 0; i < length; ++i) {
            final String loggingPackage = logging_SYSTEMS_THAT_DO_NOT_ACCESS_CONSOLE[i];
            if (this.usingLogFramework(currentLoggerImplementation, loggingPackage)) {
                loggingSystemDoesNotAccessConsoleViaPrintln = true;
                break;
            }
        }
        return loggingSystemDoesNotAccessConsoleViaPrintln;
    }
    
    private boolean loggingSystemKnownAndMightAccessConsoleViaPrintln(final Logger currentLoggerImplementation) {
        boolean loggingSystemKnownAndMightAccessConsoleViaPrintln = false;
        String[] logging_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE;
        for (int length = (logging_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE = SysOutOverSLF4JInitialiser.LOGGING_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE).length, i = 0; i < length; ++i) {
            final String loggingPackage = logging_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE[i];
            if (this.usingLogFramework(currentLoggerImplementation, loggingPackage)) {
                loggingSystemKnownAndMightAccessConsoleViaPrintln = true;
                break;
            }
        }
        return loggingSystemKnownAndMightAccessConsoleViaPrintln;
    }
    
    private void registerCurrentLoggingSystemPackage(final Logger currentLoggerImplementation) {
        String[] logging_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE;
        for (int length = (logging_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE = SysOutOverSLF4JInitialiser.LOGGING_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE).length, i = 0; i < length; ++i) {
            final String loggingPackage = logging_SYSTEMS_THAT_MIGHT_ACCESS_CONSOLE[i];
            if (this.usingLogFramework(currentLoggerImplementation, loggingPackage)) {
                this.loggingSystemRegister.registerLoggingSystem(loggingPackage);
            }
        }
    }
    
    private boolean usingLogFramework(final Logger currentLoggerImplementation, final String packageName) {
        return currentLoggerImplementation.getClass().getName().startsWith(packageName);
    }
}
