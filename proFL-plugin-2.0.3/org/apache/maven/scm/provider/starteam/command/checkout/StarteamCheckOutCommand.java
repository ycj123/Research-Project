// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.checkout;

import java.util.List;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.checkout.AbstractCheckOutCommand;

public class StarteamCheckOutCommand extends AbstractCheckOutCommand implements StarteamCommand
{
    @Override
    protected CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version, final boolean recursive) throws ScmException {
        if (fileSet.getFileList().size() != 0) {
            throw new ScmException("This provider doesn't support checking out subsets of a directory");
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Working directory: " + fileSet.getBasedir().getAbsolutePath());
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final StarteamCheckOutConsumer consumer = new StarteamCheckOutConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final Commandline cl = createCommandLine(repository, fileSet, version);
        final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new CheckOutScmResult(cl.toString(), "The starteam command failed.", stderr.getOutput(), false);
        }
        return new CheckOutScmResult(cl.toString(), consumer.getCheckedOutFiles());
    }
    
    public static Commandline createCommandLine(final StarteamScmProviderRepository repo, final ScmFileSet baseDir, final ScmVersion version) {
        final List<String> args = new ArrayList<String>();
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            args.add("-vl");
            args.add(version.getName());
        }
        StarteamCommandLineUtils.addEOLOption(args);
        return StarteamCommandLineUtils.createStarteamCommandLine("co", args, baseDir, repo);
    }
}
