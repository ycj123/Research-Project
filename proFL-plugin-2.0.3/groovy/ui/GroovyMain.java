// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import java.math.BigInteger;
import java.io.Writer;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Iterator;
import groovy.lang.Script;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.codehaus.groovy.control.CompilationFailedException;
import groovyjarjarcommonscli.OptionBuilder;
import groovyjarjarcommonscli.CommandLineParser;
import groovyjarjarcommonscli.PosixParser;
import java.io.OutputStream;
import java.io.PrintWriter;
import groovyjarjarcommonscli.HelpFormatter;
import groovyjarjarcommonscli.CommandLine;
import groovyjarjarcommonscli.Options;
import groovyjarjarcommonscli.ParseException;
import groovy.lang.GroovySystem;
import java.io.PrintStream;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.util.List;

public class GroovyMain
{
    private List args;
    private boolean isScriptFile;
    private String script;
    private boolean processFiles;
    private boolean editFiles;
    private boolean autoOutput;
    private boolean autoSplit;
    private String splitPattern;
    private boolean processSockets;
    private int port;
    private String backupExtension;
    private boolean debug;
    private CompilerConfiguration conf;
    
    public GroovyMain() {
        this.splitPattern = " ";
        this.debug = false;
        this.conf = new CompilerConfiguration(System.getProperties());
    }
    
    public static void main(final String[] args) {
        processArgs(args, System.out);
    }
    
    static void processArgs(final String[] args, final PrintStream out) {
        final Options options = buildOptions();
        try {
            final CommandLine cmd = parseCommandLine(options, args);
            if (cmd.hasOption('h')) {
                printHelp(out, options);
            }
            else if (cmd.hasOption('v')) {
                final String version = GroovySystem.getVersion();
                out.println("Groovy Version: " + version + " JVM: " + System.getProperty("java.version"));
            }
            else if (!process(cmd)) {
                System.exit(1);
            }
        }
        catch (ParseException pe) {
            out.println("error: " + pe.getMessage());
            printHelp(out, options);
        }
    }
    
    private static void printHelp(final PrintStream out, final Options options) {
        final HelpFormatter formatter = new HelpFormatter();
        final PrintWriter pw = new PrintWriter(out);
        formatter.printHelp(pw, 80, "groovy [options] [args]", "options:", options, 2, 4, null, false);
        pw.flush();
    }
    
    private static CommandLine parseCommandLine(final Options options, final String[] args) throws ParseException {
        final CommandLineParser parser = new PosixParser();
        return parser.parse(options, args, true);
    }
    
    private static synchronized Options buildOptions() {
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
        OptionBuilder.withLongOpt("define");
        OptionBuilder.withDescription("define a system property");
        OptionBuilder.hasArg(true);
        OptionBuilder.withArgName("name=value");
        options4.addOption(OptionBuilder.create('D'));
        final Options options5 = options;
        OptionBuilder.hasArg(false);
        OptionBuilder.withDescription("usage information");
        OptionBuilder.withLongOpt("help");
        options5.addOption(OptionBuilder.create('h'));
        final Options options6 = options;
        OptionBuilder.hasArg(false);
        OptionBuilder.withDescription("debug mode will print out full stack traces");
        OptionBuilder.withLongOpt("debug");
        options6.addOption(OptionBuilder.create('d'));
        final Options options7 = options;
        OptionBuilder.hasArg(false);
        OptionBuilder.withDescription("display the Groovy and JVM versions");
        OptionBuilder.withLongOpt("version");
        options7.addOption(OptionBuilder.create('v'));
        final Options options8 = options;
        OptionBuilder.withArgName("charset");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("specify the encoding of the files");
        OptionBuilder.withLongOpt("encoding");
        options8.addOption(OptionBuilder.create('c'));
        final Options options9 = options;
        OptionBuilder.withArgName("script");
        OptionBuilder.hasArg();
        OptionBuilder.withDescription("specify a command line script");
        options9.addOption(OptionBuilder.create('e'));
        final Options options10 = options;
        OptionBuilder.withArgName("extension");
        OptionBuilder.hasOptionalArg();
        OptionBuilder.withDescription("modify files in place; create backup if extension is given (e.g. '.bak')");
        options10.addOption(OptionBuilder.create('i'));
        final Options options11 = options;
        OptionBuilder.hasArg(false);
        OptionBuilder.withDescription("process files line by line using implicit 'line' variable");
        options11.addOption(OptionBuilder.create('n'));
        final Options options12 = options;
        OptionBuilder.hasArg(false);
        OptionBuilder.withDescription("process files line by line and print result (see also -n)");
        options12.addOption(OptionBuilder.create('p'));
        final Options options13 = options;
        OptionBuilder.withArgName("port");
        OptionBuilder.hasOptionalArg();
        OptionBuilder.withDescription("listen on a port and process inbound lines (default: 1960)");
        options13.addOption(OptionBuilder.create('l'));
        final Options options14 = options;
        OptionBuilder.withArgName("splitPattern");
        OptionBuilder.hasOptionalArg();
        OptionBuilder.withDescription("split lines using splitPattern (default '\\s') using implicit 'split' variable");
        OptionBuilder.withLongOpt("autosplit");
        options14.addOption(OptionBuilder.create('a'));
        return options;
    }
    
