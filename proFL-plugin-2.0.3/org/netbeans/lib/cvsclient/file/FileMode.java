// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.file;

import java.io.File;

public class FileMode
{
    private File file;
    
    public FileMode(final File file) {
        this.file = file;
    }
    
    public String toString() {
        return "u=rw,g=r,o=r";
    }
}
