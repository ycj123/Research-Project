// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.connection;

import java.text.MessageFormat;
import java.io.IOException;
import java.net.NoRouteToHostException;
import java.net.ConnectException;
import org.netbeans.lib.cvsclient.command.CommandAbortedException;
import org.netbeans.lib.cvsclient.command.CommandException;
import java.io.InputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;
import java.io.BufferedOutputStream;
import org.netbeans.lib.cvsclient.CVSRoot;
import javax.net.SocketFactory;
import java.net.Socket;

public class PServerConnection extends AbstractConnection
{
    protected static final String OPEN_PREAMBLE = "BEGIN AUTH REQUEST\n";
    protected static final String OPEN_POSTAMBLE = "END AUTH REQUEST\n";
    protected static final String VERIFY_PREAMBLE = "BEGIN VERIFICATION REQUEST\n";
    protected static final String VERIFY_POSTAMBLE = "END VERIFICATION REQUEST\n";
    protected static final String AUTHENTICATION_SUCCEEDED_RESPONSE = "I LOVE YOU";
    private static final String AUTHENTICATION_SUCCEEDED_RESPONSE_RAW = "I LOVE YOU\n";
    protected static final String AUTHENTICATION_FAILED_RESPONSE = "I HATE YOU";
    private static final String AUTHENTICATION_FAILED_RESPONSE_RAW = "I HATE YOU\n";
    protected String userName;
    protected String encodedPassword;
    public static final int DEFAULT_PORT = 2401;
    protected int port;
    protected String hostName;
    protected Socket socket;
    protected SocketFactory socketFactory;
    
    public PServerConnection() {
        this.port = 2401;
    }
    
    public PServerConnection(final CVSRoot cvsRoot) {
        this(cvsRoot, null);
    }
    
    public PServerConnection(final CVSRoot obj, final SocketFactory socketFactory) {
        this.port = 2401;
        if (!"pserver".equals(obj.getMethod())) {
            throw new IllegalArgumentException("CVS Root '" + obj + "' does not represent :pserver: connection type.");
        }
        this.socketFactory = ((socketFactory != null) ? socketFactory : SocketFactory.getDefault());
        String userName = obj.getUserName();
        if (userName == null) {
            userName = System.getProperty("user.name");
        }
        this.setUserName(userName);
        final String password = obj.getPassword();
        if (password != null) {
            this.setEncodedPassword(StandardScrambler.getInstance().scramble(password));
        }
        this.setHostName(obj.getHostName());
        this.setRepository(obj.getRepository());
        int port = obj.getPort();
        if (port == 0) {
            port = 2401;
        }
        this.setPort(port);
    }
    
    private void openConnection(final String s, final String s2) throws AuthenticationException, CommandAbortedException {
        if (this.hostName == null) {
            throw new AuthenticationException("HostIsNull", AuthenticationException.getBundleString("AuthenticationException.HostIsNull"));
        }
        try {
            this.socket = this.socketFactory.createSocket(this.hostName, this.port);
            final LoggedDataOutputStream outputStream = new LoggedDataOutputStream(new BufferedOutputStream(this.socket.getOutputStream(), 32768));
            this.setOutputStream(outputStream);
            final LoggedDataInputStream inputStream = new LoggedDataInputStream(new BufferedInputStream(this.socket.getInputStream(), 32768));
            this.setInputStream(inputStream);
            outputStream.writeBytes(s, "US-ASCII");
            outputStream.writeBytes(this.getRepository() + "\n");
            outputStream.writeBytes(this.userName + "\n");
            outputStream.writeBytes(this.getEncodedPasswordNotNull() + "\n", "US-ASCII");
            outputStream.writeBytes(s2, "US-ASCII");
            outputStream.flush();
            if (Thread.interrupted()) {
                this.reset();
                throw new CommandAbortedException("Aborted during connecting to the server.", CommandException.getLocalMessage("Client.connectionAborted", null));
            }
            String s3 = new String(inputStream.readBytes("I LOVE YOU\n".length()), "utf8");
            if (Thread.interrupted()) {
                this.reset();
                throw new CommandAbortedException("Aborted during connecting to the server.", CommandException.getLocalMessage("Client.connectionAborted", null));
            }
            if ("I LOVE YOU\n".equals(s3)) {
                return;
            }
            if ("I HATE YOU\n".equals(s3)) {
                throw new AuthenticationException("AuthenticationFailed", this.getLocalMessage("AuthenticationException.badPassword", null));
            }
            if (s3 == null) {
                s3 = "";
            }
            throw new AuthenticationException("AuthenticationFailed", this.getLocalMessage("AuthenticationException.AuthenticationFailed", new Object[] { s3 }));
        }
        catch (AuthenticationException ex) {
            this.reset();
            throw ex;
        }
        catch (ConnectException ex2) {
            this.reset();
            throw new AuthenticationException("ConnectException", ex2, this.getLocalMessage("AuthenticationException.ConnectException", new Object[] { this.hostName, Integer.toString(this.port) }));
        }
        catch (NoRouteToHostException ex3) {
            this.reset();
            throw new AuthenticationException("NoRouteToHostException", ex3, this.getLocalMessage("AuthenticationException.NoRouteToHostException", new Object[] { this.hostName }));
        }
        catch (IOException ex4) {
            this.reset();
            throw new AuthenticationException("IOException", ex4, this.getLocalMessage("AuthenticationException.IOException", new Object[] { this.hostName }));
        }
    }
    
    private void reset() {
        this.socket = null;
        this.setInputStream(null);
        this.setOutputStream(null);
    }
    
    public void verify() throws AuthenticationException {
        try {
            this.openConnection("BEGIN VERIFICATION REQUEST\n", "END VERIFICATION REQUEST\n");
        }
        catch (CommandAbortedException ex2) {}
        if (this.socket == null) {
            return;
        }
        try {
            this.socket.close();
        }
        catch (IOException ex) {
            throw new AuthenticationException("General error", ex, AuthenticationException.getBundleString("AuthenticationException.Throwable"));
        }
        finally {
            this.reset();
        }
    }
    
    public void open() throws AuthenticationException, CommandAbortedException {
        this.openConnection("BEGIN AUTH REQUEST\n", "END AUTH REQUEST\n");
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public String getEncodedPassword() {
        return this.encodedPassword;
    }
    
    private String getEncodedPasswordNotNull() {
        if (this.encodedPassword == null) {
            return StandardScrambler.getInstance().scramble("");
        }
        return this.encodedPassword;
    }
    
    public void setEncodedPassword(final String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public void setPort(final int port) {
        this.port = port;
    }
    
    public String getHostName() {
        return this.hostName;
    }
    
    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }
    
    public void close() throws IOException {
        if (!this.isOpen()) {
            return;
        }
        try {
            this.socket.close();
        }
        finally {
            this.reset();
        }
    }
    
    public void modifyInputStream(final ConnectionModifier connectionModifier) throws IOException {
        connectionModifier.modifyInputStream(this.getInputStream());
    }
    
    public void modifyOutputStream(final ConnectionModifier connectionModifier) throws IOException {
        connectionModifier.modifyOutputStream(this.getOutputStream());
    }
    
    private String getLocalMessage(final String s, final Object[] arguments) {
        final String bundleString = AuthenticationException.getBundleString(s);
        if (bundleString == null) {
            return null;
        }
        return MessageFormat.format(bundleString, arguments);
    }
    
    public boolean isOpen() {
        return this.socket != null;
    }
}
