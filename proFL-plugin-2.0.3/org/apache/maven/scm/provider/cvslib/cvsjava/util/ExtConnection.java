// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.cvsjava.util;

import org.netbeans.lib.cvsclient.connection.ConnectionModifier;
import java.io.InputStream;
import java.io.File;
import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import ch.ethz.ssh2.StreamGobbler;
import org.netbeans.lib.cvsclient.command.CommandAbortedException;
import java.io.IOException;
import org.netbeans.lib.cvsclient.connection.AuthenticationException;
import org.netbeans.lib.cvsclient.CVSRoot;
import java.io.BufferedReader;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.Connection;
import org.netbeans.lib.cvsclient.connection.AbstractConnection;

public class ExtConnection extends AbstractConnection
{
    private String host;
    private int port;
    private String userName;
    private String password;
    private ch.ethz.ssh2.Connection connection;
    private Session session;
    private BufferedReader stderrReader;
    
    public ExtConnection(final CVSRoot cvsRoot) {
        this(cvsRoot.getHostName(), cvsRoot.getPort(), cvsRoot.getUserName(), cvsRoot.getPassword(), cvsRoot.getRepository());
    }
    
    public ExtConnection(final String host, final int port, final String username, final String password, final String repository) {
        this.userName = username;
        if (this.userName == null) {
            this.userName = System.getProperty("user.name");
        }
        this.password = password;
        this.host = host;
        this.setRepository(repository);
        this.port = port;
        if (this.port == 0) {
            this.port = 22;
        }
    }
    
    public void open() throws AuthenticationException, CommandAbortedException {
        this.connection = new ch.ethz.ssh2.Connection(this.host, this.port);
        try {
            this.connection.connect();
        }
        catch (IOException e) {
            final String message = "Cannot connect. Reason: " + e.getMessage();
            throw new AuthenticationException(message, e, message);
        }
        final File privateKey = this.getPrivateKey();
        try {
            boolean authenticated;
            if (privateKey != null && privateKey.exists()) {
                authenticated = this.connection.authenticateWithPublicKey(this.userName, privateKey, this.getPassphrase());
            }
            else {
                authenticated = this.connection.authenticateWithPassword(this.userName, this.password);
            }
            if (!authenticated) {
                final String message2 = "Authentication failed.";
                throw new AuthenticationException(message2, message2);
            }
        }
        catch (IOException e2) {
            this.closeConnection();
            final String message2 = "Cannot authenticate. Reason: " + e2.getMessage();
            throw new AuthenticationException(message2, e2, message2);
        }
        try {
            this.session = this.connection.openSession();
        }
        catch (IOException e2) {
            final String message2 = "Cannot open session. Reason: " + e2.getMessage();
            throw new CommandAbortedException(message2, message2);
        }
        final String command = "cvs server";
        try {
            this.session.execCommand(command);
        }
        catch (IOException e3) {
            final String message3 = "Cannot execute remote command: " + command;
            throw new CommandAbortedException(message3, message3);
        }
        final InputStream stdout = new StreamGobbler(this.session.getStdout());
        final InputStream stderr = new StreamGobbler(this.session.getStderr());
        this.stderrReader = new BufferedReader(new InputStreamReader(stderr));
        this.setInputStream(new LoggedDataInputStream(stdout));
        this.setOutputStream(new LoggedDataOutputStream(this.session.getStdin()));
    }
    
    public void verify() throws AuthenticationException {
        try {
            this.open();
            this.verifyProtocol();
            this.close();
        }
        catch (Exception e) {
            final String message = "Failed to verify the connection: " + e.getMessage();
            throw new AuthenticationException(message, e, message);
        }
    }
    
    private void closeConnection() {
        try {
            if (this.stderrReader != null) {
                while (true) {
                    final String line = this.stderrReader.readLine();
                    if (line == null) {
                        break;
                    }
                    System.err.println(line);
                }
            }
        }
        catch (IOException ex) {}
        if (this.session != null) {
            System.out.println("Exit code:" + (int)this.session.getExitStatus());
            this.session.close();
        }
        if (this.connection != null) {
            this.connection.close();
        }
        this.reset();
    }
    
    private void reset() {
        this.connection = null;
        this.session = null;
        this.stderrReader = null;
        this.setInputStream(null);
        this.setOutputStream(null);
    }
    
    public void close() throws IOException {
        this.closeConnection();
    }
    
    public boolean isOpen() {
        return this.connection != null;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public void modifyInputStream(final ConnectionModifier modifier) throws IOException {
        modifier.modifyInputStream(this.getInputStream());
    }
    
    public void modifyOutputStream(final ConnectionModifier modifier) throws IOException {
        modifier.modifyOutputStream(this.getOutputStream());
    }
    
    private File getPrivateKey() {
        File privateKey = null;
        if (this.password == null) {
            final String pk = System.getProperty("maven.scm.cvs.java.ssh.privateKey");
            if (pk != null) {
                privateKey = new File(pk);
            }
            else {
                privateKey = this.findPrivateKey();
            }
        }
        return privateKey;
    }
    
    private String getPassphrase() {
        String passphrase = System.getProperty("maven.scm.cvs.java.ssh.passphrase");
        if (passphrase == null) {
            passphrase = "";
        }
        return passphrase;
    }
    
    private File findPrivateKey() {
        String privateKeyDirectory = System.getProperty("maven.scm.ssh.privateKeyDirectory");
        if (privateKeyDirectory == null) {
            privateKeyDirectory = System.getProperty("user.home");
        }
        File privateKey = new File(privateKeyDirectory, ".ssh/id_dsa");
        if (!privateKey.exists()) {
            privateKey = new File(privateKeyDirectory, ".ssh/id_rsa");
        }
        return privateKey;
    }
}
