// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.command.tag;

import java.util.Iterator;
import java.util.List;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.provider.synergy.util.SynergyUtil;
import org.apache.maven.scm.provider.synergy.util.SynergyRole;
import org.apache.maven.scm.provider.synergy.repository.SynergyScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.synergy.command.SynergyCommand;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class SynergyTagCommand extends AbstractTagCommand implements SynergyCommand
{
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String tag, final String message) throws ScmException {
        return this.executeTagCommand(repository, fileSet, tag, new ScmTagParameters(message));
    }
    
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String tag, final ScmTagParameters scmTagParameters) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing tag command...");
        }
        final SynergyScmProviderRepository repo = (SynergyScmProviderRepository)repository;
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("basedir: " + fileSet.getBasedir());
        }
        final String ccmAddr = SynergyUtil.start(this.getLogger(), repo.getUser(), repo.getPassword(), SynergyRole.BUILD_MGR);
        try {
            SynergyUtil.reconfigureProperties(this.getLogger(), repo.getProjectSpec(), ccmAddr);
            SynergyUtil.reconfigure(this.getLogger(), repo.getProjectSpec(), ccmAddr);
            SynergyUtil.createBaseline(this.getLogger(), repo.getProjectSpec(), tag, repo.getProjectRelease(), repo.getProjectPurpose(), ccmAddr);
        }
        finally {
            SynergyUtil.stop(this.getLogger(), ccmAddr);
        }
        final List<ScmFile> files = new ArrayList<ScmFile>(fileSet.getFileList().size());
        for (final File f : fileSet.getFileList()) {
            files.add(new ScmFile(f.getPath(), ScmFileStatus.TAGGED));
        }
        return new TagScmResult("", files);
    }
}
