// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;
import java.io.PrintStream;
import org.apache.maven.surefire.util.RunOrder;
import java.lang.reflect.Constructor;
import java.io.File;
import java.util.List;
import org.apache.maven.surefire.util.ReflectionUtils;
import org.apache.maven.surefire.util.SurefireReflectionException;
import org.apache.maven.surefire.providerapi.ProviderParameters;
import org.apache.maven.surefire.suite.RunResult;
import org.apache.maven.surefire.report.ReporterFactory;
import org.apache.maven.surefire.testset.RunOrderParameters;
import org.apache.maven.surefire.testset.DirectoryScannerParameters;
import org.apache.maven.surefire.testset.TestArtifactInfo;
import org.apache.maven.surefire.testset.TestRequest;
import org.apache.maven.surefire.report.ReporterConfiguration;

public class SurefireReflector
{
    private final ClassLoader surefireClassLoader;
    private final Class reporterConfiguration;
    private final Class testRequest;
    private final Class testArtifactInfo;
    private final Class testArtifactInfoAware;
    private final Class directoryScannerParameters;
    private final Class runOrderParameters;
    private final Class directoryScannerParametersAware;
    private final Class testSuiteDefinitionAware;
    private final Class testClassLoaderAware;
    private final Class reporterConfigurationAware;
    private final Class providerPropertiesAware;
    private final Class runResult;
    private final Class booterParameters;
    private final Class reporterFactory;
    
    public SurefireReflector(final ClassLoader surefireClassLoader) {
        this.surefireClassLoader = surefireClassLoader;
        try {
            this.reporterConfiguration = surefireClassLoader.loadClass(ReporterConfiguration.class.getName());
            this.testRequest = surefireClassLoader.loadClass(TestRequest.class.getName());
            this.testArtifactInfo = surefireClassLoader.loadClass(TestArtifactInfo.class.getName());
            this.testArtifactInfoAware = surefireClassLoader.loadClass(TestArtifactInfoAware.class.getName());
            this.directoryScannerParameters = surefireClassLoader.loadClass(DirectoryScannerParameters.class.getName());
            this.runOrderParameters = surefireClassLoader.loadClass(RunOrderParameters.class.getName());
            this.directoryScannerParametersAware = surefireClassLoader.loadClass(DirectoryScannerParametersAware.class.getName());
            this.testSuiteDefinitionAware = surefireClassLoader.loadClass(TestRequestAware.class.getName());
            this.testClassLoaderAware = surefireClassLoader.loadClass(SurefireClassLoadersAware.class.getName());
            this.reporterConfigurationAware = surefireClassLoader.loadClass(ReporterConfigurationAware.class.getName());
            this.providerPropertiesAware = surefireClassLoader.loadClass(ProviderPropertiesAware.class.getName());
            this.reporterFactory = surefireClassLoader.loadClass(ReporterFactory.class.getName());
            this.runResult = surefireClassLoader.loadClass(RunResult.class.getName());
            this.booterParameters = surefireClassLoader.loadClass(ProviderParameters.class.getName());
        }
        catch (ClassNotFoundException e) {
            throw new SurefireReflectionException(e);
        }
    }
    
    public Object convertIfRunResult(final Object result) {
        if (result == null || !this.isRunResult(result)) {
            return result;
        }
        final Integer getCompletedCount1 = (Integer)ReflectionUtils.invokeGetter(result, "getCompletedCount");
        final Integer getErrors = (Integer)ReflectionUtils.invokeGetter(result, "getErrors");
        final Integer getSkipped = (Integer)ReflectionUtils.invokeGetter(result, "getSkipped");
        final Integer getFailures = (Integer)ReflectionUtils.invokeGetter(result, "getFailures");
        return new RunResult(getCompletedCount1, getErrors, getFailures, getSkipped);
    }
    
    Object createTestRequest(final TestRequest suiteDefinition) {
        if (suiteDefinition == null) {
            return null;
        }
        final Class[] arguments = { List.class, File.class, String.class, String.class };
        final Constructor constructor = ReflectionUtils.getConstructor(this.testRequest, arguments);
        return ReflectionUtils.newInstance(constructor, new Object[] { suiteDefinition.getSuiteXmlFiles(), suiteDefinition.getTestSourceDirectory(), suiteDefinition.getRequestedTest(), suiteDefinition.getRequestedTestMethod() });
    }
    
    Object createDirectoryScannerParameters(final DirectoryScannerParameters directoryScannerParameters) {
        if (directoryScannerParameters == null) {
            return null;
        }
        final Class[] arguments = { File.class, List.class, List.class, List.class, Boolean.class, String.class };
        final Constructor constructor = ReflectionUtils.getConstructor(this.directoryScannerParameters, arguments);
        return ReflectionUtils.newInstance(constructor, new Object[] { directoryScannerParameters.getTestClassesDirectory(), directoryScannerParameters.getIncludes(), directoryScannerParameters.getExcludes(), directoryScannerParameters.getSpecificTests(), directoryScannerParameters.isFailIfNoTests(), RunOrder.asString(directoryScannerParameters.getRunOrder()) });
    }
    
    Object createRunOrderParameters(final RunOrderParameters runOrderParameters) {
        if (runOrderParameters == null) {
            return null;
        }
        final Class[] arguments = { String.class, String.class };
        final Constructor constructor = ReflectionUtils.getConstructor(this.runOrderParameters, arguments);
        final File runStatisticsFile = runOrderParameters.getRunStatisticsFile();
        return ReflectionUtils.newInstance(constructor, new Object[] { RunOrder.asString(runOrderParameters.getRunOrder()), (runStatisticsFile != null) ? runStatisticsFile.getAbsolutePath() : null });
    }
    
