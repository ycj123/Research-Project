// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.add;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractAddCommand extends AbstractCommand
{
    protected abstract ScmResult executeAddCommand(final ScmProviderRepository p0, final ScmFileSet p1, final String p2, final boolean p3) throws ScmException;
    
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return this.executeAddCommand(repository, fileSet, parameters.getString(CommandParameter.MESSAGE, "no message"), parameters.getBoolean(CommandParameter.BINARY));
    }
}
