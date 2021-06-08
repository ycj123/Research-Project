// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.status;

import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.status.StatusScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.status.AbstractStatusCommand;

public class StarteamStatusCommand extends AbstractStatusCommand implements StarteamCommand
{
    @Override
    protected StatusScmResult executeStatusCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Working directory: " + fileSet.getBasedir().getAbsolutePath());
        }
        if (fileSet.getFileList().size() != 0) {
            throw new ScmException("This provider doesn't support checking status of a subsets of a directory");
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final StarteamStatusConsumer consumer = new StarteamStatusConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final Commandline cl = createCommandLine(repository, fileSet);
        final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new StatusScmResult(cl.toString(), "The starteam command failed.", stderr.getOutput(), false);
        }
        return new StatusScmResult(cl.toString(), consumer.getChangedFiles());
    }
    
    public static Commandline createCommandLine(final StarteamScmProviderRepository repo, final ScmFileSet workingDirectory) {
        return StarteamCommandLineUtils.createStarteamCommandLine("hist", null, workingDirectory, repo);
    }
}
