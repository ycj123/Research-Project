// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import jline.SimpleCompletor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.codehaus.groovy.tools.ErrorReporter;
import java.lang.reflect.Method;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.codehaus.groovy.control.CompilationFailedException;
import java.io.Writer;
import jline.Completor;
import java.io.OutputStreamWriter;
import groovy.lang.Binding;
import java.io.IOException;
import groovyjarjarcommonscli.CommandLine;
import groovyjarjarcommonscli.CommandLineParser;
import groovy.lang.GroovySystem;
import groovyjarjarcommonscli.HelpFormatter;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import groovyjarjarcommonscli.PosixParser;
import groovyjarjarcommonscli.OptionBuilder;
import groovyjarjarcommonscli.Options;
import java.util.Map;
import org.codehaus.groovy.control.SourceUnit;
import groovy.lang.Closure;
import jline.ConsoleReader;
import java.io.PrintStream;
import java.io.InputStream;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.tools.shell.util.MessageSource;

@Deprecated
public class InteractiveShell implements Runnable
{
    private static final String NEW_LINE;
    private static final MessageSource MESSAGES;
    private final GroovyShell shell;
    private final InputStream in;
    private final PrintStream out;
    private final PrintStream err;
    private final ConsoleReader reader;
    private Object lastResult;
    private Closure beforeExecution;
    private Closure afterExecution;
    private StringBuffer accepted;
    private String pending;
    private int line;
    private boolean stale;
    private SourceUnit parser;
    private Exception error;
    private static final int COMMAND_ID_EXIT = 0;
    private static final int COMMAND_ID_HELP = 1;
    private static final int COMMAND_ID_DISCARD = 2;
    private static final int COMMAND_ID_DISPLAY = 3;
    private static final int COMMAND_ID_EXPLAIN = 4;
    private static final int COMMAND_ID_EXECUTE = 5;
    private static final int COMMAND_ID_BINDING = 6;
    private static final int COMMAND_ID_DISCARD_LOADED_CLASSES = 7;
    private static final int COMMAND_ID_INSPECT = 8;
    private static final int LAST_COMMAND_ID = 8;
    private static final String[] COMMANDS;
    private static final Map<String, Integer> COMMAND_MAPPINGS;
    private static final Map<String, String> COMMAND_HELP;
    
