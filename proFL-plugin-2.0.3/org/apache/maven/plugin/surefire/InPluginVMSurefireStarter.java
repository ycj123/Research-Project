// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import org.apache.maven.surefire.testset.TestSetFailedException;
import java.util.Properties;
import java.lang.reflect.InvocationTargetException;
import org.apache.maven.surefire.booter.SurefireExecutionException;
import org.apache.maven.surefire.booter.ProviderFactory;
import org.apache.maven.surefire.suite.RunResult;
import org.apache.maven.surefire.util.DefaultScanResult;
import javax.annotation.Nonnull;
import org.apache.maven.surefire.booter.ProviderConfiguration;
import org.apache.maven.surefire.booter.StartupConfiguration;

public class InPluginVMSurefireStarter
{
    private final StartupConfiguration startupConfiguration;
    private final StartupReportConfiguration startupReportConfiguration;
    private final ProviderConfiguration providerConfiguration;
    
    public InPluginVMSurefireStarter(@Nonnull final StartupConfiguration startupConfiguration, @Nonnull final ProviderConfiguration providerConfiguration, @Nonnull final StartupReportConfiguration startupReportConfiguration) {
        this.startupConfiguration = startupConfiguration;
        this.startupReportConfiguration = startupReportConfiguration;
        this.providerConfiguration = providerConfiguration;
    }
    
    public RunResult runSuitesInProcess(@Nonnull final DefaultScanResult scanResult) throws SurefireExecutionException, TestSetFailedException {
        final Properties providerProperties = this.providerConfiguration.getProviderProperties();
        scanResult.writeTo(providerProperties);
        this.startupConfiguration.writeSurefireTestClasspathProperty();
        final ClassLoader testsClassLoader = this.startupConfiguration.getClasspathConfiguration().createMergedClassLoader();
        final CommonReflector surefireReflector = new CommonReflector(testsClassLoader);
        final Object factory = surefireReflector.createReportingReporterFactory(this.startupReportConfiguration);
        try {
            return ProviderFactory.invokeProvider(null, testsClassLoader, factory, this.providerConfiguration, false, this.startupConfiguration, true);
        }
        catch (InvocationTargetException e) {
            throw new SurefireExecutionException("Exception in provider", e.getTargetException());
        }
    }
}
