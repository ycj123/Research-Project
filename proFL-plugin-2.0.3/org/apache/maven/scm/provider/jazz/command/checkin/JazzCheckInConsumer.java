// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.checkin;

import java.io.File;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.jazz.command.consumer.AbstractRepositoryConsumer;

public class JazzCheckInConsumer extends AbstractRepositoryConsumer
{
    private boolean haveSeenChanges;
    protected String currentDir;
    private List<ScmFile> fCheckedInFiles;
    
    public JazzCheckInConsumer(final ScmProviderRepository repository, final ScmLogger logger) {
        super(repository, logger);
        this.haveSeenChanges = false;
        this.currentDir = "";
        this.fCheckedInFiles = new ArrayList<ScmFile>();
    }
    
    @Override
    public void consumeLine(final String line) {
        super.consumeLine(line);
        if (this.haveSeenChanges) {
            final String trimmed = line.trim();
            final int spacePos = trimmed.indexOf(" ");
            final String path = trimmed.substring(spacePos + 1 + 1);
            this.fCheckedInFiles.add(new ScmFile(path, ScmFileStatus.CHECKED_OUT));
        }
        else if ("Changes:".equals(line.trim())) {
            this.haveSeenChanges = true;
        }
    }
    
    protected ScmFile getScmFile(final String filename) {
        return new ScmFile(new File(this.currentDir, filename).getAbsolutePath(), ScmFileStatus.CHECKED_IN);
    }
    
    public List<ScmFile> getFiles() {
        return this.fCheckedInFiles;
    }
}
