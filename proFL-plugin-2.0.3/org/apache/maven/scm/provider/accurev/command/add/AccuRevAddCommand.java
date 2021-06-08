// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.add;

import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.provider.accurev.AccuRev;
import org.apache.maven.scm.command.add.AddScmResult;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevCommand;

public class AccuRevAddCommand extends AbstractAccuRevCommand
{
    public AccuRevAddCommand(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    protected ScmResult executeAccurevCommand(final AccuRevScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException, AccuRevException {
        final AccuRev accuRev = repository.getAccuRev();
        final String message = parameters.getString(CommandParameter.MESSAGE, "");
        final File basedir = fileSet.getBasedir();
        final List<File> relativeFiles = fileSet.getFileList();
        final List<File> addedFiles = accuRev.add(basedir, relativeFiles, message);
        if (addedFiles != null) {
            final List<ScmFile> resultFiles = AbstractAccuRevCommand.getScmFiles(addedFiles, ScmFileStatus.ADDED);
            return new AddScmResult(accuRev.getCommandLines(), resultFiles);
        }
        return new AddScmResult(accuRev.getCommandLines(), "AccuRev Error", accuRev.getErrorOutput(), false);
    }
    
    public AddScmResult add(final ScmProviderRepository repo, final ScmFileSet scmFileSet, final CommandParameters commandParameters) throws ScmException {
        return (AddScmResult)this.execute(repo, scmFileSet, commandParameters);
    }
}
