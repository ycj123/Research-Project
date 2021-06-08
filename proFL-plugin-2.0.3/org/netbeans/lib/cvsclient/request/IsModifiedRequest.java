// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

import java.io.File;

public final class IsModifiedRequest extends Request
{
    private final String fileName;
    
    public IsModifiedRequest(final File file) {
        this.fileName = file.getName();
    }
    
    public String getRequestString() {
        return "Is-modified " + this.fileName + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
}
