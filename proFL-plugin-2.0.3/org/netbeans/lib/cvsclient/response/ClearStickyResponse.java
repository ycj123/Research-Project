// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.IOException;
import org.netbeans.lib.cvsclient.admin.Entry;
import java.io.File;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class ClearStickyResponse implements Response
{
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            final String line = loggedDataInputStream.readLine();
            final String line2 = loggedDataInputStream.readLine();
            final String convertPathname = responseServices.convertPathname(line, line2);
            if (responseServices.getGlobalOptions().isExcluded(new File(convertPathname))) {
                return;
            }
            responseServices.updateAdminData(line, line2, null);
            final File file = new File(convertPathname, "CVS/Tag");
            if (file.exists()) {
                file.delete();
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
