// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.logging.impl;

import java.lang.reflect.Method;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextCleaner implements ServletContextListener
{
    private Class[] RELEASE_SIGNATURE;
    
    public ServletContextCleaner() {
        this.RELEASE_SIGNATURE = new Class[] { ClassLoader.class };
    }
    
    public void contextDestroyed(final ServletContextEvent sce) {
        final ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        final Object[] params = { tccl };
        ClassLoader loader = tccl;
        while (loader != null) {
            try {
                final Class logFactoryClass = loader.loadClass("org.mudebug.prapr.reloc.commons.logging.LogFactory");
                final Method releaseMethod = logFactoryClass.getMethod("release", (Class[])this.RELEASE_SIGNATURE);
                releaseMethod.invoke(null, params);
                loader = logFactoryClass.getClassLoader().getParent();
            }
            catch (ClassNotFoundException ex) {
                loader = null;
            }
            catch (NoSuchMethodException ex2) {
                System.err.println("LogFactory instance found which does not support release method!");
                loader = null;
            }
            catch (IllegalAccessException ex3) {
                System.err.println("LogFactory instance found which is not accessable!");
                loader = null;
            }
            catch (InvocationTargetException ex4) {
                System.err.println("LogFactory instance release method failed!");
                loader = null;
            }
        }
        LogFactory.release(tccl);
    }
    
    public void contextInitialized(final ServletContextEvent sce) {
    }
}
