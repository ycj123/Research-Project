// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command;

import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.File;

public class PipedFileInformation extends FileInfoContainer
{
    private File file;
    private String repositoryRevision;
    private String repositoryFileName;
    private File tempFile;
    private OutputStream tmpStream;
    
    public PipedFileInformation(final File file) {
        this.tempFile = file;
        try {
            this.tmpStream = new BufferedOutputStream(new FileOutputStream(file));
        }
        catch (IOException ex) {}
    }
    
    public File getFile() {
        return this.file;
    }
    
    protected void setFile(final File file) {
        this.file = file;
    }
    
    public String getRepositoryRevision() {
        return this.repositoryRevision;
    }
    
    protected void setRepositoryRevision(final String repositoryRevision) {
        this.repositoryRevision = repositoryRevision;
    }
    
    public String getRepositoryFileName() {
        return this.repositoryFileName;
    }
    
    protected void setRepositoryFileName(final String repositoryFileName) {
        this.repositoryFileName = repositoryFileName;
    }
    
    protected void addToTempFile(final byte[] b) throws IOException {
        if (this.tmpStream != null) {
            this.tmpStream.write(b);
        }
    }
    
    public void addToTempFile(final byte[] b, final int len) throws IOException {
        if (this.tmpStream != null) {
            this.tmpStream.write(b, 0, len);
        }
    }
    
    protected void closeTempFile() throws IOException {
        if (this.tmpStream != null) {
            this.tmpStream.flush();
            this.tmpStream.close();
        }
    }
    
    public File getTempFile() {
        return this.tempFile;
    }
}
