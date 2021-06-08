// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.checkout;

import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.provider.accurev.AccuRevInfo;
import org.apache.maven.scm.provider.accurev.AccuRev;
import java.util.Collection;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.scm.provider.accurev.AccuRevVersion;
import java.io.File;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevExtractSourceCommand;

public class AccuRevCheckOutCommand extends AbstractAccuRevExtractSourceCommand
{
    public AccuRevCheckOutCommand(final ScmLogger logger) {
        super(logger);
    }
    
    public CheckOutScmResult checkout(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        return (CheckOutScmResult)this.execute(repository, fileSet, parameters);
    }
    
    @Override
    protected List<File> extractSource(final AccuRevScmProviderRepository repository, final File basedir, final AccuRevVersion version) throws AccuRevException {
        final AccuRev accuRev = repository.getAccuRev();
        final AccuRevInfo info = accuRev.info(basedir);
        final List<File> extractedFiles = new ArrayList<File>();
        final String basisStream = version.getBasisStream();
        String transactionId = version.getTimeSpec();
        boolean success = true;
        if (info.isWorkSpace()) {
            if (!repository.isWorkSpaceTop(info)) {
                throw new AccuRevException(String.format("Can't checkout to %s, a subdirectory of existing workspace %s", basedir, info.getWorkSpace()));
            }
            if (!basisStream.equals(info.getBasis())) {
                success = accuRev.chws(basedir, info.getWorkSpace(), basisStream);
            }
            if (success) {
                final List<File> poppedFiles = accuRev.pop(basedir, null);
                if (poppedFiles != null) {
                    extractedFiles.addAll(poppedFiles);
                }
                else {
                    success = false;
                }
            }
        }
        else {
            final String workSpaceName = getWorkSpaceName(basedir, basisStream);
            success = accuRev.mkws(basisStream, workSpaceName, basedir);
            transactionId = "now";
            if (success) {
                this.getLogger().info("Created workspace " + workSpaceName);
            }
        }
        if (success) {
            final List<File> updatedFiles = accuRev.update(basedir, transactionId);
            if (updatedFiles != null) {
                extractedFiles.addAll(updatedFiles);
            }
            else {
                success = false;
            }
        }
        return success ? extractedFiles : null;
    }
    
    @Override
    protected ScmResult getScmResult(final AccuRevScmProviderRepository repository, final List<ScmFile> scmFiles, final ScmVersion version) {
        final AccuRev accuRev = repository.getAccuRev();
        if (scmFiles != null) {
            return new CheckOutScmResult(accuRev.getCommandLines(), scmFiles, repository.getProjectPath());
        }
        return new CheckOutScmResult(accuRev.getCommandLines(), "AccuRev Error", accuRev.getErrorOutput(), false);
    }
    
    public static String getWorkSpaceName(final File basedir, final String basisStream) {
        final String baseName = basedir.getName();
        String workSpaceName;
        if (baseName.contains(basisStream)) {
            workSpaceName = baseName;
        }
        else if (basisStream.contains(baseName)) {
            workSpaceName = basisStream;
        }
        else {
            workSpaceName = basisStream + "_" + baseName;
        }
        return workSpaceName;
    }
}
