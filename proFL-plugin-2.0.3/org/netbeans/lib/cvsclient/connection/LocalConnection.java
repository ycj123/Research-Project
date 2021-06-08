// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.connection;

import java.io.IOException;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;

public class LocalConnection extends AbstractConnection
{
    private static final String CVS_EXE_COMMAND;
    protected Process process;
    
    public LocalConnection() {
        this.reset();
    }
    
    private void openConnection() throws AuthenticationException {
        try {
            this.process = Runtime.getRuntime().exec(LocalConnection.CVS_EXE_COMMAND);
            this.setOutputStream(new LoggedDataOutputStream(this.process.getOutputStream()));
            this.setInputStream(new LoggedDataInputStream(this.process.getInputStream()));
        }
        catch (IOException ex) {
            this.reset();
            throw new AuthenticationException("Connection error", ex, AuthenticationException.getBundleString("AuthenticationException.ServerConnection"));
        }
    }
    
    private void reset() {
        this.process = null;
        this.setInputStream(null);
        this.setOutputStream(null);
    }
    
    public void verify() throws AuthenticationException {
        try {
            this.openConnection();
            this.verifyProtocol();
            this.process.destroy();
        }
        catch (Exception ex) {
            throw new AuthenticationException("Verification error", ex, AuthenticationException.getBundleString("AuthenticationException.ServerVerification"));
        }
        finally {
            this.reset();
        }
    }
    
    public void open() throws AuthenticationException {
        this.openConnection();
    }
    
    public boolean isOpen() {
        return this.process != null;
    }
    
    public void close() throws IOException {
        try {
            if (this.process != null) {
                this.process.destroy();
            }
        }
        finally {
            this.reset();
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
    
    static {
        CVS_EXE_COMMAND = System.getProperty("Env-CVS_EXE", "cvs") + " server";
    }
}
