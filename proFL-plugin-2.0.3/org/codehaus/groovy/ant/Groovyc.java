// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.ant;

import org.codehaus.groovy.control.SourceExtensionHandler;
import org.apache.tools.ant.AntClassLoader;
import org.codehaus.groovy.tools.RootLoader;
import java.net.URL;
import groovy.lang.GroovyClassLoader;
import java.security.CodeSource;
import org.codehaus.groovy.tools.javac.JavaAwareCompilationUnit;
import org.codehaus.groovy.control.CompilationUnit;
import groovyjarjarcommonscli.CommandLine;
import groovyjarjarcommonscli.Options;
import java.util.Enumeration;
import org.codehaus.groovy.tools.ErrorReporter;
import java.io.StringWriter;
import groovyjarjarcommonscli.PosixParser;
import org.codehaus.groovy.tools.FileSystemCompiler;
import org.apache.tools.ant.taskdefs.Execute;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.IOException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Collection;
import java.util.StringTokenizer;
import org.apache.tools.ant.RuntimeConfigurable;
import java.util.Map;
import java.nio.charset.Charset;
import java.util.Iterator;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.SourceFileScanner;
import org.apache.tools.ant.util.GlobPatternMapper;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Reference;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import org.apache.tools.ant.Task;
import java.util.Set;
import java.util.List;
import org.apache.tools.ant.taskdefs.Javac;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.io.File;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.taskdefs.MatchingTask;

public class Groovyc extends MatchingTask
{
    private final LoggingHelper log;
    private Path src;
    private File destDir;
    private Path compileClasspath;
    private Path compileSourcepath;
    private String encoding;
    private boolean stacktrace;
    private boolean verbose;
    private boolean includeAntRuntime;
    private boolean includeJavaRuntime;
    private boolean fork;
    private File forkJDK;
    private String memoryInitialSize;
    private String memoryMaximumSize;
    private String scriptExtension;
    private String targetBytecode;
    protected boolean failOnError;
    protected boolean listFiles;
    protected File[] compileList;
    private String updatedProperty;
    private String errorProperty;
    private boolean taskSuccess;
    private boolean includeDestClasses;
    protected CompilerConfiguration configuration;
    private Javac javac;
    private boolean jointCompilation;
    private List<File> temporaryFiles;
    private File stubDir;
    private boolean keepStubs;
    private Set<String> scriptExtensions;
    
    public Groovyc() {
        this.log = new LoggingHelper((Task)this);
        this.stacktrace = false;
        this.verbose = false;
        this.includeAntRuntime = true;
        this.includeJavaRuntime = false;
        this.fork = false;
        this.scriptExtension = "*.groovy";
        this.targetBytecode = null;
        this.failOnError = true;
        this.listFiles = false;
        this.compileList = new File[0];
        this.taskSuccess = true;
        this.includeDestClasses = true;
        this.temporaryFiles = new ArrayList<File>(2);
        this.scriptExtensions = new LinkedHashSet<String>();
    }
    
    public Path createSrc() {
        if (this.src == null) {
            this.src = new Path(this.getProject());
        }
        return this.src.createPath();
    }
    
    protected Path recreateSrc() {
        this.src = null;
        return this.createSrc();
    }
    
    public void setSrcdir(final Path srcDir) {
        if (this.src == null) {
            this.src = srcDir;
        }
        else {
            this.src.append(srcDir);
        }
    }
    
    public Path getSrcdir() {
        return this.src;
    }
    
    public void setScriptExtension(final String scriptExtension) {
        if (scriptExtension.startsWith("*.")) {
            this.scriptExtension = scriptExtension;
        }
        else if (scriptExtension.startsWith(".")) {
            this.scriptExtension = "*" + scriptExtension;
        }
        else {
            this.scriptExtension = "*." + scriptExtension;
        }
    }
    
    public String getScriptExtension() {
        return this.scriptExtension;
    }
    
    public void setTargetBytecode(final String version) {
        if ("1.4".equals(version) || "1.5".equals(version)) {
            this.targetBytecode = version;
        }
    }
    
    public String getTargetBytecode() {
        return this.targetBytecode;
    }
    
    public void setDestdir(final File destDir) {
        this.destDir = destDir;
    }
    
    public File getDestdir() {
        return this.destDir;
    }
    
    public void setSourcepath(final Path sourcepath) {
        if (this.compileSourcepath == null) {
            this.compileSourcepath = sourcepath;
        }
        else {
            this.compileSourcepath.append(sourcepath);
        }
    }
    
    public Path getSourcepath() {
        return this.compileSourcepath;
    }
    
