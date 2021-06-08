// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import groovyjarjarcommonscli.OptionBuilder;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import groovyjarjarcommonscli.CommandLine;
import groovyjarjarcommonscli.PosixParser;
import groovy.lang.GroovySystem;
import groovyjarjarcommonscli.HelpFormatter;
import groovyjarjarcommonscli.Options;
import java.io.File;
import org.codehaus.groovy.tools.javac.JavaAwareCompilationUnit;
import org.codehaus.groovy.control.ConfigurationException;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.CompilationUnit;

public class FileSystemCompiler
{
    private final CompilationUnit unit;
    private static boolean displayStackTraceOnError;
    
    public FileSystemCompiler(final CompilerConfiguration configuration) throws ConfigurationException {
        this(configuration, null);
    }
    
    public FileSystemCompiler(final CompilerConfiguration configuration, final CompilationUnit cu) throws ConfigurationException {
        if (cu != null) {
            this.unit = cu;
        }
        else if (configuration.getJointCompilationOptions() != null) {
            this.unit = new JavaAwareCompilationUnit(configuration);
        }
        else {
            this.unit = new CompilationUnit(configuration);
        }
    }
    
    public void compile(final String[] paths) throws Exception {
        this.unit.addSources(paths);
        this.unit.compile();
    }
    
    public void compile(final File[] files) throws Exception {
        this.unit.addSources(files);
        this.unit.compile();
    }
    
    public static void displayHelp(final Options options) {
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(80, "groovyc [options] <source-files>", "options:", options, "");
    }
    
    public static void displayVersion() {
        final String version = GroovySystem.getVersion();
        System.err.println("Groovy compiler version " + version);
        System.err.println("Copyright 2003-2010 The Codehaus. http://groovy.codehaus.org/");
        System.err.println("");
    }
    
    public static int checkFiles(final String[] filenames) {
        int errors = 0;
        for (final String filename : filenames) {
            final File file = new File(filename);
            if (!file.exists()) {
                System.err.println("error: file not found: " + file);
                ++errors;
            }
            else if (!file.canRead()) {
                System.err.println("error: file not readable: " + file);
                ++errors;
            }
        }
        return errors;
    }
    
    public static boolean validateFiles(final String[] filenames) {
        return checkFiles(filenames) == 0;
    }
    
    public static void commandLineCompile(final String[] args) throws Exception {
        final Options options = createCompilationOptions();
        final PosixParser cliParser = new PosixParser();
        final CommandLine cli = cliParser.parse(options, args);
        if (cli.hasOption('h')) {
            displayHelp(options);
            return;
        }
        if (cli.hasOption('v')) {
            displayVersion();
            return;
        }
        FileSystemCompiler.displayStackTraceOnError = cli.hasOption('e');
        final CompilerConfiguration configuration = generateCompilerConfigurationFromOptions(cli);
        final String[] filenames = generateFileNamesFromOptions(cli);
        boolean fileNameErrors = filenames == null;
        if (!fileNameErrors && filenames.length == 0) {
            displayHelp(options);
            return;
        }
        fileNameErrors = (fileNameErrors && !validateFiles(filenames));
        if (!fileNameErrors) {
            doCompilation(configuration, null, filenames);
        }
    }
    
    public static void main(final String[] args) {
        try {
            commandLineCompile(args);
        }
        catch (Throwable e) {
            new ErrorReporter(e, FileSystemCompiler.displayStackTraceOnError).write(System.err);
            System.exit(1);
        }
    }
    
    public static void doCompilation(final CompilerConfiguration configuration, final CompilationUnit unit, final String[] filenames) throws Exception {
        File tmpDir = null;
        try {
            if (configuration.getJointCompilationOptions() != null && !configuration.getJointCompilationOptions().containsKey("stubDir")) {
                tmpDir = createTempDir();
                configuration.getJointCompilationOptions().put("stubDir", tmpDir);
            }
            final FileSystemCompiler compiler = new FileSystemCompiler(configuration, unit);
            compiler.compile(filenames);
        }
        finally {
            try {
                if (tmpDir != null) {
                    deleteRecursive(tmpDir);
                }
            }
            catch (Throwable t) {
                System.err.println("error: could not delete temp files - " + tmpDir.getPath());
            }
        }
    }
    
    public static String[] generateFileNamesFromOptions(final CommandLine cli) {
        final String[] filenames = cli.getArgs();
        final List<String> fileList = new ArrayList<String>(filenames.length);
        boolean errors = false;
        for (final String filename : filenames) {
            if (filename.startsWith("@")) {
                try {
                    final BufferedReader br = new BufferedReader(new FileReader(filename.substring(1)));
                    String file;
                    while ((file = br.readLine()) != null) {
                        fileList.add(file);
                    }
                }
                catch (IOException ioe) {
                    System.err.println("error: file not readable: " + filename.substring(1));
                    errors = true;
                }
            }
            else {
                fileList.addAll(Arrays.asList(filenames));
            }
        }
        if (errors) {
            return null;
        }
        return fileList.toArray(new String[fileList.size()]);
    }
    
