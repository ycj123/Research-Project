// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.bazaar.command.changelog;

import java.util.Iterator;
import org.apache.maven.scm.ScmResult;
import java.util.List;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.apache.maven.scm.ChangeSet;
import org.apache.maven.scm.provider.bazaar.command.BazaarConsumer;
import org.apache.maven.scm.provider.bazaar.BazaarUtils;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import org.apache.maven.scm.ScmBranch;
import java.util.Date;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.command.changelog.ChangeLogScmRequest;
import org.apache.maven.scm.command.Command;
import org.apache.maven.scm.command.changelog.AbstractChangeLogCommand;

public class BazaarChangeLogCommand extends AbstractChangeLogCommand implements Command
{
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ChangeLogScmRequest request) throws ScmException {
        final ScmVersion startVersion = request.getStartRevision();
        final ScmVersion endVersion = request.getEndRevision();
        final ScmFileSet fileSet = request.getScmFileSet();
        final String datePattern = request.getDatePattern();
        if (startVersion != null || endVersion != null) {
            final ScmProviderRepository scmProviderRepository = request.getScmRepository().getProviderRepository();
            return this.executeChangeLogCommand(scmProviderRepository, fileSet, startVersion, endVersion, datePattern);
        }
        return this.executeChangeLogCommand(fileSet, request.getStartDate(), request.getEndDate(), datePattern, request.getLimit());
    }
    
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repo, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        return this.executeChangeLogCommand(fileSet, startDate, endDate, datePattern, null);
    }
    
    private ChangeLogScmResult executeChangeLogCommand(final ScmFileSet fileSet, Date startDate, Date endDate, final String datePattern, final Integer limit) throws ScmException {
        final List<String> cmd = new ArrayList<String>();
        cmd.addAll(Arrays.asList("log", "--verbose"));
        if (limit != null && limit > 0) {
            cmd.add("--limit");
            cmd.add(Integer.toString(limit));
        }
        final BazaarChangeLogConsumer consumer = new BazaarChangeLogConsumer(this.getLogger(), datePattern);
        final ScmResult result = BazaarUtils.execute(consumer, this.getLogger(), fileSet.getBasedir(), cmd.toArray(new String[cmd.size()]));
        final List<ChangeSet> logEntries = consumer.getModifications();
        final List<ChangeSet> inRangeAndValid = new ArrayList<ChangeSet>();
        startDate = ((startDate == null) ? new Date(0L) : startDate);
        endDate = ((endDate == null) ? new Date() : endDate);
        for (final ChangeSet change : logEntries) {
            if (change.getFiles().size() > 0 && !change.getDate().before(startDate) && !change.getDate().after(endDate)) {
                inRangeAndValid.add(change);
            }
        }
        final ChangeLogSet changeLogSet = new ChangeLogSet(inRangeAndValid, startDate, endDate);
        return new ChangeLogScmResult(changeLogSet, result);
    }
}
