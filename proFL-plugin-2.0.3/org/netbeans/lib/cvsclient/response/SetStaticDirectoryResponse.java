// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.IOException;
import java.io.File;
import org.netbeans.lib.cvsclient.admin.Entry;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class SetStaticDirectoryResponse implements Response
{
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final String line = loggedDataInputStream.readLine();
            final String line2 = loggedDataInputStream.readLine();
            responseServices.updateAdminData(line, line2, null);
            final String convertPathname = responseServices.convertPathname(line, line2);
            if (responseServices.getGlobalOptions().isExcluded(new File(convertPathname))) {
                return;
            }
            final String string = convertPathname + "/CVS";
            if (new File(string).exists()) {
                new File(string, "Entries.Static").createNewFile();
            }
        }
        catch (IOException ex) {
            throw new ResponseException(ex);
        }
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
}
