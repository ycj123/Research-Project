// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.response;

import java.io.OutputStream;
import org.netbeans.lib.cvsclient.command.CommandException;
import java.io.IOException;
import java.io.EOFException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

class TemplateResponse implements Response
{
    protected String localPath;
    protected String repositoryPath;
    
    public TemplateResponse() {
    }
    
    public void process(final LoggedDataInputStream loggedDataInputStream, final ResponseServices responseServices) throws ResponseException {
        try {
            this.localPath = loggedDataInputStream.readLine();
            this.repositoryPath = loggedDataInputStream.readLine();
            final int int1 = Integer.parseInt(loggedDataInputStream.readLine());
            final String string = responseServices.convertPathname(this.localPath, this.repositoryPath) + "CVS/Template";
            OutputStream out = null;
            final File file = new File(string);
            file.getParentFile().mkdirs();
            try {
                out = new FileOutputStream(file);
                out = new BufferedOutputStream(out);
                final byte[] bytes = System.getProperty("line.separator").getBytes();
                final byte[] bytes2 = loggedDataInputStream.readBytes(int1);
                for (int i = 0; i < bytes2.length; ++i) {
                    final byte b = bytes2[i];
                    if (b == 10) {
                        out.write(bytes);
                    }
                    else {
                        out.write(b);
                    }
                }
            }
            catch (EOFException ex3) {}
            finally {
                if (out != null) {
                    try {
                        out.close();
                    }
                    catch (IOException ex4) {}
                }
            }
        }
        catch (EOFException ex) {
            throw new ResponseException(ex, CommandException.getLocalMessage("CommandException.EndOfFile"));
        }
        catch (IOException ex2) {
            throw new ResponseException(ex2);
        }
    }
    
    public boolean isTerminalResponse() {
        return false;
    }
}
