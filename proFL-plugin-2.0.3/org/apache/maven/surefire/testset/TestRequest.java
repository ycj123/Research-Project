// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.testset;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.File;
import java.util.List;

public class TestRequest
{
    private final List<File> suiteXmlFiles;
    private final File testSourceDirectory;
    private final String requestedTest;
    private final String requestedTestMethod;
    
    public TestRequest(final List suiteXmlFiles, final File testSourceDirectory, final String requestedTest) {
        this(suiteXmlFiles, testSourceDirectory, requestedTest, null);
    }
    
    public TestRequest(final List suiteXmlFiles, final File testSourceDirectory, final String requestedTest, final String requestedTestMethod) {
        this.suiteXmlFiles = createFiles(suiteXmlFiles);
        this.testSourceDirectory = testSourceDirectory;
        this.requestedTest = requestedTest;
        this.requestedTestMethod = requestedTestMethod;
    }
    
    public List<File> getSuiteXmlFiles() {
        return this.suiteXmlFiles;
    }
    
    public File getTestSourceDirectory() {
        return this.testSourceDirectory;
    }
    
    public String getRequestedTest() {
        return this.requestedTest;
    }
    
    public String getRequestedTestMethod() {
        return this.requestedTestMethod;
    }
    
    private static List<File> createFiles(final List suiteXmlFiles) {
        if (suiteXmlFiles != null) {
            final List<File> files = new ArrayList<File>();
            for (final Object element : suiteXmlFiles) {
                final Object suiteXmlFile = element;
                files.add((element instanceof String) ? new File((String)element) : ((File)element));
            }
            return files;
        }
        return null;
    }
}
