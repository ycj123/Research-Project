// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.starteam.command.changelog;

import java.util.List;
import org.apache.maven.scm.provider.starteam.command.StarteamCommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.starteam.repository.StarteamScmProviderRepository;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.starteam.command.StarteamCommand;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class StarteamChangeLogCommand extends AbstractChangeLogCommand implements StarteamCommand
{
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        if ((branch != null || StringUtils.isNotEmpty((branch == null) ? null : branch.getName())) && this.getLogger().isWarnEnabled()) {
            this.getLogger().warn("This provider doesn't support changelog with on a given branch.");
        }
        final StarteamScmProviderRepository repository = (StarteamScmProviderRepository)repo;
        final Commandline cl = createCommandLine(repository, fileSet, startDate);
        final StarteamChangeLogConsumer consumer = new StarteamChangeLogConsumer(fileSet.getBasedir(), this.getLogger(), startDate, endDate, datePattern);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing command.", ex);
        }
        if (exitCode != 0) {
            return new ChangeLogScmResult(cl.toString(), "The 'stcmd' command failed.", stderr.getOutput(), false);
        }
        return new ChangeLogScmResult(cl.toString(), new ChangeLogSet(consumer.getModifications(), startDate, endDate));
    }
    
    public static Commandline createCommandLine(final StarteamScmProviderRepository repo, final ScmFileSet workingDirectory, final Date startDate) {
        return StarteamCommandLineUtils.createStarteamCommandLine("hist", null, workingDirectory, repo);
    }
}
