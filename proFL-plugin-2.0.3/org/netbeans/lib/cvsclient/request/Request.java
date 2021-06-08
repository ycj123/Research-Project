// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

import java.io.IOException;
import org.netbeans.lib.cvsclient.connection.Connection;
import org.netbeans.lib.cvsclient.file.FileDetails;

public abstract class Request
{
    public abstract String getRequestString() throws UnconfiguredRequestException;
    
    public abstract boolean isResponseExpected();
    
    public FileDetails getFileForTransmission() {
        return null;
    }
    
    public void modifyOutputStream(final Connection connection) throws IOException {
    }
    
    public void modifyInputStream(final Connection connection) throws IOException {
    }
    
    public boolean modifiesInputStream() {
        return false;
    }
}