    public Path createSourcepath() {
        if (this.compileSourcepath == null) {
            this.compileSourcepath = new Path(this.getProject());
        }
        return this.compileSourcepath.createPath();
    }
    
    public void setSourcepathRef(final Reference r) {
        this.createSourcepath().setRefid(r);
    }
    
    public void setClasspath(final Path classpath) {
        if (this.compileClasspath == null) {
            this.compileClasspath = classpath;
        }
        else {
            this.compileClasspath.append(classpath);
        }
    }
    
    public Path getClasspath() {
        return this.compileClasspath;
    }
    
    public Path createClasspath() {
        if (this.compileClasspath == null) {
            this.compileClasspath = new Path(this.getProject());
        }
        return this.compileClasspath.createPath();
    }
    
    public void setClasspathRef(final Reference r) {
        this.createClasspath().setRefid(r);
    }
    
    public void setListfiles(final boolean list) {
        this.listFiles = list;
    }
    
    public boolean getListfiles() {
        return this.listFiles;
    }
    
    public void setFailonerror(final boolean fail) {
        this.failOnError = fail;
    }
    
    public void setProceed(final boolean proceed) {
        this.failOnError = !proceed;
    }
    
    public boolean getFailonerror() {
        return this.failOnError;
    }
    
    public void setMemoryInitialSize(final String memoryInitialSize) {
        this.memoryInitialSize = memoryInitialSize;
    }
    
    public String getMemoryInitialSize() {
        return this.memoryInitialSize;
    }
    
    public void setMemoryMaximumSize(final String memoryMaximumSize) {
        this.memoryMaximumSize = memoryMaximumSize;
    }
    
    public String getMemoryMaximumSize() {
        return this.memoryMaximumSize;
    }
    
    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public void setVerbose(final boolean verbose) {
        this.verbose = verbose;
    }
    
    public boolean getVerbose() {
        return this.verbose;
    }
    
    public void setIncludeantruntime(final boolean include) {
        this.includeAntRuntime = include;
    }
    
    public boolean getIncludeantruntime() {
        return this.includeAntRuntime;
    }
    
    public void setIncludejavaruntime(final boolean include) {
        this.includeJavaRuntime = include;
    }
    
    public boolean getIncludejavaruntime() {
        return this.includeJavaRuntime;
    }
    
    public void setFork(final boolean f) {
        this.fork = f;
    }
    
    public void setJavaHome(final File home) {
        this.forkJDK = home;
    }
    
    public void setUpdatedProperty(final String updatedProperty) {
        this.updatedProperty = updatedProperty;
    }
    
    public void setErrorProperty(final String errorProperty) {
        this.errorProperty = errorProperty;
    }
    
    public void setIncludeDestClasses(final boolean includeDestClasses) {
        this.includeDestClasses = includeDestClasses;
    }
    
    public boolean isIncludeDestClasses() {
        return this.includeDestClasses;
    }
    
    public boolean getTaskSuccess() {
        return this.taskSuccess;
    }
    
    public void addConfiguredJavac(final Javac javac) {
        this.javac = javac;
        this.jointCompilation = true;
    }
    
    public void setStacktrace(final boolean stacktrace) {
        this.stacktrace = stacktrace;
    }
    
    public void execute() throws BuildException {
        this.checkParameters();
        this.resetFileLists();
        this.loadRegisteredScriptExtensions();
        if (this.javac != null) {
            this.jointCompilation = true;
        }
        final String[] arr$;
        final String[] list = arr$ = this.src.list();
        for (final String filename : arr$) {
            final File file = this.getProject().resolveFile(filename);
            if (!file.exists()) {
                throw new BuildException("srcdir \"" + file.getPath() + "\" does not exist!", this.getLocation());
            }
            final DirectoryScanner ds = this.getDirectoryScanner(file);
            final String[] files = ds.getIncludedFiles();
            this.scanDir(file, (this.destDir != null) ? this.destDir : file, files);
        }
        this.compile();
        if (this.updatedProperty != null && this.taskSuccess && this.compileList.length != 0) {
            this.getProject().setNewProperty(this.updatedProperty, "true");
        }
    }
    
    protected void resetFileLists() {
        this.compileList = new File[0];
        this.scriptExtensions = new LinkedHashSet<String>();
    }
    
