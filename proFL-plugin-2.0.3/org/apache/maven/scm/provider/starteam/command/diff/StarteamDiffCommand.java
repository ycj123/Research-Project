// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.diff;

import java.util.List;
import org.codehaus.plexus.util.StringUtils;
import java.util.ArrayList;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.diff.DiffScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.diff.AbstractDiffCommand;

public class StarteamDiffCommand extends AbstractDiffCommand implements StarteamCommand
{
    @Override
    protected DiffScmResult executeDiffCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion) throws ScmException {
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Working directory: " + fileSet.getBasedir().getAbsolutePath());
        }
        if (fileSet.getFileList().isEmpty()) {
            throw new ScmException("This provider doesn't support diff command on a subsets of a directory");
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final StarteamDiffConsumer consumer = new StarteamDiffConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final Commandline cl = createCommandLine(repository, fileSet, startVersion, endVersion);
        final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new DiffScmResult(cl.toString(), "The starteam command failed.", stderr.getOutput(), false);
        }
        return new DiffScmResult(cl.toString(), consumer.getChangedFiles(), consumer.getDifferences(), consumer.getPatch());
    }
    
    public static Commandline createCommandLine(final StarteamScmProviderRepository repo, final ScmFileSet workingDirectory, final ScmVersion startLabel, final ScmVersion endLabel) throws ScmException {
        final List<String> args = new ArrayList<String>();
        args.add("-filter");
        args.add("M");
        if (startLabel != null && StringUtils.isNotEmpty(startLabel.getName())) {
            args.add("-vl");
            args.add(startLabel.getName());
        }
        if (endLabel != null && StringUtils.isNotEmpty(endLabel.getName())) {
            args.add("-vl");
            args.add(endLabel.getName());
        }
        if (endLabel != null && (startLabel == null || StringUtils.isEmpty(startLabel.getName()))) {
            throw new ScmException("Missing start label.");
        }
        StarteamCommandLineUtils.addEOLOption(args);
        return StarteamCommandLineUtils.createStarteamCommandLine("diff", args, workingDirectory, repo);
    }
}
