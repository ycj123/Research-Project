// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.gitexe.command.changelog;

import org.codehaus.plexus.util.StringUtils;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.io.File;
import org.codehaus.plexus.util.cli.Commandline;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.apache.maven.scm.provider.git.gitexe.command.GitCommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.apache.maven.scm.provider.git.repository.GitScmProviderRepository;
import org.apache.maven.scm.command.changelog.ChangeLogScmRequest;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.git.command.GitCommand;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class GitChangeLogCommand extends AbstractChangeLogCommand implements GitCommand
{
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss Z";
    
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion, final String datePattern) throws ScmException {
        return this.executeChangeLogCommand(repo, fileSet, null, null, null, datePattern, startVersion, endVersion);
    }
    
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        return this.executeChangeLogCommand(repo, fileSet, startDate, endDate, branch, datePattern, null, null);
    }
    
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern, final ScmVersion startVersion, final ScmVersion endVersion) throws ScmException {
        return this.executeChangeLogCommand(repo, fileSet, startDate, endDate, branch, datePattern, startVersion, endVersion, null);
    }
    
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ChangeLogScmRequest request) throws ScmException {
        final ScmVersion startVersion = request.getStartRevision();
        final ScmVersion endVersion = request.getEndRevision();
        final ScmFileSet fileSet = request.getScmFileSet();
        final String datePattern = request.getDatePattern();
        return this.executeChangeLogCommand(request.getScmRepository().getProviderRepository(), fileSet, request.getStartDate(), request.getEndDate(), request.getScmBranch(), datePattern, startVersion, endVersion, request.getLimit());
    }
    
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern, final ScmVersion startVersion, final ScmVersion endVersion, final Integer limit) throws ScmException {
        final Commandline cl = createCommandLine((GitScmProviderRepository)repo, fileSet.getBasedir(), branch, startDate, endDate, startVersion, endVersion, limit);
        final GitChangeLogConsumer consumer = new GitChangeLogConsumer(this.getLogger(), datePattern);
        final CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        final int exitCode = GitCommandLineUtils.execute(cl, consumer, stderr, this.getLogger());
        if (exitCode != 0) {
            return new ChangeLogScmResult(cl.toString(), "The git-log command failed.", stderr.getOutput(), false);
        }
        final ChangeLogSet changeLogSet = new ChangeLogSet(consumer.getModifications(), startDate, endDate);
        changeLogSet.setStartVersion(startVersion);
        changeLogSet.setEndVersion(endVersion);
        return new ChangeLogScmResult(cl.toString(), changeLogSet);
    }
    
    public static Commandline createCommandLine(final GitScmProviderRepository repository, final File workingDirectory, final ScmBranch branch, final Date startDate, final Date endDate, final ScmVersion startVersion, final ScmVersion endVersion) {
        return createCommandLine(repository, workingDirectory, branch, startDate, endDate, startVersion, endVersion, null);
    }
    
    static Commandline createCommandLine(final GitScmProviderRepository repository, final File workingDirectory, final ScmBranch branch, final Date startDate, final Date endDate, final ScmVersion startVersion, final ScmVersion endVersion, final Integer limit) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        final Commandline cl = GitCommandLineUtils.getBaseGitCommandLine(workingDirectory, "whatchanged");
        if (startDate != null || endDate != null) {
            if (startDate != null) {
                cl.createArg().setValue("--since=" + StringUtils.escape(dateFormat.format(startDate)));
            }
            if (endDate != null) {
                cl.createArg().setValue("--until=" + StringUtils.escape(dateFormat.format(endDate)));
            }
        }
        cl.createArg().setValue("--date=iso");
        if (startVersion != null || endVersion != null) {
            final StringBuilder versionRange = new StringBuilder();
            if (startVersion != null) {
                versionRange.append(StringUtils.escape(startVersion.getName()));
            }
            versionRange.append("..");
            if (endVersion != null) {
                versionRange.append(StringUtils.escape(endVersion.getName()));
            }
            cl.createArg().setValue(versionRange.toString());
        }
        if (limit != null && limit > 0) {
            cl.createArg().setValue("--max-count=" + limit);
        }
        if (branch != null && branch.getName() != null && branch.getName().length() > 0) {
            cl.createArg().setValue(branch.getName());
        }
        cl.createArg().setValue("--");
        cl.createArg().setFile(workingDirectory);
        return cl;
    }
}
