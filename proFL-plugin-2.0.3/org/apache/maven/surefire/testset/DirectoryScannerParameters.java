// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.testset;

import org.apache.maven.surefire.util.RunOrder;
import java.util.List;
import java.io.File;

public class DirectoryScannerParameters
{
    private final File testClassesDirectory;
    private final List includes;
    private final List excludes;
    private final List specificTests;
    private final Boolean failIfNoTests;
    private final RunOrder[] runOrder;
    
    private DirectoryScannerParameters(final File testClassesDirectory, final List includes, final List excludes, final List specificTests, final Boolean failIfNoTests, final RunOrder[] runOrder) {
        this.testClassesDirectory = testClassesDirectory;
        this.includes = includes;
        this.excludes = excludes;
        this.specificTests = specificTests;
        this.failIfNoTests = failIfNoTests;
        this.runOrder = runOrder;
    }
    
    public DirectoryScannerParameters(final File testClassesDirectory, final List includes, final List excludes, final List specificTests, final Boolean failIfNoTests, final String runOrder) {
        this(testClassesDirectory, includes, excludes, specificTests, failIfNoTests, (runOrder == null) ? RunOrder.DEFAULT : RunOrder.valueOfMulti(runOrder));
    }
    
    public List getSpecificTests() {
        return this.specificTests;
    }
    
    public File getTestClassesDirectory() {
        return this.testClassesDirectory;
    }
    
    public List getIncludes() {
        return this.includes;
    }
    
    public List getExcludes() {
        return this.excludes;
    }
    
    public Boolean isFailIfNoTests() {
        return this.failIfNoTests;
    }
    
    public RunOrder[] getRunOrder() {
        return this.runOrder;
    }
}
