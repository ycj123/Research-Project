// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.blame;

import java.util.regex.Matcher;
import java.util.Date;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.command.blame.BlameLine;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.util.AbstractConsumer;

public class PerforceBlameConsumer extends AbstractConsumer
{
    private static final Pattern LINE_PATTERN;
    private List<BlameLine> lines;
    
    public PerforceBlameConsumer(final ScmLogger logger) {
        super(logger);
        this.lines = new ArrayList<BlameLine>();
    }
    
    public void consumeLine(final String line) {
        final Matcher matcher = PerforceBlameConsumer.LINE_PATTERN.matcher(line);
        if (matcher.find()) {
            final String revision = matcher.group(1).trim();
            this.lines.add(new BlameLine(null, revision, null));
        }
    }
    
    public List<BlameLine> getLines() {
        return this.lines;
    }
    
    static {
        LINE_PATTERN = Pattern.compile("(\\d+):");
    }
}
