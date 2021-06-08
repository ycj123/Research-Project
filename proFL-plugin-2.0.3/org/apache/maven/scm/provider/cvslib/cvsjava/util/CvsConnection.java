// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.util;

import org.netbeans.lib.cvsclient.connection.StandardScrambler;
import org.netbeans.lib.cvsclient.commandLine.CommandFactory;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.log.ScmLogger;
import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import org.netbeans.lib.cvsclient.event.CVSListener;
import org.netbeans.lib.cvsclient.command.CommandAbortedException;
import org.netbeans.lib.cvsclient.connection.AbstractConnection;
import org.netbeans.lib.cvsclient.admin.AdminHandler;
import org.netbeans.lib.cvsclient.admin.StandardAdminHandler;
import org.netbeans.lib.cvsclient.connection.PServerConnection;
import org.netbeans.lib.cvsclient.connection.ConnectionFactory;
import org.netbeans.lib.cvsclient.connection.ExtConnection;
import java.io.IOException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.netbeans.lib.cvsclient.CVSRoot;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;
import org.netbeans.lib.cvsclient.Client;
import org.netbeans.lib.cvsclient.connection.Connection;

public class CvsConnection
{
    private String repository;
    private String localPath;
    private Connection connection;
    private Client client;
    private GlobalOptions globalOptions;
    
    private CvsConnection() {
    }
    
