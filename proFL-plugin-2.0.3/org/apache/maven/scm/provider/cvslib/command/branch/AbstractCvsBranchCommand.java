// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.branch;

import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.cvslib.command.CvsCommand;
import org.apache.maven.scm.command.branch.AbstractBranchCommand;

public abstract class AbstractCvsBranchCommand extends AbstractBranchCommand implements CvsCommand
{
    @Override
    protected ScmResult executeBranchCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String branchName, final String message) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("tag", repository, fileSet, false);
        cl.createArg().setValue("-b");
        cl.createArg().setValue("-F");
        cl.createArg().setValue("-c");
        cl.createArg().setValue(branchName);
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl);
    }
    
    protected abstract BranchScmResult executeCvsCommand(final Commandline p0) throws ScmException;
}
