// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.cvslib.command.blame;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.Locale;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class CvsBlameConsumer extends AbstractConsumer
{
    private static final String CVS_TIMESTAMP_PATTERN = "dd-MMM-yy";
    private static final Pattern LINE_PATTERN;
    private List<BlameLine> lines;
    
    public CvsBlameConsumer(final ScmLogger logger) {
        super(logger);
        this.lines = new ArrayList<BlameLine>();
    }
    
    public void consumeLine(final String line) {
        if (line != null && line.indexOf(58) > 0) {
            final String annotation = line.substring(0, line.indexOf(58));
            final Matcher matcher = CvsBlameConsumer.LINE_PATTERN.matcher(annotation);
            if (matcher.matches()) {
                final String revision = matcher.group(1).trim();
                final String author = matcher.group(2).trim();
                final String dateTimeStr = matcher.group(3).trim();
                final Date dateTime = this.parseDate(dateTimeStr, null, "dd-MMM-yy", Locale.US);
                this.lines.add(new BlameLine(dateTime, revision, author));
                if (this.getLogger().isDebugEnabled()) {
                    this.getLogger().debug(author + " " + dateTimeStr);
                }
            }
        }
    }
    
    public List<BlameLine> getLines() {
        return this.lines;
    }
    
    static {
        LINE_PATTERN = Pattern.compile("(.*)\\((.*)\\s+(.*)\\)");
    }
}