    public boolean executeCommand(final Command command) throws CommandException, AuthenticationException {
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
    
    private void connect(final CVSRoot root, final String password) throws AuthenticationException, CommandAbortedException {
        if ("ext".equals(root.getMethod())) {
            String cvsRsh = System.getProperty("maven.scm.cvs.java.cvs_rsh");
            if (cvsRsh == null) {
                try {
                    cvsRsh = CommandLineUtils.getSystemEnvVars().getProperty("CVS_RSH");
                }
                catch (IOException ex) {}
            }
            if (cvsRsh != null) {
                if (cvsRsh.indexOf(32) < 0) {
                    String username = root.getUserName();
                    if (username == null) {
                        username = System.getProperty("user.name");
                    }
                    cvsRsh = cvsRsh + " " + username + "@" + root.getHostName() + " cvs server";
                }
                final AbstractConnection conn = new ExtConnection(cvsRsh);
                conn.setRepository(root.getRepository());
                this.connection = conn;
            }
            else {
                this.connection = new org.apache.maven.scm.provider.cvslib.cvsjava.util.ExtConnection(root);
            }
        }
        else {
            this.connection = ConnectionFactory.getConnection(root);
            if ("pserver".equals(root.getMethod())) {
                ((PServerConnection)this.connection).setEncodedPassword(password);
            }
        }
        this.connection.open();
        (this.client = new Client(this.connection, new StandardAdminHandler())).setLocalPath(this.localPath);
    }
    
    private void disconnect() {
        if (this.connection != null && this.connection.isOpen()) {
            try {
                this.connection.close();
            }
            catch (IOException ex) {}
        }
    }
    
    private void addListener(final CVSListener listener) {
        if (this.client != null) {
            this.client.getEventManager().addCVSListener(listener);
        }
    }
    
    private static String getCVSRoot(String workingDir) {
        String root = null;
        BufferedReader r = null;
        if (workingDir == null) {
            workingDir = System.getProperty("user.dir");
        }
        try {
            final File f = new File(workingDir);
            final File rootFile = new File(f, "CVS/Root");
            if (rootFile.exists()) {
                r = new BufferedReader(new FileReader(rootFile));
                root = r.readLine();
            }
        }
        catch (IOException e) {
            try {
                if (r != null) {
                    r.close();
                }
            }
            catch (IOException e) {
                System.err.println("Warning: could not close CVS/Root file!");
            }
        }
        finally {
            try {
                if (r != null) {
                    r.close();
                }
            }
            catch (IOException e2) {
                System.err.println("Warning: could not close CVS/Root file!");
            }
        }
        if (root == null) {
            root = System.getProperty("cvs.root");
        }
        return root;
    }
    
    private static int processGlobalOptions(final String[] args, final GlobalOptions globalOptions) {
        final String getOptString = globalOptions.getOptString();
        final GetOpt go = new GetOpt(args, getOptString);
        int ch;
        while ((ch = go.getopt()) != -1) {
            final String arg = go.optArgGet();
            final boolean success = globalOptions.setCVSCommand((char)ch, arg);
            if (!success) {
                throw new IllegalArgumentException("Failed to set CVS Command: -" + ch + " = " + arg);
            }
        }
        return go.optIndexGet();
    }
    
    private static String lookupPassword(final String cvsRoot, final ScmLogger logger) {
        final File passFile = new File(System.getProperty("cygwin.user.home", System.getProperty("user.home")) + File.separatorChar + ".cvspass");
        BufferedReader reader = null;
        String password = null;
        try {
            reader = new BufferedReader(new FileReader(passFile));
            password = processCvspass(cvsRoot, reader);
        }
        catch (IOException e) {
            if (logger.isWarnEnabled()) {
                logger.warn("Could not read password for '" + cvsRoot + "' from '" + passFile + "'", e);
            }
            return null;
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e2) {
                    if (logger.isErrorEnabled()) {
                        logger.error("Warning: could not close password file.");
                    }
                }
            }
        }
        if (password == null && logger.isErrorEnabled()) {
            logger.error("Didn't find password for CVSROOT '" + cvsRoot + "'.");
        }
        return password;
    }
    
    static String processCvspass(final String cvsRoot, final BufferedReader reader) throws IOException {
        String password = null;
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("/")) {
                final String[] cvspass = StringUtils.split(line, " ");
                final String cvspassRoot = cvspass[1];
                if (compareCvsRoot(cvsRoot, cvspassRoot)) {
                    final int index = line.indexOf(cvspassRoot) + cvspassRoot.length() + 1;
                    password = line.substring(index);
                    break;
                }
                continue;
            }
            else {
                if (line.startsWith(cvsRoot)) {
                    password = line.substring(cvsRoot.length() + 1);
                    break;
                }
                continue;
            }
        }
        return password;
    }
    
    static boolean compareCvsRoot(final String cvsRoot, final String target) {
        final String s1 = completeCvsRootPort(cvsRoot);
        final String s2 = completeCvsRootPort(target);
        return s1 != null && s1.equals(s2);
    }
    
    private static String completeCvsRootPort(final String cvsRoot) {
        String result = cvsRoot;
        int idx = cvsRoot.indexOf(58);
        for (int i = 0; i < 2 && idx != -1; idx = cvsRoot.indexOf(58, idx + 1), ++i) {}
        if (idx != -1 && cvsRoot.charAt(idx + 1) == '/') {
            final StringBuilder sb = new StringBuilder();
            sb.append(cvsRoot.substring(0, idx + 1));
            sb.append("2401");
            sb.append(cvsRoot.substring(idx + 1));
            result = sb.toString();
        }
        return result;
    }
    
    public static boolean processCommand(final String[] args, final String localPath, final CVSListener listener, final ScmLogger logger) throws Exception {
        final GlobalOptions globalOptions = new GlobalOptions();
        globalOptions.setCVSRoot(getCVSRoot(localPath));
        int commandIndex;
        try {
            commandIndex = processGlobalOptions(args, globalOptions);
        }
        catch (IllegalArgumentException e) {
            if (logger.isErrorEnabled()) {
                logger.error("Invalid argument: " + e);
            }
            return false;
        }
        if (globalOptions.getCVSRoot() == null) {
            if (logger.isErrorEnabled()) {
                logger.error("No CVS root is set. Check your <repository> information in the POM.");
            }
            return false;
        }
        final String cvsRoot = globalOptions.getCVSRoot();
        CVSRoot root;
        try {
            root = CVSRoot.parse(cvsRoot);
        }
        catch (IllegalArgumentException e3) {
            if (logger.isErrorEnabled()) {
                logger.error("Incorrect format for CVSRoot: " + cvsRoot + "\nThe correct format is: " + "[:method:][[user][:password]@][hostname:[port]]/path/to/repository" + "\nwhere \"method\" is pserver.");
            }
            return false;
        }
        final String command = args[commandIndex];
        Command c;
        try {
            c = CommandFactory.getDefault().createCommand(command, args, ++commandIndex, globalOptions, localPath);
        }
        catch (IllegalArgumentException e2) {
            if (logger.isErrorEnabled()) {
                logger.error("Illegal argument: " + e2.getMessage());
            }
            return false;
        }
        String password = null;
        if ("pserver".equals(root.getMethod())) {
            password = root.getPassword();
            if (password != null) {
                password = StandardScrambler.getInstance().scramble(password);
            }
            else {
                password = lookupPassword(cvsRoot, logger);
                if (password == null) {
                    password = StandardScrambler.getInstance().scramble("");
                }
            }
        }
        final CvsConnection cvsCommand = new CvsConnection();
        cvsCommand.setGlobalOptions(globalOptions);
        cvsCommand.setRepository(root.getRepository());
        cvsCommand.setLocalPath(localPath);
        cvsCommand.connect(root, password);
        cvsCommand.addListener(listener);
        if (logger.isDebugEnabled()) {
            logger.debug("Executing CVS command: " + c.getCVSCommand());
        }
        final boolean result = cvsCommand.executeCommand(c);
        cvsCommand.disconnect();
        return result;
    }
}
