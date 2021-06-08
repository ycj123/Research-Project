// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.command.changelog;

import org.apache.maven.scm.provider.ScmProviderRepository;
import java.util.Iterator;
import java.util.Map;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ChangeSet;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.maven.scm.command.changelog.ChangeLogSet;
import org.apache.maven.scm.provider.accurev.Stream;
import org.apache.maven.scm.provider.accurev.AccuRev;
import org.apache.maven.scm.provider.accurev.FileDifference;
import java.util.List;
import org.apache.maven.scm.provider.accurev.Transaction;
import org.apache.maven.scm.command.changelog.ChangeLogScmResult;
import org.apache.maven.scm.provider.accurev.AccuRevCapability;
import java.util.Collections;
import org.apache.maven.scm.ScmRevision;
import org.apache.maven.scm.ScmException;
import java.util.Date;
import org.apache.maven.scm.provider.accurev.AccuRevException;
import org.apache.maven.scm.provider.accurev.AccuRevVersion;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.CommandParameter;
import org.apache.maven.scm.ScmBranch;
import org.apache.maven.scm.ScmResult;
import org.apache.maven.scm.CommandParameters;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.accurev.AccuRevScmProviderRepository;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.accurev.command.AbstractAccuRevCommand;

public class AccuRevChangeLogCommand extends AbstractAccuRevCommand
{
    public AccuRevChangeLogCommand(final ScmLogger logger) {
        super(logger);
    }
    
    @Override
    protected ScmResult executeAccurevCommand(final AccuRevScmProviderRepository repository, final ScmFileSet fileSet, final CommandParameters parameters) throws ScmException, AccuRevException {
        final ScmBranch branch = (ScmBranch)parameters.getScmVersion(CommandParameter.BRANCH, null);
        final AccuRevVersion branchVersion = repository.getAccuRevVersion(branch);
        String stream = branchVersion.getBasisStream();
        String fromSpec = branchVersion.getTimeSpec();
        String toSpec = "highest";
        ScmVersion startVersion = parameters.getScmVersion(CommandParameter.START_SCM_VERSION, null);
        ScmVersion endVersion = parameters.getScmVersion(CommandParameter.END_SCM_VERSION, null);
        if (startVersion != null && StringUtils.isNotEmpty(startVersion.getName())) {
            final AccuRevVersion fromVersion = repository.getAccuRevVersion(startVersion);
            final AccuRevVersion toVersion = (endVersion == null) ? new AccuRevVersion(fromVersion.getBasisStream(), "now") : repository.getAccuRevVersion(endVersion);
            if (!StringUtils.equals(fromVersion.getBasisStream(), toVersion.getBasisStream())) {
                throw new AccuRevException("Not able to provide change log between different streams " + fromVersion + "," + toVersion);
            }
            stream = fromVersion.getBasisStream();
            fromSpec = fromVersion.getTimeSpec();
            toSpec = toVersion.getTimeSpec();
        }
        Date startDate = parameters.getDate(CommandParameter.START_DATE, null);
        Date endDate = parameters.getDate(CommandParameter.END_DATE, null);
        final int numDays = parameters.getInt(CommandParameter.NUM_DAYS, 0);
        if (numDays > 0) {
            if (startDate != null || endDate != null) {
                throw new ScmException("Start or end date cannot be set if num days is set.");
            }
            final int day = 86400000;
            startDate = new Date(System.currentTimeMillis() - numDays * (long)day);
            endDate = new Date(System.currentTimeMillis() + day);
        }
        if (endDate != null && startDate == null) {
            throw new ScmException("The end date is set but the start date isn't.");
        }
        if (startDate != null) {
            fromSpec = AccuRevScmProviderRepository.formatTimeSpec(startDate);
        }
        else if (fromSpec == null) {
            fromSpec = "1";
        }
        final Transaction fromTransaction = this.getDepotTransaction(repository, stream, fromSpec);
        long fromTranId = 1L;
        if (fromTransaction != null) {
            fromTranId = fromTransaction.getTranId();
            if (startDate == null) {
                startDate = fromTransaction.getWhen();
            }
        }
        if (endDate != null) {
            toSpec = AccuRevScmProviderRepository.formatTimeSpec(endDate);
        }
        else if (toSpec == null) {
            toSpec = "highest";
        }
        final Transaction toTransaction = this.getDepotTransaction(repository, stream, toSpec);
        long toTranId = 1L;
        if (toTransaction != null) {
            toTranId = toTransaction.getTranId();
            if (endDate == null) {
                endDate = toTransaction.getWhen();
            }
        }
        startVersion = new ScmRevision(repository.getRevision(stream, fromTranId));
        endVersion = new ScmRevision(repository.getRevision(stream, toTranId));
        List<Transaction> streamHistory = Collections.emptyList();
        List<Transaction> workspaceHistory = Collections.emptyList();
        List<FileDifference> streamDifferences = Collections.emptyList();
        final StringBuilder errorMessage = new StringBuilder();
        final AccuRev accurev = repository.getAccuRev();
        final Stream changelogStream = accurev.showStream(stream);
        if (changelogStream == null) {
            errorMessage.append("Unknown accurev stream -").append(stream).append(".");
        }
        else {
            final String message = "Changelog on stream " + stream + "(" + changelogStream.getStreamType() + ") from " + fromTranId + " (" + startDate + "), to " + toTranId + " (" + endDate + ")";
            if ((startDate != null && startDate.after(endDate)) || fromTranId >= toTranId) {
                this.getLogger().warn("Skipping out of range " + message);
            }
            else {
                this.getLogger().info(message);
                Stream diffStream = changelogStream;
                if (changelogStream.isWorkspace()) {
                    workspaceHistory = accurev.history(stream, Long.toString(fromTranId + 1L), Long.toString(toTranId), 0, false, false);
                    if (workspaceHistory == null) {
                        errorMessage.append("history on workspace " + stream + " from " + fromTranId + 1 + " to " + toTranId + " failed.");
                    }
                    stream = changelogStream.getBasis();
                    diffStream = accurev.showStream(stream);
                }
                if (AccuRevCapability.DIFF_BETWEEN_STREAMS.isSupported(accurev.getClientVersion())) {
                    if (startDate.before(diffStream.getStartDate())) {
                        this.getLogger().warn("Skipping diff of " + stream + " due to start date out of range");
                    }
                    else {
                        streamDifferences = accurev.diff(stream, Long.toString(fromTranId), Long.toString(toTranId));
                        if (streamDifferences == null) {
                            errorMessage.append("Diff " + stream + "- " + fromTranId + " to " + toTranId + "failed.");
                        }
                    }
                }
                streamHistory = accurev.history(stream, Long.toString(fromTranId + 1L), Long.toString(toTranId), 0, false, false);
                if (streamHistory == null) {
                    errorMessage.append("history on stream " + stream + " from " + fromTranId + 1 + " to " + toTranId + " failed.");
                }
            }
        }
        final String errorString = errorMessage.toString();
        if (StringUtils.isBlank(errorString)) {
            final ChangeLogSet changeLog = this.getChangeLog(changelogStream, streamDifferences, streamHistory, workspaceHistory, startDate, endDate);
            changeLog.setEndVersion(endVersion);
            changeLog.setStartVersion(startVersion);
            return new ChangeLogScmResult(accurev.getCommandLines(), changeLog);
        }
        return new ChangeLogScmResult(accurev.getCommandLines(), "AccuRev errors: " + (Object)errorMessage, accurev.getErrorOutput(), false);
    }
    
