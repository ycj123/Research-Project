// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.edit;

import java.util.Iterator;
import java.io.IOException;
import java.io.File;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.edit.EditScmResult;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.edit.AbstractEditCommand;

public class PerforceEditCommand extends AbstractEditCommand implements PerforceCommand
{
    @Override
    protected ScmResult executeEditCommand(final ScmProviderRepository repo, final ScmFileSet files) throws ScmException {
        final Commandline cl = createCommandLine((PerforceScmProviderRepository)repo, files.getBasedir(), files);
        final PerforceEditConsumer consumer = new PerforceEditConsumer();
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug(PerforceScmProvider.clean("Executing " + cl.toString()));
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
        if (consumer.isSuccess()) {
            return new EditScmResult(cl.toString(), consumer.getEdits());
        }
        return new EditScmResult(cl.toString(), "Unable to edit file(s)", consumer.getErrorMessage(), false);
    }
    
    public static Commandline createCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final ScmFileSet files) throws ScmException {
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        command.createArg().setValue("edit");
        try {
            final String candir = workingDirectory.getCanonicalPath();
            for (final File f : files.getFileList()) {
                File file = null;
                if (f.isAbsolute()) {
                    file = new File(f.getPath());
                }
                else {
                    file = new File(workingDirectory, f.getPath());
                }
                String canfile = file.getCanonicalPath();
                if (canfile.startsWith(candir)) {
                    canfile = canfile.substring(candir.length() + 1);
                }
                command.createArg().setValue(canfile);
            }
        }
        catch (IOException e) {
            throw new ScmException(e.getMessage(), e);
        }
        return command;
    }
}
