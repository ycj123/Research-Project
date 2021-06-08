// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine;

import org.netbeans.lib.cvsclient.commandLine.command.CommandProvider;
import java.util.Comparator;
import java.util.Arrays;
import org.netbeans.lib.cvsclient.command.BasicCommand;
import org.netbeans.lib.cvsclient.connection.PasswordsFile;
import org.netbeans.lib.cvsclient.connection.StandardScrambler;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.netbeans.lib.cvsclient.command.CommandAbortedException;
import org.netbeans.lib.cvsclient.admin.AdminHandler;
import org.netbeans.lib.cvsclient.admin.StandardAdminHandler;
import org.netbeans.lib.cvsclient.connection.PServerConnection;
import org.netbeans.lib.cvsclient.connection.ConnectionFactory;
import org.netbeans.lib.cvsclient.CVSRoot;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.command.CommandException;
import java.io.PrintStream;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;
import org.netbeans.lib.cvsclient.Client;
import org.netbeans.lib.cvsclient.connection.Connection;

public class CVSCommand
{
    private static final String HELP_OPTIONS = "--help-options";
    private static final String HELP_COMMANDS = "--help-commands";
    private static final String HELP_SYNONYMS = "--help-synonyms";
    private String repository;
    private String localPath;
    private Connection connection;
    private Client client;
    private GlobalOptions globalOptions;
    private int port;
    
    public CVSCommand() {
        this.port = 0;
    }
    
    public boolean executeCommand(final Command command, final PrintStream errorStream) throws CommandException, AuthenticationException {
        this.client.setErrorStream(errorStream);
        return this.client.executeCommand(command, this.globalOptions);
    }
    
    public void setRepository(final String repository) {
        this.repository = repository;
    }
    
    public void setLocalPath(final String localPath) {
        this.localPath = localPath;
    }
    
    public void setGlobalOptions(final GlobalOptions globalOptions) {
        this.globalOptions = globalOptions;
    }
    
    private void connect(final CVSRoot cvsRoot, final String encodedPassword) throws IllegalArgumentException, AuthenticationException, CommandAbortedException {
        this.connection = ConnectionFactory.getConnection(cvsRoot);
        if ("pserver".equals(cvsRoot.getMethod())) {
            ((PServerConnection)this.connection).setEncodedPassword(encodedPassword);
            if (this.port > 0) {
                ((PServerConnection)this.connection).setPort(this.port);
            }
        }
        this.connection.open();
        (this.client = new Client(this.connection, new StandardAdminHandler())).setLocalPath(this.localPath);
    }
    
    private void addListener(final CVSListener cvsListener) {
        if (this.client != null) {
            this.client.getEventManager().addCVSListener(cvsListener);
        }
    }
    
    private void close(final PrintStream printStream) {
        try {
            this.connection.close();
        }
        catch (IOException obj) {
            printStream.println("Unable to close connection: " + obj);
        }
    }
    
