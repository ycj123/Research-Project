// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.branch;

import org.apache.maven.scm.log.ScmLogger;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.list.GitListCommand;
import org.apache.maven.scm.provider.git.gitexe.command.list.GitListConsumer;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.command.branch.BranchScmResult;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.branch.AbstractBranchCommand;

public class GitBranchCommand extends AbstractBranchCommand implements GitCommand
{
    public ScmResult executeBranchCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String branch, final String message) throws ScmException {
        if (branch == null || StringUtils.isEmpty(branch.trim())) {
            throw new ScmException("branch name must be specified");
        }
        if (!fileSet.getFileList().isEmpty()) {
            throw new ScmException("This provider doesn't support branching subsets of a directory");
        }
        final GitScmProviderRepository repository = (GitScmProviderRepository)repo;
        final Commandline cl = createCommandLine(repository, fileSet.getBasedir(), branch);
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode = GitCommandLineUtils.execute(cl, stdout, stderr, this.getLogger());
        if (exitCode != 0) {
            return new BranchScmResult(cl.toString(), "The git-branch command failed.", stderr.getOutput(), false);
        }
        if (repo.isPushChanges()) {
            final Commandline clPush = createPushCommandLine(repository, fileSet, branch);
            exitCode = GitCommandLineUtils.execute(clPush, stdout, stderr, this.getLogger());
            if (exitCode != 0) {
                return new BranchScmResult(clPush.toString(), "The git-push command failed.", stderr.getOutput(), false);
            }
        }
        final GitListConsumer listConsumer = new GitListConsumer(this.getLogger(), fileSet.getBasedir(), ScmFileStatus.TAGGED);
        final Commandline clList = GitListCommand.createCommandLine(repository, fileSet.getBasedir());
        exitCode = GitCommandLineUtils.execute(clList, listConsumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new BranchScmResult(clList.toString(), "The git-ls-files command failed.", stderr.getOutput(), false);
        }
        return new BranchScmResult(cl.toString(), listConsumer.getListedFiles());
    }
    
    public static Commandline createCommandLine(final GitScmProviderRepository repository, final File workingDirectory, final String branch) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "branch");
        cl.createArg().setValue(branch);
        return cl;
    }
    
    public static Commandline createPushCommandLine(final GitScmProviderRepository repository, final ScmFileSet fileSet, final String branch) throws ScmException {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(fileSet.getBasedir(), "push");
        cl.createArg().setValue(repository.getPushUrl());
        cl.createArg().setValue("refs/heads/" + branch);
        return cl;
    }
    
    public static String getCurrentBranch(final ScmLogger logger, final GitScmProviderRepository repository, final ScmFileSet fileSet) throws ScmException {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(fileSet.getBasedir(), "symbolic-ref");
        cl.createArg().setValue("HEAD");
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final GitCurrentBranchConsumer cbConsumer = new GitCurrentBranchConsumer(logger);
        final int exitCode = GitCommandLineUtils.execute(cl, cbConsumer, stderr, logger);
        if (exitCode != 0) {
            throw new ScmException("Detecting the current branch failed: " + stderr.getOutput());
        }
        return cbConsumer.getBranchName();
    }
}
