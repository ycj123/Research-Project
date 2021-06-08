// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.status;

import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class SvnStatusCommand extends AbstractStatusCommand implements SvnCommand
{
    @Override
    protected StatusScmResult executeStatusCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        final Commandline cl = createCommandLine((SvnScmProviderRepository)repo, fileSet);
        final SvnStatusConsumer consumer = new SvnStatusConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + SvnCommandLineUtils.cryptPassword(cl));
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        int exitCode;
        try {
            exitCode = SvnCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new StatusScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        return new StatusScmResult(cl.toString(), consumer.getChangedFiles());
    }
    
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final ScmFileSet fileSet) {
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(fileSet.getBasedir(), repository);
        cl.createArg().setValue("status");
        return cl;
    }
}
