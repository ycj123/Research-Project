// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import java.lang.reflect.Constructor;
import java.io.File;
import org.apache.maven.surefire.util.ReflectionUtils;
import org.apache.maven.plugin.surefire.report.DefaultReporterFactory;
import org.apache.maven.surefire.util.SurefireReflectionException;
import javax.annotation.Nonnull;

public class CommonReflector
{
    private final Class<?> startupReportConfiguration;
    private final ClassLoader surefireClassLoader;
    
    public CommonReflector(@Nonnull final ClassLoader surefireClassLoader) {
        this.surefireClassLoader = surefireClassLoader;
        try {
            this.startupReportConfiguration = surefireClassLoader.loadClass(StartupReportConfiguration.class.getName());
        }
        catch (ClassNotFoundException e) {
            throw new SurefireReflectionException(e);
        }
    }
    
    public Object createReportingReporterFactory(@Nonnull final StartupReportConfiguration startupReportConfiguration) {
        final Class<?>[] args = (Class<?>[])new Class[] { this.startupReportConfiguration };
        final Object src = this.createStartupReportConfiguration(startupReportConfiguration);
        final Object[] params = { src };
        return ReflectionUtils.instantiateObject(DefaultReporterFactory.class.getName(), args, params, this.surefireClassLoader);
    }
    
    Object createStartupReportConfiguration(@Nonnull final StartupReportConfiguration reporterConfiguration) {
        final Constructor<?> constructor = (Constructor<?>)ReflectionUtils.getConstructor(this.startupReportConfiguration, new Class[] { Boolean.TYPE, Boolean.TYPE, String.class, Boolean.TYPE, Boolean.TYPE, File.class, Boolean.TYPE, String.class, String.class, Boolean.TYPE });
        final Object[] params = { reporterConfiguration.isUseFile(), reporterConfiguration.isPrintSummary(), reporterConfiguration.getReportFormat(), reporterConfiguration.isRedirectTestOutputToFile(), reporterConfiguration.isDisableXmlReport(), reporterConfiguration.getReportsDirectory(), reporterConfiguration.isTrimStackTrace(), reporterConfiguration.getReportNameSuffix(), reporterConfiguration.getConfigurationHash(), reporterConfiguration.isRequiresRunHistory() };
        return ReflectionUtils.newInstance(constructor, params);
    }
}
