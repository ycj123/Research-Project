// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command;

import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import org.apache.maven.scm.provider.accurev.AccuRevVersion;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmException;
import java.io.File;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;

public abstract class AbstractAccuRevExtractSourceCommand extends AbstractAccuRevCommand
{
    public AbstractAccuRevExtractSourceCommand(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    protected ScmResult executeAccurevCommand(final AccuRevScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException, AccuRevException {
        final ScmVersion scmVersion = parameters.getScmVersion(CommandParameter.SCM_VERSION, null);
        File basedir = fileSet.getBasedir();
        final String outputDirectory = parameters.getString(CommandParameter.OUTPUT_DIRECTORY, null);
        if (outputDirectory != null) {
            basedir = new File(outputDirectory);
        }
        if (!basedir.exists()) {
            basedir.mkdirs();
        }
        if (!basedir.isDirectory() || basedir.list().length != 0) {
            throw new ScmException("Checkout directory " + basedir.getAbsolutePath() + " not empty");
        }
        final AccuRevVersion accuRevVersion = repository.getAccuRevVersion(scmVersion);
        final List<File> checkedOutFiles = this.extractSource(repository, basedir, accuRevVersion);
        final List<ScmFile> scmFiles = (checkedOutFiles == null) ? null : AbstractAccuRevCommand.getScmFiles(checkedOutFiles, ScmFileStatus.CHECKED_OUT);
        return this.getScmResult(repository, scmFiles, scmVersion);
    }
    
    protected abstract ScmResult getScmResult(final AccuRevScmProviderRepository p0, final List<ScmFile> p1, final ScmVersion p2);
    
    protected abstract List<File> extractSource(final AccuRevScmProviderRepository p0, final File p1, final AccuRevVersion p2) throws AccuRevException;
}
