// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.context;

import uk.org.lidalia.sysoutslf4j.system.PrintStreamCoordinatorImpl;
import uk.org.lidalia.sysoutslf4j.common.ExceptionUtils;
import uk.org.lidalia.sysoutslf4j.common.ReflectionUtils;
import java.util.concurrent.Callable;
import uk.org.lidalia.sysoutslf4j.common.PrintStreamCoordinator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

final class PrintStreamCoordinatorFactory
{
    private static final String LINE_END;
    private static final Logger LOG;
    
    static {
        LINE_END = System.getProperty("line.separator");
        LOG = LoggerFactory.getLogger((Class)SysOutOverSLF4J.class);
    }
    
    static PrintStreamCoordinator createPrintStreamCoordinator() {
        Class<?> candidateCoordinatorClass = getConfiguratorClassFromSLF4JPrintStreamClassLoader();
        if (candidateCoordinatorClass == null) {
            candidateCoordinatorClass = getConfiguratorClassFromSystemClassLoader();
        }
        if (candidateCoordinatorClass == null) {
            candidateCoordinatorClass = getConfiguratorClassFromCurrentClassLoader();
        }
        checkCoordinator(candidateCoordinatorClass);
        return makeCoordinator(candidateCoordinatorClass);
    }
    
    private static PrintStreamCoordinator makeCoordinator(final Class<?> coordinatorClass) {
        return ExceptionUtils.doUnchecked((Callable<PrintStreamCoordinator>)new Callable<PrintStreamCoordinator>() {
            @Override
            public PrintStreamCoordinator call() throws InstantiationException, IllegalAccessException {
                final Object coordinator = coordinatorClass.newInstance();
                return ReflectionUtils.wrap(coordinator, PrintStreamCoordinator.class);
            }
        });
    }
    
    private static Class<?> getConfiguratorClassFromSLF4JPrintStreamClassLoader() {
        Class<?> configuratorClass;
        if (SysOutOverSLF4J.systemOutputsAreSLF4JPrintStreams()) {
            final ClassLoader classLoader = System.out.getClass().getClassLoader();
            configuratorClass = ClassLoaderUtils.loadClass(classLoader, PrintStreamCoordinatorImpl.class);
        }
        else {
            configuratorClass = null;
        }
        return configuratorClass;
    }
    
    private static Class<?> getConfiguratorClassFromSystemClassLoader() {
        Class<?> configuratorClass = null;
        try {
            configuratorClass = ClassLoader.getSystemClassLoader().loadClass(PrintStreamCoordinatorImpl.class.getName());
        }
        catch (Exception e) {
            PrintStreamCoordinatorFactory.LOG.debug("failed to load [" + PrintStreamCoordinatorImpl.class + "] from system class loader due to " + e);
        }
        return configuratorClass;
    }
    
    private static void checkCoordinator(final Class<?> candidateCoordinatorClass) {
        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        final boolean usingSystemClassLoader = ClassLoader.getSystemClassLoader() == contextClassLoader;
        if (!usingSystemClassLoader && candidateCoordinatorClass.getClassLoader() == contextClassLoader) {
            reportFailureToAvoidClassLoaderLeak();
        }
    }
    
    private static void reportFailureToAvoidClassLoaderLeak() {
        PrintStreamCoordinatorFactory.LOG.warn("Unfortunately it is not possible to set up Sysout over SLF4J on this system without introducing a class loader memory leak." + PrintStreamCoordinatorFactory.LINE_END + "If you never need to discard the current class loader [" + Thread.currentThread().getContextClassLoader() + "] " + "this will not be a problem and you can suppress this warning." + PrintStreamCoordinatorFactory.LINE_END + "In the worst case discarding the current class loader may cause all subsequent attempts to print to " + "System.out or err to throw an exception.");
    }
    
    private static Class<PrintStreamCoordinatorImpl> getConfiguratorClassFromCurrentClassLoader() {
        return PrintStreamCoordinatorImpl.class;
    }
    
    private PrintStreamCoordinatorFactory() {
        throw new UnsupportedOperationException("Not instantiable");
    }
}
