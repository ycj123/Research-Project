// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.status;

import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.cvslib.command.CvsCommand;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public abstract class AbstractCvsStatusCommand extends AbstractStatusCommand implements CvsCommand
{
    @Override
    protected StatusScmResult executeStatusCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("update", repository, fileSet, "-n");
        cl.createArg().setValue("-d");
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl);
    }
    
    protected abstract StatusScmResult executeCvsCommand(final Commandline p0) throws ScmException;
}
