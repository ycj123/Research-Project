// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.checkout;

import java.util.Iterator;
import org.apache.maven.scm.ScmFileStatus;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import java.util.ArrayList;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.svn.svnexe.command.AbstractFileCheckingConsumer;

public class SvnCheckOutConsumer extends AbstractFileCheckingConsumer
{
    private static final String CHECKED_OUT_REVISION_TOKEN = "Checked out revision";
    private List<ScmFile> files;
    
    public SvnCheckOutConsumer(final ScmLogger logger, final File workingDirectory) {
        super(logger, workingDirectory);
        this.files = new ArrayList<ScmFile>();
    }
    
    @Override
    protected void parseLine(final String line) {
        final String statusString = line.substring(0, 1);
        String file = line.substring(3).trim();
        if (file.startsWith(this.getWorkingDirectory().getAbsolutePath())) {
            file = StringUtils.substring(file, this.getWorkingDirectory().getAbsolutePath().length() + 1);
        }
        if (line.startsWith("Checked out revision")) {
            final String revisionString = line.substring("Checked out revision".length() + 1, line.length() - 1);
            this.revision = this.parseInt(revisionString);
            return;
        }
        ScmFileStatus status;
        if (statusString.equals("A")) {
            status = ScmFileStatus.ADDED;
        }
        else {
            if (!statusString.equals("U")) {
                return;
            }
            status = ScmFileStatus.UPDATED;
        }
        this.addFile(new ScmFile(file, status));
    }
    
    public List<ScmFile> getCheckedOutFiles() {
        return this.getFiles();
    }
    
    @Override
    protected void addFile(final ScmFile file) {
        this.files.add(file);
    }
    
    @Override
    protected List<ScmFile> getFiles() {
        final List<ScmFile> onlyFiles = new ArrayList<ScmFile>();
        for (final ScmFile file : this.files) {
            if (file.getStatus().equals(ScmFileStatus.DELETED) || (new File(this.getWorkingDirectory(), file.getPath()).isFile() || file.getStatus().equals(ScmFileStatus.DELETED)) || new File(this.getWorkingDirectory().getParent(), file.getPath()).isFile()) {
                onlyFiles.add(file);
            }
        }
        return onlyFiles;
    }
}