    private static void setSystemPropertyFrom(final String nameValue) {
        if (nameValue == null) {
            throw new IllegalArgumentException("argument should not be null");
        }
        final int i = nameValue.indexOf("=");
        String name;
        String value;
        if (i == -1) {
            name = nameValue;
            value = Boolean.TRUE.toString();
        }
        else {
            name = nameValue.substring(0, i);
            value = nameValue.substring(i + 1, nameValue.length());
        }
        name = name.trim();
        System.setProperty(name, value);
    }
    
    private static boolean process(final CommandLine line) throws ParseException {
        final List args = line.getArgList();
        if (line.hasOption('D')) {
            final String[] values = line.getOptionValues('D');
            for (int i = 0; i < values.length; ++i) {
                setSystemPropertyFrom(values[i]);
            }
        }
        final GroovyMain main = new GroovyMain();
        main.conf.setSourceEncoding(line.getOptionValue('c', main.conf.getSourceEncoding()));
        main.isScriptFile = !line.hasOption('e');
        main.debug = line.hasOption('d');
        main.conf.setDebug(main.debug);
        main.processFiles = (line.hasOption('p') || line.hasOption('n'));
        main.autoOutput = line.hasOption('p');
        main.editFiles = line.hasOption('i');
        if (main.editFiles) {
            main.backupExtension = line.getOptionValue('i');
        }
        main.autoSplit = line.hasOption('a');
        final String sp = line.getOptionValue('a');
        if (sp != null) {
            main.splitPattern = sp;
        }
        if (main.isScriptFile) {
            if (args.isEmpty()) {
                throw new ParseException("neither -e or filename provided");
            }
            main.script = args.remove(0);
            if (main.script.endsWith(".java")) {
                throw new ParseException("error: cannot compile file with .java extension: " + main.script);
            }
        }
        else {
            main.script = line.getOptionValue('e');
        }
        main.processSockets = line.hasOption('l');
        if (main.processSockets) {
            final String p = line.getOptionValue('l', "1960");
            main.port = Integer.parseInt(p);
        }
        main.args = args;
        return main.run();
    }
    
    private boolean run() {
        try {
            if (this.processSockets) {
                this.processSockets();
            }
            else if (this.processFiles) {
                this.processFiles();
            }
            else {
                this.processOnce();
            }
            return true;
        }
        catch (CompilationFailedException e) {
            System.err.println(e);
            return false;
        }
        catch (Throwable e2) {
            if (e2 instanceof InvokerInvocationException) {
                final InvokerInvocationException iie = (InvokerInvocationException)e2;
                e2 = iie.getCause();
            }
            System.err.println("Caught: " + e2);
            if (this.debug) {
                e2.printStackTrace();
            }
            else {
                final StackTraceElement[] stackTrace = e2.getStackTrace();
                for (int i = 0; i < stackTrace.length; ++i) {
                    final StackTraceElement element = stackTrace[i];
                    final String fileName = element.getFileName();
                    if (fileName != null && !fileName.endsWith(".java")) {
                        System.err.println("\tat " + element);
                    }
                }
            }
            return false;
        }
    }
    
