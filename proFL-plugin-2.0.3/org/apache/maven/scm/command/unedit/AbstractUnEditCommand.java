// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.unedit;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractUnEditCommand extends AbstractCommand
{
    public ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return this.executeUnEditCommand(repository, fileSet);
    }
    
    protected abstract ScmResult executeUnEditCommand(final ScmProviderRepository p0, final ScmFileSet p1) throws ScmException;
}
