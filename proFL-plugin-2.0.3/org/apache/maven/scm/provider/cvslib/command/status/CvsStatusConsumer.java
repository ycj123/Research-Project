// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.status;

import org.apache.maven.scm.ScmFileStatus;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class CvsStatusConsumer implements StreamConsumer
{
    private ScmLogger logger;
    private File workingDirectory;
    private List<ScmFile> changedFiles;
    
    public CvsStatusConsumer(final ScmLogger logger, final File workingDirectory) {
        this.changedFiles = new ArrayList<ScmFile>();
        this.logger = logger;
        this.workingDirectory = workingDirectory;
    }
    
    public void consumeLine(final String line) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(line);
        }
        if (line.length() < 3) {
            if (StringUtils.isNotEmpty(line) && this.logger.isWarnEnabled()) {
                this.logger.warn("Unable to parse output from command: line length must be bigger than 3. (" + line + ").");
            }
            return;
        }
        final String statusString = line.substring(0, 1);
        final String file = line.substring(2);
        ScmFileStatus status;
        if (statusString.equals("A")) {
            status = ScmFileStatus.ADDED;
        }
        else if (statusString.equals("M")) {
            status = ScmFileStatus.MODIFIED;
        }
        else if (statusString.equals("D")) {
            status = ScmFileStatus.DELETED;
        }
        else if (statusString.equals("C")) {
            status = ScmFileStatus.CONFLICT;
        }
        else if (statusString.equals("?")) {
            status = ScmFileStatus.UNKNOWN;
        }
        else {
            if (statusString.equals("U") || statusString.equals("P")) {
                return;
            }
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Unknown file status: '" + statusString + "'.");
            }
            return;
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