    public static void main(final String[] args) {
        try {
            processCommandLineArguments(args);
            final InteractiveShell groovy = new InteractiveShell();
            groovy.run();
        }
        catch (Exception e) {
            System.err.println("FATAL: " + e);
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
    
    private static void processCommandLineArguments(final String[] args) throws Exception {
        assert args != null;
        final Options options2;
        final Options options = options2 = new Options();
        OptionBuilder.withLongOpt("help");
        OptionBuilder.withDescription(InteractiveShell.MESSAGES.getMessage("cli.option.help.description"));
        options2.addOption(OptionBuilder.create('h'));
        final Options options3 = options;
        OptionBuilder.withLongOpt("version");
        OptionBuilder.withDescription(InteractiveShell.MESSAGES.getMessage("cli.option.version.description"));
        options3.addOption(OptionBuilder.create('V'));
        final CommandLineParser parser = new PosixParser();
        final CommandLine line = parser.parse(options, args, true);
        final String[] lineargs = line.getArgs();
        if (lineargs.length != 0) {
            System.err.println(InteractiveShell.MESSAGES.format("cli.info.unexpected_args", new Object[] { DefaultGroovyMethods.join(lineargs, " ") }));
            System.exit(1);
        }
        final PrintWriter writer = new PrintWriter(System.out);
        if (line.hasOption('h')) {
            final HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(writer, 80, "groovysh [options]", "", options, 4, 4, "", false);
            writer.flush();
            System.exit(0);
        }
        if (line.hasOption('V')) {
            writer.println(InteractiveShell.MESSAGES.format("cli.info.version", new Object[] { GroovySystem.getVersion() }));
            writer.flush();
            System.exit(0);
        }
    }
    
    public InteractiveShell() throws IOException {
        this(System.in, System.out, System.err);
    }
    
    public InteractiveShell(final InputStream in, final PrintStream out, final PrintStream err) throws IOException {
        this(null, new Binding(), in, out, err);
    }
    
    public InteractiveShell(final Binding binding, final InputStream in, final PrintStream out, final PrintStream err) throws IOException {
        this(null, binding, in, out, err);
    }
    
    public InteractiveShell(final ClassLoader parent, final Binding binding, final InputStream in, final PrintStream out, final PrintStream err) throws IOException {
        this.accepted = new StringBuffer();
        this.stale = false;
        assert binding != null;
        assert in != null;
        assert out != null;
        assert err != null;
        this.in = in;
        this.out = out;
        this.err = err;
        final Writer writer = new OutputStreamWriter(out);
        (this.reader = new ConsoleReader(in, writer)).setDefaultPrompt("groovy> ");
        this.reader.addCompletor((Completor)new CommandNameCompletor());
        if (parent != null) {
            this.shell = new GroovyShell(parent, binding);
        }
        else {
            this.shell = new GroovyShell(binding);
        }
        final Map map = this.shell.getContext().getVariables();
        if (map.get("shell") != null) {
            map.put("shell", this.shell);
        }
    }
    
    public void run() {
        this.out.println(InteractiveShell.MESSAGES.format("startup_banner.0", new Object[] { GroovySystem.getVersion(), System.getProperty("java.version") }));
        this.out.println(InteractiveShell.MESSAGES.getMessage("startup_banner.1"));
        while (true) {
            final String code = this.read();
            if (code == null) {
                break;
            }
            this.reset();
            if (code.length() <= 0) {
                continue;
            }
            try {
                if (this.beforeExecution != null) {
                    this.beforeExecution.call();
                }
                this.lastResult = this.shell.evaluate(code);
                if (this.afterExecution != null) {
                    this.afterExecution.call();
                }
                this.out.print("===> ");
                this.out.println(this.lastResult);
            }
            catch (CompilationFailedException e) {
                this.err.println(e);
            }
            catch (Throwable e2) {
                if (e2 instanceof InvokerInvocationException) {
                    e2 = e2.getCause();
                }
                this.filterAndPrintStackTrace(e2);
            }
        }
    }
    
    public void setBeforeExecution(final Closure beforeExecution) {
        this.beforeExecution = beforeExecution;
    }
    
    public void setAfterExecution(final Closure afterExecution) {
        this.afterExecution = afterExecution;
    }
    
    private void filterAndPrintStackTrace(final Throwable cause) {
        assert cause != null;
        this.err.print("ERROR: ");
        this.err.println(cause);
        cause.printStackTrace(this.err);
    }
    
    protected void reset() {
        this.stale = true;
        this.pending = null;
        this.line = 1;
        this.parser = null;
        this.error = null;
    }
    
    protected String read() {
        this.reset();
        boolean complete = false;
        boolean done = false;
        while (!done) {
            try {
                this.pending = this.reader.readLine();
            }
            catch (IOException ex) {}
            if (this.pending == null) {
                return null;
            }
            final String command = this.pending.trim();
            if (InteractiveShell.COMMAND_MAPPINGS.containsKey(command)) {
                final int code = InteractiveShell.COMMAND_MAPPINGS.get(command);
                switch (code) {
                    case 0: {
                        return null;
                    }
                    case 1: {
                        this.displayHelp();
                        continue;
                    }
                    case 2: {
                        this.reset();
                        done = true;
                        continue;
                    }
                    case 3: {
                        this.displayStatement();
                        continue;
                    }
                    case 4: {
                        this.explainStatement();
                        continue;
                    }
                    case 6: {
                        this.displayBinding();
                        continue;
                    }
                    case 5: {
                        if (complete) {
                            done = true;
                            continue;
                        }
                        this.err.println(InteractiveShell.MESSAGES.getMessage("command.execute.not_complete"));
                        continue;
                    }
                    case 7: {
                        this.resetLoadedClasses();
                        continue;
                    }
                    case 8: {
                        this.inspect();
                        continue;
                    }
                    default: {
                        throw new Error("BUG: Unknown command for code: " + code);
                    }
                }
            }
            else {
                this.freshen();
                if (this.pending.trim().length() == 0) {
                    this.accept();
                }
                else {
                    final String code2 = this.current();
                    if (this.parse(code2)) {
                        this.accept();
                        complete = true;
                    }
                    else if (this.error == null) {
                        this.accept();
                    }
                    else {
                        this.report();
                    }
                }
            }
        }
        return this.accepted(complete);
    }
    
    private void inspect() {
        if (this.lastResult == null) {
            this.err.println(InteractiveShell.MESSAGES.getMessage("command.inspect.no_result"));
            return;
        }
        try {
            final Class type = Class.forName("groovy.inspect.swingui.ObjectBrowser");
            final Method method = type.getMethod("inspect", Object.class);
            method.invoke(type, this.lastResult);
        }
        catch (Exception e) {
            this.err.println("Cannot invoke ObjectBrowser");
            e.printStackTrace();
        }
    }
    
    private String accepted(final boolean complete) {
        if (complete) {
            return this.accepted.toString();
        }
        return "";
    }
    
    private String current() {
        return this.accepted.toString() + this.pending + InteractiveShell.NEW_LINE;
    }
    
    private void accept() {
        this.accepted.append(this.pending).append(InteractiveShell.NEW_LINE);
        ++this.line;
    }
    
    private void freshen() {
        if (this.stale) {
            this.accepted.setLength(0);
            this.stale = false;
        }
    }
    
    private boolean parse(final String code, final int tolerance) {
        assert code != null;
        boolean parsed = false;
        this.parser = null;
        this.error = null;
        try {
            (this.parser = SourceUnit.create("groovysh-script", code, tolerance)).parse();
            parsed = true;
        }
        catch (CompilationFailedException e) {
            if (this.parser.getErrorCollector().getErrorCount() > 1 || !this.parser.failedWithUnexpectedEOF()) {
                this.error = e;
            }
        }
        catch (Exception e2) {
            this.error = e2;
        }
        return parsed;
    }
    
    private boolean parse(final String code) {
        return this.parse(code, 1);
    }
    
    private void report() {
        this.err.println("Discarding invalid text:");
        new ErrorReporter(this.error, false).write(this.err);
    }
    
    private void displayHelp() {
        this.out.println(InteractiveShell.MESSAGES.getMessage("command.help.available_commands"));
        for (int i = 0; i <= 8; ++i) {
            this.out.print("    ");
            this.out.println(InteractiveShell.COMMAND_HELP.get(InteractiveShell.COMMANDS[i]));
        }
    }
    
    private void displayStatement() {
        final String[] lines = this.accepted.toString().split(InteractiveShell.NEW_LINE);
        if (lines.length == 1 && lines[0].trim().equals("")) {
            this.out.println(InteractiveShell.MESSAGES.getMessage("command.display.buffer_empty"));
        }
        else {
            int padsize = 2;
            if (lines.length >= 10) {
                ++padsize;
            }
            if (lines.length >= 100) {
                ++padsize;
            }
            if (lines.length >= 1000) {
                ++padsize;
            }
            for (int i = 0; i < lines.length; ++i) {
                final String lineno = DefaultGroovyMethods.padLeft(String.valueOf(i + 1), padsize, " ");
                this.out.print(lineno);
                this.out.print("> ");
                this.out.println(lines[i]);
            }
        }
    }
    
    private void displayBinding() {
        final Binding context = this.shell.getContext();
        final Map variables = context.getVariables();
        final Set set = variables.keySet();
        if (set.isEmpty()) {
            this.out.println(InteractiveShell.MESSAGES.getMessage("command.binding.binding_empty"));
        }
        else {
            this.out.println(InteractiveShell.MESSAGES.getMessage("command.binding.available_variables"));
            for (final Object key : set) {
                this.out.print("    ");
                this.out.print(key);
                this.out.print(" = ");
                this.out.println(variables.get(key));
            }
        }
    }
    
    private void explainStatement() {
        if (this.parse(this.accepted(true), 10) || this.error == null) {
            this.out.println(InteractiveShell.MESSAGES.getMessage("command.explain.tree_header"));
        }
        else {
            this.out.println(InteractiveShell.MESSAGES.getMessage("command.explain.unparsable"));
        }
    }
    
    private void resetLoadedClasses() {
        this.shell.resetLoadedClasses();
        this.out.println(InteractiveShell.MESSAGES.getMessage("command.discardclasses.classdefs_discarded"));
    }
    
    static {
        NEW_LINE = System.getProperty("line.separator");
        MESSAGES = new MessageSource(InteractiveShell.class);
        COMMANDS = new String[] { "exit", "help", "discard", "display", "explain", "execute", "binding", "discardclasses", "inspect" };
        COMMAND_MAPPINGS = new HashMap<String, Integer>();
        for (int i = 0; i <= 8; ++i) {
            InteractiveShell.COMMAND_MAPPINGS.put(InteractiveShell.COMMANDS[i], i);
        }
        InteractiveShell.COMMAND_MAPPINGS.put("quit", 0);
        InteractiveShell.COMMAND_MAPPINGS.put("go", 5);
        (COMMAND_HELP = new HashMap<String, String>()).put(InteractiveShell.COMMANDS[0], "exit/quit         - " + InteractiveShell.MESSAGES.getMessage("command.exit.descripion"));
        InteractiveShell.COMMAND_HELP.put(InteractiveShell.COMMANDS[1], "help              - " + InteractiveShell.MESSAGES.getMessage("command.help.descripion"));
        InteractiveShell.COMMAND_HELP.put(InteractiveShell.COMMANDS[2], "discard           - " + InteractiveShell.MESSAGES.getMessage("command.discard.descripion"));
        InteractiveShell.COMMAND_HELP.put(InteractiveShell.COMMANDS[3], "display           - " + InteractiveShell.MESSAGES.getMessage("command.display.descripion"));
        InteractiveShell.COMMAND_HELP.put(InteractiveShell.COMMANDS[4], "explain           - " + InteractiveShell.MESSAGES.getMessage("command.explain.descripion"));
        InteractiveShell.COMMAND_HELP.put(InteractiveShell.COMMANDS[5], "execute/go        - " + InteractiveShell.MESSAGES.getMessage("command.execute.descripion"));
        InteractiveShell.COMMAND_HELP.put(InteractiveShell.COMMANDS[6], "binding           - " + InteractiveShell.MESSAGES.getMessage("command.binding.descripion"));
        InteractiveShell.COMMAND_HELP.put(InteractiveShell.COMMANDS[7], "discardclasses    - " + InteractiveShell.MESSAGES.getMessage("command.discardclasses.descripion"));
        InteractiveShell.COMMAND_HELP.put(InteractiveShell.COMMANDS[8], "inspect           - " + InteractiveShell.MESSAGES.getMessage("command.inspect.descripion"));
    }
    
    private class CommandNameCompletor extends SimpleCompletor
    {
        public CommandNameCompletor() {
            super(new String[0]);
            final Iterator iter = InteractiveShell.COMMAND_MAPPINGS.keySet().iterator();
            while (iter.hasNext()) {
                this.addCandidateString((String)iter.next());
            }
        }
    }
}
