// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.security.Permission;
import groovy.security.GroovyCodeSourcePermission;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import org.codehaus.groovy.control.ProcessingUnit;
import java.io.IOException;
import org.codehaus.groovy.control.CompilationFailedException;
import java.util.List;
import java.io.File;
import java.util.Map;
import java.security.AccessController;
import java.security.PrivilegedAction;
import groovy.ui.GroovyMain;
import org.codehaus.groovy.control.CompilerConfiguration;

public class GroovyShell extends GroovyObjectSupport
{
    public static final String DEFAULT_CODE_BASE = "/groovy/shell";
    @Deprecated
    public static final String[] EMPTY_ARGS;
    private Binding context;
    private int counter;
    private CompilerConfiguration config;
    private GroovyClassLoader loader;
    
    public static void main(final String[] args) {
        GroovyMain.main(args);
    }
    
    public GroovyShell() {
        this(null, new Binding());
    }
    
    public GroovyShell(final Binding binding) {
        this(null, binding);
    }
    
    public GroovyShell(final CompilerConfiguration config) {
        this(new Binding(), config);
    }
    
    public GroovyShell(final Binding binding, final CompilerConfiguration config) {
        this(null, binding, config);
    }
    
    public GroovyShell(final ClassLoader parent, final Binding binding) {
        this(parent, binding, CompilerConfiguration.DEFAULT);
    }
    
    public GroovyShell(final ClassLoader parent) {
        this(parent, new Binding(), CompilerConfiguration.DEFAULT);
    }
    
    public GroovyShell(final ClassLoader parent, final Binding binding, final CompilerConfiguration config) {
        if (binding == null) {
            throw new IllegalArgumentException("Binding must not be null.");
        }
        if (config == null) {
            throw new IllegalArgumentException("Compiler configuration must not be null.");
        }
        final ClassLoader parentLoader = (parent != null) ? parent : GroovyShell.class.getClassLoader();
        this.loader = AccessController.doPrivileged((PrivilegedAction<GroovyClassLoader>)new PrivilegedAction<GroovyClassLoader>() {
            public GroovyClassLoader run() {
                return new GroovyClassLoader(parentLoader, config);
            }
        });
        this.context = binding;
        this.config = config;
    }
    
    @Deprecated
    public void initializeBinding() {
        final Map map = this.context.getVariables();
        if (map.get("shell") == null) {
            map.put("shell", this);
        }
    }
    
    public void resetLoadedClasses() {
        this.loader.clearCache();
    }
    
    public GroovyShell(final GroovyShell shell) {
        this(shell.loader, shell.context);
    }
    
    public Binding getContext() {
        return this.context;
    }
    
    public GroovyClassLoader getClassLoader() {
        return this.loader;
    }
    
    @Override
    public Object getProperty(final String property) {
        Object answer = this.getVariable(property);
        if (answer == null) {
            answer = super.getProperty(property);
        }
        return answer;
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        this.setVariable(property, newValue);
        try {
            super.setProperty(property, newValue);
        }
        catch (GroovyRuntimeException ex) {}
    }
    
    public Object run(final File scriptFile, final List list) throws CompilationFailedException, IOException {
        final String[] args = new String[list.size()];
        return this.run(scriptFile, list.toArray(args));
    }
    
    public Object run(final String scriptText, final String fileName, final List list) throws CompilationFailedException {
        final String[] args = new String[list.size()];
        list.toArray(args);
        return this.run(scriptText, fileName, args);
    }
    
    public Object run(final File scriptFile, final String[] args) throws CompilationFailedException, IOException {
        final String scriptName = scriptFile.getName();
        int p = scriptName.lastIndexOf(".");
        if (p++ >= 0 && scriptName.substring(p).equals("java")) {
            throw new CompilationFailedException(0, null);
        }
        final Thread thread = Thread.currentThread();
        class DoSetContext implements PrivilegedAction
        {
            ClassLoader classLoader = GroovyShell.this.loader;
            final /* synthetic */ Thread val$thread;
            
            public DoSetContext(final ClassLoader val$thread) {
                this.val$thread = (Thread)val$thread;
            }
            
            public Object run() {
                this.val$thread.setContextClassLoader(this.classLoader);
                return null;
            }
        }
        AccessController.doPrivileged((PrivilegedAction<Object>)new DoSetContext(thread));
        Class scriptClass;
        try {
            scriptClass = AccessController.doPrivileged((PrivilegedExceptionAction<Class>)new PrivilegedExceptionAction<Class>() {
                public Class run() throws CompilationFailedException, IOException {
                    return GroovyShell.this.loader.parseClass(scriptFile);
                }
            });
        }
        catch (PrivilegedActionException pae) {
            final Exception e = pae.getException();
            if (e instanceof CompilationFailedException) {
                throw (CompilationFailedException)e;
            }
            if (e instanceof IOException) {
                throw (IOException)e;
            }
            throw (RuntimeException)pae.getException();
        }
        return this.runScriptOrMainOrTestOrRunnable(scriptClass, args);
    }
    
