// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.checkin;

import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import java.io.IOException;
import java.io.File;
import org.codehaus.plexus.util.FileUtils;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class SvnCheckInCommand extends AbstractCheckInCommand implements SvnCommand
{
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final String message, final ScmVersion version) throws ScmException {
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            throw new ScmException("This provider command can't handle tags.");
        }
        final File messageFile = FileUtils.createTempFile("maven-scm-", ".commit", null);
        try {
            FileUtils.fileWrite(messageFile.getAbsolutePath(), message);
        }
        catch (IOException ex) {
            return new CheckInScmResult(null, "Error while making a temporary file for the commit message: " + ex.getMessage(), null, false);
        }
        final Commandline cl = createCommandLine((SvnScmProviderRepository)repo, fileSet, messageFile);
        final SvnCheckInConsumer consumer = new SvnCheckInConsumer(this.getLogger(), fileSet.getBasedir());
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
            return new CheckInScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        return new CheckInScmResult(cl.toString(), consumer.getCheckedInFiles(), Integer.toString(consumer.getRevision()));
    }
    
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final ScmFileSet fileSet, final File messageFile) throws ScmException {
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(fileSet.getBasedir(), repository);
        cl.createArg().setValue("commit");
        cl.createArg().setValue("--file");
        cl.createArg().setValue(messageFile.getAbsolutePath());
        try {
            SvnCommandLineUtils.addTarget(cl, fileSet.getFileList());
        }
        catch (IOException e) {
            throw new ScmException("Can't create the targets file", e);
        }
        return cl;
    }
}
