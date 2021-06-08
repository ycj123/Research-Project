// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util;

import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import org.apache.maven.surefire.SpecificTestClassFilter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class DefaultDirectoryScanner implements DirectoryScanner
{
    private static final String FS;
    private static final String[] EMPTY_STRING_ARRAY;
    private static final String JAVA_SOURCE_FILE_EXTENSION = ".java";
    private static final String JAVA_CLASS_FILE_EXTENSION = ".class";
    private final File basedir;
    private final List includes;
    private final List excludes;
    private final List specificTests;
    private final List<Class> classesSkippedByValidation;
    
    public DefaultDirectoryScanner(final File basedir, final List includes, final List excludes, final List specificTests) {
        this.classesSkippedByValidation = new ArrayList<Class>();
        this.basedir = basedir;
        this.includes = includes;
        this.excludes = excludes;
        this.specificTests = specificTests;
    }
    
    public TestsToRun locateTestClasses(final ClassLoader classLoader, final ScannerFilter scannerFilter) {
        final String[] testClassNames = this.collectTests();
        final List<Class> result = new ArrayList<Class>();
        final String[] specific = (this.specificTests == null) ? new String[0] : processIncludesExcludes(this.specificTests);
        final SpecificTestClassFilter specificTestFilter = new SpecificTestClassFilter(specific);
        for (final String className : testClassNames) {
            final Class testClass = loadClass(classLoader, className);
            if (specificTestFilter.accept(testClass)) {
                if (scannerFilter == null || scannerFilter.accept(testClass)) {
                    result.add(testClass);
                }
                else {
                    this.classesSkippedByValidation.add(testClass);
                }
            }
        }
        return new TestsToRun(result);
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
    
    String[] collectTests() {
        String[] tests = DefaultDirectoryScanner.EMPTY_STRING_ARRAY;
        if (this.basedir.exists()) {
            final org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.DirectoryScanner scanner = new org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.DirectoryScanner();
            scanner.setBasedir(this.basedir);
            if (this.includes != null) {
                scanner.setIncludes(processIncludesExcludes(this.includes));
            }
            if (this.excludes != null) {
                scanner.setExcludes(processIncludesExcludes(this.excludes));
            }
            scanner.scan();
            tests = scanner.getIncludedFiles();
            for (int i = 0; i < tests.length; ++i) {
                String test = tests[i];
                test = test.substring(0, test.indexOf("."));
                tests[i] = test.replace(DefaultDirectoryScanner.FS.charAt(0), '.');
            }
        }
        return tests;
    }
    
    private static String[] processIncludesExcludes(final List list) {
        final List<String> newList = new ArrayList<String>();
        for (final Object aList : list) {
            final String include = (String)aList;
            final String[] includes = include.split(",");
            Collections.addAll(newList, includes);
        }
        final String[] incs = new String[newList.size()];
        for (int i = 0; i < incs.length; ++i) {
            String inc = newList.get(i);
            if (inc.endsWith(".java")) {
                inc = inc.substring(0, inc.lastIndexOf(".java")) + ".class";
            }
            incs[i] = inc;
        }
        return incs;
    }
    
    static {
        FS = System.getProperty("file.separator");
        EMPTY_STRING_ARRAY = new String[0];
    }
}
