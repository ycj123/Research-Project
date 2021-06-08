// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev.cli;

import java.util.regex.Matcher;
import java.io.File;
import java.util.Collection;
import java.util.regex.Pattern;
import org.codehaus.plexus.util.cli.StreamConsumer;

public class StatBackingConsumer implements StreamConsumer
{
    private static final Pattern STAT_PATTERN;
    private static final String NO_SUCH_ELEM = "no such elem";
    private Collection<File> memberElements;
    private Collection<File> nonMemberElements;
    
    public StatBackingConsumer(final Collection<File> memberElements, final Collection<File> nonMemberElements) {
        this.memberElements = memberElements;
        this.nonMemberElements = nonMemberElements;
    }
    
    public void consumeLine(final String line) {
        final Pattern pattern = StatBackingConsumer.STAT_PATTERN;
        final Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            final File file = new File(matcher.group(1));
            final String indicator = matcher.group(2);
            if ("no such elem".equals(indicator)) {
                this.nonMemberElements.add(file);
            }
            else {
                this.memberElements.add(file);
            }
        }
    }
    
    static {
        STAT_PATTERN = Pattern.compile("\\s*(\\S+)\\s+.*\\(([^()]+)\\)\\s*");
    }
}
