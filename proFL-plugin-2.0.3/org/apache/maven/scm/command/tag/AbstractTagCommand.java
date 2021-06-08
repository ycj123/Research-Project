// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.tag;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractTagCommand extends AbstractCommand
{
    @Deprecated
    protected ScmResult executeTagCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String tagName, final String message) throws ScmException {
        return this.executeTagCommand(repository, fileSet, tagName, new ScmTagParameters(message));
    }
    
    protected abstract ScmResult executeTagCommand(final ScmProviderRepository p0, final ScmFileSet p1, final String p2, final ScmTagParameters p3) throws ScmException;
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final String tagName = parameters.getString(CommandParameter.TAG_NAME);
        final ScmTagParameters scmTagParameters = parameters.getScmTagParameters(CommandParameter.SCM_TAG_PARAMETERS);
        final String message = parameters.getString(CommandParameter.MESSAGE, null);
        if (message != null) {
            scmTagParameters.setMessage(message);
        }
        if (scmTagParameters.getMessage() == null) {
            scmTagParameters.setMessage("[maven-scm] copy for tag " + tagName);
        }
        return this.executeTagCommand(repository, fileSet, tagName, scmTagParameters);
    }
}
