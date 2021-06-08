// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.list;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractListCommand extends AbstractCommand
{
    protected abstract ListScmResult executeListCommand(final ScmProviderRepository p0, final ScmFileSet p1, final boolean p2, final ScmVersion p3) throws ScmException;
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        if (fileSet.getFileList().isEmpty()) {
            throw new IllegalArgumentException("fileSet can not be empty");
        }
        final boolean recursive = parameters.getBoolean(CommandParameter.RECURSIVE);
        final ScmVersion scmVersion = parameters.getScmVersion(CommandParameter.SCM_VERSION, null);
        return this.executeListCommand(repository, fileSet, recursive, scmVersion);
    }
}
