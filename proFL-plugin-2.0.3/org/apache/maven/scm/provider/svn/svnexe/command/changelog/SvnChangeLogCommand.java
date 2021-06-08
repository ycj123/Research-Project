// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.changelog;

import org.apache.maven.scm.provider.svn.SvnTagBranchUtils;
import org.apache.maven.scm.ScmTag;
import org.codehaus.plexus.util.StringUtils;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.svn.svnexe.command.SvnCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.apache.maven.scm.command.changelog.ChangeLogScmRequest;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.svn.command.SvnCommand;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class SvnChangeLogCommand extends AbstractChangeLogCommand implements SvnCommand
{
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss Z";
    
    @Deprecated
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion, final String datePattern) throws ScmException {
        return this.executeChangeLogCommand(repo, fileSet, null, null, null, datePattern, startVersion, endVersion, null);
    }
    
    @Deprecated
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        return this.executeChangeLogCommand(repo, fileSet, startDate, endDate, branch, datePattern, null, null, null);
    }
    
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ChangeLogScmRequest request) throws ScmException {
        final ScmVersion startVersion = request.getStartRevision();
        final ScmVersion endVersion = request.getEndRevision();
        final ScmFileSet fileSet = request.getScmFileSet();
        final String datePattern = request.getDatePattern();
        return this.executeChangeLogCommand(request.getScmRepository().getProviderRepository(), fileSet, request.getStartDate(), request.getEndDate(), request.getScmBranch(), datePattern, startVersion, endVersion, request.getLimit());
    }
    
    private ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern, final ScmVersion startVersion, final ScmVersion endVersion, final Integer limit) throws ScmException {
        final Commandline cl = createCommandLine((SvnScmProviderRepository)repo, fileSet.getBasedir(), branch, startDate, endDate, startVersion, endVersion, limit);
        final SvnChangeLogConsumer consumer = new SvnChangeLogConsumer(this.getLogger(), datePattern);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + SvnCommandLineUtils.cryptPassword(cl));
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        int exitCode;
        try {
            exitCode = SvnCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
        }
        catch (CommandLineException ex) {
            throw new ScmException("Error while executing svn command.", ex);
        }
        if (exitCode != 0) {
            return new ChangeLogScmResult(cl.toString(), "The svn command failed.", stderr.getOutput(), false);
        }
        final ChangeLogSet changeLogSet = new ChangeLogSet(consumer.getModifications(), startDate, endDate);
        changeLogSet.setStartVersion(startVersion);
        changeLogSet.setEndVersion(endVersion);
        return new ChangeLogScmResult(cl.toString(), changeLogSet);
    }
    
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final File workingDirectory, final ScmBranch branch, final Date startDate, final Date endDate, final ScmVersion startVersion, final ScmVersion endVersion) {
        return createCommandLine(repository, workingDirectory, branch, startDate, endDate, startVersion, endVersion, null);
    }
    
    public static Commandline createCommandLine(final SvnScmProviderRepository repository, final File workingDirectory, final ScmBranch branch, final Date startDate, final Date endDate, final ScmVersion startVersion, final ScmVersion endVersion, final Integer limit) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        final Commandline cl = SvnCommandLineUtils.getBaseSvnCommandLine(workingDirectory, repository);
        cl.createArg().setValue("log");
        cl.createArg().setValue("-v");
        if (limit != null && limit > 0) {
            cl.createArg().setValue("--limit");
            cl.createArg().setValue(Integer.toString(limit));
        }
        if (startDate != null) {
            cl.createArg().setValue("-r");
            if (endDate != null) {
                cl.createArg().setValue("{" + dateFormat.format(startDate) + "}" + ":" + "{" + dateFormat.format(endDate) + "}");
            }
            else {
                cl.createArg().setValue("{" + dateFormat.format(startDate) + "}:HEAD");
            }
        }
        if (startVersion != null) {
            cl.createArg().setValue("-r");
            if (endVersion != null) {
                if (startVersion.getName().equals(endVersion.getName())) {
                    cl.createArg().setValue(startVersion.getName());
                }
                else {
                    cl.createArg().setValue(startVersion.getName() + ":" + endVersion.getName());
                }
            }
            else {
                cl.createArg().setValue(startVersion.getName() + ":HEAD");
            }
        }
        if (branch != null && StringUtils.isNotEmpty(branch.getName())) {
            if (branch instanceof ScmTag) {
                cl.createArg().setValue(SvnTagBranchUtils.resolveTagUrl(repository, (ScmTag)branch));
            }
            else {
                cl.createArg().setValue(SvnTagBranchUtils.resolveBranchUrl(repository, branch));
            }
        }
        if (endVersion == null || !StringUtils.equals("BASE", endVersion.getName())) {
            cl.createArg().setValue(repository.getUrl());
        }
        return cl;
    }
}
