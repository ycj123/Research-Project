// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.command.changelog;

import java.util.Iterator;
import java.util.List;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.apache.maven.scm.provider.synergy.util.SynergyTask;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ChangeSet;
import java.util.ArrayList;
import org.apache.maven.scm.provider.synergy.util.SynergyRole;
import org.apache.maven.scm.provider.synergy.util.SynergyUtil;
import org.apache.maven.scm.provider.synergy.repository.SynergyScmProviderRepository;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.synergy.command.SynergyCommand;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class SynergyChangeLogCommand extends AbstractChangeLogCommand implements SynergyCommand
{
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing changelog command...");
        }
        final SynergyScmProviderRepository repo = (SynergyScmProviderRepository)repository;
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("basedir: " + fileSet.getBasedir());
        }
        final String ccmAddr = SynergyUtil.start(this.getLogger(), repo.getUser(), repo.getPassword(), null);
        final List<ChangeSet> csList = new ArrayList<ChangeSet>();
        try {
            final String projectSpec = SynergyUtil.getWorkingProject(this.getLogger(), repo.getProjectSpec(), repo.getUser(), ccmAddr);
            if (projectSpec == null) {
                throw new ScmException("You should checkout a working project first");
            }
            final List<SynergyTask> tasks = SynergyUtil.getCompletedTasks(this.getLogger(), projectSpec, startDate, endDate, ccmAddr);
            for (final SynergyTask t : tasks) {
                final ChangeSet cs = new ChangeSet();
                cs.setAuthor(t.getUsername());
                cs.setComment("Task " + t.getNumber() + ": " + t.getComment());
                cs.setDate(t.getModifiedTime());
                cs.setFiles(SynergyUtil.getModifiedObjects(this.getLogger(), t.getNumber(), ccmAddr));
                csList.add(cs);
            }
        }
        finally {
            SynergyUtil.stop(this.getLogger(), ccmAddr);
        }
        return new ChangeLogScmResult("ccm query ...", new ChangeLogSet(csList, startDate, endDate));
    }
}
