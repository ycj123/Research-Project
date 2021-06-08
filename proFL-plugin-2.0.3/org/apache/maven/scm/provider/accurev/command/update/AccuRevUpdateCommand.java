// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.update;

import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.provider.ScmProviderRepository;
import java.util.Date;
import org.apache.maven.scm.ScmException;
import java.util.List;
import org.apache.maven.scm.provider.accurev.AccuRevVersion;
import org.apache.maven.scm.provider.accurev.AccuRevInfo;
import java.io.File;
import org.apache.maven.scm.provider.accurev.AccuRev;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevCommand;

public class AccuRevUpdateCommand extends AbstractAccuRevCommand
{
    public AccuRevUpdateCommand(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    protected ScmResult executeAccurevCommand(final AccuRevScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException, AccuRevException {
        final AccuRev accuRev = repository.getAccuRev();
        final File basedir = fileSet.getBasedir();
        final AccuRevInfo info = accuRev.info(basedir);
        if (!info.isWorkSpace()) {
            throw new AccuRevException("No workspace at " + basedir.getAbsolutePath());
        }
        final String startRevision = this.getStartRevision(repository, parameters, info);
        final ScmVersion scmVersion = parameters.getScmVersion(CommandParameter.SCM_VERSION, null);
        String updateTransactionId = null;
        if (scmVersion != null) {
            final AccuRevVersion updateVersion = repository.getAccuRevVersion(scmVersion);
            final String newBasisStream = updateVersion.getBasisStream();
            if (newBasisStream != null && !newBasisStream.equals(info.getWorkSpace()) && !newBasisStream.equals(info.getBasis())) {
                this.getLogger().info("Reparenting " + info.getWorkSpace() + " to " + newBasisStream);
                accuRev.chws(basedir, info.getWorkSpace(), newBasisStream);
            }
            if (!updateVersion.isNow()) {
                updateTransactionId = updateVersion.getTimeSpec();
            }
        }
        if (updateTransactionId == null) {
            updateTransactionId = repository.getDepotTransactionId(info.getWorkSpace(), "now");
        }
        final String endRevision = repository.getRevision(info.getWorkSpace(), updateTransactionId);
        final List<File> updatedFiles = accuRev.update(basedir, updateTransactionId);
        if (updatedFiles != null) {
            return new AccuRevUpdateScmResult(accuRev.getCommandLines(), AbstractAccuRevCommand.getScmFiles(updatedFiles, ScmFileStatus.UPDATED), startRevision, endRevision);
        }
        return new AccuRevUpdateScmResult(accuRev.getCommandLines(), "AccuRev error", accuRev.getErrorOutput(), null, null, false);
    }
    
    private String getStartRevision(final AccuRevScmProviderRepository repository, final CommandParameters parameters, final AccuRevInfo info) throws ScmException, AccuRevException {
        final boolean runChangeLog = parameters.getBoolean(CommandParameter.RUN_CHANGELOG_WITH_UPDATE);
        final Date startDate = parameters.getDate(CommandParameter.START_DATE, null);
        final String workspace = info.getWorkSpace();
        if (!runChangeLog) {
            return null;
        }
        return (startDate == null) ? repository.getWorkSpaceRevision(workspace) : repository.getRevision(workspace, startDate);
    }
    
    public UpdateScmResult update(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (UpdateScmResult)this.execute(repository, fileSet, parameters);
    }
}
