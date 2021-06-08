// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import org.apache.maven.artifact.repository.ArtifactRepository;
import java.util.List;
import java.io.File;

public interface SurefireExecutionParameters
{
    boolean isSkipTests();
    
    void setSkipTests(final boolean p0);
    
    boolean isSkipExec();
    
    void setSkipExec(final boolean p0);
    
    boolean isSkip();
    
    void setSkip(final boolean p0);
    
    File getBasedir();
    
    void setBasedir(final File p0);
    
    File getTestClassesDirectory();
    
    void setTestClassesDirectory(final File p0);
    
    File getClassesDirectory();
    
    void setClassesDirectory(final File p0);
    
    File getReportsDirectory();
    
    void setReportsDirectory(final File p0);
    
    File getTestSourceDirectory();
    
    void setTestSourceDirectory(final File p0);
    
    String getTest();
    
    String getTestMethod();
    
    void setTest(final String p0);
    
    List<String> getIncludes();
    
    void setIncludes(final List<String> p0);
    
    List<String> getExcludes();
    
    void setExcludes(final List<String> p0);
    
    ArtifactRepository getLocalRepository();
    
    void setLocalRepository(final ArtifactRepository p0);
    
    boolean isPrintSummary();
    
    void setPrintSummary(final boolean p0);
    
    String getReportFormat();
    
    void setReportFormat(final String p0);
    
    boolean isUseFile();
    
    void setUseFile(final boolean p0);
    
    String getDebugForkedProcess();
    
    void setDebugForkedProcess(final String p0);
    
    int getForkedProcessTimeoutInSeconds();
    
    void setForkedProcessTimeoutInSeconds(final int p0);
    
    double getParallelTestsTimeoutInSeconds();
    
    void setParallelTestsTimeoutInSeconds(final double p0);
    
    double getParallelTestsTimeoutForcedInSeconds();
    
    void setParallelTestsTimeoutForcedInSeconds(final double p0);
    
    boolean isUseSystemClassLoader();
    
    void setUseSystemClassLoader(final boolean p0);
    
    boolean isUseManifestOnlyJar();
    
    void setUseManifestOnlyJar(final boolean p0);
    
    Boolean getFailIfNoSpecifiedTests();
    
    void setFailIfNoSpecifiedTests(final Boolean p0);
}
