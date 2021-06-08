// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.command.blame;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class ClearCaseBlameConsumer extends AbstractConsumer
{
    private static final String CLEARCASE_TIMESTAMP_PATTERN = "yyyyMMdd.HHmmss";
    private static final Pattern LINE_PATTERN;
    private List<BlameLine> lines;
    
    public ClearCaseBlameConsumer(final ScmLogger logger) {
        super(logger);
        this.lines = new ArrayList<BlameLine>();
    }
    
    public void consumeLine(final String line) {
        final Matcher matcher = ClearCaseBlameConsumer.LINE_PATTERN.matcher(line);
        if (matcher.matches()) {
            final String revision = matcher.group(1);
            final String author = matcher.group(2).toLowerCase();
            final String dateTimeStr = matcher.group(3);
            final Date dateTime = this.parseDate(dateTimeStr, null, "yyyyMMdd.HHmmss");
            this.lines.add(new BlameLine(dateTime, revision, author));
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug(author + " " + dateTimeStr);
            }
        }
    }
    
    public List<BlameLine> getLines() {
        return this.lines;
    }
    
    static {
        LINE_PATTERN = Pattern.compile("VERSION:(.*)@@@USER:(.*)@@@DATE:(.*)@@@(.*)");
    }
}
