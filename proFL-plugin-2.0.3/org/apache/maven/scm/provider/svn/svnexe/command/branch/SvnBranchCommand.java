// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.branch;

import org.apache.maven.scm.provider.svn.SvnCommandUtils;
import org.apache.maven.scm.provider.svn.SvnTagBranchUtils;
import org.apache.maven.scm.ScmBranch;
import java.util.Iterator;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmFile;
import java.util.ArrayList;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import java.io.IOException;
import org.apache.maven.scm.command.branch.BranchScmResult;
import java.io.File;
import org.codehaus.plexus.util.FileUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmBranchParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.branch.AbstractBranchCommand;

public class SvnBranchCommand extends AbstractBranchCommand implements SvnCommand
{
    public ScmResult executeBranchCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String branch, final ScmBranchParameters scmBranchParameters) throws ScmException {
        if (branch == null || StringUtils.isEmpty(branch.trim())) {
            throw new ScmException("branch name must be specified");
        }
        if (!fileSet.getFileList().isEmpty()) {
            throw new ScmException("This provider doesn't support branching subsets of a directory");
        }
        final SvnScmProviderRepository repository = (SvnScmProviderRepository)repo;
        final File messageFile = FileUtils.createTempFile("maven-scm-", ".commit", null);
        try {
            FileUtils.fileWrite(messageFile.getAbsolutePath(), scmBranchParameters.getMessage());
        }
        catch (IOException ex) {
            return new BranchScmResult(null, "Error while making a temporary file for the commit message: " + ex.getMessage(), null, false);
        }
        final Commandline cl = createCommandLine(repository, fileSet.getBasedir(), branch, messageFile, scmBranchParameters);
        final CommandLineUtils.StringStreamConsumer stdout = new CommandLineUtils.StringStreamConsumer();
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + SvnCommandLineUtils.cryptPassword(cl));
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        int exitCode;
        try {
            exitCode = SvnCommandLineUtils.execute(cl, stdout, stderr, this.getLogger());
        }
        catch (CommandLineException ex2) {
            throw new ScmException("Error while executing command.", ex2);
        }
        finally {
            try {
                FileUtils.forceDelete(messageFile);
            }
            catch (IOException ex3) {}
        }
        if (exitCode != 0) {
            return new BranchScmResult(cl.toString(), "The svn branch command failed.", stderr.getOutput(), false);
        }
        final List<ScmFile> fileList = new ArrayList<ScmFile>();
        List<File> files = null;
        try {
            final List<File> listFiles = files = (List<File>)FileUtils.getFiles(fileSet.getBasedir(), "**", "**/.svn/**", false);
        }
        catch (IOException e) {
            throw new ScmException("Error while executing command.", e);
        }
        for (final File f : files) {
            fileList.add(new ScmFile(f.getPath(), ScmFileStatus.TAGGED));
        }
        return new BranchScmResult(cl.toString(), fileList);
    }
    
    public ScmResult executeBranchCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String branch, final String message) throws ScmException {
        final ScmBranchParameters scmBranchParameters = new ScmBranchParameters(message);
        return this.executeBranchCommand(repo, fileSet, branch, scmBranchParameters);
    }
    
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final File workingDirectory, final String branch, final File messageFile) {
        final ScmBranchParameters scmBranchParameters = new ScmBranchParameters();
        scmBranchParameters.setRemoteBranching(false);
        return createCommandLine(repository, workingDirectory, branch, messageFile, scmBranchParameters);
    }
    
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final File workingDirectory, final String branch, final File messageFile, final ScmBranchParameters scmBranchParameters) {
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(workingDirectory, repository);
        cl.createArg().setValue("copy");
        cl.createArg().setValue("--file");
        cl.createArg().setValue(messageFile.getAbsolutePath());
        if (scmBranchParameters != null && scmBranchParameters.isRemoteBranching()) {
            if (StringUtils.isNotBlank(scmBranchParameters.getScmRevision())) {
                cl.createArg().setValue("--revision");
                cl.createArg().setValue(scmBranchParameters.getScmRevision());
            }
            cl.createArg().setValue(repository.getUrl());
        }
        else {
            cl.createArg().setValue(".");
        }
        final String branchUrl = SvnTagBranchUtils.resolveBranchUrl(repository, new ScmBranch(branch));
        cl.createArg().setValue(SvnCommandUtils.fixUrl(branchUrl, repository.getUser()));
        return cl;
    }
}
