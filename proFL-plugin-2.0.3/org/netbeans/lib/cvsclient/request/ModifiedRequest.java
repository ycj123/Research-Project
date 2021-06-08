// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.request;

import org.netbeans.lib.cvsclient.file.FileMode;
import java.io.File;
import org.netbeans.lib.cvsclient.file.FileDetails;

public class ModifiedRequest extends Request
{
    private FileDetails fileDetails;
    
    public ModifiedRequest(final File file, final boolean b) {
        this.fileDetails = new FileDetails(file, b);
    }
    
    public String getRequestString() throws UnconfiguredRequestException {
        if (this.fileDetails == null) {
            throw new UnconfiguredRequestException("FileDetails is null in ModifiedRequest");
        }
        return "Modified " + this.fileDetails.getFile().getName() + "\n" + new FileMode(this.fileDetails.getFile()).toString() + "\n";
    }
    
    public boolean isResponseExpected() {
        return false;
    }
    
    public FileDetails getFileForTransmission() {
        return this.fileDetails;
    }
}
