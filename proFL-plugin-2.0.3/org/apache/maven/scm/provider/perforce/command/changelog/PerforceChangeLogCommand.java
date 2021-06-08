// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.changelog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class PerforceChangeLogCommand extends AbstractChangeLogCommand implements PerforceCommand
{
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion, final String datePattern) throws ScmException {
        return this.executeChangeLogCommand(repo, fileSet, null, null, null, datePattern, startVersion, endVersion);
    }
    
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        if (branch != null && StringUtils.isNotEmpty(branch.getName())) {
            throw new ScmException("This SCM doesn't support branches.");
        }
        return this.executeChangeLogCommand(repo, fileSet, startDate, endDate, branch, datePattern, null, null);
    }
    
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern, final ScmVersion startVersion, final ScmVersion endVersion) throws ScmException {
        final PerforceScmProviderRepository p4repo = (PerforceScmProviderRepository)repo;
        final String clientspec = PerforceScmProvider.getClientspecName(this.getLogger(), p4repo, fileSet.getBasedir());
        Commandline cl = createCommandLine(p4repo, fileSet.getBasedir(), clientspec, null, startDate, endDate, startVersion, endVersion);
        final String location = PerforceScmProvider.getRepoPath(this.getLogger(), p4repo, fileSet.getBasedir());
        final PerforceChangesConsumer consumer = new PerforceChangesConsumer(this.getLogger());
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
        final List<String> changes = consumer.getChanges();
        cl = PerforceScmProvider.createP4Command(p4repo, fileSet.getBasedir());
        cl.createArg().setValue("describe");
        cl.createArg().setValue("-s");
        for (final String change : changes) {
            cl.createArg().setValue(change);
        }
        final PerforceDescribeConsumer describeConsumer = new PerforceDescribeConsumer(location, datePattern, this.getLogger());
        try {
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug(PerforceScmProvider.clean("Executing " + cl.toString()));
            }
            final CommandLineUtils.StringStreamConsumer err2 = new CommandLineUtils.StringStreamConsumer();
            final int exitCode2 = CommandLineUtils.executeCommandLine(cl, describeConsumer, err2);
            if (exitCode2 != 0) {
                final String cmdLine2 = CommandLineUtils.toString(cl.getCommandline());
                final StringBuilder msg2 = new StringBuilder("Exit code: " + exitCode2 + " - " + err2.getOutput());
                msg2.append('\n');
                msg2.append("Command line was:" + cmdLine2);
                throw new CommandLineException(msg2.toString());
            }
        }
        catch (CommandLineException e2) {
            if (this.getLogger().isErrorEnabled()) {
                this.getLogger().error("CommandLineException " + e2.getMessage(), e2);
            }
        }
        final ChangeLogSet cls = new ChangeLogSet(describeConsumer.getModifications(), null, null);
        cls.setStartVersion(startVersion);
        cls.setEndVersion(endVersion);
        return new ChangeLogScmResult(cl.toString(), cls);
    }
    
    public static Commandline createCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final String clientspec, final ScmBranch branch, final Date startDate, final Date endDate, final ScmVersion startVersion, final ScmVersion endVersion) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss");
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        if (clientspec != null) {
            command.createArg().setValue("-c");
            command.createArg().setValue(clientspec);
        }
        command.createArg().setValue("changes");
        command.createArg().setValue("-t");
        final StringBuilder fileSpec = new StringBuilder("...");
        if (startDate != null) {
            fileSpec.append("@").append(dateFormat.format(startDate)).append(",");
            if (endDate != null) {
                fileSpec.append(dateFormat.format(endDate));
            }
            else {
                fileSpec.append("now");
            }
        }
        if (startVersion != null) {
            fileSpec.append("@").append(startVersion.getName()).append(",");
            if (endVersion != null) {
                fileSpec.append(endVersion.getName());
            }
            else {
                fileSpec.append("now");
            }
        }
        command.createArg().setValue(fileSpec.toString());
        return command;
    }
}
