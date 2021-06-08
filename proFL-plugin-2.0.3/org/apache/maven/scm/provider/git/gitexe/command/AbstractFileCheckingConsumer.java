// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command;

import java.util.Iterator;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public abstract class AbstractFileCheckingConsumer implements StreamConsumer
{
    protected ScmLogger logger;
    protected File workingDirectory;
    private List<ScmFile> files;
    protected int revision;
    private boolean filtered;
    
    public AbstractFileCheckingConsumer(final ScmLogger logger, final File workingDirectory) {
        this.files = new ArrayList<ScmFile>();
        this.logger = logger;
        this.workingDirectory = workingDirectory;
    }
    
    public final void consumeLine(final String line) {
        if (line.length() <= 3) {
            return;
        }
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        this.parseLine(line);
    }
    
    protected abstract void parseLine(final String p0);
    
    protected List<ScmFile> getFiles() {
        if (!this.filtered) {
            final Iterator<ScmFile> it = this.files.iterator();
            while (it.hasNext()) {
                if (!new File(this.workingDirectory, it.next().getPath()).isFile()) {
                    it.remove();
                }
            }
            this.filtered = true;
        }
        return this.files;
    }
    
    protected final int parseInt(final String revisionString) {
        try {
            return Integer.parseInt(revisionString);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    protected void addFile(final ScmFile file) {
        this.files.add(file);
    }
    
    public final int getRevision() {
        return this.revision;
    }
}
