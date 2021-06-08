// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import java.io.File;

public class DefaultFileInfoContainer extends FileInfoContainer
{
    public static final String PERTINENT_STATE = "Y";
    public static final String MERGED_FILE = "G";
    private File file;
    private String type;
    
    public File getFile() {
        return this.file;
    }
    
    public boolean isDirectory() {
        final File file = this.getFile();
        return file != null && file.isDirectory();
    }
    
    public void setFile(final File file) {
        this.file = file;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.type);
        sb.append("  ");
        if (this.isDirectory()) {
            sb.append("Directory ");
        }
        sb.append((this.file != null) ? this.file.getAbsolutePath() : "null");
        return sb.toString();
    }
}
