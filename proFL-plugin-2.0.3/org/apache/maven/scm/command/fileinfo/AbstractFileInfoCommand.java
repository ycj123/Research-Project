// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.fileinfo;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import java.io.File;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractFileInfoCommand extends AbstractCommand
{
    protected abstract ScmResult executeFileInfoCommand(final ScmProviderRepository p0, final File p1, final String p2) throws ScmException;
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final File workingDirectory, final CommandParameters parameters) throws ScmException {
        final String file = parameters.getString(CommandParameter.FILE);
        return this.executeFileInfoCommand(repository, workingDirectory, file);
    }
}
