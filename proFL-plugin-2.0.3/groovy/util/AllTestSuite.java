// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.io.File;
import org.codehaus.groovy.runtime.ScriptTestAdapter;
import groovy.lang.Script;
import java.util.Iterator;
import java.util.Collection;
import java.io.IOException;
import org.codehaus.groovy.control.CompilationFailedException;
import junit.framework.Test;
import groovy.lang.GroovyClassLoader;
import java.util.logging.Logger;
import junit.framework.TestSuite;

public class AllTestSuite extends TestSuite
{
    public static final String SYSPROP_TEST_DIR = "groovy.test.dir";
    public static final String SYSPROP_TEST_PATTERN = "groovy.test.pattern";
    public static final String SYSPROP_TEST_EXCLUDES_PATTERN = "groovy.test.excludesPattern";
    private static final Logger LOG;
    private static final ClassLoader JAVA_LOADER;
    private static final GroovyClassLoader GROOVY_LOADER;
    private static final String[] EMPTY_ARGS;
    private static IFileNameFinder finder;
    
    public static Test suite() {
        final String basedir = System.getProperty("groovy.test.dir", "./test/");
        final String pattern = System.getProperty("groovy.test.pattern", "**/*Test.groovy");
        final String excludesPattern = System.getProperty("groovy.test.excludesPattern", "");
        return suite(basedir, pattern);
    }
    
    public static Test suite(final String basedir, final String pattern) {
        return suite(basedir, pattern, "");
    }
    
    public static Test suite(final String basedir, final String pattern, final String excludesPattern) {
        final AllTestSuite suite = new AllTestSuite();
        String fileName = "";
        try {
            final Collection filenames = (excludesPattern.length() > 0) ? AllTestSuite.finder.getFileNames(basedir, pattern, excludesPattern) : AllTestSuite.finder.getFileNames(basedir, pattern);
            final Iterator iter = filenames.iterator();
            while (iter.hasNext()) {
                fileName = iter.next();
                AllTestSuite.LOG.finest("trying to load " + fileName);
                suite.loadTest(fileName);
            }
        }
        catch (CompilationFailedException e1) {
            e1.printStackTrace();
            throw new RuntimeException("CompilationFailedException when loading " + fileName, e1);
        }
        catch (IOException e2) {
            throw new RuntimeException("IOException when loading " + fileName, e2);
        }
        return (Test)suite;
    }
    
    protected void loadTest(final String fileName) throws CompilationFailedException, IOException {
        final Class type = this.compile(fileName);
        if (!Test.class.isAssignableFrom(type) && Script.class.isAssignableFrom(type)) {
            this.addTest((Test)new ScriptTestAdapter(type, AllTestSuite.EMPTY_ARGS));
        }
        else {
            this.addTestSuite(type);
        }
    }
    
    protected Class compile(final String fileName) throws CompilationFailedException, IOException {
        return AllTestSuite.GROOVY_LOADER.parseClass(new File(fileName));
    }
    
    static {
        LOG = Logger.getLogger(AllTestSuite.class.getName());
        JAVA_LOADER = AllTestSuite.class.getClassLoader();
        GROOVY_LOADER = new GroovyClassLoader(AllTestSuite.JAVA_LOADER);
        EMPTY_ARGS = new String[0];
        AllTestSuite.finder = null;
        try {
            final Class finderClass = Class.forName("groovy.util.FileNameFinder");
            AllTestSuite.finder = finderClass.newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException("Cannot find and instantiate class FileNameFinder", e);
        }
    }
}
