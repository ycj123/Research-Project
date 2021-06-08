// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.update;

import java.util.Date;
import java.util.Arrays;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.ScmFileStatus;
import java.util.ArrayList;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import org.apache.maven.scm.provider.svn.svnexe.command.AbstractFileCheckingConsumer;

public class SvnUpdateConsumer extends AbstractFileCheckingConsumer
{
    private static final String UPDATED_TO_REVISION_TOKEN = "Updated to revision";
    private static final String AT_REVISION_TOKEN = "At revision";
    private static final String EXPORTED_REVISION_TOKEN = "Exported revision";
    private static final String RESTORED_TOKEN = "Restored";
    private List<ChangeSet> changeSets;
    
    public SvnUpdateConsumer(final ScmLogger logger, final File workingDirectory) {
        super(logger, workingDirectory);
        this.changeSets = new ArrayList<ChangeSet>();
    }
    
    @Override
    protected void parseLine(String line) {
        line = line.trim();
        final String statusString = line.substring(0, 1);
        String file = line.substring(3).trim();
        if (file.startsWith(this.workingDirectory.getAbsolutePath())) {
            if (file.length() == this.workingDirectory.getAbsolutePath().length()) {
                file = ".";
            }
            else {
                file = file.substring(this.workingDirectory.getAbsolutePath().length() + 1);
            }
        }
        if (line.startsWith("Updated to revision")) {
            final String revisionString = line.substring("Updated to revision".length() + 1, line.length() - 1);
            this.revision = this.parseInt(revisionString);
            return;
        }
        if (line.startsWith("At revision")) {
            final String revisionString = line.substring("At revision".length() + 1, line.length() - 1);
            this.revision = this.parseInt(revisionString);
            return;
        }
        if (line.startsWith("Exported revision")) {
            final String revisionString = line.substring("Exported revision".length() + 1, line.length() - 1);
            this.revision = this.parseInt(revisionString);
            return;
        }
        if (line.startsWith("Restored")) {
            return;
        }
        ScmFileStatus status;
        if (statusString.equals("A")) {
            status = ScmFileStatus.ADDED;
        }
        else if (statusString.equals("U") || statusString.equals("M")) {
            status = ScmFileStatus.UPDATED;
        }
        else {
            if (!statusString.equals("D")) {
                return;
            }
            status = ScmFileStatus.DELETED;
        }
        this.addFile(new ScmFile(file, status));
        final List<ChangeFile> changeFiles = Arrays.asList(new ChangeFile(line, Integer.valueOf(this.revision).toString()));
        final ChangeSet changeSet = new ChangeSet(null, null, null, changeFiles);
        this.changeSets.add(changeSet);
    }
    
    public List<ScmFile> getUpdatedFiles() {
        return this.getFiles();
    }
    
    public List<ChangeSet> getChangeSets() {
        return this.changeSets;
    }
    
    public void setChangeSets(final List<ChangeSet> changeSets) {
        this.changeSets = changeSets;
    }
}
