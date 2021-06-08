// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.javac;

import java.util.Locale;
import java.net.URL;
import java.util.Map;
import java.net.URISyntaxException;
import java.net.URLClassLoader;
import java.util.Collection;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.File;
import java.util.LinkedList;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.messages.SimpleMessage;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.codehaus.groovy.control.messages.Message;
import org.codehaus.groovy.control.ProcessingUnit;
import org.codehaus.groovy.control.messages.ExceptionMessage;
import java.io.Writer;
import java.io.StringWriter;
import java.io.PrintWriter;
import org.codehaus.groovy.control.CompilationUnit;
import java.util.List;
import org.codehaus.groovy.control.CompilerConfiguration;

public class JavacJavaCompiler implements JavaCompiler
{
    private CompilerConfiguration config;
    
    public JavacJavaCompiler(final CompilerConfiguration config) {
        this.config = config;
    }
    
    public void compile(final List<String> files, final CompilationUnit cu) {
        final String[] javacParameters = this.makeParameters(files, cu.getClassLoader());
        StringWriter javacOutput = null;
        int javacReturnValue = 0;
        try {
            final Class javac = this.findJavac(cu);
            Method method = null;
            try {
                method = javac.getMethod("compile", String[].class, PrintWriter.class);
                javacOutput = new StringWriter();
                final PrintWriter writer = new PrintWriter(javacOutput);
                final Object ret = method.invoke(null, javacParameters, writer);
                javacReturnValue = (int)ret;
            }
            catch (NoSuchMethodException ex) {}
            if (method == null) {
                method = javac.getMethod("compile", String[].class);
                final Object ret2 = method.invoke(null, javacParameters);
                javacReturnValue = (int)ret2;
            }
            cu.getConfiguration().getOutput();
        }
        catch (InvocationTargetException ite) {
            cu.getErrorCollector().addFatalError(new ExceptionMessage((Exception)ite.getCause(), true, cu));
        }
        catch (Exception e) {
            cu.getErrorCollector().addFatalError(new ExceptionMessage(e, true, cu));
        }
        if (javacReturnValue != 0) {
            switch (javacReturnValue) {
                case 1: {
                    this.addJavacError("Compile error during compilation with javac.", cu, javacOutput);
                    break;
                }
                case 2: {
                    this.addJavacError("Invalid commandline usage for javac.", cu, javacOutput);
                    break;
                }
                case 3: {
                    this.addJavacError("System error during compilation with javac.", cu, javacOutput);
                    break;
                }
                case 4: {
                    this.addJavacError("Abnormal termination of javac.", cu, javacOutput);
                    break;
                }
                default: {
                    this.addJavacError("unexpected return value by javac.", cu, javacOutput);
                    break;
                }
            }
        }
        else {
            System.out.print(javacOutput);
        }
    }
    
    private void addJavacError(String header, final CompilationUnit cu, final StringWriter msg) {
        if (msg != null) {
            header = header + "\n" + msg.getBuffer().toString();
        }
        else {
            header = header + "\nThis javac version does not support compile(String[],PrintWriter), " + "so no further details of the error are available. The message error text " + "should be found on System.err.\n";
        }
        cu.getErrorCollector().addFatalError(new SimpleMessage(header, cu));
    }
    
    private String[] makeParameters(final List<String> files, final GroovyClassLoader parentClassLoader) {
        final Map options = this.config.getJointCompilationOptions();
        final LinkedList<String> paras = new LinkedList<String>();
        File target = this.config.getTargetDirectory();
        if (target == null) {
            target = new File(".");
        }
        paras.add("-d");
        paras.add(target.getAbsolutePath());
        paras.add("-sourcepath");
        paras.add(options.get("stubDir").getAbsolutePath());
        final String[] flags = options.get("flags");
        if (flags != null) {
            for (final String flag : flags) {
                paras.add('-' + flag);
            }
        }
        boolean hadClasspath = false;
        final String[] namedValues = options.get("namedValues");
        if (namedValues != null) {
            for (int i = 0; i < namedValues.length; i += 2) {
                final String name = namedValues[i];
                if (name.equals("classpath")) {
                    hadClasspath = true;
                }
                paras.add('-' + name);
                paras.add(namedValues[i + 1]);
            }
        }
        if (!hadClasspath) {
            final StringBuffer resultPath = new StringBuffer(DefaultGroovyMethods.join(this.config.getClasspath(), File.pathSeparator));
            for (ClassLoader cl = parentClassLoader; cl != null; cl = cl.getParent()) {
                if (cl instanceof URLClassLoader) {
                    for (final URL u : ((URLClassLoader)cl).getURLs()) {
                        try {
                            resultPath.append(File.pathSeparator);
                            resultPath.append(new File(u.toURI()).getPath());
                        }
                        catch (URISyntaxException ex) {}
                    }
                }
            }
            paras.add("-classpath");
            paras.add(resultPath.toString());
        }
        paras.addAll(files);
        return paras.toArray(new String[paras.size()]);
    }
    
    private Class findJavac(final CompilationUnit cu) throws ClassNotFoundException {
        final String main = "com.sun.tools.javac.Main";
        try {
            return Class.forName(main);
        }
        catch (ClassNotFoundException e) {
            try {
                final ClassLoader cl = this.getClass().getClassLoader();
                return cl.loadClass(main);
            }
            catch (ClassNotFoundException e) {
                try {
                    return ClassLoader.getSystemClassLoader().loadClass(main);
                }
                catch (ClassNotFoundException e) {
                    try {
                        return cu.getClassLoader().getParent().loadClass(main);
                    }
                    catch (ClassNotFoundException e2) {
                        String javaHome = System.getProperty("java.home");
                        if (javaHome.toLowerCase(Locale.US).endsWith("jre")) {
                            javaHome = javaHome.substring(0, javaHome.length() - 4);
                        }
                        final File toolsJar = new File(javaHome + "/lib/tools.jar");
                        if (toolsJar.exists()) {
                            final GroovyClassLoader loader = cu.getClassLoader();
                            loader.addClasspath(toolsJar.getAbsolutePath());
                            return loader.loadClass(main);
                        }
                        throw new ClassNotFoundException("unable to locate the java compiler com.sun.tools.javac.Main, please change your classloader settings");
                    }
                }
            }
        }
    }
}
