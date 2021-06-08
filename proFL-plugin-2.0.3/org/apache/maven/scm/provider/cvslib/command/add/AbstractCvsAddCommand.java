// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.add;

import org.apache.maven.scm.command.add.AddScmResult;
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
import org.apache.maven.scm.command.add.AbstractAddCommand;

public abstract class AbstractCvsAddCommand extends AbstractAddCommand implements CvsCommand
{
    @Override
    protected ScmResult executeAddCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final boolean binary) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("add", repository, fileSet);
        if (binary) {
            cl.createArg().setValue("-kb");
        }
        if (message != null && message.length() > 0) {
            cl.createArg().setValue("-m");
            cl.createArg().setValue("\"" + message + "\"");
        }
        final List<ScmFile> addedFiles = new ArrayList<ScmFile>(fileSet.getFileList().size());
        for (final File file : fileSet.getFileList()) {
            final String path = file.getPath().replace('\\', '/');
            cl.createArg().setValue(path);
            addedFiles.add(new ScmFile(path, ScmFileStatus.ADDED));
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl, addedFiles);
    }
    
    protected abstract AddScmResult executeCvsCommand(final Commandline p0, final List<ScmFile> p1) throws ScmException;
}
