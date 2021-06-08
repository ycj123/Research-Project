// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.File;
import org.netbeans.lib.cvsclient.request.GzipStreamRequest;
import org.netbeans.lib.cvsclient.request.Request;

public class GzippedFileHandler extends DefaultFileHandler
{
    private boolean isCompressed;
    
    public Request[] getInitialisationRequests() {
        return new Request[] { new GzipStreamRequest() };
    }
    
    protected Reader getProcessedReader(final File file) throws IOException {
        return new InputStreamReader(new GZIPInputStream(new FileInputStream(file)));
    }
    
    protected InputStream getProcessedInputStream(final File file) throws IOException {
        return new GZIPInputStream(new FileInputStream(file));
    }
}
