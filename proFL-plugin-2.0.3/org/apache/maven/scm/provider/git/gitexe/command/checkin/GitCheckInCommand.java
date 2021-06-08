// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.checkin;

import org.apache.maven.scm.provider.git.util.GitUtil;
import org.apache.maven.scm.provider.git.gitexe.command.branch.GitBranchCommand;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmException;
import java.util.Iterator;
import java.util.List;
import java.net.URI;
import org.codehaus.plexus.util.cli.Commandline;
import org.mudebug.prapr.reloc.commons.io.FilenameUtils;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.status.GitStatusConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.status.GitStatusCommand;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.apache.maven.scm.provider.git.gitexe.command.add.GitAddCommand;
import java.io.IOException;
import java.io.File;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class GitCheckInCommand extends AbstractCheckInCommand implements GitCommand
{
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final ScmVersion version) throws ScmException {
        final GitScmProviderRepository repository = (GitScmProviderRepository)repo;
        CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        final File messageFile = FileUtils.createTempFile("maven-scm-", ".commit", null);
        try {
            FileUtils.fileWrite(messageFile.getAbsolutePath(), message);
        }
        catch (IOException ex) {
            return new CheckInScmResult(null, "Error while making a temporary file for the commit message: " + ex.getMessage(), null, false);
        }
        try {
            if (!fileSet.getFileList().isEmpty()) {
                final Commandline clAdd = GitAddCommand.createCommandLine(fileSet.getBasedir(), fileSet.getFileList());
                final int exitCode = GitCommandLineUtils.execute(clAdd, stdout, stderr, this.getLogger());
                if (exitCode != 0) {
                    return new CheckInScmResult(clAdd.toString(), "The git-add command failed.", stderr.getOutput(), false);
                }
            }
            final Commandline clRevparse = GitStatusCommand.createRevparseShowToplevelCommand(fileSet);
            stdout = new CommandLineUtils.StringStreamConsumer();
            stderr = new CommandLineUtils.StringStreamConsumer();
            URI relativeRepositoryPath = null;
            int exitCode = GitCommandLineUtils.execute(clRevparse, stdout, stderr, this.getLogger());
            if (exitCode != 0) {
                if (this.getLogger().isInfoEnabled()) {
                    this.getLogger().info("Could not resolve toplevel");
                }
            }
            else {
                relativeRepositoryPath = GitStatusConsumer.resolveURI(stdout.getOutput().trim(), fileSet.getBasedir().toURI());
            }
            final Commandline clStatus = GitStatusCommand.createCommandLine(repository, fileSet);
            final GitStatusConsumer statusConsumer = new GitStatusConsumer(this.getLogger(), fileSet.getBasedir(), relativeRepositoryPath);
            exitCode = GitCommandLineUtils.execute(clStatus, statusConsumer, stderr, this.getLogger());
            if (exitCode != 0 && this.getLogger().isInfoEnabled()) {
                this.getLogger().info("nothing added to commit but untracked files present (use \"git add\" to track)");
            }
            if (statusConsumer.getChangedFiles().isEmpty()) {
                return new CheckInScmResult(null, statusConsumer.getChangedFiles());
            }
            final Commandline clCommit = createCommitCommandLine(repository, fileSet, messageFile);
            exitCode = GitCommandLineUtils.execute(clCommit, stdout, stderr, this.getLogger());
            if (exitCode != 0) {
                return new CheckInScmResult(clCommit.toString(), "The git-commit command failed.", stderr.getOutput(), false);
            }
            if (repo.isPushChanges()) {
                final Commandline cl = createPushCommandLine(this.getLogger(), repository, fileSet, version);
                exitCode = GitCommandLineUtils.execute(cl, stdout, stderr, this.getLogger());
                if (exitCode != 0) {
                    return new CheckInScmResult(cl.toString(), "The git-push command failed.", stderr.getOutput(), false);
                }
            }
            final List<ScmFile> checkedInFiles = new ArrayList<ScmFile>(statusConsumer.getChangedFiles().size());
            for (final ScmFile changedFile : statusConsumer.getChangedFiles()) {
                final ScmFile scmfile = new ScmFile(changedFile.getPath(), ScmFileStatus.CHECKED_IN);
                if (fileSet.getFileList().isEmpty()) {
                    checkedInFiles.add(scmfile);
                }
                else {
                    for (final File f : fileSet.getFileList()) {
                        if (FilenameUtils.separatorsToUnix(f.getPath()).equals(scmfile.getPath())) {
                            checkedInFiles.add(scmfile);
                        }
                    }
                }
            }
            return new CheckInScmResult(clCommit.toString(), checkedInFiles);
        }
        finally {
            try {
                FileUtils.forceDelete(messageFile);
            }
            catch (IOException ex2) {}
        }
    }
    
    public static Commandline createPushCommandLine(final ScmLogger logger, final GitScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion version) throws ScmException {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(fileSet.getBasedir(), "push");
        final String branch = GitBranchCommand.getCurrentBranch(logger, repository, fileSet);
        if (branch == null || branch.length() == 0) {
            throw new ScmException("Could not detect the current branch. Don't know where I should push to!");
        }
        cl.createArg().setValue(repository.getPushUrl());
        cl.createArg().setValue("refs/heads/" + branch + ":" + "refs/heads/" + branch);
        return cl;
    }
    
    public static Commandline createCommitCommandLine(final GitScmProviderRepository repository, final ScmFileSet fileSet, final File messageFile) throws ScmException {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(fileSet.getBasedir(), "commit");
        cl.createArg().setValue("--verbose");
        cl.createArg().setValue("-F");
        cl.createArg().setValue(messageFile.getAbsolutePath());
        if (fileSet.getFileList().isEmpty()) {
            cl.createArg().setValue("-a");
        }
        else {
            GitCommandLineUtils.addTarget(cl, fileSet.getFileList());
        }
        if (GitUtil.getSettings().isCommitNoVerify()) {
            cl.createArg().setValue("--no-verify");
        }
        return cl;
    }
}
