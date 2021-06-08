// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

public class GzipFileContentsRequest extends Request
{
    private int compressionLevel;
    
    public GzipFileContentsRequest() {
        this(6);
    }
    
    public GzipFileContentsRequest(final int compressionLevel) {
        this.compressionLevel = compressionLevel;
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        return "gzip-file-contents " + this.compressionLevel + " \n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
