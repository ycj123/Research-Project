// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import org.netbeans.lib.cvsclient.util.ByteArray;
import java.io.IOException;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.MessageEvent;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class MessageResponse implements Response
{
    private boolean terminating;
    private String firstWord;
    
    public MessageResponse() {
        this.terminating = false;
    }
    
    public MessageResponse(final String firstWord) {
        this.terminating = false;
        this.firstWord = firstWord;
    }
    
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final ByteArray lineBytes = loggedDataInputStream.readLineBytes();
            String str = lineBytes.getStringFromBytes();
            if (this.firstWord != null) {
                str = this.firstWord + " " + str;
            }
            this.terminating |= str.endsWith(" [server aborted]: received termination signal");
            this.terminating |= str.endsWith(" [server aborted]: received broken pipe signal");
            this.terminating |= str.endsWith(" [checkout aborted]: end of file from server (consult above messages if any)");
            this.terminating &= (loggedDataInputStream.available() == 0);
            responseServices.getEventManager().fireCVSEvent(new MessageEvent(this, str, lineBytes.getBytes(), false));
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
}
