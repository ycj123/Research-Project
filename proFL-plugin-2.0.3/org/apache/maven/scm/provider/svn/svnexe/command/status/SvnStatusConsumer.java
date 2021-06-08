// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.status;

import org.apache.maven.scm.ScmFileStatus;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class SvnStatusConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private File workingDirectory;
    private List<ScmFile> changedFiles;
    
    public SvnStatusConsumer(final ScmLogger logger, final File workingDirectory) {
        this.changedFiles = new ArrayList<ScmFile>();
        this.logger = logger;
        this.workingDirectory = workingDirectory;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        if (StringUtils.isEmpty(line.trim())) {
            return;
        }
        if (line.length() <= 7) {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("Unexpected input, the line must be at least seven characters long. Line: '" + line + "'.");
            }
            return;
        }
        String statusString = line.substring(0, 1);
        final String file = line.substring(7).trim();
        ScmFileStatus status;
        if (statusString.equals("A")) {
            status = ScmFileStatus.ADDED;
        }
        else if (statusString.equals("M") || statusString.equals("R") || statusString.equals("~")) {
            status = ScmFileStatus.MODIFIED;
        }
        else if (statusString.equals("D")) {
            status = ScmFileStatus.DELETED;
        }
        else if (statusString.equals("?")) {
            status = ScmFileStatus.UNKNOWN;
        }
        else if (statusString.equals("!")) {
            status = ScmFileStatus.MISSING;
        }
        else if (statusString.equals("C")) {
            status = ScmFileStatus.CONFLICT;
        }
        else if (statusString.equals("L")) {
            status = ScmFileStatus.LOCKED;
        }
        else {
            if (statusString.equals("X")) {
                return;
            }
            if (statusString.equals("I")) {
                return;
            }
            statusString = line.substring(1, 1);
            if (statusString.equals("M")) {
                status = ScmFileStatus.MODIFIED;
            }
            else {
                if (!statusString.equals("C")) {
                    return;
                }
                status = ScmFileStatus.CONFLICT;
            }
        }
        if (!status.equals(ScmFileStatus.DELETED) && !new File(this.workingDirectory, file).isFile()) {
            return;
        }
        this.changedFiles.add(new ScmFile(file, status));
    }
    
    public List<ScmFile> getChangedFiles() {
        return this.changedFiles;
    }
}
