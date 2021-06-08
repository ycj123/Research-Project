// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.File;

public class FileDetails
{
    private File file;
    private boolean isBinary;
    
    public FileDetails(final File file, final boolean isBinary) {
        this.file = file;
        this.isBinary = isBinary;
    }
    
    public File getFile() {
        return this.file;
    }
    
    public boolean isBinary() {
        return this.isBinary;
    }
}
