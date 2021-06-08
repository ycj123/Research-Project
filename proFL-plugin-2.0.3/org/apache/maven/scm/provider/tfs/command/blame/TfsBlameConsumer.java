// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.tfs.command.blame;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class TfsBlameConsumer extends AbstractConsumer
{
    private static final String TFS_TIMESTAMP_PATTERN = "MM/dd/yyyy";
    private static final Pattern LINE_PATTERN;
    private List<BlameLine> lines;
    
    public TfsBlameConsumer(final ScmLogger logger) {
        super(logger);
        this.lines = new ArrayList<BlameLine>();
    }
    
    public void consumeLine(final String line) {
        final Matcher matcher = TfsBlameConsumer.LINE_PATTERN.matcher(line);
        if (matcher.find()) {
            final String revision = matcher.group(1).trim();
            final String author = matcher.group(2).trim();
            final String dateStr = matcher.group(3).trim();
            final Date date = this.parseDate(dateStr, null, "MM/dd/yyyy");
            this.lines.add(new BlameLine(date, revision, author));
        }
    }
    
    public List<BlameLine> getLines() {
        return this.lines;
    }
    
    static {
        LINE_PATTERN = Pattern.compile("([^ ]+)[ ]+([^ ]+)[ ]+([^ ]+)");
    }
}
