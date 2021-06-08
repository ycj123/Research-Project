// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.blame;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractBlameCommand extends AbstractCommand
{
    public abstract BlameScmResult executeBlameCommand(final ScmProviderRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet workingDirectory, final CommandParameters parameters) throws ScmException {
        final String file = parameters.getString(CommandParameter.FILE);
        return this.executeBlameCommand(repository, workingDirectory, file);
    }
}
