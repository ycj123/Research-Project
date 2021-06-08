// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

import java.io.IOException;
import org.netbeans.lib.cvsclient.connection.ConnectionModifier;
import org.netbeans.lib.cvsclient.connection.GzipModifier;
import org.netbeans.lib.cvsclient.connection.Connection;

public class GzipStreamRequest extends Request
{
    private int level;
    
    public GzipStreamRequest() {
        this.level = 6;
    }
    
    public GzipStreamRequest(final int level) {
        this.level = 6;
        this.level = level;
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        return "Gzip-stream " + this.level + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
    
    public void modifyOutputStream(final Connection connection) throws IOException {
        connection.modifyOutputStream(new GzipModifier());
    }
    
    public void modifyInputStream(final Connection connection) throws IOException {
        connection.modifyInputStream(new GzipModifier());
    }
    
    public boolean modifiesInputStream() {
        return true;
    }
}
