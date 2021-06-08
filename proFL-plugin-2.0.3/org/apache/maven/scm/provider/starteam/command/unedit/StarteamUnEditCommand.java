// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.unedit;

import java.util.ArrayList;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.command.unedit.UnEditScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.unedit.AbstractUnEditCommand;

public class StarteamUnEditCommand extends AbstractUnEditCommand implements StarteamCommand
{
    @Override
    protected ScmResult executeUnEditCommand(final ScmProviderRepository repo, final ScmFileSet fileSet) throws ScmException {
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Working directory: " + fileSet.getBasedir().getAbsolutePath());
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final StarteamUnEditConsumer consumer = new StarteamUnEditConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final List<File> unlockFiles = fileSet.getFileList();
        if (unlockFiles.size() == 0) {
            final Commandline cl = createCommandLine(repository, fileSet);
            final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
            if (exitCode != 0) {
                return new UnEditScmResult(cl.toString(), "The starteam command failed.", stderr.getOutput(), false);
            }
        }
        else {
            for (int i = 0; i < unlockFiles.size(); ++i) {
                final ScmFileSet unlockFile = new ScmFileSet(fileSet.getBasedir(), unlockFiles.get(i));
                final Commandline cl2 = createCommandLine(repository, unlockFile);
                final int exitCode2 = StarteamCommandLineUtils.executeCommandline(cl2, consumer, stderr, this.getLogger());
                if (exitCode2 != 0) {
                    return new UnEditScmResult(cl2.toString(), "The starteam command failed.", stderr.getOutput(), false);
                }
            }
        }
        return new UnEditScmResult(null, consumer.getUnEditFiles());
    }
    
    public static Commandline createCommandLine(final StarteamScmProviderRepository repo, final ScmFileSet dirOrFile) {
        final List<String> args = new ArrayList<String>();
        args.add("-u");
        return StarteamCommandLineUtils.createStarteamCommandLine("lck", args, dirOrFile, repo);
    }
}