    private Object runScriptOrMainOrTestOrRunnable(final Class scriptClass, final String[] args) {
        if (scriptClass == null) {
            return null;
        }
        if (Script.class.isAssignableFrom(scriptClass)) {
            Script script = null;
            try {
                script = (Script)scriptClass.newInstance();
            }
            catch (InstantiationException e) {}
            catch (IllegalAccessException ex) {}
            if (script != null) {
                script.setBinding(this.context);
                script.setProperty("args", args);
                return script.run();
            }
        }
        try {
            scriptClass.getMethod("main", String[].class);
            return InvokerHelper.invokeMethod(scriptClass, "main", new Object[] { args });
        }
        catch (NoSuchMethodException e2) {
            if (Runnable.class.isAssignableFrom(scriptClass)) {
                return this.runRunnable(scriptClass, args);
            }
            if (this.isJUnit3Test(scriptClass)) {
                return this.runJUnit3Test(scriptClass);
            }
            if (this.isJUnit3TestSuite(scriptClass)) {
                return this.runJUnit3TestSuite(scriptClass);
            }
            if (this.isJUnit4Test(scriptClass)) {
                return this.runJUnit4Test(scriptClass);
            }
            if (this.isTestNgTest(scriptClass)) {
                return this.runTestNgTest(scriptClass);
            }
            throw new GroovyRuntimeException("This script or class could not be run.\nIt should either: \n- have a main method, \n- be a JUnit test, TestNG test or extend GroovyTestCase, \n- or implement the Runnable interface.");
        }
    }
    
    private Object runRunnable(final Class scriptClass, final String[] args) {
        Constructor constructor = null;
        Runnable runnable = null;
        Throwable reason = null;
        try {
            constructor = scriptClass.getConstructor(new String[0].getClass());
            try {
                runnable = constructor.newInstance(args);
            }
            catch (Throwable t) {
                reason = t;
            }
        }
        catch (NoSuchMethodException e1) {
            try {
                constructor = scriptClass.getConstructor((Class[])new Class[0]);
                try {
                    runnable = constructor.newInstance(new Object[0]);
                }
                catch (InvocationTargetException ite) {
                    throw new InvokerInvocationException(ite.getTargetException());
                }
                catch (Throwable t2) {
                    reason = t2;
                }
            }
            catch (NoSuchMethodException nsme) {
                reason = nsme;
            }
        }
        if (constructor != null && runnable != null) {
            runnable.run();
            return null;
        }
        throw new GroovyRuntimeException("This script or class was runnable but could not be run. ", reason);
    }
    
    private Object runJUnit3Test(final Class scriptClass) {
        try {
            final Object testSuite = InvokerHelper.invokeConstructorOf("junit.framework.TestSuite", new Object[] { scriptClass });
            return InvokerHelper.invokeStaticMethod("junit.textui.TestRunner", "run", new Object[] { testSuite });
        }
        catch (ClassNotFoundException e) {
            throw new GroovyRuntimeException("Failed to run the unit test. JUnit is not on the Classpath.", e);
        }
    }
    
    private Object runJUnit3TestSuite(final Class scriptClass) {
        try {
            final Object testSuite = InvokerHelper.invokeStaticMethod(scriptClass, "suite", new Object[0]);
            return InvokerHelper.invokeStaticMethod("junit.textui.TestRunner", "run", new Object[] { testSuite });
        }
        catch (ClassNotFoundException e) {
            throw new GroovyRuntimeException("Failed to run the unit test. JUnit is not on the Classpath.", e);
        }
    }
    
    private Object runJUnit4Test(final Class scriptClass) {
        try {
            return InvokerHelper.invokeStaticMethod("org.codehaus.groovy.vmplugin.v5.JUnit4Utils", "realRunJUnit4Test", new Object[] { scriptClass, this.loader });
        }
        catch (ClassNotFoundException e) {
            throw new GroovyRuntimeException("Failed to run the JUnit 4 test.", e);
        }
    }
    
    private Object runTestNgTest(final Class scriptClass) {
        try {
            return InvokerHelper.invokeStaticMethod("org.codehaus.groovy.vmplugin.v5.TestNgUtils", "realRunTestNgTest", new Object[] { scriptClass, this.loader });
        }
        catch (ClassNotFoundException e) {
            throw new GroovyRuntimeException("Failed to run the TestNG test.", e);
        }
    }
    
