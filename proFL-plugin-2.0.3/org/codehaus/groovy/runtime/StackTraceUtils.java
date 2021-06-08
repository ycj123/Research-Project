// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.Enumeration;
import java.util.logging.LogManager;
import java.util.Iterator;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.ArrayList;
import groovy.lang.Closure;
import java.util.List;
import java.util.logging.Logger;

public class StackTraceUtils
{
    public static final String STACK_LOG_NAME = "StackTrace";
    private static final Logger STACK_LOG;
    private static final String[] GROOVY_PACKAGES;
    private static List<Closure> tests;
    
    public static void addClassTest(final Closure test) {
        StackTraceUtils.tests.add(test);
    }
    
    public static Throwable sanitize(final Throwable t) {
        if (!Boolean.getBoolean("groovy.full.stacktrace")) {
            final StackTraceElement[] trace = t.getStackTrace();
            final List<StackTraceElement> newTrace = new ArrayList<StackTraceElement>();
            for (final StackTraceElement stackTraceElement : trace) {
                if (isApplicationClass(stackTraceElement.getClassName())) {
                    newTrace.add(stackTraceElement);
                }
            }
            StackTraceUtils.STACK_LOG.log(Level.WARNING, "Sanitizing stacktrace:", t);
            final StackTraceElement[] clean = new StackTraceElement[newTrace.size()];
            newTrace.toArray(clean);
            t.setStackTrace(clean);
        }
        return t;
    }
    
    public static void printSanitizedStackTrace(Throwable t, final PrintWriter p) {
        t = sanitize(t);
        final StackTraceElement[] arr$;
        final StackTraceElement[] trace = arr$ = t.getStackTrace();
        for (final StackTraceElement stackTraceElement : arr$) {
            p.println("at " + stackTraceElement.getClassName() + "(" + stackTraceElement.getMethodName() + ":" + stackTraceElement.getLineNumber() + ")");
        }
    }
    
    public static void printSanitizedStackTrace(final Throwable t) {
        printSanitizedStackTrace(t, new PrintWriter(System.err));
    }
    
    public static boolean isApplicationClass(final String className) {
        for (final Closure test : StackTraceUtils.tests) {
            final Object result = test.call(className);
            if (result != null) {
                return DefaultTypeTransformation.castToBoolean(result);
            }
        }
        for (final String groovyPackage : StackTraceUtils.GROOVY_PACKAGES) {
            if (className.startsWith(groovyPackage)) {
                return false;
            }
        }
        return true;
    }
    
    public static Throwable extractRootCause(final Throwable t) {
        Throwable result;
        for (result = t; result.getCause() != null; result = result.getCause()) {}
        return result;
    }
    
    public static Throwable sanitizeRootCause(final Throwable t) {
        return sanitize(extractRootCause(t));
    }
    
    public static Throwable deepSanitize(final Throwable t) {
        for (Throwable current = t; current.getCause() != null; current = sanitize(current.getCause())) {}
        return sanitize(t);
    }
    
    static {
        final Enumeration existingLogs = LogManager.getLogManager().getLoggerNames();
        while (true) {
            while (existingLogs.hasMoreElements()) {
                if ("StackTrace".equals(existingLogs.nextElement())) {
                    STACK_LOG = Logger.getLogger("StackTrace");
                    GROOVY_PACKAGES = System.getProperty("groovy.sanitized.stacktraces", "groovy.,org.codehaus.groovy.,java.,javax.,sun.,gjdk.groovy.,").split("(\\s|,)+");
                    StackTraceUtils.tests = new ArrayList<Closure>();
                    return;
                }
            }
            (STACK_LOG = Logger.getLogger("StackTrace")).setUseParentHandlers(false);
            continue;
        }
    }
}
