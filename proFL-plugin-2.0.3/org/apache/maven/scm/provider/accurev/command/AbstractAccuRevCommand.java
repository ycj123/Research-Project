// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command;

import java.util.Iterator;
import java.util.ArrayList;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import java.util.List;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractAccuRevCommand extends AbstractCommand
{
    public AbstractAccuRevCommand(final ScmLogger logger) {
        this.setLogger(logger);
    }
    
    protected abstract ScmResult executeAccurevCommand(final AccuRevScmProviderRepository p0, final ScmFileSet p1, final CommandParameters p2) throws ScmException, AccuRevException;
    
    @Override
    protected final ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        if (!(repository instanceof AccuRevScmProviderRepository)) {
            throw new ScmException("Not an AccuRev repository " + repository);
        }
        final AccuRevScmProviderRepository accuRevRepository = (AccuRevScmProviderRepository)repository;
        accuRevRepository.getAccuRev().reset();
        try {
            return this.executeAccurevCommand(accuRevRepository, fileSet, parameters);
        }
        catch (AccuRevException e) {
            throw new ScmException("Error invoking AccuRev command", e);
        }
    }
    
    protected static List<ScmFile> getScmFiles(final List<File> files, final ScmFileStatus status) {
        final ArrayList<ScmFile> resultFiles = new ArrayList<ScmFile>(files.size());
        for (final File addedFile : files) {
            resultFiles.add(new ScmFile(addedFile.getPath(), status));
        }
        return resultFiles;
    }
}
