// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.mkdir;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.command.mkdir.MkdirScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.mkdir.AbstractMkdirCommand;

public abstract class AbstractCvsMkdirCommand extends AbstractMkdirCommand
{
    @Override
    protected MkdirScmResult executeMkdirCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message, final boolean createInLocal) throws ScmException {
        final CommandParameters parameters = new CommandParameters();
        parameters.setString(CommandParameter.MESSAGE, (message == null) ? "" : message);
        parameters.setString(CommandParameter.BINARY, "false");
        final Command cmd = this.getAddCommand();
        cmd.setLogger(this.getLogger());
        final ScmResult addResult = cmd.execute(repository, fileSet, parameters);
        if (!addResult.isSuccess()) {
            return new MkdirScmResult(addResult.getCommandLine().toString(), "The cvs command failed.", addResult.getCommandOutput(), false);
        }
        final List<ScmFile> addedFiles = new ArrayList<ScmFile>();
        for (final File file : fileSet.getFileList()) {
            final ScmFile scmFile = new ScmFile(file.getPath(), ScmFileStatus.ADDED);
            addedFiles.add(scmFile);
        }
        return new MkdirScmResult(addResult.getCommandLine().toString(), addedFiles);
    }
    
    protected abstract Command getAddCommand();
}
