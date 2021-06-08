// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.IOException;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import org.netbeans.lib.cvsclient.event.CVSEvent;
import org.netbeans.lib.cvsclient.event.FileRemovedEvent;
import org.netbeans.lib.cvsclient.event.FileToRemoveEvent;
import java.io.File;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class RemovedResponse implements Response
{
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final String line = loggedDataInputStream.readLine();
            final String line2 = loggedDataInputStream.readLine();
            final String absolutePath = new File(responseServices.convertPathname(line, line2)).getAbsolutePath();
            if (responseServices.getGlobalOptions().isExcluded(new File(absolutePath))) {
                return;
            }
            final FileToRemoveEvent fileToRemoveEvent = new FileToRemoveEvent(this, absolutePath);
            final FileRemovedEvent fileRemovedEvent = new FileRemovedEvent(this, absolutePath);
            responseServices.getEventManager().fireCVSEvent(fileToRemoveEvent);
            responseServices.removeLocalFile(line, line2);
            responseServices.getEventManager().fireCVSEvent(fileRemovedEvent);
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
