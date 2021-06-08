// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

import java.io.File;

public class UnchangedRequest extends Request
{
    private String filename;
    
    public UnchangedRequest(final String filename) {
        this.filename = filename;
    }
    
    public UnchangedRequest(final File file) {
        this.filename = file.getName();
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        if (this.filename == null) {
            throw new UnconfiguredRequestException("Filename must be set");
        }
        return "Unchanged " + this.filename + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