    private boolean isJUnit3Test(final Class scriptClass) {
        boolean isUnitTestCase = false;
        try {
            try {
                final Class testCaseClass = this.loader.loadClass("junit.framework.TestCase");
                if (testCaseClass.isAssignableFrom(scriptClass)) {
                    isUnitTestCase = true;
                }
            }
            catch (ClassNotFoundException ex) {}
        }
        catch (Throwable t) {}
        return isUnitTestCase;
    }
    
    private boolean isJUnit3TestSuite(final Class scriptClass) {
        boolean isUnitTestSuite = false;
        try {
            try {
                final Class testSuiteClass = this.loader.loadClass("junit.framework.TestSuite");
                if (testSuiteClass.isAssignableFrom(scriptClass)) {
                    isUnitTestSuite = true;
                }
            }
            catch (ClassNotFoundException ex) {}
        }
        catch (Throwable t) {}
        return isUnitTestSuite;
    }
    
    private boolean isJUnit4Test(final Class scriptClass) {
        final char version = System.getProperty("java.version").charAt(2);
        if (version < '5') {
            return false;
        }
        boolean isTest = false;
        try {
            if (InvokerHelper.invokeStaticMethod("org.codehaus.groovy.vmplugin.v5.JUnit4Utils", "realIsJUnit4Test", new Object[] { scriptClass, this.loader }) == Boolean.TRUE) {
                isTest = true;
            }
        }
        catch (ClassNotFoundException e) {
            throw new GroovyRuntimeException("Failed to invoke the JUnit 4 helper class.", e);
        }
        return isTest;
    }
    
    private boolean isTestNgTest(final Class scriptClass) {
        final char version = System.getProperty("java.version").charAt(2);
        if (version < '5') {
            return false;
        }
        boolean isTest = false;
        try {
            if (InvokerHelper.invokeStaticMethod("org.codehaus.groovy.vmplugin.v5.TestNgUtils", "realIsTestNgTest", new Object[] { scriptClass, this.loader }) == Boolean.TRUE) {
                isTest = true;
            }
        }
        catch (ClassNotFoundException e) {
            throw new GroovyRuntimeException("Failed to invoke the TestNG helper class.", e);
        }
        return isTest;
    }
    
    public Object run(final String scriptText, final String fileName, final String[] args) throws CompilationFailedException {
        final GroovyCodeSource gcs = AccessController.doPrivileged((PrivilegedAction<GroovyCodeSource>)new PrivilegedAction<GroovyCodeSource>() {
            public GroovyCodeSource run() {
                return new GroovyCodeSource(scriptText, fileName, "/groovy/shell");
            }
        });
        final Class scriptClass = this.parseClass(gcs);
        return this.runScriptOrMainOrTestOrRunnable(scriptClass, args);
    }
    
    public Object run(final Reader in, final String fileName, final String[] args) throws CompilationFailedException {
        final GroovyCodeSource gcs = AccessController.doPrivileged((PrivilegedAction<GroovyCodeSource>)new PrivilegedAction<GroovyCodeSource>() {
            public GroovyCodeSource run() {
                return new GroovyCodeSource(in, fileName, "/groovy/shell");
            }
        });
        final Class scriptClass = this.parseClass(gcs);
        return this.runScriptOrMainOrTestOrRunnable(scriptClass, args);
    }
    
    @Deprecated
    public Object run(final InputStream in, final String fileName, final String[] args) throws CompilationFailedException {
        final GroovyCodeSource gcs = AccessController.doPrivileged((PrivilegedAction<GroovyCodeSource>)new PrivilegedAction<GroovyCodeSource>() {
            public GroovyCodeSource run() {
                try {
                    final String scriptText = (GroovyShell.this.config.getSourceEncoding() != null) ? DefaultGroovyMethods.getText(in, GroovyShell.this.config.getSourceEncoding()) : DefaultGroovyMethods.getText(in);
                    return new GroovyCodeSource(scriptText, fileName, "/groovy/shell");
                }
                catch (IOException e) {
                    throw new RuntimeException("Impossible to read the content of the input stream for file named: " + fileName, e);
                }
            }
        });
        final Class scriptClass = this.parseClass(gcs);
        return this.runScriptOrMainOrTestOrRunnable(scriptClass, args);
    }
    
    public Object getVariable(final String name) {
        return this.context.getVariables().get(name);
    }
    
    public void setVariable(final String name, final Object value) {
        this.context.setVariable(name, value);
    }
    
