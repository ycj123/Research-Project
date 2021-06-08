// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire.booterclient;

import java.io.IOException;
import org.apache.maven.surefire.booter.ClassLoaderConfiguration;
import org.apache.maven.surefire.report.ReporterConfiguration;
import org.apache.maven.surefire.testset.RunOrderParameters;
import org.apache.maven.surefire.testset.DirectoryScannerParameters;
import org.apache.maven.surefire.testset.TestRequest;
import org.apache.maven.surefire.testset.TestArtifactInfo;
import org.apache.maven.surefire.booter.ClasspathConfiguration;
import java.util.Properties;
import org.apache.maven.surefire.booter.SystemPropertyManager;
import org.apache.maven.surefire.util.RunOrder;
import java.util.List;
import org.apache.maven.plugin.surefire.SurefireProperties;
import java.io.File;
import org.apache.maven.surefire.booter.StartupConfiguration;
import org.apache.maven.surefire.booter.ProviderConfiguration;
import org.apache.maven.surefire.booter.KeyValueSource;

class BooterSerializer
{
    private final ForkConfiguration forkConfiguration;
    
    public BooterSerializer(final ForkConfiguration forkConfiguration) {
        this.forkConfiguration = forkConfiguration;
    }
    
    public File serialize(final KeyValueSource sourceProperties, final ProviderConfiguration booterConfiguration, final StartupConfiguration providerConfiguration, final Object testSet, final boolean readTestsFromInStream) throws IOException {
        final SurefireProperties properties = new SurefireProperties(sourceProperties);
        final ClasspathConfiguration cp = providerConfiguration.getClasspathConfiguration();
        properties.setClasspath("classPathUrl.", cp.getTestClasspath());
        properties.setClasspath("surefireClassPathUrl.", cp.getProviderClasspath());
        properties.setProperty("enableAssertions", String.valueOf(cp.isEnableAssertions()));
        properties.setProperty("childDelegation", String.valueOf(cp.isChildDelegation()));
        final TestArtifactInfo testNg = booterConfiguration.getTestArtifact();
        if (testNg != null) {
            properties.setProperty("testFwJarVersion", testNg.getVersion());
            properties.setNullableProperty("testFwJarClassifier", testNg.getClassifier());
        }
        properties.setProperty("preferTestsFromInStream", readTestsFromInStream);
        properties.setNullableProperty("forkTestSet", this.getTypeEncoded(testSet));
        final TestRequest testSuiteDefinition = booterConfiguration.getTestSuiteDefinition();
        if (testSuiteDefinition != null) {
            properties.setProperty("testSuiteDefinitionTestSourceDirectory", testSuiteDefinition.getTestSourceDirectory());
            properties.addList(testSuiteDefinition.getSuiteXmlFiles(), "testSuiteXmlFiles");
            properties.setNullableProperty("requestedTest", testSuiteDefinition.getRequestedTest());
            properties.setNullableProperty("requestedTestMethod", testSuiteDefinition.getRequestedTestMethod());
        }
        final DirectoryScannerParameters directoryScannerParameters = booterConfiguration.getDirScannerParams();
        if (directoryScannerParameters != null) {
            properties.setProperty("failIfNoTests", String.valueOf(directoryScannerParameters.isFailIfNoTests()));
            properties.addList(directoryScannerParameters.getIncludes(), "includes");
            properties.addList(directoryScannerParameters.getExcludes(), "excludes");
            properties.addList(directoryScannerParameters.getSpecificTests(), "specificTest");
            properties.setProperty("testClassesDirectory", directoryScannerParameters.getTestClassesDirectory());
        }
        final RunOrderParameters runOrderParameters = booterConfiguration.getRunOrderParameters();
        if (runOrderParameters != null) {
            properties.setProperty("runOrder", RunOrder.asString(runOrderParameters.getRunOrder()));
            properties.setProperty("runStatisticsFile", runOrderParameters.getRunStatisticsFile());
        }
        final ReporterConfiguration reporterConfiguration = booterConfiguration.getReporterConfiguration();
        final Boolean rep = reporterConfiguration.isTrimStackTrace();
        properties.setProperty("isTrimStackTrace", rep);
        properties.setProperty("reportsDirectory", reporterConfiguration.getReportsDirectory());
        final ClassLoaderConfiguration classLoaderConfiguration = providerConfiguration.getClassLoaderConfiguration();
        properties.setProperty("useSystemClassLoader", String.valueOf(classLoaderConfiguration.isUseSystemClassLoader()));
        properties.setProperty("useManifestOnlyJar", String.valueOf(classLoaderConfiguration.isUseManifestOnlyJar()));
        properties.setProperty("failIfNoTests", String.valueOf(booterConfiguration.isFailIfNoTests()));
        properties.setProperty("providerConfiguration", providerConfiguration.getProviderClassName());
        return SystemPropertyManager.writePropertiesFile(properties, this.forkConfiguration.getTempDirectory(), "surefire", this.forkConfiguration.isDebug());
    }
    
    private String getTypeEncoded(final Object value) {
        if (value == null) {
            return null;
        }
        String valueToUse;
        if (value instanceof Class) {
            valueToUse = ((Class)value).getName();
        }
        else {
            valueToUse = value.toString();
        }
        return value.getClass().getName() + "|" + valueToUse;
    }
}
