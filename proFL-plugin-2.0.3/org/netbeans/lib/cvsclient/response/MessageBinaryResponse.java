// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.IOException;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.BinaryMessageEvent;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class MessageBinaryResponse implements Response
{
    private static final int CHUNK_SIZE = 262144;
    
    public MessageBinaryResponse() {
    }
    
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final String line = loggedDataInputStream.readLine();
            int i;
            try {
                i = Integer.parseInt(line);
            }
            catch (NumberFormatException ex) {
                throw new ResponseException(ex);
            }
            int n = Math.min(i, 262144);
            final byte[] array = new byte[n];
            while (i > 0) {
                final int read = loggedDataInputStream.read(array, 0, n);
                if (read == -1) {
                    throw new ResponseException("EOF", CommandException.getLocalMessage("CommandException.EndOfFile", null));
                }
                i -= read;
                n = Math.min(i, 262144);
                responseServices.getEventManager().fireCVSEvent(new BinaryMessageEvent(this, array, read));
            }
        }
        catch (EOFException ex2) {
            throw new ResponseException(ex2, CommandException.getLocalMessage("CommandException.EndOfFile", null));
        }
        catch (IOException ex3) {
            throw new ResponseException(ex3);
        }
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
}
