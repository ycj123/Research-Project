// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.TerminationEvent;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class OKResponse implements Response
{
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        responseServices.getEventManager().fireCVSEvent(new TerminationEvent(this, false));
    }
    
    public boolean isTerminalResponse() {
        return true;
    }
}
