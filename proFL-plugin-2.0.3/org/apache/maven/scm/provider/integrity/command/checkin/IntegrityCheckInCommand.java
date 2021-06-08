// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.checkin;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.integrity.Sandbox;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class IntegrityCheckInCommand extends AbstractCheckInCommand
{
    public CheckInScmResult executeCheckInCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message, final ScmVersion scmVersion) throws ScmException {
        this.getLogger().info("Attempting to check-in updates from sandbox " + fileSet.getBasedir().getAbsolutePath());
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        final Sandbox siSandbox = iRepo.getSandbox();
        final List<ScmFile> changedFiles = siSandbox.checkInUpdates(message);
        if (siSandbox.getOverallCheckInSuccess()) {
            return new CheckInScmResult("si ci/drop", changedFiles);
        }
        return new CheckInScmResult(changedFiles, new ScmResult("si ci/drop", "There was a problem updating the repository", "", false));
    }
}
