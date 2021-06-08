// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.remove;

import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.provider.accurev.AccuRev;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevCommand;

public class AccuRevRemoveCommand extends AbstractAccuRevCommand
{
    public AccuRevRemoveCommand(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    protected ScmResult executeAccurevCommand(final AccuRevScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException, AccuRevException {
        final AccuRev accuRev = repository.getAccuRev();
        final String message = parameters.getString(CommandParameter.MESSAGE, "");
        final File basedir = fileSet.getBasedir();
        final List<File> relativeFiles = fileSet.getFileList();
        final List<File> removedFiles = accuRev.defunct(basedir, relativeFiles, message);
        if (removedFiles != null) {
            final List<ScmFile> resultFiles = AbstractAccuRevCommand.getScmFiles(removedFiles, ScmFileStatus.DELETED);
            return new RemoveScmResult(accuRev.getCommandLines(), resultFiles);
        }
        return new RemoveScmResult(accuRev.getCommandLines(), "AccuRev Error", accuRev.getErrorOutput(), false);
    }
    
    public RemoveScmResult remove(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (RemoveScmResult)this.execute(repository, fileSet, parameters);
    }
}
