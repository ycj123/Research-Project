// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.status;

import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.accurev.CategorisedElements;
import java.util.Iterator;
import java.util.List;
import org.apache.maven.scm.provider.accurev.AccuRev;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.accurev.AccuRevCapability;
import java.util.ArrayList;
import java.io.File;
import java.util.Collection;
import org.apache.maven.scm.provider.accurev.AccuRevStat;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevCommand;

public class AccuRevStatusCommand extends AbstractAccuRevCommand
{
    public AccuRevStatusCommand(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    protected ScmResult executeAccurevCommand(final AccuRevScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException, AccuRevException {
        final AccuRev accuRev = repository.getAccuRev();
        final File basedir = fileSet.getBasedir();
        final List<File> elements = fileSet.getFileList();
        final List<File> defunctElements = accuRev.stat(basedir, elements, AccuRevStat.DEFUNCT);
        if (defunctElements == null) {
            return this.error(accuRev, "Failed retrieving defunct elements");
        }
        final List<File> keptElements = accuRev.stat(basedir, elements, AccuRevStat.KEPT);
        if (keptElements == null) {
            return this.error(accuRev, "Failed retrieving kept elements");
        }
        final List<File> modOrAddedElements = new ArrayList<File>();
        for (final File file : keptElements) {
            if (!defunctElements.contains(file)) {
                modOrAddedElements.add(file);
            }
        }
        List<File> modifiedElements = accuRev.stat(basedir, elements, AccuRevStat.MODIFIED);
        if (modifiedElements == null) {
            return this.error(accuRev, "Failed retrieving modified elements");
        }
        modOrAddedElements.addAll(modifiedElements);
        final CategorisedElements catElems = accuRev.statBackingStream(basedir, modOrAddedElements);
        if (catElems == null) {
            return this.error(accuRev, "Failed stat backing stream to split modified and added elements");
        }
        modifiedElements = catElems.getMemberElements();
        List<File> addedElements;
        if (AccuRevCapability.STAT_ADDED_NOT_PROMOTED_BUG.isSupported(accuRev.getClientVersion())) {
            modOrAddedElements.removeAll(modifiedElements);
            addedElements = modOrAddedElements;
        }
        else {
            addedElements = catElems.getNonMemberElements();
        }
        final List<File> missingElements = accuRev.stat(basedir, elements, AccuRevStat.MISSING);
        if (missingElements == null) {
            return this.error(accuRev, "Failed retrieving missing elements");
        }
        final List<File> externalElements = accuRev.stat(basedir, elements, AccuRevStat.EXTERNAL);
        if (externalElements == null) {
            return this.error(accuRev, "Failed retrieving external elements");
        }
        final List<ScmFile> resultFiles = AbstractAccuRevCommand.getScmFiles(defunctElements, ScmFileStatus.DELETED);
        resultFiles.addAll(AbstractAccuRevCommand.getScmFiles(modifiedElements, ScmFileStatus.MODIFIED));
        resultFiles.addAll(AbstractAccuRevCommand.getScmFiles(addedElements, ScmFileStatus.ADDED));
        resultFiles.addAll(AbstractAccuRevCommand.getScmFiles(missingElements, ScmFileStatus.MISSING));
        resultFiles.addAll(AbstractAccuRevCommand.getScmFiles(externalElements, ScmFileStatus.UNKNOWN));
        return new StatusScmResult(accuRev.getCommandLines(), resultFiles);
    }
    
    private ScmResult error(final AccuRev accuRev, final String message) {
        return new StatusScmResult(accuRev.getCommandLines(), "AccuRev " + message, accuRev.getErrorOutput(), false);
    }
    
    public StatusScmResult status(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (StatusScmResult)this.execute(repository, fileSet, parameters);
    }
}
