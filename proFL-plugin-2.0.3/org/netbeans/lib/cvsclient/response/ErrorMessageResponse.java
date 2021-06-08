// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.IOException;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.MessageEvent;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

public final class ErrorMessageResponse implements Response
{
    private boolean terminating;
    private String message;
    
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final String line = loggedDataInputStream.readLine();
            this.terminating |= line.endsWith(" [server aborted]: received termination signal");
            this.terminating |= line.endsWith(" [server aborted]: received broken pipe signal");
            this.terminating |= line.endsWith(" [checkout aborted]: end of file from server (consult above messages if any)");
            this.terminating &= (loggedDataInputStream.available() == 0);
            this.message = line;
            responseServices.getEventManager().fireCVSEvent(new MessageEvent(this, line, true));
        }
        catch (EOFException ex) {
            throw new ResponseException(ex, CommandException.getLocalMessage("CommandException.EndOfFile", null));
        }
        catch (IOException ex2) {
            throw new ResponseException(ex2);
        }
    }
    
    public boolean isTerminalResponse() {
        return this.terminating;
    }
    
    public String getMessage() {
        return this.message;
    }
}
