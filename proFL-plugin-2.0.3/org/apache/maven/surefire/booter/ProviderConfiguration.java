// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import java.util.List;
import java.io.File;
import java.util.Properties;
import org.apache.maven.surefire.testset.RunOrderParameters;
import org.apache.maven.surefire.testset.TestRequest;
import org.apache.maven.surefire.testset.TestArtifactInfo;
import org.apache.maven.surefire.report.ReporterConfiguration;
import org.apache.maven.surefire.testset.DirectoryScannerParameters;

public class ProviderConfiguration
{
    public static final int TESTS_SUCCEEDED_EXIT_CODE = 0;
    private final DirectoryScannerParameters dirScannerParams;
    private final ReporterConfiguration reporterConfiguration;
    private final TestArtifactInfo testArtifact;
    private final TestRequest testSuiteDefinition;
    private final RunOrderParameters runOrderParameters;
    private final Properties providerProperties;
    private final boolean failIfNoTests;
    private final TypeEncodedValue forkTestSet;
    private final boolean readTestsFromInStream;
    
    public ProviderConfiguration(final DirectoryScannerParameters directoryScannerParameters, final RunOrderParameters runOrderParameters, final boolean failIfNoTests, final ReporterConfiguration reporterConfiguration, final TestArtifactInfo testArtifact, final TestRequest testSuiteDefinition, final Properties providerProperties, final TypeEncodedValue typeEncodedTestSet, final boolean readTestsFromInStream) {
        this.runOrderParameters = runOrderParameters;
        this.providerProperties = providerProperties;
        this.reporterConfiguration = reporterConfiguration;
        this.testArtifact = testArtifact;
        this.testSuiteDefinition = testSuiteDefinition;
        this.dirScannerParams = directoryScannerParameters;
        this.failIfNoTests = failIfNoTests;
        this.forkTestSet = typeEncodedTestSet;
        this.readTestsFromInStream = readTestsFromInStream;
    }
    
    public ReporterConfiguration getReporterConfiguration() {
        return this.reporterConfiguration;
    }
    
    public Boolean isFailIfNoTests() {
        return this.failIfNoTests ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public File getBaseDir() {
        return this.dirScannerParams.getTestClassesDirectory();
    }
    
    public DirectoryScannerParameters getDirScannerParams() {
        return this.dirScannerParams;
    }
    
    public List getIncludes() {
        return this.dirScannerParams.getIncludes();
    }
    
    public List getExcludes() {
        return this.dirScannerParams.getExcludes();
    }
    
    public TestArtifactInfo getTestArtifact() {
        return this.testArtifact;
    }
    
    public TestRequest getTestSuiteDefinition() {
        return this.testSuiteDefinition;
    }
    
    public Properties getProviderProperties() {
        return this.providerProperties;
    }
    
    public TypeEncodedValue getTestForFork() {
        return this.forkTestSet;
    }
    
    public RunOrderParameters getRunOrderParameters() {
        return this.runOrderParameters;
    }
    
    public boolean isReadTestsFromInStream() {
        return this.readTestsFromInStream;
    }
}
