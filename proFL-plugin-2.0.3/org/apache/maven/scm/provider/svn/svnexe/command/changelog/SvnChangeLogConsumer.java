// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.changelog;

import java.util.Date;
import org.apache.maven.scm.ChangeFile;
import org.apache.maven.scm.ScmFileStatus;
import java.util.regex.Matcher;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.svn.SvnChangeSet;
import org.apache.maven.scm.ChangeSet;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class SvnChangeLogConsumer extends AbstractConsumer
{
    private static final String SVN_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss zzzzzzzzz";
    private static final int GET_HEADER = 1;
    private static final int GET_FILE = 2;
    private static final int GET_COMMENT = 3;
    private static final Pattern FILE_PATTERN;
    private static final Pattern ORIG_FILE_PATTERN;
    private static final String FILE_END_TOKEN = "";
    private static final String COMMENT_END_TOKEN = "------------------------------------------------------------------------";
    private int status;
    private List<ChangeSet> entries;
    private SvnChangeSet currentChange;
    private String currentRevision;
    private StringBuilder currentComment;
    private static final Pattern HEADER_REG_EXP;
    private static final int REVISION_GROUP = 1;
    private static final int AUTHOR_GROUP = 2;
    private static final int DATE_GROUP = 3;
    private static final Pattern REVISION_REG_EXP1;
    private static final Pattern REVISION_REG_EXP2;
    private static final Pattern DATE_REG_EXP;
    private final String userDateFormat;
    
    public SvnChangeLogConsumer(final ScmLogger logger, final String userDateFormat) {
        super(logger);
        this.status = 1;
        this.entries = new ArrayList<ChangeSet>();
        this.userDateFormat = userDateFormat;
    }
    
    public List<ChangeSet> getModifications() {
        return this.entries;
    }
    
    public void consumeLine(final String line) {
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(line);
        }
        switch (this.status) {
            case 1: {
                this.processGetHeader(line);
                break;
            }
            case 2: {
                this.processGetFile(line);
                break;
            }
            case 3: {
                this.processGetComment(line);
                break;
            }
            default: {
                throw new IllegalStateException("Unknown state: " + this.status);
            }
        }
    }
    
    private void processGetHeader(final String line) {
        final Matcher matcher = SvnChangeLogConsumer.HEADER_REG_EXP.matcher(line);
        if (!matcher.matches()) {
            return;
        }
        this.currentRevision = this.getRevision(matcher.group(1));
        (this.currentChange = new SvnChangeSet()).setAuthor(matcher.group(2));
        this.currentChange.setDate(this.getDate(matcher.group(3)));
        this.currentChange.setRevision(this.currentRevision);
        this.status = 2;
    }
    
    private String getRevision(final String revisionOutput) {
        Matcher matcher;
        if ((matcher = SvnChangeLogConsumer.REVISION_REG_EXP1.matcher(revisionOutput)).matches()) {
            return matcher.group(1);
        }
        if ((matcher = SvnChangeLogConsumer.REVISION_REG_EXP2.matcher(revisionOutput)).matches()) {
            return matcher.group(1);
        }
        throw new IllegalOutputException(revisionOutput);
    }
    
    private void processGetFile(final String line) {
        final Matcher matcher = SvnChangeLogConsumer.FILE_PATTERN.matcher(line);
        if (matcher.matches()) {
            String name;
            final String fileinfo = name = matcher.group(2);
            String originalName = null;
            String originalRev = null;
            final int n = fileinfo.indexOf(" (");
            if (n > 1 && fileinfo.endsWith(")")) {
                final String origFileInfo = fileinfo.substring(n);
                final Matcher matcher2 = SvnChangeLogConsumer.ORIG_FILE_PATTERN.matcher(origFileInfo);
                if (matcher2.find()) {
                    name = fileinfo.substring(0, n);
                    originalName = matcher2.group(1);
                    originalRev = matcher2.group(2);
                }
            }
            final String actionStr = matcher.group(1);
            ScmFileStatus action;
            if ("A".equals(actionStr)) {
                action = ((originalRev == null) ? ScmFileStatus.ADDED : ScmFileStatus.COPIED);
            }
            else if ("D".equals(actionStr)) {
                action = ScmFileStatus.DELETED;
            }
            else if ("M".equals(actionStr)) {
                action = ScmFileStatus.MODIFIED;
            }
            else if ("R".equals(actionStr)) {
                action = ScmFileStatus.UPDATED;
            }
            else {
                action = ScmFileStatus.UNKNOWN;
            }
            System.out.println(actionStr + " : " + name);
            final ChangeFile changeFile = new ChangeFile(name, this.currentRevision);
            changeFile.setAction(action);
            changeFile.setOriginalName(originalName);
            changeFile.setOriginalRevision(originalRev);
            this.currentChange.addFile(changeFile);
            this.status = 2;
        }
        else if (line.equals("")) {
            this.currentComment = new StringBuilder();
            this.status = 3;
        }
    }
    
    private void processGetComment(final String line) {
        if (line.equals("------------------------------------------------------------------------")) {
            this.currentChange.setComment(this.currentComment.toString());
            this.entries.add(this.currentChange);
            this.status = 1;
        }
        else {
            this.currentComment.append(line).append('\n');
        }
    }
    
    private Date getDate(final String dateOutput) {
        final Matcher matcher = SvnChangeLogConsumer.DATE_REG_EXP.matcher(dateOutput);
        if (!matcher.find()) {
            throw new IllegalOutputException(dateOutput);
        }
        final StringBuilder date = new StringBuilder();
        date.append(matcher.group(1));
        date.append(" GMT");
        date.append(matcher.group(2));
        date.append(matcher.group(3));
        date.append(':');
        date.append(matcher.group(4));
        return this.parseDate(date.toString(), this.userDateFormat, "yyyy-MM-dd HH:mm:ss zzzzzzzzz");
    }
    
    static {
        FILE_PATTERN = Pattern.compile("^\\s\\s\\s([A-Z])\\s(.+)$");
        ORIG_FILE_PATTERN = Pattern.compile("\\([A-Za-z]+ (.+):(\\d+)\\)");
        HEADER_REG_EXP = Pattern.compile("^(.+) \\| (.+) \\| (.+) \\|.*$");
        REVISION_REG_EXP1 = Pattern.compile("rev (\\d+):");
        REVISION_REG_EXP2 = Pattern.compile("r(\\d+)");
        DATE_REG_EXP = Pattern.compile("(\\d+-\\d+-\\d+ \\d+:\\d+:\\d+) ([\\-+])(\\d\\d)(\\d\\d)");
    }
}
