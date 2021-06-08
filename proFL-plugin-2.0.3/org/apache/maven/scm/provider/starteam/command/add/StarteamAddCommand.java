// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.add;

import java.util.List;
import java.util.ArrayList;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import java.util.Iterator;
import org.apache.maven.scm.command.add.AddScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import java.io.File;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.add.AbstractAddCommand;

public class StarteamAddCommand extends AbstractAddCommand implements StarteamCommand
{
    @Override
    protected ScmResult executeAddCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final boolean binary) throws ScmException {
        final String issue = System.getProperty("maven.scm.issue");
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Working directory: " + fileSet.getBasedir().getAbsolutePath());
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final StarteamAddConsumer consumer = new StarteamAddConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        for (final File fileToBeAdded : fileSet.getFileList()) {
            final ScmFileSet scmFile = new ScmFileSet(fileSet.getBasedir(), fileToBeAdded);
            final Commandline cl = createCommandLine(repository, scmFile, issue);
            final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
            if (exitCode != 0) {
                return new AddScmResult(cl.toString(), "The starteam command failed.", stderr.getOutput(), false);
            }
        }
        return new AddScmResult(null, consumer.getAddedFiles());
    }
    
    static Commandline createCommandLine(final StarteamScmProviderRepository repo, final ScmFileSet scmFileSet, final String issue) {
        final List<String> args = new ArrayList<String>();
        if (issue != null && issue.length() != 0) {
            args.add("-cr");
            args.add(issue);
        }
        StarteamCommandLineUtils.addEOLOption(args);
        return StarteamCommandLineUtils.createStarteamCommandLine("add", args, scmFileSet, repo);
    }
}