    private Transaction getDepotTransaction(final AccuRevScmProviderRepository repo, final String stream, final String tranSpec) throws AccuRevException {
        return repo.getDepotTransaction(stream, tranSpec);
    }
    
    private ChangeLogSet getChangeLog(final Stream stream, final List<FileDifference> streamDifferences, final List<Transaction> streamHistory, final List<Transaction> workspaceHistory, final Date startDate, final Date endDate) {
        final Map<Long, FileDifference> differencesMap = new HashMap<Long, FileDifference>();
        for (final FileDifference fileDifference : streamDifferences) {
            differencesMap.put(fileDifference.getElementId(), fileDifference);
        }
        final List<Transaction> mergedHistory = new ArrayList<Transaction>(streamHistory);
        String streamPrefix = "/";
        mergedHistory.addAll(workspaceHistory);
        streamPrefix = stream.getId() + "/";
        final List<ChangeSet> entries = new ArrayList<ChangeSet>(streamHistory.size());
        for (final Transaction t : mergedHistory) {
            if (startDate == null || !t.getWhen().before(startDate)) {
                if (endDate != null && t.getWhen().after(endDate)) {
                    continue;
                }
                if ("mkstream".equals(t.getTranType())) {
                    continue;
                }
                final Collection<Transaction.Version> versions = t.getVersions();
                final List<ChangeFile> files = new ArrayList<ChangeFile>(versions.size());
                for (final Transaction.Version v : versions) {
                    final FileDifference difference = differencesMap.get(v.getElementId());
                    if (difference != null) {
                        final String newVersionSpec = difference.getNewVersionSpec();
                        if (newVersionSpec != null && newVersionSpec.equals(v.getRealSpec())) {
                            if (this.getLogger().isDebugEnabled()) {
                                this.getLogger().debug("Removing difference for " + v);
                            }
                            differencesMap.remove(v.getElementId());
                        }
                    }
                    if (v.getRealSpec().startsWith(streamPrefix) && !v.getVirtualSpec().startsWith(streamPrefix)) {
                        if (!this.getLogger().isDebugEnabled()) {
                            continue;
                        }
                        this.getLogger().debug("Skipping workspace to basis stream promote " + v);
                    }
                    else {
                        final ChangeFile f = new ChangeFile(v.getElementName(), v.getVirtualSpec() + " (" + v.getRealSpec() + ")");
                        files.add(f);
                    }
                }
                if (versions.isEmpty() || !files.isEmpty()) {
                    final ChangeSet changeSet = new ChangeSet(t.getWhen(), t.getComment(), t.getAuthor(), files);
                    entries.add(changeSet);
                }
                else {
                    if (!this.getLogger().isDebugEnabled()) {
                        continue;
                    }
                    this.getLogger().debug("All versions removed for " + t);
                }
            }
        }
        if (!differencesMap.isEmpty()) {
            final List<ChangeFile> upstreamFiles = new ArrayList<ChangeFile>();
            for (final FileDifference difference2 : differencesMap.values()) {
                if (difference2.getNewVersionSpec() != null) {
                    upstreamFiles.add(new ChangeFile(difference2.getNewFile().getPath(), difference2.getNewVersionSpec()));
                }
                else {
                    upstreamFiles.add(new ChangeFile(difference2.getOldFile().getPath(), null));
                }
            }
            entries.add(new ChangeSet(endDate, "Upstream changes", "various", upstreamFiles));
        }
        return new ChangeLogSet(entries, startDate, endDate);
    }
    
    public ChangeLogScmResult changelog(final ScmProviderRepository repo, final ScmFileSet testFileSet, final CommandParameters params) throws ScmException {
        return (ChangeLogScmResult)this.execute(repo, testFileSet, params);
    }
}
