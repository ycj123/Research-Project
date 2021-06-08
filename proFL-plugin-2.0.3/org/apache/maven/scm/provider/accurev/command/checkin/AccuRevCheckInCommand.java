// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.checkin;

import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import java.util.Iterator;
import org.apache.maven.scm.provider.accurev.AccuRevInfo;
import java.util.List;
import org.apache.maven.scm.provider.accurev.AccuRev;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevCommand;

public class AccuRevCheckInCommand extends AbstractAccuRevCommand
{
    public AccuRevCheckInCommand(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    protected ScmResult executeAccurevCommand(final AccuRevScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException, AccuRevException {
        final AccuRev accuRev = repository.getAccuRev();
        final String message = parameters.getString(CommandParameter.MESSAGE);
        List<File> promotedFiles = null;
        final File basedir = fileSet.getBasedir();
        final List<File> fileList = fileSet.getFileList();
        if (fileList.isEmpty()) {
            final AccuRevInfo info = accuRev.info(basedir);
            if (!repository.isWorkSpaceRoot(info)) {
                throw new ScmException(String.format("Unsupported recursive checkin for %s. Not the workspace root", basedir.getAbsolutePath()));
            }
            promotedFiles = accuRev.promoteAll(basedir, message);
        }
        else {
            promotedFiles = accuRev.promote(basedir, fileList, message);
        }
        if (promotedFiles != null) {
            final Iterator<File> iter = promotedFiles.iterator();
            while (iter.hasNext()) {
                if (new File(basedir, iter.next().getPath()).isDirectory()) {
                    iter.remove();
                }
            }
            return new CheckInScmResult(accuRev.getCommandLines(), AbstractAccuRevCommand.getScmFiles(promotedFiles, ScmFileStatus.CHECKED_IN));
        }
        return new CheckInScmResult(accuRev.getCommandLines(), "AccuRev Error", accuRev.getErrorOutput(), false);
    }
    
    public CheckInScmResult checkIn(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (CheckInScmResult)this.execute(repository, fileSet, parameters);
    }
}
