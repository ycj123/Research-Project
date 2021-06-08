// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.apache.tools.ant.util.FileUtils;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilationFailedException;
import groovy.lang.MissingMethodException;
import java.lang.reflect.Field;
import org.apache.tools.ant.Project;
import groovy.util.AntBuilder;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.io.Writer;
import java.io.PrintWriter;
import org.codehaus.groovy.tools.ErrorReporter;
import java.io.StringWriter;
import org.apache.tools.ant.DirectoryScanner;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Commandline;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.apache.tools.ant.types.Path;
import java.io.File;
import org.apache.tools.ant.types.FileSet;
import java.util.Vector;
import org.apache.tools.ant.taskdefs.Java;

public class Groovy extends Java
{
    private static final String PREFIX = "embedded_script_in_";
    private static final String SUFFIX = "groovy_Ant_task";
    private final LoggingHelper log;
    private Vector<FileSet> filesets;
    private File srcFile;
    private String command;
    private File output;
    private boolean append;
    private Path classpath;
    private boolean fork;
    private boolean includeAntRuntime;
    private boolean useGroovyShell;
    private CompilerConfiguration configuration;
    private Commandline cmdline;
    private boolean contextClassLoader;
    
    public Groovy() {
        this.log = new LoggingHelper((Task)this);
        this.filesets = new Vector<FileSet>();
        this.srcFile = null;
        this.command = "";
        this.output = null;
        this.append = false;
        this.fork = false;
        this.includeAntRuntime = true;
        this.useGroovyShell = false;
        this.configuration = new CompilerConfiguration();
        this.cmdline = new Commandline();
    }
    
    public void setFork(final boolean fork) {
        this.fork = fork;
    }
    
    public void setUseGroovyShell(final boolean useGroovyShell) {
        this.useGroovyShell = useGroovyShell;
    }
    
    public void setIncludeAntRuntime(final boolean includeAntRuntime) {
        this.includeAntRuntime = includeAntRuntime;
    }
    
    public void setStacktrace(final boolean stacktrace) {
        this.configuration.setDebug(stacktrace);
    }
    
    public void setSrc(final File srcFile) {
        this.srcFile = srcFile;
    }
    
    public void addText(final String txt) {
        this.log("addText('" + txt + "')", 3);
        this.command += txt;
    }
    
    public void addFileset(final FileSet set) {
        this.filesets.addElement(set);
    }
    
    public void setOutput(final File output) {
        this.output = output;
    }
    
    public void setAppend(final boolean append) {
        this.append = append;
    }
    
    public void setClasspath(final Path classpath) {
        this.classpath = classpath;
    }
    
    public Path createClasspath() {
        if (this.classpath == null) {
            this.classpath = new Path(this.getProject());
        }
        return this.classpath.createPath();
    }
    
    public void setClasspathRef(final Reference ref) {
        this.createClasspath().setRefid(ref);
    }
    
    public Path getClasspath() {
        return this.classpath;
    }
    
    public void execute() throws BuildException {
        this.log.debug("execute()");
        this.command = this.command.trim();
        if (this.srcFile == null && this.command.length() == 0 && this.filesets.isEmpty()) {
            throw new BuildException("Source file does not exist!", this.getLocation());
        }
        if (this.srcFile != null && !this.srcFile.exists()) {
            throw new BuildException("Source file does not exist!", this.getLocation());
        }
        for (int i = 0; i < this.filesets.size(); ++i) {
            final FileSet fs = this.filesets.elementAt(i);
            final DirectoryScanner ds = fs.getDirectoryScanner(this.getProject());
            final File srcDir = fs.getDir(this.getProject());
            final String[] srcFiles = ds.getIncludedFiles();
        }
        try {
            PrintStream out = System.out;
            try {
                if (this.output != null) {
                    this.log.verbose("Opening PrintStream to output file " + this.output);
                    out = new PrintStream(new BufferedOutputStream(new FileOutputStream(this.output.getAbsolutePath(), this.append)));
                }
                if (this.command == null || this.command.trim().length() == 0) {
                    this.createClasspath().add(new Path(this.getProject(), this.srcFile.getParentFile().getCanonicalPath()));
                    this.command = getText(new BufferedReader(new FileReader(this.srcFile)));
                }
                if (this.command == null) {
                    throw new BuildException("Source file does not exist!", this.getLocation());
                }
                this.execGroovy(this.command, out);
            }
            finally {
                if (out != null && out != System.out) {
                    out.close();
                }
            }
        }
        catch (IOException e) {
            throw new BuildException((Throwable)e, this.getLocation());
        }
        this.log.verbose("statements executed successfully");
    }
    
