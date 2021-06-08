// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.io.File;
import org.codehaus.groovy.runtime.ScriptTestAdapter;
import groovy.lang.Script;
import junit.framework.Test;
import junit.textui.TestRunner;
import groovy.lang.GroovyClassLoader;
import junit.framework.TestSuite;

public class GroovyTestSuite extends TestSuite
{
    protected static String file;
    protected final GroovyClassLoader loader;
    
    public GroovyTestSuite() {
        this.loader = new GroovyClassLoader(GroovyTestSuite.class.getClassLoader());
    }
    
    public static void main(final String[] args) {
        if (args.length > 0) {
            GroovyTestSuite.file = args[0];
        }
        TestRunner.run(suite());
    }
    
    public static Test suite() {
        final GroovyTestSuite suite = new GroovyTestSuite();
        try {
            suite.loadTestSuite();
        }
        catch (Exception e) {
            throw new RuntimeException("Could not create the test suite: " + e, e);
        }
        return (Test)suite;
    }
    
    public void loadTestSuite() throws Exception {
        final String fileName = System.getProperty("test", GroovyTestSuite.file);
        if (fileName == null) {
            throw new RuntimeException("No filename given in the 'test' system property so cannot run a Groovy unit test");
        }
        System.out.println("Compiling: " + fileName);
        final Class type = this.compile(fileName);
        final String[] args = new String[0];
        if (!Test.class.isAssignableFrom(type) && Script.class.isAssignableFrom(type)) {
            this.addTest((Test)new ScriptTestAdapter(type, args));
        }
        else {
            this.addTestSuite(type);
        }
    }
    
    public Class compile(final String fileName) throws Exception {
        return this.loader.parseClass(new File(fileName));
    }
    
    static {
        GroovyTestSuite.file = null;
    }
}
