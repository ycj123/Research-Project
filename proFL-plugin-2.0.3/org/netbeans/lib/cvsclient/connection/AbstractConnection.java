// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.connection;

import org.netbeans.lib.cvsclient.request.UnconfiguredRequestException;
import org.netbeans.lib.cvsclient.request.ValidRequestsRequest;
import org.netbeans.lib.cvsclient.request.UseUnchangedRequest;
import org.netbeans.lib.cvsclient.request.RootRequest;
import java.io.IOException;
import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

public abstract class AbstractConnection implements Connection
{
    private String repository;
    private LoggedDataInputStream inputStream;
    private LoggedDataOutputStream outputStream;
    
    public AbstractConnection() {
        this.repository = null;
    }
    
    public LoggedDataInputStream getInputStream() {
        return this.inputStream;
    }
    
    protected final void setInputStream(final LoggedDataInputStream inputStream) {
        if (this.inputStream == inputStream) {
            return;
        }
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            }
            catch (IOException ex) {}
        }
        this.inputStream = inputStream;
    }
    
    public LoggedDataOutputStream getOutputStream() {
        return this.outputStream;
    }
    
    protected final void setOutputStream(final LoggedDataOutputStream outputStream) {
        if (this.outputStream == outputStream) {
            return;
        }
        if (this.outputStream != null) {
            try {
                this.outputStream.close();
            }
            catch (IOException ex) {}
        }
        this.outputStream = outputStream;
    }
    
    public String getRepository() {
        return this.repository;
    }
    
    public void setRepository(final String repository) {
        this.repository = repository;
    }
    
    protected void verifyProtocol() throws IOException {
        try {
            this.outputStream.writeBytes(new RootRequest(this.repository).getRequestString(), "US-ASCII");
            this.outputStream.writeBytes(new UseUnchangedRequest().getRequestString(), "US-ASCII");
            this.outputStream.writeBytes(new ValidRequestsRequest().getRequestString(), "US-ASCII");
            this.outputStream.writeBytes("noop \n", "US-ASCII");
        }
        catch (UnconfiguredRequestException ex) {
            throw new RuntimeException("Internal error verifying CVS protocol: " + ex.getMessage());
        }
        this.outputStream.flush();
        final StringBuffer sb = new StringBuffer();
        int read;
        while ((read = this.inputStream.read()) != -1) {
            sb.append((char)read);
            if (read == 10) {
                break;
            }
        }
        final String string = sb.toString();
        if (!string.startsWith("Valid-requests")) {
            throw new IOException("Unexpected server response: " + string);
        }
    }
}