    protected void scanDir(final File srcDir, final File destDir, final String[] files) {
        final GlobPatternMapper m = new GlobPatternMapper();
        final SourceFileScanner sfs = new SourceFileScanner((Task)this);
        for (final String extension : this.getScriptExtensions()) {
            m.setFrom("*." + extension);
            m.setTo("*.class");
            final File[] newFiles = sfs.restrictAsFiles(files, srcDir, destDir, (FileNameMapper)m);
            this.addToCompileList(newFiles);
        }
        if (this.jointCompilation) {
            m.setFrom("*.java");
            m.setTo("*.class");
            final File[] newFiles = sfs.restrictAsFiles(files, srcDir, destDir, (FileNameMapper)m);
            this.addToCompileList(newFiles);
        }
    }
    
    protected void addToCompileList(final File[] newFiles) {
        if (newFiles.length > 0) {
            final File[] newCompileList = new File[this.compileList.length + newFiles.length];
            System.arraycopy(this.compileList, 0, newCompileList, 0, this.compileList.length);
            System.arraycopy(newFiles, 0, newCompileList, this.compileList.length, newFiles.length);
            this.compileList = newCompileList;
        }
    }
    
    public File[] getFileList() {
        return this.compileList;
    }
    
    protected void checkParameters() throws BuildException {
        if (this.src == null) {
            throw new BuildException("srcdir attribute must be set!", this.getLocation());
        }
        if (this.src.size() == 0) {
            throw new BuildException("srcdir attribute must be set!", this.getLocation());
        }
        if (this.destDir != null && !this.destDir.isDirectory()) {
            throw new BuildException("destination directory \"" + this.destDir + "\" does not exist or is not a directory", this.getLocation());
        }
        if (this.encoding != null && !Charset.isSupported(this.encoding)) {
            throw new BuildException("encoding \"" + this.encoding + "\" not supported.");
        }
    }
    