    public Object evaluate(final GroovyCodeSource codeSource) throws CompilationFailedException {
        final Script script = this.parse(codeSource);
        script.setBinding(this.context);
        return script.run();
    }
    
    public Object evaluate(final String scriptText) throws CompilationFailedException {
        return this.evaluate(scriptText, this.generateScriptName(), "/groovy/shell");
    }
    
    public Object evaluate(final String scriptText, final String fileName) throws CompilationFailedException {
        return this.evaluate(scriptText, fileName, "/groovy/shell");
    }
    
    public Object evaluate(final String scriptText, final String fileName, final String codeBase) throws CompilationFailedException {
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new GroovyCodeSourcePermission(codeBase));
        }
        final GroovyCodeSource gcs = AccessController.doPrivileged((PrivilegedAction<GroovyCodeSource>)new PrivilegedAction<GroovyCodeSource>() {
            public GroovyCodeSource run() {
                return new GroovyCodeSource(scriptText, fileName, codeBase);
            }
        });
        return this.evaluate(gcs);
    }
    
    public Object evaluate(final File file) throws CompilationFailedException, IOException {
        return this.evaluate(new GroovyCodeSource(file, this.config.getSourceEncoding()));
    }
    
    public Object evaluate(final Reader in) throws CompilationFailedException {
        return this.evaluate(in, this.generateScriptName());
    }
    
    public Object evaluate(final Reader in, final String fileName) throws CompilationFailedException {
        Script script = null;
        try {
            script = this.parse(in, fileName);
            script.setBinding(this.context);
            return script.run();
        }
        finally {
            if (script != null) {
                InvokerHelper.removeClass(script.getClass());
            }
        }
    }
    
    @Deprecated
    public Object evaluate(final InputStream in) throws CompilationFailedException {
        return this.evaluate(in, this.generateScriptName());
    }
    
    @Deprecated
    public Object evaluate(final InputStream in, final String fileName) throws CompilationFailedException {
        Script script = null;
        try {
            script = this.parse(in, fileName);
            script.setBinding(this.context);
            return script.run();
        }
        finally {
            if (script != null) {
                InvokerHelper.removeClass(script.getClass());
            }
        }
    }
    
    public Script parse(final Reader reader, final String fileName) throws CompilationFailedException {
        return this.parse(new GroovyCodeSource(reader, fileName, "/groovy/shell"));
    }
    
    @Deprecated
    public Script parse(final InputStream in, final String fileName) throws CompilationFailedException {
        final GroovyCodeSource gcs = AccessController.doPrivileged((PrivilegedAction<GroovyCodeSource>)new PrivilegedAction<GroovyCodeSource>() {
            public GroovyCodeSource run() {
                try {
                    final String scriptText = (GroovyShell.this.config.getSourceEncoding() != null) ? DefaultGroovyMethods.getText(in, GroovyShell.this.config.getSourceEncoding()) : DefaultGroovyMethods.getText(in);
                    return new GroovyCodeSource(scriptText, fileName, "/groovy/shell");
                }
                catch (IOException e) {
                    throw new RuntimeException("Impossible to read the content of the input stream for file named: " + fileName, e);
                }
            }
        });
        return this.parse(gcs);
    }
    
    private Class parseClass(final GroovyCodeSource codeSource) throws CompilationFailedException {
        return this.loader.parseClass(codeSource, false);
    }
    
    public Script parse(final GroovyCodeSource codeSource) throws CompilationFailedException {
        return InvokerHelper.createScript(this.parseClass(codeSource), this.context);
    }
    
    public Script parse(final File file) throws CompilationFailedException, IOException {
        return this.parse(new GroovyCodeSource(file, this.config.getSourceEncoding()));
    }
    
    public Script parse(final String scriptText) throws CompilationFailedException {
        return this.parse(scriptText, this.generateScriptName());
    }
    
    public Script parse(final String scriptText, final String fileName) throws CompilationFailedException {
        final GroovyCodeSource gcs = AccessController.doPrivileged((PrivilegedAction<GroovyCodeSource>)new PrivilegedAction<GroovyCodeSource>() {
            public GroovyCodeSource run() {
                return new GroovyCodeSource(scriptText, fileName, "/groovy/shell");
            }
        });
        return this.parse(gcs);
    }
    
    public Script parse(final Reader in) throws CompilationFailedException {
        return this.parse(in, this.generateScriptName());
    }
    
    @Deprecated
    public Script parse(final InputStream in) throws CompilationFailedException {
        return this.parse(in, this.generateScriptName());
    }
    
    protected synchronized String generateScriptName() {
        return "Script" + ++this.counter + ".groovy";
    }
    
    static {
        EMPTY_ARGS = new String[0];
    }
}
