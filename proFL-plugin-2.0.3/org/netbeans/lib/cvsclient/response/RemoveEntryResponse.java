// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.File;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class RemoveEntryResponse implements Response
{
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final File file = new File(responseServices.convertPathname(loggedDataInputStream.readLine(), loggedDataInputStream.readLine()));
            if (responseServices.getGlobalOptions().isExcluded(file)) {
                return;
            }
            responseServices.removeEntry(file);
        }
        catch (Exception ex) {
            throw new ResponseException(ex);
        }
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
}
