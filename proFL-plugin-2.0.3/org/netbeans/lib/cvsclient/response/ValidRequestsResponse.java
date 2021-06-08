// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.IOException;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class ValidRequestsResponse implements Response
{
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final String line = loggedDataInputStream.readLine();
            responseServices.setValidRequests(line);
            if (line.indexOf("gzip-file-contents") < 0) {
                responseServices.dontUseGzipFileHandler();
            }
        }
        catch (EOFException ex) {
            throw new ResponseException(ex, CommandException.getLocalMessage("CommandException.EndOfFile", null));
        }
        catch (IOException ex2) {
            throw new ResponseException(ex2);
        }
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
}
