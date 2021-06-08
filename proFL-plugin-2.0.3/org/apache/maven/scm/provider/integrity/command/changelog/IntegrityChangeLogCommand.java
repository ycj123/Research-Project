// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity.command.changelog;

import com.mks.api.response.APIException;
import org.apache.maven.scm.provider.integrity.ExceptionHandler;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.provider.integrity.repository.IntegrityScmProviderRepository;
import org.apache.maven.scm.provider.integrity.Sandbox;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class IntegrityChangeLogCommand extends AbstractChangeLogCommand
{
    public ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        if (null == startDate || null == endDate) {
            throw new ScmException("Both 'startDate' and 'endDate' must be specified!");
        }
        if (startDate.after(endDate)) {
            throw new ScmException("'stateDate' is not allowed to occur after 'endDate'!");
        }
        this.getLogger().info("Attempting to obtain change log for date range: '" + Sandbox.RLOG_DATEFORMAT.format(startDate) + "' to '" + Sandbox.RLOG_DATEFORMAT.format(endDate) + "'");
        final IntegrityScmProviderRepository iRepo = (IntegrityScmProviderRepository)repository;
        ChangeLogScmResult result;
        try {
            result = new ChangeLogScmResult(iRepo.getSandbox().getChangeLog(startDate, endDate), new ScmResult("si rlog", "", "", true));
        }
        catch (APIException aex) {
            final ExceptionHandler eh = new ExceptionHandler(aex);
            this.getLogger().error("MKS API Exception: " + eh.getMessage());
            this.getLogger().info(eh.getCommand() + " exited with return code " + eh.getExitCode());
            result = new ChangeLogScmResult(eh.getCommand(), eh.getMessage(), "Exit Code: " + eh.getExitCode(), false);
        }
        return result;
    }
}
