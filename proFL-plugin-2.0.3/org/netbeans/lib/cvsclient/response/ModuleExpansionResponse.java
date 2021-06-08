// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.IOException;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.ModuleExpansionEvent;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class ModuleExpansionResponse implements Response
{
    public ModuleExpansionResponse() {
    }
    
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            responseServices.getEventManager().fireCVSEvent(new ModuleExpansionEvent(this, loggedDataInputStream.readLine()));
        }
        catch (IOException ex) {
            throw new ResponseException(ex);
        }
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
}
