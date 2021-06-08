// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import org.apache.maven.surefire.report.ReporterException;
import java.lang.reflect.Method;
import org.apache.maven.surefire.util.ReflectionUtils;
import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;
import org.apache.maven.surefire.testset.TestSetFailedException;
import org.apache.maven.surefire.providerapi.SurefireProvider;
import java.io.PrintStream;
import org.apache.maven.surefire.suite.RunResult;

public class ProviderFactory
{
    private final StartupConfiguration startupConfiguration;
    private final ProviderConfiguration providerConfiguration;
    private final ClassLoader classLoader;
    private final SurefireReflector surefireReflector;
    private final Object reporterManagerFactory;
    private static final Class[] invokeParamaters;
    
    public ProviderFactory(final StartupConfiguration startupConfiguration, final ProviderConfiguration providerConfiguration, final ClassLoader testsClassLoader, final Object reporterManagerFactory) {
        this.providerConfiguration = providerConfiguration;
        this.startupConfiguration = startupConfiguration;
        this.surefireReflector = new SurefireReflector(testsClassLoader);
        this.classLoader = testsClassLoader;
        this.reporterManagerFactory = reporterManagerFactory;
    }
    
    public static RunResult invokeProvider(final Object testSet, final ClassLoader testsClassLoader, final Object factory, final ProviderConfiguration providerConfiguration, final boolean insideFork, final StartupConfiguration startupConfiguration1, final boolean restoreStreams) throws TestSetFailedException, InvocationTargetException {
        final PrintStream orgSystemOut = System.out;
        final PrintStream orgSystemErr = System.err;
        final ProviderFactory providerFactory = new ProviderFactory(startupConfiguration1, providerConfiguration, testsClassLoader, factory);
        final SurefireProvider provider = providerFactory.createProvider(insideFork);
        try {
            return provider.invoke(testSet);
        }
        finally {
            if (restoreStreams && System.getSecurityManager() == null) {
                System.setOut(orgSystemOut);
                System.setErr(orgSystemErr);
            }
        }
    }
    
    public SurefireProvider createProvider(final boolean isInsideFork) {
        final ClassLoader systemClassLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(this.classLoader);
        final StartupConfiguration starterConfiguration = this.startupConfiguration;
        final Object o = this.surefireReflector.createBooterConfiguration(this.classLoader, this.reporterManagerFactory, isInsideFork);
        this.surefireReflector.setTestSuiteDefinitionAware(o, this.providerConfiguration.getTestSuiteDefinition());
        this.surefireReflector.setProviderPropertiesAware(o, this.providerConfiguration.getProviderProperties());
        this.surefireReflector.setReporterConfigurationAware(o, this.providerConfiguration.getReporterConfiguration());
        this.surefireReflector.setTestClassLoaderAware(o, this.classLoader);
        this.surefireReflector.setTestArtifactInfoAware(o, this.providerConfiguration.getTestArtifact());
        this.surefireReflector.setRunOrderParameters(o, this.providerConfiguration.getRunOrderParameters());
        this.surefireReflector.setIfDirScannerAware(o, this.providerConfiguration.getDirScannerParams());
        final Object provider = this.surefireReflector.instantiateProvider(starterConfiguration.getActualClassName(), o);
        Thread.currentThread().setContextClassLoader(systemClassLoader);
        return new ProviderProxy(provider, this.classLoader);
    }
    
    static {
        invokeParamaters = new Class[] { Object.class };
    }
    
    private class ProviderProxy implements SurefireProvider
    {
        private final Object providerInOtherClassLoader;
        private final ClassLoader testsClassLoader;
        
        private ProviderProxy(final Object providerInOtherClassLoader, final ClassLoader testsClassLoader) {
            this.providerInOtherClassLoader = providerInOtherClassLoader;
            this.testsClassLoader = testsClassLoader;
        }
        
        public Iterator getSuites() {
            final ClassLoader current = this.swapClassLoader(this.testsClassLoader);
            try {
                return (Iterator)ReflectionUtils.invokeGetter(this.providerInOtherClassLoader, "getSuites");
            }
            finally {
                Thread.currentThread().setContextClassLoader(current);
            }
        }
        
        public RunResult invoke(final Object forkTestSet) throws TestSetFailedException, ReporterException, InvocationTargetException {
            final ClassLoader current = this.swapClassLoader(this.testsClassLoader);
            try {
                final Method invoke = ReflectionUtils.getMethod(this.providerInOtherClassLoader.getClass(), "invoke", ProviderFactory.invokeParamaters);
                final Object result = ReflectionUtils.invokeMethodWithArray2(this.providerInOtherClassLoader, invoke, new Object[] { forkTestSet });
                return (RunResult)ProviderFactory.this.surefireReflector.convertIfRunResult(result);
            }
            finally {
                if (System.getSecurityManager() == null) {
                    Thread.currentThread().setContextClassLoader(current);
                }
            }
        }
        
        private ClassLoader swapClassLoader(final ClassLoader newClassLoader) {
            final ClassLoader current = Thread.currentThread().getContextClassLoader();
            Thread.currentThread().setContextClassLoader(newClassLoader);
            return current;
        }
        
        public void cancel() {
            final Method invoke = ReflectionUtils.getMethod(this.providerInOtherClassLoader.getClass(), "cancel", new Class[0]);
            ReflectionUtils.invokeMethodWithArray(this.providerInOtherClassLoader, invoke, null);
        }
    }
}
