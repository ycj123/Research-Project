// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.checkin;

import org.apache.maven.scm.ScmResult;
import java.util.Iterator;
import org.apache.maven.scm.command.status.StatusScmResult;
import java.util.List;
import org.apache.maven.scm.provider.hg.repository.HgScmProviderRepository;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import java.io.File;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.hg.command.status.HgStatusCommand;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.provider.hg.HgUtils;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class HgCheckInCommand extends AbstractCheckInCommand
{
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final ScmVersion tag) throws ScmException {
        if (tag != null && !StringUtils.isEmpty(tag.getName())) {
            throw new ScmException("This provider can't handle tags for this operation");
        }
        final File workingDir = fileSet.getBasedir();
        final String branchName = HgUtils.getCurrentBranchName(this.getLogger(), workingDir);
        final boolean differentOutgoingBranch = repo.isPushChanges() && HgUtils.differentOutgoingBranchFound(this.getLogger(), workingDir, branchName);
        final List<ScmFile> commitedFiles = new ArrayList<ScmFile>();
        final List<File> files = fileSet.getFileList();
        if (files.isEmpty()) {
            final HgStatusCommand statusCmd = new HgStatusCommand();
            statusCmd.setLogger(this.getLogger());
            final StatusScmResult status = statusCmd.executeStatusCommand(repo, fileSet);
            final List<ScmFile> statusFiles = status.getChangedFiles();
            for (final ScmFile file : statusFiles) {
                if (file.getStatus() == ScmFileStatus.ADDED || file.getStatus() == ScmFileStatus.DELETED || file.getStatus() == ScmFileStatus.MODIFIED) {
                    commitedFiles.add(new ScmFile(file.getPath(), ScmFileStatus.CHECKED_IN));
                }
            }
        }
        else {
            for (final File file2 : files) {
                commitedFiles.add(new ScmFile(file2.getPath(), ScmFileStatus.CHECKED_IN));
            }
        }
        String[] commitCmd = { "commit", "--message", message };
        commitCmd = HgUtils.expandCommandLine(commitCmd, fileSet);
        ScmResult result = HgUtils.execute(new HgConsumer(this.getLogger()), this.getLogger(), fileSet.getBasedir(), commitCmd);
        final HgScmProviderRepository repository = (HgScmProviderRepository)repo;
        if (repo.isPushChanges()) {
            if (!repository.getURI().equals(fileSet.getBasedir().getAbsolutePath())) {
                final String[] pushCmd = { "push", differentOutgoingBranch ? ("-r" + branchName) : null, repository.getURI() };
                result = HgUtils.execute(new HgConsumer(this.getLogger()), this.getLogger(), fileSet.getBasedir(), pushCmd);
            }
            return new CheckInScmResult(commitedFiles, result);
        }
        return new CheckInScmResult(commitedFiles, result);
    }
}
