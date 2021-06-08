// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.connection;

import org.netbeans.lib.cvsclient.command.CommandAbortedException;
import java.io.IOException;
import java.io.OutputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import java.io.BufferedInputStream;

public class ExtConnection extends AbstractConnection
{
    private final String command;
    private Process process;
    
    public ExtConnection(final String command) {
        this.command = command;
    }
    
    public void open() throws AuthenticationException, CommandAbortedException {
        try {
            this.process = Runtime.getRuntime().exec(this.command);
            this.setInputStream(new LoggedDataInputStream(new BufferedInputStream(this.process.getInputStream())));
            this.setOutputStream(new LoggedDataOutputStream(new BufferedOutputStream(this.process.getOutputStream())));
        }
        catch (IOException ex) {
            throw new AuthenticationException(ex, "Failed to execute: " + this.command);
        }
    }
    
    public void verify() throws AuthenticationException {
        try {
            this.open();
            this.verifyProtocol();
            this.process.destroy();
        }
        catch (Exception ex) {
            throw new AuthenticationException(ex, "Failed to execute: " + this.command);
        }
    }
    
    public void close() throws IOException {
        if (this.isOpen()) {
            this.process.destroy();
        }
    }
    
    public boolean isOpen() {
        if (this.process == null) {
            return false;
        }
        try {
            this.process.exitValue();
            return false;
        }
        catch (IllegalThreadStateException ex) {
            return true;
        }
    }
    
    public int getPort() {
        return 0;
    }
    
    public void modifyInputStream(final ConnectionModifier connectionModifier) throws IOException {
        connectionModifier.modifyInputStream(this.getInputStream());
    }
    
    public void modifyOutputStream(final ConnectionModifier connectionModifier) throws IOException {
        connectionModifier.modifyOutputStream(this.getOutputStream());
    }
}
