// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.tag;

import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class StarteamTagCommand extends AbstractTagCommand implements StarteamCommand
{
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String tag, final String message) throws ScmException {
        return this.executeTagCommand(repo, fileSet, tag, new ScmTagParameters(message));
    }
    
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String tag, final ScmTagParameters scmTagParameters) throws ScmException {
        if (fileSet.getFileList().isEmpty()) {
            throw new ScmException("This provider doesn't support tagging subsets of a directory");
        }
        if (tag == null || tag.trim().length() == 0) {
            throw new ScmException("tag must be specified");
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Working directory: " + fileSet.getBasedir().getAbsolutePath());
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final StarteamTagConsumer consumer = new StarteamTagConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final Commandline cl = createCommandLine(repository, fileSet.getBasedir(), tag);
        final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new TagScmResult(cl.toString(), "The starteam command failed.", stderr.getOutput(), false);
        }
        return new TagScmResult(cl.toString(), consumer.getTaggedFiles());
    }
    
    public static Commandline createCommandLine(final StarteamScmProviderRepository repo, final File workingDirectory, final String tag) throws ScmException {
        final Commandline cl = StarteamCommandLineUtils.createStarteamBaseCommandLine("label", repo);
        cl.createArg().setValue("-p");
        cl.createArg().setValue(repo.getFullUrl());
        cl.createArg().setValue("-nl");
        cl.createArg().setValue(tag);
        cl.createArg().setValue("-b");
        return cl;
    }
}