    public static CompilerConfiguration generateCompilerConfigurationFromOptions(final CommandLine cli) {
        final CompilerConfiguration configuration = new CompilerConfiguration();
        if (cli.hasOption("classpath")) {
            configuration.setClasspath(cli.getOptionValue("classpath"));
        }
        if (cli.hasOption('d')) {
            configuration.setTargetDirectory(cli.getOptionValue('d'));
        }
        if (cli.hasOption("encoding")) {
            configuration.setSourceEncoding(cli.getOptionValue("encoding"));
        }
        if (cli.hasOption('j')) {
            final Map<String, Object> compilerOptions = new HashMap<String, Object>();
            String[] opts = cli.getOptionValues("J");
            compilerOptions.put("namedValues", opts);
            opts = cli.getOptionValues("F");
            compilerOptions.put("flags", opts);
            configuration.setJointCompilationOptions(compilerOptions);
        }
        return configuration;
    }
    
    public static Options createCompilationOptions() {
        final Options options2;
        final Options options = options2 = new Options();
        OptionBuilder.hasArg();
        OptionBuilder.withArgName("path");
        OptionBuilder.withDescription("Specify where to find the class files - must be first argument");
        options2.addOption(OptionBuilder.create("classpath"));
        final Options options3 = options;
        OptionBuilder.withLongOpt("classpath");
        OptionBuilder.hasArg();
        OptionBuilder.withArgName("path");
        OptionBuilder.withDescription("Aliases for '-classpath'");
        options3.addOption(OptionBuilder.create("cp"));
        final Options options4 = options;
        OptionBuilder.withLongOpt("sourcepath");
        OptionBuilder.hasArg();
        OptionBuilder.withArgName("path");
        OptionBuilder.withDescription("Specify where to find the source files");
        options4.addOption(OptionBuilder.create());
        final Options options5 = options;
        OptionBuilder.withLongOpt("temp");
        OptionBuilder.hasArg();
        OptionBuilder.withArgName("temp");
        OptionBuilder.withDescription("Specify temporary directory");
        options5.addOption(OptionBuilder.create());
        final Options options6 = options;
        OptionBuilder.withLongOpt("encoding");
        OptionBuilder.hasArg();
        OptionBuilder.withArgName("encoding");
        OptionBuilder.withDescription("Specify the encoding of the user class files");
        options6.addOption(OptionBuilder.create());
        final Options options7 = options;
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("Specify where to place generated class files");
        options7.addOption(OptionBuilder.create('d'));
        final Options options8 = options;
        OptionBuilder.withLongOpt("help");
        OptionBuilder.withDescription("Print a synopsis of standard options");
        options8.addOption(OptionBuilder.create('h'));
        final Options options9 = options;
        OptionBuilder.withLongOpt("version");
        OptionBuilder.withDescription("Print the version");
        options9.addOption(OptionBuilder.create('v'));
        final Options options10 = options;
        OptionBuilder.withLongOpt("exception");
        OptionBuilder.withDescription("Print stack trace on error");
        options10.addOption(OptionBuilder.create('e'));
        final Options options11 = options;
        OptionBuilder.withLongOpt("jointCompilation");
        OptionBuilder.withDescription("Attach javac compiler to compile .java files");
        options11.addOption(OptionBuilder.create('j'));
        final Options options12 = options;
        OptionBuilder.withArgName("property=value");
        OptionBuilder.withValueSeparator();
        OptionBuilder.hasArgs(2);
        OptionBuilder.withDescription("name-value pairs to pass to javac");
        options12.addOption(OptionBuilder.create("J"));
        final Options options13 = options;
        OptionBuilder.withArgName("flag");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("passed to javac for joint compilation");
        options13.addOption(OptionBuilder.create("F"));
        return options;
    }
    
    public static File createTempDir() throws IOException {
        final int MAXTRIES = 3;
        int accessDeniedCounter = 0;
        File tempFile = null;
        int i = 0;
        while (i < 3) {
            try {
                tempFile = File.createTempFile("groovy-generated-", "-java-source");
                tempFile.delete();
                tempFile.mkdirs();
            }
            catch (IOException ioe) {
                if (ioe.getMessage().startsWith("Access is denied")) {
                    ++accessDeniedCounter;
                    try {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex) {}
                }
                if (i != 2) {
                    ++i;
                    continue;
                }
                if (accessDeniedCounter == 3) {
                    final String msg = "Access is denied.\nWe tried " + accessDeniedCounter + " times to create a temporary directory" + " and failed each time. If you are on Windows" + " you are possibly victim to" + " http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6325169. " + " this is no bug in Groovy.";
                    throw new IOException(msg);
                }
                throw ioe;
            }
            break;
        }
        return tempFile;
    }
    
    public static void deleteRecursive(final File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        }
        else if (file.isDirectory()) {
            final File[] files = file.listFiles();
            for (int i = 0; i < files.length; ++i) {
                deleteRecursive(files[i]);
            }
            file.delete();
        }
    }
    
    static {
        FileSystemCompiler.displayStackTraceOnError = false;
    }
}
