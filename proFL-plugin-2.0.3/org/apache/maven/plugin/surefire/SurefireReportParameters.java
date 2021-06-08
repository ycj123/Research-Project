// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.surefire;

import java.io.File;

public interface SurefireReportParameters
{
    boolean isSkipTests();
    
    void setSkipTests(final boolean p0);
    
    boolean isSkipExec();
    
    void setSkipExec(final boolean p0);
    
    boolean isSkip();
    
    void setSkip(final boolean p0);
    
    boolean isTestFailureIgnore();
    
    void setTestFailureIgnore(final boolean p0);
    
    File getBasedir();
    
    void setBasedir(final File p0);
    
    File getTestClassesDirectory();
    
    void setTestClassesDirectory(final File p0);
    
    File getReportsDirectory();
    
    void setReportsDirectory(final File p0);
    
    Boolean getFailIfNoTests();
    
    void setFailIfNoTests(final Boolean p0);
}
