// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.remove;

import java.io.File;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;

public class RemoveInformation extends FileInfoContainer
{
    private File file;
    private boolean removed;
    
    public File getFile() {
        return this.file;
    }
    
    public void setFile(final File file) {
        this.file = file;
    }
    
    public void setRemoved(final boolean removed) {
        this.removed = removed;
    }
    
    public boolean isRemoved() {
        return this.removed;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(30);
        sb.append("  ");
        sb.append((this.file != null) ? this.file.getAbsolutePath() : "null");
        return sb.toString();
    }
}