    protected void compile() {
        try {
            if (this.compileList.length > 0) {
                this.log("Compiling " + this.compileList.length + " source file" + ((this.compileList.length == 1) ? "" : "s") + ((this.destDir != null) ? (" to " + this.destDir) : ""));
                if (this.listFiles) {
                    for (final File srcFile : this.compileList) {
                        this.log(srcFile.getAbsolutePath());
                    }
                }
                final Path classpath = (this.getClasspath() != null) ? this.getClasspath() : new Path(this.getProject());
                final List<String> jointOptions = new ArrayList<String>();
                if (this.jointCompilation) {
                    final RuntimeConfigurable rc = this.javac.getRuntimeConfigurableWrapper();
                    for (final Map.Entry e : rc.getAttributeMap().entrySet()) {
                        final String key = e.getKey().toString();
                        final String value = this.getProject().replaceProperties(e.getValue().toString());
                        if (key.contains("debug")) {
                            String level = "";
                            if (this.javac.getDebugLevel() != null) {
                                level = ":" + this.javac.getDebugLevel();
                            }
                            jointOptions.add("-Fg" + level);
                        }
                        else {
                            if (key.contains("debugLevel")) {
                                continue;
                            }
                            if (key.contains("nowarn") || key.contains("verbose") || key.contains("deprecation")) {
                                if (!"on".equalsIgnoreCase(value) && !"true".equalsIgnoreCase(value) && !"yes".equalsIgnoreCase("value")) {
                                    continue;
                                }
                                jointOptions.add("-F" + key);
                            }
                            else if (key.contains("classpath")) {
                                classpath.add(this.javac.getClasspath());
                            }
                            else if (key.contains("depend") || key.contains("extdirs") || key.contains("encoding") || key.contains("source") || key.contains("target") || key.contains("verbose")) {
                                jointOptions.add("-J" + key + "=" + value);
                            }
                            else {
                                this.log("The option " + key + " cannot be set on the contained <javac> element. The option will be ignored", 1);
                            }
                        }
                    }
                    final Enumeration children = rc.getChildren();
                    while (children.hasMoreElements()) {
                        final RuntimeConfigurable childrc = children.nextElement();
                        if (childrc.getElementTag().equals("compilerarg")) {
                            for (final Map.Entry e2 : childrc.getAttributeMap().entrySet()) {
                                final String key2 = e2.getKey().toString();
                                if (key2.equals("value")) {
                                    final String value2 = this.getProject().replaceProperties(e2.getValue().toString());
                                    final StringTokenizer st = new StringTokenizer(value2, " ");
                                    while (st.hasMoreTokens()) {
                                        final String optionStr = st.nextToken();
                                        jointOptions.add(optionStr.replace("-X", "-FX"));
                                    }
                                }
                            }
                        }
                    }
                }
                final String separator = System.getProperty("file.separator");
                final List<String> commandLineList = new ArrayList<String>();
                if (this.fork) {
                    String javaHome;
                    if (this.forkJDK != null) {
                        javaHome = this.forkJDK.getPath();
                    }
                    else {
                        javaHome = System.getProperty("java.home");
                    }
                    if (this.includeAntRuntime) {
                        classpath.addExisting(new Path(this.getProject()).concatSystemClasspath("last"));
                    }
                    if (this.includeJavaRuntime) {
                        classpath.addJavaRuntime();
                    }
                    commandLineList.add(javaHome + separator + "bin" + separator + "java");
                    commandLineList.add("-classpath");
                    commandLineList.add(classpath.toString());
                    final String fileEncodingProp = System.getProperty("file.encoding");
                    if (fileEncodingProp != null && !fileEncodingProp.equals("")) {
                        commandLineList.add("-Dfile.encoding=" + fileEncodingProp);
                    }
                    if (this.targetBytecode != null) {
                        commandLineList.add("-Dgroovy.target.bytecode=" + this.targetBytecode);
                    }
                    if (this.memoryInitialSize != null && !this.memoryInitialSize.equals("")) {
                        commandLineList.add("-Xms" + this.memoryInitialSize);
                    }
                    if (this.memoryMaximumSize != null && !this.memoryMaximumSize.equals("")) {
                        commandLineList.add("-Xmx" + this.memoryMaximumSize);
                    }
                    if (!"*.groovy".equals(this.getScriptExtension())) {
                        String tmpExtension = this.getScriptExtension();
                        if (tmpExtension.startsWith("*.")) {
                            tmpExtension = tmpExtension.substring(1);
                        }
                        commandLineList.add("-Dgroovy.default.scriptExtension=" + tmpExtension);
                    }
                    commandLineList.add("org.codehaus.groovy.tools.FileSystemCompiler");
                }
                commandLineList.add("--classpath");
                commandLineList.add(classpath.toString());
                if (this.jointCompilation) {
                    commandLineList.add("-j");
                    commandLineList.addAll(jointOptions);
                }
                commandLineList.add("-d");
                commandLineList.add(this.destDir.getPath());
                if (this.encoding != null) {
                    commandLineList.add("--encoding");
                    commandLineList.add(this.encoding);
                }
                if (this.stacktrace) {
                    commandLineList.add("-e");
                }
                int count = 0;
                if (this.fork) {
                    for (final File srcFile2 : this.compileList) {
                        count += srcFile2.getPath().length();
                    }
                    for (final Object commandLineArg : commandLineList) {
                        count += commandLineArg.toString().length();
                    }
                    count += this.compileList.length;
                    count += commandLineList.size();
                }
                if (this.fork && count > 32767) {
                    try {
                        final File tempFile = File.createTempFile("groovyc-files-", ".txt");
                        this.temporaryFiles.add(tempFile);
                        final PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
                        for (final File srcFile3 : this.compileList) {
                            pw.println(srcFile3.getPath());
                        }
                        pw.close();
                        commandLineList.add("@" + tempFile.getPath());
                    }
                    catch (IOException e3) {
                        this.log("Error creating file list", (Throwable)e3, 0);
                    }
                }
                else {
                    for (final File srcFile2 : this.compileList) {
                        commandLineList.add(srcFile2.getPath());
                    }
                }
                final String[] commandLine = new String[commandLineList.size()];
                for (int k = 0; k < commandLine.length; ++k) {
                    commandLine[k] = commandLineList.get(k);
                }
                this.log("Compilation arguments:", 3);
                this.log(DefaultGroovyMethods.join(commandLine, "\n"), 3);
                if (this.fork) {
                    final Execute executor = new Execute();
                    executor.setAntRun(this.getProject());
                    executor.setWorkingDirectory(this.getProject().getBaseDir());
                    executor.setCommandline(commandLine);
                    try {
                        executor.execute();
                    }
                    catch (IOException ioe) {
                        throw new BuildException("Error running forked groovyc.", (Throwable)ioe);
                    }
                    final int returnCode = executor.getExitValue();
                    if (returnCode != 0) {
                        if (this.failOnError) {
                            throw new BuildException("Forked groovyc returned error code: " + returnCode);
                        }
                        this.log("Forked groovyc returned error code: " + returnCode, 0);
                    }
                }
                else {
                    try {
                        final Options options = FileSystemCompiler.createCompilationOptions();
                        final PosixParser cliParser = new PosixParser();
                        final CommandLine cli = cliParser.parse(options, commandLine);
                        (this.configuration = FileSystemCompiler.generateCompilerConfigurationFromOptions(cli)).setScriptExtensions(this.getScriptExtensions());
                        String tmpExtension2 = this.getScriptExtension();
                        if (tmpExtension2.startsWith("*.")) {
                            tmpExtension2 = tmpExtension2.substring(1);
                        }
                        this.configuration.setDefaultScriptExtension(tmpExtension2);
                        final String[] filenames = FileSystemCompiler.generateFileNamesFromOptions(cli);
                        boolean fileNameErrors = filenames == null;
                        fileNameErrors = (fileNameErrors && !FileSystemCompiler.validateFiles(filenames));
                        if (this.targetBytecode != null) {
                            this.configuration.setTargetBytecode(this.targetBytecode);
                        }
                        if (!fileNameErrors) {
                            FileSystemCompiler.doCompilation(this.configuration, this.makeCompileUnit(), filenames);
                        }
                    }
                    catch (Exception re) {
                        Throwable t = re;
                        if (re.getClass() == RuntimeException.class && re.getCause() != null) {
                            t = re.getCause();
                        }
                        final StringWriter writer = new StringWriter();
                        new ErrorReporter(t, false).write(new PrintWriter(writer));
                        final String message = writer.toString();
                        if (this.failOnError) {
                            this.log(message, 2);
                            throw new BuildException("Compilation Failed", t, this.getLocation());
                        }
                        this.log(message, 0);
                    }
                }
            }
        }
        finally {
            for (final File temporaryFile : this.temporaryFiles) {
                try {
                    FileSystemCompiler.deleteRecursive(temporaryFile);
                }
                catch (Throwable t2) {
                    System.err.println("error: could not delete temp files - " + temporaryFile.getPath());
                }
            }
        }
    }
    
