// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.list;

import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import org.codehaus.plexus.util.cli.Commandline;
import java.io.File;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.command.list.ListScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.cvslib.command.CvsCommand;
import org.apache.maven.scm.command.list.AbstractListCommand;

public abstract class AbstractCvsListCommand extends AbstractListCommand implements CvsCommand
{
    @Override
    protected ListScmResult executeListCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final boolean recursive, final ScmVersion version) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("rls", repository, fileSet, "-n");
        if (version != null && !StringUtils.isEmpty(version.getName())) {
            cl.createArg().setValue("-r");
            cl.createArg().setValue(version.getName());
        }
        cl.createArg().setValue("-d");
        cl.createArg().setValue("-e");
        if (recursive) {
            cl.createArg().setValue("-R");
        }
        for (final File target : fileSet.getFileList()) {
            String path = target.getPath();
            if (path.startsWith("\\")) {
                path = path.substring(1);
            }
            cl.createArg().setValue(path);
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl);
    }
    
    protected abstract ListScmResult executeCvsCommand(final Commandline p0) throws ScmException;
}