    private void processSockets() throws CompilationFailedException, IOException {
        final GroovyShell groovy = new GroovyShell(this.conf);
        if (this.isScriptFile) {
            groovy.parse(DefaultGroovyMethods.getText(this.huntForTheScriptFile(this.script)));
        }
        else {
            groovy.parse(this.script);
        }
        new GroovySocketServer(groovy, this.isScriptFile, this.script, this.autoOutput, this.port);
    }
    
    public File huntForTheScriptFile(final String input) {
        final String scriptFileName = input.trim();
        File scriptFile = new File(scriptFileName);
        final String[] standardExtensions = { ".groovy", ".gvy", ".gy", ".gsh" };
        for (int i = 0; i < standardExtensions.length && !scriptFile.exists(); scriptFile = new File(scriptFileName + standardExtensions[i]), ++i) {}
        if (!scriptFile.exists()) {
            scriptFile = new File(scriptFileName);
        }
        return scriptFile;
    }
    
    private void processFiles() throws CompilationFailedException, IOException {
        final GroovyShell groovy = new GroovyShell(this.conf);
        Script s;
        if (this.isScriptFile) {
            s = groovy.parse(this.huntForTheScriptFile(this.script));
        }
        else {
            s = groovy.parse(this.script, "main");
        }
        if (this.args.isEmpty()) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            final PrintWriter writer = new PrintWriter(System.out);
            try {
                this.processReader(s, reader, writer);
            }
            finally {
                reader.close();
                writer.close();
            }
        }
        else {
            for (final String filename : this.args) {
                final File file = this.huntForTheScriptFile(filename);
                this.processFile(s, file);
            }
        }
    }
    
    private void processFile(final Script s, final File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
        if (!this.editFiles) {
            final BufferedReader reader = new BufferedReader(new FileReader(file));
            try {
                final PrintWriter writer = new PrintWriter(System.out);
                this.processReader(s, reader, writer);
                writer.flush();
            }
            finally {
                reader.close();
            }
        }
        else {
            File backup;
            if (this.backupExtension == null) {
                backup = File.createTempFile("groovy_", ".tmp");
                backup.deleteOnExit();
            }
            else {
                backup = new File(file.getPath() + this.backupExtension);
            }
            backup.delete();
            if (!file.renameTo(backup)) {
                throw new IOException("unable to rename " + file + " to " + backup);
            }
            final BufferedReader reader2 = new BufferedReader(new FileReader(backup));
            try {
                final PrintWriter writer2 = new PrintWriter(new FileWriter(file));
                try {
                    this.processReader(s, reader2, writer2);
                }
                finally {
                    writer2.close();
                }
            }
            finally {
                reader2.close();
            }
        }
    }
    
    private void processReader(final Script s, final BufferedReader reader, final PrintWriter pw) throws IOException {
        final String lineCountName = "count";
        s.setProperty(lineCountName, BigInteger.ZERO);
        final String autoSplitName = "split";
        s.setProperty("out", pw);
        String line;
        while ((line = reader.readLine()) != null) {
            s.setProperty("line", line);
            s.setProperty(lineCountName, ((BigInteger)s.getProperty(lineCountName)).add(BigInteger.ONE));
            if (this.autoSplit) {
                s.setProperty(autoSplitName, line.split(this.splitPattern));
            }
            final Object o = s.run();
            if (this.autoOutput && o != null) {
                pw.println(o);
            }
        }
    }
    
    private void processOnce() throws CompilationFailedException, IOException {
        final GroovyShell groovy = new GroovyShell(this.conf);
        if (this.isScriptFile) {
            groovy.run(this.huntForTheScriptFile(this.script), this.args);
        }
        else {
            groovy.run(this.script, "script_from_command_line", this.args);
        }
    }
}
