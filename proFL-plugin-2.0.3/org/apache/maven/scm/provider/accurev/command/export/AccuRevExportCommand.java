// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.export;

import org.apache.maven.scm.command.export.ExportScmResultWithRevision;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.provider.accurev.AccuRevInfo;
import org.apache.maven.scm.provider.accurev.AccuRev;
import java.util.Collection;
import java.util.Collections;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.provider.accurev.AccuRevCapability;
import java.util.List;
import org.apache.maven.scm.provider.accurev.AccuRevVersion;
import java.io.File;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevExtractSourceCommand;

public class AccuRevExportCommand extends AbstractAccuRevExtractSourceCommand
{
    public AccuRevExportCommand(final ScmLogger logger) {
        super(logger);
    }
    
    public ExportScmResult export(final ScmProviderRepository repository, final ScmFileSet scmFileSet, final CommandParameters params) throws ScmException {
        return (ExportScmResult)this.execute(repository, scmFileSet, params);
    }
    
    @Override
    protected List<File> extractSource(final AccuRevScmProviderRepository repository, final File basedir, final AccuRevVersion version) throws AccuRevException {
        final AccuRev accuRev = repository.getAccuRev();
        final AccuRevInfo info = accuRev.info(basedir);
        final String basisStream = version.getBasisStream();
        String transactionId = version.getTimeSpec();
        if (!AccuRevVersion.isNow(transactionId) && !AccuRevCapability.POPULATE_TO_TRANSACTION.isSupported(accuRev.getClientVersion())) {
            this.getLogger().warn(String.format("Ignoring transaction id %s, Export can only extract current sources", transactionId));
            transactionId = "now";
        }
        else {
            accuRev.syncReplica();
        }
        boolean removedWorkspace = false;
        if (info.isWorkSpace()) {
            final String stat = accuRev.stat(basedir);
            if (stat != null) {
                throw new AccuRevException(String.format("Cannot populate %s, as it is a non-ignored subdirectory of workspace %s rooted at %s.", basedir.getAbsolutePath(), info.getWorkSpace(), info.getTop()));
            }
            removedWorkspace = accuRev.rmws(info.getWorkSpace());
        }
        try {
            final File path = new File(repository.getDepotRelativeProjectPath());
            return accuRev.popExternal(basedir, basisStream, transactionId, Collections.singletonList(path));
        }
        finally {
            if (removedWorkspace) {
                accuRev.reactivate(info.getWorkSpace());
            }
        }
    }
    
    @Override
    protected ScmResult getScmResult(final AccuRevScmProviderRepository repository, final List<ScmFile> scmFiles, final ScmVersion scmVersion) {
        final AccuRev accuRev = repository.getAccuRev();
        if (scmFiles == null) {
            return new ExportScmResult(accuRev.getCommandLines(), "AccuRev Error", accuRev.getErrorOutput(), false);
        }
        if (scmVersion == null) {
            return new ExportScmResult(accuRev.getCommandLines(), scmFiles);
        }
        return new ExportScmResultWithRevision(accuRev.getCommandLines(), scmFiles, scmVersion.toString());
    }
}
