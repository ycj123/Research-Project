// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.command.status;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.LinkedList;
import org.apache.maven.scm.provider.synergy.util.SynergyRole;
import org.apache.maven.scm.provider.synergy.util.SynergyUtil;
import org.apache.maven.scm.provider.synergy.repository.SynergyScmProviderRepository;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.synergy.command.SynergyCommand;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class SynergyStatusCommand extends AbstractStatusCommand implements SynergyCommand
{
    @Override
    protected StatusScmResult executeStatusCommand(final ScmProviderRepository repository, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing status command...");
        }
        final SynergyScmProviderRepository repo = (SynergyScmProviderRepository)repository;
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("basedir: " + fileSet.getBasedir());
        }
        final String ccmAddr = SynergyUtil.start(this.getLogger(), repo.getUser(), repo.getPassword(), null);
        List<String> l;
        try {
            l = SynergyUtil.getWorkingFiles(this.getLogger(), repo.getProjectSpec(), repo.getProjectRelease(), ccmAddr);
        }
        finally {
            SynergyUtil.stop(this.getLogger(), ccmAddr);
        }
        final List<ScmFile> result = new LinkedList<ScmFile>();
        for (final String filename : l) {
            final ScmFile f = new ScmFile(filename, ScmFileStatus.MODIFIED);
            result.add(f);
        }
        return new StatusScmResult("ccm dir", result);
    }
}
