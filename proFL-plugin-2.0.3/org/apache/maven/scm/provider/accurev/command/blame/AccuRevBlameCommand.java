// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.blame;

import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import org.apache.maven.scm.provider.accurev.AccuRev;
import java.io.File;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevCommand;

public class AccuRevBlameCommand extends AbstractAccuRevCommand
{
    public AccuRevBlameCommand(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    protected BlameScmResult executeAccurevCommand(final AccuRevScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException, AccuRevException {
        final AccuRev accuRev = repository.getAccuRev();
        final File file = new File(parameters.getString(CommandParameter.FILE));
        final List<BlameLine> lines = accuRev.annotate(fileSet.getBasedir(), file);
        if (lines != null) {
            return new BlameScmResult(accuRev.getCommandLines(), lines);
        }
        return new BlameScmResult(accuRev.getCommandLines(), "AccuRev Error", accuRev.getErrorOutput(), false);
    }
    
    public BlameScmResult blame(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (BlameScmResult)this.execute(repository, fileSet, parameters);
    }
}
