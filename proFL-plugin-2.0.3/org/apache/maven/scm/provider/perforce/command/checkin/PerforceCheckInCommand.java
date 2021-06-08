// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.checkin;

import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.util.HashSet;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class PerforceCheckInCommand extends AbstractCheckInCommand implements PerforceCommand
{
    private static final String NEWLINE = "\r\n";
    
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository repo, final ScmFileSet files, final String message, final ScmVersion version) throws ScmException {
        final Commandline cl = createCommandLine((PerforceScmProviderRepository)repo, files.getBasedir());
        final PerforceCheckInConsumer consumer = new PerforceCheckInConsumer();
        try {
            final String jobs = System.getProperty("maven.scm.jobs");
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug(PerforceScmProvider.clean("Executing " + cl.toString()));
            }
            final PerforceScmProviderRepository prepo = (PerforceScmProviderRepository)repo;
            final String changes = createChangeListSpecification(prepo, files, message, PerforceScmProvider.getRepoPath(this.getLogger(), prepo, files.getBasedir()), jobs);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Sending changelist:\n" + changes);
            }
            final CommandLineUtils.StringStreamConsumer err = new CommandLineUtils.StringStreamConsumer();
            final int exitCode = CommandLineUtils.executeCommandLine(cl, new ByteArrayInputStream(changes.getBytes()), consumer, err);
            if (exitCode != 0) {
                final String cmdLine = CommandLineUtils.toString(cl.getCommandline());
                final StringBuilder msg = new StringBuilder("Exit code: " + exitCode + " - " + err.getOutput());
                msg.append('\n');
                msg.append("Command line was:" + cmdLine);
                throw new CommandLineException(msg.toString());
            }
        }
        catch (CommandLineException e) {
            if (this.getLogger().isErrorEnabled()) {
                this.getLogger().error("CommandLineException " + e.getMessage(), e);
            }
        }
        return new CheckInScmResult(cl.toString(), consumer.isSuccess() ? "Checkin successful" : "Unable to submit", consumer.getOutput(), consumer.isSuccess());
    }
    
    public static Commandline createCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory) {
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        command.createArg().setValue("submit");
        command.createArg().setValue("-i");
        return command;
    }
    
    public static String createChangeListSpecification(final PerforceScmProviderRepository repo, final ScmFileSet files, final String msg, final String canonicalPath, final String jobs) {
        final StringBuilder buf = new StringBuilder();
        buf.append("Change: new").append("\r\n").append("\r\n");
        buf.append("Description:").append("\r\n").append("\t").append(msg).append("\r\n").append("\r\n");
        if (jobs != null && jobs.length() != 0) {
            buf.append("Jobs:").append("\r\n").append("\t").append(jobs).append("\r\n").append("\r\n");
        }
        buf.append("Files:").append("\r\n");
        try {
            final Set<String> dupes = new HashSet<String>();
            final File workingDir = files.getBasedir();
            final String candir = workingDir.getCanonicalPath();
            final List<File> fs = files.getFileList();
            for (int i = 0; i < fs.size(); ++i) {
                File file = null;
                if (fs.get(i).isAbsolute()) {
                    file = new File(fs.get(i).getPath());
                }
                else {
                    file = new File(workingDir, fs.get(i).getPath());
                }
                String canfile = file.getCanonicalPath();
                if (dupes.contains(canfile)) {
                    System.err.println("Skipping duplicate file: " + file);
                }
                else {
                    dupes.add(canfile);
                    if (canfile.startsWith(candir)) {
                        canfile = canfile.substring(candir.length() + 1);
                    }
                    buf.append("\t").append(canonicalPath).append("/").append(canfile.replace('\\', '/')).append("\r\n");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
}
