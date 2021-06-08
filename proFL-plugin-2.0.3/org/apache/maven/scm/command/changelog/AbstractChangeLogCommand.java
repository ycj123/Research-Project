// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.changelog;

import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.AbstractCommand;

public abstract class AbstractChangeLogCommand extends AbstractCommand implements ChangeLogCommand
{
    @Deprecated
    protected abstract ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository p0, final ScmFileSet p1, final Date p2, final Date p3, final ScmBranch p4, final String p5) throws ScmException;
    
    @Deprecated
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion, final String datePattern) throws ScmException {
        throw new ScmException("Unsupported method for this provider.");
    }
    
    public ScmResult executeCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException {
        Date startDate = parameters.getDate(CommandParameter.START_DATE, null);
        Date endDate = parameters.getDate(CommandParameter.END_DATE, null);
        final int numDays = parameters.getInt(CommandParameter.NUM_DAYS, 0);
        Integer limit = parameters.getInt(CommandParameter.LIMIT, -1);
        if (limit < 1) {
            limit = null;
        }
        final ScmBranch branch = (ScmBranch)parameters.getScmVersion(CommandParameter.BRANCH, null);
        final ScmVersion startVersion = parameters.getScmVersion(CommandParameter.START_SCM_VERSION, null);
        final ScmVersion endVersion = parameters.getScmVersion(CommandParameter.END_SCM_VERSION, null);
        final String datePattern = parameters.getString(CommandParameter.CHANGELOG_DATE_PATTERN, null);
        if (startVersion != null || endVersion != null) {
            return this.executeChangeLogCommand(repository, fileSet, startVersion, endVersion, datePattern);
        }
        if (numDays != 0 && (startDate != null || endDate != null)) {
            throw new ScmException("Start or end date cannot be set if num days is set.");
        }
        if (endDate != null && startDate == null) {
            throw new ScmException("The end date is set but the start date isn't.");
        }
        if (numDays > 0) {
            final int day = 86400000;
            startDate = new Date(System.currentTimeMillis() - numDays * (long)day);
            endDate = new Date(System.currentTimeMillis() + day);
        }
        else if (endDate == null) {
            endDate = new Date();
        }
        return this.executeChangeLogCommand(repository, fileSet, startDate, endDate, branch, datePattern);
    }
    
    protected ChangeLogScmResult executeChangeLogCommand(final ChangeLogScmRequest request) throws ScmException {
        throw new ScmException("Unsupported method for this provider.");
    }
}
