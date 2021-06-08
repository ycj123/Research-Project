// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Collections;
import java.util.List;

public class DefaultScanResult implements ScanResult
{
    private final List<String> files;
    private static final String scanResultNo = "tc.";
    
    public DefaultScanResult(final List<String> files) {
        this.files = Collections.unmodifiableList((List<? extends String>)files);
    }
    
    public int size() {
        return this.files.size();
    }
    
    public String getClassName(final int index) {
        return this.files.get(index);
    }
    
    public void writeTo(final Properties properties) {
        for (int size = this.files.size(), i = 0; i < size; ++i) {
            properties.setProperty("tc." + i, this.files.get(i));
        }
    }
    
    public static DefaultScanResult from(final Properties properties) {
        final List<String> result = new ArrayList<String>();
        int i = 0;
        while (true) {
            final String item = properties.getProperty("tc." + i++);
            if (item == null) {
                break;
            }
            result.add(item);
        }
        return new DefaultScanResult(result);
    }
    
    public boolean isEmpty() {
        return this.files.isEmpty();
    }
    
    public List getFiles() {
        return this.files;
    }
    
    public TestsToRun applyFilter(final ScannerFilter scannerFilter, final ClassLoader testClassLoader) {
        final List<Class> result = new ArrayList<Class>();
        for (int size = this.size(), i = 0; i < size; ++i) {
            final String className = this.getClassName(i);
            final Class testClass = loadClass(testClassLoader, className);
            if (scannerFilter == null || scannerFilter.accept(testClass)) {
                result.add(testClass);
            }
        }
        return new TestsToRun(result);
    }
    
    public List getClassesSkippedByValidation(final ScannerFilter scannerFilter, final ClassLoader testClassLoader) {
        final List<Class> result = new ArrayList<Class>();
        for (int size = this.size(), i = 0; i < size; ++i) {
            final String className = this.getClassName(i);
            final Class testClass = loadClass(testClassLoader, className);
            if (scannerFilter != null && !scannerFilter.accept(testClass)) {
                result.add(testClass);
            }
        }
        return result;
    }
    
    private static Class loadClass(final ClassLoader classLoader, final String className) {
        Class testClass;
        try {
            testClass = classLoader.loadClass(className);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to create test class '" + className + "'", e);
        }
        return testClass;
    }
    
    public DefaultScanResult append(final DefaultScanResult other) {
        if (other != null) {
            final List<String> src = new ArrayList<String>(this.files);
            src.addAll(other.files);
            return new DefaultScanResult(src);
        }
        return this;
    }
}
