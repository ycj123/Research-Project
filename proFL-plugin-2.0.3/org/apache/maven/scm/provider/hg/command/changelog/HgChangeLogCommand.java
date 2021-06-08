// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.hg.command.changelog;

import org.apache.maven.scm.ChangeSet;
import org.apache.maven.scm.ScmResult;
import java.util.List;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.apache.maven.scm.provider.hg.command.HgConsumer;
import org.apache.maven.scm.provider.hg.HgUtils;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
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

public class HgChangeLogCommand extends AbstractChangeLogCommand implements Command
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
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository scmProviderRepository, final ScmFileSet fileSet, final Date startDate, final Date endDate, final ScmBranch branch, final String datePattern) throws ScmException {
        return this.executeChangeLogCommand(fileSet, startDate, endDate, datePattern, null);
    }
    
    private ChangeLogScmResult executeChangeLogCommand(final ScmFileSet fileSet, final Date startDate, final Date endDate, final String datePattern, final Integer limit) throws ScmException {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final StringBuilder dateInterval = new StringBuilder();
        dateInterval.append(dateFormat.format((startDate == null) ? new Date(86400000L) : startDate));
        dateInterval.append(" to ");
        dateInterval.append(dateFormat.format((endDate == null) ? new Date() : endDate));
        final List<String> cmd = new ArrayList<String>();
        cmd.addAll(Arrays.asList("log", "--template", "changeset:   {rev}:{node|short}\nbranch:      {branch}\nuser:        {author}\ndate:        {date|isodatesec}\ntag:         {tags}\nfiles:       {files}\ndescription:\n{desc}\n", "--no-merges", "--date", dateInterval.toString()));
        if (limit != null && limit > 0) {
            cmd.add("--limit");
            cmd.add(Integer.toString(limit));
        }
        final HgChangeLogConsumer consumer = new HgChangeLogConsumer(this.getLogger(), datePattern);
        final ScmResult result = HgUtils.execute(consumer, this.getLogger(), fileSet.getBasedir(), cmd.toArray(new String[cmd.size()]));
        final List<ChangeSet> logEntries = consumer.getModifications();
        final ChangeLogSet changeLogSet = new ChangeLogSet(logEntries, startDate, endDate);
        return new ChangeLogScmResult(changeLogSet, result);
    }
    
    @Override
    protected ChangeLogScmResult executeChangeLogCommand(final ScmProviderRepository repository, final ScmFileSet fileSet, final ScmVersion startVersion, final ScmVersion endVersion, final String datePattern) throws ScmException {
        final StringBuilder revisionInterval = new StringBuilder();
        if (startVersion != null) {
            revisionInterval.append(startVersion.getName());
        }
        revisionInterval.append(":");
        if (endVersion != null) {
            revisionInterval.append(endVersion.getName());
        }
        final String[] cmd = { "log", "--template", "changeset:   {rev}:{node|short}\nbranch:      {branch}\nuser:        {author}\ndate:        {date|isodatesec}\ntag:         {tags}\nfiles:       {files}\ndescription:\n{desc}\n", "--no-merges", "-r", revisionInterval.toString() };
        final HgChangeLogConsumer consumer = new HgChangeLogConsumer(this.getLogger(), datePattern);
        final ScmResult result = HgUtils.execute(consumer, this.getLogger(), fileSet.getBasedir(), cmd);
        final List<ChangeSet> logEntries = consumer.getModifications();
        Date startDate = null;
        Date endDate = null;
        if (!logEntries.isEmpty()) {
            startDate = logEntries.get(0).getDate();
            endDate = logEntries.get(logEntries.size() - 1).getDate();
        }
        final ChangeLogSet changeLogSet = new ChangeLogSet(logEntries, startDate, endDate);
        changeLogSet.setStartVersion(startVersion);
        changeLogSet.setEndVersion(endVersion);
        return new ChangeLogScmResult(changeLogSet, result);
    }
}
