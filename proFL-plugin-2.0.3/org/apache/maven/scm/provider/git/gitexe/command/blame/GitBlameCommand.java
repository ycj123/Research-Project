// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.blame;

import java.io.File;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.blame.BlameScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.blame.AbstractBlameCommand;

public class GitBlameCommand extends AbstractBlameCommand implements GitCommand
{
    @Override
    protected ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet workingDirectory, final CommandParameters parameters) throws ScmException {
        final String filename = parameters.getString(CommandParameter.FILE);
        final Commandline cl = createCommandLine(workingDirectory.getBasedir(), filename, parameters.getBoolean(CommandParameter.IGNORE_WHITESPACE, false));
        final GitBlameConsumer consumer = new GitBlameConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final int exitCode = GitCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new BlameScmResult(cl.toString(), "The git blame command failed.", stderr.getOutput(), false);
        }
        return new BlameScmResult(cl.toString(), consumer.getLines());
    }
    
    @Override
    public BlameScmResult executeBlameCommand(final ScmProviderRepository repo, final ScmFileSet workingDirectory, final String filename) throws ScmException {
        final CommandParameters commandParameters = new CommandParameters();
        commandParameters.setString(CommandParameter.FILE, filename);
        commandParameters.setString(CommandParameter.IGNORE_WHITESPACE, Boolean.FALSE.toString());
        return (BlameScmResult)this.execute(repo, workingDirectory, commandParameters);
    }
    
    protected static Commandline createCommandLine(final File workingDirectory, final String filename, final boolean ignoreWhitespace) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "blame");
        cl.createArg().setValue("--porcelain");
        cl.createArg().setValue(filename);
        if (ignoreWhitespace) {
            cl.createArg().setValue("-w");
        }
        return cl;
    }
}
