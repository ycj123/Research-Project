// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command.consumer;

import java.io.File;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class FileListConsumer implements StreamConsumer
{
    private boolean fed;
    protected String currentDir;
    private List<ScmFile> files;
    
    public FileListConsumer() {
        this.fed = false;
        this.currentDir = "";
        this.files = new ArrayList<ScmFile>();
    }
    
    public void consumeLine(final String line) {
        this.fed = true;
        if (line.endsWith(":")) {
            this.currentDir = line.substring(0, line.lastIndexOf(58));
            final ScmFile scmFile = new ScmFile(this.currentDir, ScmFileStatus.CHECKED_OUT);
            if (!this.files.contains(scmFile)) {
                this.files.add(scmFile);
            }
        }
        else if (line.trim().equals("")) {
            this.currentDir = "";
        }
        else if (!this.currentDir.equals("") && line.indexOf(32) >= 0) {
            final String filename = line.split(" ")[1];
            this.files.add(this.getScmFile(filename));
        }
        else {
            this.files.add(this.getScmFile(line));
        }
    }
    
    protected ScmFile getScmFile(final String filename) {
        return new ScmFile(new File(this.currentDir, filename).getAbsolutePath(), ScmFileStatus.CHECKED_OUT);
    }
    
    public List<ScmFile> getFiles() {
        return this.files;
    }
    
    public boolean hasBeenFed() {
        return this.fed;
    }
}
