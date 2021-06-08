// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.checkin;

import org.apache.maven.scm.ScmResult;
import java.util.Iterator;
import org.apache.maven.scm.command.status.StatusScmResult;
import java.util.List;
import org.apache.maven.scm.provider.bazaar.repository.BazaarScmProviderRepository;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import org.apache.maven.scm.provider.bazaar.BazaarUtils;
import java.io.File;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.bazaar.command.status.BazaarStatusCommand;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class BazaarCheckInCommand extends AbstractCheckInCommand
{
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final ScmVersion version) throws ScmException {
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            throw new ScmException("This provider can't handle tags.");
        }
        final List<ScmFile> commitedFiles = new ArrayList<ScmFile>();
        final List<File> files = fileSet.getFileList();
        if (files.isEmpty()) {
            final BazaarStatusCommand statusCmd = new BazaarStatusCommand();
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
        commitCmd = BazaarUtils.expandCommandLine(commitCmd, fileSet);
        ScmResult result = BazaarUtils.execute(new BazaarConsumer(this.getLogger()), this.getLogger(), fileSet.getBasedir(), commitCmd);
        final BazaarScmProviderRepository repository = (BazaarScmProviderRepository)repo;
        if (!repository.getURI().equals(fileSet.getBasedir().getAbsolutePath()) && repo.isPushChanges()) {
            final String[] pushCmd = { "push", "--no-strict", repository.getURI() };
            result = BazaarUtils.execute(new BazaarConsumer(this.getLogger()), this.getLogger(), fileSet.getBasedir(), pushCmd);
        }
        return new CheckInScmResult(commitedFiles, result);
    }
}
