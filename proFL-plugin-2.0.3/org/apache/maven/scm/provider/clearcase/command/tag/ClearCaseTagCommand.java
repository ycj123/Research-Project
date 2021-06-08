// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.tag;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.tag.TagScmResult;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.clearcase.command.checkin.ClearCaseCheckInConsumer;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmTagParameters;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.clearcase.command.ClearCaseCommand;
import org.apache.maven.scm.command.tag.AbstractTagCommand;

public class ClearCaseTagCommand extends AbstractTagCommand implements ClearCaseCommand
{
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository scmProviderRepository, final ScmFileSet fileSet, final String tag, final String message) throws ScmException {
        return this.executeTagCommand(scmProviderRepository, fileSet, tag, new ScmTagParameters(message));
    }
    
    @Override
    protected ScmResult executeTagCommand(final ScmProviderRepository scmProviderRepository, final ScmFileSet fileSet, final String tag, final ScmTagParameters scmTagParameters) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing tag command...");
        }
        final Commandline cl = createCommandLine(fileSet, tag);
        final ClearCaseCheckInConsumer consumer = new ClearCaseCheckInConsumer(this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Creating label: " + tag);
            }
            final Commandline newLabelCommandLine = createNewLabelCommandLine(fileSet, tag);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Executing: " + newLabelCommandLine.getWorkingDirectory().getAbsolutePath() + ">>" + newLabelCommandLine.toString());
            }
            exitCode = CommandLineUtils.executeCommandLine(newLabelCommandLine, new CommandLineUtils.StringStreamConsumer(), stderr);
            if (exitCode == 0) {
                this.getLogger().debug("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
                exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
            }
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing clearcase command.", ex);
        }
        if (exitCode != 0) {
            return new TagScmResult(cl.toString(), "The cleartool command failed.", stderr.getOutput(), false);
        }
        return new TagScmResult(cl.toString(), consumer.getCheckedInFiles());
    }
    
    public static Commandline createCommandLine(final ScmFileSet scmFileSet, final String tag) {
        final Commandline command = new Commandline();
        final File workingDirectory = scmFileSet.getBasedir();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("mklabel");
        final List<File> files = scmFileSet.getFileList();
        if (files.isEmpty()) {
            command.createArg().setValue("-recurse");
        }
        command.createArg().setValue(tag);
        if (files.size() > 0) {
            for (final File file : files) {
                command.createArg().setValue(file.getName());
            }
        }
        else {
            command.createArg().setValue(".");
        }
        return command;
    }
    
    private static Commandline createNewLabelCommandLine(final ScmFileSet scmFileSet, final String tag) {
        final Commandline command = new Commandline();
        final File workingDirectory = scmFileSet.getBasedir();
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        command.setExecutable("cleartool");
        command.createArg().setValue("mklbtype");
        command.createArg().setValue("-nc");
        command.createArg().setValue(tag);
        return command;
    }
}
