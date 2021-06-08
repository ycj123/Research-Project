// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.command.remove;

import java.util.List;
import java.util.Iterator;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.synergy.util.SynergyRole;
import org.apache.maven.scm.provider.synergy.util.SynergyUtil;
import org.apache.maven.scm.provider.synergy.repository.SynergyScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.synergy.command.SynergyCommand;
import org.apache.maven.scm.command.remove.AbstractRemoveCommand;

public class SynergyRemoveCommand extends AbstractRemoveCommand implements SynergyCommand
{
    @Override
    protected ScmResult executeRemoveCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing remove command...");
        }
        final SynergyScmProviderRepository repo = (SynergyScmProviderRepository)repository;
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("basedir: " + fileSet.getBasedir());
        }
        final String ccmAddr = SynergyUtil.start(this.getLogger(), repo.getUser(), repo.getPassword(), null);
        try {
            final String projectSpec = SynergyUtil.getWorkingProject(this.getLogger(), repo.getProjectSpec(), repo.getUser(), ccmAddr);
            if (projectSpec == null) {
                throw new ScmException("You should checkout a working project first");
            }
            final File waPath = SynergyUtil.getWorkArea(this.getLogger(), projectSpec, ccmAddr);
            final File destPath = new File(waPath, repo.getProjectName());
            for (final File f : fileSet.getFileList()) {
                final File source = new File(fileSet.getBasedir(), f.getPath());
                final File dest = new File(destPath, f.getPath());
                SynergyUtil.delete(this.getLogger(), dest, ccmAddr, false);
                if (!source.equals(dest)) {
                    if (this.getLogger().isDebugEnabled()) {
                        this.getLogger().debug("Delete file [" + source + "].");
                    }
                    dest.delete();
                }
            }
        }
        finally {
            SynergyUtil.stop(this.getLogger(), ccmAddr);
        }
        final List<ScmFile> scmFiles = new ArrayList<ScmFile>();
        for (final File file : fileSet.getFileList()) {
            scmFiles.add(new ScmFile(file.getPath(), ScmFileStatus.DELETED));
        }
        return new StatusScmResult("", scmFiles);
    }
}
