// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.IOException;
import org.netbeans.lib.cvsclient.admin.Entry;
import java.io.File;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class NewEntryResponse implements Response
{
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final String line = loggedDataInputStream.readLine();
            final String line2 = loggedDataInputStream.readLine();
            final String line3 = loggedDataInputStream.readLine();
            final File file = new File(responseServices.convertPathname(line, line2));
            final Entry entry = new Entry(line3);
            entry.setConflict("dummy timestamp");
            responseServices.setEntry(file, entry);
        }
        catch (IOException ex) {
            throw new ResponseException((Exception)ex.fillInStackTrace(), ex.getLocalizedMessage());
        }
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
}
