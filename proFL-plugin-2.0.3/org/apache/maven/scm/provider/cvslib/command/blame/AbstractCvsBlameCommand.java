// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.blame;

import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.cvslib.command.CvsCommand;
import org.apache.maven.scm.command.blame.AbstractBlameCommand;

public abstract class AbstractCvsBlameCommand extends AbstractBlameCommand implements CvsCommand
{
    @Override
    public BlameScmResult executeBlameCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String filename) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("annotate", repository, fileSet);
        cl.createArg().setValue(filename);
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl, repository);
    }
    
    protected abstract BlameScmResult executeCvsCommand(final Commandline p0, final CvsScmProviderRepository p1) throws ScmException;
}
