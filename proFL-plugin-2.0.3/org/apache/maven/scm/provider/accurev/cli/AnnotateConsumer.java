// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import java.util.Date;
import java.util.regex.Matcher;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class AnnotateConsumer extends AbstractConsumer
{
    private static final Pattern LINE_PATTERN;
    private List<BlameLine> lines;
    
    public AnnotateConsumer(final List<BlameLine> lines, final ScmLogger scmLogger) {
        super(scmLogger);
        this.lines = lines;
    }
    
    public void consumeLine(final String line) {
        final Matcher matcher = AnnotateConsumer.LINE_PATTERN.matcher(line);
        if (matcher.matches()) {
            final String revision = matcher.group(1).trim();
            final String author = matcher.group(2).trim();
            final String dateStr = matcher.group(3).trim();
            final Date date = this.parseDate(dateStr, null, "yyyy/MM/dd HH:mm:ss");
            this.lines.add(new BlameLine(date, revision, author));
            return;
        }
        throw new RuntimeException("Unable to parse annotation from line: " + line);
    }
    
    public List<BlameLine> getLines() {
        return this.lines;
    }
    
    static {
        LINE_PATTERN = Pattern.compile("^\\s+(\\d+)\\s+(\\w+)\\s+([0-9/]+ [0-9:]+).*");
    }
}
