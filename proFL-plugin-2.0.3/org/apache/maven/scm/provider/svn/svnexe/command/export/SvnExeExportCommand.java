// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.export;

import org.apache.maven.scm.ScmRevision;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.export.ExportScmResultWithRevision;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.svn.svnexe.command.update.SvnUpdateConsumer;
import org.apache.maven.scm.provider.svn.SvnCommandUtils;
import org.apache.maven.scm.ScmBranch;
import org.apache.maven.scm.provider.svn.SvnTagBranchUtils;
import org.apache.maven.scm.ScmTag;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.command.export.ExportScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.export.AbstractExportCommand;

public class SvnExeExportCommand extends AbstractExportCommand implements SvnCommand
{
    @Override
    protected ExportScmResult executeExportCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version, String outputDirectory) throws ScmException {
        if (outputDirectory == null) {
            outputDirectory = fileSet.getBasedir().getAbsolutePath();
        }
        final SvnScmProviderRepository repository = (SvnScmProviderRepository)repo;
        String url = repository.getUrl();
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            if (version instanceof ScmTag) {
                url = SvnTagBranchUtils.resolveTagUrl(repository, (ScmTag)version);
            }
            else if (version instanceof ScmBranch) {
                url = SvnTagBranchUtils.resolveBranchUrl(repository, (ScmBranch)version);
            }
        }
        url = SvnCommandUtils.fixUrl(url, repository.getUser());
        final Commandline cl = createCommandLine((SvnScmProviderRepository)repo, fileSet.getBasedir(), version, url, outputDirectory);
        final SvnUpdateConsumer consumer = new SvnUpdateConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + SvnCommandLineUtils.cryptPassword(cl));
            if (cl.getWorkingDirectory() != null) {
                this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
            }
        }
        int exitCode;
        try {
            exitCode = SvnCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new ExportScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        return new ExportScmResultWithRevision(cl.toString(), consumer.getUpdatedFiles(), String.valueOf(consumer.getRevision()));
    }
    
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final File workingDirectory, ScmVersion version, final String url, final String outputSirectory) {
        if (version != null && StringUtils.isEmpty(version.getName())) {
            version = null;
        }
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(workingDirectory, repository);
        cl.createArg().setValue("export");
        if (version != null && StringUtils.isNotEmpty(version.getName()) && version instanceof ScmRevision) {
            cl.createArg().setValue("-r");
            cl.createArg().setValue(version.getName());
        }
        cl.createArg().setValue("--force");
        cl.createArg().setValue(url);
        if (StringUtils.isNotEmpty(outputSirectory)) {
            cl.createArg().setValue(outputSirectory);
        }
        return cl;
    }
}
