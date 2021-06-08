// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.reflection.ReflectionUtils;
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.regex.Matcher;
import groovy.lang.Closure;

public class DefaultGroovyStaticMethods
{
    public static Thread start(final Thread self, final Closure closure) {
        return createThread(null, false, closure);
    }
    
    public static Thread start(final Thread self, final String name, final Closure closure) {
        return createThread(name, false, closure);
    }
    
    public static Thread startDaemon(final Thread self, final Closure closure) {
        return createThread(null, true, closure);
    }
    
    public static Thread startDaemon(final Thread self, final String name, final Closure closure) {
        return createThread(name, true, closure);
    }
    
    private static Thread createThread(final String name, final boolean daemon, final Closure closure) {
        final Thread thread = (name != null) ? new Thread(closure, name) : new Thread(closure);
        if (daemon) {
            thread.setDaemon(true);
        }
        thread.start();
        return thread;
    }
    
    public static Matcher getLastMatcher(final Matcher self) {
        return RegexSupport.getLastMatcher();
    }
    
    private static void sleepImpl(final long millis, final Closure closure) {
        final long start = System.currentTimeMillis();
        long rest = millis;
        while (rest > 0L) {
            try {
                Thread.sleep(rest);
                rest = 0L;
            }
            catch (InterruptedException e) {
                if (closure != null && DefaultTypeTransformation.castToBoolean(closure.call(e))) {
                    return;
                }
                final long current = System.currentTimeMillis();
                rest = millis + start - current;
            }
        }
    }
    
    public static void sleep(final Object self, final long milliseconds) {
        sleepImpl(milliseconds, null);
    }
    
    public static void sleep(final Object self, final long milliseconds, final Closure onInterrupt) {
        sleepImpl(milliseconds, onInterrupt);
    }
    
    public static Date parse(final Date self, final String format, final String input) throws ParseException {
        return new SimpleDateFormat(format).parse(input);
    }
    
    public static ResourceBundle getBundle(final ResourceBundle self, final String bundleName) {
        return getBundle(self, bundleName, Locale.getDefault());
    }
    
    public static ResourceBundle getBundle(final ResourceBundle self, final String bundleName, final Locale locale) {
        final Class c = ReflectionUtils.getCallingClass();
        ClassLoader targetCL = (c != null) ? c.getClassLoader() : null;
        if (targetCL == null) {
            targetCL = ClassLoader.getSystemClassLoader();
        }
        return ResourceBundle.getBundle(bundleName, locale, targetCL);
    }
}
