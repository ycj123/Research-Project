// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.branch;

import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.branch.AbstractBranchCommand;

public class JazzBranchCommand extends AbstractBranchCommand
{
    @Override
    protected ScmResult executeBranchCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String branch, final String message) throws ScmException {
        throw new ScmException("This provider does not support the branch command.");
    }
}
