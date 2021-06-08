// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.tag;

import org.apache.maven.scm.provider.svn.SvnCommandUtils;
import org.apache.maven.scm.provider.svn.SvnTagBranchUtils;
import org.apache.maven.scm.ScmTag;
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
import org.apache.maven.scm.command.tag.TagScmResult;
import java.io.File;
import org.codehaus.plexus.util.FileUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class SvnTagCommand extends AbstractTagCommand implements SvnCommand
{
    public ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String tag, final String message) throws ScmException {
        final ScmTagParameters scmTagParameters = new ScmTagParameters(message);
        scmTagParameters.setRemoteTagging(false);
        return this.executeTagCommand(repo, fileSet, tag, scmTagParameters);
    }
    
    public ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String tag, ScmTagParameters scmTagParameters) throws ScmException {
        if (scmTagParameters == null) {
            this.getLogger().debug("SvnTagCommand :: scmTagParameters is null create an empty one");
            scmTagParameters = new ScmTagParameters();
            scmTagParameters.setRemoteTagging(false);
        }
        else {
            this.getLogger().debug("SvnTagCommand :: scmTagParameters.remoteTagging : " + scmTagParameters.isRemoteTagging());
        }
        if (tag == null || StringUtils.isEmpty(tag.trim())) {
            throw new ScmException("tag must be specified");
        }
        if (!fileSet.getFileList().isEmpty()) {
            throw new ScmException("This provider doesn't support tagging subsets of a directory");
        }
        final SvnScmProviderRepository repository = (SvnScmProviderRepository)repo;
        final File messageFile = FileUtils.createTempFile("maven-scm-", ".commit", null);
        try {
            FileUtils.fileWrite(messageFile.getAbsolutePath(), (scmTagParameters == null) ? "" : scmTagParameters.getMessage());
        }
        catch (IOException ex) {
            return new TagScmResult(null, "Error while making a temporary file for the commit message: " + ex.getMessage(), null, false);
        }
        final Commandline cl = createCommandLine(repository, fileSet.getBasedir(), tag, messageFile, scmTagParameters);
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
            return new TagScmResult(cl.toString(), "The svn tag command failed.", stderr.getOutput(), false);
        }
        final List<ScmFile> fileList = new ArrayList<ScmFile>();
        List<File> files = null;
        try {
            if (StringUtils.isNotEmpty(fileSet.getExcludes())) {
                final List<File> list = files = (List<File>)FileUtils.getFiles(fileSet.getBasedir(), StringUtils.isEmpty(fileSet.getIncludes()) ? "**" : fileSet.getIncludes(), fileSet.getExcludes() + ",**/.svn/**", false);
            }
            else {
                final List<File> list = files = (List<File>)FileUtils.getFiles(fileSet.getBasedir(), StringUtils.isEmpty(fileSet.getIncludes()) ? "**" : fileSet.getIncludes(), "**/.svn/**", false);
            }
        }
        catch (IOException e) {
            throw new ScmException("Error while executing command.", e);
        }
        for (final File f : files) {
            fileList.add(new ScmFile(f.getPath(), ScmFileStatus.TAGGED));
        }
        return new TagScmResult(cl.toString(), fileList);
    }
    
    @Deprecated
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final File workingDirectory, final String tag, final File messageFile) {
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(workingDirectory, repository);
        cl.createArg().setValue("copy");
        cl.createArg().setValue("--file");
        cl.createArg().setValue(messageFile.getAbsolutePath());
        cl.createArg().setValue(".");
        final String tagUrl = SvnTagBranchUtils.resolveTagUrl(repository, new ScmTag(tag));
        cl.createArg().setValue(SvnCommandUtils.fixUrl(tagUrl, repository.getUser()));
        return cl;
    }
    
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final File workingDirectory, final String tag, final File messageFile, final ScmTagParameters scmTagParameters) {
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(workingDirectory, repository);
        cl.createArg().setValue("copy");
        cl.createArg().setValue("--file");
        cl.createArg().setValue(messageFile.getAbsolutePath());
        cl.createArg().setValue("--parents");
        if (scmTagParameters != null && scmTagParameters.getScmRevision() != null) {
            cl.createArg().setValue("--revision");
            cl.createArg().setValue(scmTagParameters.getScmRevision());
        }
        if (scmTagParameters != null && scmTagParameters.isRemoteTagging()) {
            cl.createArg().setValue(SvnCommandUtils.fixUrl(repository.getUrl(), repository.getUser()));
        }
        else {
            cl.createArg().setValue(".");
        }
        final String tagUrl = SvnTagBranchUtils.resolveTagUrl(repository, new ScmTag(tag));
        cl.createArg().setValue(SvnCommandUtils.fixUrl(tagUrl, repository.getUser()));
        return cl;
    }
}
