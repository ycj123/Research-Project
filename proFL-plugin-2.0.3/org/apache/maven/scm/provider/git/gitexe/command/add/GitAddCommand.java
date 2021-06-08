// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.add;

import java.util.Collections;
import org.codehaus.plexus.util.Os;
import java.util.Iterator;
import java.util.List;
import java.net.URI;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.add.AddScmResult;
import org.mudebug.prapr.reloc.commons.io.FilenameUtils;
import java.io.File;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.status.GitStatusConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.git.gitexe.command.status.GitStatusCommand;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.add.AbstractAddCommand;

public class GitAddCommand extends AbstractAddCommand implements GitCommand
{
    @Override
    protected ScmResult executeAddCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final boolean binary) throws ScmException {
        final GitScmProviderRepository repository = (GitScmProviderRepository)repo;
        if (fileSet.getFileList().isEmpty()) {
            throw new ScmException("You must provide at least one file/directory to add");
        }
        final AddScmResult result = this.executeAddFileSet(fileSet);
        if (result != null) {
            return result;
        }
        final Commandline clRevparse = GitStatusCommand.createRevparseShowToplevelCommand(fileSet);
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
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
        stderr = new CommandLineUtils.StringStreamConsumer();
        exitCode = GitCommandLineUtils.execute(clStatus, statusConsumer, stderr, this.getLogger());
        if (exitCode != 0 && this.getLogger().isInfoEnabled()) {
            this.getLogger().info("nothing added to commit but untracked files present (use \"git add\" to track)");
        }
        final List<ScmFile> changedFiles = new ArrayList<ScmFile>();
        for (final ScmFile scmfile : statusConsumer.getChangedFiles()) {
            for (final File f : fileSet.getFileList()) {
                if (FilenameUtils.separatorsToUnix(f.getPath()).equals(scmfile.getPath())) {
                    changedFiles.add(scmfile);
                }
            }
        }
        final Commandline cl = createCommandLine(fileSet.getBasedir(), fileSet.getFileList());
        return new AddScmResult(cl.toString(), changedFiles);
    }
    
    public static Commandline createCommandLine(final File workingDirectory, final List<File> files) throws ScmException {
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "add");
        cl.createArg().setValue("--");
        GitCommandLineUtils.addTarget(cl, files);
        return cl;
    }
    
    private AddScmResult executeAddFileSet(final ScmFileSet fileSet) throws ScmException {
        final File workingDirectory = fileSet.getBasedir();
        final List<File> files = fileSet.getFileList();
        if (Os.isFamily("windows")) {
            for (final File file : files) {
                final AddScmResult result = this.executeAddFiles(workingDirectory, Collections.singletonList(file));
                if (result != null) {
                    return result;
                }
            }
        }
        else {
            final AddScmResult result2 = this.executeAddFiles(workingDirectory, files);
            if (result2 != null) {
                return result2;
            }
        }
        return null;
    }
    
    private AddScmResult executeAddFiles(final File workingDirectory, final List<File> files) throws ScmException {
        final Commandline cl = createCommandLine(workingDirectory, files);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        final int exitCode = GitCommandLineUtils.execute(cl, stdout, stderr, this.getLogger());
        if (exitCode != 0) {
            return new AddScmResult(cl.toString(), "The git-add command failed.", stderr.getOutput(), false);
        }
        return null;
    }
}
