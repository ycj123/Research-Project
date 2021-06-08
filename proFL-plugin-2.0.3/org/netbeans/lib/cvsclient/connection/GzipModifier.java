// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.connection;

import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.netbeans.lib.cvsclient.util.LoggedDataInputStream;

public class GzipModifier implements ConnectionModifier
{
    public void modifyInputStream(final LoggedDataInputStream loggedDataInputStream) throws IOException {
        loggedDataInputStream.setUnderlyingStream(new GZIPInputStream(loggedDataInputStream.getUnderlyingStream()));
    }
    
    public void modifyOutputStream(final LoggedDataOutputStream loggedDataOutputStream) throws IOException {
        loggedDataOutputStream.setUnderlyingStream(new GZIPOutputStream(loggedDataOutputStream.getUnderlyingStream()));
    }
}
