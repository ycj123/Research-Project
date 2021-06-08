// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.diff;

import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.cvslib.command.CvsCommand;
import org.apache.maven.scm.command.diff.AbstractDiffCommand;

public abstract class AbstractCvsDiffCommand extends AbstractDiffCommand implements CvsCommand
{
    @Override
    protected DiffScmResult executeDiffCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion startRevision, final ScmVersion endRevision) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("diff", repository, fileSet);
        cl.createArg().setValue("-u");
        if (this.isSupportNewFileParameter()) {
            cl.createArg().setValue("-N");
        }
        if (startRevision != null && StringUtils.isNotEmpty(startRevision.getName())) {
            cl.createArg().setValue("-r");
            cl.createArg().setValue(startRevision.getName());
        }
        if (endRevision != null && StringUtils.isNotEmpty(endRevision.getName())) {
            cl.createArg().setValue("-r");
            cl.createArg().setValue(endRevision.getName());
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl);
    }
    
    protected abstract DiffScmResult executeCvsCommand(final Commandline p0) throws ScmException;
    
    protected boolean isSupportNewFileParameter() {
        return true;
    }
}
