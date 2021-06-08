// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.status;

import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractStatusCommand extends AbstractCommand
{
    protected abstract StatusScmResult executeStatusCommand(final ScmProviderRepository p0, final ScmFileSet p1) throws ScmException;
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return this.executeStatusCommand(repository, fileSet);
    }
}
