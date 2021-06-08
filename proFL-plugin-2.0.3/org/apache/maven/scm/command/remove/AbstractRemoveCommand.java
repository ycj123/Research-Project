// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.remove;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractRemoveCommand extends AbstractCommand
{
    protected abstract ScmResult executeRemoveCommand(final ScmProviderRepository p0, final ScmFileSet p1, final String p2) throws ScmException;
    
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final String message = parameters.getString(CommandParameter.MESSAGE);
        return this.executeRemoveCommand(repository, fileSet, message);
    }
}
