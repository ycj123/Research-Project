// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.diff;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractDiffCommand extends AbstractCommand
{
    protected abstract DiffScmResult executeDiffCommand(final ScmProviderRepository p0, final ScmFileSet p1, final ScmVersion p2, final ScmVersion p3) throws ScmException;
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final ScmVersion startRevision = parameters.getScmVersion(CommandParameter.START_SCM_VERSION, null);
        final ScmVersion endRevision = parameters.getScmVersion(CommandParameter.END_SCM_VERSION, null);
        return this.executeDiffCommand(repository, fileSet, startRevision, endRevision);
    }
}