    private static String getText(final BufferedReader reader) throws IOException {
        final StringBuffer answer = new StringBuffer();
        final char[] charBuffer = new char[4096];
        int nbCharRead = 0;
        while ((nbCharRead = reader.read(charBuffer)) != -1) {
            answer.append(charBuffer, 0, nbCharRead);
        }
        reader.close();
        return answer.toString();
    }
    
    public Commandline.Argument createArg() {
        return this.cmdline.createArgument();
    }
    
    protected void runStatements(final Reader reader, final PrintStream out) throws IOException {
        this.log.debug("runStatements()");
        final StringBuffer txt = new StringBuffer();
        String line = "";
        final BufferedReader in = new BufferedReader(reader);
        while ((line = in.readLine()) != null) {
            line = this.getProject().replaceProperties(line);
            if (line.indexOf("--") >= 0) {
                txt.append("\n");
            }
        }
        if (!txt.toString().equals("")) {
            this.execGroovy(txt.toString(), out);
        }
    }
    
    protected void execGroovy(final String txt, final PrintStream out) {
        this.log.debug("execGroovy()");
        if ("".equals(txt.trim())) {
            return;
        }
        this.log.verbose("Script: " + txt);
        if (this.classpath != null) {
            this.log.debug("Explicit Classpath: " + this.classpath.toString());
        }
        if (this.fork) {
            this.log.debug("Using fork mode");
            try {
                this.createClasspathParts();
                this.createNewArgs(txt);
                super.setFork(this.fork);
                super.setClassname(this.useGroovyShell ? "groovy.lang.GroovyShell" : "org.codehaus.groovy.ant.Groovy");
                super.execute();
            }
            catch (Exception e) {
                final StringWriter writer = new StringWriter();
                new ErrorReporter(e, false).write(new PrintWriter(writer));
                final String message = writer.toString();
                throw new BuildException("Script Failed: " + message, (Throwable)e, this.getLocation());
            }
            return;
        }
        Object mavenPom = null;
        final Project project = this.getProject();
        ClassLoader savedLoader = null;
        final Thread thread = Thread.currentThread();
        final boolean maven = "org.mudebug.prapr.reloc.commons.grant.GrantProject".equals(project.getClass().getName());
        ClassLoader baseClassLoader;
        if (maven) {
            if (this.contextClassLoader) {
                throw new BuildException("Using setContextClassLoader not permitted when using Maven.", this.getLocation());
            }
            try {
                final Object propsHandler = project.getClass().getMethod("getPropsHandler", (Class<?>[])new Class[0]).invoke(project, new Object[0]);
                final Field contextField = propsHandler.getClass().getDeclaredField("context");
                contextField.setAccessible(true);
                final Object context = contextField.get(propsHandler);
                mavenPom = InvokerHelper.invokeMethod(context, "getProject", new Object[0]);
            }
            catch (Exception e2) {
                throw new BuildException("Impossible to retrieve Maven's Ant project: " + e2.getMessage(), this.getLocation());
            }
            baseClassLoader = mavenPom.getClass().getClassLoader();
        }
        else {
            baseClassLoader = GroovyShell.class.getClassLoader();
        }
        if (this.contextClassLoader || maven) {
            savedLoader = thread.getContextClassLoader();
            thread.setContextClassLoader(GroovyShell.class.getClassLoader());
        }
        final String scriptName = this.computeScriptName();
        final GroovyClassLoader classLoader = new GroovyClassLoader(baseClassLoader);
        this.addClassPathes(classLoader);
        final GroovyShell groovy = new GroovyShell(classLoader, new Binding(), this.configuration);
        try {
            this.parseAndRunScript(groovy, txt, mavenPom, scriptName, null, new AntBuilder((Task)this));
        }
        finally {
            if (this.contextClassLoader || maven) {
                thread.setContextClassLoader(savedLoader);
            }
        }
    }
    
