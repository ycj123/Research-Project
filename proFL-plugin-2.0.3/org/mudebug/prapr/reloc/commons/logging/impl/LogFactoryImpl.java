// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.logging.impl;

import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.mudebug.prapr.reloc.commons.logging.LogConfigurationException;
import org.mudebug.prapr.reloc.commons.logging.Log;
import java.util.Enumeration;
import java.util.Vector;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.Hashtable;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;

public class LogFactoryImpl extends LogFactory
{
    public static final String LOG_PROPERTY = "org.mudebug.prapr.reloc.commons.logging.Log";
    protected static final String LOG_PROPERTY_OLD = "org.mudebug.prapr.reloc.commons.logging.log";
    private static final String LOG_INTERFACE = "org.mudebug.prapr.reloc.commons.logging.Log";
    protected Hashtable attributes;
    protected Hashtable instances;
    private String logClassName;
    protected Constructor logConstructor;
    protected Class[] logConstructorSignature;
    protected Method logMethod;
    protected Class[] logMethodSignature;
    
    public LogFactoryImpl() {
        this.attributes = new Hashtable();
        this.instances = new Hashtable();
        this.logConstructor = null;
        this.logConstructorSignature = new Class[] { String.class };
        this.logMethod = null;
        this.logMethodSignature = new Class[] { LogFactory.class };
    }
    
    public Object getAttribute(final String name) {
        return this.attributes.get(name);
    }
    
    public String[] getAttributeNames() {
        final Vector names = new Vector();
        final Enumeration keys = this.attributes.keys();
        while (keys.hasMoreElements()) {
            names.addElement(keys.nextElement());
        }
        final String[] results = new String[names.size()];
        for (int i = 0; i < results.length; ++i) {
            results[i] = names.elementAt(i);
        }
        return results;
    }
    
    public Log getInstance(final Class clazz) throws LogConfigurationException {
        return this.getInstance(clazz.getName());
    }
    
    public Log getInstance(final String name) throws LogConfigurationException {
        Log instance = this.instances.get(name);
        if (instance == null) {
            instance = this.newInstance(name);
            this.instances.put(name, instance);
        }
        return instance;
    }
    
    public void release() {
        this.instances.clear();
    }
    
    public void removeAttribute(final String name) {
        this.attributes.remove(name);
    }
    
    public void setAttribute(final String name, final Object value) {
        if (value == null) {
            this.attributes.remove(name);
        }
        else {
            this.attributes.put(name, value);
        }
    }
    
    protected String getLogClassName() {
        if (this.logClassName != null) {
            return this.logClassName;
        }
        this.logClassName = (String)this.getAttribute("org.mudebug.prapr.reloc.commons.logging.Log");
        if (this.logClassName == null) {
            this.logClassName = (String)this.getAttribute("org.mudebug.prapr.reloc.commons.logging.log");
        }
        if (this.logClassName == null) {
            try {
                this.logClassName = System.getProperty("org.mudebug.prapr.reloc.commons.logging.Log");
            }
            catch (SecurityException ex) {}
        }
        if (this.logClassName == null) {
            try {
                this.logClassName = System.getProperty("org.mudebug.prapr.reloc.commons.logging.log");
            }
            catch (SecurityException ex2) {}
        }
        if (this.logClassName == null && this.isLog4JAvailable()) {
            this.logClassName = "org.mudebug.prapr.reloc.commons.logging.impl.Log4JLogger";
        }
        if (this.logClassName == null && this.isJdk14Available()) {
            this.logClassName = "org.mudebug.prapr.reloc.commons.logging.impl.Jdk14Logger";
        }
        if (this.logClassName == null && this.isJdk13LumberjackAvailable()) {
            this.logClassName = "org.mudebug.prapr.reloc.commons.logging.impl.Jdk13LumberjackLogger";
        }
        if (this.logClassName == null) {
            this.logClassName = "org.mudebug.prapr.reloc.commons.logging.impl.SimpleLog";
        }
        return this.logClassName;
    }
    
    protected Constructor getLogConstructor() throws LogConfigurationException {
        if (this.logConstructor != null) {
            return this.logConstructor;
        }
        final String logClassName = this.getLogClassName();
        Class logClass = null;
        Class logInterface = null;
        try {
            logInterface = this.getClass().getClassLoader().loadClass("org.mudebug.prapr.reloc.commons.logging.Log");
            logClass = loadClass(logClassName);
            if (logClass == null) {
                throw new LogConfigurationException("No suitable Log implementation for " + logClassName);
            }
            if (!logInterface.isAssignableFrom(logClass)) {
                final Class[] interfaces = logClass.getInterfaces();
                for (int i = 0; i < interfaces.length; ++i) {
                    if ("org.mudebug.prapr.reloc.commons.logging.Log".equals(interfaces[i].getName())) {
                        throw new LogConfigurationException("Invalid class loader hierarchy.  You have more than one version of 'org.apache.commons.logging.Log' visible, which is not allowed.");
                    }
                }
                throw new LogConfigurationException("Class " + logClassName + " does not implement '" + "org.mudebug.prapr.reloc.commons.logging.Log" + "'.");
            }
        }
        catch (Throwable t) {
            throw new LogConfigurationException(t);
        }
        try {
            this.logMethod = logClass.getMethod("setLogFactory", (Class[])this.logMethodSignature);
        }
        catch (Throwable t) {
            this.logMethod = null;
        }
        try {
            return this.logConstructor = logClass.getConstructor((Class[])this.logConstructorSignature);
        }
        catch (Throwable t) {
            throw new LogConfigurationException("No suitable Log constructor " + this.logConstructorSignature + " for " + logClassName, t);
        }
    }
    
    private static Class loadClass(final String name) throws ClassNotFoundException {
        final Object result = AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            public Object run() {
                final ClassLoader threadCL = LogFactory.getContextClassLoader();
                if (threadCL != null) {
                    try {
                        return threadCL.loadClass(name);
                    }
                    catch (ClassNotFoundException ex) {}
                }
                try {
                    return Class.forName(name);
                }
                catch (ClassNotFoundException e) {
                    return e;
                }
            }
        });
        if (result instanceof Class) {
            return (Class)result;
        }
        throw (ClassNotFoundException)result;
    }
    
    protected boolean isJdk13LumberjackAvailable() {
        try {
            loadClass("java.util.logging.Logger");
            loadClass("org.mudebug.prapr.reloc.commons.logging.impl.Jdk13LumberjackLogger");
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    protected boolean isJdk14Available() {
        try {
            loadClass("java.util.logging.Logger");
            loadClass("org.mudebug.prapr.reloc.commons.logging.impl.Jdk14Logger");
            final Class throwable = loadClass("java.lang.Throwable");
            return throwable.getDeclaredMethod("getStackTrace", (Class[])null) != null;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    protected boolean isLog4JAvailable() {
        try {
            loadClass("org.apache.log4j.Logger");
            loadClass("org.mudebug.prapr.reloc.commons.logging.impl.Log4JLogger");
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    protected Log newInstance(final String name) throws LogConfigurationException {
        Log instance = null;
        try {
            final Object[] params = { name };
            instance = this.getLogConstructor().newInstance(params);
            if (this.logMethod != null) {
                params[0] = this;
                this.logMethod.invoke(instance, params);
            }
            return instance;
        }
        catch (InvocationTargetException e) {
            final Throwable c = e.getTargetException();
            if (c != null) {
                throw new LogConfigurationException(c);
            }
            throw new LogConfigurationException(e);
        }
        catch (Throwable t) {
            throw new LogConfigurationException(t);
        }
    }
}
