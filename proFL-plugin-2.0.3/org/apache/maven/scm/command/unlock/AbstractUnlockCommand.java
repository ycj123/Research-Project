// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.unlock;

import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import java.io.File;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractUnlockCommand extends AbstractCommand
{
    protected abstract ScmResult executeUnlockCommand(final ScmProviderRepository p0, final File p1) throws ScmException;
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final File workingDirectory, final CommandParameters parameters) throws ScmException {
        return this.executeUnlockCommand(repository, workingDirectory);
    }
}
