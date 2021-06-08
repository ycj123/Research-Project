// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

import java.util.Map;
import java.util.List;
import java.util.Date;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.ScmVersion;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.maven.scm.provider.accurev.util.WorkspaceUtils;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepositoryWithHost;

public class AccuRevScmProviderRepository extends ScmProviderRepositoryWithHost
{
    public static final String DEFAULT_TAG_FORMAT = "%s";
    private AccuRev accurev;
    private String streamName;
    private String projectPath;
    private String tagFormat;
    private ScmLogger logger;
    private String checkoutRelativePath;
    private boolean shouldUseExportForNonPersistentCheckout;
    
    public AccuRevScmProviderRepository() {
        this.tagFormat = "%s";
        this.setPersistCheckout(this.shouldUseExportForNonPersistentCheckout = true);
        this.setShouldUseExportForNonPersistentCheckout(true);
    }
    
    public String getTagFormat() {
        return this.tagFormat;
    }
    
    public void setTagFormat(final String tagFormat) {
        if (tagFormat == null || !tagFormat.contains("%s")) {
            throw new IllegalArgumentException("tagFormat must contain '%s' to be replaced");
        }
        this.tagFormat = tagFormat;
    }
    
    public String getStreamName() {
        return this.streamName;
    }
    
    public void setStreamName(final String streamName) {
        this.streamName = streamName;
    }
    
    public String getProjectPath() {
        return this.projectPath;
    }
    
    public void setProjectPath(final String projectPath) {
        this.setCheckoutRelativePath(this.projectPath = projectPath);
    }
    
    public AccuRev getAccuRev() {
        return this.accurev;
    }
    
    public void setAccuRev(final AccuRev accurev) {
        this.accurev = accurev;
    }
    
    public boolean isWorkSpaceRoot(final AccuRevInfo info) {
        final String p = this.getProjectPath();
        return (p != null && WorkspaceUtils.isSameFile(info.getBasedir(), new File(info.getTop(), p))) || this.isWorkSpaceTop(info);
    }
    
    public boolean isWorkSpaceTop(final AccuRevInfo info) {
        return info.isWorkSpaceTop();
    }
    
    String tagToStream(final String tagName) {
        return String.format(this.getTagFormat(), tagName);
    }
    
    String streamToTag(final String streamName) {
        this.tagFormat = this.getTagFormat();
        final String tagPatternString = this.tagToStream("(.*)");
        final Pattern tagPattern = Pattern.compile(tagPatternString);
        final Matcher tagMatcher = tagPattern.matcher(streamName);
        if (tagMatcher.matches()) {
            return tagMatcher.group(1);
        }
        return streamName;
    }
    
    public void setLogger(final ScmLogger logger) {
        this.logger = logger;
    }
    
    public String getCheckoutRelativePath() {
        if (this.checkoutRelativePath == null) {
            return "";
        }
        return this.checkoutRelativePath;
    }
    
    public void setCheckoutRelativePath(final String checkoutRelativePath) {
        this.checkoutRelativePath = checkoutRelativePath;
    }
    
    public String getExportRelativePath() {
        return this.getCheckoutRelativePath();
    }
    
    public boolean shouldUseExportForNonPersistentCheckout() {
        return this.shouldUseExportForNonPersistentCheckout;
    }
    
    public void setShouldUseExportForNonPersistentCheckout(final boolean shouldUseExportForNonPersistentCheckout) {
        this.shouldUseExportForNonPersistentCheckout = shouldUseExportForNonPersistentCheckout;
    }
    
    public String getDepotRelativeProjectPath() {
        return "/./" + ((this.projectPath == null) ? "" : this.projectPath);
    }
    
    public AccuRevVersion getAccuRevVersion(final ScmVersion scmVersion) {
        String tran = null;
        String basisStream = null;
        if (scmVersion == null) {
            basisStream = this.getStreamName();
        }
        else {
            final String name = StringUtils.clean(scmVersion.getName());
            final String[] versionComponents = name.split("[/\\\\]", 2);
            basisStream = versionComponents[0];
            if (basisStream.length() == 0) {
                basisStream = this.getStreamName();
            }
            else {
                basisStream = this.tagToStream(basisStream);
            }
            if (versionComponents.length == 2 && versionComponents[1].length() > 0) {
                tran = versionComponents[1];
            }
        }
        return new AccuRevVersion(basisStream, tran);
    }
    
    public String getSnapshotName(final String tagName) {
        return this.tagToStream(tagName);
    }
    
    public String getRevision(final String streamName, final Date date) {
        return this.getRevision(streamName, AccuRev.ACCUREV_TIME_SPEC.format((date == null) ? new Date() : date));
    }
    
    public String getRevision(final String stream, final long fromTranId) {
        return this.getRevision(stream, Long.toString(fromTranId));
    }
    
    public String getRevision(final String streamName, final String transaction) {
        return this.streamToTag(streamName) + "/" + transaction;
    }
    
    public String getWorkSpaceRevision(final String workspace) throws AccuRevException {
        return this.getRevision(workspace, Long.toString(this.getCurrentTransactionId(workspace)));
    }
    
    public Transaction getDepotTransaction(final String stream, String tranSpec) throws AccuRevException {
        if (tranSpec == null) {
            tranSpec = "now";
        }
        final List<Transaction> transactions = this.getAccuRev().history(stream, tranSpec, null, 1, true, true);
        if (transactions == null || transactions.isEmpty()) {
            this.logger.warn("Unable to find transaction for tranSpec=" + tranSpec);
            return null;
        }
        return transactions.get(0);
    }
    
    public String getDepotTransactionId(final String stream, final String tranSpec) throws AccuRevException {
        final Transaction t = this.getDepotTransaction(stream, tranSpec);
        return (t == null) ? tranSpec : Long.toString(t.getTranId());
    }
    
    private long getCurrentTransactionId(final String workSpaceName) throws AccuRevException {
        final AccuRev accuRev = this.getAccuRev();
        Map<String, WorkSpace> workSpaces = accuRev.showWorkSpaces();
        WorkSpace workspace = workSpaces.get(workSpaceName);
        if (workspace == null) {
            workSpaces = accuRev.showRefTrees();
            workspace = workSpaces.get(workSpaceName);
        }
        if (workspace == null) {
            throw new AccuRevException("Can't find workspace " + workSpaceName);
        }
        return workspace.getTransactionId();
    }
    
    @Override
    public String toString() {
        final StringBuilder buff = new StringBuilder("AccuRevScmProviderRepository");
        buff.append(" user=");
        buff.append(this.getUser());
        buff.append(" pass=");
        buff.append((this.getPassword() == null) ? "null" : StringUtils.repeat("*", this.getPassword().length()));
        buff.append(" host=");
        buff.append(this.getHost());
        buff.append(" port=");
        buff.append(this.getPort());
        buff.append(" stream=");
        buff.append(this.getStreamName());
        buff.append(" projectPath=");
        buff.append(this.getProjectPath());
        return buff.toString();
    }
    
    public static String formatTimeSpec(final Date when) {
        if (when == null) {
            return "now";
        }
        return AccuRev.ACCUREV_TIME_SPEC.format(when);
    }
}
