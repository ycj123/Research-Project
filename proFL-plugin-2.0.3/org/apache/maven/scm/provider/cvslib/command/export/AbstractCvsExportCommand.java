// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.export;

import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.export.AbstractExportCommand;

public abstract class AbstractCvsExportCommand extends AbstractExportCommand
{
    @Override
    protected ExportScmResult executeExportCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version, final String outputDirectory) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("export", repository, fileSet);
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            cl.createArg().setValue("-r" + version.getName());
        }
        else {
            cl.createArg().setValue("-rHEAD");
        }
        if (StringUtils.isNotEmpty(outputDirectory)) {
            cl.createArg().setValue("-d");
            cl.createArg().setValue(outputDirectory);
        }
        cl.createArg().setValue(repository.getModule());
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl);
    }
    
    protected abstract ExportScmResult executeCvsCommand(final Commandline p0) throws ScmException;
}
