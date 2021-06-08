// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.IOException;
import java.io.EOFException;
import org.netbeans.lib.cvsclient.command.CommandException;
import java.io.File;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class CopyFileResponse implements Response
{
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final String line = loggedDataInputStream.readLine();
            final String line2 = loggedDataInputStream.readLine();
            final String line3 = loggedDataInputStream.readLine();
            final String convertPathname = responseServices.convertPathname(line, line2);
            if (responseServices.getGlobalOptions().isExcluded(new File(convertPathname))) {
                return;
            }
            if (!responseServices.getGlobalOptions().isDoNoChanges()) {
                responseServices.removeLocalFile(new File(new File(convertPathname).getParentFile(), line3).getAbsolutePath());
                responseServices.renameLocalFile(convertPathname, line3);
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
