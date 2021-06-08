// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import org.netbeans.lib.cvsclient.command.GlobalOptions;
import org.netbeans.lib.cvsclient.request.Request;
import java.util.Date;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;
import java.io.IOException;
import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;
import java.io.File;

public interface FileHandler
{
    void transmitTextFile(final File p0, final LoggedDataOutputStream p1) throws IOException;
    
    void transmitBinaryFile(final File p0, final LoggedDataOutputStream p1) throws IOException;
    
    void writeTextFile(final String p0, final String p1, final LoggedDataInputStream p2, final int p3) throws IOException;
    
    void writeRcsDiffFile(final String p0, final String p1, final LoggedDataInputStream p2, final int p3) throws IOException;
    
    void writeBinaryFile(final String p0, final String p1, final LoggedDataInputStream p2, final int p3) throws IOException;
    
    void removeLocalFile(final String p0) throws IOException;
    
    void renameLocalFile(final String p0, final String p1) throws IOException;
    
    void setNextFileDate(final Date p0);
    
    Request[] getInitialisationRequests();
    
    void setGlobalOptions(final GlobalOptions p0);
}
