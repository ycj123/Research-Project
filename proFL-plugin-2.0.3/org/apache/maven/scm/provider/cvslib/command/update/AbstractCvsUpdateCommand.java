// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.update;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import java.io.File;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.cvslib.command.CvsCommand;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public abstract class AbstractCvsUpdateCommand extends AbstractUpdateCommand implements CvsCommand
{
    public UpdateScmResult executeUpdateCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("update", repository, fileSet, false);
        cl.createArg().setValue("-d");
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            cl.createArg().setValue("-r" + version.getName());
        }
        final List<File> files = fileSet.getFileList();
        if (!files.isEmpty()) {
            final Iterator<File> fileIterator = files.iterator();
            while (fileIterator.hasNext()) {
                cl.createArg().setValue(fileIterator.next().getPath());
            }
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl);
    }
    
    protected abstract UpdateScmResult executeCvsCommand(final Commandline p0) throws ScmException;
}
