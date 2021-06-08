// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.checkout;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractCheckOutCommand extends AbstractCommand
{
    protected CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion scmVersion) throws ScmException {
        return this.executeCheckOutCommand(repository, fileSet, scmVersion, true);
    }
    
    protected abstract CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository p0, final ScmFileSet p1, final ScmVersion p2, final boolean p3) throws ScmException;
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final ScmVersion scmVersion = parameters.getScmVersion(CommandParameter.SCM_VERSION, null);
        final String recursiveParam = parameters.getString(CommandParameter.RECURSIVE, null);
        if (recursiveParam != null) {
            final boolean recursive = parameters.getBoolean(CommandParameter.RECURSIVE);
            return this.executeCheckOutCommand(repository, fileSet, scmVersion, recursive);
        }
        return this.executeCheckOutCommand(repository, fileSet, scmVersion);
    }
}
