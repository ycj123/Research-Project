// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.providerapi;

import org.apache.maven.surefire.testset.TestArtifactInfo;
import java.util.Properties;
import org.apache.maven.surefire.testset.TestRequest;
import org.apache.maven.surefire.report.ReporterConfiguration;
import org.apache.maven.surefire.testset.DirectoryScannerParameters;
import org.apache.maven.surefire.report.ConsoleLogger;
import org.apache.maven.surefire.report.ReporterFactory;
import org.apache.maven.surefire.util.RunOrderCalculator;
import org.apache.maven.surefire.util.ScanResult;
import org.apache.maven.surefire.util.DirectoryScanner;

public interface ProviderParameters
{
    @Deprecated
    DirectoryScanner getDirectoryScanner();
    
    ScanResult getScanResult();
    
    RunOrderCalculator getRunOrderCalculator();
    
    ReporterFactory getReporterFactory();
    
    ConsoleLogger getConsoleLogger();
    
    @Deprecated
    DirectoryScannerParameters getDirectoryScannerParameters();
    
    ReporterConfiguration getReporterConfiguration();
    
    TestRequest getTestRequest();
    
    ClassLoader getTestClassLoader();
    
    Properties getProviderProperties();
    
    TestArtifactInfo getTestArtifactInfo();
}
