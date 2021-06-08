// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.command.changelog;

import java.util.Date;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmBranch;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.repository.ScmRepository;
import org.apache.maven.scm.ScmRequest;

public class ChangeLogScmRequest extends ScmRequest
{
    private static final long serialVersionUID = 20120620L;
    
    public ChangeLogScmRequest(final ScmRepository scmRepository, final ScmFileSet scmFileSet) {
        super(scmRepository, scmFileSet);
    }
    
    public ScmBranch getScmBranch() throws ScmException {
        return (ScmBranch)this.parameters.getScmVersion(CommandParameter.BRANCH, null);
    }
    
    public void setScmBranch(final ScmBranch scmBranch) throws ScmException {
        this.parameters.setScmVersion(CommandParameter.BRANCH, scmBranch);
    }
    
    public Date getStartDate() throws ScmException {
        return this.parameters.getDate(CommandParameter.START_DATE);
    }
    
    public void setStartDate(final Date startDate) throws ScmException {
        this.parameters.setDate(CommandParameter.START_DATE, startDate);
    }
    
    public Date getEndDate() throws ScmException {
        return this.parameters.getDate(CommandParameter.END_DATE);
    }
    
    public void setEndDate(final Date endDate) throws ScmException {
        this.parameters.setDate(CommandParameter.END_DATE, endDate);
    }
    
    public int getNumDays() throws ScmException {
        return this.parameters.getInt(CommandParameter.START_DATE);
    }
    
    public void setNumDays(final int numDays) throws ScmException {
        this.parameters.setInt(CommandParameter.NUM_DAYS, numDays);
    }
    
    public ScmVersion getStartRevision() throws ScmException {
        return this.parameters.getScmVersion(CommandParameter.START_SCM_VERSION, null);
    }
    
    public void setStartRevision(final ScmVersion startRevision) throws ScmException {
        this.parameters.setScmVersion(CommandParameter.START_SCM_VERSION, startRevision);
    }
    
    public ScmVersion getEndRevision() throws ScmException {
        return this.parameters.getScmVersion(CommandParameter.END_SCM_VERSION, null);
    }
    
    public void setEndRevision(final ScmVersion endRevision) throws ScmException {
        this.parameters.setScmVersion(CommandParameter.END_SCM_VERSION, endRevision);
    }
    
    public String getDatePattern() throws ScmException {
        return this.parameters.getString(CommandParameter.CHANGELOG_DATE_PATTERN, null);
    }
    
    public void setDatePattern(final String datePattern) throws ScmException {
        this.parameters.setString(CommandParameter.CHANGELOG_DATE_PATTERN, datePattern);
    }
    
    public Integer getLimit() throws ScmException {
        final int limit = this.parameters.getInt(CommandParameter.LIMIT, -1);
        return (limit > 0) ? Integer.valueOf(limit) : null;
    }
    
    public void setLimit(final Integer limit) throws ScmException {
        if (limit != null) {
            this.parameters.setInt(CommandParameter.LIMIT, limit);
        }
        else {
            this.parameters.remove(CommandParameter.LIMIT);
        }
    }
    
    public void setDateRange(final Date startDate, final Date endDate) throws ScmException {
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }
}
