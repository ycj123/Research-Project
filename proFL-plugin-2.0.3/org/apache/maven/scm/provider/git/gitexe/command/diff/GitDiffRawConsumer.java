// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.diff;

import org.apache.maven.scm.ScmFileStatus;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class GitDiffRawConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private List<ScmFile> changedFiles;
    
    public GitDiffRawConsumer(final ScmLogger logger) {
        this.changedFiles = new ArrayList<ScmFile>();
        this.logger = logger;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        if (StringUtils.isEmpty(line)) {
            return;
        }
        ScmFileStatus status = null;
        final String[] parts = line.split("\\s", 6);
        if (parts.length != 6) {
            this.logger.warn("Skipping line because it doesn't contain the right status parameters: " + line);
            return;
        }
        final String modus = parts[4];
        final String file = parts[5];
        if ("A".equals(modus)) {
            status = ScmFileStatus.ADDED;
        }
        else if ("M".equals(modus)) {
            status = ScmFileStatus.UPDATED;
        }
        else {
            if (!"D".equals(modus)) {
                this.logger.warn("unknown status detected in line: " + line);
                return;
            }
            status = ScmFileStatus.DELETED;
        }
        this.changedFiles.add(new ScmFile(file, status));
    }
    
    public List<ScmFile> getChangedFiles() {
        return this.changedFiles;
    }
}
