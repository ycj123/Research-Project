// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.mkdir;

import java.util.Iterator;
import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.Os;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import java.io.IOException;
import java.io.File;
import org.codehaus.plexus.util.FileUtils;
import org.apache.maven.scm.command.mkdir.MkdirScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.mkdir.AbstractMkdirCommand;

public class SvnMkdirCommand extends AbstractMkdirCommand implements SvnCommand
{
    @Override
    protected MkdirScmResult executeMkdirCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final String message, final boolean createInLocal) throws ScmException {
        final File messageFile = FileUtils.createTempFile("maven-scm-", ".commit", null);
        try {
            FileUtils.fileWrite(messageFile.getAbsolutePath(), message);
        }
        catch (IOException ex) {
            return new MkdirScmResult(null, "Error while making a temporary file for the mkdir message: " + ex.getMessage(), null, false);
        }
        final Commandline cl = createCommandLine((SvnScmProviderRepository)repository, fileSet, messageFile, createInLocal);
        final SvnMkdirConsumer consumer = new SvnMkdirConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + SvnCommandLineUtils.cryptPassword(cl));
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        int exitCode;
        try {
            exitCode = SvnCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
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
            return new MkdirScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        if (createInLocal) {
            return new MkdirScmResult(cl.toString(), consumer.getCreatedDirs());
        }
        return new MkdirScmResult(cl.toString(), Integer.toString(consumer.getRevision()));
    }
    
    protected static Commandline createCommandLine(final SvnScmProviderRepository repository, final ScmFileSet fileSet, final File messageFile, final boolean createInLocal) {
        if (!fileSet.getBasedir().exists() && !createInLocal) {
            fileSet.getBasedir().mkdirs();
        }
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(fileSet.getBasedir(), repository);
        cl.createArg().setValue("mkdir");
        final Iterator<File> it = fileSet.getFileList().iterator();
        String dirPath = it.next().getPath();
        if (dirPath != null && Os.isFamily("dos")) {
            dirPath = StringUtils.replace(dirPath, "\\", "/");
        }
        if (!createInLocal) {
            cl.createArg().setValue(repository.getUrl() + "/" + dirPath);
            if (messageFile != null) {
                cl.createArg().setValue("--file");
                cl.createArg().setValue(messageFile.getAbsolutePath());
            }
        }
        else {
            cl.createArg().setValue(dirPath);
        }
        return cl;
    }
}
