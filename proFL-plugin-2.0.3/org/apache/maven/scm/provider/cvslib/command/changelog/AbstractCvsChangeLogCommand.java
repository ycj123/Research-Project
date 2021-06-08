// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.changelog;

import org.codehaus.plexus.util.Os;
import org.apache.maven.scm.provider.cvslib.util.CvsUtil;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.StringUtils;
import java.text.SimpleDateFormat;
import org.apache.maven.scm.provider.cvslib.command.CvsCommandUtils;
import org.apache.maven.scm.provider.cvslib.repository.CvsScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.cvslib.command.CvsCommand;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public abstract class AbstractCvsChangeLogCommand extends AbstractChangeLogCommand implements CvsCommand
{
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion, final String datePattern) throws ScmException {
        return this.executeChangeLogCommand(repo, fileSet, null, null, null, startVersion, endVersion, datePattern);
    }
    
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        return this.executeChangeLogCommand(repo, fileSet, startDate, endDate, branch, null, null, datePattern);
    }
    
    private ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final ScmVersion startVersion, final ScmVersion endVersion, final String datePattern) throws ScmException {
        final CvsScmProviderRepository repository = (CvsScmProviderRepository)repo;
        final Commandline cl = CvsCommandUtils.getBaseCommand("log", repository, fileSet);
        if (startDate != null) {
            final SimpleDateFormat outputDate = new SimpleDateFormat(this.getDateFormat());
            String dateRange;
            if (endDate == null) {
                dateRange = ">" + outputDate.format(startDate);
            }
            else {
                dateRange = outputDate.format(startDate) + "<" + outputDate.format(endDate);
            }
            cl.createArg().setValue("-d");
            this.addDateRangeParameter(cl, dateRange);
        }
        if (branch != null && StringUtils.isNotEmpty(branch.getName())) {
            cl.createArg().setValue("-r" + branch.getName());
        }
        if (startVersion != null || endVersion != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("-r");
            if (startVersion != null && StringUtils.isNotEmpty(startVersion.getName())) {
                sb.append(startVersion.getName());
            }
            sb.append("::");
            if (endVersion != null && StringUtils.isNotEmpty(endVersion.getName())) {
                sb.append(endVersion.getName());
            }
            cl.createArg().setValue(sb.toString());
        }
        if (this.getLogger().isInfoEnabled()) {
            this.getLogger().info("Executing: " + cl);
            this.getLogger().info("Working directory: " + cl.getWorkingDirectory().getAbsolutePath());
        }
        return this.executeCvsCommand(cl, startDate, endDate, startVersion, endVersion, datePattern);
    }
    
    protected abstract ChangeLogScmResult executeCvsCommand(final Commandline p0, final Date p1, final Date p2, final ScmVersion p3, final ScmVersion p4, final String p5) throws ScmException;
    
    protected String getDateFormat() {
        return CvsUtil.getSettings().getChangeLogCommandDateFormat();
    }
    
    protected void addDateRangeParameter(final Commandline cl, final String dateRange) {
        if (Os.isFamily("windows")) {
            cl.createArg().setValue("\"" + dateRange + "\"");
        }
        else {
            cl.createArg().setValue(dateRange);
        }
    }
}
