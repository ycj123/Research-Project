// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.command.checkin;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.provider.synergy.util.SynergyTaskManager;
import org.apache.maven.scm.provider.synergy.util.SynergyRole;
import org.apache.maven.scm.provider.synergy.util.SynergyUtil;
import org.apache.maven.scm.provider.synergy.repository.SynergyScmProviderRepository;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.synergy.command.SynergyCommand;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class SynergyCheckInCommand extends AbstractCheckInCommand implements SynergyCommand
{
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message, final ScmVersion version) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing checkin command...");
        }
        final SynergyScmProviderRepository repo = (SynergyScmProviderRepository)repository;
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(fileSet.toString());
        }
        final String ccmAddr = SynergyUtil.start(this.getLogger(), repo.getUser(), repo.getPassword(), null);
        try {
            SynergyTaskManager.getInstance().checkinDefaultTask(this.getLogger(), message, ccmAddr);
        }
        finally {
            SynergyUtil.stop(this.getLogger(), ccmAddr);
        }
        final List<ScmFile> scmFiles = new ArrayList<ScmFile>(fileSet.getFileList().size());
        for (final File f : fileSet.getFileList()) {
            scmFiles.add(new ScmFile(f.getPath(), ScmFileStatus.CHECKED_IN));
        }
        return new CheckInScmResult("ccm checkin", scmFiles);
    }
}
