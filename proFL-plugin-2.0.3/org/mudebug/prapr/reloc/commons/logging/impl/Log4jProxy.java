// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.logging.impl;

import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.net.URL;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.lang.reflect.Method;
import java.io.Serializable;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class Log4jProxy implements Log, Serializable
{
    private static final long serialVersionUID = 1L;
    private static final String PROXY_FQCN = "org.mudebug.prapr.reloc.commons.logging.impl.Log4JLogger";
    private Object FATAL;
    private Object ERROR;
    private Object WARN;
    private Object INFO;
    private Object DEBUG;
    private Object TRACE;
    private transient Object logger;
    private Method isEnabledFor;
    private Method log;
    
    static ClassLoader threadContextClassLoader() {
        final PrivilegedAction action = new PrivilegedAction() {
            public Object run() {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                try {
                    final Class levelClass = loader.loadClass("org.apache.log4j.Level");
                    final Class loggerClass = loader.loadClass("org.apache.log4j.Logger");
                    final Class categoryClass = loader.loadClass("org.apache.log4j.Category");
                    final Class priorityClass = loader.loadClass("org.apache.log4j.Priority");
                    final ClassLoader testCL = levelClass.getClassLoader();
                    if (testCL != loggerClass.getClassLoader() || testCL != categoryClass.getClassLoader() || testCL != loggerClass.getClassLoader() || testCL != priorityClass.getClassLoader()) {
                        loader = Log4jProxy.class.getClassLoader();
                    }
                }
                catch (ClassNotFoundException e) {
                    loader = Log4jProxy.class.getClassLoader();
                }
                return loader;
            }
        };
        final ClassLoader tcl = AccessController.doPrivileged((PrivilegedAction<ClassLoader>)action);
        return tcl;
    }
    
    Log4jProxy(final String name) {
        this.logger = null;
        final ClassLoader tcl = threadContextClassLoader();
        Class levelClass = null;
        Class priorityClass = null;
        Class loggerClass = null;
        try {
            levelClass = tcl.loadClass("org.apache.log4j.Level");
            final Class[] sig = { String.class };
            Method toLevel = levelClass.getMethod("toLevel", (Class[])sig);
            final String[] level = { "FATAL" };
            this.FATAL = toLevel.invoke(null, (Object[])level);
            level[0] = "ERROR";
            this.ERROR = toLevel.invoke(null, (Object[])level);
            level[0] = "WARN";
            this.WARN = toLevel.invoke(null, (Object[])level);
            level[0] = "INFO";
            this.INFO = toLevel.invoke(null, (Object[])level);
            level[0] = "DEBUG";
            this.DEBUG = toLevel.invoke(null, (Object[])level);
            this.TRACE = this.DEBUG;
            try {
                try {
                    this.TRACE = levelClass.getDeclaredField("TRACE").get(null);
                }
                catch (Exception ex) {
                    levelClass = tcl.loadClass("org.jboss.logging.XLevel");
                    final Class[] toLevelSig = { String.class, this.DEBUG.getClass() };
                    toLevel = levelClass.getMethod("toLevel", (Class[])toLevelSig);
                    final Object[] args = { "TRACE", this.DEBUG };
                    this.TRACE = toLevel.invoke(null, args);
                }
            }
            catch (Throwable t2) {}
            loggerClass = tcl.loadClass("org.apache.log4j.Logger");
            final Method getLogger = loggerClass.getMethod("getLogger", (Class[])sig);
            final Object[] args2 = { name };
            this.logger = getLogger.invoke(null, args2);
            priorityClass = tcl.loadClass("org.apache.log4j.Priority");
            final Class[] isEnabledForSig = { priorityClass };
            this.isEnabledFor = loggerClass.getMethod("isEnabledFor", (Class[])isEnabledForSig);
            final Class[] logSig = { String.class, priorityClass, Object.class, Throwable.class };
            this.log = loggerClass.getMethod("log", (Class[])logSig);
        }
        catch (Throwable t) {
            final StringBuffer msg = new StringBuffer();
            msg.append("[levelClass, ");
            if (levelClass != null) {
                displayClassInfo(levelClass, msg);
            }
            else {
                msg.append("null");
            }
            msg.append("]; ");
            msg.append("[priorityClass, ");
            if (priorityClass != null) {
                displayClassInfo(priorityClass, msg);
            }
            else {
                msg.append("null");
            }
            msg.append("]");
            msg.append("]; ");
            msg.append("[loggerClass, ");
            if (loggerClass != null) {
                displayClassInfo(loggerClass, msg);
                msg.append(", Methods:\n");
                final Method[] methods = loggerClass.getMethods();
                for (int n = 0; n < methods.length; ++n) {
                    final Method m = methods[n];
                    msg.append(m);
                    msg.append('\n');
                    if (m.getName().equals("isEnabledFor")) {
                        final Class[] sig2 = m.getParameterTypes();
                        displayClassInfo(sig2[0], msg);
                    }
                }
            }
            else {
                msg.append("null");
            }
            msg.append("]");
            throw new UndeclaredThrowableException(t, msg.toString());
        }
    }
    
    public boolean isDebugEnabled() {
        return this.isEnabledFor(this.DEBUG);
    }
    
    public boolean isErrorEnabled() {
        return this.isEnabledFor(this.ERROR);
    }
    
    public boolean isFatalEnabled() {
        return this.isEnabledFor(this.FATAL);
    }
    
    public boolean isInfoEnabled() {
        return this.isEnabledFor(this.INFO);
    }
    
    public boolean isTraceEnabled() {
        return this.isEnabledFor(this.TRACE);
    }
    
    public boolean isWarnEnabled() {
        return this.isEnabledFor(this.WARN);
    }
    
    public void trace(final Object message) {
        this.log(this.TRACE, message, null);
    }
    
    public void trace(final Object message, final Throwable t) {
        this.log(this.TRACE, message, t);
    }
    
    public void debug(final Object message) {
        this.log(this.DEBUG, message, null);
    }
    
    public void debug(final Object message, final Throwable t) {
        this.log(this.DEBUG, message, t);
    }
    
    public void info(final Object message) {
        this.log(this.INFO, message, null);
    }
    
    public void info(final Object message, final Throwable t) {
        this.log(this.INFO, message, t);
    }
    
    public void warn(final Object message) {
        this.log(this.WARN, message, null);
    }
    
    public void warn(final Object message, final Throwable t) {
        this.log(this.WARN, message, t);
    }
    
    public void error(final Object message) {
        this.log(this.ERROR, message, null);
    }
    
    public void error(final Object message, final Throwable t) {
        this.log(this.ERROR, message, t);
    }
    
    public void fatal(final Object message) {
        this.log(this.FATAL, message, null);
    }
    
    public void fatal(final Object message, final Throwable t) {
        this.log(this.FATAL, message, t);
    }
    
    private void log(final Object level, final Object message, final Throwable t) {
        final Object[] args = { "org.mudebug.prapr.reloc.commons.logging.impl.Log4JLogger", level, message, t };
        try {
            this.log.invoke(this.logger, args);
        }
        catch (IllegalAccessException e) {
            throw new UndeclaredThrowableException(e);
        }
        catch (InvocationTargetException e2) {
            throw new UndeclaredThrowableException(e2.getTargetException());
        }
    }
    
    private boolean isEnabledFor(final Object level) {
        final Object[] args = { level };
        try {
            final Boolean flag = (Boolean)this.isEnabledFor.invoke(this.logger, args);
            return flag;
        }
        catch (IllegalAccessException e) {
            throw new UndeclaredThrowableException(e);
        }
        catch (InvocationTargetException e2) {
            throw new UndeclaredThrowableException(e2.getTargetException());
        }
    }
    
    public static void displayClassInfo(final Class clazz, final StringBuffer results) {
        final ClassLoader cl = clazz.getClassLoader();
        results.append("\n" + clazz.getName() + "(" + Integer.toHexString(clazz.hashCode()) + ").ClassLoader=" + cl);
        for (ClassLoader parent = cl; parent != null; parent = parent.getParent()) {
            results.append("\n.." + parent);
            final URL[] urls = getClassLoaderURLs(parent);
            for (int length = (urls != null) ? urls.length : 0, u = 0; u < length; ++u) {
                results.append("\n...." + urls[u]);
            }
            if (parent != null) {}
        }
        final CodeSource clazzCS = clazz.getProtectionDomain().getCodeSource();
        if (clazzCS != null) {
            results.append("\n++++CodeSource: " + clazzCS);
        }
        else {
            results.append("\n++++Null CodeSource");
        }
        results.append("\nImplemented Interfaces:");
        final Class[] ifaces = clazz.getInterfaces();
        for (int i = 0; i < ifaces.length; ++i) {
            final Class iface = ifaces[i];
            results.append("\n++" + iface + "(" + Integer.toHexString(iface.hashCode()) + ")");
            final ClassLoader loader = ifaces[i].getClassLoader();
            results.append("\n++++ClassLoader: " + loader);
            final ProtectionDomain pd = ifaces[i].getProtectionDomain();
            final CodeSource cs = pd.getCodeSource();
            if (cs != null) {
                results.append("\n++++CodeSource: " + cs);
            }
            else {
                results.append("\n++++Null CodeSource");
            }
        }
    }
    
    public static URL[] getClassLoaderURLs(final ClassLoader cl) {
        URL[] urls = new URL[0];
        try {
            final Class returnType = urls.getClass();
            final Class[] parameterTypes = new Class[0];
            final Class clClass = cl.getClass();
            final Method getURLs = clClass.getMethod("getURLs", (Class[])parameterTypes);
            if (returnType.isAssignableFrom(getURLs.getReturnType())) {
                final Object[] args = new Object[0];
                urls = (URL[])getURLs.invoke(cl, args);
            }
            if (urls == null || urls.length == 0) {
                final Method getCp = clClass.getMethod("getClasspath", (Class[])parameterTypes);
                if (returnType.isAssignableFrom(getCp.getReturnType())) {
                    final Object[] args2 = new Object[0];
                    urls = (URL[])getCp.invoke(cl, args2);
                }
            }
        }
        catch (Exception ex) {}
        return urls;
    }
}