    protected CompilationUnit makeCompileUnit() {
        final Map<String, Object> options = this.configuration.getJointCompilationOptions();
        if (options != null) {
            if (this.keepStubs) {
                options.put("keepStubs", Boolean.TRUE);
            }
            if (this.stubDir != null) {
                options.put("stubDir", this.stubDir);
            }
            else {
                try {
                    final File tempStubDir = FileSystemCompiler.createTempDir();
                    this.temporaryFiles.add(tempStubDir);
                    options.put("stubDir", tempStubDir);
                }
                catch (IOException ioe) {
                    throw new BuildException((Throwable)ioe);
                }
            }
            return new JavaAwareCompilationUnit(this.configuration, this.buildClassLoaderFor());
        }
        return new CompilationUnit(this.configuration, null, this.buildClassLoaderFor());
    }
    
    protected GroovyClassLoader buildClassLoaderFor() {
        final ClassLoader parent = (ClassLoader)(this.getIncludeantruntime() ? this.getClass().getClassLoader() : new AntClassLoader((ClassLoader)new RootLoader(new URL[0], (ClassLoader)null), this.getProject(), this.getClasspath()));
        if (parent instanceof AntClassLoader) {
            final AntClassLoader antLoader = (AntClassLoader)parent;
            final String[] pathElm = antLoader.getClasspath().split(File.pathSeparator);
            final List<String> classpath = this.configuration.getClasspath();
            for (final String cpEntry : classpath) {
                boolean found = false;
                for (final String path : pathElm) {
                    if (cpEntry.equals(path)) {
                        found = true;
                        break;
                    }
                }
                if (!found && new File(cpEntry).exists()) {
                    antLoader.addPathElement(cpEntry);
                }
            }
        }
        return new GroovyClassLoader(parent, this.configuration);
    }
    
    public void setStubdir(final File stubDir) {
        this.jointCompilation = true;
        this.stubDir = stubDir;
    }
    
    public File getStubdir() {
        return this.stubDir;
    }
    
    public void setKeepStubs(final boolean keepStubs) {
        this.keepStubs = keepStubs;
    }
    
    public boolean getKeepStubs() {
        return this.keepStubs;
    }
    
    private Set<String> getScriptExtensions() {
        return this.scriptExtensions;
    }
    
    private void loadRegisteredScriptExtensions() {
        if (this.scriptExtensions.isEmpty()) {
            this.scriptExtensions.add(this.getScriptExtension().substring(2));
            final Path classpath = (this.getClasspath() != null) ? this.getClasspath() : new Path(this.getProject());
            final String[] pe = classpath.list();
            final GroovyClassLoader loader = new GroovyClassLoader(this.getClass().getClassLoader());
            for (final String file : pe) {
                loader.addClasspath(file);
            }
            this.scriptExtensions.addAll(SourceExtensionHandler.getRegisteredExtensions(loader));
        }
    }
}
