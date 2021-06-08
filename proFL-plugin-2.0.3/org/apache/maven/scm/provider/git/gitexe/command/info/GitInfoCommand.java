// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.info;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.info.InfoScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.AbstractCommand;

public class GitInfoCommand extends AbstractCommand implements GitCommand
{
    public static final int NO_REVISION_LENGTH = -1;
    
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final GitInfoConsumer consumer = new GitInfoConsumer(this.getLogger(), fileSet);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final Commandline cli = createCommandLine(repository, fileSet, parameters);
        final int exitCode = GitCommandLineUtils.execute(cli, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new InfoScmResult(cli.toString(), "The git rev-parse command failed.", stderr.getOutput(), false);
        }
        return new InfoScmResult(cli.toString(), consumer.getInfoItems());
    }
    
    public static Commandline createCommandLine(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        final Commandline cli = GitCommandLineUtils.getBaseGitCommandLine(fileSet.getBasedir(), "rev-parse");
        cli.createArg().setValue("--verify");
        final int revLength = getRevisionLength(parameters);
        if (revLength > -1) {
            cli.createArg().setValue("--short=" + revLength);
        }
        cli.createArg().setValue("HEAD");
        return cli;
    }
    
    private static int getRevisionLength(final CommandParameters parameters) throws ScmException {
        if (parameters == null) {
            return -1;
        }
        return parameters.getInt(CommandParameter.SCM_SHORT_REVISION_LENGTH, -1);
    }
}
