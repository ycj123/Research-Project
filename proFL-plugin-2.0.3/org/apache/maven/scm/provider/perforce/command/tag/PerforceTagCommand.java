// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.tag;

import org.apache.maven.scm.provider.perforce.command.PerforceInfoCommand;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.OutputStream;
import org.codehaus.plexus.util.IOUtil;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class PerforceTagCommand extends AbstractTagCommand implements PerforceCommand
{
    private String actualRepoLocation;
    private static final String NEWLINE = "\r\n";
    
    public PerforceTagCommand() {
        this.actualRepoLocation = null;
    }
    
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet files, final String tag, final String message) throws ScmException {
        return this.executeTagCommand(repo, files, tag, new ScmTagParameters(message));
    }
    
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository repo, final ScmFileSet files, final String tag, final ScmTagParameters scmTagParameters) throws ScmException {
        final PerforceScmProviderRepository prepo = (PerforceScmProviderRepository)repo;
        this.actualRepoLocation = PerforceScmProvider.getRepoPath(this.getLogger(), prepo, files.getBasedir());
        PerforceTagConsumer consumer = new PerforceTagConsumer();
        this.createLabel(repo, files, tag, consumer, false);
        if (consumer.isSuccess()) {
            this.syncLabel(repo, files, tag, consumer);
        }
        if (consumer.isSuccess() && this.shouldLock()) {
            consumer = new PerforceTagConsumer();
            this.createLabel(repo, files, tag, consumer, true);
        }
        if (consumer.isSuccess()) {
            return new TagScmResult("p4 label -i", consumer.getTagged());
        }
        return new TagScmResult("p4 label -i", "Tag failed", consumer.getOutput(), false);
    }
    
    private boolean shouldLock() {
        return Boolean.valueOf(System.getProperty("maven.scm.locktag", "true"));
    }
    
    private void syncLabel(final ScmProviderRepository repo, final ScmFileSet files, final String tag, final PerforceTagConsumer consumer) {
        final Commandline cl = createLabelsyncCommandLine((PerforceScmProviderRepository)repo, files.getBasedir(), files, tag);
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug(PerforceScmProvider.clean("Executing: " + cl.toString()));
            }
            final CommandLineUtils.StringStreamConsumer err = new CommandLineUtils.StringStreamConsumer();
            final int exitCode = CommandLineUtils.executeCommandLine(cl, consumer, err);
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
    }
    
    private void createLabel(final ScmProviderRepository repo, final ScmFileSet files, final String tag, final PerforceTagConsumer consumer, final boolean lock) {
        final Commandline cl = createLabelCommandLine((PerforceScmProviderRepository)repo, files.getBasedir());
        DataOutputStream dos = null;
        InputStreamReader isReader = null;
        InputStreamReader isReaderErr = null;
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug(PerforceScmProvider.clean("Executing: " + cl.toString()));
            }
            final Process proc = cl.execute();
            final OutputStream out = proc.getOutputStream();
            dos = new DataOutputStream(out);
            final String label = this.createLabelSpecification((PerforceScmProviderRepository)repo, tag, lock);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("LabelSpec: \r\n" + label);
            }
            dos.write(label.getBytes());
            dos.close();
            out.close();
            isReader = new InputStreamReader(proc.getInputStream());
            isReaderErr = new InputStreamReader(proc.getErrorStream());
            final BufferedReader stdout = new BufferedReader(isReader);
            final BufferedReader stderr = new BufferedReader(isReaderErr);
            String line;
            while ((line = stdout.readLine()) != null) {
                if (this.getLogger().isDebugEnabled()) {
                    this.getLogger().debug("Consuming stdout: " + line);
                }
                consumer.consumeLine(line);
            }
            while ((line = stderr.readLine()) != null) {
                if (this.getLogger().isDebugEnabled()) {
                    this.getLogger().debug("Consuming stderr: " + line);
                }
                consumer.consumeLine(line);
            }
            stderr.close();
            stdout.close();
        }
        catch (CommandLineException e) {
            if (this.getLogger().isErrorEnabled()) {
                this.getLogger().error("CommandLineException " + e.getMessage(), e);
            }
        }
        catch (IOException e2) {
            if (this.getLogger().isErrorEnabled()) {
                this.getLogger().error("IOException " + e2.getMessage(), e2);
            }
        }
        finally {
            IOUtil.close(dos);
            IOUtil.close(isReader);
            IOUtil.close(isReaderErr);
        }
    }
    
    public static Commandline createLabelCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory) {
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        command.createArg().setValue("label");
        command.createArg().setValue("-i");
        return command;
    }
    
    public static Commandline createLabelsyncCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final ScmFileSet files, final String tag) {
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        command.createArg().setValue("labelsync");
        command.createArg().setValue("-l");
        command.createArg().setValue(tag);
        final List<File> fs = files.getFileList();
        for (final File file : fs) {
            command.createArg().setValue(file.getPath());
        }
        return command;
    }
    
    public String createLabelSpecification(final PerforceScmProviderRepository repo, final String tag, final boolean lock) {
        final StringBuilder buf = new StringBuilder();
        buf.append("Label: ").append(tag).append("\r\n");
        buf.append("View: ").append(PerforceScmProvider.getCanonicalRepoPath(this.actualRepoLocation)).append("\r\n");
        String username = repo.getUser();
        if (username == null) {
            username = PerforceInfoCommand.getInfo(this.getLogger(), repo).getEntry("User name");
        }
        buf.append("Owner: ").append(username).append("\r\n");
        buf.append("Options: ").append(lock ? "" : "un").append("locked").append("\r\n");
        return buf.toString();
    }
}
