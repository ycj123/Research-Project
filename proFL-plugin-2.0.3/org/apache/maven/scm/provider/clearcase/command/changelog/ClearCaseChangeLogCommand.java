// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.changelog;

import org.apache.maven.scm.providers.clearcase.settings.Settings;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.clearcase.util.ClearCaseUtil;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.clearcase.command.ClearCaseCommand;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class ClearCaseChangeLogCommand extends AbstractChangeLogCommand implements ClearCaseCommand
{
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("executing changelog command...");
        }
        final Commandline cl = createCommandLine(fileSet.getBasedir(), branch, startDate);
        final ClearCaseChangeLogConsumer consumer = new ClearCaseChangeLogConsumer(this.getLogger(), datePattern);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        int exitCode;
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Executing: " + cl.getWorkingDirectory().getAbsolutePath() + ">>" + cl.toString());
            }
            exitCode = CommandLineUtils.executeCommandLine(cl, consumer, stderr);
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing cvs command.", ex);
        }
        if (exitCode != 0) {
            return new ChangeLogScmResult(cl.toString(), "The cleartool command failed.", stderr.getOutput(), false);
        }
        return new ChangeLogScmResult(cl.toString(), new ChangeLogSet(consumer.getModifications(), startDate, endDate));
    }
    
    public static Commandline createCommandLine(final File workingDirectory, final ScmBranch branch, final Date startDate) {
        final Commandline command = new Commandline();
        command.setExecutable("cleartool");
        command.createArg().setValue("lshistory");
        command.setWorkingDirectory(workingDirectory.getAbsolutePath());
        final Settings settings = ClearCaseUtil.getSettings();
        final String userFormat = StringUtils.isEmpty(settings.getChangelogUserFormat()) ? "" : settings.getChangelogUserFormat();
        final StringBuilder format = new StringBuilder();
        format.append("NAME:%En\\n");
        format.append("DATE:%Nd\\n");
        format.append("COMM:%-12.12o - %o - %c - Activity: %[activity]p\\n");
        format.append("USER:%" + userFormat + "u\\n");
        format.append("REVI:%Ln\\n");
        command.createArg().setValue("-fmt");
        command.createArg().setValue(format.toString());
        command.createArg().setValue("-recurse");
        command.createArg().setValue("-nco");
        if (startDate != null) {
            final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            final String start = sdf.format(startDate);
            command.createArg().setValue("-since");
            command.createArg().setValue(start);
        }
        if (branch != null && StringUtils.isNotEmpty(branch.getName())) {
            command.createArg().setValue("-branch");
            command.createArg().setValue(branch.getName());
        }
        return command;
    }
}
