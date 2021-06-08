// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn.svnexe.command.blame;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.TimeZone;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class SvnBlameConsumer extends AbstractConsumer
{
    private static final String SVN_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final Pattern LINE_PATTERN;
    private static final Pattern REVISION_PATTERN;
    private static final Pattern AUTHOR_PATTERN;
    private static final Pattern DATE_PATTERN;
    private SimpleDateFormat dateFormat;
    private List<BlameLine> lines;
    private int lineNumber;
    private String revision;
    private String author;
    
    public SvnBlameConsumer(final ScmLogger logger) {
        super(logger);
        this.lines = new ArrayList<BlameLine>();
        (this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    public void consumeLine(final String line) {
        Matcher matcher;
        if ((matcher = SvnBlameConsumer.LINE_PATTERN.matcher(line)).find()) {
            final String lineNumberStr = matcher.group(1);
            this.lineNumber = Integer.parseInt(lineNumberStr);
        }
        else if ((matcher = SvnBlameConsumer.REVISION_PATTERN.matcher(line)).find()) {
            this.revision = matcher.group(1);
        }
        else if ((matcher = SvnBlameConsumer.AUTHOR_PATTERN.matcher(line)).find()) {
            this.author = matcher.group(1);
        }
        else if ((matcher = SvnBlameConsumer.DATE_PATTERN.matcher(line)).find()) {
            final String date = matcher.group(1);
            final String time = matcher.group(2);
            final Date dateTime = this.parseDateTime(date + " " + time);
            this.lines.add(new BlameLine(dateTime, this.revision, this.author));
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Author of line " + this.lineNumber + ": " + this.author + " (" + date + ")");
            }
        }
    }
    
    protected Date parseDateTime(final String dateTimeStr) {
        try {
            return this.dateFormat.parse(dateTimeStr);
        }
        catch (ParseException e) {
            this.getLogger().error("skip ParseException: " + e.getMessage() + " during parsing date " + dateTimeStr, e);
            return null;
        }
    }
    
    public List<BlameLine> getLines() {
        return this.lines;
    }
    
    static {
        LINE_PATTERN = Pattern.compile("line-number=\"(.*)\"");
        REVISION_PATTERN = Pattern.compile("revision=\"(.*)\"");
        AUTHOR_PATTERN = Pattern.compile("<author>(.*)</author>");
        DATE_PATTERN = Pattern.compile("<date>(.*)T(.*)\\.(.*)Z</date>");
    }
}
