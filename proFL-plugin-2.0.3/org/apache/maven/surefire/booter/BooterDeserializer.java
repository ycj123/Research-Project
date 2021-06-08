// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import java.util.List;
import org.apache.maven.surefire.report.ReporterConfiguration;
import org.apache.maven.surefire.testset.TestRequest;
import org.apache.maven.surefire.testset.TestArtifactInfo;
import org.apache.maven.surefire.testset.RunOrderParameters;
import org.apache.maven.surefire.testset.DirectoryScannerParameters;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BooterDeserializer implements BooterConstants
{
    private final PropertiesWrapper properties;
    
    public BooterDeserializer(final InputStream inputStream) throws IOException {
        this.properties = SystemPropertyManager.loadProperties(inputStream);
    }
    
    public ProviderConfiguration deserialize() {
        final File reportsDirectory = new File(this.properties.getProperty("reportsDirectory"));
        final String testNgVersion = this.properties.getProperty("testFwJarVersion");
        final String testArtifactClassifier = this.properties.getProperty("testFwJarClassifier");
        final TypeEncodedValue typeEncodedTestForFork = this.properties.getTypeEncodedValue("forkTestSet");
        final boolean preferTestsFromInStream = this.properties.getBooleanProperty("preferTestsFromInStream");
        final String requestedTest = this.properties.getProperty("requestedTest");
        final String requestedTestMethod = this.properties.getProperty("requestedTestMethod");
        final File sourceDirectory = this.properties.getFileProperty("testSuiteDefinitionTestSourceDirectory");
        final List excludesList = this.properties.getStringList("excludes");
        final List includesList = this.properties.getStringList("includes");
        final List specificTestsList = this.properties.getStringList("specificTest");
        final List testSuiteXmlFiles = this.properties.getStringList("testSuiteXmlFiles");
        final File testClassesDirectory = this.properties.getFileProperty("testClassesDirectory");
        final String runOrder = this.properties.getProperty("runOrder");
        final String runStatisticsFile = this.properties.getProperty("runStatisticsFile");
        final DirectoryScannerParameters dirScannerParams = new DirectoryScannerParameters(testClassesDirectory, includesList, excludesList, specificTestsList, this.properties.getBooleanObjectProperty("failIfNoTests"), runOrder);
        final RunOrderParameters runOrderParameters = new RunOrderParameters(runOrder, runStatisticsFile);
        final TestArtifactInfo testNg = new TestArtifactInfo(testNgVersion, testArtifactClassifier);
        final TestRequest testSuiteDefinition = new TestRequest(testSuiteXmlFiles, sourceDirectory, requestedTest, requestedTestMethod);
        final ReporterConfiguration reporterConfiguration = new ReporterConfiguration(reportsDirectory, this.properties.getBooleanObjectProperty("isTrimStackTrace"));
        return new ProviderConfiguration(dirScannerParams, runOrderParameters, this.properties.getBooleanProperty("failIfNoTests"), reporterConfiguration, testNg, testSuiteDefinition, this.properties.getProperties(), typeEncodedTestForFork, preferTestsFromInStream);
    }
    
    public StartupConfiguration getProviderConfiguration() {
        final boolean useSystemClassLoader = this.properties.getBooleanProperty("useSystemClassLoader");
        final boolean useManifestOnlyJar = this.properties.getBooleanProperty("useManifestOnlyJar");
        final String providerConfiguration = this.properties.getProperty("providerConfiguration");
        final ClassLoaderConfiguration classLoaderConfiguration = new ClassLoaderConfiguration(useSystemClassLoader, useManifestOnlyJar);
        final ClasspathConfiguration classpathConfiguration = new ClasspathConfiguration(this.properties);
        return StartupConfiguration.inForkedVm(providerConfiguration, classpathConfiguration, classLoaderConfiguration);
    }
}
