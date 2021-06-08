// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.checkin;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractCheckInCommand extends AbstractCommand
{
    public static final String NAME = "check-in";
    
    protected abstract CheckInScmResult executeCheckInCommand(final ScmProviderRepository p0, final ScmFileSet p1, final String p2, final ScmVersion p3) throws ScmException;
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final String message = parameters.getString(CommandParameter.MESSAGE);
        final ScmVersion scmVersion = parameters.getScmVersion(CommandParameter.SCM_VERSION, null);
        return this.executeCheckInCommand(repository, fileSet, message, scmVersion);
    }
}
