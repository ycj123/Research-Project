// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.update;

import org.apache.maven.scm.ScmBranch;
import java.io.File;
import org.apache.maven.scm.provider.git.gitexe.command.changelog.GitChangeLogCommand;
import org.apache.maven.scm.command.changelog.ChangeLogCommand;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.update.UpdateScmResultWithRevision;
import org.apache.maven.scm.provider.git.gitexe.command.diff.GitDiffCommand;
import org.apache.maven.scm.provider.git.gitexe.command.diff.GitDiffRawConsumer;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import org.apache.maven.scm.command.update.UpdateScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.update.AbstractUpdateCommand;

public class GitUpdateCommand extends AbstractUpdateCommand implements GitCommand
{
    @Override
    protected UpdateScmResult executeUpdateCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion scmVersion) throws ScmException {
        final GitScmProviderRepository repository = (GitScmProviderRepository)repo;
        if ("file".equals(repository.getFetchInfo().getProtocol()) && repository.getFetchInfo().getPath().indexOf(fileSet.getBasedir().getPath()) >= 0) {
            throw new ScmException("remote repository must not be the working directory");
        }
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final Commandline clRev = createLatestRevisionCommandLine(repository, fileSet.getBasedir(), scmVersion);
        GitLatestRevisionCommandConsumer consumerRev = new GitLatestRevisionCommandConsumer(this.getLogger());
        int exitCode = GitCommandLineUtils.execute(clRev, consumerRev, stderr, this.getLogger());
        if (exitCode != 0) {
            return new UpdateScmResult(clRev.toString(), "The git-log command failed.", stderr.getOutput(), false);
        }
        final String origSha1 = consumerRev.getLatestRevision();
        final Commandline cl = createCommandLine(repository, fileSet.getBasedir(), scmVersion);
        exitCode = GitCommandLineUtils.execute(cl, stdout, stderr, this.getLogger());
        if (exitCode != 0) {
            return new UpdateScmResult(cl.toString(), "The git-pull command failed.", stderr.getOutput(), false);
        }
        final GitDiffRawConsumer diffRawConsumer = new GitDiffRawConsumer(this.getLogger());
        final Commandline clDiffRaw = GitDiffCommand.createDiffRawCommandLine(fileSet.getBasedir(), origSha1);
        exitCode = GitCommandLineUtils.execute(clDiffRaw, diffRawConsumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new UpdateScmResult(clDiffRaw.toString(), "The git-diff --raw command failed.", stderr.getOutput(), false);
        }
        consumerRev = new GitLatestRevisionCommandConsumer(this.getLogger());
        exitCode = GitCommandLineUtils.execute(clRev, consumerRev, stderr, this.getLogger());
        if (exitCode != 0) {
            return new UpdateScmResult(clRev.toString(), "The git-log command failed.", stderr.getOutput(), false);
        }
        final String latestRevision = consumerRev.getLatestRevision();
        return new UpdateScmResultWithRevision(cl.toString(), diffRawConsumer.getChangedFiles(), latestRevision);
    }
    
    @Override
    protected ChangeLogCommand getChangeLogCommand() {
        final GitChangeLogCommand changelogCmd = new GitChangeLogCommand();
        changelogCmd.setLogger(this.getLogger());
        return changelogCmd;
    }
    
    public static Commandline createCommandLine(final GitScmProviderRepository repository, final File workingDirectory, final ScmVersion scmVersion) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "pull");
        cl.createArg().setLine(repository.getFetchUrl());
        if (scmVersion instanceof ScmBranch) {
            cl.createArg().setLine(scmVersion.getName());
        }
        else {
            cl.createArg().setLine("master");
        }
        return cl;
    }
    
    public static Commandline createLatestRevisionCommandLine(final GitScmProviderRepository repository, final File workingDirectory, final ScmVersion scmVersion) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "log");
        cl.createArg().setValue("-n1");
        cl.createArg().setValue("--date-order");
        if (scmVersion != null && scmVersion instanceof ScmBranch && scmVersion.getName() != null && scmVersion.getName().length() > 0) {
            cl.createArg().setValue(scmVersion.getName());
        }
        else {
            cl.createArg().setValue("master");
        }
        return cl;
    }
}
