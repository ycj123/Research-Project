// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.logging;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Properties;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Hashtable;

public abstract class LogFactory
{
    public static final String FACTORY_PROPERTY = "org.mudebug.prapr.reloc.commons.logging.LogFactory";
    public static final String FACTORY_DEFAULT = "org.mudebug.prapr.reloc.commons.logging.impl.LogFactoryImpl";
    public static final String FACTORY_PROPERTIES = "commons-logging.properties";
    protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
    protected static Hashtable factories;
    
    protected LogFactory() {
    }
    
    public abstract Object getAttribute(final String p0);
    
    public abstract String[] getAttributeNames();
    
    public abstract Log getInstance(final Class p0) throws LogConfigurationException;
    
    public abstract Log getInstance(final String p0) throws LogConfigurationException;
    
    public abstract void release();
    
    public abstract void removeAttribute(final String p0);
    
    public abstract void setAttribute(final String p0, final Object p1);
    
    public static LogFactory getFactory() throws LogConfigurationException {
        final ClassLoader contextClassLoader = AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new PrivilegedAction() {
            public Object run() {
                return LogFactory.getContextClassLoader();
            }
        });
        LogFactory factory = getCachedFactory(contextClassLoader);
        if (factory != null) {
            return factory;
        }
        Properties props = null;
        try {
            final InputStream stream = getResourceAsStream(contextClassLoader, "commons-logging.properties");
            if (stream != null) {
                props = new Properties();
                props.load(stream);
                stream.close();
            }
        }
        catch (IOException e) {}
        catch (SecurityException ex) {}
        try {
            final String factoryClass = System.getProperty("org.mudebug.prapr.reloc.commons.logging.LogFactory");
            if (factoryClass != null) {
                factory = newFactory(factoryClass, contextClassLoader);
            }
        }
        catch (SecurityException ex2) {}
        if (factory == null) {
            try {
                final InputStream is = getResourceAsStream(contextClassLoader, "META-INF/services/org.apache.commons.logging.LogFactory");
                if (is != null) {
                    BufferedReader rd;
                    try {
                        rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    }
                    catch (UnsupportedEncodingException e2) {
                        rd = new BufferedReader(new InputStreamReader(is));
                    }
                    final String factoryClassName = rd.readLine();
                    rd.close();
                    if (factoryClassName != null && !"".equals(factoryClassName)) {
                        factory = newFactory(factoryClassName, contextClassLoader);
                    }
                }
            }
            catch (Exception ex3) {}
        }
        if (factory == null && props != null) {
            final String factoryClass = props.getProperty("org.mudebug.prapr.reloc.commons.logging.LogFactory");
            if (factoryClass != null) {
                factory = newFactory(factoryClass, contextClassLoader);
            }
        }
        if (factory == null) {
            factory = newFactory("org.mudebug.prapr.reloc.commons.logging.impl.LogFactoryImpl", LogFactory.class.getClassLoader());
        }
        if (factory != null) {
            cacheFactory(contextClassLoader, factory);
            if (props != null) {
                final Enumeration names = props.propertyNames();
                while (names.hasMoreElements()) {
                    final String name = names.nextElement();
                    final String value = props.getProperty(name);
                    factory.setAttribute(name, value);
                }
            }
        }
        return factory;
    }
    
    public static Log getLog(final Class clazz) throws LogConfigurationException {
        return getFactory().getInstance(clazz);
    }
    
    public static Log getLog(final String name) throws LogConfigurationException {
        return getFactory().getInstance(name);
    }
    
    public static void release(final ClassLoader classLoader) {
        synchronized (LogFactory.factories) {
            final LogFactory factory = LogFactory.factories.get(classLoader);
            if (factory != null) {
                factory.release();
                LogFactory.factories.remove(classLoader);
            }
        }
    }
    
    public static void releaseAll() {
        synchronized (LogFactory.factories) {
            final Enumeration elements = LogFactory.factories.elements();
            while (elements.hasMoreElements()) {
                final LogFactory element = elements.nextElement();
                element.release();
            }
            LogFactory.factories.clear();
        }
    }
    
    protected static ClassLoader getContextClassLoader() throws LogConfigurationException {
        ClassLoader classLoader = null;
        try {
            final Method method = Thread.class.getMethod("getContextClassLoader", (Class[])null);
            try {
                classLoader = (ClassLoader)method.invoke(Thread.currentThread(), (Object[])null);
            }
            catch (IllegalAccessException e) {
                throw new LogConfigurationException("Unexpected IllegalAccessException", e);
            }
            catch (InvocationTargetException e2) {
                if (!(e2.getTargetException() instanceof SecurityException)) {
                    throw new LogConfigurationException("Unexpected InvocationTargetException", e2.getTargetException());
                }
            }
        }
        catch (NoSuchMethodException e3) {
            classLoader = LogFactory.class.getClassLoader();
        }
        return classLoader;
    }
    
    private static LogFactory getCachedFactory(final ClassLoader contextClassLoader) {
        LogFactory factory = null;
        if (contextClassLoader != null) {
            factory = LogFactory.factories.get(contextClassLoader);
        }
        return factory;
    }
    
    private static void cacheFactory(final ClassLoader classLoader, final LogFactory factory) {
        if (classLoader != null && factory != null) {
            LogFactory.factories.put(classLoader, factory);
        }
    }
    
    protected static LogFactory newFactory(final String factoryClass, final ClassLoader classLoader) throws LogConfigurationException {
        final Object result = AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            static /* synthetic */ Class class$org$apache$commons$logging$LogFactory;
            
            public Object run() {
                Class logFactoryClass = null;
                try {
                    if (classLoader != null) {
                        try {
                            logFactoryClass = classLoader.loadClass(factoryClass);
                            return logFactoryClass.newInstance();
                        }
                        catch (ClassNotFoundException ex) {
                            if (classLoader == ((LogFactory$2.class$org$apache$commons$logging$LogFactory == null) ? (LogFactory$2.class$org$apache$commons$logging$LogFactory = class$("org.mudebug.prapr.reloc.commons.logging.LogFactory")) : LogFactory$2.class$org$apache$commons$logging$LogFactory).getClassLoader()) {
                                throw ex;
                            }
                        }
                        catch (NoClassDefFoundError e) {
                            if (classLoader == ((LogFactory$2.class$org$apache$commons$logging$LogFactory == null) ? (LogFactory$2.class$org$apache$commons$logging$LogFactory = class$("org.mudebug.prapr.reloc.commons.logging.LogFactory")) : LogFactory$2.class$org$apache$commons$logging$LogFactory).getClassLoader()) {
                                throw e;
                            }
                        }
                        catch (ClassCastException e2) {
                            if (classLoader == ((LogFactory$2.class$org$apache$commons$logging$LogFactory == null) ? (LogFactory$2.class$org$apache$commons$logging$LogFactory = class$("org.mudebug.prapr.reloc.commons.logging.LogFactory")) : LogFactory$2.class$org$apache$commons$logging$LogFactory).getClassLoader()) {
                                throw e2;
                            }
                        }
                    }
                    logFactoryClass = Class.forName(factoryClass);
                    return logFactoryClass.newInstance();
                }
                catch (Exception e3) {
                    if (logFactoryClass != null && !((LogFactory$2.class$org$apache$commons$logging$LogFactory == null) ? (LogFactory$2.class$org$apache$commons$logging$LogFactory = class$("org.mudebug.prapr.reloc.commons.logging.LogFactory")) : LogFactory$2.class$org$apache$commons$logging$LogFactory).isAssignableFrom(logFactoryClass)) {
                        return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", e3);
                    }
                    return new LogConfigurationException(e3);
                }
            }
            
            static /* synthetic */ Class class$(final String x0) {
                try {
                    return Class.forName(x0);
                }
                catch (ClassNotFoundException x) {
                    throw new NoClassDefFoundError(x.getMessage());
                }
            }
        });
        if (result instanceof LogConfigurationException) {
            throw (LogConfigurationException)result;
        }
        return (LogFactory)result;
    }
    
    private static InputStream getResourceAsStream(final ClassLoader loader, final String name) {
        return AccessController.doPrivileged((PrivilegedAction<InputStream>)new PrivilegedAction() {
            public Object run() {
                if (loader != null) {
                    return loader.getResourceAsStream(name);
                }
                return ClassLoader.getSystemResourceAsStream(name);
            }
        });
    }
    
    static {
        LogFactory.factories = new Hashtable();
    }
}
