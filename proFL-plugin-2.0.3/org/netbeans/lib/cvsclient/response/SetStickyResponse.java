// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class SetStickyResponse implements Response
{
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        PrintWriter printWriter = null;
        try {
            final String line = loggedDataInputStream.readLine();
            final String line2 = loggedDataInputStream.readLine();
            final String line3 = loggedDataInputStream.readLine();
            final String convertPathname = responseServices.convertPathname(line, line2);
            if (responseServices.getGlobalOptions().isExcluded(new File(convertPathname))) {
                return;
            }
            final String string = convertPathname + "/CVS";
            if (new File(string).exists()) {
                final File file = new File(string, "Tag");
                if ("THEAD".equals(line3) | "NHEAD".equals(line3)) {
                    file.delete();
                }
                else {
                    printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                    printWriter.println(line3);
                }
            }
        }
        catch (IOException ex) {
            throw new ResponseException(ex);
        }
        finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
}
