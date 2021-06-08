// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.vss.commands.changelog;

import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.maven.scm.ScmException;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.vss.commands.VssCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.vss.repository.VssScmProviderRepository;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class VssHistoryCommand extends AbstractChangeLogCommand
{
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        final VssScmProviderRepository repo = (VssScmProviderRepository)repository;
        final Commandline cl = this.buildCmdLine(repo, fileSet, startDate, endDate);
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        final VssChangeLogConsumer consumer = new VssChangeLogConsumer(repo, datePattern, this.getLogger());
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final int exitCode = VssCommandLineUtils.executeCommandline(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new ChangeLogScmResult(cl.toString(), "The vss command failed.", stderr.getOutput(), false);
        }
        return new ChangeLogScmResult(cl.toString(), new ChangeLogSet(consumer.getModifications(), startDate, endDate));
    }
    
    public Commandline buildCmdLine(final VssScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, Date endDate) throws ScmException {
        final Commandline command = new Commandline();
        command.setWorkingDirectory(fileSet.getBasedir().getAbsolutePath());
        try {
            command.addSystemEnvironment();
        }
        catch (Exception e) {
            throw new ScmException("Can't add system environment.", e);
        }
        command.addEnvironment("SSDIR", repo.getVssdir());
        final String ssDir = VssCommandLineUtils.getSsDir();
        command.setExecutable(ssDir + "ss");
        command.createArg().setValue("History");
        command.createArg().setValue("$" + repo.getProject());
        if (repo.getUserPassword() != null) {
            command.createArg().setValue("-Y" + repo.getUserPassword());
        }
        command.createArg().setValue("-R");
        command.createArg().setValue("-I-");
        if (startDate != null) {
            if (endDate == null) {
                endDate = new Date();
            }
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            final String dateRange = sdf.format(endDate) + "~" + sdf.format(startDate);
            command.createArg().setValue("-Vd" + dateRange);
        }
        return command;
    }
}
