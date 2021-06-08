// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.mkdir;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractMkdirCommand extends AbstractCommand
{
    protected abstract MkdirScmResult executeMkdirCommand(final ScmProviderRepository p0, final ScmFileSet p1, final String p2, final boolean p3) throws ScmException;
    
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        if (fileSet.getFileList().isEmpty()) {
            throw new IllegalArgumentException("fileSet can not be empty");
        }
        return this.executeMkdirCommand(repository, fileSet, parameters.getString(CommandParameter.MESSAGE), parameters.getBoolean(CommandParameter.SCM_MKDIR_CREATE_IN_LOCAL));
    }
}