    Object createTestArtifactInfo(final TestArtifactInfo testArtifactInfo) {
        if (testArtifactInfo == null) {
            return null;
        }
        final Class[] arguments = { String.class, String.class };
        final Constructor constructor = ReflectionUtils.getConstructor(this.testArtifactInfo, arguments);
        return ReflectionUtils.newInstance(constructor, new Object[] { testArtifactInfo.getVersion(), testArtifactInfo.getClassifier() });
    }
    
    Object createReporterConfiguration(final ReporterConfiguration reporterConfiguration) {
        final Constructor constructor = ReflectionUtils.getConstructor(this.reporterConfiguration, new Class[] { File.class, Boolean.class });
        return ReflectionUtils.newInstance(constructor, new Object[] { reporterConfiguration.getReportsDirectory(), reporterConfiguration.isTrimStackTrace() });
    }
    
    public static ReporterFactory createForkingReporterFactoryInCurrentClassLoader(final Boolean trimStackTrace, final PrintStream originalSystemOut) {
        return new ForkingReporterFactory(trimStackTrace, originalSystemOut);
    }
    
    public Object createBooterConfiguration(final ClassLoader surefireClassLoader, final Object factoryInstance, final boolean insideFork) {
        return ReflectionUtils.instantiateTwoArgs(surefireClassLoader, BaseProviderFactory.class.getName(), this.reporterFactory, factoryInstance, Boolean.class, insideFork ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public Object instantiateProvider(final String providerClassName, final Object booterParameters) {
        return ReflectionUtils.instantiateOneArg(this.surefireClassLoader, providerClassName, this.booterParameters, booterParameters);
    }
    
    public void setIfDirScannerAware(final Object o, final DirectoryScannerParameters dirScannerParams) {
        if (this.directoryScannerParametersAware.isAssignableFrom(o.getClass())) {
            this.setDirectoryScannerParameters(o, dirScannerParams);
        }
    }
    
    public void setDirectoryScannerParameters(final Object o, final DirectoryScannerParameters dirScannerParams) {
        final Object param = this.createDirectoryScannerParameters(dirScannerParams);
        ReflectionUtils.invokeSetter(o, "setDirectoryScannerParameters", this.directoryScannerParameters, param);
    }
    
    public void setRunOrderParameters(final Object o, final RunOrderParameters runOrderParameters) {
        final Object param = this.createRunOrderParameters(runOrderParameters);
        ReflectionUtils.invokeSetter(o, "setRunOrderParameters", this.runOrderParameters, param);
    }
    
    public void setTestSuiteDefinitionAware(final Object o, final TestRequest testSuiteDefinition2) {
        if (this.testSuiteDefinitionAware.isAssignableFrom(o.getClass())) {
            this.setTestSuiteDefinition(o, testSuiteDefinition2);
        }
    }
    
    void setTestSuiteDefinition(final Object o, final TestRequest testSuiteDefinition1) {
        final Object param = this.createTestRequest(testSuiteDefinition1);
        ReflectionUtils.invokeSetter(o, "setTestRequest", this.testRequest, param);
    }
    
    public void setProviderPropertiesAware(final Object o, final Properties properties) {
        if (this.providerPropertiesAware.isAssignableFrom(o.getClass())) {
            this.setProviderProperties(o, properties);
        }
    }
    
    void setProviderProperties(final Object o, final Properties providerProperties) {
        ReflectionUtils.invokeSetter(o, "setProviderProperties", Properties.class, providerProperties);
    }
    
    public void setReporterConfigurationAware(final Object o, final ReporterConfiguration reporterConfiguration1) {
        if (this.reporterConfigurationAware.isAssignableFrom(o.getClass())) {
            this.setReporterConfiguration(o, reporterConfiguration1);
        }
    }
    
    void setReporterConfiguration(final Object o, final ReporterConfiguration reporterConfiguration) {
        final Object param = this.createReporterConfiguration(reporterConfiguration);
        ReflectionUtils.invokeSetter(o, "setReporterConfiguration", this.reporterConfiguration, param);
    }
    
    public void setTestClassLoaderAware(final Object o, final ClassLoader testClassLoader) {
        if (this.testClassLoaderAware.isAssignableFrom(o.getClass())) {
            this.setTestClassLoader(o, testClassLoader);
        }
    }
    
    void setTestClassLoader(final Object o, final ClassLoader testClassLoader) {
        final Method setter = ReflectionUtils.getMethod(o, "setClassLoaders", new Class[] { ClassLoader.class });
        ReflectionUtils.invokeMethodWithArray(o, setter, new Object[] { testClassLoader });
    }
    
    public void setTestArtifactInfoAware(final Object o, final TestArtifactInfo testArtifactInfo1) {
        if (this.testArtifactInfoAware.isAssignableFrom(o.getClass())) {
            this.setTestArtifactInfo(o, testArtifactInfo1);
        }
    }
    
    void setTestArtifactInfo(final Object o, final TestArtifactInfo testArtifactInfo) {
        final Object param = this.createTestArtifactInfo(testArtifactInfo);
        ReflectionUtils.invokeSetter(o, "setTestArtifactInfo", this.testArtifactInfo, param);
    }
    
    private boolean isRunResult(final Object o) {
        return this.runResult.isAssignableFrom(o.getClass());
    }
    
    class ClassLoaderProxy implements InvocationHandler
    {
        private final Object target;
        
        public ClassLoaderProxy(final Object delegate) {
            this.target = delegate;
        }
        
        public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
            final Method delegateMethod = this.target.getClass().getMethod(method.getName(), method.getParameterTypes());
            return delegateMethod.invoke(this.target, args);
        }
    }
}
