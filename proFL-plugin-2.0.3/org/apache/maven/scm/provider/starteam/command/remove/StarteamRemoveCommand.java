// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.remove;

import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import java.util.List;
import java.io.File;
import org.apache.maven.scm.command.remove.RemoveScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.command.checkin.StarteamCheckInConsumer;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.remove.AbstractRemoveCommand;

public class StarteamRemoveCommand extends AbstractRemoveCommand implements StarteamCommand
{
    @Override
    protected ScmResult executeRemoveCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message) throws ScmException {
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Working directory: " + fileSet.getBasedir().getAbsolutePath());
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final StarteamCheckInConsumer consumer = new StarteamCheckInConsumer(this.getLogger(), fileSet.getBasedir());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final List<File> remvoveFiles = fileSet.getFileList();
        if (remvoveFiles.size() == 0) {
            final Commandline cl = createCommandLine(repository, fileSet);
            final int exitCode = StarteamCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
            if (exitCode != 0) {
                return new RemoveScmResult(cl.toString(), "The starteam command failed.", stderr.getOutput(), false);
            }
        }
        else {
            for (int i = 0; i < remvoveFiles.size(); ++i) {
                final File fileToBeRemoved = remvoveFiles.get(i);
                final ScmFileSet scmFileSet = new ScmFileSet(fileSet.getBasedir(), fileToBeRemoved);
                final Commandline cl2 = createCommandLine(repository, scmFileSet);
                final int exitCode2 = StarteamCommandLineUtils.executeCommandline(cl2, consumer, stderr, this.getLogger());
                if (exitCode2 != 0) {
                    return new RemoveScmResult(cl2.toString(), "The starteam command failed.", stderr.getOutput(), false);
                }
            }
        }
        return new RemoveScmResult(null, consumer.getCheckedInFiles());
    }
    
    public static Commandline createCommandLine(final StarteamScmProviderRepository repo, final ScmFileSet dirOrFile) {
        return StarteamCommandLineUtils.createStarteamCommandLine("remove", null, dirOrFile, repo);
    }
}
