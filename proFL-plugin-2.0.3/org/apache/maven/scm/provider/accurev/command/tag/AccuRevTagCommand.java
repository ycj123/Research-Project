// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.tag;

import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.ScmException;
import java.util.List;
import org.apache.maven.scm.provider.accurev.AccuRevInfo;
import java.io.File;
import org.apache.maven.scm.provider.accurev.AccuRev;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevCommand;

public class AccuRevTagCommand extends AbstractAccuRevCommand
{
    public AccuRevTagCommand(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    protected ScmResult executeAccurevCommand(final AccuRevScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException, AccuRevException {
        final AccuRev accuRev = repository.getAccuRev();
        String snapshotName = parameters.getString(CommandParameter.TAG_NAME);
        snapshotName = repository.getSnapshotName(snapshotName);
        final File basedir = fileSet.getBasedir();
        boolean success = true;
        final AccuRevInfo info = accuRev.info(basedir);
        List<File> taggedFiles = null;
        success = accuRev.mksnap(snapshotName, info.getBasis());
        if (success) {
            taggedFiles = accuRev.statTag(snapshotName);
        }
        if (success && taggedFiles != null) {
            return new TagScmResult(accuRev.getCommandLines(), AbstractAccuRevCommand.getScmFiles(taggedFiles, ScmFileStatus.TAGGED));
        }
        return new TagScmResult(accuRev.getCommandLines(), "AccuRev error", accuRev.getErrorOutput(), false);
    }
    
    public TagScmResult tag(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (TagScmResult)this.execute(repository, fileSet, parameters);
    }
}