    private void parseAndRunScript(final GroovyShell shell, final String txt, final Object mavenPom, final String scriptName, final File scriptFile, final AntBuilder builder) {
        try {
            Script script;
            if (scriptFile != null) {
                script = shell.parse(scriptFile);
            }
            else {
                script = shell.parse(txt, scriptName);
            }
            final Project project = this.getProject();
            script.setProperty("ant", builder);
            script.setProperty("project", project);
            script.setProperty("properties", new AntProjectPropertiesDelegate(project));
            script.setProperty("target", this.getOwningTarget());
            script.setProperty("task", this);
            script.setProperty("args", this.cmdline.getCommandline());
            if (mavenPom != null) {
                script.setProperty("pom", mavenPom);
            }
            script.run();
        }
        catch (MissingMethodException mme) {
            if (scriptFile != null) {
                try {
                    shell.run(scriptFile, this.cmdline.getCommandline());
                }
                catch (IOException e) {
                    this.processError(e);
                }
            }
            else {
                shell.run(txt, scriptName, this.cmdline.getCommandline());
            }
        }
        catch (CompilationFailedException e2) {
            this.processError(e2);
        }
        catch (IOException e3) {
            this.processError(e3);
        }
    }
    
    private void processError(final Exception e) {
        final StringWriter writer = new StringWriter();
        new ErrorReporter(e, false).write(new PrintWriter(writer));
        final String message = writer.toString();
        throw new BuildException("Script Failed: " + message, (Throwable)e, this.getLocation());
    }
    
    public static void main(final String[] args) {
        final GroovyShell shell = new GroovyShell(new Binding());
        final Groovy groovy = new Groovy();
        for (int i = 1; i < args.length; ++i) {
            final Commandline.Argument argument = groovy.createArg();
            argument.setValue(args[i]);
        }
        final AntBuilder builder = new AntBuilder();
        groovy.setProject(builder.getProject());
        groovy.parseAndRunScript(shell, null, null, null, new File(args[0]), builder);
    }
    
    private void createClasspathParts() {
        if (this.classpath != null) {
            final Path path = super.createClasspath();
            path.setPath(this.classpath.toString());
        }
        if (this.includeAntRuntime) {
            final Path path = super.createClasspath();
            path.setPath(System.getProperty("java.class.path"));
        }
        String groovyHome = null;
        final String[] strings = this.getSysProperties().getVariables();
        if (strings != null) {
            for (final String prop : strings) {
                if (prop.startsWith("-Dgroovy.home=")) {
                    groovyHome = prop.substring("-Dgroovy.home=".length());
                }
            }
        }
        if (groovyHome == null) {
            groovyHome = System.getProperty("groovy.home");
        }
        if (groovyHome == null) {
            groovyHome = System.getenv("GROOVY_HOME");
        }
        if (groovyHome == null) {
            throw new IllegalStateException("Neither ${groovy.home} nor GROOVY_HOME defined.");
        }
        final File jarDir = new File(groovyHome, "embeddable");
        if (!jarDir.exists()) {
            throw new IllegalStateException("GROOVY_HOME incorrectly defined. No embeddable directory found in: " + groovyHome);
        }
        final File[] arr$2;
        final File[] files = arr$2 = jarDir.listFiles();
        for (final File file : arr$2) {
            try {
                this.log.debug("Adding jar to classpath: " + file.getCanonicalPath());
            }
            catch (IOException ex) {}
            final Path path = super.createClasspath();
            path.setLocation(file);
        }
    }
    
    private void createNewArgs(final String txt) throws IOException {
        final String[] args = this.cmdline.getCommandline();
        final File tempFile = FileUtils.getFileUtils().createTempFile("embedded_script_in_", "groovy_Ant_task", (File)null, true, true);
        final String[] commandline = new String[args.length + 1];
        DefaultGroovyMethods.write(tempFile, txt);
        commandline[0] = tempFile.getCanonicalPath();
        System.arraycopy(args, 0, commandline, 1, args.length);
        super.clearArgs();
        for (final String arg : commandline) {
            final Commandline.Argument argument = super.createArg();
            argument.setValue(arg);
        }
    }
    
    private String computeScriptName() {
        if (this.srcFile != null) {
            return this.srcFile.getAbsolutePath();
        }
        String name = "embedded_script_in_";
        if (this.getLocation().getFileName().length() > 0) {
            name += this.getLocation().getFileName().replaceAll("[^\\w_\\.]", "_").replaceAll("[\\.]", "_dot_");
        }
        else {
            name += "groovy_Ant_task";
        }
        return name;
    }
    
    protected void addClassPathes(final GroovyClassLoader classLoader) {
        if (this.classpath != null) {
            for (int i = 0; i < this.classpath.list().length; ++i) {
                classLoader.addClasspath(this.classpath.list()[i]);
            }
        }
    }
    
    protected void printResults(final PrintStream out) {
        this.log.debug("printResults()");
        final StringBuffer line = new StringBuffer();
        out.println(line);
        out.println();
    }
    
    public void setContextClassLoader(final boolean contextClassLoader) {
        this.contextClassLoader = contextClassLoader;
    }
}
