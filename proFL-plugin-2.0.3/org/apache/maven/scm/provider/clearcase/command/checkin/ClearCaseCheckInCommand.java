// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.checkin;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.command.checkin.CheckInScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.clearcase.command.ClearCaseCommand;
import org.apache.maven.scm.command.checkin.AbstractCheckInCommand;

public class ClearCaseCheckInCommand extends AbstractCheckInCommand implements ClearCaseCommand
{
    @Override
    protected CheckInScmResult executeCheckInCommand(final ScmProviderRepository scmProviderRepository, final ScmFileSet fileSet, final String message, final ScmVersion version) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing checkin command...");
        }
        final Commandline cl = createCommandLine(fileSet, message);
        final ClearCaseCheckInConsumer consumer = new ClearCaseCheckInConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
            }
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing clearcase command.", ex);
        }
        if (exitCode != 0) {
            return new CheckInScmResult(cl.toString(), "The cleartool command failed.", stderr.getOutput(), false);
        }
        return new CheckInScmResult(cl.toString(), consumer.getCheckedInFiles());
    }
    
    public static Commandline createCommandLine(final ScmFileSet scmFileSet, final String message) throws ScmException {
        final Commandline command = new Commandline();
        final File workingDirectory = scmFileSet.getBasedir();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("ci");
        if (message != null) {
            command.createArg().setValue("-c");
            command.createArg().setLine("\"" + message + "\"");
        }
        else {
            command.createArg().setValue("-nc");
        }
        final List<File> files = scmFileSet.getFileList();
        if (files.isEmpty()) {
            throw new ScmException("There are no files in the fileset to check in!");
        }
        for (final File file : files) {
            command.createArg().setValue(file.getAbsolutePath());
        }
        return command;
    }
}