    private static String getCVSRoot(String property) {
        String s = null;
        BufferedReader bufferedReader = null;
        if (property == null) {
            property = System.getProperty("user.dir");
        }
        try {
            final File file = new File(new File(property), "CVS/Root");
            if (file.exists()) {
                bufferedReader = new BufferedReader(new FileReader(file));
                s = bufferedReader.readLine();
            }
        }
        catch (IOException ex) {}
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (IOException ex2) {
                System.err.println("Warning: could not close CVS/Root file!");
            }
        }
        if (s == null) {
            s = System.getProperty("cvs.root");
        }
        return s;
    }
    
    private static int processGlobalOptions(final String[] array, final GlobalOptions globalOptions, final PrintStream printStream) {
        final GetOpt getOpt = new GetOpt(array, globalOptions.getOptString());
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!globalOptions.setCVSCommand((char)getopt, getOpt.optArgGet())) {
                b = true;
            }
        }
        if (b) {
            showUsage(printStream);
            return -10;
        }
        return getOpt.optIndexGet();
    }
    
    private static void showUsage(final PrintStream printStream) {
        printStream.println(MessageFormat.format(ResourceBundle.getBundle(CVSCommand.class.getPackage().getName() + ".Bundle").getString("MSG_HelpUsage"), "--help-options", "--help-commands", "--help-synonyms"));
    }
    
    private static boolean performLogin(final String userName, final String str, final String s, final int port, final GlobalOptions globalOptions) {
        final PServerConnection pServerConnection = new PServerConnection();
        pServerConnection.setUserName(userName);
        String line;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter password: ");
            line = bufferedReader.readLine();
        }
        catch (IOException obj) {
            System.err.println("Could not read password: " + obj);
            return false;
        }
        final String scramble = StandardScrambler.getInstance().scramble(line);
        pServerConnection.setEncodedPassword(scramble);
        pServerConnection.setHostName(str);
        pServerConnection.setRepository(s);
        pServerConnection.setPort(port);
        try {
            pServerConnection.verify();
        }
        catch (AuthenticationException ex) {
            System.err.println("Could not login to host " + str);
            return false;
        }
        final String cvsRoot = globalOptions.getCVSRoot();
        try {
            PasswordsFile.storePassword(cvsRoot, scramble);
            System.err.println("Logged in successfully to " + s + " on host " + str);
            return true;
        }
        catch (IOException ex2) {
            System.err.println("Error: could not write password file.");
            return false;
        }
    }
    
    private static String lookupPassword(final String s, final String str, final PrintStream printStream) {
        final File file = new File(System.getProperty("cvs.passfile", System.getProperty("user.home") + "/.cvspass"));
        BufferedReader bufferedReader = null;
        String s2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String s3;
            while ((s3 = bufferedReader.readLine()) != null) {
                if (s3.startsWith("/1 ")) {
                    s3 = s3.substring("/1 ".length());
                }
                if (s3.startsWith(s + " ")) {
                    s2 = s3.substring(s.length() + 1);
                    break;
                }
                if (s3.startsWith(str + " ")) {
                    s2 = s3.substring(str.length() + 1);
                    break;
                }
            }
        }
        catch (IOException obj) {
            printStream.println("Could not read password for host: " + obj);
            return null;
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex) {
                    printStream.println("Warning: could not close password file.");
                }
            }
        }
        if (s2 == null) {
            printStream.println("Didn't find password for CVSROOT '" + s + "'.");
        }
        return s2;
    }
    
    public static void main(final String[] array) {
        if (processCommand(array, null, System.getProperty("user.dir"), System.out, System.err)) {
            System.exit(0);
        }
        else {
            System.exit(1);
        }
    }
    
    public static boolean processCommand(final String[] array, final File[] array2, final String s, final PrintStream printStream, final PrintStream printStream2) {
        return processCommand(array, array2, s, 0, printStream, printStream2);
    }
    
    public static boolean processCommand(final String[] array, final File[] files, String canonicalPath, final int n, final PrintStream printStream, final PrintStream s) {
        assert printStream != null : "The output stream must be defined.";
        assert s != null : "The error stream must be defined.";
        if (array.length > 0) {
            if ("--help-options".equals(array[0])) {
                printHelpOptions(printStream);
                return true;
            }
            if ("--help-commands".equals(array[0])) {
                printHelpCommands(printStream);
                return true;
            }
            if ("--help-synonyms".equals(array[0])) {
                printHelpSynonyms(printStream);
                return true;
            }
        }
        try {
            canonicalPath = new File(canonicalPath).getCanonicalPath();
        }
        catch (IOException ex3) {}
        final GlobalOptions globalOptions = new GlobalOptions();
        globalOptions.setCVSRoot(getCVSRoot(canonicalPath));
        int processGlobalOptions;
        try {
            processGlobalOptions = processGlobalOptions(array, globalOptions, s);
            if (processGlobalOptions == -10) {
                return true;
            }
        }
        catch (IllegalArgumentException obj) {
            s.println("Invalid argument: " + obj);
            return false;
        }
        if (globalOptions.isShowHelp()) {
            printHelp(processGlobalOptions, array, printStream, s);
            return true;
        }
        if (globalOptions.isShowVersion()) {
            printVersion(printStream, s);
            return true;
        }
        if (globalOptions.getCVSRoot() == null) {
            s.println("No CVS root is set. Use the cvs.root property, e.g. java -Dcvs.root=\":pserver:user@host:/usr/cvs\" or start the application in a directory containing a CVS subdirectory or use the -d command switch.");
            return false;
        }
        final String cvsRoot = globalOptions.getCVSRoot();
        CVSRoot parse;
        try {
            parse = CVSRoot.parse(cvsRoot);
        }
        catch (IllegalArgumentException ex4) {
            s.println("Incorrect format for CVSRoot: " + cvsRoot + "\nThe correct format is: " + "[:method:][[user][:password]@][hostname:[port]]/path/to/repository" + "\nwhere \"method\" is pserver.");
            return false;
        }
        if (processGlobalOptions >= array.length) {
            showUsage(s);
            return false;
        }
        final String s2 = array[processGlobalOptions];
        if (s2.equals("login")) {
            if ("pserver".equals(parse.getMethod())) {
                return performLogin(parse.getUserName(), parse.getHostName(), parse.getRepository(), parse.getPort(), globalOptions);
            }
            s.println("login does not apply for connection type '" + parse.getMethod() + "'");
            return false;
        }
        else {
            Command command;
            try {
                command = CommandFactory.getDefault().createCommand(s2, array, ++processGlobalOptions, globalOptions, canonicalPath);
            }
            catch (IllegalArgumentException ex) {
                s.println("Illegal argument: " + ex.getMessage());
                return false;
            }
            if (files != null && command instanceof BasicCommand) {
                ((BasicCommand)command).setFiles(files);
            }
            String s3 = null;
            if ("pserver".equals(parse.getMethod())) {
                final String password = parse.getPassword();
                if (password != null) {
                    s3 = StandardScrambler.getInstance().scramble(password);
                }
                else {
                    if (n > 0) {
                        parse.setPort(n);
                    }
                    s3 = lookupPassword(cvsRoot, parse.toString(), s);
                    if (s3 == null) {
                        s3 = StandardScrambler.getInstance().scramble("");
                    }
                }
            }
            final CVSCommand cvsCommand = new CVSCommand();
            cvsCommand.setGlobalOptions(globalOptions);
            cvsCommand.setRepository(parse.getRepository());
            if (n > 0) {
                cvsCommand.port = n;
            }
            cvsCommand.setLocalPath(canonicalPath);
            try {
                cvsCommand.connect(parse, s3);
                CVSListener cvsListener;
                if (command instanceof ListenerProvider) {
                    cvsListener = ((ListenerProvider)command).createCVSListener(printStream, s);
                }
                else {
                    cvsListener = new BasicListener(printStream, s);
                }
                cvsCommand.addListener(cvsListener);
                return cvsCommand.executeCommand(command, s);
            }
            catch (AuthenticationException ex2) {
                s.println(ex2.getLocalizedMessage());
                return false;
            }
            catch (CommandAbortedException obj2) {
                s.println("Error: " + obj2);
                Thread.currentThread().interrupt();
                return false;
            }
            catch (Exception obj3) {
                s.println("Error: " + obj3);
                obj3.printStackTrace(s);
                return false;
            }
            finally {
                if (cvsCommand != null) {
                    cvsCommand.close(s);
                }
            }
        }
    }
    
    private static void printHelpOptions(final PrintStream printStream) {
        printStream.println(ResourceBundle.getBundle(CVSCommand.class.getPackage().getName() + ".Bundle").getString("MSG_HelpOptions"));
    }
    
    private static void printHelpCommands(final PrintStream printStream) {
        printStream.println(ResourceBundle.getBundle(CVSCommand.class.getPackage().getName() + ".Bundle").getString("MSG_CVSCommands"));
        final CommandProvider[] commandProviders = CommandFactory.getDefault().getCommandProviders();
        Arrays.sort(commandProviders, new CommandProvidersComparator());
        int n = 0;
        for (int i = 0; i < commandProviders.length; ++i) {
            final int length = commandProviders[i].getName().length();
            if (n < length) {
                n = length;
            }
        }
        n += 2;
        for (int j = 0; j < commandProviders.length; ++j) {
            printStream.print("\t" + commandProviders[j].getName());
            final char[] array = new char[n - commandProviders[j].getName().length()];
            Arrays.fill(array, ' ');
            printStream.print(new String(array));
            commandProviders[j].printShortDescription(printStream);
            printStream.println();
        }
    }
    
    private static void printHelpSynonyms(final PrintStream printStream) {
        printStream.println(ResourceBundle.getBundle(CVSCommand.class.getPackage().getName() + ".Bundle").getString("MSG_CVSSynonyms"));
        final CommandProvider[] commandProviders = CommandFactory.getDefault().getCommandProviders();
        Arrays.sort(commandProviders, new CommandProvidersComparator());
        int n = 0;
        for (int i = 0; i < commandProviders.length; ++i) {
            final int length = commandProviders[i].getName().length();
            if (n < length) {
                n = length;
            }
        }
        n += 2;
        for (int j = 0; j < commandProviders.length; ++j) {
            final String[] synonyms = commandProviders[j].getSynonyms();
            if (synonyms.length > 0) {
                printStream.print("\t" + commandProviders[j].getName());
                final char[] array = new char[n - commandProviders[j].getName().length()];
                Arrays.fill(array, ' ');
                printStream.print(new String(array));
                for (int k = 0; k < synonyms.length; ++k) {
                    printStream.print(synonyms[k] + " ");
                }
                printStream.println();
            }
        }
    }
    
    private static void printHelp(final int n, final String[] array, final PrintStream printStream, final PrintStream printStream2) {
        if (n >= array.length) {
            showUsage(printStream);
        }
        else {
            final String s = array[n];
            final CommandProvider commandProvider = CommandFactory.getDefault().getCommandProvider(s);
            if (commandProvider == null) {
                printUnknownCommand(s, printStream2);
            }
            else {
                commandProvider.printLongDescription(printStream);
            }
        }
    }
    
    private static void printVersion(final PrintStream printStream, final PrintStream printStream2) {
        printStream.println("Java Concurrent Versions System (JavaCVS) " + CVSCommand.class.getPackage().getSpecificationVersion() + " (client)");
    }
    
    private static void printUnknownCommand(final String s, final PrintStream printStream) {
        printStream.println(MessageFormat.format(ResourceBundle.getBundle(CVSCommand.class.getPackage().getName() + ".Bundle").getString("MSG_UnknownCommand"), s));
        printHelpCommands(printStream);
    }
    
    private static final class CommandProvidersComparator implements Comparator
    {
        public int compare(final Object obj, final Object obj2) {
            if (!(obj instanceof CommandProvider) || !(obj2 instanceof CommandProvider)) {
                throw new IllegalArgumentException("Can not compare objects " + obj + " and " + obj2);
            }
            return ((CommandProvider)obj).getName().compareTo(((CommandProvider)obj2).getName());
        }
    }
}
