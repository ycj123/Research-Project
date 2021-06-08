// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.branch;

import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmBranchParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractBranchCommand extends AbstractCommand
{
    protected abstract ScmResult executeBranchCommand(final ScmProviderRepository p0, final ScmFileSet p1, final String p2, final String p3) throws ScmException;
    
    protected ScmResult executeBranchCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String branchName, final ScmBranchParameters scmBranchParameters) throws ScmException {
        return this.executeBranchCommand(repository, fileSet, branchName, scmBranchParameters.getMessage());
    }
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final String branchName = parameters.getString(CommandParameter.BRANCH_NAME);
        final ScmBranchParameters scmBranchParameters = parameters.getScmBranchParameters(CommandParameter.SCM_BRANCH_PARAMETERS);
        final String message = parameters.getString(CommandParameter.MESSAGE, "[maven-scm] copy for branch " + branchName);
        if (StringUtils.isBlank(scmBranchParameters.getMessage()) && StringUtils.isNotBlank(message)) {
            scmBranchParameters.setMessage(message);
        }
        return this.executeBranchCommand(repository, fileSet, branchName, scmBranchParameters);
    }
}
