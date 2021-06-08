// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.tag;

import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.list.GitListCommand;
import org.apache.maven.scm.provider.git.gitexe.command.list.GitListConsumer;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import java.io.IOException;
import org.apache.maven.scm.command.tag.TagScmResult;
import java.io.File;
import org.codehaus.plexus.util.FileUtils;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class GitTagCommand extends AbstractTagCommand implements GitCommand
{
    public ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String tag, final String message) throws ScmException {
        return this.executeTagCommand(repo, fileSet, tag, new ScmTagParameters(message));
    }
    
    public ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String tag, final ScmTagParameters scmTagParameters) throws ScmException {
        if (tag == null || StringUtils.isEmpty(tag.trim())) {
            throw new ScmException("tag name must be specified");
        }
        if (!fileSet.getFileList().isEmpty()) {
            throw new ScmException("This provider doesn't support tagging subsets of a directory");
        }
        final GitScmProviderRepository repository = (GitScmProviderRepository)repo;
        final File messageFile = FileUtils.createTempFile("maven-scm-", ".commit", null);
        try {
            FileUtils.fileWrite(messageFile.getAbsolutePath(), scmTagParameters.getMessage());
        }
        catch (IOException ex) {
            return new TagScmResult(null, "Error while making a temporary file for the commit message: " + ex.getMessage(), null, false);
        }
        try {
            final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
            final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
            final Commandline clTag = createCommandLine(repository, fileSet.getBasedir(), tag, messageFile);
            int exitCode = GitCommandLineUtils.execute(clTag, stdout, stderr, this.getLogger());
            if (exitCode != 0) {
                return new TagScmResult(clTag.toString(), "The git-tag command failed.", stderr.getOutput(), false);
            }
            if (repo.isPushChanges()) {
                final Commandline clPush = createPushCommandLine(repository, fileSet, tag);
                exitCode = GitCommandLineUtils.execute(clPush, stdout, stderr, this.getLogger());
                if (exitCode != 0) {
                    return new TagScmResult(clPush.toString(), "The git-push command failed.", stderr.getOutput(), false);
                }
            }
            final GitListConsumer listConsumer = new GitListConsumer(this.getLogger(), fileSet.getBasedir(), ScmFileStatus.TAGGED);
            final Commandline clList = GitListCommand.createCommandLine(repository, fileSet.getBasedir());
            exitCode = GitCommandLineUtils.execute(clList, listConsumer, stderr, this.getLogger());
            if (exitCode != 0) {
                return new CheckOutScmResult(clList.toString(), "The git-ls-files command failed.", stderr.getOutput(), false);
            }
            return new TagScmResult(clTag.toString(), listConsumer.getListedFiles());
        }
        finally {
            try {
                FileUtils.forceDelete(messageFile);
            }
            catch (IOException ex2) {}
        }
    }
    
    public static Commandline createCommandLine(final GitScmProviderRepository repository, final File workingDirectory, final String tag, final File messageFile) {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "tag");
        cl.createArg().setValue("-F");
        cl.createArg().setValue(messageFile.getAbsolutePath());
        cl.createArg().setValue(tag);
        return cl;
    }
    
    public static Commandline createPushCommandLine(final GitScmProviderRepository repository, final ScmFileSet fileSet, final String tag) throws ScmException {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(fileSet.getBasedir(), "push");
        cl.createArg().setValue(repository.getPushUrl());
        cl.createArg().setValue("refs/tags/" + tag);
        return cl;
    }
}
