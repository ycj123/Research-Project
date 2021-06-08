// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.remove;

import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.ScmFileStatus;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.cvslib.command.CvsCommand;
import org.apache.maven.scm.command.remove.AbstractRemoveCommand;

public abstract class AbstractCvsRemoveCommand extends AbstractRemoveCommand implements CvsCommand
{
    @Override
    protected ScmResult executeRemoveCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("remove", repository, fileSet);
        cl.createArg().setValue("-f");
        cl.createArg().setValue("-l");
        final List<File> files = fileSet.getFileList();
        final List<ScmFile> removedFiles = new ArrayList<ScmFile>();
        for (final File file : files) {
            final String path = file.getPath().replace('\\', '/');
            cl.createArg().setValue(path);
            removedFiles.add(new ScmFile(path, ScmFileStatus.DELETED));
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl, removedFiles);
    }
    
    protected abstract RemoveScmResult executeCvsCommand(final Commandline p0, final List<ScmFile> p1) throws ScmException;
}
