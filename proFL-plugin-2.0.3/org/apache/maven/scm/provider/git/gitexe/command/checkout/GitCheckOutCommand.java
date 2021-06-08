// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.checkout;

import org.apache.maven.scm.ScmTag;
import org.apache.maven.scm.ScmBranch;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.remoteinfo.RemoteInfoScmResult;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.list.GitListCommand;
import org.apache.maven.scm.provider.git.gitexe.command.list.GitListConsumer;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.provider.git.gitexe.command.remoteinfo.GitRemoteInfoCommand;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import java.io.File;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.checkout.AbstractCheckOutCommand;

public class GitCheckOutCommand extends AbstractCheckOutCommand implements GitCommand
{
    @Override
    protected CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion version, final boolean recursive) throws ScmException {
        final GitScmProviderRepository repository = (GitScmProviderRepository)repo;
        if ("file".equals(repository.getFetchInfo().getProtocol()) && repository.getFetchInfo().getPath().indexOf(fileSet.getBasedir().getPath()) >= 0) {
            throw new ScmException("remote repository must not be the working directory");
        }
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        String lastCommandLine = "git-nothing-to-do";
        if (!fileSet.getBasedir().exists() || !new File(fileSet.getBasedir(), ".git").exists()) {
            if (fileSet.getBasedir().exists()) {
                fileSet.getBasedir().delete();
            }
            final Commandline clClone = this.createCloneCommand(repository, fileSet.getBasedir(), version);
            final int exitCode = GitCommandLineUtils.execute(clClone, stdout, stderr, this.getLogger());
            if (exitCode != 0) {
                return new CheckOutScmResult(clClone.toString(), "The git-clone command failed.", stderr.getOutput(), false);
            }
            lastCommandLine = clClone.toString();
        }
        final GitRemoteInfoCommand gitRemoteInfoCommand = new GitRemoteInfoCommand();
        gitRemoteInfoCommand.setLogger(this.getLogger());
        final RemoteInfoScmResult result = gitRemoteInfoCommand.executeRemoteInfoCommand(repository, null, null);
        if (fileSet.getBasedir().exists() && new File(fileSet.getBasedir(), ".git").exists() && result.getBranches().size() > 0) {
            final Commandline clPull = this.createPullCommand(repository, fileSet.getBasedir(), version);
            int exitCode = GitCommandLineUtils.execute(clPull, stdout, stderr, this.getLogger());
            if (exitCode != 0) {
                return new CheckOutScmResult(clPull.toString(), "The git-pull command failed.", stderr.getOutput(), false);
            }
            lastCommandLine = clPull.toString();
            final Commandline clCheckout = createCommandLine(repository, fileSet.getBasedir(), version);
            exitCode = GitCommandLineUtils.execute(clCheckout, stdout, stderr, this.getLogger());
            if (exitCode != 0) {
                return new CheckOutScmResult(clCheckout.toString(), "The git-checkout command failed.", stderr.getOutput(), false);
            }
            lastCommandLine = clCheckout.toString();
        }
        final GitListConsumer listConsumer = new GitListConsumer(this.getLogger(), fileSet.getBasedir(), ScmFileStatus.CHECKED_IN);
        final Commandline clList = GitListCommand.createCommandLine(repository, fileSet.getBasedir());
        int exitCode = GitCommandLineUtils.execute(clList, listConsumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new CheckOutScmResult(clList.toString(), "The git-ls-files command failed.", stderr.getOutput(), false);
        }
        return new CheckOutScmResult(lastCommandLine, listConsumer.getListedFiles());
    }
    
    public static Commandline createCommandLine(final GitScmProviderRepository repository, final File workingDirectory, final ScmVersion version) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "checkout");
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            cl.createArg().setValue(version.getName());
        }
        return cl;
    }
    
    private Commandline createCloneCommand(final GitScmProviderRepository repository, final File workingDirectory, final ScmVersion version) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory.getParentFile(), "clone");
        if (version != null && version instanceof ScmBranch) {
            cl.createArg().setValue("--branch");
            cl.createArg().setValue(version.getName());
        }
        cl.createArg().setValue(repository.getFetchUrl());
        cl.createArg().setFile(workingDirectory);
        return cl;
    }
    
    private Commandline createPullCommand(final GitScmProviderRepository repository, final File workingDirectory, final ScmVersion version) {
        Commandline cl;
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            if (version instanceof ScmTag) {
                cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "fetch");
                cl.createArg().setValue(repository.getFetchUrl());
            }
            else {
                cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "pull");
                cl.createArg().setValue(repository.getFetchUrl());
                cl.createArg().setValue(version.getName() + ":" + version.getName());
            }
        }
        else {
            cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "pull");
            cl.createArg().setValue(repository.getFetchUrl());
            cl.createArg().setValue("master");
        }
        return cl;
    }
}
